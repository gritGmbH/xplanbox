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

import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanFeature extends GeltungsbereichFeature {

	private InspectorContext inspectorContext;

	/**
	 * @param feature never <code>null</code>
	 * @param inspectorContext
	 * @param toleranceMetre
	 */
	public PlanFeature(Feature feature, InspectorContext inspectorContext, double toleranceMetre) {
		super(feature, toleranceMetre);
		this.inspectorContext = inspectorContext;
	}

	/**
	 * @return <code>true</code> if this plan has the property aendertPlan or at least one
	 * of the bereiche assigned to this plan has the property aendertPlanBereich,
	 * <code>false</code> otherwise
	 */
	public boolean isAenderungsPlan() {
		boolean aendertPlan = getPropertyValue("aendertPlan") != null;
		if (aendertPlan)
			return true;

		Map<String, BereichFeature> bereichFeatures = inspectorContext.getBereichFeatures();
		long numberOfAenderungsBereichen = bereichFeatures.values()
			.stream()
			.filter(bereichFeature -> getFeatureId().equals(bereichFeature.getPlanId())
					&& bereichFeature.isAendertPlanBereich())
			.count();

		return numberOfAenderungsBereichen > 0;
	}

}
