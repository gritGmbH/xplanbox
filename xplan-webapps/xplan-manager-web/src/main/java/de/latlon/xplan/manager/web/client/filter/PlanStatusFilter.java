/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.client.filter;

import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;

/**
 * Checks if the plan is assigned to the plan status or not.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class PlanStatusFilter implements PlanFilter {

	private final String planStatus;

	/**
	 * Instantiates a positive filter: a plan matches if the status of the plan is the
	 * same (ignoring case) as the given status.
	 * @param planStatus the expected category, <code>null</code> if the filter is
	 * disabled
	 */
	public PlanStatusFilter(String planStatus) {
		this.planStatus = planStatus;
	}

	@Override
	public boolean isMatching(XPlan plan) {
		if (planStatus == null || planStatus.isEmpty())
			return true;
		String planStatusFromPlan = retrievePlanStatusFromPlan(plan);
		return planStatus.equalsIgnoreCase(planStatusFromPlan);
	}

	private String retrievePlanStatusFromPlan(XPlan plan) {
		AdditionalPlanData xplanMetadata = plan.getXplanMetadata();
		if (xplanMetadata == null)
			return null;
		PlanStatus planStatusFromPlan = xplanMetadata.getPlanStatus();
		if (planStatusFromPlan == null)
			return null;
		return planStatusFromPlan.getMessage();
	}

}
