package com.gimnasio.gimnasio_backend.modulo3.repository;

import com.gimnasio.gimnasio_backend.modulo3.entity.DetalleRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRutinaRepository extends JpaRepository<DetalleRutina, Integer> {
    List<DetalleRutina> findByRutinaRutinaId(Integer rutinaId);
}