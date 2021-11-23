/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.web.client.filter;

import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
