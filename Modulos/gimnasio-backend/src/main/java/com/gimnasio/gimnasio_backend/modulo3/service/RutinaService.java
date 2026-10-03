package com.gimnasio.gimnasio_backend.modulo3.service;

import com.gimnasio.gimnasio_backend.modulo2.entity.Cliente;
import com.gimnasio.gimnasio_backend.modulo2.entity.Entrenador;
import com.gimnasio.gimnasio_backend.modulo2.repository.ClienteRepository;
import com.gimnasio.gimnasio_backend.modulo2.repository.EntrenadorRepository;
import com.gimnasio.gimnasio_backend.modulo3.dto.RutinaDTO;
import com.gimnasio.gimnasio_backend.modulo3.entity.DetalleRutina;
import com.gimnasio.gimnasio_backend.modulo3.entity.Ejercicio;
import com.gimnasio.gimnasio_backend.modulo3.entity.Rutina;
import com.gimnasio.gimnasio_backend.modulo3.repository.EjercicioRepository;
import com.gimnasio.gimnasio_backend.modulo3.repository.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutinaService {

    @Autowired
    private RutinaRepository rutinaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @Autowired
    private EjercicioRepository ejercicioRepository;

    public List<RutinaDTO.Response> listarPorCliente(Integer clienteId) {
        return rutinaRepository.findByClienteClienteIdAndEstadoTrue(clienteId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public RutinaDTO.Response crearRutina(RutinaDTO.Request req) {
        Cliente cliente = clienteRepository.findById(req.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));

        Entrenador entrenador = entrenadorRepository.findById(req.getEntrenadorId())
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado."));

        Rutina rutina = Rutina.builder()
                .cliente(cliente)
                .entrenador(entrenador)
                .nombre(req.getNombre())
                .objetivo(req.getObjetivo())
                .fechaCreacion(LocalDate.now())
                .estado(true)
                .build();

        if (req.getDetalles() != null) {
            List<DetalleRutina> detalles = req.getDetalles().stream().map(dReq -> {
                Ejercicio ejercicio = ejercicioRepository.findById(dReq.getEjercicioId())
                        .orElseThrow(() -> new RuntimeException("Ejercicio no encontrado ID: " + dReq.getEjercicioId()));
                return DetalleRutina.builder()
                        .rutina(rutina)
                        .ejercicio(ejercicio)
                        .diaSemana(dReq.getDiaSemana())
                        .series(dReq.getSeries())
                        .repeticiones(dReq.getRepeticiones())
                        .descansoSegundos(dReq.getDescansoSegundos())
                        .build();
            }).collect(Collectors.toList());

            rutina.setDetalles(detalles);
        }

        return mapToResponse(rutinaRepository.save(rutina));
    }

    private RutinaDTO.Response mapToResponse(Rutina r) {
        RutinaDTO.Response res = new RutinaDTO.Response();
        res.setRutinaId(r.getRutinaId());
        res.setClienteId(r.getCliente().getClienteId());
        res.setNombreCliente(r.getCliente().getNombres() + " " + r.getCliente().getApellidos());
        res.setEntrenadorId(r.getEntrenador().getEntrenadorId());
        res.setNombreEntrenador(r.getEntrenador().getNombres() + " " + r.getEntrenador().getApellidos());
        res.setNombre(r.getNombre());
        res.setObjetivo(r.getObjetivo());
        res.setFechaCreacion(r.getFechaCreacion());
        res.setEstado(r.getEstado());

        if (r.getDetalles() != null) {
            res.setDetalles(r.getDetalles().stream().map(d -> {
                RutinaDTO.DetalleResponse dRes = new RutinaDTO.DetalleResponse();
                dRes.setDetalleRutinaId(d.getDetalleRutinaId());
                dRes.setEjercicioId(d.getEjercicio().getEjercicioId());
                dRes.setNombreEjercicio(d.getEjercicio().getNombre());
                dRes.setDiaSemana(d.getDiaSemana());
                dRes.setSeries(d.getSeries());
                dRes.setRepeticiones(d.getRepeticiones());
                dRes.setDescansoSegundos(d.getDescansoSegundos());
                return dRes;
            }).collect(Collectors.toList()));
        }

        return res;
    }
}