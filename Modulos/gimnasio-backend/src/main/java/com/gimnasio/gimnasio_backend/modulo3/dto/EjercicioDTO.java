package com.gimnasio.gimnasio_backend.modulo3.dto;

import lombok.Data;

public class EjercicioDTO {

    @Data
    public static class Request {
        private String nombre;
        private String grupoMuscular;
        private String descripcion;
    }

    @Data
    public static class Response {
        private Integer ejercicioId;
        private String nombre;
        private String grupoMuscular;
        private String descripcion;
        private Boolean estado;
    }
}