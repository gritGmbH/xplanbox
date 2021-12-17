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
package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.manager.synthesizer.expression.XplanTextAbschnitte;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;

import java.util.Arrays;
import java.util.List;

public class XpTextAbschnittFlattener extends AbstractFlattener {

	private static final List<String> ACCEPTEDELEMENTS = Arrays.asList(new String[] { "XP_TextAbschnitt",
			"BP_TextAbschnitt", "FP_TextAbschnitt", "LP_TextAbschnitt", "RP_TextAbschnitt", "SO_TextAbschnitt" });

	@Override
	public boolean accepts(TypedObjectNode node) {
		String elName = null;
		if (node instanceof Feature) {
			elName = ((Feature) node).getName().getLocalPart();
		}
		return ACCEPTEDELEMENTS.contains(elName);
	}

	@Override
	public String flatten(TypedObjectNode xpTextAbschnitt) {
		Feature feature = (Feature) xpTextAbschnitt;
		return new XplanTextAbschnitte().toString(feature);
	}

}
