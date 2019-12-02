package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.FormulaireEvaluation;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link FormulaireEvaluation}.
 */
public interface FormulaireEvaluationService {

    /**
     * Save a formulaireEvaluation.
     *
     * @param formulaireEvaluation the entity to save.
     * @return the persisted entity.
     */
    FormulaireEvaluation save(FormulaireEvaluation formulaireEvaluation);

    /**
     * Get all the formulaireEvaluations.
     *
     * @return the list of entities.
     */
    List<FormulaireEvaluation> findAll();


    /**
     * Get the "id" formulaireEvaluation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FormulaireEvaluation> findOne(Long id);

    /**
     * Delete the "id" formulaireEvaluation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the formulaireEvaluation corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<FormulaireEvaluation> search(String query);
}
