/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.synthesizer;

import org.deegree.commons.tom.Reference;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class Features {

	public static TypedObjectNode getPropertyValue(Feature f, QName propName) {
		List<Property> props = f.getProperties(propName);
		if (props.isEmpty()) {
			return null;
		}
		if (props.get(0).getValue() instanceof GenericXMLElement) {
			GenericXMLElement xmlEl = (GenericXMLElement) props.get(0).getValue();
			if (xmlEl.getChildren().size() == 1) {
				return xmlEl.getChildren().get(0);
			}
			return null;
		}
		else {
			return props.get(0).getValue();
		}
	}

	public static List<TypedObjectNode> getPropertyValues(Feature f, QName propName) {
		List<Property> props = f.getProperties(propName);
		List<TypedObjectNode> values = new ArrayList<TypedObjectNode>();
		for (Property prop : props) {
			values.add(prop.getValue());
		}
		return values;
	}

	public static Feature getPropertyFeatureValue(Feature f, QName propName) {
		List<Property> props = f.getProperties(propName);
		if (props.isEmpty()) {
			return null;
		}
		TypedObjectNode value = props.get(0).getValue();
		if (value instanceof Feature) {
			return (Feature) value;
		}
		if (value instanceof Reference<?>) {
			Object refObject = ((Reference<?>) value).getReferencedObject();
			if (refObject instanceof Feature) {
				return (Feature) refObject;
			}
		}
		throw new RuntimeException("Value of property '" + propName + "' is not a Feature.");
	}

	public static String getPropertyStringValue(Feature f, QName propName) {
		List<Property> props = f.getProperties(propName);
		if (props.isEmpty()) {
			return null;
		}
		Property prop = props.get(0);
		TypedObjectNode value = prop.getValue();
		if (value == null) {
			return null;
		}
		return "" + value;
	}

	public static Feature getPropertyFeatureValue(Property prop) {
		TypedObjectNode value = prop.getValue();
		if (value instanceof Feature) {
			return (Feature) value;
		}
		if (value instanceof Reference<?>) {
			Object refObject = ((Reference<?>) value).getReferencedObject();
			if (refObject instanceof Feature) {
				return (Feature) refObject;
			}
		}
		throw new RuntimeException("Value of property '" + prop.getName() + "' is not a Feature.");
	}

}
