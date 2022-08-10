package de.latlon.xplan.manager.synthesizer.expression.praesentation;

import de.latlon.xplan.manager.synthesizer.expression.Expression;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import org.apache.xerces.xs.XSConstants;
import org.apache.xerces.xs.XSElementDeclaration;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.BaseType;
import org.deegree.commons.tom.primitive.PrimitiveType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.synthesizer.expression.praesentation.AttributePropertyType.CODE_OR_ENUM;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.AttributePropertyType.OTHER;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.AttributePropertyType.STRING;
import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.castToArray;

/**
 * Abstract Lookup class for Praesentationsobjekte.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class PraesentationsobjektLookup implements Expression {

	private static final Logger LOG = LoggerFactory.getLogger(PraesentationsobjektLookup.class);

	protected final Xpath artXPath;

	protected final Xpath dientZurDarstellungVonXPath;

	public PraesentationsobjektLookup() {
		this.artXPath = new Xpath("xplan:art");
		this.dientZurDarstellungVonXPath = new Xpath("xplan:dientZurDarstellungVon");
	}

	@Override
	public TypedObjectNode evaluate(Feature feature, FeatureCollection features) {
		Feature referencedFeature = resolveDientZurDarstellungVon(feature, features);
		if (referencedFeature != null) {
			List<AttributeProperty> attributeProperty = parseArtProperties(feature, features, referencedFeature);
			return evaluate(feature, features, referencedFeature, attributeProperty);
		}
		return null;
	}

	/**
	 * Evaluates on the given {@link Feature}.
	 * @param feature feature to operate on, must not be <code>null</code>
	 * @param features the feature collection the feature is part of, must not be
	 * <code>null</code>
	 * @param referencedFeature the feature referenced by "dientZurDarstellungVon", may be
	 * <code>null</code> if not available
	 * @param attributeProperty the parsed art attribute, may be <code>null</code> if not
	 * available
	 * @return expression value, suitable as property value, can be <code>null</code> (no
	 * value, omit property)
	 */
	protected abstract TypedObjectNode evaluate(Feature feature, FeatureCollection features, Feature referencedFeature,
			List<AttributeProperty> attributeProperty);

	private Feature resolveDientZurDarstellungVon(Feature feature, FeatureCollection features) {
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

	private List<AttributeProperty> parseArtProperties(Feature feature, FeatureCollection features,
			Feature referencedFeature) {
		TypedObjectNodeArray<TypedObjectNode> propertiesArray = castToArray(artXPath.evaluate(feature, features));
		if (propertiesArray != null) {
			return convertToAttributeOProperties(referencedFeature, propertiesArray);
		}
		return null;
	}

	private List<AttributeProperty> convertToAttributeOProperties(Feature referencedFeature,
			TypedObjectNodeArray<TypedObjectNode> propertiesArray) {
		List<AttributeProperty> attributeProperties = new ArrayList<>();
		TypedObjectNode[] artProperties = propertiesArray.getElements();
		for (TypedObjectNode artProperty : artProperties) {
			List<Step> artPropertySteps = parseArtAsXPath(artProperty);
			AttributeProperty attributeProperty = parseArtProperty(referencedFeature, artPropertySteps);
			if (attributeProperty != null)
				attributeProperties.add(attributeProperty);
		}
		return attributeProperties;
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

	private static AttributeProperty parseArtProperty(Feature referencedFeature, List<Step> artPropertySteps) {
		if (!artPropertySteps.isEmpty()) {
			int firstStepIndex = 0;
			Step firstStep = artPropertySteps.get(firstStepIndex);
			List<Property> properties = referencedFeature
					.getProperties(new QName(referencedFeature.getName().getNamespaceURI(), firstStep.name));
			Property propertyStep = properties.get(firstStep.index);
			AttributeProperty attributeProperty = parseArtProperty(firstStep, artPropertySteps, 1, propertyStep, null);
			if (attributeProperty != null)
				return attributeProperty;
		}
		return null;
	}

	private static AttributeProperty parseArtProperty(Step currentStep, List<Step> allSteps, int nextStepIndex,
			TypedObjectNode stepValue, TypedObjectNode parentStep) {
		if (stepValue instanceof PrimitiveValue) {
			AttributePropertyType attributePropertyType = detectAttributePropertyType((PrimitiveValue) stepValue,
					parentStep);
			String primitiveValue = ((PrimitiveValue) stepValue).getAsText();
			return new AttributeProperty(currentStep.name, attributePropertyType, primitiveValue);
		}
		else if (stepValue instanceof GenericProperty) {
			TypedObjectNode propertyStepValue = ((GenericProperty) stepValue).getValue();
			return parseArtProperty(currentStep, allSteps, nextStepIndex, propertyStepValue, stepValue);
		}
		else if (stepValue instanceof SimpleProperty) {
			TypedObjectNode propertyStepValue = ((SimpleProperty) stepValue).getValue();
			return parseArtProperty(currentStep, allSteps, nextStepIndex, propertyStepValue, stepValue);
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
					return parseArtProperty(nextStep, allSteps, nextStepIndex + 1, childrenWithStepName.get(0),
							stepValueGenericXml);
				}
				else if (!children.isEmpty()) {
					return parseArtProperty(nextStep, allSteps, nextStepIndex, stepValueGenericXml.getChildren().get(0),
							stepValueGenericXml);
				}
			}
			else if (stepValueGenericXml.getChildren().size() == 1) {
				List<TypedObjectNode> children = stepValueGenericXml.getChildren();
				return parseArtProperty(currentStep, allSteps, nextStepIndex, children.get(0), stepValueGenericXml);
			}
		}
		return new AttributeProperty(currentStep.name, STRING);
	}

	private static AttributePropertyType detectAttributePropertyType(PrimitiveValue stepValue,
			TypedObjectNode parentStep) {
		PrimitiveType type = stepValue.getType();
		if (!BaseType.STRING.equals(type.getBaseType()))
			return OTHER;
		if (parentStep instanceof GenericXMLElement) {
			XSElementDeclaration xsType = ((GenericXMLElement) parentStep).getXSType();
			boolean isCodeList = xsType.getTypeDefinition().derivedFrom("http://www.opengis.net/gml/3.2", "CodeType",
					XSConstants.DERIVATION_NONE);
			if (isCodeList)
				return CODE_OR_ENUM;
		}
		String namespace = type.getXSType().getNamespace();
		if (namespace.startsWith("http://www.xplanung.de/xplangml")) {
			return CODE_OR_ENUM;
		}
		return STRING;
	}

	protected class Step {

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
