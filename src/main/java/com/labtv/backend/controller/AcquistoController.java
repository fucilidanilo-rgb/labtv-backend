
package com.labtv.backend.controller;

import com.labtv.backend.dto.AcquistoRequest;
import com.labtv.backend.dto.AcquistoResponse;
import com.labtv.backend.entity.Acquisto;
import com.labtv.backend.entity.Utente;
import com.labtv.backend.repository.UtenteRepository;
import com.labtv.backend.service.AcquistoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/acquisti")
public class AcquistoController {

    private final AcquistoService acquistoService;
    private final UtenteRepository utenteRepository;

    public AcquistoController(
            AcquistoService acquistoService,
            UtenteRepository utenteRepository) {

        this.acquistoService = acquistoService;
        this.utenteRepository = utenteRepository;
    }

    // Recupera l'utente autenticato dal JWT
    private Long getUtenteId(Jwt jwt) {

        if (jwt == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Token mancante"
            );
        }

        String email = jwt.getSubject();

        Utente utente = utenteRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Utente non trovato"
                ));

        return utente.getId();
    }

    // GET /api/acquisti
    @GetMapping
    public List<AcquistoResponse> listaAcquisti(
            @AuthenticationPrincipal Jwt jwt) {

        Long utenteId = getUtenteId(jwt);

        return acquistoService.trovaAcquisti(utenteId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // POST /api/acquisti
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AcquistoResponse acquista(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody AcquistoRequest request) {

        Long utenteId = getUtenteId(jwt);

        Acquisto acquisto = acquistoService.acquista(
                utenteId,
                request.movieId(),
                request.title(),
                request.poster()
        );

        return toResponse(acquisto);
    }

    // DELETE /api/acquisti/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void restituisci(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Long id) {

        Long utenteId = getUtenteId(jwt);

        acquistoService.restituisci(utenteId, id);
    }

    // Converte l'entità JPA in DTO
    private AcquistoResponse toResponse(Acquisto acquisto) {

        return new AcquistoResponse(
                acquisto.getId(),
                acquisto.getMovieId(),
                acquisto.getTitle(),
                acquisto.getPoster(),
                acquisto.getPurchaseDate()
        );
    }
}
