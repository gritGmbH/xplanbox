/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.praesentation;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributeProperty;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.composite.CompositeGeometry;
import org.deegree.geometry.multi.MultiGeometry;
import org.deegree.geometry.primitive.GeometricPrimitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static de.latlon.xplan.manager.synthesizer.expression.praesentation.GeometryTypeAbbreviation.LINE;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.GeometryTypeAbbreviation.POINT;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.GeometryTypeAbbreviation.POLYGON;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.GeometryTypeAbbreviation.UNKNOWN;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.CODE;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.ENUM;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.PRIMITIVE;
import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.toPrimitiveValue;

/**
 * Creates the stylesheetIds dependent on the referenced feature (via
 * xplan:dientZurDarstellungVon):
 *
 * <pre>
 *     <Objektklasse>[<Attribut>=<Wert>][…]_<Geometrietypkuerzel F|L|P>
 *
 *     Examples:
 *       BP_GemeinbedarfsFlaeche[zweckbestimmung=1000]_F
 *       BP_BaugebietsTeilFlaeche[text]_F
 * </pre>
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class StylesheetIdLookup extends PraesentationsobjektLookup {

	private static final Logger LOG = LoggerFactory.getLogger(StylesheetIdLookup.class);

	private final Xpath stylesheetId;

	public StylesheetIdLookup() {
		super();
		this.stylesheetId = new Xpath("xplan:stylesheetId");
	}

	@Override
	protected TypedObjectNode evaluate(Feature feature, FeatureCollection features, PlanContext planContext,
			Feature referencedFeature, List<AttributeProperty> attributeProperty) {
		if (referencedFeature != null) {
			GeometryTypeAbbreviation geomTypeAbbr = parseGeometryType(referencedFeature);
			String objectClass = referencedFeature.getType().getName().getLocalPart();
			String stylesheetId = createStylesheetId(objectClass, attributeProperty, geomTypeAbbr);
			return toPrimitiveValue(stylesheetId);
		}
		return stylesheetId.evaluate(feature, features, planContext);
	}

	private String createStylesheetId(String objectClass, List<AttributeProperty> attributeProperties,
			GeometryTypeAbbreviation geomTypeAbbr) {
		StringBuffer sb = new StringBuffer();
		sb.append(objectClass);
		if (attributeProperties != null) {
			for (AttributeProperty attributeProperty : attributeProperties) {
				if (attributeProperty.getAttributePropertyType().equals(ENUM)
						|| attributeProperty.getAttributePropertyType().equals(CODE)) {
					sb.append("[");
					sb.append(attributeProperty.getAttribute());
					if (attributeProperty.getValue() != null)
						sb.append("=").append(attributeProperty.getValue());
					sb.append("]");
				}
				else if (attributeProperty.getAttributePropertyType().equals(PRIMITIVE)) {
					sb.append("[");
					sb.append(attributeProperty.getAttribute());
					sb.append("]");
				}
			}
		}
		sb.append("_");
		sb.append(geomTypeAbbr.getAbbreviation());
		return sb.toString();
	}

	private GeometryTypeAbbreviation parseGeometryType(Feature referencedFeature) {
		List<Property> geometryProperties = referencedFeature.getGeometryProperties();
		if (geometryProperties.size() != 1) {
			LOG.warn("Could not find geometry property");
			return UNKNOWN;
		}
		Geometry geometry = (Geometry) geometryProperties.get(0).getValue();
		return parseGeometryType(geometry);
	}

	private static GeometryTypeAbbreviation parseGeometryType(Geometry geometry) {
		switch (geometry.getGeometryType()) {
			case ENVELOPE:
				return POLYGON;
			case PRIMITIVE_GEOMETRY:
				GeometricPrimitive.PrimitiveType primitiveType = ((GeometricPrimitive) geometry).getPrimitiveType();
				switch (primitiveType) {
					case Point:
						return POINT;
					case Curve:
						return LINE;
					case Surface:
					case Solid:
						return POLYGON;
				}
				break;
			case COMPOSITE_GEOMETRY:
				CompositeGeometry compositeGeometry = (CompositeGeometry) geometry;
				Optional firstCompositeGeom = compositeGeometry.stream().findFirst();
				if (firstCompositeGeom.isPresent()) {
					Geometry firstCompositeGeometry = (Geometry) firstCompositeGeom.get();
					return parseGeometryType(firstCompositeGeometry);
				}
			case MULTI_GEOMETRY:
				MultiGeometry multiGeometry = (MultiGeometry) geometry;
				Optional firstMultiGeom = multiGeometry.stream().findFirst();
				if (firstMultiGeom.isPresent()) {
					Geometry firstCompositeGeometry = (Geometry) firstMultiGeom.get();
					return parseGeometryType(firstCompositeGeometry);
				}
		}
		return UNKNOWN;
	}

}
