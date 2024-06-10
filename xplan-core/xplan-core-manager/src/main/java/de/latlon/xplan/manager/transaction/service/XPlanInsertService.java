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

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.document.XPlanDocumentManager;
import de.latlon.xplan.manager.transaction.PlanImportData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.transaction.Transactional;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class XPlanInsertService {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanInsertService.class);

	private final XPlanManagerDao xplanDao;

	private final XPlanDocumentManager xPlanDocumentManager;

	public XPlanInsertService(XPlanManagerDao xplanDao, XPlanDocumentManager xPlanDocumentManager) {
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

	private PlanImportData doImport(PlanImportData planToImport) throws Exception {
		XPlanArchive archive = planToImport.getxPlanArchive();
		XPlanFeatureCollection xPlanFeatureCollection = planToImport.getxPlanFC();
		PlanStatus planStatus = planToImport.getPlanStatus();
		ExternalReferenceInfo externalReferenceInfo = parseExternalReferencesInfo(archive, xPlanFeatureCollection);
		int planId = xplanDao.insert(archive, xPlanFeatureCollection, planStatus, planToImport.getSortDate(),
				planToImport.getInternalId());
		insertDocuments(planId, externalReferenceInfo, archive);
		LOG.info("XPlanArchiv wurde erfolgreich importiert. Zugewiesene Id: " + planId);
		planToImport.setPlanId(planId);
		return planToImport;
	}

	private void insertDocuments(int planId, ExternalReferenceInfo externalReferenceInfo, XPlanArchive archive)
			throws StorageException {
		if (xPlanDocumentManager != null) {
			xPlanDocumentManager.importDocuments(planId, externalReferenceInfo, archive);
		}
	}

	private ExternalReferenceInfo parseExternalReferencesInfo(XPlanArchive archive,
			XPlanFeatureCollection xPlanFeatureCollection) {
		ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();
		return externalReferenceScanner.scan(xPlanFeatureCollection.getFeatures(), archive.getVersion());
	}

}
