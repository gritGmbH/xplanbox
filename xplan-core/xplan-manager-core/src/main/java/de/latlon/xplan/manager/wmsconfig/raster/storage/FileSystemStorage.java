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
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class FileSystemStorage implements RasterStorage {

	protected String copyEntry(File workspaceLocation, XPlanArchiveContentAccess archive, int planId, String entryName)
			throws IOException {
		String rasterFileName = createFileName(planId, entryName);
		File target = createTargetFile(workspaceLocation, rasterFileName);
		FileUtils.copyInputStreamToFile(archive.retrieveInputStreamFor(entryName), target);
		return rasterFileName;
	}

	protected void copyFile(File workspaceLocation, int planId, File file) throws IOException {
		String newFileName = createFileName(planId, file.getName());
		File target = createTargetFile(workspaceLocation, newFileName);
		FileUtils.copyFile(file, target);
	}

	private String createFileName(int planId, String fileName) {
		return planId + "_" + fileName;
	}

	private File createTargetFile(File workspaceLocation, String newFileName) {
		return new File(workspaceLocation, "data/" + newFileName);
	}

}
