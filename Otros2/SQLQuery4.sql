INSERT INTO Cliente
    (UsuarioID, Nombres, Apellidos, Telefono, FechaNacimiento, Estado)
VALUES
    (
        (SELECT UsuarioID FROM Usuario WHERE NombreUsuario = 'cliente_ana'),
        'Ana',
        'López',
        '55510001',
        '2000-03-15',
        1
    ),
    (
        (SELECT UsuarioID FROM Usuario WHERE NombreUsuario = 'cliente_luis'),
        'Luis',
        'Hernández',
        '55510002',
        '1998-07-20',
        1
    ),
    (
        (SELECT UsuarioID FROM Usuario WHERE NombreUsuario = 'cliente_sofia'),
        'Sofía',
        'García',
        '55510003',
        '2001-11-05',
        1
    ),
    (
        (SELECT UsuarioID FROM Usuario WHERE NombreUsuario = 'cliente_diego'),
        'Diego',
        'Martínez',
        '55510004',
        '1997-01-28',
        1
    ),
    (
        (SELECT UsuarioID FROM Usuario WHERE NombreUsuario = 'cliente_laura'),
        'Laura',
        'Ramírez',
        '55510005',
        '1999-09-12',
        1
    );
GO