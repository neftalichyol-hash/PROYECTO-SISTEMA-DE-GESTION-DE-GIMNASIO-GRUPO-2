USE GimnasioDB;
GO

CREATE TABLE TokenRecuperacion (
    Id INT IDENTITY(1,1) PRIMARY KEY,

    UsuarioID INT NOT NULL,

    Token VARCHAR(255) NOT NULL,

    FechaExpiracion DATETIME2 NOT NULL,

    Usado BIT NOT NULL DEFAULT 0,

    CONSTRAINT FK_TokenRecuperacion_Usuario
        FOREIGN KEY (UsuarioID)
        REFERENCES Usuario(UsuarioID),

    CONSTRAINT UQ_TokenRecuperacion_Token
        UNIQUE (Token)
);
GO