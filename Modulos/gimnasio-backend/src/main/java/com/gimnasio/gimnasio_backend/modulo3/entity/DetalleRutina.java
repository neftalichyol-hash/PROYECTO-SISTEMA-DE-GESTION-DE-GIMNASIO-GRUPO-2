package com.gimnasio.gimnasio_backend.modulo3.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DetalleRutina", schema = "dbo")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class DetalleRutina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DetalleRutinaID")
    private Integer detalleRutinaId;

    // FK_DetalleRutina_Rutina
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RutinaID", nullable = false)
    private Rutina rutina;

    // FK_DetalleRutina_Ejercicio
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EjercicioID", nullable = false)
    private Ejercicio ejercicio;

    @Column(name = "DiaSemana", nullable = false, length = 20)
    private String diaSemana; // Ej: 'Lunes', 'Martes'

    @Column(name = "Series", nullable = false)
    private Integer series;

    @Column(name = "Repeticiones", nullable = false)
    private Integer repeticiones;

    @Column(name = "DescansoSegundos")
    private Integer descansoSegundos;
}