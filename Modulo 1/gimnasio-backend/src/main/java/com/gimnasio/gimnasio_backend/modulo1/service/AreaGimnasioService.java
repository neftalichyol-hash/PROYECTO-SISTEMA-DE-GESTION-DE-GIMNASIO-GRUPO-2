package com.gimnasio.gimnasio_backend.modulo1.service;

import com.gimnasio.gimnasio_backend.modulo1.entity.AreaGimnasio;
import com.gimnasio.gimnasio_backend.modulo1.repository.AreaGimnasioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaGimnasioService {

    @Autowired
    private AreaGimnasioRepository areaRepository;

    public List<AreaGimnasio> listarTodas() {
        return areaRepository.findAll();
    }

    public AreaGimnasio obtenerPorId(Integer id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + id));
    }

    @Transactional
    public AreaGimnasio crear(AreaGimnasio area) {
        return areaRepository.save(area);
    }

    @Transactional
    public AreaGimnasio actualizar(Integer id, AreaGimnasio areaDetails) {
        AreaGimnasio area = obtenerPorId(id);
        area.setNombre(areaDetails.getNombre());
        area.setDescripcion(areaDetails.getDescripcion());
        area.setCapacidad(areaDetails.getCapacidad());
        area.setEstado(areaDetails.getEstado());
        return areaRepository.save(area);
    }
}