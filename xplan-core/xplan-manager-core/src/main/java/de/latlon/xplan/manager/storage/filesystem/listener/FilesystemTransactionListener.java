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
package de.latlon.xplan.manager.wmsconfig.listener;

import de.latlon.xplan.manager.storage.StorageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.springframework.transaction.event.TransactionPhase.AFTER_ROLLBACK;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Component
public class FilesystemTransactionListener {

	private static final Logger LOG = LoggerFactory.getLogger(FilesystemTransactionListener.class);

	@TransactionalEventListener(phase = AFTER_ROLLBACK)
	public void rollbackFilesystem(StorageEvent storageEvent) {
		List<Path> insertedPaths = storageEvent.getInsertedPaths();
		insertedPaths.forEach(insertedPath -> {
			try {
				Files.delete(insertedPath);
			}
			catch (IOException e) {
				LOG.warn("Could not rollback created file {}.", insertedPath);
			}
		});

		Map<Path, byte[]> deletedPathsToObjects = storageEvent.getDeletedPathsToObjects();
		deletedPathsToObjects.entrySet().forEach(deletedPathAndObject -> {
			Path path = deletedPathAndObject.getKey();
			try {
				Files.write(path, deletedPathAndObject.getValue());
			}
			catch (IOException e) {
				LOG.warn("Could not rollback deleted file {}.", path);
			}
		});
	}

}
