package com.gimnasio.gimnasio_backend.modulo1.dto;

import lombok.Data;

public class AuthDTOs {

    @Data
    public static class LoginRequest {
        private String nombreUsuario;
        private String contrasena;
    }

    @Data
    public static class RegisterRequest {
        private String nombreUsuario;
        private String correo;
        private String contrasena;
        private String rol;
    }

    @Data
    public static class AuthResponse {
        private Integer usuarioId;
        private String nombreUsuario;
        private String correo;
        private String rol;
        private String token; // Marcador de posición para integración con Spring Security / JWT
    }
}