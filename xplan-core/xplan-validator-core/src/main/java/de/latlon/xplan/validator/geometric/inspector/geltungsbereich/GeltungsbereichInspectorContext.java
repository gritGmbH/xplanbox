/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.inspector.geltungsbereich;

import org.deegree.feature.Feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds the features relevant for the GeltungsbereichInspector
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichInspectorContext {

	public static final double TOLERANCE_METRE = 0.001;

	private final GeltungsbereichFeatureAnalyser featureAnalyser = new GeltungsbereichFeatureAnalyser();

	private Map<String, PlanFeature> planFeatures = new HashMap<>();

	private Map<String, BereichFeature> bereichFeatures = new HashMap<>();

	private List<InGeltungsbereichFeature> inGeltungsbereichFeatures = new ArrayList<>();

	/**
	 * Adss a new feature to the context.
	 * @param feature to add, never <code>null</code>
	 */
	void addToContext(Feature feature) {
		if (featureAnalyser.isPlanFeature(feature)) {
			planFeatures.put(feature.getId(), new PlanFeature(feature, featureAnalyser, TOLERANCE_METRE));
		}
		else if (featureAnalyser.isBereichFeature(feature)) {
			bereichFeatures.put(feature.getId(), new BereichFeature(feature, featureAnalyser, TOLERANCE_METRE));
		}
		else if (!featureAnalyser.isAllowedToBeOutside(feature)) {
			inGeltungsbereichFeatures.add(new InGeltungsbereichFeature(feature, featureAnalyser, this));
		}
	}

	/**
	 * @return all plan features, never <code>null</code>
	 */
	public Map<String, PlanFeature> getPlanFeatures() {
		return planFeatures;
	}

	/**
	 * @return all bereich features, never <code>null</code>
	 */
	public Map<String, BereichFeature> getBereichFeatures() {
		return bereichFeatures;
	}

	/**
	 * @return all features which should be part of the Geltungsbereich, never
	 * <code>null</code>
	 */
	public List<InGeltungsbereichFeature> getInGeltungsbereichFeatures() {
		return inGeltungsbereichFeatures;
	}

}
