package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ConsentementService;
import io.github.jhipster.application.domain.Consentement;
import io.github.jhipster.application.repository.ConsentementRepository;
import io.github.jhipster.application.repository.search.ConsentementSearchRepository;
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
 * Service Implementation for managing {@link Consentement}.
 */
@Service
@Transactional
public class ConsentementServiceImpl implements ConsentementService {

    private final Logger log = LoggerFactory.getLogger(ConsentementServiceImpl.class);

    private final ConsentementRepository consentementRepository;

    private final ConsentementSearchRepository consentementSearchRepository;

    public ConsentementServiceImpl(ConsentementRepository consentementRepository, ConsentementSearchRepository consentementSearchRepository) {
        this.consentementRepository = consentementRepository;
        this.consentementSearchRepository = consentementSearchRepository;
    }

    /**
     * Save a consentement.
     *
     * @param consentement the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Consentement save(Consentement consentement) {
        log.debug("Request to save Consentement : {}", consentement);
        Consentement result = consentementRepository.save(consentement);
        consentementSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the consentements.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Consentement> findAll() {
        log.debug("Request to get all Consentements");
        return consentementRepository.findAll();
    }


    /**
     * Get one consentement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Consentement> findOne(Long id) {
        log.debug("Request to get Consentement : {}", id);
        return consentementRepository.findById(id);
    }

    /**
     * Delete the consentement by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Consentement : {}", id);
        consentementRepository.deleteById(id);
        consentementSearchRepository.deleteById(id);
    }

    /**
     * Search for the consentement corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Consentement> search(String query) {
        log.debug("Request to search Consentements for query {}", query);
        return StreamSupport
            .stream(consentementSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
