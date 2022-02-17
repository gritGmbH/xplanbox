/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;
import static de.latlon.xplan.manager.codelists.XPlanCodeListsFactory.getXPlanSyn;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.feature.FeatureCollection;

/**
 * {@link Expression} for translating codes from internal XPlan2 codelists to their
 * XPlan3/XPlanSyn counterpart.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class Xplan2CodeLookup implements Expression {

	private final Expression exp;

	private final String xplanSynCodeList;

	public Xplan2CodeLookup(Expression exp, String xplan2CodeList, String xplan3CodeList) {
		this.exp = exp;
		this.xplanSynCodeList = xplan3CodeList;
	}

	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features) {
		String descriptions = null;
		XPlanVersion version = determineBaseVersion(feature.getName());
		try {
			TypedObjectNodeArray<TypedObjectNode> codes = castToArray(exp.evaluate(feature, features));
			if (codes != null) {
				descriptions = "";
				for (TypedObjectNode o : codes.getElements()) {
					String code = o.toString();
					String desc;
					if (version == XPLAN_3) {
						desc = getXPlanSyn().getDescription(xplanSynCodeList, code);
					}
					else {
						// TODO lookup xplanSynCodeList
						desc = code;
					}
					descriptions += "[" + escape(desc) + "]";
				}
				if (codes.getElements().length == 1) {
					// if there is only one code, then it does not need bracket delimiters
					descriptions = descriptions.substring(1, descriptions.length() - 1);
				}
			}
		}
		catch (Exception e) {
			String msg = "Error performing code list lookup for feature '" + feature.getId() + "': " + e.getMessage();
			throw new RuntimeException(msg, e);
		}
		return toPrimitiveValue(descriptions);
	}

	private String escape(String desc) {
		return desc.replace("][", "][][");
	}

}
