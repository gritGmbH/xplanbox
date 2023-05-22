/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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

import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.storage.StorageCleanUpManager;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import javax.transaction.Transactional;
import java.io.IOException;

import static java.lang.Integer.parseInt;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanDeleteManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanDeleteManager.class);

	private final XPlanDao xPlanDao;

	private final XPlanRasterManager xPlanRasterManager;

	private final StorageCleanUpManager storageCleanUpManager;

	private final WorkspaceReloader workspaceReloader;

	private final ApplicationEventPublisher applicationEventPublisher;

	public XPlanDeleteManager(XPlanDao xPlanDao, XPlanRasterManager xPlanRasterManager,
			StorageCleanUpManager storageCleanUpManager, WorkspaceReloader workspaceReloader,
			ApplicationEventPublisher applicationEventPublisher) {
		this.xPlanDao = xPlanDao;
		this.xPlanRasterManager = xPlanRasterManager;
		this.storageCleanUpManager = storageCleanUpManager;
		this.workspaceReloader = workspaceReloader;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	/**
	 * Removes the plan with the given ID from the XPlanManager. Includes: Database and
	 * raster data. WMS configuration is not removed.
	 * @param planId the plan id to delete should be used.
	 * @throws Exception
	 */
	@Transactional(rollbackOn = Exception.class)
	public void delete(String planId) throws Exception {
		xPlanDao.deletePlan(planId);
		xPlanRasterManager.removeRasterLayers(planId);
		deleteFromStorage(planId);
		reloadWorkspace(planId);
		LOG.info("XPlanArchiv mit Id {} wurde gelöscht", planId);
	}

	private void deleteFromStorage(String planId) throws IOException, StorageException {
		StorageEvent storageEvent = new StorageEvent();
		try {
			storageCleanUpManager.deleteAll(planId, storageEvent);
		}
		finally {
			applicationEventPublisher.publishEvent(storageEvent);
		}
	}

	private void reloadWorkspace(String planId) {
		if (workspaceReloader != null) {
			workspaceReloader.reloadWorkspace(parseInt(planId));
		}
	}

}
