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

import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpVerfahrensMerkmalFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return accepts(node, "XP_VerfahrensMerkmal");
	}

	@Override
	public String flatten(TypedObjectNode xpVerfahrensmerkmal, boolean keepCodes) {
		PrimitiveValue datum = (PrimitiveValue) getPropertyValue(xpVerfahrensmerkmal, "datum");
		PrimitiveValue vermerk = (PrimitiveValue) getPropertyValue(xpVerfahrensmerkmal, "vermerk");
		PrimitiveValue signatur = (PrimitiveValue) getPropertyValue(xpVerfahrensmerkmal, "signatur");
		PrimitiveValue signiert = (PrimitiveValue) getPropertyValue(xpVerfahrensmerkmal, "signiert");
		return encode(datum, vermerk, signatur, signiert);
	}

	private String encode(PrimitiveValue datum, PrimitiveValue vermerk, PrimitiveValue signatur,
			PrimitiveValue signiert) {
		String s = "[";
		if (datum != null) {
			s += datum + ": ";
		}
		if (vermerk != null) {
			s += "\"" + vermerk + "\"";
		}
		s += " (";
		if (signatur != null && !signatur.toString().isEmpty()) {
			s += signatur + ", ";
		}
		if (signiert != null && "true".equalsIgnoreCase(signiert.toString())) {
			s += "signiert";
		}
		else {
			s += "nicht signiert";
		}
		s += ")]";
		return s;
	}

}
