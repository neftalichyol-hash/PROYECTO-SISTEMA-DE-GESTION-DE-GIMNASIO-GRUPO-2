package com.gimnasio.gimnasio_backend.modulo2.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MembresiaDTO {

    @Data
    public static class AsignarRequest {
        private Integer clienteId;
        private Integer tipoMembresiaId;
        private LocalDate fechaInicio;
    }

    @Data
    public static class Response {
        private Integer membresiaId;
        private Integer clienteId;
        private String nombreCliente;
        private Integer tipoMembresiaId;
        private String nombreTipoMembresia;
        private LocalDate fechaInicio;
        private LocalDate fechaVencimiento;
        private BigDecimal precio;
        private String estado;
    }
}