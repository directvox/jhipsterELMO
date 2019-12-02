package io.github.jhipster.application.repository.search;
import io.github.jhipster.application.domain.FormulaireEvaluation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link FormulaireEvaluation} entity.
 */
public interface FormulaireEvaluationSearchRepository extends ElasticsearchRepository<FormulaireEvaluation, Long> {
}
