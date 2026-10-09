package com.gimnasio.gimnasio_backend.modulo2.dto;

import lombok.Data;
import java.time.LocalDate;

public class ClienteDTO {

    @Data
    public static class Request {
        private Integer usuarioId;
        private String nombres;
        private String apellidos;
        private String telefono;
        private LocalDate fechaNacimiento;
    }

    @Data
    public static class Response {
        private Integer clienteId;
        private Integer usuarioId;
        private String nombres;
        private String apellidos;
        private String telefono;
        private LocalDate fechaNacimiento;
        private Boolean estado;
    }
}