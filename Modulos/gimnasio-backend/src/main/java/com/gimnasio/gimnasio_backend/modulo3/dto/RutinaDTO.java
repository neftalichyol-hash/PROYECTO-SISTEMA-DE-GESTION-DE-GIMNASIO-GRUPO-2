package com.gimnasio.gimnasio_backend.modulo3.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

public class RutinaDTO {

    @Data
    public static class DetalleRequest {
        private Integer ejercicioId;
        private String diaSemana;
        private Integer series;
        private Integer repeticiones;
        private Integer descansoSegundos;
    }

    @Data
    public static class Request {
        private Integer clienteId;
        private Integer entrenadorId;
        private String nombre;
        private String objetivo;
        private List<DetalleRequest> detalles;
    }

    @Data
    public static class DetalleResponse {
        private Integer detalleRutinaId;
        private Integer ejercicioId;
        private String nombreEjercicio;
        private String diaSemana;
        private Integer series;
        private Integer repeticiones;
        private Integer descansoSegundos;
    }

    @Data
    public static class Response {
        private Integer rutinaId;
        private Integer clienteId;
        private String nombreCliente;
        private Integer entrenadorId;
        private String nombreEntrenador;
        private String nombre;
        private String objetivo;
        private LocalDate fechaCreacion;
        private Boolean estado;
        private List<DetalleResponse> detalles;
    }
}