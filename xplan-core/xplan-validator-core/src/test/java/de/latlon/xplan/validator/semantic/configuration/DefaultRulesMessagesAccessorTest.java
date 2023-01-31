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
package de.latlon.xplan.validator.semantic.configuration;

import de.latlon.xplan.validator.semantic.configuration.message.DefaultRulesMessagesAccessor;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class DefaultRulesMessagesAccessorTest {

	private DefaultRulesMessagesAccessor rulesMessagesAccessor = new DefaultRulesMessagesAccessor();

	@Test
	public void testRetrieveMessageForRule_KnownRuleWithoutVersion() {
		String message = rulesMessagesAccessor.retrieveMessageForRule("3.1.2.3", null);
		assertThat(message, is("Regel 3.1.2.3 muss erf\u00FCllt sein"));
	}

	@Test
	public void testRetrieveMessageForRule_UnknownRuleWithoutVersion() {
		String message = rulesMessagesAccessor.retrieveMessageForRule("unknownRule", null);
		assertThat(message, notNullValue());
	}

	@Test
	public void testRetrieveMessageForRule_KnownRuleWithVersion() {
		String message = rulesMessagesAccessor.retrieveMessageForRule("3.1.2.3", XPLAN_51);
		assertThat(message, is("XP_Bereich: Relation auf Basis-Rasterplan"));
	}

	@Test
	public void testRetrieveMessageForRule_UnknownRuleWithVersion() {
		String message = rulesMessagesAccessor.retrieveMessageForRule("unknownRule", XPLAN_51);
		assertThat(message, notNullValue());
	}

	@Test
	public void testRetrieveMessageForRule_EmptyRule() {
		String message = rulesMessagesAccessor.retrieveMessageForRule("", null);
		assertThat(message, notNullValue());
	}

	@Test
	public void testRetrieveMessageForRule_NullRule() {
		String message = rulesMessagesAccessor.retrieveMessageForRule(null, null);
		assertThat(message, notNullValue());
	}

}
