package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.manager.synthesizer.expression.Ausrichtung;
import de.latlon.xplan.manager.synthesizer.expression.ScenarioLoader;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AusrichtungTest {

    @Test
    public void testEvaluate_Empty() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_51, "Praesentationsobjekte.gml", "XP_PTO_EMPTY" );
        Ausrichtung horzontaleAusrichtung = new Ausrichtung( new Xpath( "xplan:horizontaleAusrichtung" ) );
        TypedObjectNode horizontaleValue = horzontaleAusrichtung.evaluate( feature );
        assertThat( asDouble( horizontaleValue ), is( 0.0 ) );

        Ausrichtung vertikaleAusrichtung = new Ausrichtung( new Xpath( "xplan:vertikaleAusrichtung" ) );
        TypedObjectNode vertikaleValue = vertikaleAusrichtung.evaluate( feature );
        assertThat( asDouble( vertikaleValue ), is( 0.0 ) );
    }

    @Test
    public void testEvaluate() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_51, "Praesentationsobjekte.gml", "XP_PTO" );
        Ausrichtung horzontaleAusrichtung = new Ausrichtung( new Xpath( "xplan:horizontaleAusrichtung" ) );
        TypedObjectNode horizontaleValue = horzontaleAusrichtung.evaluate( feature );
        assertThat( asDouble( horizontaleValue ), is( 0.0 ) );

        Ausrichtung vertikaleAusrichtung = new Ausrichtung( new Xpath( "xplan:vertikaleAusrichtung" ) );
        TypedObjectNode vertikaleValue = vertikaleAusrichtung.evaluate( feature );
        assertThat( asDouble( vertikaleValue ), is( 1.0 ) );
    }

    private double asDouble( TypedObjectNode value ) {
        PrimitiveValue primitiveValue = (PrimitiveValue) value;
        return (double) primitiveValue.getValue();
    }

}
