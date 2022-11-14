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
package de.latlon.xplan.manager.wmsconfig.raster.storage;

import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GdalRasterStorage extends FileSystemStorage {

	private GdalRasterAdapter rasterAdapter;

	public GdalRasterStorage(GdalRasterAdapter rasterAdapter) {
		this.rasterAdapter = rasterAdapter;
	}

	@Override
	public String copyRasterfile(File workspaceLocation, int planId, XPlanArchiveContentAccess archive,
			String entryName) throws IOException {
		String rasterFileName = copyEntry(workspaceLocation, archive, planId, entryName);
		Vector<?> referencedFiles = rasterAdapter.getReferencedFiles(archive, entryName);
		if (referencedFiles != null) {
			for (Object referencedFile : referencedFiles) {
				File file = new File(referencedFile.toString());
				if (!file.getName().equals(rasterFileName)) {
					copyFile(workspaceLocation, planId, file);
				}
			}
		}
		return rasterFileName;
	}

	@Override
	public void deleteRasterFiles(String planId) {

	}

	@Override
	public void deleteRasterFiles(String planId, String rasterId) {

	}

}
