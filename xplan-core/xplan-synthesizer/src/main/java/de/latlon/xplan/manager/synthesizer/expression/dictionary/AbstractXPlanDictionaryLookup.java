/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.manager.synthesizer.expression.dictionary;

import de.latlon.xplan.manager.dictionary.XPlanDictionaries;
import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
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
 * {@link Expression} for translating codes from {@link XPlanDictionaries} to their
 * textual representation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class AbstractXPlanDictionaryLookup implements Expression {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractXPlanDictionaryLookup.class);

	private final Expression exp;

	private final String dictionaryName;

	public AbstractXPlanDictionaryLookup(Expression exp, String dictionaryName) {
		this.exp = exp;
		this.dictionaryName = dictionaryName;
	}

	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features, PlanContext planContext) {
		XPlanDictionaries xPlanDictionaries = getXPlanDictionaries(feature);
		if (xPlanDictionaries == null)
			return null;

		String translation = null;
		try {
			TypedObjectNodeArray<TypedObjectNode> codes = castToArray(exp.evaluate(feature, features, planContext));
			if (codes != null) {
				translation = "";
				for (TypedObjectNode o : codes.getElements()) {
					String code = toString(o);
					String desc = xPlanDictionaries.getTranslation(dictionaryName, code);
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

	protected abstract XPlanDictionaries getXPlanDictionaries(Feature feature);

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
