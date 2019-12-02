package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.FormulaireEvaluationService;
import io.github.jhipster.application.domain.FormulaireEvaluation;
import io.github.jhipster.application.repository.FormulaireEvaluationRepository;
import io.github.jhipster.application.repository.search.FormulaireEvaluationSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link FormulaireEvaluation}.
 */
@Service
@Transactional
public class FormulaireEvaluationServiceImpl implements FormulaireEvaluationService {

    private final Logger log = LoggerFactory.getLogger(FormulaireEvaluationServiceImpl.class);

    private final FormulaireEvaluationRepository formulaireEvaluationRepository;

    private final FormulaireEvaluationSearchRepository formulaireEvaluationSearchRepository;

    public FormulaireEvaluationServiceImpl(FormulaireEvaluationRepository formulaireEvaluationRepository, FormulaireEvaluationSearchRepository formulaireEvaluationSearchRepository) {
        this.formulaireEvaluationRepository = formulaireEvaluationRepository;
        this.formulaireEvaluationSearchRepository = formulaireEvaluationSearchRepository;
    }

    /**
     * Save a formulaireEvaluation.
     *
     * @param formulaireEvaluation the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FormulaireEvaluation save(FormulaireEvaluation formulaireEvaluation) {
        log.debug("Request to save FormulaireEvaluation : {}", formulaireEvaluation);
        FormulaireEvaluation result = formulaireEvaluationRepository.save(formulaireEvaluation);
        formulaireEvaluationSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the formulaireEvaluations.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FormulaireEvaluation> findAll() {
        log.debug("Request to get all FormulaireEvaluations");
        return formulaireEvaluationRepository.findAll();
    }


    /**
     * Get one formulaireEvaluation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FormulaireEvaluation> findOne(Long id) {
        log.debug("Request to get FormulaireEvaluation : {}", id);
        return formulaireEvaluationRepository.findById(id);
    }

    /**
     * Delete the formulaireEvaluation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormulaireEvaluation : {}", id);
        formulaireEvaluationRepository.deleteById(id);
        formulaireEvaluationSearchRepository.deleteById(id);
    }

    /**
     * Search for the formulaireEvaluation corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FormulaireEvaluation> search(String query) {
        log.debug("Request to search FormulaireEvaluations for query {}", query);
        return StreamSupport
            .stream(formulaireEvaluationSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
