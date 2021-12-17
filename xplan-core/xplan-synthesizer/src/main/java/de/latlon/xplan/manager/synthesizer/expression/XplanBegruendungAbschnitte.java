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

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.reference.FeatureReference;

import javax.xml.namespace.QName;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static de.latlon.xplan.manager.synthesizer.XplanAbschnittLookup.lookupXpBegruendungAbschnitt;

/**
 * {@link Expression} that returns a textual representation of the
 * "XP_BegruendungAbschnitt" features referenced by an "XP_Objekt" feature.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class XplanBegruendungAbschnitte extends XPlanTexlicherAbschnitt {

	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features) {
		Set<Feature> abschnittFeatures = new LinkedHashSet<Feature>();
		abschnittFeatures.addAll(getBegruendungAbschnitteReferencedBySchluessel(feature));
		abschnittFeatures.addAll(getBegruendungAbschnitteReferencedByRef(feature));
		if (abschnittFeatures.isEmpty()) {
			return null;
		}
		StringBuilder sBuilder = new StringBuilder();
		for (Feature abschnitt : abschnittFeatures) {
			sBuilder.append(escape(toString(abschnitt)));
		}
		return new PrimitiveValue(sBuilder.toString());
	}

	public String toString(Feature f) {
		return super.toString(f);
	}

	private Set<Feature> getBegruendungAbschnitteReferencedBySchluessel(Feature feature) {
		Set<Feature> abschnittFeatures = new LinkedHashSet<Feature>();
		QName propName = new QName(feature.getName().getNamespaceURI(), "textSchluesselBegruendung");
		List<Property> props = feature.getProperties(propName);
		for (Property prop : props) {
			TypedObjectNode value = prop.getValue();
			if (value != null) {
				String[] schluessels = value.toString().split("[,;\\s]+");
				for (String schluessel : schluessels) {
					Feature begruendungAbschnitt = lookupXpBegruendungAbschnitt(schluessel);
					if (begruendungAbschnitt != null) {
						abschnittFeatures.add(begruendungAbschnitt);
					}
				}
			}
		}
		return abschnittFeatures;
	}

	private Set<Feature> getBegruendungAbschnitteReferencedByRef(Feature feature) {
		Set<Feature> abschnittFeatures = new LinkedHashSet<Feature>();
		QName propName = new QName(feature.getName().getNamespaceURI(), "refBegruendungInhalt");
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
