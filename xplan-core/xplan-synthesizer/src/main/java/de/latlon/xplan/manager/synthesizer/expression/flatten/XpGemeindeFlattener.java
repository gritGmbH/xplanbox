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

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class XpGemeindeFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		String elName = null;
		if (node instanceof ElementNode) {
			elName = ((ElementNode) node).getName().getLocalPart();
		}
		return "XP_Gemeinde".equals(elName);
	}

	@Override
	public String flatten(TypedObjectNode xpGemeinde) {
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Gemeindeschlüssel", xpGemeinde, "ags", properties);
		append("Regionalschlüssel", xpGemeinde, "rs", properties);
		append("Gemeinde", xpGemeinde, "gemeindeName", properties);
		append("Ortsteil", xpGemeinde, "ortsteilName", properties);
		return encode(properties);
	}

}
