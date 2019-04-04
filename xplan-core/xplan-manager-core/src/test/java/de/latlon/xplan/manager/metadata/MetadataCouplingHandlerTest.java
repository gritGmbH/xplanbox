package de.latlon.xplan.manager.metadata;

import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.metadata.csw.CswClient;
import de.latlon.xplan.manager.metadata.csw.PlanRecordMetadata;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadata;
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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.xmlmatchers.XmlMatchers.hasXPath;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MetadataCouplingHandlerTest {

    @Test
    public void testProcessMetadataCoupling()
                    throws Exception {
        PlanRecordMetadata planRecordMetadata = new PlanRecordMetadata( "id", "http://test.de/id" );
        String planName = "TestPlan1";
        CoupledResourceConfiguration config = createConfig();
        XPlanDao xPlanDao = mock( XPlanDao.class );
        CswClient cswClient = mockCswClient( planRecordMetadata, planName );
        MetadataCouplingHandler metadataCouplingHandler = new MetadataCouplingHandler( xPlanDao, config, cswClient );

        int planId = 1;
        metadataCouplingHandler.processMetadataCoupling( planId, planName, mockPlanwerkServiceMetadata( planName ) );

        Path directoryToStoreDatasetMetadata = config.getDirectoryToStoreDatasetMetadata();
        assertThat( numberOfCreatedRecords( directoryToStoreDatasetMetadata ), is( 1l ) );

        assertThat( theRecordIn( directoryToStoreDatasetMetadata ),
                    hasXPath( "//gmd:MD_Metadata/gmd:dateStamp/gco:Date", nsContext() ) );

        verify( xPlanDao, times( 1 ) ).insertPlanWerkWmsMetadata( eq( planId ), eq( planName ), anyString(),
                                                                  anyString(), anyString() );
    }

    @Test
    public void testProcessMetadataCoupling_UnavailableRecord()
                    throws Exception {
        String planName = "TestPlan2";
        CoupledResourceConfiguration config = createConfig();
        XPlanDao xPlanDao = mock( XPlanDao.class );
        CswClient cswClient = mockCswClient( null, planName );
        MetadataCouplingHandler metadataCouplingHandler = new MetadataCouplingHandler( xPlanDao, config, cswClient );

        int planId = 1;
        metadataCouplingHandler.processMetadataCoupling( planId, planName, mockPlanwerkServiceMetadata( planName ) );

        Path directoryToStoreDatasetMetadata = config.getDirectoryToStoreDatasetMetadata();
        assertThat( numberOfCreatedRecords( directoryToStoreDatasetMetadata ), is( 0l ) );
        verify( xPlanDao, times( 1 ) ).insertPlanWerkWmsMetadata( anyInt(), anyString(), anyString(), anyString(),
                                                                  anyString() );
    }

    private Object numberOfCreatedRecords( Path directoryToStoreDatasetMetadata )
                    throws IOException {
        return Files.list( directoryToStoreDatasetMetadata ).count();
    }

    private Source theRecordIn( Path createdMetadataRecords )
                    throws IOException {
        Path metadataRecord = Files.list( createdMetadataRecords ).findFirst().get();
        byte[] bytes = Files.readAllBytes( metadataRecord );
        return XmlConverters.the( new String( bytes ) );
    }

    private PlanwerkServiceMetadata mockPlanwerkServiceMetadata( String planName )
                    throws UnknownCRSException {
        PlanwerkServiceMetadata metadata = mock( PlanwerkServiceMetadata.class );
        when( metadata.getTitle() ).thenReturn( planName );
        Envelope bbox = new SimpleGeometryFactory().createEnvelope( 5, 45, 8, 46, CRSManager.lookup( "EPSG:4326" ) );
        when( metadata.getEnvelope() ).thenReturn( bbox );
        return metadata;
    }

    private CswClient mockCswClient( PlanRecordMetadata coupledResource, String planName )
                    throws DataServiceCouplingException {
        CswClient cswClient = mock( CswClient.class );
        when( cswClient.requestMetadataRecord( planName ) ).thenReturn( coupledResource );
        return cswClient;
    }

    private CoupledResourceConfiguration createConfig()
                    throws IOException {
        String cswUrlProvidingDatasetMetadata = "http://test.de";

        Path metadataConfigDirectory = createDirectoryWithTemplate();

        Path directoryToStoreDatasetMetadata = Files.createTempDirectory( "directoryToStoreDatasetMetadataTest" );

        String planWerkBaseUrl = "http://localhost:8080/xplan-planwerk-wms";
        return new CoupledResourceConfiguration( cswUrlProvidingDatasetMetadata, metadataConfigDirectory,
                                                 directoryToStoreDatasetMetadata, planWerkBaseUrl, 750, 750 );
    }

    private Path createDirectoryWithTemplate()
                    throws IOException {
        InputStream resourceAsStream = MetadataCouplingHandlerTest.class.getResourceAsStream(
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