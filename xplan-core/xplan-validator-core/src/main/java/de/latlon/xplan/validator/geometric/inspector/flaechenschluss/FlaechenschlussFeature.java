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

import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.locationtech.jts.geom.Geometry;

import javax.xml.namespace.QName;
import java.util.List;

/**
 *
 * Encapsulates a feature part of the Flaechenschluss.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FlaechenschlussFeature {

	private final Feature feature;

	private AbstractDefaultGeometry geometry;

	private Geometry jtsGeometry;

	public FlaechenschlussFeature(Feature feature) {
		this.feature = feature;
	}

	public String getFeatureId() {
		return feature.getId();
	}

	public String getFeatureType() {
		return feature.getName().getLocalPart();
	}

	public AbstractDefaultGeometry getGeometry() {
		if (geometry == null) {
			String namespaceURI = feature.getType().getName().getNamespaceURI();
			QName positionPropName = new QName(namespaceURI, "position");
			List<Property> positionProperties = feature.getProperties(positionPropName);
			Property property = positionProperties.get(0);
			geometry = (AbstractDefaultGeometry) property.getValue();
		}
		return geometry;
	}

	public Geometry getJtsGeometry() {
		if (jtsGeometry == null) {
			jtsGeometry = geometry.getJTSGeometry();
		}
		return jtsGeometry;
	}

}
