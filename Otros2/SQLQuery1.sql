USE GimnasioDB;
GO

INSERT INTO Usuario
    (NombreUsuario, Correo, Contrasena, Rol, Estado)
VALUES
    ('admin_gimnasio', 'admin@gimnasio.com', 'Admin123', 'ADMINISTRADOR', 1),

    ('entrenador_carlos', 'carlos@gimnasio.com', 'Entrenador123', 'ENTRENADOR', 1),
    ('entrenador_maria', 'maria@gimnasio.com', 'Entrenador123', 'ENTRENADOR', 1),
    ('entrenador_jose', 'jose@gimnasio.com', 'Entrenador123', 'ENTRENADOR', 1),

    ('cliente_ana', 'ana@gimnasio.com', 'Cliente123', 'CLIENTE', 1),
    ('cliente_luis', 'luis@gimnasio.com', 'Cliente123', 'CLIENTE', 1),
    ('cliente_sofia', 'sofia@gimnasio.com', 'Cliente123', 'CLIENTE', 1),
    ('cliente_diego', 'diego@gimnasio.com', 'Cliente123', 'CLIENTE', 1),
    ('cliente_laura', 'laura@gimnasio.com', 'Cliente123', 'CLIENTE', 1);
GO