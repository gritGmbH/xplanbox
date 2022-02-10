package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.commons.synthesizer.Features;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;

import javax.xml.namespace.QName;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class XPlanTexlicherAbschnitt implements Expression {

	protected String toString(Feature f) {
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
