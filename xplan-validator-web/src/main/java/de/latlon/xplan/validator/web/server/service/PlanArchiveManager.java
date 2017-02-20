package de.latlon.xplan.validator.web.server.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.ValidatorException;

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

    private static final File UPLOAD_FOLDER = new File( System.getProperty( "java.io.tmpdir" ), "xplanvalidator" );

    private static final String LOCAL_PLAN_ATTRIBUTE = "localPlan";

    public PlanArchiveManager() {
        if ( !UPLOAD_FOLDER.exists() )
            UPLOAD_FOLDER.mkdir();
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

    File retrieveXPlanArchiveFromFileSystem( XPlan planToVerify )
                            throws IOException {
        String fileToBeValidated = planToVerify.getId() + ".zip";
        return new File( UPLOAD_FOLDER, fileToBeValidated );
    }

    void writeXPlanArchiveToFileSystem( FileItem uploadedFileItem, XPlan plan )
                            throws Exception {
        File uploadedFile = new File( UPLOAD_FOLDER, plan.getId() + ".zip" );
        uploadedFileItem.write( uploadedFile );
        LOG.debug( "File was written to {}", uploadedFile );
    }

    File createReportDirectory( String planUuid ) {
        File reportDirectory = createPlanDirectory( planUuid );
        reportDirectory.mkdir();
        return reportDirectory;
    }

    private File createPlanDirectory( String planUuid ) {
        return new File( UPLOAD_FOLDER, planUuid );
    }

}