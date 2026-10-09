package com.gimnasio.gimnasio_backend.modulo4.controller;

import com.gimnasio.gimnasio_backend.modulo4.dto.AsistenciaDTO;
import com.gimnasio.gimnasio_backend.modulo4.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencia")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    // POST: http://localhost:8080/api/asistencia/marcar
    @PostMapping("/marcar")
    public ResponseEntity<AsistenciaDTO.Response> marcar(@RequestBody AsistenciaDTO.MarcarRequest request) {
        return ResponseEntity.ok(asistenciaService.marcar(request));
    }

    // PUT: http://localhost:8080/api/asistencia/1/salida
    @PutMapping("/{id}/salida")
    public ResponseEntity<AsistenciaDTO.Response> marcarSalida(@PathVariable Integer id) {
        return ResponseEntity.ok(asistenciaService.marcarSalida(id));
    }

    // GET: http://localhost:8080/api/asistencia/cliente/1
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<AsistenciaDTO.Response>> listarPorCliente(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(asistenciaService.listarPorCliente(clienteId));
    }
}
