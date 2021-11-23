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

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Geometry;

class Expressions {

	public static TypedObjectNode evaluateSingle(Expression expr, Feature f, FeatureCollection features) {
		TypedObjectNode value = expr.evaluate(f, features);
		if (value == null) {
			return null;
		}
		if (value instanceof TypedObjectNodeArray<?>) {
			@SuppressWarnings("unchecked")
			TypedObjectNodeArray<TypedObjectNode> array = (TypedObjectNodeArray<TypedObjectNode>) value;
			if (array.getElements().length == 0) {
				return null;
			}
			return array.getElements()[0];
		}
		return value;
	}

	public static PrimitiveValue castToPrimitive(TypedObjectNode node) {
		if (node == null) {
			return null;
		}
		if (node instanceof PrimitiveValue) {
			return (PrimitiveValue) node;
		}
		if (node instanceof Property) {
			Property prop = (Property) node;
			if (prop.getValue() instanceof PrimitiveValue) {
				return (PrimitiveValue) prop.getValue();
			}
		}
		if (node instanceof ElementNode) {
			ElementNode elNode = (ElementNode) node;
			if (elNode.getChildren().isEmpty()) {
				return null;
			}
			TypedObjectNode firstChild = elNode.getChildren().get(0);
			if (firstChild == null) {
				return null;
			}
			if (firstChild instanceof PrimitiveValue) {
				return (PrimitiveValue) firstChild;
			}
		}
		throw new IllegalArgumentException("Cannot cast '" + node.getClass() + "' to PrimitiveValue.");
	}

	@SuppressWarnings("unchecked")
	public static TypedObjectNodeArray<TypedObjectNode> castToArray(TypedObjectNode node) {
		if (node == null) {
			return null;
		}
		if (node instanceof TypedObjectNodeArray) {
			return (TypedObjectNodeArray<TypedObjectNode>) node;
		}
		return new TypedObjectNodeArray<TypedObjectNode>(new TypedObjectNode[] { node });
	}

	public static Geometry castToGeometry(TypedObjectNode node) {
		Geometry geom = null;
		if (node instanceof Geometry) {
			return (Geometry) node;
		}
		if (node instanceof ElementNode) {
			geom = castToGeometry((ElementNode) node);
		}
		return geom;
	}

	private static Geometry castToGeometry(ElementNode node) {
		if (node instanceof Geometry) {
			return (Geometry) node;
		}
		if (node != null) {
			for (TypedObjectNode child : node.getChildren()) {
				Geometry castChild = castToGeometry(child);
				if (castChild != null) {
					return castChild;
				}
			}
		}
		return null;
	}

	public static PrimitiveValue toPrimitiveValue(String s) {
		if (s == null) {
			return null;
		}
		return new PrimitiveValue(s);
	}

	public static PrimitiveValue toPrimitiveValue(Double s) {
		if (s == null) {
			return null;
		}
		return new PrimitiveValue(s);
	}

}
