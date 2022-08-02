/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;
import org.deegree.feature.Feature;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpHoehenangabeFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		String elName = null;
		if (node instanceof Feature) {
			elName = ((Feature) node).getName().getLocalPart();
		}
		if (node instanceof ElementNode) {
			elName = ((ElementNode) node).getName().getLocalPart();
		}
		return "XP_Hoehenangabe".equals(elName);
	}

	@Override
	public String flatten(TypedObjectNode xpHoehenangabe) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) xpHoehenangabe).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		appendTranslatedCode("Höhenbezug", xpHoehenangabe, "hoehenbezug", version, "XP_ArtHoehenbezug", properties);
		append("Abweichender Höhenbezug", xpHoehenangabe, "abweichenderHoehenbezug", properties);
		appendTranslatedCode("Bezugspunkt", xpHoehenangabe, "bezugspunkt", version, "XP_ArtHoehenbezugspunkt",
				properties);
		append("Abweichender Bezugspunkt", xpHoehenangabe, "abweichenderBezugspunkt", properties);
		append("Höhe", xpHoehenangabe, "h", properties);
		append("Höhe Min", xpHoehenangabe, "hMin", properties);
		append("Höhe Max", xpHoehenangabe, "hMax", properties);
		append("Höhe Zwingend", xpHoehenangabe, "hZwingend", properties);
		return encode(properties);
	}

}
