/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.client.filter;

import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class PlanStatusFilterTest {

	@Test
	public void testIsMatchingWithNullStringShouldReturnTrue() throws Exception {
		PlanStatusFilter planStatusFilter = new PlanStatusFilter(null);

		XPlan plan = createPlan(FESTGESTELLT);
		boolean matching = planStatusFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithEmptyStringShouldReturnTrue() throws Exception {
		PlanStatusFilter planStatusFilter = new PlanStatusFilter("");

		XPlan plan = createPlan(FESTGESTELLT);
		boolean matching = planStatusFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	@Test
	public void testIsMatchingWithNotMatchingStringShouldReturnTrue() throws Exception {
		PlanStatusFilter planStatusFilter = new PlanStatusFilter(IN_AUFSTELLUNG.getMessage());

		XPlan plan = createPlan(FESTGESTELLT);
		boolean matching = planStatusFilter.isMatching(plan);

		assertThat(matching, is(false));
	}

	@Test
	public void testIsMatchingWithMatchingStringShouldReturnTrue() throws Exception {
		PlanStatusFilter planStatusFilter = new PlanStatusFilter(FESTGESTELLT.getMessage());

		XPlan plan = createPlan(FESTGESTELLT);
		boolean matching = planStatusFilter.isMatching(plan);

		assertThat(matching, is(true));
	}

	private XPlan createPlan(PlanStatus planStatus) {
		XPlan xPlan = new XPlan("name", "id", "type");
		AdditionalPlanData xplanMetadata = new AdditionalPlanData();
		xplanMetadata.setPlanStatus(planStatus);
		xPlan.setXplanMetadata(xplanMetadata);
		return xPlan;
	}

}
