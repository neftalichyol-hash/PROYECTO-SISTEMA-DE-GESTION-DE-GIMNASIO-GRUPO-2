USE GimnasioDB;
GO

CREATE OR ALTER VIEW vw_ClientesRiesgoDesercion
AS
SELECT
    c.ClienteID,
    c.Nombres,
    c.Apellidos,
    c.Telefono,
    MAX(a.Fecha) AS UltimaAsistencia,
    DATEDIFF(
        DAY,
        MAX(a.Fecha),
        CAST(GETDATE() AS DATE)
    ) AS DiasSinAsistir
FROM Cliente c
LEFT JOIN Asistencia a
    ON c.ClienteID = a.ClienteID
    AND a.Estado = 'VALIDADA'
GROUP BY
    c.ClienteID,
    c.Nombres,
    c.Apellidos,
    c.Telefono
HAVING
    MAX(a.Fecha) IS NULL
    OR DATEDIFF(
        DAY,
        MAX(a.Fecha),
        CAST(GETDATE() AS DATE)
    ) > 15;
GO