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

import static de.latlon.xplan.manager.codelists.XPlanCodeListsFactory.*;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;

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
public class XPlanCodeNormalizeExt implements Expression {

	private final Expression exp;

	private final String xplan3CodeList;

	private final String xplanSynCodeList;

	public XPlanCodeNormalizeExt(Expression exp, String xplan2CodeList, String xplan3CodeList) {
		this.exp = exp;
		this.xplan3CodeList = xplan3CodeList;
		this.xplanSynCodeList = xplan3CodeList;
	}

	@Override
	public TypedObjectNodeArray<PrimitiveValue> evaluate(Feature feature, FeatureCollection features) {
		PrimitiveValue[] normalizedCodes = null;
		try {
			TypedObjectNodeArray<TypedObjectNode> codes = castToArray(exp.evaluate(feature, features));
			if (codes != null) {
				normalizedCodes = new PrimitiveValue[codes.getElements().length];
				int i = 0;
				for (TypedObjectNode code : codes.getElements()) {
					String description = getXPlan3Ext().getDescription(xplan3CodeList, code.toString());
					String xplanSynCode = getXPlanSynExt().getCode(xplanSynCodeList, description);
					normalizedCodes[i++] = new PrimitiveValue(xplanSynCode);
				}
			}
		}
		catch (Exception e) {
			String msg = "Error performing external code list lookup (" + xplan3CodeList + ") for feature '"
					+ feature.getId() + "': " + e.getMessage();
			throw new RuntimeException(msg, e);
		}
		return new TypedObjectNodeArray<>(normalizedCodes);
	}

}
