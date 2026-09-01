USE GimnasioDB;
GO

CREATE OR ALTER VIEW vw_EjerciciosMasPrescritos
AS
SELECT
    e.EjercicioID,
    e.Nombre AS Ejercicio,
    gm.Nombre AS GrupoMuscular,
    COUNT(re.RutinaEjercicioID) AS VecesPrescrito
FROM Ejercicio e
INNER JOIN GrupoMuscular gm
    ON e.GrupoMuscularID = gm.GrupoMuscularID
LEFT JOIN RutinaEjercicio re
    ON e.EjercicioID = re.EjercicioID
GROUP BY
    e.EjercicioID,
    e.Nombre,
    gm.Nombre;
GO