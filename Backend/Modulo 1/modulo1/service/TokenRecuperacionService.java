package com.gimnasio.gimnasio_backend.modulo1.service;

import com.gimnasio.gimnasio_backend.modulo1.dto.TokenRecuperacionDTOs.*;
import com.gimnasio.gimnasio_backend.modulo1.entity.TokenRecuperacion;
import com.gimnasio.gimnasio_backend.modulo1.entity.Usuario;
import com.gimnasio.gimnasio_backend.modulo1.repository.TokenRecuperacionRepository;
import com.gimnasio.gimnasio_backend.modulo1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenRecuperacionService {

    @Autowired
    private TokenRecuperacionRepository tokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public String generarTokenRecuperacion(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("No existe ningún usuario registrado con ese correo."));

        String token = UUID.randomUUID().toString();

        TokenRecuperacion tokenEntity = TokenRecuperacion.builder()
                .usuario(usuario)
                .token(token)
                .fechaExpiracion(LocalDateTime.now().plusHours(24)) // Expira en 24 horas
                .usado(false)
                .build();

        tokenRepository.save(tokenEntity);

        return token;
    }

    @Transactional
    public void cambiarContrasena(ResetPasswordRequest request) {
        TokenRecuperacion tokenEntity = tokenRepository.findByTokenAndUsadoFalse(request.getToken())
                .orElseThrow(() -> new RuntimeException("El token es inválido o ya ha sido utilizado."));

        if (tokenEntity.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("El token de recuperación ha expirado.");
        }

        Usuario usuario = tokenEntity.getUsuario();
        usuario.setContrasena(request.getNuevaContrasena()); // En producción aplicar hash (BCrypt)
        usuarioRepository.save(usuario);

        tokenEntity.setUsado(true);
        tokenRepository.save(tokenEntity);
    }
}