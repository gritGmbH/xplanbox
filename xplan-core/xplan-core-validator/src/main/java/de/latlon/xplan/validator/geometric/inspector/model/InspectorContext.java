/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.inspector.model;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.feature.Feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class InspectorContext {

	public static final double TOLERANCE_METRE = 0.001;

	private Map<String, PlanFeature> planFeatures = new HashMap<>();

	private Map<String, BereichFeature> bereichFeatures = new HashMap<>();

	protected List<FeatureUnderTest> featuresUnderTest = new ArrayList<>();

	private final XPlanVersion xPlanVersion;

	public InspectorContext(XPlanVersion xPlanVersion) {
		this.xPlanVersion = xPlanVersion;
	}

	/**
	 * Adds a new feature to the context.
	 * @param feature to add, never <code>null</code>
	 */
	public void addToContext(Feature feature) {
		addToContext(feature, TOLERANCE_METRE);
	}

	/**
	 * Adds a new feature to the context.
	 * @param feature to add, never <code>null</code>
	 * @param toleranceInMeter allowed tolerance in meter, never <code>null</code>
	 */
	public void addToContext(Feature feature, double toleranceInMeter) {
		if (isPlanFeature(feature)) {
			planFeatures.put(feature.getId(), new PlanFeature(feature, this, toleranceInMeter));
		}
		else if (isBereichFeature(feature)) {
			bereichFeatures.put(feature.getId(), new BereichFeature(feature, this, toleranceInMeter));
		}
		else {
			addFeatureUnderTest(feature);
		}
	}

	protected abstract void addFeatureUnderTest(Feature feature);

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
	public List<FeatureUnderTest> getFeaturesUnderTest() {
		return featuresUnderTest;
	}

	/**
	 * @return the version of the plan, never <code>null</code>
	 */
	public XPlanVersion getxPlanVersion() {
		return xPlanVersion;
	}

	private boolean isPlanFeature(Feature feature) {
		return feature.getName().getLocalPart().endsWith("_Plan");
	}

	private boolean isBereichFeature(Feature feature) {
		return feature.getName().getLocalPart().endsWith("_Bereich");
	}

}
