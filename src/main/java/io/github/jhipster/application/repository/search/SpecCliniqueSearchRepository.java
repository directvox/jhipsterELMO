package io.github.jhipster.application.repository.search;
import io.github.jhipster.application.domain.SpecClinique;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SpecClinique} entity.
 */
public interface SpecCliniqueSearchRepository extends ElasticsearchRepository<SpecClinique, Long> {
}
