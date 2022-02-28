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

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import static de.latlon.xplan.manager.codelists.XPlanCodeListsFactory.getXPlanSyn;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;

/**
 * Expression for setting the value of a "besondereZweckbestimmung" property.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @version $Revision: 1028 $, $Date: 2010-02-18 15:48:22 +0100 (Do, 18 Feb 2010) $
 */
public class XPlanBesondZweckbestLookup implements Expression {

	private Expression besondZweckbest;

	private String besondZweckBestCodeList;

	public XPlanBesondZweckbestLookup(Expression zweckbest, Expression besondZweckbest, String zweckbestCodeList,
			String besondZweckBestCodeList) {
		this.besondZweckbest = besondZweckbest;
		this.besondZweckBestCodeList = besondZweckBestCodeList;
	}

	@Override
	public TypedObjectNodeArray<PrimitiveValue> evaluate(Feature feature, FeatureCollection features) {
		PrimitiveValue[] normalizedCodes = null;

		// XPlan 3: always use value from besondereZweckbestimmung property
		TypedObjectNodeArray<TypedObjectNode> codes = castToArray(besondZweckbest.evaluate(feature, features));
		if (codes != null && codes.getElements().length > 1) {
			String xplanSynCode = codes.getElements()[0].toString();
			String xplanSynDesc = getXPlanSyn().getDescription(besondZweckBestCodeList, xplanSynCode);
			normalizedCodes = new PrimitiveValue[] { new PrimitiveValue(escape(xplanSynDesc)) };
		}

		if (normalizedCodes == null) {
			return null;
		}
		return new TypedObjectNodeArray<PrimitiveValue>(normalizedCodes);
	}

	private String escape(String desc) {
		return desc.replace("][", "][][");
	}

}
