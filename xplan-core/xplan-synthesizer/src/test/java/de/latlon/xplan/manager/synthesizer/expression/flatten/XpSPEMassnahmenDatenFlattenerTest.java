package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.XplanFlattenProperty;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpSPEMassnahmenDatenFlattenerTest {

    @Test
    public void testFlatten() {
        FeatureCollection features = getTestFeatures( XPLAN_51, "flatten/XpSPEMassnahmenDaten.xml" );
        Feature feature = getTestFeature( features, "BP_SCHUTZPFLENTWFLAECHE" );
        XplanFlattenProperty expr = new XplanFlattenProperty( new Xpath( "xplan:massnahme" ) );
        PrimitiveValue value = expr.evaluate( feature, features );
        assertEquals( "[Maßnahme: Trockenrasen]", value.toString() );
    }

}