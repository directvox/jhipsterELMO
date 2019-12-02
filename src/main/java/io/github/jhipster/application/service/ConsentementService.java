package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Consentement;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Consentement}.
 */
public interface ConsentementService {

    /**
     * Save a consentement.
     *
     * @param consentement the entity to save.
     * @return the persisted entity.
     */
    Consentement save(Consentement consentement);

    /**
     * Get all the consentements.
     *
     * @return the list of entities.
     */
    List<Consentement> findAll();


    /**
     * Get the "id" consentement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Consentement> findOne(Long id);

    /**
     * Delete the "id" consentement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the consentement corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<Consentement> search(String query);
}
