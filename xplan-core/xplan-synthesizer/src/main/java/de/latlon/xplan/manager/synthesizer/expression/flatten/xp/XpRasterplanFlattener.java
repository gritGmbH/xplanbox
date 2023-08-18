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
package de.latlon.xplan.manager.synthesizer.expression.flatten.xp;

import java.util.List;

import javax.xml.namespace.QName;

import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.types.AppSchema;
import org.deegree.feature.types.FeatureType;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpRasterplanFlattener extends AbstractFlattener {

	private static final Logger LOG = LoggerFactory.getLogger(XpRasterplanFlattener.class);

	@Override
	public boolean accepts(TypedObjectNode node) {
		if (node instanceof Feature) {
			Feature feature = (Feature) node;
			FeatureType ft = feature.getType();
			AppSchema schema = feature.getType().getSchema();
			String ns = feature.getName().getNamespaceURI();
			FeatureType rasterAenderungFt = schema.getFeatureType(new QName(ns, "XP_RasterplanAenderung"));
			if (rasterAenderungFt != null && schema.isSubType(rasterAenderungFt, ft)) {
				return true;
			}
			FeatureType rasterBasisFt = schema.getFeatureType(new QName(ns, "XP_RasterplanBasis"));
			if (rasterBasisFt != null && schema.isSubType(rasterBasisFt, ft)) {
				return true;
			}
			FeatureType rasterDarstellungFt = schema.getFeatureType(new QName(ns, "XP_Rasterdarstellung"));
			if (rasterDarstellungFt != null && schema.isSubType(rasterDarstellungFt, ft)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String flatten(TypedObjectNode rasterPlanFeatureEl, boolean keepCodes) {
		Feature feature = (Feature) rasterPlanFeatureEl;
		String ns = feature.getName().getNamespaceURI();
		QName refName = new QName(ns, "refScan");
		List<Property> props = feature.getProperties(refName);
		String s = "";
		for (Property prop : props) {
			TypedObjectNode value = prop.getValue();
			if (value != null) {
				if (value instanceof FeatureReference) {
					Feature xpExterneReferenzPlan = ((FeatureReference) value).getReferencedObject();
					s += new XpExterneReferenzFlattener(xpExterneReferenzPlan).flatten(xpExterneReferenzPlan,
							keepCodes);
				}
				else if (value instanceof ElementNode) {
					ElementNode elNode = (ElementNode) value;
					s += new XpExterneReferenzFlattener(feature).flatten(getFirstChild(elNode), keepCodes);
				}
				else {
					LOG.warn("Flattening of nodes from class {} is not supported yet!", value.getClass());
				}
			}
		}
		return s;
	}

	private TypedObjectNode getFirstChild(ElementNode elNode) {
		TypedObjectNode value;
		if (elNode.getChildren().size() == 1 && elNode.getChildren().get(0) instanceof ElementNode) {
			value = elNode.getChildren().get(0);
		}
		else {
			throw new IllegalArgumentException();
		}
		return value;
	}

}
