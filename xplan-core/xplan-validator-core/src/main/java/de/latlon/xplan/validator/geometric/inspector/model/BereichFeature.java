/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.inspector.model;

import org.deegree.feature.Feature;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class BereichFeature extends GeltungsbereichFeature {

	private InspectorContext inspectorContext;

	/**
	 * @param feature never <code>null</code>
	 * @param inspectorContext never <code>null</code>
	 * @param toleranceMetre
	 */
	public BereichFeature(Feature feature, InspectorContext inspectorContext, double toleranceMetre) {
		super(feature, toleranceMetre);
		this.inspectorContext = inspectorContext;
	}

	/**
	 * @return the id of the plan this bereich belongs to.
	 */
	public String getPlanId() {
		return getPropertyValue("gehoertZuPlan");
	}

	/**
	 * @return the Plan feature of this BereichFeature, may not be <code>null</code>
	 */
	public PlanFeature getPlanFeature() {
		String planId = getPlanId();
		return inspectorContext.getPlanFeatures().get(planId);
	}

	/**
	 * @return <code>true</code> if this bereich has the property aendertPlanBereich,
	 * <code>false</code> otherwise
	 */
	public boolean isAendertPlanBereich() {
		return getPropertyValue("aendertPlanBereich") != null;
	}

}
