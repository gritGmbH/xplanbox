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

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.deegree.gml.reference.FeatureReference;
import org.locationtech.jts.geom.Geometry;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AbstractGeltungsbereichFeature {

	protected final Feature feature;

	private AbstractDefaultGeometry originalGeometry;

	private org.locationtech.jts.geom.Geometry jtsGeometry;

	public AbstractGeltungsbereichFeature(Feature feature) {
		this.feature = feature;
		this.originalGeometry = parseOriginalGeometry();
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
			jtsGeometry = createJtsGeometry();
		return jtsGeometry;
	}

	protected String getPropertyValue(String propName) {
		QName qName = new QName(feature.getName().getNamespaceURI(), propName);
		List<Property> properties = feature.getProperties(qName);
		if (properties == null || properties.isEmpty())
			return null;
		Property property = properties.get(0);
		TypedObjectNode value = property.getValue();
		if (value instanceof FeatureReference)
			return ((FeatureReference) value).getURI().substring(1);
		if (value == null)
			return null;
		return value.toString();
	}

	/**
	 * @return the geometry of the feature, <code>null</code> if the feature has no
	 * geometry
	 */
	private AbstractDefaultGeometry parseOriginalGeometry() {
		List<Property> geometryProperties = feature.getGeometryProperties();
		if (!geometryProperties.isEmpty())
			return (AbstractDefaultGeometry) geometryProperties.get(0).getValue();
		return null;
	}

	/**
	 * @return the geometry of the feature as JTS geometry, <code>null</code> if the
	 * feature has no geometry
	 */
	private Geometry createJtsGeometry() {
		AbstractDefaultGeometry originalGeometry = getOriginalGeometry();
		if (originalGeometry != null)
			return originalGeometry.getJTSGeometry();
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AbstractGeltungsbereichFeature that = (AbstractGeltungsbereichFeature) o;
		return Objects.equals(getFeatureId(), that.getFeatureId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFeatureId());
	}

}
