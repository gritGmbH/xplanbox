package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceManager;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.manager.workspace.WorkspaceReloaderConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.findWorkspaceDirectory;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanDeleteManager {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanDeleteManager.class );

    private final XPlanDao xPlanDao;

    private final XPlanRasterManager xPlanRasterManager;

    private final WorkspaceReloader workspaceReloader;

    private final ManagerConfiguration managerConfiguration;

    public XPlanDeleteManager( XPlanDao xPlanDao, XPlanRasterManager xPlanRasterManager,
                               WorkspaceReloader workspaceReloader, ManagerConfiguration managerConfiguration ) {
        this.xPlanDao = xPlanDao;
        this.xPlanRasterManager = xPlanRasterManager;
        this.workspaceReloader = workspaceReloader;
        this.managerConfiguration = managerConfiguration;
    }

    /**
     * Removes the plan with the given ID from the XPlanManager. Includes: Database and raster data. WMS configuration is not removed.
     *
     * @param planId
     */
    public void delete( String planId )
                            throws Exception {
        delete( planId, false, null );
    }

    /**
     * @param planId
     *                         the plan id to delete
     * @param removeWMSConfig
     *                         <code>true</code> if the WMS configuration for the plan to delete should be removed,
     *                         <code>false</code> otherwise
     * @param workspaceFolder
     *                         workspace folder, may be <code>null</code> if default path should be used.
     * @throws Exception
     */
    public void delete( String planId, boolean removeWMSConfig, File workspaceFolder )
                            throws Exception {
        xPlanDao.deletePlan( planId );
        xPlanRasterManager.removeRasterLayers( planId );
        if ( removeWMSConfig ) {
            new WmsWorkspaceManager( findWorkspaceDirectory( workspaceFolder ) ).deleteWmsWorkspaceFilesForId( planId );
        }
        reloadWorkspace();
        LOG.info( "XPlan-Archiv " + planId + " wurde gelöscht" );
    }

    private void reloadWorkspace() {
        if ( workspaceReloader != null ) {
            WorkspaceReloaderConfiguration configuration = managerConfiguration.getWorkspaceReloaderConfiguration();
            workspaceReloader.reloadWorkspace( configuration );
        }
    }

}