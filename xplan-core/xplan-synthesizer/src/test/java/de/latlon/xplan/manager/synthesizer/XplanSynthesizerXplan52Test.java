package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;
import org.junit.runner.RunWith;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;
import static org.xmlmatchers.transform.XmlConverters.the;
import static org.xmlmatchers.xpath.XpathReturnType.returningANumber;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(JUnitParamsRunner.class)
public class XplanSynthesizerXplan52Test extends AbstractXplanSynthesizerTest {

    @Parameters({ "xplan52/BP2070.zip", "xplan52/BP2135.zip", "xplan52/LA22.zip", "xplan52/LA67.zip" })
    @Test
    public void testCreateSynFeatures( String archiveName )
                    throws Exception {
        XPlanArchive archive = getTestArchive( archiveName );
        XPlanFeatureCollection originalFeatureCollection = readFeatures( archive );
        FeatureCollection synFeatureCollection = createSynFeatures( archive.getVersion(), originalFeatureCollection );

        int numberOfOriginalFeatures = originalFeatureCollection.getFeatures().size();
        int numberOfSynFeatures = synFeatureCollection.size();

        assertThat( numberOfSynFeatures, is( numberOfOriginalFeatures ) );
        String synGml = writeSynFeatureCollection( synFeatureCollection );

        assertThat( the( synGml ),
                    hasXPath( "count(//xplansyn:rechtscharakter[text() = ''])", nsContext(), returningANumber(),
                              is( 0d ) ) );
    }

    @Override
    XPlanVersion getXPlanVersion() {
        return XPLAN_52;
    }
}
