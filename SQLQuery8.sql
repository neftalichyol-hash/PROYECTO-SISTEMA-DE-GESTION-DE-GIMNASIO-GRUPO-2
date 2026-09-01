USE GimnasioDB;
GO

CREATE INDEX IX_Membresia_Cliente
ON Membresia(ClienteID);
GO

CREATE INDEX IX_Membresia_FechaVencimiento
ON Membresia(FechaVencimiento);
GO

CREATE INDEX IX_Pago_FechaPago
ON Pago(FechaPago);
GO

CREATE INDEX IX_Asistencia_Cliente_Fecha
ON Asistencia(ClienteID, Fecha);
GO

CREATE INDEX IX_Rutina_Cliente
ON Rutina(ClienteID);
GO

CREATE INDEX IX_Rutina_Entrenador
ON Rutina(EntrenadorID);
GO

CREATE INDEX IX_RutinaRealizada_Cliente_Fecha
ON RutinaRealizada(ClienteID, Fecha);
GO

CREATE INDEX IX_Ejercicio_GrupoMuscular
ON Ejercicio(GrupoMuscularID);
GO

CREATE INDEX IX_InscripcionClase_Cliente
ON InscripcionClase(ClienteID);
GO

CREATE INDEX IX_Reserva_Cliente_Fecha
ON Reserva(ClienteID, Fecha);
GO