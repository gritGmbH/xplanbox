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
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.geometry.Geometry;
import org.junit.jupiter.api.Test;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static org.deegree.commons.tom.primitive.BaseType.DECIMAL;
import static org.deegree.commons.tom.primitive.BaseType.DOUBLE;
import static org.deegree.commons.tom.primitive.BaseType.INTEGER;
import static org.deegree.commons.tom.primitive.BaseType.STRING;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class XpathTest {

	@Test
	void testSimpleProperty() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		Xpath expr = new Xpath("xplan:beschreibung");
		Property prop = (Property) expr.evaluate(feature, features, planContext);
		PrimitiveValue value = (PrimitiveValue) prop.getValue();
		assertEquals("Testdatensatz XPlabGML", value.toString());
	}

	@Test
	void testGeometryProperty() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		Xpath expr = new Xpath("xplan:raeumlicherGeltungsbereich");
		Property prop = (Property) expr.evaluate(feature, features, planContext);
		Geometry value = (Geometry) prop.getValue();
		assertEquals("Polygon_1", value.getId());
	}

	@Test
	void testGmlIdAttribute() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		Xpath expr = new Xpath("@gml:id");
		PrimitiveValue value = (PrimitiveValue) expr.evaluate(feature, features, planContext);
		assertEquals("BP_Plan_1", value.toString());
	}

	@Test
	void testDrehwinkelDefaultValue() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "XP_PPO_4");
		Xpath expr = new Xpath("xplan:drehwinkel/text()", 0.0);
		PrimitiveValue value = (PrimitiveValue) expr.evaluate(feature, features, planContext);
		assertEquals(DOUBLE, value.getType().getBaseType());
		assertEquals("0.0", value.toString());
	}

	@Test
	void testDrehwinkelValue() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "XP_PPO_1");
		Xpath expr = new Xpath("xplan:drehwinkel/text()");
		PrimitiveValue value = (PrimitiveValue) expr.evaluate(feature, features, planContext);
		assertEquals(DECIMAL, value.getType().getBaseType());
		assertEquals("0", value.toString());
	}

	@Test
	void testDrehwinkelUomAttribute() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "XP_PPO_1");
		Xpath expr = new Xpath("xplan:drehwinkel/@uom");
		PrimitiveValue value = (PrimitiveValue) expr.evaluate(feature, features, planContext);
		assertEquals("urn:adv:uom:grad", value.toString());
	}

	@Test
	void testMultiProperty() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Bereich_1");
		Xpath expr = new Xpath("xplan:nachrichtlich");
		TypedObjectNodeArray<?> props = (TypedObjectNodeArray<?>) expr.evaluate(feature, features, planContext);
		assertEquals(8, props.getElements().length);
		for (TypedObjectNode node : props.getElements()) {
			Property prop = (Property) node;
			prop.getValue();
		}
	}

	@Test
	void testIntegerPropertyIndex() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "XP_PPO_1");
		Xpath expr = new Xpath("xplan:index");
		TypedObjectNodeArray nodeArray = (TypedObjectNodeArray) expr.evaluate(feature, features, planContext);
		TypedObjectNode[] nodes = nodeArray.getElements();
		for (int i = 0; i < nodes.length; i++) {
			PrimitiveValue value = ((SimpleProperty) nodes[i]).getValue();
			assertEquals(INTEGER, (value.getType().getBaseType()));
			assertEquals(Integer.toString(i), value.toString());
		}
	}

	@Test
	void testComplexProperty() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_60);
		Feature feature = getTestFeature(features, "GML_fa0eea57-ebb1-4d50-b205-95865d6b9284");
		Xpath expr = new Xpath("xplan:zweckbestimmung/xplan:BP_KomplexeZweckbestGruen/xplan:allgemein");
		PrimitiveValue value = (PrimitiveValue) expr.evaluate(feature, features, planContext);
		assertEquals(STRING, value.getType().getBaseType());
		assertEquals("1000", value.toString());
	}

}
