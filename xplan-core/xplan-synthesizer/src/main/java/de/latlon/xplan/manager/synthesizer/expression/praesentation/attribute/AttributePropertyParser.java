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
package de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute;

import org.apache.xerces.xs.XSElementDeclaration;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.CODE;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.ENUM;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.PRIMITIVE;
import static org.apache.xerces.xs.XSConstants.DERIVATION_NONE;
import static org.deegree.commons.xml.CommonNamespaces.GML3_2_NS;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AttributePropertyParser {

	private static final Logger LOG = LoggerFactory.getLogger(AttributePropertyParser.class);

	public static final String XPLAN_GML_NS_PREFIX = "http://www.xplanung.de/xplangml";

	public List<AttributeProperty> parseAttributeProperties(Feature referencedFeature,
			TypedObjectNodeArray<TypedObjectNode> artProperties,
			TypedObjectNodeArray<TypedObjectNode> indexProperties) {
		TypedObjectNode[] artPropertyElements = artProperties.getElements();
		List<AttributeProperty> attributeProperties = new ArrayList<>();
		for (int artIndex = 0; artIndex < artPropertyElements.length; artIndex++) {
			TypedObjectNode artProperty = artPropertyElements[artIndex];
			TypedObjectNode indexProperty = findCorrespondingIndex(artIndex, indexProperties);
			List<Step> steps = parseXPath(artProperty, indexProperty, referencedFeature);
			AttributeProperty attributeProperty = parseSteps(referencedFeature, steps);
			if (attributeProperty != null) {
				attributeProperties.add(attributeProperty);
			}
		}
		return attributeProperties;
	}

	private List<Step> parseXPath(TypedObjectNode art, TypedObjectNode index, Feature referencedFeature) {
		if (art instanceof SimpleProperty && ((SimpleProperty) art).getValue() instanceof PrimitiveValue) {
			String artProperty = ((SimpleProperty) art).getValue().getAsText();
			String[] split = artProperty.split("/");
			return Arrays.stream(split).map(step -> {
				if (step.endsWith("]")) {
					int indexOfPredicateBegin = step.indexOf("[");
					if (indexOfPredicateBegin > 1) {
						String position = step.substring(indexOfPredicateBegin + 1, step.length() - 1);
						String nameWithPrefix = step.substring(0, indexOfPredicateBegin);
						String name = parseStepNameAndCheckPrefix(referencedFeature, nameWithPrefix);
						try {
							return new Step(name, Integer.parseInt(position));
						}
						catch (NumberFormatException e) {
							LOG.warn("Could not parse " + position + " as integer.");
							String nameWithoutPrefix = parseStepNameAndCheckPrefix(referencedFeature, step);
							return new Step(nameWithoutPrefix, 0);
						}
					}
				}
				else if (index != null && index instanceof SimpleProperty
						&& ((SimpleProperty) index).getValue() instanceof PrimitiveValue) {
					String indexProperty = ((SimpleProperty) index).getValue().getAsText();
					String name = parseStepNameAndCheckPrefix(referencedFeature, step);
					try {
						int indexAsInt = Integer.parseInt(indexProperty);
						if (indexAsInt < 0) {
							LOG.warn("Negative index {}, art is ignored.", indexAsInt);
							return null;
						}
						return new Step(name, indexAsInt);
					}
					catch (NumberFormatException e) {
						LOG.warn("Could not parse attribute art " + indexProperty + " as integer.");
						String nameWithoutPrefix = parseStepNameAndCheckPrefix(referencedFeature, step);
						return new Step(nameWithoutPrefix, 0);
					}
				}
				String name = parseStepNameAndCheckPrefix(referencedFeature, step);
				return new Step(name);
			}).filter(prop -> prop != null).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	private TypedObjectNode findCorrespondingIndex(int artIndex,
			TypedObjectNodeArray<TypedObjectNode> indexProperties) {
		if (indexProperties != null && indexProperties.getElements().length > artIndex)
			return indexProperties.getElements()[artIndex];
		return null;
	}

	private String parseStepNameAndCheckPrefix(Feature referencedFeature, String nameWithPrefix) {
		if (nameWithPrefix.contains(":")) {
			int indexOfPrefixBegin = nameWithPrefix.indexOf(":");
			String prefix = nameWithPrefix.substring(0, indexOfPrefixBegin);
			checkPrefix(prefix, referencedFeature);
			return nameWithPrefix.substring(indexOfPrefixBegin + 1);
		}
		return nameWithPrefix;
	}

	private void checkPrefix(String prefix, Feature referencedFeature) {
		String referencedFeaturePrefix = referencedFeature.getName().getPrefix();
		if (referencedFeaturePrefix != null)
			if (!referencedFeaturePrefix.equals(prefix))
				LOG.warn(
						"Prefix in xplan:art {} does not match the prefix of the referenced feature {]. Prefix will be ignored/default prefix assumed.",
						prefix, referencedFeaturePrefix);
			else if (prefix != null)
				LOG.warn(
						"Prefix in xplan:art {} but prefix of the referenced feature is null. Prefix will be ignored/default prefix assumed.",
						prefix);
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
			else {
				LOG.warn("Referenced feature with id {} contains no property with name {} on index {}.",
						referencedFeature.getId(), firstStep.name, firstStep.index);
			}
		}
		return null;
	}

	private static AttributeProperty parseSteps(List<Property> properties, int index, Step firstStep,
			List<Step> steps) {
		Property propertyStep = properties.get(index);
		if (propertyStep instanceof GenericProperty) {
			TypedObjectNode propertyStepValue = ((GenericProperty) propertyStep).getValue();
			return parseSteps(firstStep, steps, index + 1, propertyStepValue, propertyStep);
		}
		else if (propertyStep instanceof SimpleProperty) {
			TypedObjectNode propertyStepValue = ((SimpleProperty) propertyStep).getValue();
			return parseSteps(firstStep, steps, index + 1, propertyStepValue, propertyStep);
		}
		return null;
	}

	private static AttributeProperty parseSteps(Step currentStep, List<Step> allSteps, int nextStepIndex,
			TypedObjectNode stepValue, TypedObjectNode parentStep) {
		if (stepValue instanceof PrimitiveValue) {
			AttributePropertyType attributePropertyType = detectAttributePropertyType((PrimitiveValue) stepValue,
					parentStep);
			String codeListId = detectCodelistId((PrimitiveValue) stepValue, attributePropertyType);
			String primitiveValue = ((PrimitiveValue) stepValue).getAsText();
			return new AttributeProperty(currentStep.name, attributePropertyType, primitiveValue, codeListId);
		}
		else if (stepValue instanceof GenericXMLElement) {
			GenericXMLElement stepValueGenericXml = (GenericXMLElement) stepValue;
			List<TypedObjectNode> children = stepValueGenericXml.getChildren();
			if (allSteps.size() > nextStepIndex) {
				Step nextStep = allSteps.get(nextStepIndex);
				List<TypedObjectNode> childrenWithStepName = children.stream().filter(child -> {
					if (child instanceof GenericXMLElement)
						return ((GenericXMLElement) child).getName().getLocalPart().equals(nextStep.name);
					return false;
				}).collect(Collectors.toList());
				if (!childrenWithStepName.isEmpty()) {
					return parseSteps(nextStep, allSteps, nextStepIndex + 1, childrenWithStepName.get(0),
							stepValueGenericXml);
				}
			}
			else if (children.size() == 1 && children.get(0) instanceof PrimitiveValue) {
				return parseSteps(currentStep, allSteps, nextStepIndex, children.get(0), stepValueGenericXml);
			}
		}
		return null;
	}

	private static AttributePropertyType detectAttributePropertyType(PrimitiveValue stepValue,
			TypedObjectNode parentStep) {
		PrimitiveType type = stepValue.getType();
		if (parentStep instanceof GenericXMLElement) {
			XSElementDeclaration xsType = ((GenericXMLElement) parentStep).getXSType();
			boolean isCodeList = xsType.getTypeDefinition().derivedFrom(GML3_2_NS, "CodeType", DERIVATION_NONE);
			if (isCodeList)
				return CODE;
		}
		String namespace = type.getXSType().getNamespace();
		if (namespace.startsWith(XPLAN_GML_NS_PREFIX)) {
			return ENUM;
		}
		return PRIMITIVE;
	}

	private static String detectCodelistId(PrimitiveValue stepValue, AttributePropertyType attributePropertyType) {
		if (ENUM.equals(attributePropertyType)) {
			PrimitiveType type = stepValue.getType();
			return type.getXSType().getName();
		}
		return null;
	}

	private class Step {

		String name;

		int index;

		public Step(String name) {
			this(name, 0);
		}

		public Step(String name, int index) {
			this.name = name;
			this.index = index;
		}

	}

}
