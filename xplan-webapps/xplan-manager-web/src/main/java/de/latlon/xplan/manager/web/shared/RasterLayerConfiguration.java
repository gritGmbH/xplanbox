/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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

import java.io.Serializable;

/**
 * Encapsulates a map preview configuration
 *
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @version $Revision: $, $Date: $
 */
public class RasterLayerConfiguration implements Serializable {

	private static final long serialVersionUID = -6690846114049627142L;

	private String rasterWmsName;

	private String bpRasterLayer;

	private String fpRasterLayer;

	private String lpRasterLayer;

	private String rpRasterLayer;

	private String soRasterLayer;

	public RasterLayerConfiguration() {
	}

	/**
	 * @param rasterWmsName displayed name of the raster maps of the wms, may be
	 * <code>null</code>
	 * @param bpRasterLayer the bp_* Layer to display in map preview, never
	 * <code>null</code>
	 * @param fpRasterLayer the fp_* Layer to display in map preview, never
	 * <code>null</code>
	 * @param lpRasterLayer the lp_* Layer to display in map preview, never
	 * <code>null</code>
	 * @param rpRasterLayer the rp_* Layer to display in map preview, never
	 * <code>null</code>
	 * @param soRasterLayer the so_* Layer to display in map preview, never
	 * <code>null</code>
	 */
	public RasterLayerConfiguration(String rasterWmsName, String bpRasterLayer, String fpRasterLayer,
			String lpRasterLayer, String rpRasterLayer, String soRasterLayer) {
		this.rasterWmsName = rasterWmsName;
		this.bpRasterLayer = bpRasterLayer;
		this.fpRasterLayer = fpRasterLayer;
		this.lpRasterLayer = lpRasterLayer;
		this.rpRasterLayer = rpRasterLayer;
		this.soRasterLayer = soRasterLayer;
	}

	public String getRasterWmsName() {
		return rasterWmsName;
	}

	public String getBpRasterLayer() {
		return bpRasterLayer;
	}

	public String getFpRasterLayer() {
		return fpRasterLayer;
	}

	public String getLpRasterLayer() {
		return lpRasterLayer;
	}

	public String getRpRasterLayer() {
		return rpRasterLayer;
	}

	public String getSoRasterLayer() {
		return soRasterLayer;
	}

	@Override
	public int hashCode() {
		int result = rasterWmsName.hashCode();
		result = 31 * result + bpRasterLayer.hashCode();
		result = 31 * result + fpRasterLayer.hashCode();
		result = 31 * result + lpRasterLayer.hashCode();
		result = 31 * result + rpRasterLayer.hashCode();
		result = 31 * result + soRasterLayer.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RasterLayerConfiguration that = (RasterLayerConfiguration) o;

		if (!rasterWmsName.equals(that.rasterWmsName))
			return false;
		if (bpRasterLayer != null ? !bpRasterLayer.equals(that.bpRasterLayer) : that.bpRasterLayer != null)
			return false;
		if (fpRasterLayer != null ? !fpRasterLayer.equals(that.fpRasterLayer) : that.fpRasterLayer != null)
			return false;
		if (lpRasterLayer != null ? !lpRasterLayer.equals(that.lpRasterLayer) : that.lpRasterLayer != null)
			return false;
		if (rpRasterLayer != null ? !rpRasterLayer.equals(that.rpRasterLayer) : that.rpRasterLayer != null)
			return false;
		if (soRasterLayer != null ? !soRasterLayer.equals(that.soRasterLayer) : that.soRasterLayer != null)
			return false;

		return true;
	}

}
