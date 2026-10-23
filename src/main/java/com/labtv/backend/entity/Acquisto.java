
package com.labtv.backend.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "acquisti")
public class Acquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @Column(nullable = false)
    private Long movieId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 500)
    private String poster;

    @Column(nullable = false)
    private Instant purchaseDate;

    protected Acquisto() {
    }

    public Acquisto(
            Utente utente,
            Long movieId,
            String title,
            String poster) {

        this.utente = utente;
        this.movieId = movieId;
        this.title = title;
        this.poster = poster;
        this.purchaseDate = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public Utente getUtente() {
        return utente;
    }

    public Long getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public Instant getPurchaseDate() {
        return purchaseDate;
    }
}
