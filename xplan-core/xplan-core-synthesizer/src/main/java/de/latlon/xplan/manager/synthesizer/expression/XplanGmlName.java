/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;

import java.util.List;

import javax.xml.namespace.QName;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.GMLVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Returns a textual representation of the all <code>gml:name</code> properties of the
 * features.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class XplanGmlName implements Expression {

	private static final Logger LOG = LoggerFactory.getLogger(XplanGmlName.class);

	/**
	 * Returns a string representation of all 'gml:name' properties of the given feature.
	 *
	 * @returns comma separated list of 'gml:name property' values, may be
	 * <code>null</code> (if the feature does not have any)
	 */
	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features, PlanContext planContext) {
		StringBuilder sBuilder = new StringBuilder("");
		QName gmlName = getGmlNameQName(feature);
		List<Property> props = feature.getProperties(gmlName);
		if (props != null && !props.isEmpty()) {
			for (Property prop : props) {
				TypedObjectNode value = prop.getValue();
				if (value instanceof ElementNode) {
					ElementNode elNode = (ElementNode) value;
					Object codeValue = elNode.getChildren().get(0);
					PrimitiveValue codeSpace = elNode.getAttributes().get(new QName("codeSpace"));
					if (codeValue != null || codeSpace != null) {
						StringBuilder code = new StringBuilder();
						if (codeSpace != null) {
							code.append("{");
							code.append(codeSpace);
							code.append("}");
						}
						if (codeValue != null) {
							code.append(codeValue);
						}
						sBuilder.append("[");
						sBuilder.append(escape(code.toString()));
						sBuilder.append("]");
					}
				}
				else if (value != null) {
					LOG.warn("Unexpected type in XPlanGmlName: " + value.getClass());
				}
			}
		}
		if (sBuilder.toString().isEmpty()) {
			return null;
		}
		return new PrimitiveValue(sBuilder.toString());
	}

	private QName getGmlNameQName(Feature feature) {
		GMLVersion version = determineBaseVersion(feature.getName()).getGmlVersion();
		return new QName(version.getNamespace(), "name");
	}

	private String escape(String desc) {
		return desc.replace("][", "][][");
	}

}
