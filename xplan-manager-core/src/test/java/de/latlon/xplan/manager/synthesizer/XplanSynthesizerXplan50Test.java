package de.latlon.xplan.manager.synthesizer;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.deegree.gml.GMLVersion.GML_31;
import static org.deegree.gml.GMLVersion.GML_32;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;
import static org.xmlmatchers.xpath.XpathReturnType.returningANumber;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
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
import org.junit.runner.RunWith;
import org.xmlmatchers.namespace.SimpleNamespaceContext;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import org.xmlmatchers.xpath.XpathReturnType;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(JUnitParamsRunner.class)
public class XplanSynthesizerXplan50Test {

    private final XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

    private AppSchema synSchema;

    @Before
    public void setup() {
        synSchema = XPlanSchemas.getInstance().getAppSchema( XPLAN_SYN, null );
    }

    // , "xplan50/BP2135.zip", "xplan50/FPlan.zip", "xplan50/LA22.zip",
    // "xplan50/LA67.zip"
    @Parameters({ "xplan50/BP2070.zip" })
    @Test
    public void testCreateSynFeatures( String archiveName )
                            throws Exception {
        XPlanArchive archive = getTestArchive( archiveName );
        XPlanFeatureCollection originalFeatureCollection = readFeatures( archive );
        FeatureCollection synFeatureCollection = createSynFeatures( archive.getVersion(), originalFeatureCollection );

        int numberOfOriginalFeatures = originalFeatureCollection.getFeatures().size();
        int numberOfSynFeatures = synFeatureCollection.size();

        assertThat( numberOfSynFeatures, is( numberOfOriginalFeatures ) );
        Path synGml = writeSynFeatureCollection( synFeatureCollection );

        assertThat( the( synGml ), hasXPath( "count(//xplansyn:rechtscharakter[text() = ''])", nsContext(), returningANumber(), is( 0d ) ) );
    }

    public static Source the( Path synGml )
                            throws IOException {
        InputStream fileInputStream = Files.newInputStream( synGml );
        return new StreamSource( fileInputStream );
    }

    private NamespaceContext nsContext() {
        SimpleNamespaceContext nsContext = new SimpleNamespaceContext();
        nsContext = nsContext.withBinding( "xplansyn", XPlanVersion.XPLAN_SYN.getNamespace() );
        return nsContext;
    }

    private FeatureCollection createSynFeatures( XPlanVersion version, XPlanFeatureCollection xplanFc ) {
        int id = 1;
        for ( Feature feature : xplanFc.getFeatures() ) {
            feature.setId( "FEATURE_" + id++ );
        }
        return xPlanSynthesizer.synthesize( version, xplanFc );
    }

    private Path writeSynFeatureCollection( FeatureCollection fc )
                            throws Exception {
        Path tempFile = Files.createTempFile( "XplanSynthesizerXplan50Test", "gml" );
        OutputStream os = Files.newOutputStream( tempFile );
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
        return tempFile;
    }

    private XPlanArchive getTestArchive( String name )
                            throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

    private XPlanFeatureCollection readFeatures( XPlanArchive archive )
                            throws XMLStreamException, UnknownCRSException, ValidatorException {
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( archive.getVersion(), archive.getAde() );
        ICRS crs = archive.getCrs();
        GeometricValidatorImpl geometricValidator = new GeometricValidatorImpl();
        return geometricValidator.retrieveGeometricallyValidXPlanFeatures( archive, crs, schema, true, null );

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

}
