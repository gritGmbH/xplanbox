package de.latlon.xplan.commons.util;

import javax.xml.stream.XMLInputFactory;

import static javax.xml.XMLConstants.ACCESS_EXTERNAL_DTD;
import static javax.xml.stream.XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES;
import static javax.xml.stream.XMLInputFactory.SUPPORT_DTD;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public final class XmlUtils {

	private XmlUtils() {
	}

	public static XMLInputFactory createXMLInputFactory() {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		// This disables DTDs entirely for that factory
		xmlInputFactory.setProperty(SUPPORT_DTD, false);
		// This causes XMLStreamException to be thrown if external DTDs are accessed.
		// ACCESS_EXTERNAL_DTD is not supported by woodstox, see
		// https://github.com/FasterXML/woodstox/issues/50
		if (xmlInputFactory.isPropertySupported(ACCESS_EXTERNAL_DTD)) {
			xmlInputFactory.setProperty(ACCESS_EXTERNAL_DTD, "");
		}
		// disable external entities
		xmlInputFactory.setProperty(IS_SUPPORTING_EXTERNAL_ENTITIES, false);
		return xmlInputFactory;
	}

}
