package io.github.jhipster.application.repository.search;
import io.github.jhipster.application.domain.Destinataire;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Destinataire} entity.
 */
public interface DestinataireSearchRepository extends ElasticsearchRepository<Destinataire, Long> {
}
