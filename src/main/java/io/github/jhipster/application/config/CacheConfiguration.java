package io.github.jhipster.application.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(io.github.jhipster.application.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(io.github.jhipster.application.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            createCache(cm, io.github.jhipster.application.domain.SpecClinique.class.getName());
            createCache(cm, io.github.jhipster.application.domain.SpecClinique.class.getName() + ".destinataires");
            createCache(cm, io.github.jhipster.application.domain.DemandeDeService.class.getName());
            createCache(cm, io.github.jhipster.application.domain.DemandeDeService.class.getName() + ".formulaireEvaluations");
            createCache(cm, io.github.jhipster.application.domain.AttachementDemandeDeService.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Patient.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Destinataire.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Destinataire.class.getName() + ".specCliniques");
            createCache(cm, io.github.jhipster.application.domain.Intervenant.class.getName());
            createCache(cm, io.github.jhipster.application.domain.FormulaireEvaluation.class.getName());
            createCache(cm, io.github.jhipster.application.domain.FormulaireEvaluation.class.getName() + ".demandeDeServices");
            createCache(cm, io.github.jhipster.application.domain.FormulaireConsentement.class.getName());
            createCache(cm, io.github.jhipster.application.domain.Consentement.class.getName());
            createCache(cm, io.github.jhipster.application.domain.ReponseDemandeDeService.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }
}
