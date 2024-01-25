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
package de.latlon.xplan.commons;

/**
 * Enumeration for easy differentiating of XPlanGML plan types.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public enum XPlanType {

	BP_Plan, FP_Plan, RP_Plan, LP_Plan, SO_Plan;

	/**
	 * @param name the name
	 * @return the matching entry or <code>null</code> if the XPlanType enum has no entry
	 * with the specified name
	 */
	public static XPlanType valueOfDefaultNull(String name) {
		try {
			return XPlanType.valueOf(name);
		}
		catch (IllegalArgumentException e) {
			return null;
		}
	}

}
