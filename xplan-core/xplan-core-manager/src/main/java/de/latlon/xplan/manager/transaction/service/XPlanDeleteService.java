/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.manager.transaction.service;

import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.storage.StorageCleanUpManager;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.springframework.context.ApplicationEventPublisher;

import javax.transaction.Transactional;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class XPlanDeleteService {

	private final XPlanManagerDao xPlanDao;

	private final StorageCleanUpManager storageCleanUpManager;

	private final ApplicationEventPublisher applicationEventPublisher;

	public XPlanDeleteService(XPlanManagerDao xPlanDao, StorageCleanUpManager storageCleanUpManager,
			ApplicationEventPublisher applicationEventPublisher) {
		this.xPlanDao = xPlanDao;
		this.storageCleanUpManager = storageCleanUpManager;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Transactional(rollbackOn = Exception.class)
	public void deletePlan(String planId) throws Exception {
		xPlanDao.deletePlan(planId);
		deleteFromStorage(planId);
	}

	private void deleteFromStorage(String planId) throws StorageException {
		StorageEvent storageEvent = new StorageEvent();
		try {
			storageCleanUpManager.deleteAll(planId, storageEvent);
		}
		finally {
			applicationEventPublisher.publishEvent(storageEvent);
		}
	}

}
