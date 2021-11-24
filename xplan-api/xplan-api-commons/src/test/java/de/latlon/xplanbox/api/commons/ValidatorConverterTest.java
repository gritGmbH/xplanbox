/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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
		assertTrue(ValidatorConverter.detectOrCreateValidationName(null)
				.matches("^[0-9a-f]{8}-[0-9a-f]{4}-[4][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$"));
	}

	@Test
	public void verifyThat_FilenameIsReturned() {
		assertThat(ValidatorConverter.detectOrCreateValidationName("xplan.gml"), containsString("xplan"));
	}

	@Test
	public void verifyThat_FilenameWithoutSuffixIsReturned() {
		assertThat(ValidatorConverter.detectOrCreateValidationName("xplan.file.name.gml"),
				containsString("xplan.file.name"));
	}

	@Test
	public void verifyThat_NameIsReturned() {
		assertThat(ValidatorConverter.detectOrCreateValidationName("xplan.gml", "XPlanArchive"),
				containsString("XPlanArchive"));
	}

	@Test
	public void verifyThat_CreateValidationSettings_ReturnsCompleteSettings() {
		ValidationSettings validationSettings = ValidatorConverter.createValidationSettings("foo", false, true, true,
				false);
		assertThat(validationSettings.getValidationName(), containsString("foo"));
		assertThat(validationSettings.getValidationTypes(), hasItem(ValidationType.GEOMETRIC));
		assertThat(validationSettings.getExtendedOptions(), hasItem(GeometricValidatorImpl.SKIP_FLAECHENSCHLUSS));
	}

}
