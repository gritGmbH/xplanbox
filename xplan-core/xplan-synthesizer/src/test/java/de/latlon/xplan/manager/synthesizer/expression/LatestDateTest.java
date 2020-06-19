package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.SimpleProperty;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LatestDateTest {

    @Test
    public void testEvaluate_Empty() {
        FeatureCollection features = getTestFeatures( XPLAN_51, "Praesentationsobjekte.gml" );
        Feature feature = getTestFeature( features, "BP_PLAN" );
        LatestDate latestDate = new LatestDate( new Xpath( "xplan:auslegungsStartDatum" ) );
        TypedObjectNode property = latestDate.evaluate( feature, features );
        assertThat( property, is( nullValue() ) );
    }

    @Test
    public void testEvaluate() {
        FeatureCollection features = getTestFeatures( XPLAN_51, "MultipleDates.gml" );
        Feature feature = getTestFeature( features, "BP_PLAN" );
        LatestDate latestDate = new LatestDate( new Xpath( "xplan:auslegungsStartDatum" ) );
        SimpleProperty property = (SimpleProperty) latestDate.evaluate( feature, features );
        assertThat( property.getValue().getAsText(), is( "1998-01-01" ) );
    }

}