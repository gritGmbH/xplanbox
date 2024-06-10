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
package de.latlon.xplan.manager.web.client.comparator;

import de.latlon.xplan.manager.web.client.gui.PlanListColumnType;
import de.latlon.xplan.manager.web.shared.XPlan;

import java.util.Comparator;
import java.util.Date;

import static java.lang.Integer.parseInt;

/**
 * Compares different columns (name, id, type, additional type, legislation status,
 * release date, import date and ade).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ColumnComparator implements Comparator<XPlan> {

	private static final int IS_EQUAL = 0;

	private static final int IS_GREATER = 1;

	private static final int IS_SMALLER = -1;

	private PlanListColumnType type;

	/**
	 * @param type indicating the column to compare, never <code>null</code>
	 * @throws NullPointerException if type is <code>null</code>
	 */
	public ColumnComparator(PlanListColumnType type) {
		if (type == null)
			throw new NullPointerException();
		this.type = type;
	}

	@Override
	public int compare(XPlan first, XPlan second) {
		if (first == null && second == null)
			return IS_EQUAL;
		if (first == null)
			return IS_SMALLER;
		if (second == null)
			return IS_GREATER;
		if (first.equals(second))
			return IS_EQUAL;
		switch (type) {
			case NAME:
				return compareString(first.getName(), second.getName());
			case ID:
				Integer firstIdNumber = parseInt(first.getId());
				Integer secondIdNumber = parseInt(second.getId());
				return firstIdNumber.compareTo(secondIdNumber);
			case NUMBER:
				return compareString(first.getNumber(), second.getNumber());
			case TYPE:
				return compareString(first.getType(), second.getType());
			case ADDITIONALTYPE:
				return compareString(first.getAdditionalType(), second.getAdditionalType());
			case LEGISLATIONSTATUS:
				return compareString(first.getLegislationStatus(), second.getLegislationStatus());
			case RELEASEDATE:
				return compareDate(first.getReleaseDate(), second.getReleaseDate());
			case IMPORTDATE:
				return compareDate(first.getImportDate(), second.getImportDate());
			case PLANSTATUS:
				String firstPlanStatus = retrievePlanStatus(first);
				String secondPlanStatus = retrievePlanStatus(second);
				return compareString(firstPlanStatus, secondPlanStatus);
			case VERSION:
				return compareString(first.getVersion(), second.getVersion());
		}
		return IS_SMALLER;
	}

	private int compareString(String first, String second) {
		return first == null ? (second == null ? IS_EQUAL : IS_SMALLER)
				: (second == null ? IS_GREATER : first.compareTo(second));
	}

	private int compareDate(Date first, Date second) {
		return first == null ? (second == null ? IS_EQUAL : IS_SMALLER)
				: (second == null ? IS_GREATER : first.compareTo(second));
	}

	private String retrievePlanStatus(XPlan plan) {
		if (plan.getPlanStatus() != null)
			return plan.getPlanStatus().getMessage();
		return null;
	}

}
