USE GimnasioDB;
GO

/* ============================================================
   9. GRUPO MUSCULAR
   ============================================================ */

CREATE TABLE GrupoMuscular (
    GrupoMuscularID INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL UNIQUE,
    Descripcion VARCHAR(250)
);
GO


/* ============================================================
   10. NIVEL DE DIFICULTAD
   ============================================================ */

CREATE TABLE NivelDificultad (
    NivelDificultadID INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(30) NOT NULL UNIQUE,
    Descripcion VARCHAR(250)
);
GO


/* ============================================================
   11. ÁREA DEL GIMNASIO
   ============================================================ */

CREATE TABLE AreaGimnasio (
    AreaID INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL UNIQUE,
    Descripcion VARCHAR(250),
    Estado BIT NOT NULL DEFAULT 1
);
GO


/* ============================================================
   12. EQUIPAMIENTO
   ============================================================ */

CREATE TABLE Equipamiento (
    EquipamientoID INT IDENTITY(1,1) PRIMARY KEY,
    AreaID INT NOT NULL,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion VARCHAR(250),
    Estado BIT NOT NULL DEFAULT 1,

    CONSTRAINT FK_Equipamiento_Area
        FOREIGN KEY (AreaID)
        REFERENCES AreaGimnasio(AreaID),

    CONSTRAINT UQ_Equipamiento_Area_Nombre
        UNIQUE (AreaID, Nombre)
);
GO


/* ============================================================
   13. EJERCICIO
   ============================================================ */

CREATE TABLE Ejercicio (
    EjercicioID INT IDENTITY(1,1) PRIMARY KEY,
    GrupoMuscularID INT NOT NULL,
    NivelDificultadID INT NOT NULL,
    EquipamientoID INT NULL,
    Nombre VARCHAR(100) NOT NULL UNIQUE,
    Descripcion VARCHAR(250),
    Estado BIT NOT NULL DEFAULT 1,

    CONSTRAINT FK_Ejercicio_Grupo
        FOREIGN KEY (GrupoMuscularID)
        REFERENCES GrupoMuscular(GrupoMuscularID),

    CONSTRAINT FK_Ejercicio_Nivel
        FOREIGN KEY (NivelDificultadID)
        REFERENCES NivelDificultad(NivelDificultadID),

    CONSTRAINT FK_Ejercicio_Equipamiento
        FOREIGN KEY (EquipamientoID)
        REFERENCES Equipamiento(EquipamientoID)
);
GO