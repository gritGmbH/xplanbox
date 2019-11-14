package de.latlon.xplan.validator.wms;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.memory.jaxb.GMLVersionType;
import org.deegree.feature.persistence.memory.jaxb.MemoryFeatureStoreConfig;
import org.deegree.feature.types.AppSchema;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static java.lang.Boolean.TRUE;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static org.deegree.gml.GMLVersion.GML_32;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidatorWmsManager {

    private static final Logger LOG = LoggerFactory.getLogger( ValidatorWmsManager.class );

    private static final String RELATIVE_PATH_TO_STORE = "datasources/feature/xplansyn.xml";

    private static final String RELATIVE_PATH_TO_DATE_DIR = "data";

    public static final String JAXB_CTX_PATH = "org.deegree.feature.persistence.memory.jaxb";

    private final Path pathToMemoryFeatureStore;

    private final Path pathToDataDirectory;

    private XPlanSynthesizer synthesizer;

    public ValidatorWmsManager( XPlanSynthesizer synthesizer, Path workspaceLocation )
                            throws IOException {
        this.synthesizer = synthesizer;
        if ( workspaceLocation == null )
            throw new IllegalArgumentException( "Workspace does not exist" );
        this.pathToMemoryFeatureStore = workspaceLocation.resolve( RELATIVE_PATH_TO_STORE );
        if ( !Files.exists( pathToMemoryFeatureStore ) )
            throw new IllegalArgumentException( "Memory feature store confighuration does not exist at "
                                                + pathToMemoryFeatureStore );
        this.pathToDataDirectory = workspaceLocation.resolve( RELATIVE_PATH_TO_DATE_DIR );
        if ( !Files.exists( pathToDataDirectory ) )
            Files.createDirectory( pathToDataDirectory );
    }

    /**
     * @param featureCollection
     *                         feature collection to append to the {@link MemoryFeatureStoreConfig}, never <code>null</code>
     * @throws ValidatorWmsException
     *                         if the configuration could not be writtem
     */
    public void insert( XPlanFeatureCollection featureCollection )
                            throws ValidatorWmsException {
        try {
            Path path = writeSynFeatureCollectionAsGml( featureCollection );
            LOG.info( "Write XPlanValidatorWMS gml to {}", path );
            MemoryFeatureStoreConfig config = appendToConfig( path );
            persist( config );
        } catch ( IOException | JAXBException | TransformationException | XMLStreamException | UnknownCRSException e ) {
            throw new ValidatorWmsException( e );
        }
    }

    private Path writeSynFeatureCollectionAsGml( XPlanFeatureCollection featureCollection )
                            throws IOException, TransformationException, XMLStreamException, UnknownCRSException {
        FeatureCollection synthesizedFeatureCollection = synthesizer.synthesize( featureCollection );

        String fileName = UUID.randomUUID().toString() + ".gml";
        Path pathToFile = pathToDataDirectory.resolve( fileName );
        GMLStreamWriter gmlWriter = null;
        try (OutputStream output = Files.newOutputStream( pathToFile )) {
            gmlWriter = createGmlWriter( output );
            AppSchema synSchema = XPlanSchemas.getInstance().getAppSchema( XPLAN_SYN, null );
            Map<String, String> nsBindings = synSchema.getNamespaceBindings();
            nsBindings.put( "gml32", GML_32.getNamespace() );
            gmlWriter.setNamespaceBindings( nsBindings );
            gmlWriter.write( synthesizedFeatureCollection );
            return pathToFile;
        } finally {
            closeQuietly( gmlWriter );
        }
    }

    private MemoryFeatureStoreConfig appendToConfig( Path pathToDataFile )
                            throws IOException, JAXBException {
        JAXBContext ctx = JAXBContext.newInstance( JAXB_CTX_PATH );
        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        try (InputStream memoryStore = Files.newInputStream( pathToMemoryFeatureStore )) {
            MemoryFeatureStoreConfig config = (MemoryFeatureStoreConfig) unmarshaller.unmarshal( memoryStore );
            List<MemoryFeatureStoreConfig.GMLFeatureCollection> collections = config.getGMLFeatureCollection();
            MemoryFeatureStoreConfig.GMLFeatureCollection gmlFeatureCollection = new MemoryFeatureStoreConfig.GMLFeatureCollection();
            gmlFeatureCollection.setVersion( GMLVersionType.GML_32 );
            gmlFeatureCollection.setValue( pathToDataFile.toString() );
            collections.add( gmlFeatureCollection );
            return config;
        }
    }

    private void persist( MemoryFeatureStoreConfig memoryFeatureStore )
                            throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance( JAXB_CTX_PATH );
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty( JAXB_FORMATTED_OUTPUT, TRUE );
        marshaller.marshal( memoryFeatureStore, pathToMemoryFeatureStore.toFile() );
    }

    private GMLStreamWriter createGmlWriter( OutputStream output )
                            throws XMLStreamException {
        XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter( output );
        xmlWriter = new IndentingXMLStreamWriter( xmlWriter );
        return GMLOutputFactory.createGMLStreamWriter( GML_32, xmlWriter );
    }

    private void closeQuietly( GMLStreamWriter gmlWriter ) {
        try {
            if ( gmlWriter != null )
                gmlWriter.close();
        } catch ( XMLStreamException e ) {
        }
    }

}