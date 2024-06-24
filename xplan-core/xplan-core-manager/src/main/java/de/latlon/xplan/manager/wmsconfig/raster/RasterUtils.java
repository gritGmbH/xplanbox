/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
import org.apache.tika.Tika;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RasterUtils {

	private RasterUtils() {
	}

	/**
	 * @param archive to search for the entry
	 * @param scanFile name of the entry, never <code>null</code>
	 * @return the {@link ArchiveEntry} with the passed name (scanFile)
	 * @throws IllegalArgumentException if the scanFile does not exist in the archive
	 */
	public static ArchiveEntry findRasterplanZipEntry(XPlanArchiveContentAccess archive, String scanFile) {
		ArchiveEntry entry = archive.getEntry(scanFile);
		if (entry == null) {
			throw new IllegalArgumentException("Rasterscan-Datei '" + scanFile + "' ist nicht im Archiv vorhanden.");
		}
		return entry;
	}

	public static RasterType detectRasterType(XPlanArchiveContentAccess archive, String scanFile) {
		try (InputStream entryStream = archive.retrieveInputStreamFor(scanFile)) {
			String mediaType = new Tika().detect(entryStream);
			return RasterType.fromMediaType(mediaType);
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Rasterscan-Datei '" + scanFile + "' ist nicht im Archiv vorhanden.");

		}
	}

}
