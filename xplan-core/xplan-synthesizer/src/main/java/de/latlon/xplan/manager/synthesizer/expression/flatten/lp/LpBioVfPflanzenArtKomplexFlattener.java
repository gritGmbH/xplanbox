package de.latlon.xplan.manager.synthesizer.expression.flatten.lp;

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
public class LpBioVfPflanzenArtKomplexFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "LP_BioVfPflanzenArtKomplex");
	}

	@Override
	public String flatten(TypedObjectNode node) {
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(((ElementNode) node).getName());
		List<Pair<String, String>> properties = new ArrayList<>();
		append("Pflanzenart", node, "bioVfPflanzenArtName", properties);
		appendTranslatedCode("Einordnung", node, "bioVfPflanzenSystematik", version, "LP_BioVfPflanzenArtSystematik",
				properties);
		append("Einordnung (Ergänzung)", node, "bioVfPflanzenSystematikText", properties);
		appendTranslatedCode("Rechtliche Grundlage", node, "bioVfPflanzenRechtlicherSchutz", version,
				"LP_BioVfPflanzenArtRechtlicherSchutz", properties);
		append("Rechtliche Grundlage (Ergänzung)", node, "bioVfPflanzenRechtlicherSchutzText", properties);
		return encode(properties);
	}

}