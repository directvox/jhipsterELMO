package io.github.jhipster.application.repository.search;
import io.github.jhipster.application.domain.Consentement;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Consentement} entity.
 */
public interface ConsentementSearchRepository extends ElasticsearchRepository<Consentement, Long> {
}
