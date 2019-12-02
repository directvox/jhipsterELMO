package io.github.jhipster.application.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import io.github.jhipster.application.web.rest.TestUtil;

public class SpecCliniqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SpecClinique.class);
        SpecClinique specClinique1 = new SpecClinique();
        specClinique1.setId(1L);
        SpecClinique specClinique2 = new SpecClinique();
        specClinique2.setId(specClinique1.getId());
        assertThat(specClinique1).isEqualTo(specClinique2);
        specClinique2.setId(2L);
        assertThat(specClinique1).isNotEqualTo(specClinique2);
        specClinique1.setId(null);
        assertThat(specClinique1).isNotEqualTo(specClinique2);
    }
}
