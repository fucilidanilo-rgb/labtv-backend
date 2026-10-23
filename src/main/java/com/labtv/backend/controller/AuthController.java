
package com.labtv.backend.controller;

import com.labtv.backend.dto.LoginRequest;
import com.labtv.backend.dto.LoginResponse;
import com.labtv.backend.dto.RegistrazioneRequest;
import com.labtv.backend.dto.UtenteResponse;
import com.labtv.backend.entity.Utente;
import com.labtv.backend.security.JwtService;
import com.labtv.backend.service.UtenteService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UtenteService utenteService;
    private final JwtService jwtService;

    public AuthController(
            UtenteService utenteService,
            JwtService jwtService) {

        this.utenteService = utenteService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<UtenteResponse> registra(
            @Valid @RequestBody RegistrazioneRequest request) {

        Utente utente = utenteService.registra(
                request.nome(),
                request.email(),
                request.password()
        );

        UtenteResponse response = new UtenteResponse(
                utente.getId(),
                utente.getNome(),
                utente.getEmail()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        Utente utente = utenteService.login(
                request.email(),
                request.password()
        );

        String token = jwtService.generaToken(utente);

        LoginResponse response = new LoginResponse(
                utente.getId(),
                utente.getNome(),
                utente.getEmail(),
                token
        );

        return ResponseEntity.ok(response);
    }
}
