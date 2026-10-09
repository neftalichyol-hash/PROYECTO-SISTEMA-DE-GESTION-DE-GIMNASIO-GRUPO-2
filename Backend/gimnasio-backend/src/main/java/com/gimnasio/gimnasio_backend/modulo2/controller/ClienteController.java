package com.gimnasio.gimnasio_backend.modulo2.controller;

import com.gimnasio.gimnasio_backend.modulo2.dto.ClienteDTO;
import com.gimnasio.gimnasio_backend.modulo2.repository.ClienteRiesgoProjection;
import com.gimnasio.gimnasio_backend.modulo2.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO.Response>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO.Response> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    @GetMapping("/riesgo-desercion")
    public ResponseEntity<List<ClienteRiesgoProjection>> clientesEnRiesgo() {
        return ResponseEntity.ok(clienteService.obtenerClientesEnRiesgo());
    }

    @PostMapping
    public ResponseEntity<ClienteDTO.Response> crear(@RequestBody ClienteDTO.Request request) {
        return ResponseEntity.ok(clienteService.crear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO.Response> actualizar(@PathVariable Integer id, @RequestBody ClienteDTO.Request request) {
        return ResponseEntity.ok(clienteService.actualizar(id, request));
    }
}