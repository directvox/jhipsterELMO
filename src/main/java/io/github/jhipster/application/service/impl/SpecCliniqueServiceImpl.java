package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.SpecCliniqueService;
import io.github.jhipster.application.domain.SpecClinique;
import io.github.jhipster.application.repository.SpecCliniqueRepository;
import io.github.jhipster.application.repository.search.SpecCliniqueSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link SpecClinique}.
 */
@Service
@Transactional
public class SpecCliniqueServiceImpl implements SpecCliniqueService {

    private final Logger log = LoggerFactory.getLogger(SpecCliniqueServiceImpl.class);

    private final SpecCliniqueRepository specCliniqueRepository;

    private final SpecCliniqueSearchRepository specCliniqueSearchRepository;

    public SpecCliniqueServiceImpl(SpecCliniqueRepository specCliniqueRepository, SpecCliniqueSearchRepository specCliniqueSearchRepository) {
        this.specCliniqueRepository = specCliniqueRepository;
        this.specCliniqueSearchRepository = specCliniqueSearchRepository;
    }

    /**
     * Save a specClinique.
     *
     * @param specClinique the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SpecClinique save(SpecClinique specClinique) {
        log.debug("Request to save SpecClinique : {}", specClinique);
        SpecClinique result = specCliniqueRepository.save(specClinique);
        specCliniqueSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the specCliniques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SpecClinique> findAll(Pageable pageable) {
        log.debug("Request to get all SpecCliniques");
        return specCliniqueRepository.findAll(pageable);
    }


    /**
     * Get one specClinique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SpecClinique> findOne(Long id) {
        log.debug("Request to get SpecClinique : {}", id);
        return specCliniqueRepository.findById(id);
    }

    /**
     * Delete the specClinique by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SpecClinique : {}", id);
        specCliniqueRepository.deleteById(id);
        specCliniqueSearchRepository.deleteById(id);
    }

    /**
     * Search for the specClinique corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SpecClinique> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SpecCliniques for query {}", query);
        return specCliniqueSearchRepository.search(queryStringQuery(query), pageable);    }
}
