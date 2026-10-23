
package com.labtv.backend.dto;

public record LoginResponse(
        Long id,
        String nome,
        String email,
        String token
) {
}
