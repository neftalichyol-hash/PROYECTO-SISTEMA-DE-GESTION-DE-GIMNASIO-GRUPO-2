USE GimnasioDB;
GO

/* ============================================================
   1. USUARIO
   ============================================================ */

CREATE TABLE Usuario (
    UsuarioID INT IDENTITY(1,1) PRIMARY KEY,
    NombreUsuario VARCHAR(50) NOT NULL UNIQUE,
    Correo VARCHAR(100) NOT NULL UNIQUE,
    Contrasena VARCHAR(255) NOT NULL,
    Rol VARCHAR(20) NOT NULL,
    Estado BIT NOT NULL DEFAULT 1,
    FechaRegistro DATETIME2 NOT NULL DEFAULT SYSDATETIME(),

    CONSTRAINT CK_Usuario_Rol
        CHECK (Rol IN ('ADMINISTRADOR', 'ENTRENADOR', 'CLIENTE'))
);
GO


/* ============================================================
   2. CLIENTE
   ============================================================ */

CREATE TABLE Cliente (
    ClienteID INT IDENTITY(1,1) PRIMARY KEY,
    UsuarioID INT NOT NULL UNIQUE,
    Nombres VARCHAR(100) NOT NULL,
    Apellidos VARCHAR(100) NOT NULL,
    Telefono VARCHAR(20),
    FechaNacimiento DATE,
    FechaRegistro DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    Estado BIT NOT NULL DEFAULT 1,

    CONSTRAINT FK_Cliente_Usuario
        FOREIGN KEY (UsuarioID)
        REFERENCES Usuario(UsuarioID)
);
GO


/* ============================================================
   3. ENTRENADOR
   ============================================================ */

CREATE TABLE Entrenador (
    EntrenadorID INT IDENTITY(1,1) PRIMARY KEY,
    UsuarioID INT NOT NULL UNIQUE,
    Nombres VARCHAR(100) NOT NULL,
    Apellidos VARCHAR(100) NOT NULL,
    Telefono VARCHAR(20),
    Especialidad VARCHAR(100),
    Estado BIT NOT NULL DEFAULT 1,

    CONSTRAINT FK_Entrenador_Usuario
        FOREIGN KEY (UsuarioID)
        REFERENCES Usuario(UsuarioID)
);
GO