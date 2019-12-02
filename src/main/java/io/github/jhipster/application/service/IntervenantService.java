package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Intervenant;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Intervenant}.
 */
public interface IntervenantService {

    /**
     * Save a intervenant.
     *
     * @param intervenant the entity to save.
     * @return the persisted entity.
     */
    Intervenant save(Intervenant intervenant);

    /**
     * Get all the intervenants.
     *
     * @return the list of entities.
     */
    List<Intervenant> findAll();


    /**
     * Get the "id" intervenant.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Intervenant> findOne(Long id);

    /**
     * Delete the "id" intervenant.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the intervenant corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<Intervenant> search(String query);
}
