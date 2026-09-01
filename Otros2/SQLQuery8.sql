USE GimnasioDB;
GO

INSERT INTO Membresia
(
    ClienteID,
    TipoMembresiaID,
    FechaInicio,
    FechaVencimiento,
    Estado
)
VALUES

(
    (SELECT ClienteID
     FROM Cliente
     WHERE Nombres = 'Ana'
       AND Apellidos = 'López'),

    (SELECT TipoMembresiaID
     FROM TipoMembresia
     WHERE Nombre = 'MENSUAL'),

    '2026-08-01',
    '2026-09-01',
    'ACTIVA'
),

(
    (SELECT ClienteID
     FROM Cliente
     WHERE Nombres = 'Luis'
       AND Apellidos = 'Hernández'),

    (SELECT TipoMembresiaID
     FROM TipoMembresia
     WHERE Nombre = 'TRIMESTRAL'),

    '2026-07-01',
    '2026-10-01',
    'ACTIVA'
),

(
    (SELECT ClienteID
     FROM Cliente
     WHERE Nombres = 'Sofía'
       AND Apellidos = 'García'),

    (SELECT TipoMembresiaID
     FROM TipoMembresia
     WHERE Nombre = 'ANUAL'),

    '2026-01-01',
    '2027-01-01',
    'ACTIVA'
),

(
    (SELECT ClienteID
     FROM Cliente
     WHERE Nombres = 'Diego'
       AND Apellidos = 'Martínez'),

    (SELECT TipoMembresiaID
     FROM TipoMembresia
     WHERE Nombre = 'MENSUAL'),

    '2026-06-01',
    '2026-07-01',
    'VENCIDA'
),

(
    (SELECT ClienteID
     FROM Cliente
     WHERE Nombres = 'Laura'
       AND Apellidos = 'Ramírez'),

    (SELECT TipoMembresiaID
     FROM TipoMembresia
     WHERE Nombre = 'MENSUAL'),

    '2026-08-15',
    '2026-09-15',
    'ACTIVA'
);
GO