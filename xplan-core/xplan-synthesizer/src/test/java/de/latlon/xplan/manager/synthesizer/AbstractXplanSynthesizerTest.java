package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.xml.XMLParsingException;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.Geometry;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLStreamWriter;
import org.deegree.gml.GMLVersion;
import org.junit.Before;
import org.xmlmatchers.namespace.SimpleNamespaceContext;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.deegree.gml.GMLVersion.GML_31;
import static org.deegree.gml.GMLVersion.GML_32;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class AbstractXplanSynthesizerTest {

    private final XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

    private AppSchema synSchema;

    @Before
    public void setup() {
        synSchema = XPlanSchemas.getInstance().getAppSchema( XPLAN_SYN, null );
    }

    abstract XPlanVersion getXPlanVersion();

    public static Source the( Path synGml )
                    throws IOException {
        InputStream fileInputStream = Files.newInputStream( synGml );
        return new StreamSource( fileInputStream );
    }

    protected NamespaceContext nsContext() {
        SimpleNamespaceContext nsContext = new SimpleNamespaceContext();
        nsContext = nsContext.withBinding( "xplansyn", XPlanVersion.XPLAN_SYN.getNamespace() );
        return nsContext;
    }

    protected FeatureCollection createSynFeatures( String archiveName )
                    throws IOException, XMLStreamException, UnknownCRSException, ValidatorException {
        XPlanArchive archive = getTestArchive( archiveName );
        XPlanFeatureCollection xplanFc = readFeatures( archive );
        int id = 1;
        for ( Feature feature : xplanFc.getFeatures() ) {
            feature.setId( "FEATURE_" + id++ );
        }
        return createSynFeatures( getXPlanVersion(), xplanFc );
    }

    protected FeatureCollection createSynFeatures( XPlanVersion version, XPlanFeatureCollection xplanFc ) {
        int id = 1;
        for ( Feature feature : xplanFc.getFeatures() ) {
            feature.setId( "FEATURE_" + id++ );
        }
        return xPlanSynthesizer.synthesize( version, xplanFc );
    }

    protected Path writeSynFeatureCollection( FeatureCollection fc, String archiveName )
                    throws Exception {
        String fileName = archiveName.substring( archiveName.indexOf( "/" ) + 1, archiveName.indexOf( "." ) );
        Path tempFile = Files.createTempFile( "XplanSynthesizerTest_" + fileName + "_", ".gml" );
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

    protected XPlanArchive getTestArchive( String name )
                    throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

    protected XPlanFeatureCollection readFeatures( XPlanArchive archive )
                    throws XMLStreamException, UnknownCRSException, ValidatorException {
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( archive.getVersion(), archive.getAde() );
        ICRS crs = archive.getCrs();
        GeometricValidatorImpl geometricValidator = new GeometricValidatorImpl();
        return geometricValidator.retrieveGeometricallyValidXPlanFeatures( archive, crs, schema, true, null );

    }

    protected FeatureCollection readSynFeatures( String fileName )
                    throws XMLStreamException, FactoryConfigurationError, XMLParsingException, UnknownCRSException {
        InputStream is = ResourceAccessor.readResourceStream( "xplansyn/" + fileName );
        XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader( is );
        GMLVersion gmlVersion = getXPlanVersion().getGmlVersion();
        GMLStreamReader gmlReader = GMLInputFactory.createGMLStreamReader( gmlVersion, xmlReader );
        gmlReader.setApplicationSchema( synSchema );
        return gmlReader.readFeatureCollection();
    }

    protected void assertEqualContent( FeatureCollection expected, FeatureCollection actual ) {
        assertEquals( "Wrong number of synthesized features.", expected.size(), actual.size() );
        Iterator<Feature> iter = actual.iterator();
        for ( Feature expectedFeature : expected ) {
            Feature actualFeature = iter.next();
            assertEqualContent( expectedFeature, actualFeature );
        }
    }

    private void assertEqualContent( Feature expected, Feature actual ) {
        removeBoundedBy( expected );
        removeBoundedBy( actual );
        assertEquals( "Wrong feature type.", expected.getType().getName(), actual.getType().getName() );
        assertEquals( "Wrong number of properties. ", expected.getProperties().size(), actual.getProperties().size() );
        String expectedProps = toString( expected.getProperties() );
        String actualProps = toString( actual.getProperties() );
        assertEquals( "Wrong property sequence. ", expectedProps, actualProps );
        Iterator<Property> iter = actual.getProperties().iterator();
        for ( Property expectedProperty : expected.getProperties() ) {
            Property actualProperty = iter.next();
            assertEqualContent( expectedProperty, actualProperty, actual.getId() );
        }
    }

    private void assertEqualContent( Property expected, Property actual, String featureId ) {
        if ( expected.getName().getLocalPart().equals( "xpPlanName" ) ) {
            return;
        }
        TypedObjectNode value = expected.getValue();
        if ( value instanceof Geometry ) {
            assertEquals( "Wrong geometry type of feature with id " + featureId + ".", expected.getValue().getClass(),
                          actual.getValue().getClass() );
        } else if ( value instanceof PrimitiveValue ) {
            assertEquals( "Wrong value of property '" + expected.getName() + "' of feature with id " + featureId + ".",
                          "" + expected.getValue(), "" + actual.getValue() );
        } else {
            throw new IllegalArgumentException();
        }
    }

    private String toString( List<Property> props ) {
        String s = "";
        for ( Property property : props ) {
            s += property.getName() + ", ";
        }
        return s;
    }

    private void removeBoundedBy( Feature f ) {
        List<Property> props = f.getProperties();
        List<Property> newProps = new ArrayList<Property>( props.size() );
        for ( Property property : props ) {
            if ( property.getName().getNamespaceURI().equals( GML_31.getNamespace() ) ) {
                continue;
            }
            newProps.add( property );
        }
        f.setProperties( newProps );
    }

    protected void writeTemp( FeatureCollection fc, String prefix ) {
        try {
            File file = Files.createTempFile( prefix, ".gml" ).toFile();
            FileOutputStream os = new FileOutputStream( file );
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

}