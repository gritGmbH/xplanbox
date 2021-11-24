/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.SimpleProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LatestDate implements Expression {

	private final Expression expression;

	public LatestDate(Expression expression) {
		this.expression = expression;
	}

	@Override
	public TypedObjectNode evaluate(Feature feature, FeatureCollection features) {
		TypedObjectNode prop = expression.evaluate(feature, features);
		TypedObjectNodeArray<TypedObjectNode> props = Expressions.castToArray(prop);
		if (props == null || props.getElements().length == 0)
			return null;

		TypedObjectNode[] elements = props.getElements();
		if (elements.length == 1)
			return elements[0];

		List<TypedObjectNode> typedObjectNodes = Arrays.asList(elements);
		Collections.sort(typedObjectNodes, (o1, o2) -> {
			PrimitiveValue value1 = getValue(o1);
			PrimitiveValue value2 = getValue(o2);
			return value1.compareTo(value2);
		});
		return typedObjectNodes.get(typedObjectNodes.size() - 1);
	}

	private PrimitiveValue getValue(TypedObjectNode prop) {
		if (!(prop instanceof SimpleProperty)) {
			String msg = "Trying to compare  '" + prop.getClass() + "', only properties are supported.";
			throw new IllegalArgumentException(msg);
		}
		return ((SimpleProperty) prop).getValue();
	}

}
