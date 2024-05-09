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
package de.latlon.xplan.manager.synthesizer.expression.praesentation;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.SimpleProperty;

import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.toPrimitiveValue;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class Ausrichtung implements Expression {

	private static final double AUSRICHTUNG_DEFAULT = 0.5;

	private enum AUSRICHTUNG {

		/* HORIZONTAL */
		LINKS("linksbündig", 0.0),

		RECHTS("rechtsbündig", 1.0),

		ZENTRUM("zentrisch", 0.5),

		/* VERTIKAL */
		BASIS("Basis", 0.0),

		MITTE("Mitte", 0.5),

		OBEN("Oben", 1.0);

		private final String codelistName;

		private final double anchor;

		AUSRICHTUNG(String codelistName, double anchorX) {
			this.codelistName = codelistName;
			this.anchor = anchorX;
		}

		public static AUSRICHTUNG valueOfCodelistName(String codelistName) {
			for (AUSRICHTUNG ausrichtung : values()) {
				if (ausrichtung.codelistName.equals(codelistName)) {
					return ausrichtung;
				}
			}
			return null;
		}

	}

	private final Expression exp;

	public Ausrichtung(Expression exp) {
		this.exp = exp;
	}

	@Override
	public TypedObjectNode evaluate(Feature feature, FeatureCollection features, PlanContext planContext) {
		TypedObjectNode property = exp.evaluate(feature, features, planContext);
		if (property != null) {
			if (property instanceof SimpleProperty) {
				return asTypedObjectNode(((SimpleProperty) property).getValue());
			}
			if (property instanceof GenericXMLElement) {
				return asTypedObjectNode(((GenericXMLElement) property).getValue());
			}
		}
		return toPrimitiveValue(AUSRICHTUNG_DEFAULT);
	}

	private TypedObjectNode asTypedObjectNode(PrimitiveValue property) {
		if (property != null) {
			String asText = property.getAsText();
			AUSRICHTUNG ausrichtung = AUSRICHTUNG.valueOfCodelistName(asText);
			if (ausrichtung != null) {
				return toPrimitiveValue(ausrichtung.anchor);
			}
		}
		return toPrimitiveValue(AUSRICHTUNG_DEFAULT);
	}

}
