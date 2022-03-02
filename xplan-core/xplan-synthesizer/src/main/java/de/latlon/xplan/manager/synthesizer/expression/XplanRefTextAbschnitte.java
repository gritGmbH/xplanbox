/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.manager.synthesizer.expression.flatten.XpExterneReferenzFlattener;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.List;

import static de.latlon.xplan.commons.synthesizer.Features.getPropertyValue;

/**
 * Returns a textual representation of the "XP_TextAbschnitt" features that use external
 * references and are referenced via the "texte" property of an "XP_Plan" feature.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class XplanRefTextAbschnitte implements Expression {

	private static final Logger LOG = LoggerFactory.getLogger(XplanRefTextAbschnitte.class);

	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features) {
		StringBuilder s = new StringBuilder();
		QName textePropName = new QName(feature.getName().getNamespaceURI(), "texte");
		List<Property> props = feature.getProperties(textePropName);
		for (Property prop : props) {
			FeatureReference ref = (FeatureReference) prop.getValue();
			if (ref != null) {
				append(s, ref.getReferencedObject());
			}
		}
		if (s.toString().isEmpty())
			return null;
		return new PrimitiveValue(s.toString());
	}

	private void append(StringBuilder s, Feature textAbschnitt) {
		QName propName = new QName(textAbschnitt.getName().getNamespaceURI(), "refText");
		TypedObjectNode refTextValue = getPropertyValue(textAbschnitt, propName);
		if (refTextValue != null) {
			if (refTextValue instanceof FeatureReference) {
				Feature extRef = ((FeatureReference) refTextValue).getReferencedObject();
				s.append(new XpExterneReferenzFlattener(extRef).flatten(extRef));
			}
			else if (refTextValue instanceof ElementNode) {
				ElementNode extRef = (ElementNode) refTextValue;
				s.append(new XpExterneReferenzFlattener(textAbschnitt).flatten(extRef));
			}
			else {
				LOG.warn("Creating a String value from class {} is not supported.", refTextValue.getClass());
			}
		}
	}

}
