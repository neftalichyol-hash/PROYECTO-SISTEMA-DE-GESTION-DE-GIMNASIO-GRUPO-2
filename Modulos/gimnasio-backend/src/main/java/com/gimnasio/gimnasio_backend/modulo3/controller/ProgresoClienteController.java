package com.gimnasio.gimnasio_backend.modulo3.controller;

import com.gimnasio.gimnasio_backend.modulo3.dto.ProgresoClienteDTO;
import com.gimnasio.gimnasio_backend.modulo3.service.ProgresoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progresos")
@CrossOrigin(origins = "*")
public class ProgresoClienteController {

    @Autowired
    private ProgresoClienteService progresoClienteService;

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ProgresoClienteDTO.Response>> listarPorCliente(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(progresoClienteService.listarPorCliente(clienteId));
    }

    @PostMapping
    public ResponseEntity<ProgresoClienteDTO.Response> registrar(@RequestBody ProgresoClienteDTO.Request request) {
        return ResponseEntity.ok(progresoClienteService.registrar(request));
    }
}