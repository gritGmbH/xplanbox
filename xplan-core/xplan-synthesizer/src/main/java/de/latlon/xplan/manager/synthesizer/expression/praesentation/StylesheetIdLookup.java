/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.praesentation;

import de.latlon.xplan.manager.synthesizer.expression.Expression;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.composite.CompositeGeometry;
import org.deegree.geometry.multi.MultiGeometry;
import org.deegree.geometry.primitive.GeometricPrimitive;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.synthesizer.expression.praesentation.GeometryTypeAbbreviation.LINE;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.GeometryTypeAbbreviation.POINT;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.GeometryTypeAbbreviation.POLYGON;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.GeometryTypeAbbreviation.UNKNOWN;
import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.castToArray;
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
public class StylesheetIdLookup implements Expression {

	private static final Logger LOG = LoggerFactory.getLogger(StylesheetIdLookup.class);

	private final Xpath artXPath;

	private final Xpath dientZurDarstellungVonXPath;

	private final Xpath stylesheetId;

	public StylesheetIdLookup() {
		this.dientZurDarstellungVonXPath = new Xpath("xplan:dientZurDarstellungVon");
		this.artXPath = new Xpath("xplan:art");
		this.stylesheetId = new Xpath("xplan:stylesheetId");
	}

	@Override
	public TypedObjectNode evaluate(Feature feature, FeatureCollection features) {
		try {
			Feature referencedFeature = resolveDientZurDarstellungVon(feature, features);
			if (referencedFeature != null) {
				List<AttributeProperty> attributeProperty = parseArtProperties(feature, features, referencedFeature);
				if (!attributeProperty.isEmpty()) {
					GeometryTypeAbbreviation geomTypeAbbr = parseGeometryType(referencedFeature);
					String objectClass = referencedFeature.getType().getName().getLocalPart();
					String stylesheetId = createStylesheetId(objectClass, attributeProperty, geomTypeAbbr);
					return toPrimitiveValue(stylesheetId);
				}
			}
		}
		catch (FilterEvaluationException e) {
			LOG.warn("Could not evaluate stylesheetId of feature with ID {}", feature.getId());
		}
		return stylesheetId.evaluate(feature, features);
	}

	private Feature resolveDientZurDarstellungVon(Feature feature, FeatureCollection features)
			throws FilterEvaluationException {
		TypedObjectNode dientZurDarstellungVonProperty = dientZurDarstellungVonXPath.evaluate(feature, features);
		if (dientZurDarstellungVonProperty instanceof GenericProperty) {
			List<TypedObjectNode> children = ((GenericProperty) dientZurDarstellungVonProperty).getChildren();
			if (!children.isEmpty() && children.get(0) instanceof FeatureReference)
				return ((FeatureReference) children.get(0)).getReferencedObject();
		}
		else if (dientZurDarstellungVonProperty instanceof FeatureReference) {
			return ((FeatureReference) dientZurDarstellungVonProperty).getReferencedObject();
		}
		return null;
	}

	private String createStylesheetId(String objectClass, List<AttributeProperty> attributeProperties,
			GeometryTypeAbbreviation geomTypeAbbr) {
		StringBuffer sb = new StringBuffer();
		sb.append(objectClass);
		for (AttributeProperty attributeProperty : attributeProperties) {
			sb.append("[");
			sb.append(attributeProperty.getAttribute());
			if (attributeProperty.getValue() != null)
				sb.append("=").append(attributeProperty.getValue());
			sb.append("]");
		}
		sb.append("_");
		sb.append(geomTypeAbbr.getAbbreviation());
		return sb.toString();
	}

	private List<AttributeProperty> parseArtProperties(Feature feature, FeatureCollection features,
			Feature referencedFeature) {
		List<AttributeProperty> attributeProperties = new ArrayList<>();
		TypedObjectNodeArray<TypedObjectNode> propertiesArray = castToArray(artXPath.evaluate(feature, features));
		if (propertiesArray != null) {
			TypedObjectNode[] artProperties = propertiesArray.getElements();
			for (TypedObjectNode artProperty : artProperties) {
				List<Step> artPropertySteps = parseArtAsXPath(artProperty);
				AttributeProperty attributeProperty = parseArtProperty(referencedFeature, artPropertySteps);
				if (attributeProperty != null)
					attributeProperties.add(attributeProperty);
			}
		}
		return attributeProperties;
	}

