package de.latlon.xplan.manager.inspireplu;

import static org.deegree.gml.GMLVersion.GML_32;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.io.IOUtils;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.manager.database.XPlanDao;

/**
 * Retrieves the XPlan GML from the database, transforms the plan to INSPIRE PLU and inserts the transformed plan to the
 * INSPIRE download service for PLU.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InspirePluPublisher {

    private static final Logger LOG = LoggerFactory.getLogger( InspirePluPublisher.class );

    private XPlanDao xPlanDao;

    private InspirePluTransformator transformator;

    /**
     * Instantiates the {@link InspirePluPublisher}
     *
     * @param xPlanDao
     *            used to retrieve the XPlan GML insert the INSPIRE PLU, never <code>null</code>
     * @param transformator
     *            the {@link InspirePluTransformator} to transform the XPlan GML to INSPIRE PLU, never
     *            <code>nulll</code>
     */
    public InspirePluPublisher( XPlanDao xPlanDao, InspirePluTransformator transformator ) {
        this.xPlanDao = xPlanDao;
        this.transformator = transformator;
    }

    /**
     * Retrieves the XPlan GML from the database, transforms the plan to INSPIRE PLU and inserts the transformed plan to
     * the INSPIRE download service for PLU.
     *
     * @param planId
     *            id of the plan to transform and publish, never <code>null</code>
     * @throws Exception
     *             if an exception occurs
     */
    public void transformAndPublish( String planId )
                            throws Exception {
        Path xPlanGml = retrieveXPlan( planId );
        Path inspirePlu = transformator.transformToPlu( xPlanGml );
        FeatureCollection featureCollection = parseFeatureCollection( inspirePlu );
        xPlanDao.insertInspirePlu( featureCollection );
    }

    private FeatureCollection parseFeatureCollection( Path inspirePlu )
                            throws Exception {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        try (FileInputStream stream = new FileInputStream( inspirePlu.toFile() )) {
            XMLStreamReader xmlStream = xmlInputFactory.createXMLStreamReader( stream );
            GMLStreamReader gmlReader = GMLInputFactory.createGMLStreamReader( GML_32, xmlStream );
            return gmlReader.readFeatureCollection();
        } catch ( Exception e ) {
            LOG.error( "Could not parse INSPIRE PLU dataset as feature collection", e );
            throw new Exception( "Could not parse INSPIRE PLU dataset as feature collection" );
        }
    }

    private Path retrieveXPlan( String planId )
                            throws Exception {
        try {
            InputStream sourceStream = xPlanDao.retrieveXPlanArtefact( planId );
            Path sourceFile = Files.createTempFile( "xplanGmlSource", ".xml" );
            FileOutputStream sourceFileStream = new FileOutputStream( sourceFile.toFile() );
            IOUtils.copy( sourceStream, sourceFileStream );
            sourceStream.close();
            sourceFileStream.close();
            return sourceFile;
        } catch ( Exception e ) {
            LOG.error( "Could not retrieve XPlan GML from database", e );
            throw new Exception( "Could not retrieve XPlan GML from database" );
        }
    }

}
