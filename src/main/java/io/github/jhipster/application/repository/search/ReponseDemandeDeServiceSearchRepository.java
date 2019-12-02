package io.github.jhipster.application.repository.search;
import io.github.jhipster.application.domain.ReponseDemandeDeService;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ReponseDemandeDeService} entity.
 */
public interface ReponseDemandeDeServiceSearchRepository extends ElasticsearchRepository<ReponseDemandeDeService, Long> {
}
