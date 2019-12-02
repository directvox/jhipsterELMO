package io.github.jhipster.application.repository;
import io.github.jhipster.application.domain.ReponseDemandeDeService;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReponseDemandeDeService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReponseDemandeDeServiceRepository extends JpaRepository<ReponseDemandeDeService, Long> {

}
