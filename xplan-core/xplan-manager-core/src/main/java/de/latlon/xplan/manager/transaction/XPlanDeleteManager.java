/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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
        LOG.info( "XPlanArchiv mit Id {} wurde gelöscht", planId );
    }

    private void reloadWorkspace() {
        if ( workspaceReloader != null ) {
            WorkspaceReloaderConfiguration configuration = managerConfiguration.getWorkspaceReloaderConfiguration();
            workspaceReloader.reloadWorkspace( configuration );
        }
    }

}
