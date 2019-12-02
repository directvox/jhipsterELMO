package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.DemandeDeServiceService;
import io.github.jhipster.application.domain.DemandeDeService;
import io.github.jhipster.application.repository.DemandeDeServiceRepository;
import io.github.jhipster.application.repository.search.DemandeDeServiceSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link DemandeDeService}.
 */
@Service
@Transactional
public class DemandeDeServiceServiceImpl implements DemandeDeServiceService {

    private final Logger log = LoggerFactory.getLogger(DemandeDeServiceServiceImpl.class);

    private final DemandeDeServiceRepository demandeDeServiceRepository;

    private final DemandeDeServiceSearchRepository demandeDeServiceSearchRepository;

    public DemandeDeServiceServiceImpl(DemandeDeServiceRepository demandeDeServiceRepository, DemandeDeServiceSearchRepository demandeDeServiceSearchRepository) {
        this.demandeDeServiceRepository = demandeDeServiceRepository;
        this.demandeDeServiceSearchRepository = demandeDeServiceSearchRepository;
    }

    /**
     * Save a demandeDeService.
     *
     * @param demandeDeService the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DemandeDeService save(DemandeDeService demandeDeService) {
        log.debug("Request to save DemandeDeService : {}", demandeDeService);
        DemandeDeService result = demandeDeServiceRepository.save(demandeDeService);
        demandeDeServiceSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the demandeDeServices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DemandeDeService> findAll(Pageable pageable) {
        log.debug("Request to get all DemandeDeServices");
        return demandeDeServiceRepository.findAll(pageable);
    }

    /**
     * Get all the demandeDeServices with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DemandeDeService> findAllWithEagerRelationships(Pageable pageable) {
        return demandeDeServiceRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one demandeDeService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DemandeDeService> findOne(Long id) {
        log.debug("Request to get DemandeDeService : {}", id);
        return demandeDeServiceRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the demandeDeService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DemandeDeService : {}", id);
        demandeDeServiceRepository.deleteById(id);
        demandeDeServiceSearchRepository.deleteById(id);
    }

    /**
     * Search for the demandeDeService corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DemandeDeService> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of DemandeDeServices for query {}", query);
        return demandeDeServiceSearchRepository.search(queryStringQuery(query), pageable);    }
}
