package de.latlon.xplan.manager.synthesizer;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.deegree.gml.GMLVersion.GML_31;
import static org.deegree.gml.GMLVersion.GML_32;
import static org.junit.Assert.assertEquals;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.deegree.commons.config.ResourceInitException;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.xml.XMLParsingException;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.Geometry;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLStreamWriter;
import org.junit.Before;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;

public class XplanSynthesizerXplan3Test {

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
        FeatureCollection expected = readSynFeatures( "BP2070_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/BP2070.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testBp2135()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "BP2135_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/BP2135.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testDemo()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "Demo_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/Demo.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testFplan()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "FPlan_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/FPlan.zip" );
        writeTemp( actual );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testHc7()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "hc7_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/hc7.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testLa22()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "LA22_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/LA22.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testLa67()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "LA67_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/LA67.zip" );
        assertEqualContent( expected, actual );
    }

    private void assertEqualContent( FeatureCollection expected, FeatureCollection actual ) {
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
            assertEqualContent( expectedProperty, actualProperty );
        }
    }

    private void assertEqualContent( Property expected, Property actual ) {
        if ( expected.getName().getLocalPart().equals( "xpPlanName" ) ) {
            return;
        }
        TypedObjectNode value = expected.getValue();
        if ( value instanceof Geometry ) {
            assertEquals( "Wrong geometry type.", expected.getValue().getClass(), actual.getValue().getClass() );
        } else if ( value instanceof PrimitiveValue ) {
            assertEquals( "Wrong value of property '" + expected.getName() + "'.", "" + expected.getValue(),
                          "" + actual.getValue() );
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
        ICRS crs = CRSManager.lookup( "EPSG:31467" );
        if ( archive.getCrs() != null ) {
            crs = archive.getCrs();
        }
        return ( new GeometricValidatorImpl() ).retrieveGeometricallyValidXPlanFeatures( archive, crs, schema, true,
                                                                                         null );

    }

    private FeatureCollection readSynFeatures( String fileName )
                    throws XMLStreamException, FactoryConfigurationError, IOException, XMLParsingException,
                    UnknownCRSException, URISyntaxException {
        String name = "xplansyn/" + fileName;
        InputStream is = ResourceAccessor.readResourceStream( name );
        XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader( is );
        GMLStreamReader gmlReader = GMLInputFactory.createGMLStreamReader( GML_31, xmlReader );
        gmlReader.setApplicationSchema( synSchema );
        return gmlReader.readFeatureCollection();
    }
}
