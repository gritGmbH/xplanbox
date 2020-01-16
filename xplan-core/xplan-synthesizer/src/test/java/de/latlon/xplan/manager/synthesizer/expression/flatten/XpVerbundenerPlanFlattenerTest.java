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
public class XpVerbundenerPlanFlattenerTest {

    @Test
    public void testFlattenAendert() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_51, "flatten/XpVerbundenerPlanFlattener.xml",
                                                               "BP_PLAN" );
        XplanFlattenProperty expr = new XplanFlattenProperty( new Xpath( "xplan:aendert" ) );
        PrimitiveValue value = expr.evaluate( feature );
        assertEquals( "[Verbundener Plan: Heideweg1, Rechtscharakter Planänderung: Ergaenzung, Nummer verbundener Plan: 42]", value.toString() );
    }

    @Test
    public void testFlattenWurdeGeaendertVon() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_51, "flatten/XpVerbundenerPlanFlattener.xml",
                                                               "BP_PLAN" );
        XplanFlattenProperty expr = new XplanFlattenProperty( new Xpath( "xplan:wurdeGeaendertVon" ) );
        PrimitiveValue value = expr.evaluate( feature );
        assertEquals( "[Verbundener Plan: Heideweg8, Rechtscharakter Planänderung: Aufhebung, Nummer verbundener Plan: 88]", value.toString() );
    }

}