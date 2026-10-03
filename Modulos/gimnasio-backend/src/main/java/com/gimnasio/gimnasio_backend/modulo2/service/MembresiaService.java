package com.gimnasio.gimnasio_backend.modulo2.service;

import com.gimnasio.gimnasio_backend.modulo2.dto.MembresiaDTO;
import com.gimnasio.gimnasio_backend.modulo2.entity.Cliente;
import com.gimnasio.gimnasio_backend.modulo2.entity.Membresia;
import com.gimnasio.gimnasio_backend.modulo2.entity.TipoMembresia;
import com.gimnasio.gimnasio_backend.modulo2.repository.ClienteRepository;
import com.gimnasio.gimnasio_backend.modulo2.repository.MembresiaRepository;
import com.gimnasio.gimnasio_backend.modulo2.repository.TipoMembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class MembresiaService {

    @Autowired
    private MembresiaRepository membresiaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoMembresiaRepository tipoMembresiaRepository;

    @Transactional
    public MembresiaDTO.Response asignarMembresia(MembresiaDTO.AsignarRequest req) {
        Cliente cliente = clienteRepository.findById(req.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));

        TipoMembresia tipo = tipoMembresiaRepository.findById(req.getTipoMembresiaId())
                .orElseThrow(() -> new RuntimeException("Tipo de membresía no encontrado."));

        LocalDate fechaInicio = (req.getFechaInicio() != null) ? req.getFechaInicio() : LocalDate.now();
        LocalDate fechaVencimiento = fechaInicio.plusDays(tipo.getDuracionDias());

        Membresia membresia = Membresia.builder()
                .cliente(cliente)
                .tipoMembresia(tipo)
                .fechaInicio(fechaInicio)
                .fechaVencimiento(fechaVencimiento)
                .precio(tipo.getPrecio())
                .estado("ACTIVA")
                .build();

        Membresia guardada = membresiaRepository.save(membresia);
        return mapToResponse(guardada);
    }

    @Transactional
    public void ejecutarMantenimientoVencimientos() {
        membresiaRepository.actualizarMembresiasVencidas();
    }

    private MembresiaDTO.Response mapToResponse(Membresia m) {
        MembresiaDTO.Response res = new MembresiaDTO.Response();
        res.setMembresiaId(m.getMembresiaId());
        res.setClienteId(m.getCliente().getClienteId());
        res.setNombreCliente(m.getCliente().getNombres() + " " + m.getCliente().getApellidos());
        res.setTipoMembresiaId(m.getTipoMembresia().getTipoMembresiaId());
        res.setNombreTipoMembresia(m.getTipoMembresia().getNombre());
        res.setFechaInicio(m.getFechaInicio());
        res.setFechaVencimiento(m.getFechaVencimiento());
        res.setPrecio(m.getPrecio());
        res.setEstado(m.getEstado());
        return res;
    }
}