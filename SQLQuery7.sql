USE GimnasioDB;
GO

/* ============================================================
   20. SERVICIO
   ============================================================ */

CREATE TABLE Servicio (
    ServicioID INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL UNIQUE,
    Descripcion VARCHAR(250),
    Precio DECIMAL(10,2) NOT NULL,
    Estado BIT NOT NULL DEFAULT 1,

    CONSTRAINT CK_Servicio_Precio
        CHECK (Precio >= 0)
);
GO


/* ============================================================
   21. CLASE
   ============================================================ */

CREATE TABLE Clase (
    ClaseID INT IDENTITY(1,1) PRIMARY KEY,
    ServicioID INT NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion VARCHAR(250),
    CupoMaximo INT NOT NULL,
    Estado BIT NOT NULL DEFAULT 1,

    CONSTRAINT FK_Clase_Servicio
        FOREIGN KEY (ServicioID)
        REFERENCES Servicio(ServicioID),

    CONSTRAINT CK_Clase_Cupo
        CHECK (CupoMaximo > 0)
);
GO


/* ============================================================
   22. HORARIO DE CLASE
   ============================================================ */

CREATE TABLE HorarioClase (
    HorarioID INT IDENTITY(1,1) PRIMARY KEY,
    ClaseID INT NOT NULL,
    EntrenadorID INT NOT NULL,
    Fecha DATE NOT NULL,
    HoraInicio TIME NOT NULL,
    HoraFin TIME NOT NULL,

    CONSTRAINT FK_HorarioClase_Clase
        FOREIGN KEY (ClaseID)
        REFERENCES Clase(ClaseID),

    CONSTRAINT FK_HorarioClase_Entrenador
        FOREIGN KEY (EntrenadorID)
        REFERENCES Entrenador(EntrenadorID),

    CONSTRAINT CK_HorarioClase_Horas
        CHECK (HoraFin > HoraInicio)
);
GO


/* ============================================================
   23. INSCRIPCIÓN A CLASE
   ============================================================ */

CREATE TABLE InscripcionClase (
    InscripcionID INT IDENTITY(1,1) PRIMARY KEY,
    ClaseID INT NOT NULL,
    ClienteID INT NOT NULL,
    FechaInscripcion DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    Estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVA',

    CONSTRAINT FK_Inscripcion_Clase
        FOREIGN KEY (ClaseID)
        REFERENCES Clase(ClaseID),

    CONSTRAINT FK_Inscripcion_Cliente
        FOREIGN KEY (ClienteID)
        REFERENCES Cliente(ClienteID),

    CONSTRAINT UQ_Inscripcion_Cliente_Clase
        UNIQUE (ClaseID, ClienteID),

    CONSTRAINT CK_Inscripcion_Estado
        CHECK (Estado IN ('ACTIVA', 'CANCELADA', 'FINALIZADA'))
);
GO


/* ============================================================
   24. RESERVA
   ============================================================ */

CREATE TABLE Reserva (
    ReservaID INT IDENTITY(1,1) PRIMARY KEY,
    ClienteID INT NOT NULL,
    ServicioID INT NOT NULL,
    Fecha DATE NOT NULL,
    HoraInicio TIME NOT NULL,
    HoraFin TIME NULL,
    Estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    Observaciones VARCHAR(500),

    CONSTRAINT FK_Reserva_Cliente
        FOREIGN KEY (ClienteID)
        REFERENCES Cliente(ClienteID),

    CONSTRAINT FK_Reserva_Servicio
        FOREIGN KEY (ServicioID)
        REFERENCES Servicio(ServicioID),

    CONSTRAINT CK_Reserva_Horas
        CHECK (HoraFin IS NULL OR HoraFin > HoraInicio),

    CONSTRAINT CK_Reserva_Estado
        CHECK (Estado IN (
            'PENDIENTE',
            'CONFIRMADA',
            'CANCELADA',
            'FINALIZADA'
        ))
);
GO


/* ============================================================
   25. NOTIFICACIÓN
   ============================================================ */

CREATE TABLE Notificacion (
    NotificacionID INT IDENTITY(1,1) PRIMARY KEY,
    ClienteID INT NOT NULL,
    Titulo VARCHAR(150) NOT NULL,
    Mensaje VARCHAR(500) NOT NULL,
    FechaEnvio DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    Leida BIT NOT NULL DEFAULT 0,

    CONSTRAINT FK_Notificacion_Cliente
        FOREIGN KEY (ClienteID)
        REFERENCES Cliente(ClienteID)
);
GO


/* ============================================================
   26. PROGRESO DEL CLIENTE
   ============================================================ */

CREATE TABLE ProgresoCliente (
    ProgresoID INT IDENTITY(1,1) PRIMARY KEY,
    ClienteID INT NOT NULL,
    Fecha DATE NOT NULL,
    Peso DECIMAL(6,2),
    Observaciones VARCHAR(500),

    CONSTRAINT FK_Progreso_Cliente
        FOREIGN KEY (ClienteID)
        REFERENCES Cliente(ClienteID),

    CONSTRAINT CK_Progreso_Peso
        CHECK (Peso IS NULL OR Peso > 0)
);
GO