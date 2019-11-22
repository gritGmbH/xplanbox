package de.latlon.xplan.validator.web.server.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.web.client.service.MapPreviewConfigService;
import de.latlon.xplan.validator.web.shared.MapPreviewException;
import de.latlon.xplan.validator.web.shared.MapPreviewMetadata;
import de.latlon.xplan.validator.wms.MapPreviewCreationException;
import de.latlon.xplan.validator.wms.MapPreviewManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnServletContext;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RemoteServiceRelativePath("mappreviewconfig")
public class ValidatorMapPreviewConfigService extends RemoteServiceServlet implements MapPreviewConfigService {

    private static final Logger LOG = LoggerFactory.getLogger( ValidatorMapPreviewConfigService.class );

    private final PlanArchiveManager planArchiveManager = new PlanArchiveManager();

    protected HttpSession session;

    @Autowired
    private MapPreviewManager mapPreviewManager;

    @Override
    public void init( ServletConfig config )
                            throws ServletException {
        super.init( config );
        processInjectionBasedOnServletContext( this, config.getServletContext() );
    }

    @Override
    public void service( final HttpServletRequest request, HttpServletResponse response )
                            throws ServletException, IOException {
        session = request.getSession( true );
        super.service( request, response );
    }

    @Override
    public boolean isMapPreviewAvaialable()
                            throws MapPreviewException {
        return mapPreviewManager != null;
    }

    @Override
    public MapPreviewMetadata createMapPreviewConfig()
                            throws MapPreviewException {
        if ( mapPreviewManager == null )
            throw new MapPreviewException( "Map preview manager is not available" );
        try {
            XPlan planToVerify = planArchiveManager.readPlanFromSession( session );
            String planUuid = planToVerify.getId();
            File archive = planArchiveManager.retrieveXPlanArchiveFromFileSystem( planToVerify );

            return mapPreviewManager.createConfigurations( planUuid, archive );
        } catch ( ValidatorException | IOException | MapPreviewCreationException e ) {
            LOG.error( "An exception occurred during validation", e );
            throw new MapPreviewException( e.getMessage() );
        }
    }

}
