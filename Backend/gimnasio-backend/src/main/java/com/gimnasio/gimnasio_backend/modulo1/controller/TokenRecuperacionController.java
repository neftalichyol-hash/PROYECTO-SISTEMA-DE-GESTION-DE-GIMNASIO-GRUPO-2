package com.gimnasio.gimnasio_backend.modulo1.controller;

import com.gimnasio.gimnasio_backend.modulo1.dto.TokenRecuperacionDTOs.*;
import com.gimnasio.gimnasio_backend.modulo1.service.TokenRecuperacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/recuperacion")
@CrossOrigin(origins = "*")
public class TokenRecuperacionController {

    @Autowired
    private TokenRecuperacionService tokenService;

    @PostMapping("/solicitar")
    public ResponseEntity<String> solicitarRecuperacion(@RequestBody SolicitarRecuperacionRequest request) {
        String token = tokenService.generarTokenRecuperacion(request.getCorreo());
        return ResponseEntity.ok("Token generado correctamente: " + token);
    }

    @PostMapping("/resetear")
    public ResponseEntity<String> resetearContrasena(@RequestBody ResetPasswordRequest request) {
        tokenService.cambiarContrasena(request);
        return ResponseEntity.ok("Contraseña restablecida exitosamente.");
    }
}