USE GimnasioDB;
GO

/* ============================================================
   4. TIPO DE MEMBRESÍA
   ============================================================ */

CREATE TABLE TipoMembresia (
    TipoMembresiaID INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(50) NOT NULL UNIQUE,
    DuracionMeses INT NOT NULL,
    Precio DECIMAL(10,2) NOT NULL,
    Descripcion VARCHAR(250),
    Estado BIT NOT NULL DEFAULT 1,

    CONSTRAINT CK_TipoMembresia_Duracion
        CHECK (DuracionMeses > 0),

    CONSTRAINT CK_TipoMembresia_Precio
        CHECK (Precio >= 0)
);
GO


/* ============================================================
   5. DESCUENTO
   ============================================================ */

CREATE TABLE Descuento (
    DescuentoID INT IDENTITY(1,1) PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Porcentaje DECIMAL(5,2) NOT NULL,
    FechaInicio DATE NOT NULL,
    FechaFin DATE,
    Estado BIT NOT NULL DEFAULT 1,

    CONSTRAINT CK_Descuento_Porcentaje
        CHECK (Porcentaje >= 0 AND Porcentaje <= 100),

    CONSTRAINT CK_Descuento_Fechas
        CHECK (FechaFin IS NULL OR FechaFin >= FechaInicio)
);
GO


/* ============================================================
   6. MEMBRESÍA
   ============================================================ */

CREATE TABLE Membresia (
    MembresiaID INT IDENTITY(1,1) PRIMARY KEY,
    ClienteID INT NOT NULL,
    TipoMembresiaID INT NOT NULL,
    FechaInicio DATE NOT NULL,
    FechaVencimiento DATE NOT NULL,
    Precio DECIMAL(10,2) NOT NULL,
    Estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVA',

    CONSTRAINT FK_Membresia_Cliente
        FOREIGN KEY (ClienteID)
        REFERENCES Cliente(ClienteID),

    CONSTRAINT FK_Membresia_Tipo
        FOREIGN KEY (TipoMembresiaID)
        REFERENCES TipoMembresia(TipoMembresiaID),

    CONSTRAINT CK_Membresia_Fechas
        CHECK (FechaVencimiento >= FechaInicio),

    CONSTRAINT CK_Membresia_Precio
        CHECK (Precio >= 0),

    CONSTRAINT CK_Membresia_Estado
        CHECK (Estado IN ('ACTIVA', 'SUSPENDIDA', 'VENCIDA'))
);
GO


/* ============================================================
   7. PAGO
   ============================================================ */

CREATE TABLE Pago (
    PagoID INT IDENTITY(1,1) PRIMARY KEY,
    MembresiaID INT NOT NULL,
    DescuentoID INT NULL,
    FechaPago DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    MontoOriginal DECIMAL(10,2) NOT NULL,
    MontoDescuento DECIMAL(10,2) NOT NULL DEFAULT 0,
    MontoPagado DECIMAL(10,2) NOT NULL,
    MetodoPago VARCHAR(20) NOT NULL,
    Estado VARCHAR(20) NOT NULL DEFAULT 'PAGADO',

    CONSTRAINT FK_Pago_Membresia
        FOREIGN KEY (MembresiaID)
        REFERENCES Membresia(MembresiaID),

    CONSTRAINT FK_Pago_Descuento
        FOREIGN KEY (DescuentoID)
        REFERENCES Descuento(DescuentoID),

    CONSTRAINT CK_Pago_MontoOriginal
        CHECK (MontoOriginal >= 0),

    CONSTRAINT CK_Pago_MontoDescuento
        CHECK (MontoDescuento >= 0),

    CONSTRAINT CK_Pago_MontoPagado
        CHECK (MontoPagado >= 0),

    CONSTRAINT CK_Pago_Metodo
        CHECK (MetodoPago IN ('EFECTIVO', 'TARJETA', 'TRANSFERENCIA')),

    CONSTRAINT CK_Pago_Estado
        CHECK (Estado IN ('PAGADO', 'ANULADO'))
);
GO


/* ============================================================
   8. FACTURA
   ============================================================ */

CREATE TABLE Factura (
    FacturaID INT IDENTITY(1,1) PRIMARY KEY,
    PagoID INT NOT NULL UNIQUE,
    NumeroFactura VARCHAR(50) NOT NULL UNIQUE,
    FechaEmision DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    Total DECIMAL(10,2) NOT NULL,

    CONSTRAINT FK_Factura_Pago
        FOREIGN KEY (PagoID)
        REFERENCES Pago(PagoID),

    CONSTRAINT CK_Factura_Total
        CHECK (Total >= 0)
);
GO