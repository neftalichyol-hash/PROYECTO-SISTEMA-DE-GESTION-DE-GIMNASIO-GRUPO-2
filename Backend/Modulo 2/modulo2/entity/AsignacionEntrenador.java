package com.gimnasio.gimnasio_backend.modulo2.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "AsignacionEntrenador", schema = "dbo")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AsignacionEntrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AsignacionID") // Corregido: En la BD es AsignacionID
    private Integer asignacionId;

    // FK_Asignacion_Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClienteID", nullable = false)
    private Cliente cliente;

    // FK_Asignacion_Entrenador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EntrenadorID", nullable = false)
    private Entrenador entrenador;

    @Column(name = "FechaInicio", nullable = false) // Corregido: En la BD es FechaInicio
    private LocalDate fechaInicio;

    @Column(name = "FechaFin") // Agregado: Campo existente en la BD
    private LocalDate fechaFin;

    @Column(name = "Estado", nullable = false)
    @Builder.Default
    private Boolean estado = true;
}