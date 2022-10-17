package de.latlon.xplan.manager.synthesizer.expression.flatten.xp;

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

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpVerbundenerPlanBereichFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "XP_VerbundenerPlanBereich");
	}

	@Override
	public String flatten(TypedObjectNode node, boolean translateCodes) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) node).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Verbundener Planbereich", node, "planName", properties);
		appendCode("Rechtscharakter Planänderung", node, "rechtscharakter", version, "XP_RechtscharakterPlanaenderung",
				translateCodes, properties);
		appendCode("Änderungsart", node, "aenderungsArt", version, "XP_Aenderungsarten", translateCodes, properties);
		append("Nummer verbundener Plan", node, "nummer", properties);
		return encode(properties);
	}

}
