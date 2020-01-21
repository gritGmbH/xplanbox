package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.manager.synthesizer.expression.ScenarioLoader;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.XplanFlattenProperty;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpSPEMassnahmenDatenFlattenerTest {

    @Test
    public void testFlatten() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_51, "flatten/XpSPEMassnahmenDaten.xml",
                                                               "BP_SCHUTZPFLENTWFLAECHE" );
        XplanFlattenProperty expr = new XplanFlattenProperty( new Xpath( "xplan:massnahme" ) );
        PrimitiveValue value = expr.evaluate( feature );
        assertEquals( "[Maßnahme: Trockenrasen]", value.toString() );
    }

}