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
package de.latlon.xplanbox.api.dokumente.service;

import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import de.latlon.xplanbox.api.dokumente.exception.InvalidDocument;
import de.latlon.xplanbox.api.dokumente.v1.model.Document;

import java.util.List;

/**
 * Interface to access documents.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public interface DocumentService {

	/**
	 * @param planId id of the plan
	 * @return the list of all documents of the plan with the passed planId
	 */
	List<Document> listDocuments(int planId);

	/**
	 * @param planId id of the plan
	 * @param fileName, name of the file to return header information, never
	 * <code>null</code>
	 * @return the header information of the document
	 * @throws InvalidDocument if a document with the passed fileName for the plan with
	 * the passed planId does not exist
	 * @throws StorageException if the document is not available
	 */
	DocumentHeader retrieveHeader(int planId, String fileName) throws InvalidDocument, StorageException;

	/**
	 * @param planId id of the plan
	 * @param fileName, name of the file to return header information, never
	 * <code>null</code>
	 * @return the document and header information of the document
	 * @throws InvalidDocument if a document with the passed fileName for the plan with
	 * the passed planId does not exist
	 * @throws StorageException if the document is not available
	 */
	DocumentHeaderWithStream retrieveDocumentAndHeader(int planId, String fileName)
			throws InvalidDocument, StorageException;

}
