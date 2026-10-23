package com.labtv.backend.dto;

public record UtenteResponse(
        Long id,
        String nome,
        String email
) {
}