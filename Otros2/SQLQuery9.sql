SELECT
    m.MembresiaID,
    c.Nombres + ' ' + c.Apellidos AS Cliente,
    tm.Nombre AS TipoMembresia,
    m.FechaInicio,
    m.FechaVencimiento,
    m.Estado
FROM Membresia m
INNER JOIN Cliente c
    ON m.ClienteID = c.ClienteID
INNER JOIN TipoMembresia tm
    ON m.TipoMembresiaID = tm.TipoMembresiaID
ORDER BY m.MembresiaID;