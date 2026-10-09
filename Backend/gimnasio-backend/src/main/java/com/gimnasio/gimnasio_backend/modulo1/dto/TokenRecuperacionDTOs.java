package com.gimnasio.gimnasio_backend.modulo1.dto;

import lombok.Data;

public class TokenRecuperacionDTOs {

    @Data
    public static class SolicitarRecuperacionRequest {
        private String correo;
    }

    @Data
    public static class ResetPasswordRequest {
        private String token;
        private String nuevaContrasena;
    }
}