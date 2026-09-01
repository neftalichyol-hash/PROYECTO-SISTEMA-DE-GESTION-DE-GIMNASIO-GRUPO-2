USE GimnasioDB;
GO

CREATE OR ALTER PROCEDURE sp_RegistrarAsistencia
    @ClienteID INT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @MembresiaValida INT;

    SELECT TOP 1
        @MembresiaValida = MembresiaID
    FROM Membresia
    WHERE ClienteID = @ClienteID
      AND Estado = 'ACTIVA'
      AND FechaInicio <= CAST(GETDATE() AS DATE)
      AND FechaVencimiento >= CAST(GETDATE() AS DATE)
    ORDER BY FechaVencimiento DESC;

    IF @MembresiaValida IS NULL
    BEGIN
        INSERT INTO Asistencia
        (
            ClienteID,
            Fecha,
            HoraEntrada,
            Estado
        )
        VALUES
        (
            @ClienteID,
            CAST(GETDATE() AS DATE),
            CAST(GETDATE() AS TIME),
            'RECHAZADA'
        );

        PRINT 'Acceso rechazado: el cliente no posee una membresía vigente.';
        RETURN;
    END;

    INSERT INTO Asistencia
    (
        ClienteID,
        Fecha,
        HoraEntrada,
        Estado
    )
    VALUES
    (
        @ClienteID,
        CAST(GETDATE() AS DATE),
        CAST(GETDATE() AS TIME),
        'VALIDADA'
    );

    PRINT 'Acceso permitido.';
END;
GO