package de.latlon.xplan.manager.synthesizer;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.deegree.gml.GMLVersion.GML_31;
import static org.deegree.gml.GMLVersion.GML_32;
import static org.junit.Assert.assertThat;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.deegree.commons.config.ResourceInitException;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamWriter;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;

public class XplanSynthesizerXplan41Test {

    private final XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

    private AppSchema synSchema;

    @Before
    public void setup()
                    throws ResourceInitException {
        synSchema = XPlanSchemas.getInstance().getAppSchema( XPLAN_SYN, null );
    }

    @Test
    public void testBp2070()
                    throws Exception {
        FeatureCollection actual = createSynFeatures( "xplan41/BP2070.zip" );
        writeTemp( actual );
    }

    @Test
    public void testBp2135()
                    throws Exception {
        createSynFeatures( "xplan41/BP2135.zip" );
    }

    @Test
    public void testDemo()
                    throws Exception {
        createSynFeatures( "xplan41/Demo.zip" );
    }

    @Test
    public void testEidelstedt4()
                    throws Exception {
        createSynFeatures( "xplan41/Eidelstedt_4_V4.zip" );
    }

    @Test
    public void testEidelstedt4ContainsPropertyHoehenangabe()
                    throws Exception {
        FeatureCollection features = createSynFeatures( "xplan41/Eidelstedt_4_V4.zip" );

        assertThat( features, hasFeature( "BP_BaugebietsTeilFlaeche" ) );
        assertThat( features,
                    hasHoehenangabeProperty( "BP_BaugebietsTeilFlaeche",
                                             "[hoehenbezug=absolutNHN;bezugspunkt=HBA;h=23;]" ) );
    }

    @Test
    public void testFplan()
                    throws Exception {
        createSynFeatures( "xplan41/FPlan.zip" );
    }

    @Test
    public void testHc7Bereich2()
                    throws Exception {
        createSynFeatures( "xplan41/hc7_bereich_2_V4.zip" );
    }

    @Test
    public void testLa22()
                    throws Exception {
        createSynFeatures( "xplan41/LA22.zip" );
    }

    @Test
    public void testLa67()
                    throws Exception {
        createSynFeatures( "xplan41/LA67.zip" );
    }

    @Test
    public void testId103()
                    throws Exception {
        createSynFeatures( "xplan41/V4_1_ID_103.zip" );
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

    private void writeTemp( FeatureCollection fc ) {
        try {
            FileOutputStream os = new FileOutputStream( "/tmp/debug.gml" );
            XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter( os );
            xmlWriter = new IndentingXMLStreamWriter( xmlWriter );
            GMLStreamWriter gmlWriter = GMLOutputFactory.createGMLStreamWriter( GML_31, xmlWriter );
            Map<String, String> nsBindings = synSchema.getNamespaceBindings();
            nsBindings.put( "gml32", GML_32.getNamespace() );
            gmlWriter.setNamespaceBindings( nsBindings );
            gmlWriter.write( fc );
            gmlWriter.close();
            xmlWriter.close();
            os.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ( new GeometricValidatorImpl() ).retrieveGeometricallyValidXPlanFeatures( archive, crs, schema, true,
                                                                                         null );

    }

    private Matcher<? super FeatureCollection> hasFeature( final String featureName ) {
        return new BaseMatcher<FeatureCollection>() {

            @Override
            public boolean matches( Object item ) {
                FeatureCollection features = (FeatureCollection) item;
                for ( Feature feature : features ) {
                    if ( featureName.equals( feature.getName().getLocalPart() ) )
                        return true;
                }
                return false;
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Expect a feature with name " + featureName );
            }

        };
    }

    private Matcher<? super FeatureCollection> hasHoehenangabeProperty( final String featureName,
                                                                        final String expectedPropertyValue ) {
        return new BaseMatcher<FeatureCollection>() {
            private QName propName = new QName( "http://www.deegree.org/xplanung/1/0", "hoehenangabe" );

            @Override
            public boolean matches( Object item ) {
                FeatureCollection features = (FeatureCollection) item;
                List<Property> properties = findHoehenangabeProperties( features, featureName );
                if ( properties != null && !properties.isEmpty() ) {
                    String propertyValue = properties.get( 0 ).getValue().toString();
                    return expectedPropertyValue.equals( propertyValue );
                }
                return false;
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Expect a feature with name " + featureName + " and property " + propName
                                        + " with value " + expectedPropertyValue );
            }

            private List<Property> findHoehenangabeProperties( FeatureCollection features, String featureName ) {
                for ( Feature feature : features ) {
                    if ( featureName.equals( feature.getName().getLocalPart() ) ) {
                        return feature.getProperties( propName );
                    }
                }
                return null;
            }

        };
    }

}
