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
package de.latlon.xplan.manager.synthesizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

/**
 * Utility class for tracking down XP_TextAbschnitt / XP_BegruendungAbschnitt features
 * that have a certain key (schluessel). It occurs in the evaluation of
 * XPlanTextSchluessel objects.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @version $Revision: 1028 $, $Date: 2010-02-18 15:48:22 +0100 (Do, 18 Feb 2010) $
 */
public class XplanAbschnittLookup {

	private static final Map<String, Feature> textSchluesselToAbschnitt = new HashMap<String, Feature>();

	private static final Map<String, Feature> begruendungSchluesselToAbschnitt = new HashMap<String, Feature>();

	/**
	 * @param fc
	 */
	public static void init(FeatureCollection fc) {
		textSchluesselToAbschnitt.clear();
		begruendungSchluesselToAbschnitt.clear();
		for (Feature f : fc) {
			String ftName = f.getName().getLocalPart();
			if ("XP_TextAbschnitt".equals(ftName)) {
				String schluessel = getSchluesselPropertyValue(f);
				if (schluessel != null) {
					textSchluesselToAbschnitt.put(schluessel, f);
				}
			}
			else if ("XP_BegruendungAbschnitt".equals(f.getName().getLocalPart())) {
				String schluessel = getSchluesselPropertyValue(f);
				if (schluessel != null) {
					begruendungSchluesselToAbschnitt.put(schluessel, f);
				}
			}
		}
	}

	private static String getSchluesselPropertyValue(Feature f) {
		QName propName = new QName(f.getName().getNamespaceURI(), "schluessel");
		List<Property> props = f.getProperties(propName);
		if (props.isEmpty()) {
			return null;
		}
		return props.get(0).getValue().toString();
	}

	public static Feature lookupXpTextAbschnitt(String key) {
		return textSchluesselToAbschnitt.get(key);
	}

	public static Feature lookupXpBegruendungAbschnitt(String key) {
		return begruendungSchluesselToAbschnitt.get(key);
	}

}
