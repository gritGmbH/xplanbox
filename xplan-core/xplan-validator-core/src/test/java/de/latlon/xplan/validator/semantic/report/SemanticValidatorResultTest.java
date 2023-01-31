/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.semantic.report;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Text.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 */
public class SemanticValidatorResultTest {

	private static final String NAME_1 = "1.1.3";

	private static final String NAME_2 = "1.10.2";

	private static final String NAME_3 = "1.2.1";

	private static final String NAME_4 = "2";

	private static final String NAME_5 = "1.2.1.3";

	private static final String NAME_INVALID = "INVALID";

	@Test
	public void getRules() {
		SemanticValidatorResult result = retrieveResultWithRules();

		List<RuleResult> rules = result.getRules();

		assertThat(rules.get(0).getName(), is(NAME_1));
		assertThat(rules.get(1).getName(), is(NAME_3));
		assertThat(rules.get(2).getName(), is(NAME_5));
		assertThat(rules.get(3).getName(), is(NAME_2));
		assertThat(rules.get(4).getName(), is(NAME_4));
	}

	@Test
	public void getRulesWithInvalid() {
		SemanticValidatorResult result = retrieveResultWithRules();
		InvalidFeaturesResult id_2 = new InvalidFeaturesResult("id_2");
		result.addRule(NAME_INVALID, "invalid", Collections.singletonList(id_2));

		List<RuleResult> rules = result.getRules();

		assertThat(rules.get(5).getName(), is(NAME_INVALID));
	}

	private SemanticValidatorResult retrieveResultWithRules() {
		SemanticValidatorResult result = new SemanticValidatorResult();
		result.addRule(NAME_1, "message1", Collections.emptyList());
		InvalidFeaturesResult id_2 = new InvalidFeaturesResult("id_2");
		result.addRule(NAME_2, "message2", Collections.singletonList(id_2));
		result.addRule(NAME_3, "message3", Collections.emptyList());
		result.addRule(NAME_4, "message4", Collections.emptyList());
		result.addRule(NAME_5, "message5", Collections.emptyList());
		return result;
	}

}
