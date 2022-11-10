/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.wmsconfig.raster;

import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationType.geotiff;
import static org.apache.commons.io.IOUtils.close;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RasterUtils {

	private static final Logger LOG = LoggerFactory.getLogger(RasterUtils.class);

	public static List<ArchiveEntry> findRasterplanZipEntries(XPlanArchiveContentAccess archive, List<String> scanFiles,
			RasterConfigurationType rasterConfigurationType) {
		List<ArchiveEntry> entries = new ArrayList<>();
		for (String scanFile : scanFiles) {
			if (scanFile != null) {
				ArchiveEntry entry = archive.getEntry(scanFile);
				if (entry == null) {
					throw new RuntimeException("Rasterscan-Datei:" + scanFile + " ist nicht im Archiv vorhanden.");
				}
				if (geotiff.equals(rasterConfigurationType)) {
					String name = entry.getName().toLowerCase();
					if (!name.endsWith("tif") && !name.endsWith("tiff")) {
						LOG.info("Ignoriere Datei '{}'. Keine TIFF-Datei.", entry.getName());
					}
				}
				entries.add(entry);
			}
		}
		return entries;
	}

	public static File unzipArchiveInTmpDirectory(XPlanArchiveContentAccess archive) throws IOException {
		List<? extends ArchiveEntry> archiveEntries = archive.getZipFileEntries();
		File archiveDirectory = Files.createTempDirectory("xplanbox-archive").toFile();
		for (ArchiveEntry zipEntry : archiveEntries) {
			copyToTempFile(archive, zipEntry.getName(), archiveDirectory);
		}
		return archiveDirectory;
	}

	private static void copyToTempFile(XPlanArchiveContentAccess archive, String entryName, File archiveDirectory)
			throws IOException {
		InputStream content = archive.retrieveInputStreamFor(entryName);
		File writeRasterIn = new File(archiveDirectory, entryName);
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(writeRasterIn);
			IOUtils.copy(content, outputStream);
		}
		finally {
			close(content);
			close(outputStream);
		}
	}

}