	private static AttributeProperty parseArtProperty(Feature referencedFeature, List<Step> artPropertySteps) {
		if (!artPropertySteps.isEmpty()) {
			int firstStepIndex = 0;
			Step firstStep = artPropertySteps.get(firstStepIndex);
			List<Property> properties = referencedFeature
					.getProperties(new QName(referencedFeature.getName().getNamespaceURI(), firstStep.name));
			Property propertyStep = properties.get(firstStep.index);
			AttributeProperty attributeProperty = parseArtProperty(firstStep, artPropertySteps, 1, propertyStep);
			if (attributeProperty != null)
				return attributeProperty;
		}
		return null;
	}

	private static AttributeProperty parseArtProperty(Step currentStep, List<Step> allSteps, int nextStepIndex,
			TypedObjectNode stepValue) {
		if (stepValue instanceof PrimitiveValue) {
			String primitiveValue = ((PrimitiveValue) stepValue).getAsText();
			return new AttributeProperty(currentStep.name, primitiveValue);
		}
		else if (stepValue instanceof GenericProperty) {
			TypedObjectNode propertyStepValue = ((GenericProperty) stepValue).getValue();
			return parseArtProperty(currentStep, allSteps, nextStepIndex, propertyStepValue);
		}
		else if (stepValue instanceof SimpleProperty) {
			TypedObjectNode propertyStepValue = ((SimpleProperty) stepValue).getValue();
			return parseArtProperty(currentStep, allSteps, nextStepIndex, propertyStepValue);
		}
		else if (stepValue instanceof GenericXMLElement) {
			GenericXMLElement stepValueGenericXml = (GenericXMLElement) stepValue;
			if (allSteps.size() > nextStepIndex) {
				Step nextStep = allSteps.get(nextStepIndex);
				List<TypedObjectNode> children = stepValueGenericXml.getChildren();
				List<TypedObjectNode> childrenWithStepName = children.stream().filter(child -> {
					if (child instanceof GenericXMLElement)
						return ((GenericXMLElement) child).getName().getLocalPart().equals(nextStep.name);
					return false;
				}).collect(Collectors.toList());
				if (!childrenWithStepName.isEmpty()) {
					return parseArtProperty(nextStep, allSteps, nextStepIndex + 1, childrenWithStepName.get(0));
				}
				else if (!children.isEmpty()) {
					return parseArtProperty(nextStep, allSteps, nextStepIndex,
							stepValueGenericXml.getChildren().get(0));
				}
			}
			else if (stepValueGenericXml.getChildren().size() == 1) {
				List<TypedObjectNode> children = stepValueGenericXml.getChildren();
				return parseArtProperty(currentStep, allSteps, nextStepIndex, children.get(0));
			}
		}
		return new AttributeProperty(currentStep.name);
	}

	private List<Step> parseArtAsXPath(TypedObjectNode art) {
		if (art instanceof SimpleProperty && ((SimpleProperty) art).getValue() instanceof PrimitiveValue) {
			String artProperty = ((SimpleProperty) art).getValue().getAsText();
			String[] split = artProperty.split("/");
			return Arrays.stream(split).map(step -> {
				if (step.endsWith("]")) {
					int indexOfPredicateBegin = step.indexOf("[");
					if (indexOfPredicateBegin > 1) {
						String name = step.substring(0, indexOfPredicateBegin);
						String position = step.substring(indexOfPredicateBegin + 1, step.length() - 1);
						try {
							return new Step(name, Integer.parseInt(position));
						}
						catch (NumberFormatException e) {
							LOG.warn("Could not parse " + position + " as integer.");
						}
					}
				}
				return new Step(step);
			}).collect(Collectors.toList());
		}
		return Collections.emptyList();
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

	private class Step {

		String name;

		int index;

		public Step(String name) {
			this(name, 0);
		}

		public Step(String name, int position) {
			this.name = name;
			this.index = position;
		}

	}

}
