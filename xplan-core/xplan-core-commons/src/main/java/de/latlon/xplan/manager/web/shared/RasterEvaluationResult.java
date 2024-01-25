/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared;

/**
 * Encapsulates results of the raster evaluation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class RasterEvaluationResult {

	private String rasterName;

	private String rasterCrs;

	private String rasterConfigurationCrs;

	private boolean crsSet;

	private boolean configuredCrs;

	private boolean supportedImageFormat;

	public RasterEvaluationResult() {
	}

	/**
	 * @param rasterName name of the raster file, never <code>null</code>
	 * @param rasterCrs crs of the raster file, may be <code>null</code> if none is set
	 * @param rasterConfigurationCrs crs of the raster configuration, may be
	 * <code>null</code> if none is set
	 * @param isCrsSet true if a crs is set in the rasterfile, false otherwise
	 * @param isConfiguredCrs true if the crs of the raster file is the same as the crs in
	 * the configuration, false if not or the crs is <code>null</code>
	 * @param supportedImageFormat true if the image format is supported, false otherwise
	 */
	public RasterEvaluationResult(String rasterName, String rasterCrs, String rasterConfigurationCrs, boolean isCrsSet,
			boolean isConfiguredCrs, boolean supportedImageFormat) {
		this.rasterName = rasterName;
		this.rasterCrs = rasterCrs;
		this.rasterConfigurationCrs = rasterConfigurationCrs;
		this.crsSet = isCrsSet;
		this.configuredCrs = isConfiguredCrs;
		this.supportedImageFormat = supportedImageFormat;
	}

	/**
	 * @return the name of the raster file, never <code>null</code>
	 */
	public String getRasterName() {
		return rasterName;
	}

	/**
	 * @param rasterName name of the raster file, never <code>null</code>
	 */
	public void setRasterName(String rasterName) {
		this.rasterName = rasterName;
	}

	/**
	 * @return crs of the raster file, may be <code>null</code> if none is set
	 */
	public String getRasterCrs() {
		return rasterCrs;
	}

	/**
	 * @param rasterCrs crs of the raster file, may be <code>null</code>
	 */
	public void setRasterCrs(String rasterCrs) {
		this.rasterCrs = rasterCrs;
	}

	/**
	 * @return crs of the raster configuration, may be <code>null</code> if none is set
	 */
	public String getRasterConfigurationCrs() {
		return rasterConfigurationCrs;
	}

	/**
	 * @param rasterConfigurationCrs crs of the raster configuration, may be
	 * <code>null</code>
	 */
	public void setRasterConfigurationCrs(String rasterConfigurationCrs) {
		this.rasterConfigurationCrs = rasterConfigurationCrs;
	}

	/**
	 * @param crsSet true if a crs is set in the rasterfile, false otherwise
	 */
	public void setCrsSet(boolean crsSet) {
		this.crsSet = crsSet;
	}

	/**
	 * @return the true if a crs is set in the rasterfile, false otherwise
	 */
	public boolean isCrsSet() {
		return crsSet;
	}

	/**
	 * @param configuredCrs true if the crs of the raster file is the same as the crs in
	 * the configuration, false if not or the crs is <code>null</code>
	 */
	public void setConfiguredCrs(boolean configuredCrs) {
		this.configuredCrs = configuredCrs;
	}

	/**
	 * @return the true if the crs of the raster file is the same as the crs in the
	 * configuration, false if not or the crs is <code>null</code>
	 */
	public boolean isConfiguredCrs() {
		return configuredCrs;
	}

	/**
	 * @return true if the image format is supported, false otherwise
	 */
	public boolean isSupportedImageFormat() {
		return supportedImageFormat;
	}

	/**
	 * @param supportedImageFormat true if the image format is supported, false otherwise
	 */
	public void setSupportedImageFormat(boolean supportedImageFormat) {
		this.supportedImageFormat = supportedImageFormat;
	}

	@Override
	public String toString() {
		return "RasterEvaluationResult {rasterName=" + rasterName + ", rasterCrs=" + rasterCrs
				+ ", rasterConfigurationCrs=" + rasterConfigurationCrs + ", crsSet=" + crsSet + ", configuredCrs="
				+ configuredCrs + ", supportedImageFormat=" + supportedImageFormat + "}";
	}

}
