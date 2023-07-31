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

import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;
import de.latlon.xplanbox.api.commons.exception.UnsupportedHeaderValue;
import de.latlon.xplanbox.api.commons.exception.UnsupportedParameterValue;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
public class ValidatorConverterTest {

	@Test
	public void verifyThat_UuidIsReturnedForNull() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		assertTrue(ValidatorConverter.detectOrCreateValidationName(null)
			.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[4][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$"));
	}

	@Test
	public void verifyThat_FilenameIsReturned() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		assertThat(ValidatorConverter.detectOrCreateValidationName("xplan.gml"), containsString("xplan"));
	}

	@Test
	public void verifyThat_FilenameWithoutSuffixIsReturned() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		assertThat(ValidatorConverter.detectOrCreateValidationName("xplan.file.name.gml"),
				containsString("xplan.file.name"));
	}

	@Test
	public void verifyThat_NameIsReturned() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		assertThat(ValidatorConverter.detectOrCreateValidationName("xplan.gml", "XPlanArchive"),
				containsString("XPlanArchive"));
	}

	@Test
	public void verifyThat_CreateValidationSettings_ReturnsCompleteSettings() {
		ValidationSettings validationSettings = ValidatorConverter.createValidationSettings("foo", false, true, true,
				false, true, Collections.singletonList("10"));
		assertThat(validationSettings.getValidationName(), containsString("foo"));
		assertThat(validationSettings.getValidationTypes(), hasItem(ValidationType.GEOMETRIC));
		assertThat(validationSettings.getExtendedOptions(), hasItem(GeometricValidatorImpl.SKIP_FLAECHENSCHLUSS));
		assertThat(validationSettings.getExtendedOptions(), hasItem(GeometricValidatorImpl.SKIP_LAUFRICHTUNG));
		assertThat(validationSettings.getProfiles(), hasItem("10"));
	}

	@Test(expected = UnsupportedParameterValue.class)
	public void verifyThat_NameIsInvalid() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		ValidatorConverter.detectOrCreateValidationName("xplan.gml", "XPlan Archive");
	}

	@Test(expected = UnsupportedHeaderValue.class)
	public void verifyThat_XFilenameIsInvalid() throws UnsupportedParameterValue, UnsupportedHeaderValue {
		ValidatorConverter.detectOrCreateValidationName("xplan 2.gml");
	}

}
