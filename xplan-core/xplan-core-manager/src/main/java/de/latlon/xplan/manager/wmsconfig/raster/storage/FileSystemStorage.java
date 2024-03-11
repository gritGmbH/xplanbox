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
import de.latlon.xplan.manager.storage.filesystem.DeegreeRasterCacheCleaner;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.RasterEvaluation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FileSystemStorage implements RasterStorage {

	private static final Logger LOG = LoggerFactory.getLogger(FileSystemStorage.class);

	private final Path dataDirectory;

	private final RasterEvaluation rasterEvaluation;

	private final DeegreeRasterCacheCleaner deegreeRasterCacheCleaner;

	public FileSystemStorage(Path dataDirectory, RasterEvaluation rasterEvaluation,
			DeegreeRasterCacheCleaner deegreeRasterCacheCleaner) {
		this.dataDirectory = dataDirectory;
		this.rasterEvaluation = rasterEvaluation;
		this.deegreeRasterCacheCleaner = deegreeRasterCacheCleaner;
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
	public void deleteRasterFile(int planId, String fileName, StorageEvent storageEvent) {
		final String rasterLayerFileName = planId + "_" + fileName;
		deegreeRasterCacheCleaner.clearCache(rasterLayerFileName);
		Path file = dataDirectory.resolve(rasterLayerFileName);
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
	}

	protected String createFileName(int planId, String fileName) {
		return planId + "_" + fileName;
	}

	protected Path createTargetFile(String newFileName) {
		return dataDirectory.resolve(newFileName);
	}

}
