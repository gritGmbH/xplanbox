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

import de.latlon.xplan.manager.synthesizer.PlanContext;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

/**
 * {@link Expression} that returns a string constant.
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class StringConstant implements Expression {

	private final PrimitiveValue value;

	public StringConstant(String s) {
		this.value = new PrimitiveValue(s);
	}

	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features, PlanContext planContext) {
		return value;
	}

}
