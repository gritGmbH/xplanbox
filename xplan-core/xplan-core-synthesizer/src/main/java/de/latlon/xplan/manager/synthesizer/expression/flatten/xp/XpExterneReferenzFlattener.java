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

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.dictionary.XPlanDictionaries;
import de.latlon.xplan.manager.dictionary.XPlanEnumerationFactory;
import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;
import org.deegree.feature.property.GenericProperty;

import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpExterneReferenzFlattener extends AbstractFlattener {

	private final String fid;

	private final Feature contextFeature;

	public XpExterneReferenzFlattener(Feature contextFeature) {
		this.contextFeature = contextFeature;
		this.fid = contextFeature.getId();
	}

	@Override
	public boolean accepts(TypedObjectNode node) {
		String elName = null;
		if (node instanceof Feature) {
			elName = ((Feature) node).getName().getLocalPart();
		}
		else if (node instanceof ElementNode) {
			elName = ((ElementNode) node).getName().getLocalPart();
		}
		return "XP_ExterneReferenz".equals(elName) || "XP_ExterneReferenzPlan".equals(elName)
				|| "XP_SpezExterneReferenz".equals(elName);
	}

	@Override
	public String flatten(TypedObjectNode xpExterneReferenz, boolean keepCodes) {
		StringBuilder extRef = new StringBuilder();
		TypedObjectNode referenzUrl = getPropertyValue(xpExterneReferenz, "referenzURL");
		String typ = translateTyp(xpExterneReferenz);
		appendUrl(referenzUrl, extRef, typ);
		TypedObjectNode georefURL = getPropertyValue(xpExterneReferenz, "georefURL");
		appendUrl(georefURL, extRef, "Georeferenz");
		return extRef.toString();
	}

	private void appendUrl(TypedObjectNode georefURL, StringBuilder extRef, String typ) {
		if (georefURL != null) {
			extRef.append("[");
			String georefUrlString = georefURL.toString();
			if (georefUrlString.contains(":/")) {
				extRef.append(escape(georefUrlString));
			}
			else {
				extRef.append("/getAttachment?featureID=")
					.append(fid)
					.append("&filename=")
					.append(escape(georefUrlString));
			}
			extRef.append(" | ");
			if (typ != null)
				extRef.append(typ);
			else
				extRef.append("Externe Referenz");
			extRef.append("]");
		}
	}

	private String translateTyp(TypedObjectNode xpExterneReferenz) {
		XPlanVersion version = determineBaseVersion(contextFeature.getName());
		TypedObjectNode typ = getPropertyValue(xpExterneReferenz, "typ");
		if (typ == null)
			return null;
		XPlanDictionaries xPlanCodeLists = XPlanEnumerationFactory.get(version);
		String code = toString(typ);
		return xPlanCodeLists.getTranslation("XP_ExterneReferenzTyp", code);
	}

	private String toString(TypedObjectNode o) {
		if (o instanceof GenericProperty) {
			TypedObjectNode value = ((GenericProperty) o).getValue();
			return value.toString();
		}
		return o.toString();
	}

}
