package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterElMoApp;
import io.github.jhipster.application.domain.FormulaireConsentement;
import io.github.jhipster.application.repository.FormulaireConsentementRepository;
import io.github.jhipster.application.repository.search.FormulaireConsentementSearchRepository;
import io.github.jhipster.application.service.FormulaireConsentementService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FormulaireConsentementResource} REST controller.
 */
@SpringBootTest(classes = JhipsterElMoApp.class)
public class FormulaireConsentementResourceIT {

    private static final String DEFAULT_ID_FORMULAIRE = "AAAAAAAAAA";
    private static final String UPDATED_ID_FORMULAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_FORMULAIRE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_FORMULAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_FORMULAIRE = "AAAAAAAAAA";
    private static final String UPDATED_DATE_FORMULAIRE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIF = false;
    private static final Boolean UPDATED_ACTIF = true;

    private static final String DEFAULT_FORMULAIRE_CONSENTEMENT_TEXTE = "AAAAAAAAAA";
    private static final String UPDATED_FORMULAIRE_CONSENTEMENT_TEXTE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_FORMULAIRE_CONSENTEMENT_PDF = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_FORMULAIRE_CONSENTEMENT_PDF = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_FORMULAIRE_CONSENTEMENT_PDF_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_FORMULAIRE_CONSENTEMENT_PDF_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_FORMULAIRE_CONSENTEMENT_URL = "AAAAAAAAAA";
    private static final String UPDATED_FORMULAIRE_CONSENTEMENT_URL = "BBBBBBBBBB";

    @Autowired
    private FormulaireConsentementRepository formulaireConsentementRepository;

    @Autowired
    private FormulaireConsentementService formulaireConsentementService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.FormulaireConsentementSearchRepositoryMockConfiguration
     */
    @Autowired
    private FormulaireConsentementSearchRepository mockFormulaireConsentementSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restFormulaireConsentementMockMvc;

