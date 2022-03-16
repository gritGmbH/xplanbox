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
import org.deegree.geometry.standard.AbstractDefaultGeometry;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AbstractGeltungsbereichFeature {

	protected GeltungsbereichFeatureAnalyser featureAnalyser;

	protected final Feature feature;

	private AbstractDefaultGeometry originalGeometry;

	private org.locationtech.jts.geom.Geometry jtsGeometry;

	public AbstractGeltungsbereichFeature(Feature feature, GeltungsbereichFeatureAnalyser featureAnalyser) {
		this.feature = feature;
		this.originalGeometry = featureAnalyser.getOriginalGeometry(feature);
		this.featureAnalyser = featureAnalyser;
	}

	/**
	 * @return the ID of the feature
	 */
	public String getFeatureId() {
		return feature.getId();
	}

	/**
	 * @return the feature, never <code>null</code>
	 */
	public Feature getFeature() {
		return feature;
	}

	/**
	 * @return the original geometry of the feature
	 */
	public AbstractDefaultGeometry getOriginalGeometry() {
		return originalGeometry;
	}

	/**
	 * @return the JTS geometry of the feature
	 */
	public org.locationtech.jts.geom.Geometry getJtsGeometry() {
		if (jtsGeometry == null)
			jtsGeometry = featureAnalyser.getJtsGeometry(feature);
		return jtsGeometry;
	}

}
