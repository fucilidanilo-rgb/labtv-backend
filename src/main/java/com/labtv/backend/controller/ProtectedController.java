
package com.labtv.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProtectedController {

    @GetMapping("/protected")
    public ResponseEntity<Map<String, String>> protectedEndpoint(
            Authentication authentication) {

        return ResponseEntity.ok(
                Map.of(
                        "messaggio", "Accesso autorizzato a LABTV!",
                        "utente", authentication.getName()
                )
        );
    }
}
