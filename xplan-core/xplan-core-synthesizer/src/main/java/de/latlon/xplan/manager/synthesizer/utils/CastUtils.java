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
package de.latlon.xplan.manager.synthesizer.utils;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.geometry.Geometry;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CastUtils {

	private CastUtils() {
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
		return new TypedObjectNodeArray<>(new TypedObjectNode[] { node });
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
