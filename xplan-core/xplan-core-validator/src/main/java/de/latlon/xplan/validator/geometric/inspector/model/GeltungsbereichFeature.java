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

import org.deegree.feature.Feature;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class GeltungsbereichFeature extends AbstractGeltungsbereichFeature {

	private org.locationtech.jts.geom.Geometry geometryWithBuffer;

	private final double toleranceMetre;

	/**
	 * @param feature never <code>null</code>
	 */
	public GeltungsbereichFeature(Feature feature, double toleranceMetre) {
		super(feature);
		this.toleranceMetre = toleranceMetre;
	}

	/**
	 * @return the buffered JTS geometry of the feature
	 */
	public org.locationtech.jts.geom.Geometry getBufferedGeometry() {
		if (geometryWithBuffer == null && getJtsGeometry() != null)
			geometryWithBuffer = getJtsGeometry().buffer(toleranceMetre);
		return geometryWithBuffer;
	}

	public boolean hasGeometry() {
		return getOriginalGeometry() != null;
	}

	/**
	 * @return <code>true</code> if the geometry is not empty and valid,
	 * <code>false</code> otherwise
	 */
	public boolean isGeometryValid() {
		org.locationtech.jts.geom.Geometry jtsGeometry = getJtsGeometry();
		if (jtsGeometry != null)
			return jtsGeometry.isValid();
		return false;
	}

}
