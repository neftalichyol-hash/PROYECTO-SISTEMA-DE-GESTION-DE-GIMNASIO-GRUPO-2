package com.gimnasio.gimnasio_backend.modulo1.service;

import com.gimnasio.gimnasio_backend.modulo1.dto.AuthDTOs.*;
import com.gimnasio.gimnasio_backend.modulo1.entity.Usuario;
import com.gimnasio.gimnasio_backend.modulo1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public AuthResponse registrar(RegisterRequest request) {
        if (usuarioRepository.existsByNombreUsuario(request.getNombreUsuario())) {
            throw new RuntimeException("El nombre de usuario ya está registrado.");
        }
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        Usuario usuario = Usuario.builder()
                .nombreUsuario(request.getNombreUsuario())
                .correo(request.getCorreo())
                .contrasena(request.getContrasena()) // NOTA: Hash con BCrypt en producción
                .rol(request.getRol() != null ? request.getRol() : "CLIENTE")
                .estado(true)
                .build();

        Usuario guardado = usuarioRepository.save(usuario);

        AuthResponse response = new AuthResponse();
        response.setUsuarioId(guardado.getUsuarioId());
        response.setNombreUsuario(guardado.getNombreUsuario());
        response.setCorreo(guardado.getCorreo());
        response.setRol(guardado.getRol());
        response.setToken("SESSION-TOKEN-MOCK");
        return response;
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(request.getNombreUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos."));

        if (!usuario.getContrasena().equals(request.getContrasena())) {
            throw new RuntimeException("Usuario o contraseña incorrectos.");
        }

        if (!usuario.getEstado()) {
            throw new RuntimeException("El usuario se encuentra inactivo.");
        }

        AuthResponse response = new AuthResponse();
        response.setUsuarioId(usuario.getUsuarioId());
        response.setNombreUsuario(usuario.getNombreUsuario());
        response.setCorreo(usuario.getCorreo());
        response.setRol(usuario.getRol());
        response.setToken("SESSION-TOKEN-MOCK");
        return response;
    }
}