
package com.labtv.backend.repository;

import com.labtv.backend.entity.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AcquistoRepository
        extends JpaRepository<Acquisto, Long> {

    List<Acquisto> findByUtenteId(Long utenteId);

    Optional<Acquisto> findByIdAndUtenteId(
            Long id,
            Long utenteId
    );

    boolean existsByUtenteIdAndMovieId(
            Long utenteId,
            Long movieId
    );
}
