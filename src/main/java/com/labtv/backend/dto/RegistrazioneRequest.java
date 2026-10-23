package com.labtv.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrazioneRequest(

        @NotBlank(message = "Il nome è obbligatorio")
        @Size(max = 100, message = "Il nome può contenere massimo 100 caratteri")
        String nome,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Inserisci un'email valida")
        @Size(max = 255, message = "L'email è troppo lunga")
        String email,

        @NotBlank(message = "La password è obbligatoria")
        @Size(min = 8, max = 64,
                message = "La password deve contenere da 8 a 64 caratteri")
        String password

) {
}