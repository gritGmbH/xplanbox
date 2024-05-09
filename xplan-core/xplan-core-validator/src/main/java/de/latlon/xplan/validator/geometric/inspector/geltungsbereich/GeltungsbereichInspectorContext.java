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
package de.latlon.xplan.validator.geometric.inspector.geltungsbereich;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.geometric.inspector.model.FeatureUnderTest;
import de.latlon.xplan.validator.geometric.inspector.model.InspectorContext;
import org.deegree.feature.Feature;

/**
 * Holds the features relevant for the GeltungsbereichInspector
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichInspectorContext extends InspectorContext {

	private final GeltungsbereichFeatureAnalyser featureAnalyser = new GeltungsbereichFeatureAnalyser();

	public GeltungsbereichInspectorContext(XPlanVersion xPlanVersion) {
		super(xPlanVersion);
	}

	@Override
	protected void addFeatureUnderTest(Feature feature) {
		if (!featureAnalyser.isAllowedToBeOutside(feature) && !featureAnalyser.isFeatureCollection(feature)
				&& !feature.getGeometryProperties().isEmpty()) {
			featuresUnderTest.add(new FeatureUnderTest(feature, this));
		}
	}

}
