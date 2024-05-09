/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.shared;

import de.latlon.xplan.validator.web.shared.XPlanEnvelope;

import java.io.Serializable;

/**
 * Encapsulates a map preview configuration
 *
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @version $Revision: $, $Date: $
 */
public class MapPreviewConfiguration implements Serializable {

	private static final long serialVersionUID = -6690846114049627140L;

	private String basemapUrl;

	private String basemapName;

	private String basemapLayer;

	private String wmsUrl;

	private String wmsEndpoint;

	private String wmsPreEndpoint;

	private String wmsArchiveEndpoint;

	private XPlanEnvelope mapExtent;

	private VectorLayerConfiguration vectorLayerConfiguration;

	private RasterLayerConfiguration rasterLayerConfiguration;

	public MapPreviewConfiguration() {
	}

	/**
	 * @param basemapUrl the basemap wms url, never <code>null</code> must be of the
	 * following template format: http://{host}/{service
	 * }?SERVICE=WMS&REQUEST=GetMap&FORMAT={format}&BBOX={bbox}&SRS={srs}&VERSION={version
	 * }&layers={layername}
	 * @param basemapName the name of the basemap, may be <code>null</code>
	 * @param basemapLayer the layer of the basemap, never <code>null</code>
	 * @param wmsUrl the xplan wms url, never <code>null</code> must be of the following
	 * template format: http://{host}/{service}. If the url ends with '?' or not with
	 * 'services', the configured url is used as wms url, otherwise the wmsEndpoint or
	 * wmsPreEndpoint is appended (if configured).
	 * @param wmsEndpoint endpoint of the default wms, providing the plans with status
	 * 'FESTGESTELLT', may be <code>null</code> (if the service only provides one wms or
	 * the wmsUrl is configured as complete endpoint)
	 * @param wmsPreEndpoint endpoint of the default wms, providing the plans with status
	 * 'IN_AUFSTELLUNG', may be <code>null</code> (if the service only provides one wms or
	 * the wmsUrl is configured as complete endpoint)
	 * @param wmsArchiveEndpoint endpoint of the default wms, providing the plans with
	 * status 'ARCHIVIERT', may be <code>null</code> (if the service only provides one wms
	 * or the wmsUrl is configured as complete endpoint)
	 * @param mapExtent the max extent of the map (with crs), never <code>null</code>
	 * @param vectorLayerConfiguration the vector layer configuration, never
	 * <code>null</code>
	 * @param rasterLayerConfiguration the raster layer configuration, never
	 * <code>null</code>
	 */
	public MapPreviewConfiguration(String basemapUrl, String basemapName, String basemapLayer, String wmsUrl,
			String wmsEndpoint, String wmsPreEndpoint, String wmsArchiveEndpoint, XPlanEnvelope mapExtent,
			VectorLayerConfiguration vectorLayerConfiguration, RasterLayerConfiguration rasterLayerConfiguration) {
		this.basemapUrl = basemapUrl;
		this.basemapName = basemapName;
		this.basemapLayer = basemapLayer;
		this.wmsUrl = wmsUrl;
		this.wmsEndpoint = wmsEndpoint;
		this.wmsPreEndpoint = wmsPreEndpoint;
		this.wmsArchiveEndpoint = wmsArchiveEndpoint;
		this.mapExtent = mapExtent;
		this.vectorLayerConfiguration = vectorLayerConfiguration;
		this.rasterLayerConfiguration = rasterLayerConfiguration;
	}

	public String getBasemapUrl() {
		return basemapUrl;
	}

	public String getBasemapName() {
		return basemapName;
	}

	public String getBasemapLayer() {
		return basemapLayer;
	}

	public String getWmsUrl() {
		return wmsUrl;
	}

	public String getWmsEndpoint() {
		return wmsEndpoint;
	}

	public String getWmsPreEndpoint() {
		return wmsPreEndpoint;
	}

	public String getWmsArchiveEndpoint() {
		return wmsArchiveEndpoint;
	}

	public XPlanEnvelope getMapExtent() {
		return mapExtent;
	}

	public VectorLayerConfiguration getVectorLayerConfiguration() {
		return vectorLayerConfiguration;
	}

	public RasterLayerConfiguration getRasterLayerConfiguration() {
		return rasterLayerConfiguration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basemapLayer == null) ? 0 : basemapLayer.hashCode());
		result = prime * result + ((basemapName == null) ? 0 : basemapName.hashCode());
		result = prime * result + ((basemapUrl == null) ? 0 : basemapUrl.hashCode());
		result = prime * result + ((mapExtent == null) ? 0 : mapExtent.hashCode());
		result = prime * result + ((rasterLayerConfiguration == null) ? 0 : rasterLayerConfiguration.hashCode());
		result = prime * result + ((vectorLayerConfiguration == null) ? 0 : vectorLayerConfiguration.hashCode());
		result = prime * result + ((wmsEndpoint == null) ? 0 : wmsEndpoint.hashCode());
		result = prime * result + ((wmsPreEndpoint == null) ? 0 : wmsPreEndpoint.hashCode());
		result = prime * result + ((wmsUrl == null) ? 0 : wmsUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapPreviewConfiguration other = (MapPreviewConfiguration) obj;
		if (basemapLayer == null) {
			if (other.basemapLayer != null)
				return false;
		}
		else if (!basemapLayer.equals(other.basemapLayer))
			return false;
		if (basemapName == null) {
			if (other.basemapName != null)
				return false;
		}
		else if (!basemapName.equals(other.basemapName))
			return false;
		if (basemapUrl == null) {
			if (other.basemapUrl != null)
				return false;
		}
		else if (!basemapUrl.equals(other.basemapUrl))
			return false;
		if (mapExtent == null) {
			if (other.mapExtent != null)
				return false;
		}
		else if (!mapExtent.equals(other.mapExtent))
			return false;
		if (rasterLayerConfiguration == null) {
			if (other.rasterLayerConfiguration != null)
				return false;
		}
		else if (!rasterLayerConfiguration.equals(other.rasterLayerConfiguration))
			return false;
		if (vectorLayerConfiguration == null) {
			if (other.vectorLayerConfiguration != null)
				return false;
		}
		else if (!vectorLayerConfiguration.equals(other.vectorLayerConfiguration))
			return false;
		if (wmsEndpoint == null) {
			if (other.wmsEndpoint != null)
				return false;
		}
		else if (!wmsEndpoint.equals(other.wmsEndpoint))
			return false;
		if (wmsPreEndpoint == null) {
			if (other.wmsPreEndpoint != null)
				return false;
		}
		else if (!wmsPreEndpoint.equals(other.wmsPreEndpoint))
			return false;
		if (wmsUrl == null) {
			if (other.wmsUrl != null)
				return false;
		}
		else if (!wmsUrl.equals(other.wmsUrl))
			return false;
		return true;
	}

}
