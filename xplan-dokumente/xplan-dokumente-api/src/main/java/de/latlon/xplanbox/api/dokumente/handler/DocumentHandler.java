/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumenteAPI
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
package de.latlon.xplanbox.api.dokumente.handler;

import de.latlon.xplan.core.manager.db.repository.PlanRepository;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import de.latlon.xplanbox.api.commons.exception.InvalidPlanId;
import de.latlon.xplanbox.api.commons.exception.InvalidPlanIdSyntax;
import de.latlon.xplanbox.api.dokumente.exception.InvalidDocument;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeader;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeaderWithStream;
import de.latlon.xplanbox.api.dokumente.service.DocumentService;
import de.latlon.xplanbox.api.dokumente.v1.model.Document;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Component
@Singleton
public class DocumentHandler {

	private static final Logger LOG = LoggerFactory.getLogger(DocumentHandler.class);

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private DocumentService documentService;

	public List<Document> listDocuments(String planId) throws InvalidPlanIdSyntax, InvalidPlanId {
		int planIdAsInt = checkPlanIdAndConvertIdToInt(planId);
		LOG.debug("List documents of plan with id {}.", planIdAsInt);
		return documentService.listDocuments(planIdAsInt);
	}

	public DocumentHeader headDocument(String planId, String fileName)
			throws InvalidPlanIdSyntax, InvalidPlanId, InvalidDocument, StorageException {
		int planIdAsInt = checkPlanIdAndConvertIdToInt(planId);
		LOG.debug("Retrieve header of document with filename {} of plan with id {}.",
				StringUtils.normalizeSpace(fileName), planIdAsInt);
		return documentService.retrieveHeader(planIdAsInt, fileName);
	}

	public DocumentHeaderWithStream getDocument(String planId, String fileName)
			throws InvalidPlanIdSyntax, InvalidPlanId, InvalidDocument, StorageException {
		int planIdAsInt = checkPlanIdAndConvertIdToInt(planId);
		LOG.debug("Retrieve document with filename {} of plan with id {}.", StringUtils.normalizeSpace(fileName),
				planIdAsInt);
		return documentService.retrieveDocumentAndHeader(planIdAsInt, fileName);
	}

	private int checkPlanIdAndConvertIdToInt(String planId) throws InvalidPlanIdSyntax, InvalidPlanId {
		try {
			int planIdAsInt = Integer.parseInt(planId);
			if (!planRepository.existsPlanById(planIdAsInt)) {
				throw new InvalidPlanId(planId);
			}
			return planIdAsInt;
		}
		catch (NumberFormatException e) {
			throw new InvalidPlanIdSyntax(planId);
		}
	}

}
