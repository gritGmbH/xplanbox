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
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.commons.util.XPlanVersionUtils;
import de.latlon.xplan.manager.synthesizer.PlanContext;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.xml.NamespaceBindings;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.xpath.TypedObjectNodeXPathEvaluator;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.expression.ValueReference;

import java.util.List;

/**
 * {@link Expression} that fetches a specific property or node (of the feature).
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class Xpath implements Expression {

	private final String expression;

	private final Object defaultValue;

	public Xpath(String expression) {
		this(expression, null);
	}

	public Xpath(String expression, Object defaultValue) {
		this.expression = expression;
		this.defaultValue = defaultValue;
	}

	@Override
	public TypedObjectNode evaluate(Feature feature, FeatureCollection features, PlanContext planContext) {
		NamespaceBindings nsContext = XPlanVersionUtils.retrieveNamespaceBindings(feature.getName());
		ValueReference propName = new ValueReference(expression, nsContext);
		TypedObjectNodeXPathEvaluator evaluator = new TypedObjectNodeXPathEvaluator();
		TypedObjectNode[] valueNodes;
		try {
			valueNodes = evaluator.eval(feature, propName);
		}
		catch (FilterEvaluationException e) {
			throw new RuntimeException(e.getMessage());
		}
		if (valueNodes == null || valueNodes.length == 0) {
			if (defaultValue == null)
				return null;
			else
				return new PrimitiveValue(defaultValue);
		}
		if (valueNodes.length == 1) {
			TypedObjectNode valueNode = valueNodes[0];
			if (valueNode instanceof GenericXMLElement) {
				List<TypedObjectNode> children = ((GenericXMLElement) valueNode).getChildren();
				if (children != null) {
					if (children.size() == 1) {
						return children.get(0);
					}
					else if (children.size() > 1) {
						return new TypedObjectNodeArray<>(children.toArray(new TypedObjectNode[] {}));
					}
				}
			}
			return valueNode;
		}
		return new TypedObjectNodeArray<>(valueNodes);
	}

	public String toString() {
		return "xpath( " + expression + " )";
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

}
