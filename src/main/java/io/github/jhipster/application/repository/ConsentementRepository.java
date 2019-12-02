package io.github.jhipster.application.repository;
import io.github.jhipster.application.domain.Consentement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Consentement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsentementRepository extends JpaRepository<Consentement, Long> {

}
