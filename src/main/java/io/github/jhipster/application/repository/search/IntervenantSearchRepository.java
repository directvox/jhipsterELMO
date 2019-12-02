package io.github.jhipster.application.repository.search;
import io.github.jhipster.application.domain.Intervenant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Intervenant} entity.
 */
public interface IntervenantSearchRepository extends ElasticsearchRepository<Intervenant, Long> {
}
