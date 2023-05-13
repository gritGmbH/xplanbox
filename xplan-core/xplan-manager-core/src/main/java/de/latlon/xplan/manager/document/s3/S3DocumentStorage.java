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
package de.latlon.xplan.manager.document.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.manager.document.DocumentStorage;
import de.latlon.xplan.manager.document.DocumentStorageEvent;
import de.latlon.xplan.manager.storage.s3.S3Storage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a {@link DocumentStorage}, using AWS S3 as a storage for documents.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class S3DocumentStorage extends S3Storage implements DocumentStorage {

	public S3DocumentStorage(AmazonS3 client, String bucketName) {
		super(client, bucketName);
	}

	@Override
	public List<String> importDocuments(int planId, XPlanArchive xPlanArchive, List<String> documentsToAdd,
			DocumentStorageEvent documentStorageEvent) throws StorageException {
		List<String> keys = new ArrayList<>();
		for (String documentToAdd : documentsToAdd) {
			String key = insertObject(planId, documentToAdd, xPlanArchive);
			documentStorageEvent.addInsertedKey(key);
			keys.add(key);
		}
		return keys;
	}

	@Override
	public void importDocument(int planId, String referenceToAdd, Path fileToAdd,
			DocumentStorageEvent documentStorageEvent) throws StorageException {
		String key = createKey(planId, referenceToAdd);
		insertObject(key, fileToAdd);
		documentStorageEvent.addInsertedKey(key);
	}

	@Override
	public void deleteDocument(int planId, String referenzUrl, DocumentStorageEvent documentStorageEvent)
			throws StorageException {
		if (referenzUrl != null) {
			String key = createKey(planId, referenzUrl);
			S3Object object = getObject(key);
			documentStorageEvent.addRemovedDocument(object.getKey(), object.getObjectContent());
			deleteObject(key);
		}
	}

}
