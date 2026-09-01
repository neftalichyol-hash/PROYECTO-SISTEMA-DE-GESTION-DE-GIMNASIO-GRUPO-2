USE GimnasioDB;
GO

CREATE OR ALTER VIEW vw_IngresosMensuales
AS
SELECT
    YEAR(p.FechaPago) AS Anio,
    MONTH(p.FechaPago) AS Mes,
    tm.Nombre AS TipoMembresia,
    COUNT(p.PagoID) AS CantidadPagos,
    SUM(p.MontoPagado) AS Ingresos
FROM Pago p
INNER JOIN Membresia m
    ON p.MembresiaID = m.MembresiaID
INNER JOIN TipoMembresia tm
    ON m.TipoMembresiaID = tm.TipoMembresiaID
WHERE p.Estado = 'PAGADO'
GROUP BY
    YEAR(p.FechaPago),
    MONTH(p.FechaPago),
    tm.Nombre;
GO