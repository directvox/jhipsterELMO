package io.github.jhipster.application.repository;
import io.github.jhipster.application.domain.FormulaireEvaluation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FormulaireEvaluation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FormulaireEvaluationRepository extends JpaRepository<FormulaireEvaluation, Long> {

}
