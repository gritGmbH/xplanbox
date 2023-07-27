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
package de.latlon.xplan.manager.document;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;

import java.nio.file.Path;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public interface DocumentStorage {

	/**
	 * Imports all passed documentsToAdd in the storage.
	 * @param planId the id of the plan, never <code>null</code>
	 * @param xPlanArchive containing the documents, never <code>null</code>
	 * @param documentsToAdd names of the documents to add, never <code>null</code>
	 * @param storageEvent to store keys of imported documents, never <code>null</code>
	 * @return the keys (filenames) of the imported documents, may be empty but never
	 * <code>null</code>
	 * @throws StorageException if the documents could not be stored
	 */
	List<String> importDocuments(int planId, XPlanArchive xPlanArchive, List<String> documentsToAdd,
			StorageEvent storageEvent) throws StorageException;

	void importDocument(int planId, String referenceToAdd, Path fileToAdd, StorageEvent storageEvent)
			throws StorageException;

	void deleteDocument(int planId, String referenzUrl, StorageEvent storageEvent) throws StorageException;

}
