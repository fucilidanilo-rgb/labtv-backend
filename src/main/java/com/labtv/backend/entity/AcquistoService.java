
package com.labtv.backend.service;

import com.labtv.backend.entity.Acquisto;
import com.labtv.backend.entity.Utente;
import com.labtv.backend.repository.AcquistoRepository;
import com.labtv.backend.repository.UtenteRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AcquistoService {

    private final AcquistoRepository acquistoRepository;
    private final UtenteRepository utenteRepository;

    public AcquistoService(
            AcquistoRepository acquistoRepository,
            UtenteRepository utenteRepository) {

        this.acquistoRepository = acquistoRepository;
        this.utenteRepository = utenteRepository;
    }

    // Recupera gli acquisti di un utente
    public List<Acquisto> trovaAcquisti(Long utenteId) {

        return acquistoRepository.findByUtenteId(utenteId);
    }

    // Registra un nuovo acquisto
    public Acquisto acquista(
            Long utenteId,
            Long movieId,
            String title,
            String poster) {

        Utente utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Utente non trovato"
                ));

        // Impedisce di acquistare due volte lo stesso film
        if (acquistoRepository.existsByUtenteIdAndMovieId(
                utenteId, movieId)) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Film già acquistato"
            );
        }

        Acquisto acquisto = new Acquisto(
                utente,
                movieId,
                title,
                poster
        );

        return acquistoRepository.save(acquisto);
    }

    // Restituisce un film acquistato
    public void restituisci(
            Long utenteId,
            Long acquistoId) {

        Acquisto acquisto = acquistoRepository
                .findByIdAndUtenteId(acquistoId, utenteId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Acquisto non trovato"
                ));

        acquistoRepository.delete(acquisto);
    }
}
