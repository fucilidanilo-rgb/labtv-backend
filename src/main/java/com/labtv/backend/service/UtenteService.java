package com.labtv.backend.service;

import com.labtv.backend.entity.Utente;
import com.labtv.backend.exception.CredenzialiNonValideException;
import com.labtv.backend.exception.EmailGiaRegistrataException;
import com.labtv.backend.repository.UtenteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public UtenteService(
            UtenteRepository utenteRepository,
            PasswordEncoder passwordEncoder) {

        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utente registra(String nome, String email, String password) {

        if (utenteRepository.existsByEmail(email)) {
            throw new EmailGiaRegistrataException("Email già registrata");
        }

        String passwordHash = passwordEncoder.encode(password);

        Utente utente = new Utente(nome, email, passwordHash);

        return utenteRepository.save(utente);
    }

    public Utente login(String email, String password) {

        Utente utente = utenteRepository.findByEmail(email)
                .orElseThrow(CredenzialiNonValideException::new);

        if (!passwordEncoder.matches(password, utente.getPasswordHash())) {
            throw new CredenzialiNonValideException();
        }

        return utente;
    }
}