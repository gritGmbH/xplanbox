package de.latlon.xplanbox.api.commons;

import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

public class ValidatorConverterTest {

    @Test
    public void verifyThat_UuidIsReturnedForNull() {
        assertTrue( ValidatorConverter.detectOrCreateValidationName(null)
                .matches("^[0-9a-f]{8}-[0-9a-f]{4}-[4][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$"));
    }

    @Test
    public void verifyThat_FilenameIsReturned() {
        assertThat( ValidatorConverter.detectOrCreateValidationName("xplan.gml"),
                containsString("xplan"));
    }

    @Test
    public void verifyThat_FilenameWithoutSuffixIsReturned() {
        assertThat( ValidatorConverter.detectOrCreateValidationName("xplan.file.name.gml"),
                containsString("xplan.file.name"));
    }

    @Test
    public void verifyThat_NameIsReturned() {
        assertThat( ValidatorConverter.detectOrCreateValidationName("xplan.gml", "XPlanArchive"),
                containsString("XPlanArchive"));
    }

    @Test
    public void verifyThat_CreateValidationSettings_ReturnsCompleteSettings() {
        ValidationSettings validationSettings = ValidatorConverter.createValidationSettings("foo", false, true, true, false );
        assertThat( validationSettings.getValidationName(), containsString("foo" ) );
        assertThat( validationSettings.getValidationTypes(), hasItem( ValidationType.GEOMETRIC ) );
        assertThat( validationSettings.getExtendedOptions(), hasItem( GeometricValidatorImpl.SKIP_FLAECHENSCHLUSS ) );
    }
}