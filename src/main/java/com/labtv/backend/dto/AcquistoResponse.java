
package com.labtv.backend.dto;

import java.time.Instant;

public record AcquistoResponse(

        Long id,
        Long movieId,
        String title,
        String poster,
        Instant purchaseDate

) {
}
