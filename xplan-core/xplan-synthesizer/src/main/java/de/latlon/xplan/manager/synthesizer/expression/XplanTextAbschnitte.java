/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.commons.synthesizer.Features;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.reference.FeatureReference;

import javax.xml.namespace.QName;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static de.latlon.xplan.manager.synthesizer.XplanAbschnittLookup.lookupXpTextAbschnitt;

/**
 * Returns a textual representation of the "XP_TextAbschnitt" features referenced by an
 * "XP_Objekt" feature.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class XplanTextAbschnitte implements Expression {

	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features) {
		Set<Feature> abschnittFeatures = new LinkedHashSet<>();
		abschnittFeatures.addAll(getTextAbschnitteReferencedBySchluessel(feature));
		abschnittFeatures.addAll(getTextAbschnitteReferencedByRef(feature));
		if (abschnittFeatures.isEmpty()) {
			return null;
		}
		StringBuilder sBuilder = new StringBuilder();
		for (Feature abschnitt : abschnittFeatures) {
			sBuilder.append(escape(toString(abschnitt)));
		}
		return new PrimitiveValue(sBuilder.toString());
	}

	public static String toString(Feature f) {
		String namespaceURI = f.getName().getNamespaceURI();
		String text = getPropertyValue(f, namespaceURI, "text", "");
		String gesetzlicheGrundlage = getPropertyValue(f, namespaceURI, "gesetzlicheGrundlage");
		String schluessel = getPropertyValue(f, namespaceURI, "schluessel");
		String externeReferenzUrl = parseExterneReferenzUrl(f);

		StringBuffer textAbschnittText = new StringBuffer();
		textAbschnittText.append("[");
		if (schluessel != null && !schluessel.isEmpty()) {
			schluessel = replaceDelimiter(schluessel);
			textAbschnittText.append(schluessel);
		}
		if (text != null && !text.isEmpty()) {
			if (textAbschnittText.length() > 1)
				textAbschnittText.append(" | ");
			text = replaceDelimiter(text);
			textAbschnittText.append(text);
		}

		if (gesetzlicheGrundlage != null && !gesetzlicheGrundlage.isEmpty()) {
			gesetzlicheGrundlage = replaceDelimiter(gesetzlicheGrundlage);
			textAbschnittText.append(" (Gesetzliche Grundlage: ").append(gesetzlicheGrundlage).append(")");
		}
		if (externeReferenzUrl != null && !externeReferenzUrl.isEmpty()) {
			if (textAbschnittText.length() > 1)
				textAbschnittText.append(" | ");
			textAbschnittText.append("Externe Referenz: ").append(externeReferenzUrl);
		}
		textAbschnittText.append("]");
		return textAbschnittText.toString();
	}

	private static String parseExterneReferenzUrl(Feature feature) {
		String namespaceURI = feature.getName().getNamespaceURI();
		List<Property> refTexts = feature.getProperties(new QName(namespaceURI, "refText"));
		if (refTexts != null && refTexts.size() == 1) {
			Property refText = refTexts.get(0);
			GenericXMLElement xp_externeReferenz = getChildByName(refText.getChildren(), namespaceURI,
					"XP_ExterneReferenz");
			if (xp_externeReferenz != null && xp_externeReferenz.getChildren() != null) {
				GenericXMLElement referenzURL = getChildByName(xp_externeReferenz.getChildren(), namespaceURI,
						"referenzURL");
				TypedObjectNode propertyValue = referenzURL.getValue();
				if (propertyValue != null)
					return propertyValue.toString();
			}
		}
		return null;
	}

	private static GenericXMLElement getChildByName(List<TypedObjectNode> children, String namespaceURI,
			String propertyName) {
		for (TypedObjectNode child : children) {
			if (child != null && child instanceof GenericXMLElement) {
				GenericXMLElement property = (GenericXMLElement) child;
				if (new QName(namespaceURI, propertyName).equals(property.getName())) {
					return property;
				}
			}
		}
		return null;
	}

	private static String replaceDelimiter(String text) {
		text = text.replace("|", "_");
		return text;
	}

	private static String getPropertyValue(Feature f, String namespaceUrl, String localName) {
		return getPropertyValue(f, namespaceUrl, localName, null);
	}

	private static String getPropertyValue(Feature f, String namespaceUrl, String localName, String defaultValue) {
		QName propName = new QName(namespaceUrl, localName);
		TypedObjectNode propertyValue = Features.getPropertyValue(f, propName);
		if (propertyValue != null)
			return propertyValue.toString();
		return defaultValue;
	}

	private Set<Feature> getTextAbschnitteReferencedBySchluessel(Feature feature) {
		Set<Feature> abschnittFeatures = new LinkedHashSet<>();
		QName propName = new QName(feature.getName().getNamespaceURI(), "textSchluessel");
		List<Property> props = feature.getProperties(propName);
		for (Property prop : props) {
			TypedObjectNode value = prop.getValue();
			if (value != null) {
				String[] schluessels = value.toString().split("[,;\\s]+");
				for (String schluessel : schluessels) {
					Feature begruendungAbschnitt = lookupXpTextAbschnitt(schluessel);
					if (begruendungAbschnitt != null) {
						abschnittFeatures.add(begruendungAbschnitt);
					}
				}
			}
		}
		return abschnittFeatures;
	}

	private Set<Feature> getTextAbschnitteReferencedByRef(Feature feature) {
		Set<Feature> abschnittFeatures = new LinkedHashSet<Feature>();
		QName propName = new QName(feature.getName().getNamespaceURI(), "refTextInhalt");
		List<Property> props = feature.getProperties(propName);
		for (Property prop : props) {
			FeatureReference ref = (FeatureReference) prop.getValue();
			if (ref != null) {
				Feature begruendungAbschnitt = ref.getReferencedObject();
				abschnittFeatures.add(begruendungAbschnitt);
			}
		}
		return abschnittFeatures;
	}

	private String escape(String desc) {
		String result = desc;
		result = result.replace("][", "][][");
		return result;
	}

}
