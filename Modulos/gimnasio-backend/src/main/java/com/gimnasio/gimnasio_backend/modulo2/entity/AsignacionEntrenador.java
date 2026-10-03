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
    @Column(name = "AsignacionEntrenadorID")
    private Integer asignacionEntrenadorId;

    // FK_AsignacionEntrenador_Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClienteID", nullable = false)
    private Cliente cliente;

    // FK_AsignacionEntrenador_Entrenador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EntrenadorID", nullable = false)
    private Entrenador entrenador;

    @Column(name = "FechaAsignacion", nullable = false)
    private LocalDate fechaAsignacion;

    @Column(name = "Estado", nullable = false)
    @Builder.Default
    private Boolean estado = true;
}