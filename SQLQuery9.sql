USE GimnasioDB;
GO

CREATE INDEX IX_ProgresoCliente_Cliente_Fecha
ON ProgresoCliente(ClienteID, Fecha);
GO