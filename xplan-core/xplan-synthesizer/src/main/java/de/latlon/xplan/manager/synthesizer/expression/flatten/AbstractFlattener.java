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
package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.synthesizer.Features;
import de.latlon.xplan.manager.codelists.XPlanCodeListsFactory;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.utils.Pair;
import org.deegree.feature.Feature;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
abstract class AbstractFlattener implements Flattener {

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
	public boolean acceptsElementNode(TypedObjectNode node, String acceptedElementNodeName) {
		if (node instanceof ElementNode) {
			String elName = ((ElementNode) node).getName().getLocalPart();
			return acceptedElementNodeName.equals(elName);
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

	TypedObjectNode getPropertyValue(TypedObjectNode xpVerfahrensmerkmal, String propName) {
		if (xpVerfahrensmerkmal instanceof Feature) {
			Feature f = (Feature) xpVerfahrensmerkmal;
			QName qName = new QName(f.getName().getNamespaceURI(), propName);
			return Features.getPropertyValue(f, qName);
		}
		else if (xpVerfahrensmerkmal instanceof ElementNode) {
			return getPropertyValue((ElementNode) xpVerfahrensmerkmal, propName);
		}
		throw new IllegalArgumentException();
	}

	TypedObjectNode getPropertyValue(ElementNode xpVerfahrensmerkmal, String propName) {
		for (TypedObjectNode child : xpVerfahrensmerkmal.getChildren()) {
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

	String escape(String desc) {
		String result = desc;
		if (result.startsWith("[") && result.endsWith("]")) {
			result = result.substring(1, result.length() - 1);
		}
		result = result.replace("][", "][][");
		return result;
	}

	protected String encode(List<Pair<String, String>> properties) {
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

	protected void append(String label, TypedObjectNode feature, String propertyName,
			List<Pair<String, String>> properties) {
		String propertyValue = asString(feature, propertyName);
		if (propertyValue != null) {
			properties.add(new Pair(label, propertyValue));
		}
	}

	protected void appendTranslatedCode(String label, TypedObjectNode feature, String propertyName,
			XPlanVersion version, String codeListName, List<Pair<String, String>> properties) {
		String propertyValue = asString(feature, propertyName);
		if (propertyValue != null) {
			String translatedValue = XPlanCodeListsFactory.get(version).getDescription(codeListName, propertyValue);
			properties.add(new Pair(label, translatedValue));
		}
	}

	protected String asString(TypedObjectNode property, String ags) {
		if (property instanceof Property) {
			property = ((Property) property).getValue();
		}
		if (property instanceof PrimitiveValue) {
			return ((PrimitiveValue) property).getAsText();
		}
		PrimitiveValue propertyValue = (PrimitiveValue) getPropertyValue(property, ags);
		if (propertyValue != null)
			return propertyValue.getAsText();
		return null;
	}

}
