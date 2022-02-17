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
package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.commons.XPlanVersion;
import org.apache.xerces.xs.XSElementDeclaration;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;
import org.deegree.feature.types.AppSchema;
import org.deegree.feature.types.FeatureType;

import javax.xml.namespace.QName;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpGenerAttributFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		if (node instanceof Feature) {
			return isGenerAttribut((Feature) node);
		}
		else if (node instanceof ElementNode) {
			XSElementDeclaration elDecl = ((ElementNode) node).getXSType();
			return isGenerAttribut(elDecl);
		}
		return false;
	}

	private boolean isGenerAttribut(Feature feature) {
		XPlanVersion version = determineBaseVersion(feature.getName());
		if (!XPLAN_3.equals(version)) {
			return false;
		}
		FeatureType ft = feature.getType();
		AppSchema schema = ft.getSchema();
		String ns = feature.getName().getNamespaceURI();
		FeatureType generAttributFt = schema.getFeatureType(new QName(ns, "XP_GenerAttribut"));
		return schema.isSubType(generAttributFt, ft);
	}

	private boolean isGenerAttribut(XSElementDeclaration elDecl) {
		if ("XP_GenerAttribut".equals(elDecl.getName())) {
			return true;
		}
		return elDecl.getSubstitutionGroupAffiliation() != null
				&& isGenerAttribut(elDecl.getSubstitutionGroupAffiliation());
	}

	@Override
	public String flatten(TypedObjectNode xpGenerAttribut) {
		TypedObjectNode name = getPropertyValue(xpGenerAttribut, "name");
		TypedObjectNode wert = getPropertyValue(xpGenerAttribut, "wert");
		return "[\"" + name + "\"=\"" + wert + "\"]";
	}

}
