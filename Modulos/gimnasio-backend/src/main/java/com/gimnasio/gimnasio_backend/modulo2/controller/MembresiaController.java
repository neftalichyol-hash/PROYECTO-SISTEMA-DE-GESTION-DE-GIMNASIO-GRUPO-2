package com.gimnasio.gimnasio_backend.modulo2.controller;

import com.gimnasio.gimnasio_backend.modulo2.dto.MembresiaDTO;
import com.gimnasio.gimnasio_backend.modulo2.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/membresias")
@CrossOrigin(origins = "*")
public class MembresiaController {

    @Autowired
    private MembresiaService membresiaService;

    @PostMapping("/asignar")
    public ResponseEntity<MembresiaDTO.Response> asignarMembresia(@RequestBody MembresiaDTO.AsignarRequest request) {
        return ResponseEntity.ok(membresiaService.asignarMembresia(request));
    }

    @PostMapping("/actualizar-vencidas")
    public ResponseEntity<String> actualizarVencidas() {
        membresiaService.ejecutarMantenimientoVencimientos();
        return ResponseEntity.ok("Proceso de actualización de membresías vencidas ejecutado correctamente.");
    }
}