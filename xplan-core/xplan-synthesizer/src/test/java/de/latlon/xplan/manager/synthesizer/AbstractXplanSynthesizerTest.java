package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamWriter;
import org.junit.Before;
import org.xmlmatchers.namespace.SimpleNamespaceContext;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.deegree.gml.GMLVersion.GML_31;
import static org.deegree.gml.GMLVersion.GML_32;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AbstractXplanSynthesizerTest {

    private final XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

    private AppSchema synSchema;

    @Before
    public void setup() {
        synSchema = XPlanSchemas.getInstance().getAppSchema( XPLAN_SYN, null );
    }

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

}
