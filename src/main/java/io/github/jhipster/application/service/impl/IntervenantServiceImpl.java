package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.IntervenantService;
import io.github.jhipster.application.domain.Intervenant;
import io.github.jhipster.application.repository.IntervenantRepository;
import io.github.jhipster.application.repository.search.IntervenantSearchRepository;
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
 * Service Implementation for managing {@link Intervenant}.
 */
@Service
@Transactional
public class IntervenantServiceImpl implements IntervenantService {

    private final Logger log = LoggerFactory.getLogger(IntervenantServiceImpl.class);

    private final IntervenantRepository intervenantRepository;

    private final IntervenantSearchRepository intervenantSearchRepository;

    public IntervenantServiceImpl(IntervenantRepository intervenantRepository, IntervenantSearchRepository intervenantSearchRepository) {
        this.intervenantRepository = intervenantRepository;
        this.intervenantSearchRepository = intervenantSearchRepository;
    }

    /**
     * Save a intervenant.
     *
     * @param intervenant the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Intervenant save(Intervenant intervenant) {
        log.debug("Request to save Intervenant : {}", intervenant);
        Intervenant result = intervenantRepository.save(intervenant);
        intervenantSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the intervenants.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Intervenant> findAll() {
        log.debug("Request to get all Intervenants");
        return intervenantRepository.findAll();
    }


    /**
     * Get one intervenant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Intervenant> findOne(Long id) {
        log.debug("Request to get Intervenant : {}", id);
        return intervenantRepository.findById(id);
    }

    /**
     * Delete the intervenant by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Intervenant : {}", id);
        intervenantRepository.deleteById(id);
        intervenantSearchRepository.deleteById(id);
    }

    /**
     * Search for the intervenant corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Intervenant> search(String query) {
        log.debug("Request to search Intervenants for query {}", query);
        return StreamSupport
            .stream(intervenantSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
