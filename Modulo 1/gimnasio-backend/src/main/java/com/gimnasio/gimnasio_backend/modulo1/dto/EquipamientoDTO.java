package com.gimnasio.gimnasio_backend.modulo1.dto;

import lombok.Data;
import java.time.LocalDate;

public class EquipamientoDTO {

    @Data
    public static class Request {
        private String nombre;
        private String numeroSerie;
        private LocalDate fechaAdquisicion;
        private String estado;
        private Integer areaGimnasioId;
    }

    @Data
    public static class Response {
        private Integer equipamientoId;
        private String nombre;
        private String numeroSerie;
        private LocalDate fechaAdquisicion;
        private String estado;
        private Integer areaGimnasioId;
        private String nombreArea;
    }
}