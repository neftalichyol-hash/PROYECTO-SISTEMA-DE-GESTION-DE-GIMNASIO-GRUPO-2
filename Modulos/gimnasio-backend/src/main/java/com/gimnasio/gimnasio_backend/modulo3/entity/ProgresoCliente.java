package com.gimnasio.gimnasio_backend.modulo3.entity;

import com.gimnasio.gimnasio_backend.modulo2.entity.Cliente;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ProgresoCliente", schema = "dbo")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProgresoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProgresoID")
    private Integer progresoId;

    // FK_ProgresoCliente_Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClienteID", nullable = false)
    private Cliente cliente;

    @Column(name = "FechaMedicion", nullable = false)
    private LocalDate fechaMedicion;

    @Column(name = "PesoKg", precision = 5, scale = 2)
    private BigDecimal pesoKg;

    @Column(name = "PorcentajeGrasa", precision = 5, scale = 2)
    private BigDecimal porcentajeGrasa;

    @Column(name = "MasaMuscularKg", precision = 5, scale = 2)
    private BigDecimal masaMuscularKg;

    @Column(name = "Observaciones", length = 255)
    private String observaciones;
}