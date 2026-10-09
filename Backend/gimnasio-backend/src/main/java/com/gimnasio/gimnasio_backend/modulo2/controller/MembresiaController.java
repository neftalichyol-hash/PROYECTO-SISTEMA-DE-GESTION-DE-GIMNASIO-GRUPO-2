package com.gimnasio.gimnasio_backend.modulo2.controller;

import com.gimnasio.gimnasio_backend.modulo2.dto.MembresiaDTO;
import com.gimnasio.gimnasio_backend.modulo2.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membresias")
@CrossOrigin(origins = "*")
public class MembresiaController {

    @Autowired
    private MembresiaService membresiaService;

    // GET: http://localhost:8080/api/membresias (Lista todas las membresías)
    @GetMapping
    public ResponseEntity<List<MembresiaDTO.Response>> listarTodas() {
        return ResponseEntity.ok(membresiaService.listarTodas());
    }

    // GET: http://localhost:8080/api/membresias/cliente/1 (Busca membresías por ID de Cliente)
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<MembresiaDTO.Response>> listarPorCliente(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(membresiaService.listarPorCliente(clienteId));
    }

    // POST: http://localhost:8080/api/membresias/asignar
    @PostMapping("/asignar")
    public ResponseEntity<MembresiaDTO.Response> asignarMembresia(@RequestBody MembresiaDTO.AsignarRequest request) {
        return ResponseEntity.ok(membresiaService.asignarMembresia(request));
    }

    // POST: http://localhost:8080/api/membresias/actualizar-vencidas
    @PostMapping("/actualizar-vencidas")
    public ResponseEntity<String> actualizarVencidas() {
        membresiaService.ejecutarMantenimientoVencimientos();
        return ResponseEntity.ok("Proceso de actualización de membresías vencidas ejecutado correctamente.");
    }
}