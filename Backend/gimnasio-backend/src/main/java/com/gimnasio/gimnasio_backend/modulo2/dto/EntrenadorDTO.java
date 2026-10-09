package com.gimnasio.gimnasio_backend.modulo2.dto;

import lombok.Data;

public class EntrenadorDTO {

    @Data
    public static class Request {
        private Integer usuarioId;
        private String nombres;
        private String apellidos;
        private String especialidad;
        private String telefono;
    }

    @Data
    public static class Response {
        private Integer entrenadorId;
        private Integer usuarioId;
        private String nombres;
        private String apellidos;
        private String especialidad;
        private String telefono;
        private Boolean estado;
    }
}