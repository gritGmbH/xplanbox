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
package de.latlon.xplan.manager.wmsconfig.raster.storage;

import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.RasterEvaluation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FileSystemStorage implements RasterStorage {

	private static final Logger LOG = LoggerFactory.getLogger(FileSystemStorage.class);

	private final Path dataDirectory;

	private final RasterEvaluation rasterEvaluation;

	public FileSystemStorage(Path dataDirectory, RasterEvaluation rasterEvaluation) {
		this.dataDirectory = dataDirectory;
		this.rasterEvaluation = rasterEvaluation;
	}

	@Override
	public String addRasterFile(int planId, String entryName, XPlanArchiveContentAccess archive,
			StorageEvent storageEvent) throws IOException {
		if (rasterEvaluation.isSupportedFile(entryName)) {
			String rasterFileName = createFileName(planId, entryName);
			Path target = createTargetFile(rasterFileName);
			Files.copy(archive.retrieveInputStreamFor(entryName), target);
			storageEvent.addStoredPath(target);
			return rasterFileName;
		}
		return null;
	}

	@Override
	public void deleteRasterFile(int planId, String rasterId, StorageEvent storageEvent) throws IOException {
		final String rasterLayerFileName = planId + "_" + rasterId;
		deleteFilesWithPrefix((path, basicFileAttributes) -> {
			String fileName = path.getFileName().toString();
			String nameWithoutPrefix = fileName;
			int lastIndexOfDot = fileName.lastIndexOf(".");
			if (lastIndexOfDot > 0)
				nameWithoutPrefix = fileName.substring(0, lastIndexOfDot);
			return rasterLayerFileName.startsWith(nameWithoutPrefix);
		}, storageEvent);
	}

	protected String createFileName(int planId, String fileName) {
		return planId + "_" + fileName;
	}

	protected Path createTargetFile(String newFileName) {
		return dataDirectory.resolve(newFileName);
	}

	private void deleteFilesWithPrefix(BiPredicate<Path, BasicFileAttributes> filenameFilter, StorageEvent storageEvent)
			throws IOException {
		if (!Files.exists(dataDirectory) || !Files.isDirectory(dataDirectory)) {
			return;
		}
		Stream<Path> filesToDelete = Files.find(dataDirectory, Integer.MAX_VALUE, filenameFilter);
		filesToDelete.forEach(file -> {
			LOG.info("- Entferne Raster-Datei '" + file + "'...");
			try {
				byte[] bytes = Files.readAllBytes(file);
				Files.delete(file);
				storageEvent.addDeletedPath(file, bytes);
				LOG.info("OK");
			}
			catch (Exception e) {
				LOG.error("Fehler: " + e.getMessage());
				LOG.debug("Fehler: ", e);
			}
		});
	}

}
