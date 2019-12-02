package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.ReponseDemandeDeServiceService;
import io.github.jhipster.application.domain.ReponseDemandeDeService;
import io.github.jhipster.application.repository.ReponseDemandeDeServiceRepository;
import io.github.jhipster.application.repository.search.ReponseDemandeDeServiceSearchRepository;
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
 * Service Implementation for managing {@link ReponseDemandeDeService}.
 */
@Service
@Transactional
public class ReponseDemandeDeServiceServiceImpl implements ReponseDemandeDeServiceService {

    private final Logger log = LoggerFactory.getLogger(ReponseDemandeDeServiceServiceImpl.class);

    private final ReponseDemandeDeServiceRepository reponseDemandeDeServiceRepository;

    private final ReponseDemandeDeServiceSearchRepository reponseDemandeDeServiceSearchRepository;

    public ReponseDemandeDeServiceServiceImpl(ReponseDemandeDeServiceRepository reponseDemandeDeServiceRepository, ReponseDemandeDeServiceSearchRepository reponseDemandeDeServiceSearchRepository) {
        this.reponseDemandeDeServiceRepository = reponseDemandeDeServiceRepository;
        this.reponseDemandeDeServiceSearchRepository = reponseDemandeDeServiceSearchRepository;
    }

    /**
     * Save a reponseDemandeDeService.
     *
     * @param reponseDemandeDeService the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ReponseDemandeDeService save(ReponseDemandeDeService reponseDemandeDeService) {
        log.debug("Request to save ReponseDemandeDeService : {}", reponseDemandeDeService);
        ReponseDemandeDeService result = reponseDemandeDeServiceRepository.save(reponseDemandeDeService);
        reponseDemandeDeServiceSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the reponseDemandeDeServices.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReponseDemandeDeService> findAll() {
        log.debug("Request to get all ReponseDemandeDeServices");
        return reponseDemandeDeServiceRepository.findAll();
    }


    /**
     * Get one reponseDemandeDeService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReponseDemandeDeService> findOne(Long id) {
        log.debug("Request to get ReponseDemandeDeService : {}", id);
        return reponseDemandeDeServiceRepository.findById(id);
    }

    /**
     * Delete the reponseDemandeDeService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReponseDemandeDeService : {}", id);
        reponseDemandeDeServiceRepository.deleteById(id);
        reponseDemandeDeServiceSearchRepository.deleteById(id);
    }

    /**
     * Search for the reponseDemandeDeService corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReponseDemandeDeService> search(String query) {
        log.debug("Request to search ReponseDemandeDeServices for query {}", query);
        return StreamSupport
            .stream(reponseDemandeDeServiceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
