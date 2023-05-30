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
package de.latlon.xplan.manager.storage.filesystem;

import de.latlon.xplan.manager.storage.StorageCleanUpManager;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.wmsconfig.raster.storage.FileSystemStorage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class FilesystemStorageCleanUpManager implements StorageCleanUpManager {

	private static final Logger LOG = LoggerFactory.getLogger(FileSystemStorage.class);

	private final Path dataDirectory;

	public FilesystemStorageCleanUpManager(Path dataDirectory) {
		this.dataDirectory = dataDirectory;
	}

	@Override
	public void deleteAll(String id, StorageEvent storageEvent) throws StorageException {
		String prefix = id + "_";
		deleteFilesWithPrefix((path, basicFileAttributes) -> path.getFileName().toString().startsWith(prefix),
				storageEvent);
	}

	private void deleteFilesWithPrefix(BiPredicate<Path, BasicFileAttributes> filenameFilter, StorageEvent storageEvent)
			throws StorageException {
		if (!Files.exists(dataDirectory) || !Files.isDirectory(dataDirectory)) {
			return;
		}
		List<Path> filesToDelete = findFilesToDelete(filenameFilter);
		for (Path file : filesToDelete) {
			LOG.info("- Entferne Raster-Datei '" + file + "'...");
			try {
				byte[] bytes = Files.readAllBytes(file);
				Files.delete(file);
				storageEvent.addDeletedPath(file, bytes);
				LOG.info("OK");
			}
			catch (Exception e) {
				LOG.error("File {} could not be removed from filesystem: {}", file, e.getMessage());
				throw new StorageException("File could not be removed from filesystem.", e);
			}
		}
	}

	private List<Path> findFilesToDelete(BiPredicate<Path, BasicFileAttributes> filenameFilter)
			throws StorageException {
		try {
			return Files.find(dataDirectory, Integer.MAX_VALUE, filenameFilter).collect(Collectors.toList());
		}
		catch (IOException e) {
			throw new StorageException("Files could not be removed from filesystem.", e);
		}
	}

}
