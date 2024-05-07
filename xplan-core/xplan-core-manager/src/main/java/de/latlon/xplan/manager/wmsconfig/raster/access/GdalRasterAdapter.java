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
package de.latlon.xplan.manager.wmsconfig.raster.access;

import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Vector;

import static org.apache.commons.io.IOUtils.close;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GdalRasterAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(GdalRasterAdapter.class);

	private static boolean gdalSuccessfullInitialized = false;

	static {
		try {
			gdal.AllRegister();
			LOG.info("Installed GDAL JNI Version : " + gdal.VersionInfo());
			gdalSuccessfullInitialized = true;
		}
		catch (Throwable e) {
			LOG.warn("Registration of GDAL JNI adapter failed: " + e.getMessage(), e);
			LOG.warn("GDAL raster configurations are not supported. "
					+ "In the managerConfiguration.properties file the rasterConfigurationType must not be gdal!");
		}
	}

	/**
	 * @return <code>true</code> if gdal was correctly initialized, <code>false</code>
	 * otherwise
	 */
	public static boolean isGdalSuccessfullInitialized() {
		return gdalSuccessfullInitialized;
	}

	/**
	 * @param archive the XPlanArchive, never <code>null</code>
	 * @param entryName the name of the entry to detect the referenced files,
	 * <code>null</code>
	 * @return all referenced files, e.g. tfw or pgw files, may be <code>null</code> if an
	 * entry with the passed name is not found.
	 * @throws IOException if the zip file could not be unziped
	 */
	public Vector<?> getReferencedFiles(XPlanArchiveContentAccess archive, String entryName) throws IOException {
		File zipArchiveLocation = unzipArchiveInTmpDirectory(archive);
		File entry = new File(zipArchiveLocation, FilenameUtils.getName(entryName));
		Dataset dataset = gdal.OpenShared(entry.getAbsolutePath());
		if (dataset != null) {
			return dataset.GetFileList();
		}
		return null;
	}

	/**
	 * @param rasterFile the file with the raster, to retrieve the crs from, never
	 * <<code>null</code>
	 * @return the crs definition , may be <code>null</code> if the entry with the passed
	 * name is not found or is not a gdal dataset.
	 */
	public String getRasterCrs(File rasterFile) {
		Dataset dataset = gdal.OpenShared(rasterFile.getAbsolutePath());
		if (dataset != null) {
			return dataset.GetProjectionRef();
		}
		return null;
	}

	public File unzipArchiveInTmpDirectory(XPlanArchiveContentAccess archive) throws IOException {
		List<? extends ArchiveEntry> archiveEntries = archive.getZipFileEntries();
		File archiveDirectory = Files.createTempDirectory("xplanbox-archive").toFile();
		for (ArchiveEntry zipEntry : archiveEntries) {
			copyToTempFile(archive, zipEntry.getName(), archiveDirectory);
		}
		return archiveDirectory;
	}

	private void copyToTempFile(XPlanArchiveContentAccess archive, String entryName, File archiveDirectory)
			throws IOException {
		InputStream content = archive.retrieveInputStreamFor(entryName);
		File writeRasterIn = new File(archiveDirectory, FilenameUtils.getName(entryName));
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
