/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.inspector.flaechenschluss;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.datetime.Date;
import org.deegree.commons.tom.datetime.ISO8601Converter;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.geometry.multi.MultiSurface;
import org.deegree.geometry.primitive.Surface;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FlaechenschlussFeatureInspector {

	private final Date now = new Date(new java.util.Date(), null);

	/**
	 * Alle Objekte mit flaechenhaftem Raumbezug, die zur Ebene 0 (ebene == 0) gehoeren
	 * und bei denen das Attribut flaechenschluss den Wert true hat
	 * (Flaechenschlussobjekte).
	 * <p>
	 * Von der Erfuellung der Flaechenschlussbedingung ausgenommen sind die raumbezogenen
	 * Objekte des BPlan-Schemas (s. Kap. 0), deren Wirksamkeit durch sachliche oder
	 * zeitliche Bedingungen (Attribute startBedingung und endeBedingung in BP_Objekt) so
	 * eingeschraenkt sind, dass sie nicht gleichzeitig rechtswirksam sind.
	 */
	boolean isFlaechenschlussobjekt(Feature feature) {
		String namespaceURI = feature.getType().getName().getNamespaceURI();
		return isFlaechenschluss(feature, namespaceURI) && isEbene0(feature, namespaceURI)
				&& isFlaechenhaft(feature, namespaceURI) && isWirksam(feature, namespaceURI);
	}

	/* Alle Objekte mit flaechenhaftem Raumbezug, */
	private boolean isFlaechenhaft(Feature feature, String namespaceURI) {
		QName positionPropName = new QName(namespaceURI, "position");
		Property property = getProperty(positionPropName, feature);
		if (property != null) {
			TypedObjectNode value = property.getValue();
			return value != null && (value instanceof MultiSurface || value instanceof Surface);
		}
		return false;
	}

	/* die zur Ebene 0 (ebene == 0) gehoeren */
	private boolean isEbene0(Feature feature, String namespaceURI) {
		QName ebenePropName = new QName(namespaceURI, "ebene");
		Property property = getProperty(ebenePropName, feature);
		if (property != null) {
			PrimitiveValue value = (PrimitiveValue) property.getValue();
			return value == null || Integer.valueOf(value.getAsText()) == 0;
		}
		return true;
	}

	/* und bei denen das Attribut flaechenschluss den Wert true hat */
	private boolean isFlaechenschluss(Feature feature, String namespaceURI) {
		QName flaechenschlussPropName = new QName(namespaceURI, "flaechenschluss");
		Property property = getProperty(flaechenschlussPropName, feature);
		if (property != null) {
			PrimitiveValue value = (PrimitiveValue) property.getValue();
			return value != null && Boolean.valueOf(value.getAsText());
		}
		return false;
	}

	/*
	 * ... raumbezogenen Objekte des BPlan-Schemas [...], deren Wirksamkeit durch [...]
	 * zeitliche Bedingungen (Attribute startBedingung und endeBedingung in BP_Objekt) so
	 * eingeschraenkt sind, dass sie nicht gleichzeitig rechtswirksam sind.
	 */
	private boolean isWirksam(Feature feature, String namespaceURI) {
		if (!feature.getName().getLocalPart().startsWith("BP_"))
			return true;
		QName startBedingungPropName = new QName(namespaceURI, "startBedingung");
		Date startBedingung = getWirsamkeitDatum(feature, startBedingungPropName);
		boolean isAfterStart = true;
		if (startBedingung != null) {
			int i = now.compareTo(startBedingung);
			isAfterStart = i >= 0;
		}
		QName endeBedingungPropName = new QName(namespaceURI, "endeBedingung");
		Date endeBedingung = getWirsamkeitDatum(feature, endeBedingungPropName);
		boolean isBeforeEnd = true;
		if (endeBedingung != null) {
			int i = now.compareTo(endeBedingung);
			isBeforeEnd = i <= 0;
		}
		return isAfterStart && isBeforeEnd;
	}

	private Date getWirsamkeitDatum(Feature feature, QName propName) {
		Property property = getProperty(propName, feature);
		if (property != null && !property.getChildren().isEmpty()) {
			String namespaceURI = feature.getName().getNamespaceURI();
			QName xpWirksamkeitBedingungName = new QName(namespaceURI, "XP_WirksamkeitBedingung");
			GenericXMLElement xpWirksamkeitBedingung = getChildWithName(property.getChildren(),
					xpWirksamkeitBedingungName);
			if (xpWirksamkeitBedingung != null) {
				QName datumAbsolutName = new QName(namespaceURI, "datumAbsolut");
				GenericXMLElement datumAbsolut = getChildWithName(xpWirksamkeitBedingung.getChildren(),
						datumAbsolutName);
				if (datumAbsolut != null) {
					String dateAsText = datumAbsolut.getValue().getAsText();
					return ISO8601Converter.parseDate(dateAsText);
				}
			}
		}
		return null;
	}

	private GenericXMLElement getChildWithName(List<TypedObjectNode> childs, QName name) {
		if (childs.isEmpty())
			return null;
		for (TypedObjectNode child : childs) {
			GenericXMLElement genericXMLElement = (GenericXMLElement) child;
			if (genericXMLElement instanceof GenericXMLElement)
				if (name.equals(genericXMLElement.getName())) {
					return genericXMLElement;
				}
		}
		return null;
	}

	private Property getProperty(QName propName, Feature feature) {
		List<Property> positionProperties = feature.getProperties(propName);
		if (!positionProperties.isEmpty())
			return positionProperties.get(0);
		return null;
	}

}
