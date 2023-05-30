package de.latlon.xplan.manager.transaction.service;

import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.storage.StorageCleanUpManager;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.springframework.context.ApplicationEventPublisher;

import javax.transaction.Transactional;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class XPlanDeleteService {

	private final XPlanDao xPlanDao;

	private final StorageCleanUpManager storageCleanUpManager;

	private final ApplicationEventPublisher applicationEventPublisher;

	public XPlanDeleteService(XPlanDao xPlanDao, StorageCleanUpManager storageCleanUpManager,
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
