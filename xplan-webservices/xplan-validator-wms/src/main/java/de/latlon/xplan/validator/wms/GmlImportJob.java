package de.latlon.xplan.validator.wms;

import org.apache.commons.io.IOUtils;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreProvider;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.services.controller.OGCFrontController;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.deegree.gml.GMLVersion.GML_32;
import static org.deegree.protocol.wfs.transaction.action.IDGenMode.USE_EXISTING;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GmlImportJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger( GmlImportJob.class );

    private static final String MEMORY_FEATURESTORE = "xplansyn";

    private static Set<Path> insertedGml = new HashSet<>();

    @Override
    public void execute( JobExecutionContext jobExecutionContext )
                            throws JobExecutionException {
        DeegreeWorkspace workspace = OGCFrontController.getServiceWorkspace();
        if ( workspace == null )
            return;
        File workspaceLocation = workspace.getLocation();
        Path path = Paths.get( workspaceLocation.toURI() ).resolve( "data" );
        if ( !Files.exists( path ) )
            return;
        try {
            Files.find( path, Integer.MAX_VALUE,
                        ( p, bfa ) -> Files.isRegularFile( p ) && !insertedGml.contains( p.getFileName() ) ).forEach(
                                    p -> importGml( p, workspace ) );
        } catch ( IOException e ) {
            LOG.warn( "Could not find GML files to insert", e );
        }
    }

    private void importGml( Path p, DeegreeWorkspace workspace ) {
        LOG.info( "Insert {}", p );
        InputStream inputStream = null;
        XMLStreamReader xmlStreamReader = null;
        GMLStreamReader gmlStreamReader = null;
        try {
            inputStream = Files.newInputStream( p );
            xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader( inputStream );
            gmlStreamReader = GMLInputFactory.createGMLStreamReader( GML_32, xmlStreamReader );
            FeatureCollection fc = gmlStreamReader.readFeatureCollection();
            FeatureStore fs = workspace.getNewWorkspace().getResource( FeatureStoreProvider.class,
                                                                       MEMORY_FEATURESTORE );
            FeatureStoreTransaction ta = fs.acquireTransaction();
            int fids = ta.performInsert( fc, USE_EXISTING ).size();
            LOG.info( "Inserted featureCollection with " + fids + " features in memory." );
            ta.commit();
            insertedGml.add( p.getFileName() );
        } catch ( Exception e ) {
            LOG.warn( "Could not add featureCollection", e );
        } finally {
            closeQuietly( gmlStreamReader );
            closeQuietly( xmlStreamReader );
            IOUtils.closeQuietly( inputStream );
        }
    }

    private void closeQuietly( GMLStreamReader gmlStreamReader ) {
        if ( gmlStreamReader != null ) {
            try {
                gmlStreamReader.close();
            } catch ( XMLStreamException e ) {
            }
        }
    }

    private void closeQuietly( XMLStreamReader xmlStreamReader ) {
        if ( xmlStreamReader != null ) {
            try {
                xmlStreamReader.close();
            } catch ( XMLStreamException e ) {
            }
        }
    }

}