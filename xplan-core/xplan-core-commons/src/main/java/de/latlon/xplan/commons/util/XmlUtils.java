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
