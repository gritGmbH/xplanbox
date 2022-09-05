/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Integer.parseInt;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanDeleteManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanDeleteManager.class);

	private final XPlanDao xPlanDao;

	private final XPlanRasterManager xPlanRasterManager;

	private final WorkspaceReloader workspaceReloader;

	private final ManagerConfiguration managerConfiguration;

	public XPlanDeleteManager(XPlanDao xPlanDao, XPlanRasterManager xPlanRasterManager,
			WorkspaceReloader workspaceReloader, ManagerConfiguration managerConfiguration) {
		this.xPlanDao = xPlanDao;
		this.xPlanRasterManager = xPlanRasterManager;
		this.workspaceReloader = workspaceReloader;
		this.managerConfiguration = managerConfiguration;
	}

	/**
	 * Removes the plan with the given ID from the XPlanManager. Includes: Database and
	 * raster data. WMS configuration is not removed.
	 * @param planId the plan id to delete should be used.
	 * @throws Exception
	 */
	public void delete(String planId) throws Exception {
		xPlanDao.deletePlan(planId);
		xPlanRasterManager.removeRasterLayers(planId);
		reloadWorkspace(planId);
		LOG.info("XPlanArchiv mit Id {} wurde gelöscht", planId);
	}

	private void reloadWorkspace(String planId) {
		if (workspaceReloader != null) {
			workspaceReloader.reloadWorkspace(parseInt(planId));
		}
	}

}
