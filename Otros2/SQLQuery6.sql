INSERT INTO Entrenador
    (UsuarioID, Nombres, Apellidos, Telefono, Especialidad, Estado)
VALUES
    (
        (SELECT UsuarioID FROM Usuario WHERE NombreUsuario = 'entrenador_carlos'),
        'Carlos',
        'Pérez',
        '55520001',
        'Entrenamiento de fuerza',
        1
    ),
    (
        (SELECT UsuarioID FROM Usuario WHERE NombreUsuario = 'entrenador_maria'),
        'María',
        'Gómez',
        '55520002',
        'Entrenamiento funcional',
        1
    ),
    (
        (SELECT UsuarioID FROM Usuario WHERE NombreUsuario = 'entrenador_jose'),
        'José',
        'Castillo',
        '55520003',
        'Cardio y acondicionamiento',
        1
    );
GO