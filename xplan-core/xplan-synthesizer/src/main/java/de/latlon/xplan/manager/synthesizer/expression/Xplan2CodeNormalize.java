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
package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

/**
 * Expression for normalizing internal XPlanGML 2.0 / 3.0 codes so they match their XPlan
 * Syn counterparts.
 * <p>
 * For XPlan 3.0 features, the normalization always returns the original value (as the Syn
 * codes follow the XPlan 3.0 codes). For XPlan 2.0, the code is determined by looking up
 * the XPlan 2.0 description of the code, then finding the XPlan Syn code for this
 * description.
 * </p>
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @version $Revision: 1028 $, $Date: 2010-02-18 15:48:22 +0100 (Do, 18 Feb 2010) $
 */
public class Xplan2CodeNormalize implements Expression {

	private final Expression exp;

	private String xplanCodeList;

	public Xplan2CodeNormalize(Expression exp, String xplanCodeList) {
		this.exp = exp;
		this.xplanCodeList = xplanCodeList;
	}

	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features) {
		String normalizedCodes = null;
		try {
			TypedObjectNodeArray<TypedObjectNode> codes = castToArray(exp.evaluate(feature, features));
			if (codes != null) {
				normalizedCodes = "";
				for (TypedObjectNode o : codes.getElements()) {
					String code = o.toString();
					normalizedCodes += "[" + escape(code) + "]";
				}
				if (codes.getElements().length == 1) {
					// if there is only one code, then it does not need bracket delimiters
					normalizedCodes = normalizedCodes.substring(1, normalizedCodes.length() - 1);
				}
			}
		}
		catch (Exception e) {
			String msg = "Error performing code list lookup (" + xplanCodeList + ") for feature '" + feature.getId()
					+ "': " + e.getMessage();
			throw new RuntimeException(msg, e);
		}
		return toPrimitiveValue(normalizedCodes);
	}

	private String escape(String desc) {
		return desc.replace("][", "][][");
	}

}
