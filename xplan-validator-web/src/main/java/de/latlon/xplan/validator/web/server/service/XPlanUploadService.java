package de.latlon.xplan.validator.web.server.service;

import static java.lang.String.format;
import static java.util.UUID.randomUUID;
import static org.apache.commons.fileupload.FileUploadBase.isMultipartContent;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.latlon.xplan.manager.web.shared.XPlan;

/**
 * Stores an uploaded zip file into a tmp directory
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
@SuppressWarnings("serial")
public class XPlanUploadService extends RemoteServiceServlet {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanUploadService.class );

    private static final ResourceBundle bundle = ResourceBundle.getBundle( "de.latlon.xplan.validator.web.client.XPlanValidatorWebMessages" );

    private static final int FIRST = 0;

    private final PlanArchiveManager planArchiveManager = new PlanArchiveManager();

    @Override
    protected void service( HttpServletRequest request, HttpServletResponse response )
                    throws ServletException, IOException {
        boolean isMultiPart = isMultipartContent( new ServletRequestContext( request ) );
        if ( !isMultiPart )
            super.service( request, response );
        else {
            handleMultiPart( request, response );
        }
    }

    private void handleMultiPart( HttpServletRequest request, HttpServletResponse response ) {
        ServletFileUpload upload = new ServletFileUpload( new DiskFileItemFactory() );
        try {
            FileItem uploadedFileItem = retrieveFirstUploadedFile( request, upload );
            if ( checkUploadConditions( uploadedFileItem ) ) {
                String fileName = uploadedFileItem.getName();
                XPlan plan = createPlan( uploadedFileItem, fileName );

                planArchiveManager.writePlanToSession( request.getSession( true ), plan );
                planArchiveManager.writeXPlanArchiveToFileSystem( uploadedFileItem, plan );
                populateResponse( response, uploadedFileItem, fileName );
                LOG.debug( "Plan was successfully loaded." );
            } else {
                super.service( request, response );
            }
        } catch ( Exception e ) {
            LOG.error( "An error occured during uploadind a plan!", e );
        }
    }

    private FileItem retrieveFirstUploadedFile( HttpServletRequest request, ServletFileUpload upload )
                    throws FileUploadException {
        return upload.parseRequest( request ).get( FIRST );
    }

    private XPlan createPlan( FileItem uploadedFileItem, String fileName ) {
        return new XPlan( fileName, randomUUID().toString(), uploadedFileItem.getContentType() );
    }

    private void populateResponse( HttpServletResponse response, FileItem uploadedFileItem, String fileName )
                    throws IOException {
        String message = format( bundle.getString( "loadedPlan" ), fileName, uploadedFileItem.getSize() );

        response.setStatus( HttpServletResponse.SC_CREATED );
        response.getWriter().println( message );
        response.flushBuffer();
    }

    private boolean checkUploadConditions( FileItem uploadedFileItem ) {
        return uploadedFileItem != null && uploadedFileItem.getFieldName() != null
               && "uploadPlanItem".equalsIgnoreCase( uploadedFileItem.getFieldName() );
    }

}