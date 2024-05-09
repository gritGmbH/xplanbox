/*-
 * #%L
 * xplan-core-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.flatten.xp;

import de.latlon.xplan.commons.synthesizer.Features;
import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class AbstractTextlicherAbschnittFlattener extends AbstractFlattener {

	protected String flatten(Feature f) {
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
		textAbschnittText.append(" | ");
		int lengthWithSchluessel = textAbschnittText.length();
		if (text != null && !text.isEmpty()) {
			text = replaceDelimiter(text);
			textAbschnittText.append(text);
		}

		if (gesetzlicheGrundlage != null && !gesetzlicheGrundlage.isEmpty()) {
			gesetzlicheGrundlage = replaceDelimiter(gesetzlicheGrundlage);
			textAbschnittText.append(" (Gesetzliche Grundlage: ").append(gesetzlicheGrundlage).append(")");
		}
		if (externeReferenzUrl != null && !externeReferenzUrl.isEmpty()) {
			if (textAbschnittText.length() > lengthWithSchluessel)
				textAbschnittText.append(" | ");
			textAbschnittText.append("Externe Referenz: ").append(externeReferenzUrl);
		}
		textAbschnittText.append("]");
		return textAbschnittText.toString();
	}

	private String parseExterneReferenzUrl(Feature feature) {
		String namespaceURI = feature.getName().getNamespaceURI();
		List<Property> refTexts = feature.getProperties(new QName(namespaceURI, "refText"));
		if (refTexts != null && refTexts.size() == 1) {
			Property refText = refTexts.get(0);
			GenericXMLElement xp_externeReferenz = getChildByName(refText.getChildren(), namespaceURI,
					"XP_ExterneReferenz");
			if (xp_externeReferenz != null && xp_externeReferenz.getChildren() != null) {
				GenericXMLElement referenzURL = getChildByName(xp_externeReferenz.getChildren(), namespaceURI,
						"referenzURL");
				if (referenzURL != null && referenzURL.getValue() != null) {
					TypedObjectNode propertyValue = referenzURL.getValue();
					return propertyValue.toString();
				}
			}
		}
		return null;
	}

	private GenericXMLElement getChildByName(List<TypedObjectNode> children, String namespaceURI, String propertyName) {
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

	public String replaceDelimiter(String text) {
		text = text.replace("|", "_");
		return text;
	}

	public String getPropertyValue(Feature f, String namespaceUrl, String localName) {
		return getPropertyValue(f, namespaceUrl, localName, null);
	}

	public String getPropertyValue(Feature f, String namespaceUrl, String localName, String defaultValue) {
		QName propName = new QName(namespaceUrl, localName);
		TypedObjectNode propertyValue = Features.getPropertyValue(f, propName);
		if (propertyValue != null)
			return propertyValue.toString();
		return defaultValue;
	}

}
