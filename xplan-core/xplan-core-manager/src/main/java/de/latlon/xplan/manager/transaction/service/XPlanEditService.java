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
package de.latlon.xplan.manager.transaction.service;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.document.XPlanDocumentManager;
import de.latlon.xplan.manager.edit.EditedArtefacts;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.deegree.feature.FeatureCollection;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class XPlanEditService {

	private final XPlanManagerDao xplanDao;

	private final XPlanDocumentManager xPlanDocumentManager;

	public XPlanEditService(XPlanManagerDao xplanDao, XPlanDocumentManager xPlanDocumentManager) {
		this.xplanDao = xplanDao;
		this.xPlanDocumentManager = xPlanDocumentManager;
	}

	@Transactional(rollbackOn = Exception.class)
	public void update(XPlan oldXplan, List<File> uploadedArtefacts, int planId, byte[] xPlanGml,
			EditedArtefacts editedArtefacts, XPlanFeatureCollection modifiedPlanFc, FeatureCollection synFc,
			AdditionalPlanData xPlanMetadata, Date sortDate, String internalId) throws Exception {
		xplanDao.update(oldXplan, xPlanMetadata, modifiedPlanFc, synFc, xPlanGml, sortDate, uploadedArtefacts,
				editedArtefacts, internalId);
		updateDocuments(planId, uploadedArtefacts, editedArtefacts);
	}

	@SuppressFBWarnings(value = "PATH_TRAVERSAL_IN")
	private void updateDocuments(int planId, List<File> uploadedArtefacts, EditedArtefacts editedArtefacts)
			throws StorageException {
		if (xPlanDocumentManager != null) {
			List<Path> uploadedArtefactsAsPaths = uploadedArtefacts.stream()
				.map(uploadedArtefact -> Paths.get(uploadedArtefact.toURI()))
				.collect(Collectors.toList());
			xPlanDocumentManager.updateDocuments(planId, uploadedArtefactsAsPaths, editedArtefacts);
		}
	}

}
