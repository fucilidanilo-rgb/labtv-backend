
package com.labtv.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AcquistoRequest(

        @NotNull(message = "L'ID del film è obbligatorio")
        @Positive(message = "L'ID del film deve essere positivo")
        Long movieId,

        @NotBlank(message = "Il titolo è obbligatorio")
        String title,

        String poster

) {
}
