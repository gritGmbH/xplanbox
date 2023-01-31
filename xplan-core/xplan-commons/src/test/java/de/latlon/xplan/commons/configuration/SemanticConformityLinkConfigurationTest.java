/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.configuration;

import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class SemanticConformityLinkConfigurationTest {

	@Test
	public void testRetrieveLink() {
		SemanticConformityLinkConfiguration linkConfiguration = new SemanticConformityLinkConfiguration();
		linkConfiguration.addLink(XPLAN_40, "link1");
		linkConfiguration.addLink(XPLAN_41, "link2");
		linkConfiguration.addLink(XPLAN_41, "link3");

		String firstLink = linkConfiguration.retrieveLink(XPLAN_40);
		String overwrittenLink = linkConfiguration.retrieveLink(XPLAN_41);
		String unconfiguredLink = linkConfiguration.retrieveLink(XPLAN_54);

		assertThat(firstLink, is("link1"));
		assertThat(overwrittenLink, is("link3"));
		assertThat(unconfiguredLink, nullValue());
	}

}
