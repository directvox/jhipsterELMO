package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.AttachementDemandeDeServiceService;
import io.github.jhipster.application.domain.AttachementDemandeDeService;
import io.github.jhipster.application.repository.AttachementDemandeDeServiceRepository;
import io.github.jhipster.application.repository.search.AttachementDemandeDeServiceSearchRepository;
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
 * Service Implementation for managing {@link AttachementDemandeDeService}.
 */
@Service
@Transactional
public class AttachementDemandeDeServiceServiceImpl implements AttachementDemandeDeServiceService {

    private final Logger log = LoggerFactory.getLogger(AttachementDemandeDeServiceServiceImpl.class);

    private final AttachementDemandeDeServiceRepository attachementDemandeDeServiceRepository;

    private final AttachementDemandeDeServiceSearchRepository attachementDemandeDeServiceSearchRepository;

    public AttachementDemandeDeServiceServiceImpl(AttachementDemandeDeServiceRepository attachementDemandeDeServiceRepository, AttachementDemandeDeServiceSearchRepository attachementDemandeDeServiceSearchRepository) {
        this.attachementDemandeDeServiceRepository = attachementDemandeDeServiceRepository;
        this.attachementDemandeDeServiceSearchRepository = attachementDemandeDeServiceSearchRepository;
    }

    /**
     * Save a attachementDemandeDeService.
     *
     * @param attachementDemandeDeService the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AttachementDemandeDeService save(AttachementDemandeDeService attachementDemandeDeService) {
        log.debug("Request to save AttachementDemandeDeService : {}", attachementDemandeDeService);
        AttachementDemandeDeService result = attachementDemandeDeServiceRepository.save(attachementDemandeDeService);
        attachementDemandeDeServiceSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the attachementDemandeDeServices.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AttachementDemandeDeService> findAll() {
        log.debug("Request to get all AttachementDemandeDeServices");
        return attachementDemandeDeServiceRepository.findAll();
    }


    /**
     * Get one attachementDemandeDeService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AttachementDemandeDeService> findOne(Long id) {
        log.debug("Request to get AttachementDemandeDeService : {}", id);
        return attachementDemandeDeServiceRepository.findById(id);
    }

    /**
     * Delete the attachementDemandeDeService by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AttachementDemandeDeService : {}", id);
        attachementDemandeDeServiceRepository.deleteById(id);
        attachementDemandeDeServiceSearchRepository.deleteById(id);
    }

    /**
     * Search for the attachementDemandeDeService corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AttachementDemandeDeService> search(String query) {
        log.debug("Request to search AttachementDemandeDeServices for query {}", query);
        return StreamSupport
            .stream(attachementDemandeDeServiceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
