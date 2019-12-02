package io.github.jhipster.application.repository.search;
import io.github.jhipster.application.domain.FormulaireConsentement;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link FormulaireConsentement} entity.
 */
public interface FormulaireConsentementSearchRepository extends ElasticsearchRepository<FormulaireConsentement, Long> {
}
