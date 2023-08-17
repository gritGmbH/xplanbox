/*-
 * #%L
 * xplan-validator-cli - Kommandozeilentool des XPlan Validators
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
package de.latlon.xplan.validator.cli.options;

import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationType;
import org.apache.commons.cli.ParseException;
import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static de.latlon.xplan.validator.web.shared.ValidationType.GEOMETRIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SEMANTIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SYNTACTIC;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for <link>CliOptionsParser</link>
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class CliOptionsParserTest {

	@Test
	public void testParseValidationOptions() throws ParseException {
		CliOptionsParser parser = new CliOptionsParser();
		String[] optionsToParse = new String[] { "-validate", ".", "-name", "validation", "-vo",
				"skip-flaechenschluss" };
		CliOptions options = parser.parse(optionsToParse);
		List<ValidationOption> parsedOptions = options.getVoOptions();
		assertThat(parsedOptions.size(), is(1));
		assertThat(parsedOptions, containsOption("skip-flaechenschluss", null));
	}

	@Test
	public void testParseValidationOptionsWithNullArgumentShouldReturnEmptyList() {
		List<ValidationOption> parsedOptions = new CliOptionsParser().parseValidationOptions(null);
		assertThat(parsedOptions.size(), is(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseValidationOptionsWithEmptyNameShouldFail() {
		new CliOptionsParser().parseValidationOptions(new String[] { "" });
	}

	@Test(expected = ParseException.class)
	public void testInvalidInputIllegalArgument() throws Exception {
		String[] invalidArgs = new String[] { "-invalid", "name", "-validate", "validation" };
		new CliOptionsParser().parse(invalidArgs);
	}

	@Test(expected = ParseException.class)
	public void testInvalidInputWrongArgumentNumber() throws Exception {
		String[] invalidArgs = new String[] {};
		new CliOptionsParser().parse(invalidArgs);
	}

	@Test(expected = ParseException.class)
	public void testInvalidInputNoArgs() throws Exception {
		new CliOptionsParser().parse(new String[0]);
	}

	@Test
	public void testValidateWithVoOptionsExpectCorrectOptions() throws Exception {
		String[] args = new String[] { "-validate", "path", "-name", "validation", "-vo", "skip-flaechenschluss", "-vo",
				"skip-geltungsbereich" };
		CliOptions options = new CliOptionsParser().parse(args);
		List<ValidationOption> voOptions = options.getVoOptions();
		assertThat(voOptions, allOf(hasCorrectArgument("skip-flaechenschluss", null),
				hasCorrectArgument("skip-geltungsbereich", null)));
	}

	@Test
	public void testNoVtypeShouldDefaultToSemantic() throws Exception {
		String[] args = new String[] { "-validate", "path", "-name", "validation" };
		CliOptions options = new CliOptionsParser().parse(args);
		assertThat(options.getValidationTypes(), hasItems(SEMANTIC, SYNTACTIC, GEOMETRIC));
	}

	@Test
	public void testVtype() throws Exception {
		String[] args = new String[] { "-validate", "path", "-name", "validation" };
		CliOptions options = new CliOptionsParser().parse(args);
		assertThat(options.getValidationTypes(), hasItems(SEMANTIC, SYNTACTIC, GEOMETRIC));
	}

	@Test
	public void testVoShouldNotBeRequired() throws Exception {
		String[] args = new String[] { "-validate", "path", "-name", "validation", "-vtype", "syntax,semantic" };
		CliOptions options = new CliOptionsParser().parse(args);
		assertThat(options.getValidationTypes(), hasItems(SYNTACTIC, SEMANTIC));
		assertThat(options.getValidationTypes(), not(hasItem(GEOMETRIC)));
	}

	private BaseMatcher<List<ValidationOption>> hasCorrectArgument(final String parameter, final String value) {
		return new BaseMatcher<List<ValidationOption>>() {

			@SuppressWarnings("unchecked")
			@Override
			public boolean matches(Object item) {
				// noinspection unchecked
				for (ValidationOption validationOption : (List<ValidationOption>) item) {
					if (validationOption.getName().equals(parameter))
						if (validationOption.getArgument() != null)
							return validationOption.getArgument().equals(value);
						else
							return value == null;
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("ValidationOptions should contain " + parameter);
			}
		};
	}

	private Matcher<? super List<ValidationOption>> containsOption(final String name, final String argument) {
		return new BaseMatcher<List<ValidationOption>>() {

			@Override
			public boolean matches(Object item) {
				@SuppressWarnings("unchecked")
				List<ValidationOption> validationOptions = (List<ValidationOption>) item;
				for (ValidationOption validationOption : validationOptions) {
					String optName = validationOption.getName();
					String optArgument = validationOption.getArgument();
					if (optName.equals(name) && (optArgument == null && argument == null
							|| optArgument != null && optArgument.equals(argument))) {
						return true;
					}
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Expected is an option with name " + name + "and argument " + argument);
			}
		};
	}

}
