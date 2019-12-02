package io.github.jhipster.application.repository;
import io.github.jhipster.application.domain.Intervenant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Intervenant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IntervenantRepository extends JpaRepository<Intervenant, Long> {

}
