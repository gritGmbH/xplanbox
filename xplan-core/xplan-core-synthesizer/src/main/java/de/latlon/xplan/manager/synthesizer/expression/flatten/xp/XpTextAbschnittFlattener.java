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

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpTextAbschnittFlattener extends AbstractTextlicherAbschnittFlattener {

	private static final List<String> ACCEPTEDELEMENTS = Arrays.asList(new String[] { "XP_TextAbschnitt",
			"BP_TextAbschnitt", "FP_TextAbschnitt", "LP_TextAbschnitt", "RP_TextAbschnitt", "SO_TextAbschnitt" });

	@Override
	public boolean accepts(TypedObjectNode node) {
		if (node instanceof Feature) {
			String elName = ((Feature) node).getName().getLocalPart();
			return ACCEPTEDELEMENTS.contains(elName);
		}
		return false;
	}

	@Override
	public String flatten(TypedObjectNode xpTextAbschnitt, boolean keepCodes) {
		Feature feature = (Feature) xpTextAbschnitt;
		return flatten(feature);
	}

}
