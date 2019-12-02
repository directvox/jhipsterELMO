package io.github.jhipster.application.repository.search;
import io.github.jhipster.application.domain.DemandeDeService;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DemandeDeService} entity.
 */
public interface DemandeDeServiceSearchRepository extends ElasticsearchRepository<DemandeDeService, Long> {
}
