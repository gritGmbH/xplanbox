/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.manager.synthesizer.expression.Expression;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.SchriftinhaltLookup;
import org.junit.Test;

import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.ENUM;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.PRIMITIVE;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RuleParserTest {

	@Test
	public void testParse_Xpath() {
		RuleParser ruleParser = new RuleParser("BP_Plan", "Name", null);
		Xpath xpath = (Xpath) ruleParser.parse("xpath('xplan:drehwinkel/text()')");

		assertThat(xpath.getDefaultValue(), is(nullValue()));
	}

	@Test
	public void testParse_XpathWithDefaultValue() {
		RuleParser ruleParser = new RuleParser("BP_Plan", "Name", null);
		Xpath xpath = (Xpath) ruleParser.parse("xpath('xplan:drehwinkel/text()', 42.0)");

		assertThat(xpath.getDefaultValue(), is(42.0));
	}

	@Test
	public void testParse_SchriftinhaltLookup() {
		RuleParser ruleParser = new RuleParser("BP_Plan", "Name", null);
		Expression expression = ruleParser.parse("schriftinhaltLookup()");

		assertThat(expression, is(instanceOf(SchriftinhaltLookup.class)));
		assertThat(((SchriftinhaltLookup) expression).getPropertyType(), is(PRIMITIVE));
	}

	@Test
	public void testParse_SchriftinhaltLookupWithType() {
		RuleParser ruleParser = new RuleParser("BP_Plan", "Name", null);
		Expression expression = ruleParser.parse("schriftinhaltLookup(ENUM)");

		assertThat(expression, is(instanceOf(SchriftinhaltLookup.class)));
		assertThat(((SchriftinhaltLookup) expression).getPropertyType(), is(ENUM));
	}

}
