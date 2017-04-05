package de.latlon.xplan.manager.synthesizer;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.xml.stream.XMLStreamException;

import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.junit.Ignore;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;

public class XplanSynthesizerXplan40Test {

    private final XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

    @Ignore ("File size of referenced plan was too large for Github. Test plan was removed from repository.")
    @Test
    public void testId106()
                    throws Exception {
        createSynFeatures( "xplan40/V4_1_ID_106.zip" );
    }

    private FeatureCollection createSynFeatures( String archiveName )
                    throws URISyntaxException, IOException, XMLStreamException, UnknownCRSException, ValidatorException {
        XPlanArchive archive = getTestArchive( archiveName );
        XPlanFeatureCollection xplanFc = readFeatures( archive );
        int id = 1;
        for ( Feature feature : xplanFc.getFeatures() ) {
            feature.setId( "FEATURE_" + id++ );
        }
        return xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );
    }

    private XPlanArchive getTestArchive( String name )
                    throws URISyntaxException, IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

    private XPlanFeatureCollection readFeatures( XPlanArchive archive )
                    throws XMLStreamException, UnknownCRSException, ValidatorException {
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( archive.getVersion(), archive.getAde() );
        ICRS crs = null;
        try {
            crs = CRSManager.lookup( "EPSG:31467" );
            if ( archive.getCrs() != null ) {
                crs = archive.getCrs();
            }
        } catch ( UnknownCRSException e ) {
            e.printStackTrace();
        }
        return ( new GeometricValidatorImpl() ).retrieveGeometricallyValidXPlanFeatures( archive, crs, schema, true,
                                                                                         null );

    }
}
