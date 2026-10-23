package com.labtv.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailGiaRegistrataException.class)
    public ResponseEntity<Map<String, String>> gestisciEmailGiaRegistrata(
            EmailGiaRegistrataException exception) {

        Map<String, String> risposta = Map.of(
                "messaggio", exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(risposta);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> gestisciValidazione(
            MethodArgumentNotValidException exception) {

        Map<String, String> errori = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(errore -> errori.putIfAbsent(
                        errore.getField(),
                        errore.getDefaultMessage()
                ));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errori);
    }

    @ExceptionHandler(CredenzialiNonValideException.class)
    public ResponseEntity<Map<String, String>> gestisciCredenzialiNonValide(
            CredenzialiNonValideException exception) {

        Map<String, String> risposta = Map.of(
                "messaggio", exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(risposta);
    }
}