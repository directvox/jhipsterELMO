package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.FormulaireConsentementService;
import io.github.jhipster.application.domain.FormulaireConsentement;
import io.github.jhipster.application.repository.FormulaireConsentementRepository;
import io.github.jhipster.application.repository.search.FormulaireConsentementSearchRepository;
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
 * Service Implementation for managing {@link FormulaireConsentement}.
 */
@Service
@Transactional
public class FormulaireConsentementServiceImpl implements FormulaireConsentementService {

    private final Logger log = LoggerFactory.getLogger(FormulaireConsentementServiceImpl.class);

    private final FormulaireConsentementRepository formulaireConsentementRepository;

    private final FormulaireConsentementSearchRepository formulaireConsentementSearchRepository;

    public FormulaireConsentementServiceImpl(FormulaireConsentementRepository formulaireConsentementRepository, FormulaireConsentementSearchRepository formulaireConsentementSearchRepository) {
        this.formulaireConsentementRepository = formulaireConsentementRepository;
        this.formulaireConsentementSearchRepository = formulaireConsentementSearchRepository;
    }

    /**
     * Save a formulaireConsentement.
     *
     * @param formulaireConsentement the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FormulaireConsentement save(FormulaireConsentement formulaireConsentement) {
        log.debug("Request to save FormulaireConsentement : {}", formulaireConsentement);
        FormulaireConsentement result = formulaireConsentementRepository.save(formulaireConsentement);
        formulaireConsentementSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the formulaireConsentements.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FormulaireConsentement> findAll() {
        log.debug("Request to get all FormulaireConsentements");
        return formulaireConsentementRepository.findAll();
    }


    /**
     * Get one formulaireConsentement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FormulaireConsentement> findOne(Long id) {
        log.debug("Request to get FormulaireConsentement : {}", id);
        return formulaireConsentementRepository.findById(id);
    }

    /**
     * Delete the formulaireConsentement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FormulaireConsentement : {}", id);
        formulaireConsentementRepository.deleteById(id);
        formulaireConsentementSearchRepository.deleteById(id);
    }

    /**
     * Search for the formulaireConsentement corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FormulaireConsentement> search(String query) {
        log.debug("Request to search FormulaireConsentements for query {}", query);
        return StreamSupport
            .stream(formulaireConsentementSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
