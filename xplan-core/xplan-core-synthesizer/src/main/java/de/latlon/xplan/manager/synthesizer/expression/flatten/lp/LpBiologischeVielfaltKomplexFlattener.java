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
package de.latlon.xplan.manager.synthesizer.expression.flatten.lp;

import de.latlon.xplan.manager.dictionary.XPlanCodelists;
import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.DefaultFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.Flattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.complex.ComplexFlattener;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LpBiologischeVielfaltKomplexFlattener extends AbstractFlattener {

	private final XPlanCodelists xPlanCodelists;

	private boolean keepCodes;

	/**
	 * @param xPlanCodelists
	 * @param keepCodes <code>true</code> if code properties should be translated
	 */
	public LpBiologischeVielfaltKomplexFlattener(XPlanCodelists xPlanCodelists, boolean keepCodes) {
		this.xPlanCodelists = xPlanCodelists;
		this.keepCodes = keepCodes;
	}

	@Override
	public boolean accepts(TypedObjectNode node) {
		return acceptsElementNode(node, "LP_BiologischeVielfaltKomplex");
	}

	@Override
	public String flatten(TypedObjectNode node, boolean keepCodes) {
		List<Pair<String, String>> properties = new ArrayList<>();
		appendFlattenedValue("Biologische Vielfalt", node, new ComplexFlattener(xPlanCodelists), "bioVielfaltTypus",
				properties);
		appendFlattenedValue("Planzenart", node, new ComplexFlattener(xPlanCodelists), "bioVfPflanzenArt", properties);
		appendFlattenedValue("Tierart", node, new ComplexFlattener(xPlanCodelists), "bioVfTierArt", properties);
		appendFlattenedValue("Biotoptyp", node, new ComplexFlattener(xPlanCodelists), "bioVfBiotoptyp", properties);
		appendBoolean("von gemeinschaftlichem Interesse kartiert", node, "bioVfArtFFHAnhangII", properties);
		return encode(properties);
	}

	private void appendFlattenedValue(String label, TypedObjectNode node, Flattener flattener, String propName,
			List<Pair<String, String>> properties) {
		String flattenedValue = toString(node, flattener, propName);
		if (flattenedValue != null) {
			properties.add(new Pair<>(label, flattenedValue));
		}
	}

	private String toString(TypedObjectNode node, Flattener flattener, String propName) {
		TypedObjectNode property = getPropertyValue(node, propName);
		if (property == null)
			return null;
		if (flattener.accepts(property)) {
			return flattener.flatten(property, keepCodes);
		}
		return new DefaultFlattener().flatten(node, keepCodes);
	}

}
