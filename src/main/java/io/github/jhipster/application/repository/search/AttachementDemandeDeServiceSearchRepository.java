package io.github.jhipster.application.repository.search;
import io.github.jhipster.application.domain.AttachementDemandeDeService;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AttachementDemandeDeService} entity.
 */
public interface AttachementDemandeDeServiceSearchRepository extends ElasticsearchRepository<AttachementDemandeDeService, Long> {
}
