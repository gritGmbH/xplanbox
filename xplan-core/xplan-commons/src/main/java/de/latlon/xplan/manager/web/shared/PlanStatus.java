/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared;

/**
 * Enumeration for easy differentiating of xplan status.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public enum PlanStatus {

	FESTGESTELLT("Festgestellt"), IN_AUFSTELLUNG("In Aufstellung"), ARCHIVIERT("Archiviert");

	private String message;

	PlanStatus(String message) {
		this.message = message;
	}

	/**
	 * @return message of plan status
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Find the {@link PlanStatus} by the message.
	 * @param mesage of the plan status to return
	 * @return the {@link PlanStatus} with the passed message, never <code>null</code>
	 * @throws IllegalArgumentException if no {@link PlanStatus} with the message exists
	 * (or message is <code>null</code>)
	 */
	public static PlanStatus findByMessage(String mesage) {
		for (PlanStatus planStatus : PlanStatus.values()) {
			if (planStatus.getMessage().equals(mesage))
				return planStatus;
		}
		throw new IllegalArgumentException(
				"No enum constant de.latlon.xplan.manager.web.shared.PlanStatus with message " + mesage);
	}

	/**
	 * Find the {@link PlanStatus} by the legislation status code:
	 * <ul>
	 * <li>FESTGESTELLT: < 0, 3000, 4000
	 * <li>ARCHIVIERT: 4500, 5000
	 * <li>IN_AUFSTELLUNG: other
	 * </ul>
	 * @param legislationStatusCode the legislation status code of the plan
	 * @return the {@link PlanStatus} assigned to the legislation status code, never
	 * <code>null</code>
	 */
	public static PlanStatus findByLegislationStatusCode(int legislationStatusCode) {
		if (legislationStatusCode < 0 || legislationStatusCode == 3000 || legislationStatusCode == 4000)
			return FESTGESTELLT;
		if (legislationStatusCode == 4500 || legislationStatusCode == 5000 || legislationStatusCode == 50000
				|| legislationStatusCode == 50001)
			return ARCHIVIERT;
		return IN_AUFSTELLUNG;
	}

}
