package de.latlon.xplan.validator.web.server.service;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.ValidatorException;
import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Access to plan archive from session and filesystem
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class PlanArchiveManager {

    private static final Logger LOG = LoggerFactory.getLogger( PlanArchiveManager.class );

    private static final Path UPLOAD_FOLDER = Paths.get( System.getProperty( "java.io.tmpdir" ), "xplanvalidator" );

    private static final String LOCAL_PLAN_ATTRIBUTE = "localPlan";

    public PlanArchiveManager() {
        if ( !Files.exists( UPLOAD_FOLDER ) ) {
            try {
                Files.createDirectory( UPLOAD_FOLDER );
            } catch ( IOException e ) {
                LOG.error( "Could not create tmp directory " + UPLOAD_FOLDER + " to upload files." );
            }
        }
    }

    XPlan readPlanFromSession( HttpSession session )
                            throws ValidatorException {
        Object localPlan = session.getAttribute( LOCAL_PLAN_ATTRIBUTE );
        if ( localPlan != null )
            return (XPlan) localPlan;
        throw new ValidatorException( "Could not find a plan to validate!" );
    }

    void writePlanToSession( HttpSession session, XPlan plan ) {
        session.setAttribute( LOCAL_PLAN_ATTRIBUTE, plan );
    }

    File retrieveXPlanArchiveFromFileSystem( XPlan plan )
                            throws IOException {
        String planId = plan.getId();
        Iterator<Path> paths = Files.find( UPLOAD_FOLDER, 1,
                                           ( path, basicFileAttributes ) -> Files.isRegularFile( path )
                                                                            && path.getFileName().toString().startsWith(
                                                                   planId ) ).iterator();
        if ( paths.hasNext() )
            return paths.next().toFile();
        throw new IllegalArgumentException( "Could not find plan for id " + planId );
    }

    void writeXPlanArchiveToFileSystem( FileItem uploadedFileItem, XPlan plan )
                            throws Exception {
        String suffix = parseSuffix( uploadedFileItem );
        Path uploadedFile = UPLOAD_FOLDER.resolve( plan.getId() + suffix );
        try (InputStream uploadedFileItemInputStream = uploadedFileItem.getInputStream()) {
            Files.copy( uploadedFileItemInputStream, uploadedFile );
            LOG.debug( "File was written to {}", uploadedFile );
        }
    }

    File createReportDirectory( String planUuid )
                            throws IOException {
        Path reportDirectory = UPLOAD_FOLDER.resolve( planUuid );
        if ( !Files.exists( reportDirectory ) )
            Files.createDirectory( reportDirectory );
        return reportDirectory.toFile();
    }

    private String parseSuffix( FileItem uploadedFileItem ) {
        String fileName = uploadedFileItem.getName();
        int suffixStart = fileName.lastIndexOf( "." );
        if ( suffixStart < 0 )
            return ".xml";
        return fileName.substring( suffixStart );
    }

}