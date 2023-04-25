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
package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.synthesizer.Features;
import de.latlon.xplan.manager.dictionary.XPlanCodelists;
import de.latlon.xplan.manager.dictionary.XPlanDictionaries;
import de.latlon.xplan.manager.dictionary.XPlanEnumerationFactory;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.utils.Pair;
import org.deegree.feature.Feature;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class AbstractFlattener implements Flattener {

	private final XPlanCodelists xPlanCodelists;

	public AbstractFlattener() {
		this(null);
	}

	public AbstractFlattener(XPlanCodelists xPlanCodelists) {
		this.xPlanCodelists = xPlanCodelists;
	}

	/**
	 * Checks if the node is an accepted ElementNode or Feature
	 * @param node never <code>null</code>
	 * @param acceptedFeatureTypeOrElementName never <code>null</code>
	 * @return
	 */
	public boolean accepts(TypedObjectNode node, String acceptedFeatureTypeOrElementName) {
		return acceptsElementNode(node, acceptedFeatureTypeOrElementName)
				|| acceptsFeature(node, acceptedFeatureTypeOrElementName);
	}

	/**
	 * Checks if the node is an accepted ElementNode
	 * @param node never <code>null</code>
	 * @param acceptedElementNodeName never <code>null</code>
	 * @return
	 */
	public boolean acceptsElementNode(TypedObjectNode node, String... acceptedElementNodeName) {
		return acceptsElementNode(node, Arrays.asList(acceptedElementNodeName));
	}

	/**
	 * Checks if the node is an accepted ElementNode
	 * @param node never <code>null</code>
	 * @param acceptedElementNodeNames never <code>null</code>
	 * @return
	 */
	public boolean acceptsElementNode(TypedObjectNode node, Collection<String> acceptedElementNodeNames) {
		if (node instanceof ElementNode) {
			String elName = ((ElementNode) node).getName().getLocalPart();
			return acceptedElementNodeNames.contains(elName);
		}
		return false;
	}

	/**
	 * Checks if the node is an accepted Feature
	 * @param node never <code>null</code>
	 * @param acceptedFeatureTypeName never <code>null</code>
	 * @return
	 */
	public boolean acceptsFeature(TypedObjectNode node, String acceptedFeatureTypeName) {
		if (node instanceof Feature) {
			String elName = ((Feature) node).getName().getLocalPart();
			return acceptedFeatureTypeName.equals(elName);
		}
		return false;
	}

	public TypedObjectNode getPropertyValue(TypedObjectNode node, String propName) {
		if (node instanceof Feature) {
			Feature f = (Feature) node;
			QName qName = new QName(f.getName().getNamespaceURI(), propName);
			return Features.getPropertyValue(f, qName);
		}
		else if (node instanceof ElementNode) {
			return getPropertyValue((ElementNode) node, propName);
		}
		throw new IllegalArgumentException();
	}

	public TypedObjectNode getPropertyValue(ElementNode node, String propName) {
		for (TypedObjectNode child : node.getChildren()) {
			if (child instanceof ElementNode) {
				ElementNode childEl = (ElementNode) child;
				if (!childEl.getName().getLocalPart().equals(propName)) {
					continue;
				}
				if (childEl.getChildren().isEmpty()) {
					return new PrimitiveValue("");
				}
				if (childEl.getChildren().size() == 1) {
					return childEl.getChildren().get(0);
				}
				throw new IllegalArgumentException();
			}
		}
		return null;
	}

	public String escape(String desc) {
		String result = desc;
		if (result.startsWith("[") && result.endsWith("]")) {
			result = result.substring(1, result.length() - 1);
		}
		result = result.replace("][", "][][");
		return result;
	}

	public String encode(List<Pair<String, String>> properties) {
		if (properties.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (Pair<String, String> property : properties) {
			if (properties.indexOf(property) > 0)
				sb.append("|");
			sb.append(property.first).append(": ").append(property.second);
		}
		sb.append("]");
		return sb.toString();
	}

	public void append(String label, TypedObjectNode feature, String propertyName,
			List<Pair<String, String>> properties) {
		String propertyValue = asString(feature, propertyName);
		if (propertyValue != null) {
			properties.add(new Pair<>(label, propertyValue));
		}
	}

	public void appendBoolean(String label, TypedObjectNode feature, String propertyName,
			List<Pair<String, String>> properties) {
		String propertyValue = asString(feature, propertyName);
		if (propertyValue != null) {
			String value = Boolean.parseBoolean(propertyValue) ? "ja" : "nein";
			properties.add(new Pair<>(label, value));
		}
	}

	public void appendEnum(String label, TypedObjectNode feature, String propertyName, XPlanVersion version,
			String enumName, boolean keepCodes, List<Pair<String, String>> properties) {
		if (keepCodes) {
			append(label, feature, propertyName, properties);
		}
		else {
			String propertyValue = asString(feature, propertyName);
			if (propertyValue != null) {
				String translatedValue = XPlanEnumerationFactory.get(version).getTranslation(enumName, propertyValue);
				properties.add(new Pair<>(label, translatedValue));
			}
		}
	}

	public void appendCode(String label, TypedObjectNode feature, String propertyName, XPlanVersion version,
			String codeListName, boolean keepCodes, List<Pair<String, String>> properties) {
		XPlanDictionaries codelists = getxPlanDictionaries(version);
		if (keepCodes || codelists == null) {
			append(label, feature, propertyName, properties);
		}
		else {
			String propertyValue = asString(feature, propertyName);
			if (propertyValue != null) {
				if (codelists != null) {
					try {
						String translatedValue = codelists.getTranslation(codeListName, propertyValue);
						properties.add(new Pair<>(label, translatedValue));
					}
					catch (IllegalArgumentException e) {
						properties.add(new Pair<>(label, propertyValue));
					}
				}
			}
		}
	}

	private XPlanDictionaries getxPlanDictionaries(XPlanVersion version) {
		if (xPlanCodelists != null)
			return xPlanCodelists.getCodelists(version);
		return null;
	}

	public String asString(TypedObjectNode node, String ags) {
		if (node instanceof Property) {
			node = ((Property) node).getValue();
		}
		if (node instanceof PrimitiveValue) {
			return ((PrimitiveValue) node).getAsText();
		}
		PrimitiveValue propertyValue = (PrimitiveValue) getPropertyValue(node, ags);
		if (propertyValue != null)
			return propertyValue.getAsText();
		return null;
	}

}
