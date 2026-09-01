USE GimnasioDB;
GO

/* ============================================================
   14. ASISTENCIA
   ============================================================ */

CREATE TABLE Asistencia (
    AsistenciaID INT IDENTITY(1,1) PRIMARY KEY,
    ClienteID INT NOT NULL,
    Fecha DATE NOT NULL,
    HoraEntrada TIME NOT NULL,
    HoraSalida TIME NULL,
    Estado VARCHAR(20) NOT NULL DEFAULT 'VALIDADA',

    CONSTRAINT FK_Asistencia_Cliente
        FOREIGN KEY (ClienteID)
        REFERENCES Cliente(ClienteID),

    CONSTRAINT CK_Asistencia_Estado
        CHECK (Estado IN ('VALIDADA', 'RECHAZADA')),

    CONSTRAINT CK_Asistencia_Horas
        CHECK (HoraSalida IS NULL OR HoraSalida >= HoraEntrada)
);
GO


/* ============================================================
   15. ASIGNACIÓN DE ENTRENADORES
   ============================================================ */

CREATE TABLE AsignacionEntrenador (
    AsignacionID INT IDENTITY(1,1) PRIMARY KEY,
    EntrenadorID INT NOT NULL,
    ClienteID INT NOT NULL,
    FechaInicio DATE NOT NULL,
    FechaFin DATE NULL,
    Estado BIT NOT NULL DEFAULT 1,

    CONSTRAINT FK_Asignacion_Entrenador
        FOREIGN KEY (EntrenadorID)
        REFERENCES Entrenador(EntrenadorID),

    CONSTRAINT FK_Asignacion_Cliente
        FOREIGN KEY (ClienteID)
        REFERENCES Cliente(ClienteID),

    CONSTRAINT CK_Asignacion_Fechas
        CHECK (FechaFin IS NULL OR FechaFin >= FechaInicio)
);
GO


/* ============================================================
   16. RUTINA
   ============================================================ */

CREATE TABLE Rutina (
    RutinaID INT IDENTITY(1,1) PRIMARY KEY,
    ClienteID INT NOT NULL,
    EntrenadorID INT NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
    Objetivo VARCHAR(250),
    FechaInicio DATE NOT NULL,
    FechaFin DATE NULL,
    Estado BIT NOT NULL DEFAULT 1,

    CONSTRAINT FK_Rutina_Cliente
        FOREIGN KEY (ClienteID)
        REFERENCES Cliente(ClienteID),

    CONSTRAINT FK_Rutina_Entrenador
        FOREIGN KEY (EntrenadorID)
        REFERENCES Entrenador(EntrenadorID),

    CONSTRAINT CK_Rutina_Fechas
        CHECK (FechaFin IS NULL OR FechaFin >= FechaInicio)
);
GO


/* ============================================================
   17. DÍA DE RUTINA
   ============================================================ */

CREATE TABLE DiaRutina (
    DiaRutinaID INT IDENTITY(1,1) PRIMARY KEY,
    RutinaID INT NOT NULL,
    DiaSemana VARCHAR(15) NOT NULL,
    OrdenDia INT NOT NULL,

    CONSTRAINT FK_DiaRutina_Rutina
        FOREIGN KEY (RutinaID)
        REFERENCES Rutina(RutinaID),

    CONSTRAINT CK_DiaRutina_Dia
        CHECK (DiaSemana IN (
            'LUNES',
            'MARTES',
            'MIERCOLES',
            'JUEVES',
            'VIERNES',
            'SABADO',
            'DOMINGO'
        )),

    CONSTRAINT CK_DiaRutina_Orden
        CHECK (OrdenDia > 0),

    CONSTRAINT UQ_DiaRutina_Rutina_Orden
        UNIQUE (RutinaID, OrdenDia)
);
GO


/* ============================================================
   18. RUTINA EJERCICIO
   ============================================================ */

CREATE TABLE RutinaEjercicio (
    RutinaEjercicioID INT IDENTITY(1,1) PRIMARY KEY,
    DiaRutinaID INT NOT NULL,
    EjercicioID INT NOT NULL,
    Series INT NOT NULL,
    Repeticiones INT NOT NULL,
    DescansoSegundos INT NOT NULL DEFAULT 60,
    Observaciones VARCHAR(250),

    CONSTRAINT FK_RutinaEjercicio_Dia
        FOREIGN KEY (DiaRutinaID)
        REFERENCES DiaRutina(DiaRutinaID),

    CONSTRAINT FK_RutinaEjercicio_Ejercicio
        FOREIGN KEY (EjercicioID)
        REFERENCES Ejercicio(EjercicioID),

    CONSTRAINT CK_RutinaEjercicio_Series
        CHECK (Series > 0),

    CONSTRAINT CK_RutinaEjercicio_Repeticiones
        CHECK (Repeticiones > 0),

    CONSTRAINT CK_RutinaEjercicio_Descanso
        CHECK (DescansoSegundos >= 0)
);
GO


/* ============================================================
   19. RUTINA REALIZADA
   ============================================================ */

CREATE TABLE RutinaRealizada (
    RutinaRealizadaID INT IDENTITY(1,1) PRIMARY KEY,
    RutinaID INT NOT NULL,
    ClienteID INT NOT NULL,
    Fecha DATE NOT NULL,
    DuracionMinutos INT,
    CaloriasQuemadas DECIMAL(10,2),
    Observaciones VARCHAR(250),

    CONSTRAINT FK_RutinaRealizada_Rutina
        FOREIGN KEY (RutinaID)
        REFERENCES Rutina(RutinaID),

    CONSTRAINT FK_RutinaRealizada_Cliente
        FOREIGN KEY (ClienteID)
        REFERENCES Cliente(ClienteID),

    CONSTRAINT CK_RutinaRealizada_Duracion
        CHECK (DuracionMinutos IS NULL OR DuracionMinutos > 0),

    CONSTRAINT CK_RutinaRealizada_Calorias
        CHECK (CaloriasQuemadas IS NULL OR CaloriasQuemadas >= 0)
);
GO