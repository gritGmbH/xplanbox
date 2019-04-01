package de.latlon.xplan.manager.metadata;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import org.apache.commons.io.IOUtils;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.SimpleGeometryFactory;
import org.junit.Test;
import org.xmlmatchers.namespace.SimpleNamespaceContext;
import org.xmlmatchers.transform.XmlConverters;

import javax.xml.namespace.NamespaceContext;
import javax.xml.transform.Source;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.xmlmatchers.XmlMatchers.hasXPath;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MetadataCouplingHandlerTest {

    @Test
    public void testProcessMetadataCoupling()
                    throws Exception {
        CoupledResource coupledResource = new CoupledResource( "id", "http://test.de/id" );
        String planName = "TestPlan";
        CoupledResourceConfiguration config = createConfig();
        MetadataCouplingHandler metadataCouplingHandler = new MetadataCouplingHandler( config,
                                                                                       mockCswClient( coupledResource ) );

        metadataCouplingHandler.processMetadataCoupling( mockXplanFeatureCollection( planName ) );

        Path directoryToStoreDatasetMetadata = config.getDirectoryToStoreDatasetMetadata();
        long numberOfCreatedRecords = Files.list( directoryToStoreDatasetMetadata ).count();
        assertThat( numberOfCreatedRecords, is( 1l ) );

        assertThat( the( directoryToStoreDatasetMetadata ),
                    hasXPath( "//gmd:MD_Metadata/gmd:dateStamp/gco:Date", nsContext() ) );
    }

    private Source the( Path createdMetadataRecords )
                    throws IOException {
        Path metadataRecord = Files.list( createdMetadataRecords ).findFirst().get();
        byte[] bytes = Files.readAllBytes( metadataRecord );
        return XmlConverters.the( new String( bytes ) );
    }

    private XPlanFeatureCollection mockXplanFeatureCollection( String planName )
                    throws UnknownCRSException {
        XPlanFeatureCollection fc = mock( XPlanFeatureCollection.class );
        when( fc.getPlanName() ).thenReturn( planName );
        Envelope bbox = new SimpleGeometryFactory().createEnvelope( 5, 45, 8, 46, CRSManager.lookup( "EPSG:4326" ) );
        when( fc.getBboxIn4326() ).thenReturn( bbox );
        return fc;
    }

    private CswClient mockCswClient( CoupledResource coupledResource )
                    throws DataServiceCouplingException {
        CswClient cswClient = mock( CswClient.class );
        when( cswClient.requestMetadataRecord() ).thenReturn( coupledResource );
        return cswClient;
    }

    private CoupledResourceConfiguration createConfig()
                    throws IOException {
        String cswUrlProvidingDatasetMetadata = "http://test.de";
        String metadataResourceTemplate = "http://test.de/${METADATA_RECORD_IDENTIFIER}";

        Path metadataConfigDirectory = createDirectoryWithTemplate();

        Path directoryToStoreDatasetMetadata = Files.createTempDirectory( "directoryToStoreDatasetMetadataTest" );

        return new CoupledResourceConfiguration( cswUrlProvidingDatasetMetadata, metadataResourceTemplate,
                                                 metadataConfigDirectory, directoryToStoreDatasetMetadata );
    }

    private Path createDirectoryWithTemplate()
                    throws IOException {
        InputStream resourceAsStream = ServiceMetadataDocumentWriterTest.class.getResourceAsStream(
                        "iso-service-metadata-example-template.xml" );
        Path metadataConfigDirectory = Files.createTempDirectory( "metadataConfigDirectoryTest" );
        Path target = Files.createFile( metadataConfigDirectory.resolve( "service-iso-metadata-template.xml" ) );
        OutputStream output = Files.newOutputStream( target );
        IOUtils.copy( resourceAsStream, output );
        output.close();
        resourceAsStream.close();
        return metadataConfigDirectory;
    }

    private NamespaceContext nsContext() {
        SimpleNamespaceContext nsContext = new SimpleNamespaceContext();
        nsContext.bind( "gmd", "http://www.isotc211.org/2005/gmd" );
        nsContext.bind( "gco", "http://www.isotc211.org/2005/gco" );
        return nsContext;
    }

}