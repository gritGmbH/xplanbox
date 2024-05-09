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
package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DefaultFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		return node instanceof Feature || node instanceof ElementNode;
	}

	@Override
	public String flatten(TypedObjectNode node, boolean keepCodes) {
		if (node instanceof Feature) {
			return flatten((Feature) node);
		}
		if (node instanceof PrimitiveValue) {
			return node.toString();
		}
		if (node instanceof GenericXMLElement) {
			GenericXMLElement el = (GenericXMLElement) node;
			String s = "[" + el.getName().getLocalPart() + "=";
			for (TypedObjectNode child : el.getChildren()) {
				s += flatten(child, keepCodes);
			}
			s += "]";
			return s;
		}
		return "";
	}

	private String flatten(Feature feature) {
		return "[" + feature.getId() + "]";
	}

}
