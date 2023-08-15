/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplanbox.api.commons;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;
import de.latlon.xplanbox.api.commons.exception.UnsupportedHeaderValue;
import de.latlon.xplanbox.api.commons.exception.UnsupportedParameterValue;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
class ValidatorConverterTest {

	@Test
	void verifyThat_UuidIsReturnedForNull() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		assertTrue(ValidatorConverter.detectOrCreateValidationName(null)
			.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[4][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$"));
	}

	@Test
	void verifyThat_FilenameIsReturned() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		assertThat(ValidatorConverter.detectOrCreateValidationName("xplan.gml")).contains("xplan");
	}

	@Test
	void verifyThat_FilenameWithoutSuffixIsReturned() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		assertThat(ValidatorConverter.detectOrCreateValidationName("xplan.file.name.gml")).contains("xplan.file.name");
	}

	@Test
	void verifyThat_NameIsReturned() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		assertThat(ValidatorConverter.detectOrCreateValidationName("xplan.gml", "XPlanArchive"))
			.contains("XPlanArchive");
	}

	@Test
	void verifyThat_CreateValidationSettings_ReturnsCompleteSettings() {
		ValidationSettings validationSettings = ValidatorConverter.createValidationSettings("foo", false, true, true,
				false, true, Collections.singletonList("10"));
		assertThat(validationSettings.getValidationName()).contains("foo");
		assertThat(validationSettings.getValidationTypes()).contains(ValidationType.GEOMETRIC);
		assertThat(validationSettings.getExtendedOptions()).contains(GeometricValidatorImpl.SKIP_FLAECHENSCHLUSS);
		assertThat(validationSettings.getExtendedOptions()).contains(GeometricValidatorImpl.SKIP_LAUFRICHTUNG);
		assertThat(validationSettings.getProfiles()).contains("10");
	}

	@Test
	void verifyThat_NameIsInvalid() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		assertThrows(UnsupportedParameterValue.class, () -> {
			ValidatorConverter.detectOrCreateValidationName("xplan.gml", "XPlan Archive");
		});
	}

	@Test
	void verifyThat_XFilenameIsInvalid() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		assertThrows(UnsupportedHeaderValue.class, () -> {
			ValidatorConverter.detectOrCreateValidationName("xplan 2.gml");
		});
	}

}