    private FormulaireConsentement formulaireConsentement;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FormulaireConsentementResource formulaireConsentementResource = new FormulaireConsentementResource(formulaireConsentementService);
        this.restFormulaireConsentementMockMvc = MockMvcBuilders.standaloneSetup(formulaireConsentementResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormulaireConsentement createEntity(EntityManager em) {
        FormulaireConsentement formulaireConsentement = new FormulaireConsentement()
            .idFormulaire(DEFAULT_ID_FORMULAIRE)
            .nomFormulaire(DEFAULT_NOM_FORMULAIRE)
            .dateFormulaire(DEFAULT_DATE_FORMULAIRE)
            .actif(DEFAULT_ACTIF)
            .formulaireConsentementTexte(DEFAULT_FORMULAIRE_CONSENTEMENT_TEXTE)
            .formulaireConsentementPDF(DEFAULT_FORMULAIRE_CONSENTEMENT_PDF)
            .formulaireConsentementPDFContentType(DEFAULT_FORMULAIRE_CONSENTEMENT_PDF_CONTENT_TYPE)
            .formulaireConsentementURL(DEFAULT_FORMULAIRE_CONSENTEMENT_URL);
        return formulaireConsentement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FormulaireConsentement createUpdatedEntity(EntityManager em) {
        FormulaireConsentement formulaireConsentement = new FormulaireConsentement()
            .idFormulaire(UPDATED_ID_FORMULAIRE)
            .nomFormulaire(UPDATED_NOM_FORMULAIRE)
            .dateFormulaire(UPDATED_DATE_FORMULAIRE)
            .actif(UPDATED_ACTIF)
            .formulaireConsentementTexte(UPDATED_FORMULAIRE_CONSENTEMENT_TEXTE)
            .formulaireConsentementPDF(UPDATED_FORMULAIRE_CONSENTEMENT_PDF)
            .formulaireConsentementPDFContentType(UPDATED_FORMULAIRE_CONSENTEMENT_PDF_CONTENT_TYPE)
            .formulaireConsentementURL(UPDATED_FORMULAIRE_CONSENTEMENT_URL);
        return formulaireConsentement;
    }

    @BeforeEach
    public void initTest() {
        formulaireConsentement = createEntity(em);
    }

    @Test
    @Transactional
    public void createFormulaireConsentement() throws Exception {
        int databaseSizeBeforeCreate = formulaireConsentementRepository.findAll().size();

        // Create the FormulaireConsentement
        restFormulaireConsentementMockMvc.perform(post("/api/formulaire-consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireConsentement)))
            .andExpect(status().isCreated());

        // Validate the FormulaireConsentement in the database
        List<FormulaireConsentement> formulaireConsentementList = formulaireConsentementRepository.findAll();
        assertThat(formulaireConsentementList).hasSize(databaseSizeBeforeCreate + 1);
        FormulaireConsentement testFormulaireConsentement = formulaireConsentementList.get(formulaireConsentementList.size() - 1);
        assertThat(testFormulaireConsentement.getIdFormulaire()).isEqualTo(DEFAULT_ID_FORMULAIRE);
        assertThat(testFormulaireConsentement.getNomFormulaire()).isEqualTo(DEFAULT_NOM_FORMULAIRE);
        assertThat(testFormulaireConsentement.getDateFormulaire()).isEqualTo(DEFAULT_DATE_FORMULAIRE);
        assertThat(testFormulaireConsentement.isActif()).isEqualTo(DEFAULT_ACTIF);
        assertThat(testFormulaireConsentement.getFormulaireConsentementTexte()).isEqualTo(DEFAULT_FORMULAIRE_CONSENTEMENT_TEXTE);
        assertThat(testFormulaireConsentement.getFormulaireConsentementPDF()).isEqualTo(DEFAULT_FORMULAIRE_CONSENTEMENT_PDF);
        assertThat(testFormulaireConsentement.getFormulaireConsentementPDFContentType()).isEqualTo(DEFAULT_FORMULAIRE_CONSENTEMENT_PDF_CONTENT_TYPE);
        assertThat(testFormulaireConsentement.getFormulaireConsentementURL()).isEqualTo(DEFAULT_FORMULAIRE_CONSENTEMENT_URL);

        // Validate the FormulaireConsentement in Elasticsearch
        verify(mockFormulaireConsentementSearchRepository, times(1)).save(testFormulaireConsentement);
    }

    @Test
    @Transactional
    public void createFormulaireConsentementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = formulaireConsentementRepository.findAll().size();

        // Create the FormulaireConsentement with an existing ID
        formulaireConsentement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFormulaireConsentementMockMvc.perform(post("/api/formulaire-consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireConsentement)))
            .andExpect(status().isBadRequest());

        // Validate the FormulaireConsentement in the database
        List<FormulaireConsentement> formulaireConsentementList = formulaireConsentementRepository.findAll();
        assertThat(formulaireConsentementList).hasSize(databaseSizeBeforeCreate);

        // Validate the FormulaireConsentement in Elasticsearch
        verify(mockFormulaireConsentementSearchRepository, times(0)).save(formulaireConsentement);
    }


    @Test
    @Transactional
    public void getAllFormulaireConsentements() throws Exception {
        // Initialize the database
        formulaireConsentementRepository.saveAndFlush(formulaireConsentement);

        // Get all the formulaireConsentementList
        restFormulaireConsentementMockMvc.perform(get("/api/formulaire-consentements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formulaireConsentement.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFormulaire").value(hasItem(DEFAULT_ID_FORMULAIRE)))
            .andExpect(jsonPath("$.[*].nomFormulaire").value(hasItem(DEFAULT_NOM_FORMULAIRE)))
            .andExpect(jsonPath("$.[*].dateFormulaire").value(hasItem(DEFAULT_DATE_FORMULAIRE)))
            .andExpect(jsonPath("$.[*].actif").value(hasItem(DEFAULT_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].formulaireConsentementTexte").value(hasItem(DEFAULT_FORMULAIRE_CONSENTEMENT_TEXTE.toString())))
            .andExpect(jsonPath("$.[*].formulaireConsentementPDFContentType").value(hasItem(DEFAULT_FORMULAIRE_CONSENTEMENT_PDF_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].formulaireConsentementPDF").value(hasItem(Base64Utils.encodeToString(DEFAULT_FORMULAIRE_CONSENTEMENT_PDF))))
            .andExpect(jsonPath("$.[*].formulaireConsentementURL").value(hasItem(DEFAULT_FORMULAIRE_CONSENTEMENT_URL)));
    }
    
    @Test
    @Transactional
    public void getFormulaireConsentement() throws Exception {
        // Initialize the database
        formulaireConsentementRepository.saveAndFlush(formulaireConsentement);

        // Get the formulaireConsentement
        restFormulaireConsentementMockMvc.perform(get("/api/formulaire-consentements/{id}", formulaireConsentement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(formulaireConsentement.getId().intValue()))
            .andExpect(jsonPath("$.idFormulaire").value(DEFAULT_ID_FORMULAIRE))
            .andExpect(jsonPath("$.nomFormulaire").value(DEFAULT_NOM_FORMULAIRE))
            .andExpect(jsonPath("$.dateFormulaire").value(DEFAULT_DATE_FORMULAIRE))
            .andExpect(jsonPath("$.actif").value(DEFAULT_ACTIF.booleanValue()))
            .andExpect(jsonPath("$.formulaireConsentementTexte").value(DEFAULT_FORMULAIRE_CONSENTEMENT_TEXTE.toString()))
            .andExpect(jsonPath("$.formulaireConsentementPDFContentType").value(DEFAULT_FORMULAIRE_CONSENTEMENT_PDF_CONTENT_TYPE))
            .andExpect(jsonPath("$.formulaireConsentementPDF").value(Base64Utils.encodeToString(DEFAULT_FORMULAIRE_CONSENTEMENT_PDF)))
            .andExpect(jsonPath("$.formulaireConsentementURL").value(DEFAULT_FORMULAIRE_CONSENTEMENT_URL));
    }

    @Test
    @Transactional
    public void getNonExistingFormulaireConsentement() throws Exception {
        // Get the formulaireConsentement
        restFormulaireConsentementMockMvc.perform(get("/api/formulaire-consentements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFormulaireConsentement() throws Exception {
        // Initialize the database
        formulaireConsentementService.save(formulaireConsentement);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockFormulaireConsentementSearchRepository);

        int databaseSizeBeforeUpdate = formulaireConsentementRepository.findAll().size();

        // Update the formulaireConsentement
        FormulaireConsentement updatedFormulaireConsentement = formulaireConsentementRepository.findById(formulaireConsentement.getId()).get();
        // Disconnect from session so that the updates on updatedFormulaireConsentement are not directly saved in db
        em.detach(updatedFormulaireConsentement);
        updatedFormulaireConsentement
            .idFormulaire(UPDATED_ID_FORMULAIRE)
            .nomFormulaire(UPDATED_NOM_FORMULAIRE)
            .dateFormulaire(UPDATED_DATE_FORMULAIRE)
            .actif(UPDATED_ACTIF)
            .formulaireConsentementTexte(UPDATED_FORMULAIRE_CONSENTEMENT_TEXTE)
            .formulaireConsentementPDF(UPDATED_FORMULAIRE_CONSENTEMENT_PDF)
            .formulaireConsentementPDFContentType(UPDATED_FORMULAIRE_CONSENTEMENT_PDF_CONTENT_TYPE)
            .formulaireConsentementURL(UPDATED_FORMULAIRE_CONSENTEMENT_URL);

        restFormulaireConsentementMockMvc.perform(put("/api/formulaire-consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFormulaireConsentement)))
            .andExpect(status().isOk());

        // Validate the FormulaireConsentement in the database
        List<FormulaireConsentement> formulaireConsentementList = formulaireConsentementRepository.findAll();
        assertThat(formulaireConsentementList).hasSize(databaseSizeBeforeUpdate);
        FormulaireConsentement testFormulaireConsentement = formulaireConsentementList.get(formulaireConsentementList.size() - 1);
        assertThat(testFormulaireConsentement.getIdFormulaire()).isEqualTo(UPDATED_ID_FORMULAIRE);
        assertThat(testFormulaireConsentement.getNomFormulaire()).isEqualTo(UPDATED_NOM_FORMULAIRE);
        assertThat(testFormulaireConsentement.getDateFormulaire()).isEqualTo(UPDATED_DATE_FORMULAIRE);
        assertThat(testFormulaireConsentement.isActif()).isEqualTo(UPDATED_ACTIF);
        assertThat(testFormulaireConsentement.getFormulaireConsentementTexte()).isEqualTo(UPDATED_FORMULAIRE_CONSENTEMENT_TEXTE);
        assertThat(testFormulaireConsentement.getFormulaireConsentementPDF()).isEqualTo(UPDATED_FORMULAIRE_CONSENTEMENT_PDF);
        assertThat(testFormulaireConsentement.getFormulaireConsentementPDFContentType()).isEqualTo(UPDATED_FORMULAIRE_CONSENTEMENT_PDF_CONTENT_TYPE);
        assertThat(testFormulaireConsentement.getFormulaireConsentementURL()).isEqualTo(UPDATED_FORMULAIRE_CONSENTEMENT_URL);

        // Validate the FormulaireConsentement in Elasticsearch
        verify(mockFormulaireConsentementSearchRepository, times(1)).save(testFormulaireConsentement);
    }

    @Test
    @Transactional
    public void updateNonExistingFormulaireConsentement() throws Exception {
        int databaseSizeBeforeUpdate = formulaireConsentementRepository.findAll().size();

        // Create the FormulaireConsentement

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFormulaireConsentementMockMvc.perform(put("/api/formulaire-consentements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(formulaireConsentement)))
            .andExpect(status().isBadRequest());

        // Validate the FormulaireConsentement in the database
        List<FormulaireConsentement> formulaireConsentementList = formulaireConsentementRepository.findAll();
        assertThat(formulaireConsentementList).hasSize(databaseSizeBeforeUpdate);

        // Validate the FormulaireConsentement in Elasticsearch
        verify(mockFormulaireConsentementSearchRepository, times(0)).save(formulaireConsentement);
    }

    @Test
    @Transactional
    public void deleteFormulaireConsentement() throws Exception {
        // Initialize the database
        formulaireConsentementService.save(formulaireConsentement);

        int databaseSizeBeforeDelete = formulaireConsentementRepository.findAll().size();

        // Delete the formulaireConsentement
        restFormulaireConsentementMockMvc.perform(delete("/api/formulaire-consentements/{id}", formulaireConsentement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FormulaireConsentement> formulaireConsentementList = formulaireConsentementRepository.findAll();
        assertThat(formulaireConsentementList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the FormulaireConsentement in Elasticsearch
        verify(mockFormulaireConsentementSearchRepository, times(1)).deleteById(formulaireConsentement.getId());
    }

    @Test
    @Transactional
    public void searchFormulaireConsentement() throws Exception {
        // Initialize the database
        formulaireConsentementService.save(formulaireConsentement);
        when(mockFormulaireConsentementSearchRepository.search(queryStringQuery("id:" + formulaireConsentement.getId())))
            .thenReturn(Collections.singletonList(formulaireConsentement));
        // Search the formulaireConsentement
        restFormulaireConsentementMockMvc.perform(get("/api/_search/formulaire-consentements?query=id:" + formulaireConsentement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(formulaireConsentement.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFormulaire").value(hasItem(DEFAULT_ID_FORMULAIRE)))
            .andExpect(jsonPath("$.[*].nomFormulaire").value(hasItem(DEFAULT_NOM_FORMULAIRE)))
            .andExpect(jsonPath("$.[*].dateFormulaire").value(hasItem(DEFAULT_DATE_FORMULAIRE)))
            .andExpect(jsonPath("$.[*].actif").value(hasItem(DEFAULT_ACTIF.booleanValue())))
            .andExpect(jsonPath("$.[*].formulaireConsentementTexte").value(hasItem(DEFAULT_FORMULAIRE_CONSENTEMENT_TEXTE.toString())))
            .andExpect(jsonPath("$.[*].formulaireConsentementPDFContentType").value(hasItem(DEFAULT_FORMULAIRE_CONSENTEMENT_PDF_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].formulaireConsentementPDF").value(hasItem(Base64Utils.encodeToString(DEFAULT_FORMULAIRE_CONSENTEMENT_PDF))))
            .andExpect(jsonPath("$.[*].formulaireConsentementURL").value(hasItem(DEFAULT_FORMULAIRE_CONSENTEMENT_URL)));
    }
}
