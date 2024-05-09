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
package de.latlon.xplan.manager.synthesizer.expression.praesentation;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class AbstractPraesentationsobjektLookupTest {

	protected static PrimitiveValue getEvaluate(FeatureCollection features, String gmlId,
			PraesentationsobjektLookup lookup) {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		Feature feature = getTestFeature(features, gmlId);
		TypedObjectNode evaluate = lookup.evaluate(feature, features, planContext);
		if (evaluate instanceof GenericProperty) {
			GenericProperty genericProperty = (GenericProperty) evaluate;
			TypedObjectNode child = genericProperty.getChildren().get(0);
			return (PrimitiveValue) child;
		}
		if (evaluate instanceof SimpleProperty) {
			SimpleProperty simpleProperty = (SimpleProperty) evaluate;
			return simpleProperty.getValue();
		}
		return (PrimitiveValue) evaluate;
	}

}
