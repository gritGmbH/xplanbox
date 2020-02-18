package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.deegree.commons.tom.primitive.BaseType.DECIMAL;
import static org.deegree.commons.tom.primitive.BaseType.DOUBLE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.geometry.Geometry;
import org.junit.Assert;
import org.junit.Test;

public class XpathTest {

    @Test
    public void testSimpleProperty() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Plan_1" );
        Xpath expr = new Xpath( "xplan:beschreibung" );
        Property prop = (Property) expr.evaluate( feature );
        PrimitiveValue value = (PrimitiveValue) prop.getValue();
        Assert.assertEquals( "Testdatensatz XPlabGML", value.toString() );
    }

    @Test
    public void testGeometryProperty() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Plan_1" );
        Xpath expr = new Xpath( "xplan:raeumlicherGeltungsbereich" );
        Property prop = (Property) expr.evaluate( feature );
        Geometry value = (Geometry) prop.getValue();
        Assert.assertEquals( "Polygon_1", value.getId() );
    }

    @Test
    public void testGmlIdAttribute() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Plan_1" );
        Xpath expr = new Xpath( "@gml:id" );
        PrimitiveValue value = (PrimitiveValue) expr.evaluate( feature );
        Assert.assertEquals( "BP_Plan_1", value.toString() );
    }

    @Test
    public void testDrehwinkelDefaultValue() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "XP_PPO_4" );
        Xpath expr = new Xpath( "xplan:drehwinkel/text()", 0.0 );
        PrimitiveValue value = (PrimitiveValue) expr.evaluate( feature );
        assertEquals( DOUBLE, value.getType().getBaseType() );
        assertEquals( "0.0", value.toString() );
    }

    @Test
    public void testDrehwinkelValue() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "XP_PPO_1" );
        Xpath expr = new Xpath( "xplan:drehwinkel/text()" );
        PrimitiveValue value = (PrimitiveValue) expr.evaluate( feature );
        assertEquals( DECIMAL, value.getType().getBaseType() );
        assertEquals( "0", value.toString() );
    }

    @Test
    public void testDrehwinkelUomAttribute() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "XP_PPO_1" );
        Xpath expr = new Xpath( "xplan:drehwinkel/@uom" );
        PrimitiveValue value = (PrimitiveValue) expr.evaluate( feature );
        Assert.assertEquals( "urn:adv:uom:grad", value.toString() );
    }

    @Test
    public void testMultiProperty() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Bereich_1" );
        Xpath expr = new Xpath( "xplan:nachrichtlich" );
        TypedObjectNodeArray<?> props = (TypedObjectNodeArray<?>) expr.evaluate( feature );
        Assert.assertEquals( 8, props.getElements().length );
        for ( TypedObjectNode node : props.getElements() ) {
            Property prop = (Property) node;
            prop.getValue();
        }
    }

    @Test
    public void testIntegerPropertyIndex() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "XP_PPO_1" );
        Xpath expr = new Xpath( "xplan:index" );
        TypedObjectNodeArray nodeArray = (TypedObjectNodeArray) expr.evaluate( feature );
        TypedObjectNode[] nodes = nodeArray.getElements();
        for ( int i = 0; i < nodes.length; i++ ) {
            PrimitiveValue value = ( (SimpleProperty) nodes[i] ).getValue();
            assertThat( value.getType().toString(), is( "INTEGER" ) );
            assertThat( value.toString(), is( Integer.toString( i ) ) );
        }
    }

}
