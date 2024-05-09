/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.validator.semantic.configuration;

import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_SO;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_XP;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.NONE;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.getByDirectoryName;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.getByOption;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import de.latlon.xplan.validator.web.shared.ValidationOption;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class SemanticValidationOptionsTest {

	@Test
	public void testGetByDirectoryNameSo() {
		SemanticValidationOptions soOption = getByDirectoryName("so");
		assertThat(soOption, is(IGNORE_SO));
	}

	@Test
	public void testGetByDirectoryNameXp() {
		SemanticValidationOptions soOption = getByDirectoryName("xp");
		assertThat(soOption, is(IGNORE_XP));
	}

	@Test
	public void testGetByDirectoryNameUnknown() {
		SemanticValidationOptions soOption = getByDirectoryName("unknown");
		assertThat(soOption, is(NONE));
	}

	@Test
	public void testGetByDirectoryNameEmpty() {
		SemanticValidationOptions soOption = getByDirectoryName("");
		assertThat(soOption, is(NONE));
	}

	@Test
	public void testGetByOptionSo() {
		SemanticValidationOptions soOption = getByOption(new ValidationOption("ignore-so"));
		assertThat(soOption, is(IGNORE_SO));
	}

	@Test
	public void testGetByOptionXp() {
		SemanticValidationOptions soOption = getByOption(new ValidationOption("ignore-xp"));
		assertThat(soOption, is(IGNORE_XP));
	}

	@Test
	public void testGetByOptionUnknown() {
		SemanticValidationOptions soOption = getByOption(new ValidationOption("unknown"));
		assertThat(soOption, is(NONE));
	}

	@Test
	public void testIgnoreSoName() {
		assertThat(IGNORE_SO.getOptionName(), is("ignore-so"));
	}

	@Test
	public void testIgnoreXpName() {
		assertThat(IGNORE_XP.getOptionName(), is("ignore-xp"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetByDirectoryNameWithNullShouldFail() {
		getByDirectoryName(null);
	}

}
