package de.latlon.xplan.manager.transaction.service;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.document.XPlanDocumentManager;
import de.latlon.xplan.manager.transaction.PlanImportData;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class XPlanInsertService {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanInsertService.class);

	private final XPlanDao xplanDao;

	private final XPlanDocumentManager xPlanDocumentManager;

	public XPlanInsertService(XPlanDao xplanDao, XPlanDocumentManager xPlanDocumentManager) {
		this.xplanDao = xplanDao;
		this.xPlanDocumentManager = xPlanDocumentManager;
	}

	@Transactional(rollbackOn = Exception.class)
	public List<PlanImportData> importPlans(List<PlanImportData> plansToImport) throws Exception {
		for (PlanImportData planToImport : plansToImport) {
			doImport(planToImport);
		}
		return plansToImport;
	}

	@Transactional(rollbackOn = Exception.class)
	public PlanImportData importPlan(PlanImportData planToImport) throws Exception {
		return doImport(planToImport);
	}

	private PlanImportData doImport(PlanImportData planToImport) throws Exception {
		XPlanArchive archive = planToImport.getxPlanArchive();
		XPlanFeatureCollection xPlanFeatureCollection = planToImport.getxPlanFC();
		PlanStatus planStatus = planToImport.getPlanStatus();
		AdditionalPlanData xPlanMetadata = planToImport.getxPlanMetadata();
		int planId = xplanDao.insert(archive, xPlanFeatureCollection, planToImport.getSynFc(), planStatus,
				xPlanMetadata.getStartDateTime(), xPlanMetadata.getEndDateTime(), planToImport.getSortDate(), null);
		insertDocuments(planId, xPlanFeatureCollection, archive);
		LOG.info("XPlanArchiv wurde erfolgreich importiert. Zugewiesene Id: " + planId);
		planToImport.setPlanId(planId);
		return planToImport;
	}

	private void insertDocuments(int planId, XPlanFeatureCollection xPlanInstance, XPlanArchive archive)
			throws StorageException {
		if (xPlanDocumentManager != null) {
			xPlanDocumentManager.importDocuments(planId, xPlanInstance.getFeatures(), archive);
		}
	}

}
