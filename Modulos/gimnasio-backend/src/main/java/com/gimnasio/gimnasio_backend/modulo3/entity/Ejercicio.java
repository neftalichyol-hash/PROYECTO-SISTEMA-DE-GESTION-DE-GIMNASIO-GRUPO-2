package com.gimnasio.gimnasio_backend.modulo3.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Ejercicio", schema = "dbo")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Ejercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EjercicioID")
    private Integer ejercicioId;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "GrupoMuscular", nullable = false, length = 50)
    private String grupoMuscular;

    @Column(name = "Descripcion", length = 255)
    private String descripcion;

    @Column(name = "Estado", nullable = false)
    @Builder.Default
    private Boolean estado = true;
}