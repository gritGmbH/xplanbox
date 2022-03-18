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
package de.latlon.xplan.validator.geometric.inspector.flaechenschluss;

import de.latlon.xplan.validator.geometric.inspector.model.FeatureUnderTest;
import de.latlon.xplan.validator.geometric.inspector.model.GeltungsbereichFeature;
import de.latlon.xplan.validator.geometric.inspector.model.InspectorContext;
import org.deegree.feature.Feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FlaechenschlussContext extends InspectorContext {

	private final FlaechenschlussFeatureInspector flaechenschlussFeatureInspector = new FlaechenschlussFeatureInspector();

	@Override
	protected void addFeatureUnderTest(Feature feature) {
		if (flaechenschlussFeatureInspector.isFlaechenschlussobjekt(feature)) {
			featuresUnderTest.add(new FeatureUnderTest(feature, this));
		}
	}

	/**
	 * @return all flaechenschluss features assigned to a plan
	 */
	public Map<GeltungsbereichFeature, List<FeatureUnderTest>> getAllFlaechenschlussFeaturesOfAPlan() {
		Map<GeltungsbereichFeature, List<FeatureUnderTest>> gealtungsbereichFeatureToFeaturesUnderTest = new HashMap();
		for (FeatureUnderTest featureUnderTest : featuresUnderTest) {
			GeltungsbereichFeature geltungsbereichFeature = featureUnderTest.getGeltungsbereichFeature();
			if (!gealtungsbereichFeatureToFeaturesUnderTest.containsKey(geltungsbereichFeature)) {
				gealtungsbereichFeatureToFeaturesUnderTest.put(geltungsbereichFeature, new ArrayList<>());
			}
			gealtungsbereichFeatureToFeaturesUnderTest.get(geltungsbereichFeature).add(featureUnderTest);
		}
		return gealtungsbereichFeatureToFeaturesUnderTest;
	}

}