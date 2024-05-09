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
package de.latlon.xplan.validator.geometric.inspector.flaechenschluss;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.ValidatorException;
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

	public FlaechenschlussContext(XPlanVersion xPlanVersion) {
		super(xPlanVersion);
	}

	@Override
	protected void addFeatureUnderTest(Feature feature) {
		if (flaechenschlussFeatureInspector.isFlaechenschlussobjekt(feature)) {
			featuresUnderTest.add(new FeatureUnderTest(feature, this));
		}
	}

	/**
	 * @return all flaechenschluss features assigned to a plan
	 */
	public Map<GeltungsbereichFeature, List<FeatureUnderTest>> getAllFlaechenschlussFeaturesOfAPlan()
			throws ValidatorException {
		Map<GeltungsbereichFeature, List<FeatureUnderTest>> geltungsbereichFeatureToFeaturesUnderTest = new HashMap<>();
		for (FeatureUnderTest featureUnderTest : featuresUnderTest) {
			GeltungsbereichFeature geltungsbereichFeature = featureUnderTest.getGeltungsbereichFeature();
			if (geltungsbereichFeature == null)
				throw new ValidatorException("Mindestens das Flaechenschlussobjekt mit der ID "
						+ featureUnderTest.getFeatureId()
						+ " kann keinem Bereich/Plan zugeordnet werden. Die Flaechenschlussprüfung kann nicht durchgefuehrt werden.");
			if (!geltungsbereichFeatureToFeaturesUnderTest.containsKey(geltungsbereichFeature)) {
				geltungsbereichFeatureToFeaturesUnderTest.put(geltungsbereichFeature, new ArrayList<>());
			}
			geltungsbereichFeatureToFeaturesUnderTest.get(geltungsbereichFeature).add(featureUnderTest);
		}
		return geltungsbereichFeatureToFeaturesUnderTest;
	}

}
