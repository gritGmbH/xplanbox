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
package de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute;

import org.apache.xerces.xs.XSElementDeclaration;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.BaseType;
import org.deegree.commons.tom.primitive.PrimitiveType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.CODE_OR_ENUM;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.OTHER;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.STRING;
import static org.apache.xerces.xs.XSConstants.DERIVATION_NONE;
import static org.deegree.commons.xml.CommonNamespaces.GML3_2_NS;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AttributePropertyParser {

	private static final Logger LOG = LoggerFactory.getLogger(AttributePropertyParser.class);

	public static final String XPLAN_GML_NS_PREFIX = "http://www.xplanung.de/xplangml";

	public List<AttributeProperty> parseAttributeProperties(Feature referencedFeature,
			TypedObjectNodeArray<TypedObjectNode> propertiesArray) {
		TypedObjectNode[] artProperties = propertiesArray.getElements();
		return Arrays.stream(artProperties).map(artProperty -> {
			List<Step> steps = parseXPath(artProperty);
			return parseSteps(referencedFeature, steps);
		}).filter(Objects::nonNull).collect(Collectors.toList());
	}

	private List<Step> parseXPath(TypedObjectNode art) {
		if (art instanceof SimpleProperty && ((SimpleProperty) art).getValue() instanceof PrimitiveValue) {
			String artProperty = ((SimpleProperty) art).getValue().getAsText();
			String[] split = artProperty.split("/");
			return Arrays.stream(split).map(step -> {
				if (step.endsWith("]")) {
					int indexOfPredicateBegin = step.indexOf("[");
					if (indexOfPredicateBegin > 1) {
						String name = step.substring(0, indexOfPredicateBegin);
						String position = step.substring(indexOfPredicateBegin + 1, step.length() - 1);
						return new Step(name, parseAsInteger(position));
					}
				}
				return new Step(step);
			}).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	private int parseAsInteger(String position) {
		try {
			return Integer.parseInt(position);
		}
		catch (NumberFormatException e) {
			LOG.warn("Could not parse " + position + " as integer, assume 0.");
			return 0;
		}
	}

	private static AttributeProperty parseSteps(Feature referencedFeature, List<Step> steps) {
		if (!steps.isEmpty()) {
			int firstStepIndex = 0;
			Step firstStep = steps.get(firstStepIndex);
			List<Property> properties = referencedFeature
					.getProperties(new QName(referencedFeature.getName().getNamespaceURI(), firstStep.name));
			if (properties.size() > firstStep.index) {
				return parseSteps(properties, firstStep.index, firstStep, steps);
			}
			else if (!properties.isEmpty()) {
				LOG.warn(
						"Referenced feature with id {} contains no property with name {} on index {}. Use first property.",
						referencedFeature.getId(), firstStep.name, firstStep.index);
				return parseSteps(properties, 0, firstStep, steps);
			}
			else {
				LOG.warn("Referenced feature with id {} contains no property with name {}", referencedFeature.getId(),
						firstStep.name);
			}
		}
		return null;
	}

	private static AttributeProperty parseSteps(List<Property> properties, int index, Step firstStep,
			List<Step> steps) {
		Property propertyStep = properties.get(index);
		AttributeProperty attributeProperty = parseSteps(firstStep, steps, 1, propertyStep, null);
		if (attributeProperty != null)
			return attributeProperty;
		return null;
	}

	private static AttributeProperty parseSteps(Step currentStep, List<Step> allSteps, int nextStepIndex,
			TypedObjectNode stepValue, TypedObjectNode parentStep) {
		if (stepValue instanceof PrimitiveValue && isCurrentStep(currentStep, parentStep)) {
			AttributePropertyType attributePropertyType = detectAttributePropertyType((PrimitiveValue) stepValue,
					parentStep);
			String primitiveValue = ((PrimitiveValue) stepValue).getAsText();
			return new AttributeProperty(currentStep.name, attributePropertyType, primitiveValue);
		}
		else if (stepValue instanceof GenericProperty) {
			TypedObjectNode propertyStepValue = ((GenericProperty) stepValue).getValue();
			return parseSteps(currentStep, allSteps, nextStepIndex, propertyStepValue, stepValue);
		}
		else if (stepValue instanceof SimpleProperty) {
			TypedObjectNode propertyStepValue = ((SimpleProperty) stepValue).getValue();
			return parseSteps(currentStep, allSteps, nextStepIndex, propertyStepValue, stepValue);
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
					return parseSteps(nextStep, allSteps, nextStepIndex + 1, childrenWithStepName.get(0),
							stepValueGenericXml);
				}
				else if (!children.isEmpty()) {
					return parseSteps(nextStep, allSteps, nextStepIndex, stepValueGenericXml.getChildren().get(0),
							stepValueGenericXml);
				}
			}
			else if (stepValueGenericXml.getChildren().size() == 1) {
				List<TypedObjectNode> children = stepValueGenericXml.getChildren();
				return parseSteps(currentStep, allSteps, nextStepIndex, children.get(0), stepValueGenericXml);
			}
		}
		return null;
	}

	private static boolean isCurrentStep(Step currentStep, TypedObjectNode parentStep) {
		if (parentStep instanceof GenericXMLElement) {
			return currentStep.name.equals(((GenericXMLElement) parentStep).getName().getLocalPart());
		}
		if (parentStep instanceof GenericProperty) {
			return currentStep.name.equals(((GenericProperty) parentStep).getName().getLocalPart());
		}
		if (parentStep instanceof SimpleProperty) {
			return currentStep.name.equals(((SimpleProperty) parentStep).getName().getLocalPart());
		}
		return true;
	}

	private static AttributePropertyType detectAttributePropertyType(PrimitiveValue stepValue,
			TypedObjectNode parentStep) {
		PrimitiveType type = stepValue.getType();
		if (!BaseType.STRING.equals(type.getBaseType()))
			return OTHER;
		if (parentStep instanceof GenericXMLElement) {
			XSElementDeclaration xsType = ((GenericXMLElement) parentStep).getXSType();
			boolean isCodeList = xsType.getTypeDefinition().derivedFrom(GML3_2_NS, "CodeType", DERIVATION_NONE);
			if (isCodeList)
				return CODE_OR_ENUM;
		}
		String namespace = type.getXSType().getNamespace();
		if (namespace.startsWith(XPLAN_GML_NS_PREFIX)) {
			return CODE_OR_ENUM;
		}
		return STRING;
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
