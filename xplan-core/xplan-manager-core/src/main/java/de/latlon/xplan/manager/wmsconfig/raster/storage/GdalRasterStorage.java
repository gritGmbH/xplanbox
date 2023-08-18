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
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.RasterEvaluation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GdalRasterStorage extends FileSystemStorage {

	private GdalRasterAdapter rasterAdapter;

	public GdalRasterStorage(Path dataDirectory, RasterEvaluation rasterEvaluation, GdalRasterAdapter rasterAdapter) {
		super(dataDirectory, rasterEvaluation);
		this.rasterAdapter = rasterAdapter;
	}

	@Override
	public String addRasterFile(int planId, String entryName, XPlanArchiveContentAccess archive,
			StorageEvent storageEvent) throws IOException {
		String rasterFileName = super.addRasterFile(planId, entryName, archive, storageEvent);
		if (rasterFileName != null) {
			Vector<?> referencedFiles = rasterAdapter.getReferencedFiles(archive, entryName);
			if (referencedFiles != null) {
				for (Object referencedFile : referencedFiles) {
					Path file = Paths.get(referencedFile.toString());
					String newFileName = createFileName(planId, file.getFileName().toString());
					if (!newFileName.equals(rasterFileName)) {
						Path target = createTargetFile(newFileName);
						Files.copy(file, target);
					}
				}
			}
		}
		return rasterFileName;
	}

}
