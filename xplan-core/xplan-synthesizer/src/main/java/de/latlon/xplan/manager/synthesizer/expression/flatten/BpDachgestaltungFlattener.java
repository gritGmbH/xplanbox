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

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class BpDachgestaltungFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "BP_Dachgestaltung");
	}

	@Override
	public String flatten(TypedObjectNode bpDachgestaltung) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) bpDachgestaltung).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Dachneigung", bpDachgestaltung, "DN", properties);
		append("Dachneigung Min", bpDachgestaltung, "DNmin", properties);
		append("Dachneigung Max", bpDachgestaltung, "DNmax", properties);
		append("Dachneigung Zwingend", bpDachgestaltung, "DNzwingend", properties);
		appendTranslatedCode("Dachform", bpDachgestaltung, "dachform", version, "BP_Dachform", properties);
		append("Detaillierte Dachform", bpDachgestaltung, "detaillierteDachform", properties);
		return encode(properties);
	}

}
