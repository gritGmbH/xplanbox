package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanId;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanToEdit;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class EditHandler {

    private static final Logger LOG = getLogger( EditHandler.class );

    @Autowired
    protected XPlanManager manager;

    public XPlan findPlanById( String planId )
                    throws Exception {
        LOG.info( "Find plan by Id '{}'", planId );
        int id = Integer.parseInt( planId );
        return findPlanById( id );
    }

    /**
     * Stores the passed content as tmp file with the passed filename.
     *
     * @param content
     *                 may be <code>null</code>
     * @param fileMetadata
     *                 name of the file, never <code>null</code> if content is <code>not null</code>
     * @return the file, <code>null</code> if content is <code>null</code>
     * @throws IOException
     */
    public File storeAsFile( InputStream content, FormDataContentDisposition fileMetadata )
                    throws IOException {
        if ( content == null )
            return null;
        java.nio.file.Path tmpDir = Files.createTempDirectory( "postDokument" );
        java.nio.file.Path targetFile = tmpDir.resolve( fileMetadata.getFileName() );
        Files.copy( content, targetFile );
        content.close();
        return targetFile.toFile();
    }

    private XPlan findPlanById( int id )
                    throws Exception {
        XPlan xPlanById = manager.getXPlanById( id );
        if ( xPlanById == null ) {
            throw new InvalidPlanId( id );
        }
        checkIfPlanIsSupported( xPlanById );
        return xPlanById;
    }

    private void checkIfPlanIsSupported( XPlan xPlanById )
                    throws InvalidPlanToEdit {
        String version = xPlanById.getVersion();
        XPlanVersion xPlanVersion = XPlanVersion.valueOf( version );
        switch ( xPlanVersion ) {
        case XPLAN_3:
        case XPLAN_40:
            throw new InvalidPlanToEdit(
                            String.format( "Plan with ID %s can not be edited, because the version (%s) is not supported. Supported versions: 4.1 and heigher",
                                           xPlanById.getId(), xPlanVersion ) );
        }
        String type = xPlanById.getType();
        XPlanType xPlanType = XPlanType.valueOf( type );
        switch ( xPlanType ) {
        case FP_Plan:
        case RP_Plan:
        case LP_Plan:
        case SO_Plan:
            throw new InvalidPlanToEdit(
                            String.format( "Plan with ID %s can not be edited, because the type (%s) is not supported. Supported types: BP_Plan",
                                           xPlanById.getId(), xPlanType ) );
        }
    }
}
