package de.latlon.xplan.manager.transaction.service;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.document.XPlanDocumentManager;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.deegree.feature.FeatureCollection;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class XPlanEditService {

	private final XPlanManagerDao xplanDao;

	private final XPlanDocumentManager xPlanDocumentManager;

	public XPlanEditService(XPlanManagerDao xplanDao, XPlanDocumentManager xPlanDocumentManager) {
		this.xplanDao = xplanDao;
		this.xPlanDocumentManager = xPlanDocumentManager;
	}

	@Transactional(rollbackOn = Exception.class)
	public void update(XPlan oldXplan, XPlanToEdit xPlanToEdit, List<File> uploadedArtefacts, int planId,
			byte[] xPlanGml, ExternalReferenceInfo externalReferenceInfoToUpdate,
			ExternalReferenceInfo externalReferenceInfoToRemove, Map<String, String> addedRefFileNames,
			Set<String> removedRefFileNames, XPlanFeatureCollection modifiedPlanFc, FeatureCollection synFc,
			AdditionalPlanData xPlanMetadata, Date sortDate, String internalId) throws Exception {
		xplanDao.update(oldXplan, xPlanMetadata, modifiedPlanFc, synFc, xPlanGml, xPlanToEdit, sortDate,
				uploadedArtefacts, addedRefFileNames, removedRefFileNames, internalId);
		updateDocuments(planId, uploadedArtefacts, externalReferenceInfoToUpdate, externalReferenceInfoToRemove);
	}

	private void updateDocuments(int planId, List<File> uploadedArtefacts,
			ExternalReferenceInfo externalReferenceInfoToAdd, ExternalReferenceInfo externalReferenceInfoToRemove)
			throws StorageException {
		if (xPlanDocumentManager != null) {
			List<Path> uploadedArtefactsAsPaths = uploadedArtefacts.stream()
					.map(uploadedArtefact -> Paths.get(uploadedArtefact.toURI())).collect(Collectors.toList());
			xPlanDocumentManager.updateDocuments(planId, uploadedArtefactsAsPaths,
					externalReferenceInfoToAdd.getNonRasterRefs(), externalReferenceInfoToRemove.getNonRasterRefs());
		}
	}

}
