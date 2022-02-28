/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import de.latlon.xplan.manager.web.shared.XPlan;

/**
 * Common interface for all filters, checking if a plan is visible or not.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public interface PlanFilter {

	/**
	 * Checks if the passed plan matches this filter or not.
	 * @param plan to check, never <code>null</code>
	 * @return <code>true</code> if the plan matches this filter, <code>false</code>
	 * otherwise
	 * @throws NullPointerException - plan is <code>null</code>
	 */
	boolean isMatching(XPlan plan);

}
