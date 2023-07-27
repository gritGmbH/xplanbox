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
package de.latlon.xplan.manager.storage.s3.listener;

import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.storage.s3.S3Object;
import de.latlon.xplan.manager.storage.s3.S3Storage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

import static org.springframework.transaction.event.TransactionPhase.AFTER_ROLLBACK;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Component
public class S3TransactionListener {

	private static final Logger LOG = LoggerFactory.getLogger(S3TransactionListener.class);

	private final S3Storage s3Storage;

	public S3TransactionListener(@Qualifier("rollbackStorage") S3Storage s3Storage) {
		this.s3Storage = s3Storage;
	}

	@TransactionalEventListener(phase = AFTER_ROLLBACK)
	public void rollbackDocumentS3(StorageEvent storageEvent) {
		List<String> insertedKeys = storageEvent.getInsertedKeys();
		insertedKeys.forEach(insertedKey -> s3Storage.deleteObjects(insertedKey));

		List<S3Object> deletedKeysToObjects = storageEvent.getDeletedS3Objects();
		deletedKeysToObjects.forEach(deletedObject -> {
			try {
				s3Storage.insertObject(deletedObject);
			}
			catch (StorageException e) {
				LOG.warn("Could not rollback deleted document with id {} from S3 Storage.",
						deletedObject.getS3Metadata().getKey());
			}
		});
	}

}
