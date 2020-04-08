package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;
import org.deegree.feature.property.SimpleProperty;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LatestDateTest {

    @Test
    public void testEvaluate_Empty() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_51, "Praesentationsobjekte.gml", "BP_PLAN" );
        LatestDate latestDate = new LatestDate( new Xpath( "xplan:auslegungsStartDatum" ) );
        TypedObjectNode property = latestDate.evaluate( feature );
        assertThat( property, is( nullValue() ) );
    }

    @Test
    public void testEvaluate() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_51, "MultipleDates.gml", "BP_PLAN" );
        LatestDate latestDate = new LatestDate( new Xpath( "xplan:auslegungsStartDatum" ) );
        SimpleProperty property = (SimpleProperty) latestDate.evaluate( feature );
        assertThat( property.getValue().getAsText(), is( "1998-01-01" ) );
    }

}