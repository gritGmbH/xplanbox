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
package de.latlon.xplan.manager.document.s3.listener;

import de.latlon.xplan.manager.document.DocumentStorageEvent;
import de.latlon.xplan.manager.document.s3.S3DocumentStorage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.springframework.transaction.event.TransactionPhase.AFTER_ROLLBACK;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
@Component
public class S3DocumentTransactionListener {

	private static final Logger LOG = LoggerFactory.getLogger(S3DocumentTransactionListener.class);

	private final S3DocumentStorage s3DocumentStorage;

	public S3DocumentTransactionListener(S3DocumentStorage s3DocumentStorage) {
		this.s3DocumentStorage = s3DocumentStorage;
	}

	@TransactionalEventListener(phase = AFTER_ROLLBACK)
	public void rollbackDocumentS3(DocumentStorageEvent documentStorageEvent) {
		List<String> insertedKeys = documentStorageEvent.getInsertedKeys();
		insertedKeys.forEach(insertedKey -> s3DocumentStorage.deleteObject(insertedKey));

		Map<String, InputStream> deletedKeysToObjects = documentStorageEvent.getDeletedKeysToObjects();
		deletedKeysToObjects.entrySet().forEach(deletedKeyAndObject -> {
			try {
				s3DocumentStorage.insertObject(deletedKeyAndObject.getKey(), deletedKeyAndObject.getValue());
			}
			catch (StorageException e) {
				LOG.warn("Could not rollback deleted document with id {} from S3 Storage.",
						deletedKeyAndObject.getKey());
			}
		});
	}

}
