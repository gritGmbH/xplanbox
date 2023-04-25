/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanType;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanContext {

	private final XPlanType planType;

	private final String planName;

	/**
	 * @param planType the type of xplan document. See {@link XPlanType}
	 * @param planName the name of xplan document, i.e. the name of the XP_Plan-descendant
	 * feature in the document
	 */
	public PlanContext(XPlanType planType, String planName) {
		this.planType = planType;
		this.planName = planName;
	}

	public XPlanType getPlanType() {
		return planType;
	}

	public String getPlanName() {
		return planName;
	}

}
