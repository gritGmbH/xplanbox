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
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.geometry.Geometry;
import org.junit.Assert;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.deegree.commons.tom.primitive.BaseType.DECIMAL;
import static org.deegree.commons.tom.primitive.BaseType.DOUBLE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class XpathTest {

    @Test
    public void testSimpleProperty() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "BP_Plan_1" );
        Xpath expr = new Xpath( "xplan:beschreibung" );
        Property prop = (Property) expr.evaluate( feature, features );
        PrimitiveValue value = (PrimitiveValue) prop.getValue();
        Assert.assertEquals( "Testdatensatz XPlabGML", value.toString() );
    }

    @Test
    public void testGeometryProperty() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "BP_Plan_1" );
        Xpath expr = new Xpath( "xplan:raeumlicherGeltungsbereich" );
        Property prop = (Property) expr.evaluate( feature, features );
        Geometry value = (Geometry) prop.getValue();
        Assert.assertEquals( "Polygon_1", value.getId() );
    }

    @Test
    public void testGmlIdAttribute() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "BP_Plan_1" );
        Xpath expr = new Xpath( "@gml:id" );
        PrimitiveValue value = (PrimitiveValue) expr.evaluate( feature, features );
        Assert.assertEquals( "BP_Plan_1", value.toString() );
    }

    @Test
    public void testDrehwinkelDefaultValue() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "XP_PPO_4" );
        Xpath expr = new Xpath( "xplan:drehwinkel/text()", 0.0 );
        PrimitiveValue value = (PrimitiveValue) expr.evaluate( feature, features );
        assertEquals( DOUBLE, value.getType().getBaseType() );
        assertEquals( "0.0", value.toString() );
    }

    @Test
    public void testDrehwinkelValue() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "XP_PPO_1" );
        Xpath expr = new Xpath( "xplan:drehwinkel/text()" );
        PrimitiveValue value = (PrimitiveValue) expr.evaluate( feature, features );
        assertEquals( DECIMAL, value.getType().getBaseType() );
        assertEquals( "0", value.toString() );
    }

    @Test
    public void testDrehwinkelUomAttribute() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "XP_PPO_1" );
        Xpath expr = new Xpath( "xplan:drehwinkel/@uom" );
        PrimitiveValue value = (PrimitiveValue) expr.evaluate( feature, features );
        Assert.assertEquals( "urn:adv:uom:grad", value.toString() );
    }

    @Test
    public void testMultiProperty() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "BP_Bereich_1" );
        Xpath expr = new Xpath( "xplan:nachrichtlich" );
        TypedObjectNodeArray<?> props = (TypedObjectNodeArray<?>) expr.evaluate( feature, features );
        Assert.assertEquals( 8, props.getElements().length );
        for ( TypedObjectNode node : props.getElements() ) {
            Property prop = (Property) node;
            prop.getValue();
        }
    }

    @Test
    public void testIntegerPropertyIndex() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "XP_PPO_1" );
        Xpath expr = new Xpath( "xplan:index" );
        TypedObjectNodeArray nodeArray = (TypedObjectNodeArray) expr.evaluate( feature, features );
        TypedObjectNode[] nodes = nodeArray.getElements();
        for ( int i = 0; i < nodes.length; i++ ) {
            PrimitiveValue value = ( (SimpleProperty) nodes[i] ).getValue();
            assertThat( value.getType().toString(), is( "INTEGER" ) );
            assertThat( value.toString(), is( Integer.toString( i ) ) );
        }
    }

}
