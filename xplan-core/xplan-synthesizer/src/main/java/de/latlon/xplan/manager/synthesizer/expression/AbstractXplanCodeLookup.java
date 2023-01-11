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

import de.latlon.xplan.manager.codelists.XPlanCodeLists;
import de.latlon.xplan.manager.synthesizer.PlanContext;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.castToArray;
import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.toPrimitiveValue;

/**
 * {@link Expression} for translating codes from {@link XPlanCodeLists} to their textual
 * representation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class AbstractXplanCodeLookup implements Expression {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractXplanCodeLookup.class);

	private final Expression exp;

	private final String codeListName;

	public AbstractXplanCodeLookup(Expression exp, String codeListName) {
		this.exp = exp;
		this.codeListName = codeListName;
	}

	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features, PlanContext planContext) {
		XPlanCodeLists xPlanCodeLists = getXplanCodeLists(feature);
		if (xPlanCodeLists == null)
			return null;

		String translation = null;
		try {
			TypedObjectNodeArray<TypedObjectNode> codes = castToArray(exp.evaluate(feature, features, planContext));
			if (codes != null) {
				translation = "";
				for (TypedObjectNode o : codes.getElements()) {
					String code = toString(o);
					String desc = xPlanCodeLists.getTranslation(codeListName, code);
					translation += "[" + escape(desc) + "]";
				}
				if (codes.getElements().length == 1) {
					translation = translation.substring(1, translation.length() - 1);
				}
			}
		}
		catch (Exception e) {
			String msg = "Error performing code translation lookup for feature '" + feature.getId() + "': "
					+ e.getMessage();
			LOG.warn(msg);
			translation = "";
		}
		return toPrimitiveValue(translation);
	}

	protected abstract XPlanCodeLists getXplanCodeLists(Feature feature);

	private String escape(String desc) {
		return desc.replace("][", "][][");
	}

	private String toString(TypedObjectNode o) {
		if (o instanceof GenericProperty) {
			TypedObjectNode value = ((GenericProperty) o).getValue();
			return value.toString();
		}
		return o.toString();
	}

}
