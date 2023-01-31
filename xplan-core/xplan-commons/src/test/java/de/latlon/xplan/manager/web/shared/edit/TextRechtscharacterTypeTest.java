/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.web.shared.edit;

import org.junit.Test;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.LP_Plan;
import static de.latlon.xplan.commons.XPlanType.RP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.BP_FESTSETZUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.LP_GEPLANT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_NACHRICHTLICHEUEBERNAHME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TextRechtscharacterTypeTest {

	@Test
	public void testFromCode() {
		assertThat(TextRechtscharacterType.fromCode(1000, XPLAN_50.name(), BP_Plan.name()), is(BP_FESTSETZUNG));
		assertThat(TextRechtscharacterType.fromCode(2000, XPLAN_52.name(), LP_Plan.name()), is(LP_GEPLANT));
		assertThat(TextRechtscharacterType.fromCode(2000, XPLAN_60.name(), LP_Plan.name()),
				is(XP_NACHRICHTLICHEUEBERNAHME));
	}

	@Test
	public void testIsCodeFor() {
		assertThat(BP_FESTSETZUNG.isCodeFor(XPLAN_50.name(), BP_Plan.name()), is(true));
		assertThat(LP_GEPLANT.isCodeFor(XPLAN_52.name(), LP_Plan.name()), is(true));
		assertThat(XP_NACHRICHTLICHEUEBERNAHME.isCodeFor(XPLAN_60.name(), LP_Plan.name()), is(true));

		assertThat(BP_FESTSETZUNG.isCodeFor(XPLAN_50.name(), RP_Plan.name()), is(false));
		assertThat(LP_GEPLANT.isCodeFor(XPLAN_60.name(), LP_Plan.name()), is(false));
		assertThat(XP_NACHRICHTLICHEUEBERNAHME.isCodeFor(XPLAN_52.name(), LP_Plan.name()), is(false));
	}

}
