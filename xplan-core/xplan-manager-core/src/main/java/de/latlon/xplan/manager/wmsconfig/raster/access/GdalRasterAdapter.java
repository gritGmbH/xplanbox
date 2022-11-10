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
package de.latlon.xplan.manager.wmsconfig.raster.access;

import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Vector;

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

	public Vector<?> getReferencedFiles(File mainRasterFile) {
		Dataset dataset = gdal.OpenShared(mainRasterFile.getAbsolutePath());
		if (dataset != null) {
			return dataset.GetFileList();
		}
		return null;
	}

	public String getRasterCrs(File mainRasterFile) {
		Dataset dataset = gdal.OpenShared(mainRasterFile.getAbsolutePath());
		if (dataset != null) {
			return dataset.GetProjectionRef();
		}
		return null;
	}

}
