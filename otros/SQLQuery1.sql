USE GimnasioDB;
GO

CREATE OR ALTER PROCEDURE sp_ActualizarMembresiasVencidas
AS
BEGIN
    SET NOCOUNT ON;

    UPDATE Membresia
    SET Estado = 'VENCIDA'
    WHERE Estado = 'ACTIVA'
      AND FechaVencimiento < CAST(GETDATE() AS DATE);
END;
GO