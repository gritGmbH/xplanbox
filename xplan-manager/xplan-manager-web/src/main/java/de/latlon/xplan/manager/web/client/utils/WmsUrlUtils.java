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
package de.latlon.xplan.manager.web.client.utils;

import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import org.gwtopenmaps.openlayers.client.Bounds;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static de.latlon.xplan.manager.web.shared.PlanStatus.ARCHIVIERT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;

/**
 * Contains some useful methods to create WMS urls.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public final class WmsUrlUtils {

	private WmsUrlUtils() {
	}

	/**
	 * Determines the correct WMS url. If the configured wms url ends with a '?' the
	 * configured url is returned (plan status is ignored). If the configured wms url ends
	 * not with 'services' a '?' is appended to the configured url (plan status is
	 * ignored). Otherwise the correct endpoint and a '?' is appenden.
	 * @param planStatus status of the plan, may be <code>null</code> (means
	 * PlanStatus.FESTGESTELLT)
	 * @param configuration never <code>null</code>
	 * @return the url of the wms (ending with a '?'), never <code>null</code>
	 */
	public static String determineWmsUrl(PlanStatus planStatus, MapPreviewConfiguration configuration) {
		String wmsUrl = configuration.getWmsUrl();
		if (wmsUrl.endsWith("?"))
			return wmsUrl;
		if (wmsUrl.endsWith("/"))
			wmsUrl = wmsUrl.substring(0, wmsUrl.length() - 1);
		if (!wmsUrl.endsWith("services"))
			return wmsUrl + "?";
		String endpointToAdd = detectEndpointToAdd(configuration, planStatus);
		if (endpointToAdd == null)
			return wmsUrl + "?";
		return wmsUrl + "/" + endpointToAdd + "?";
	}

	public static String createPlanwerkWmsUrl(String planname, MapPreviewConfiguration configuration,
			PlanStatus planStatus) {
		String wmsUrl = determineWmsUrl(null, configuration);
		int servicesIndex = wmsUrl.lastIndexOf("services");
		if (servicesIndex < 0)
			return null;
		wmsUrl = wmsUrl.substring(0, servicesIndex);
		StringBuilder planwerkWmsUrl = new StringBuilder();
		planwerkWmsUrl.append(wmsUrl);
		planwerkWmsUrl.append("services/");
		planwerkWmsUrl.append(planwerkWmsPath(planStatus));
		planwerkWmsUrl.append("/planname/");
		planwerkWmsUrl.append(planname);
		planwerkWmsUrl.append("?request=GetCapabilities&service=WMS&version=1.3.0");
		return planwerkWmsUrl.toString();
	}

	public static String createUrl(final MapPreviewConfiguration configuration, final String planType,
			final PlanStatus planStatus, final Bounds bounds, String width, String height) {
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(determineWmsUrl(planStatus, configuration));
		urlBuilder.append("SERVICE=").append("WMS");
		urlBuilder.append("&VERSION=").append("1.1.1");
		urlBuilder.append("&REQUEST=").append("GetMap");
		urlBuilder.append("&LAYERS=").append(createLayerValue(planType));
		urlBuilder.append("&STYLES=").append("");
		urlBuilder.append("&FORMAT=").append("image/png");
		urlBuilder.append("&TRANSPARENT=").append("true");
		urlBuilder.append("&EXCEPTIONS=").append("application/vnd.ogc.se_inimage");
		urlBuilder.append("&SRS=").append(configuration.getMapExtent().getCrs());
		urlBuilder.append("&BBOX=").append(createBboxValue(bounds));
		urlBuilder.append("&WIDTH=").append(width);
		urlBuilder.append("&HEIGHT=").append(height);
		return urlBuilder.toString();
	}

	private static String planwerkWmsPath(PlanStatus planStatus) {
		switch (planStatus) {
			case ARCHIVIERT:
				return "planwerkwmsarchive";
			case IN_AUFSTELLUNG:
				return "planwerkwmspre";
			default:
				return "planwerkwms";
		}
	}

	private static String detectEndpointToAdd(MapPreviewConfiguration configuration, PlanStatus planStatus) {
		String wmsEndpoint = configuration.getWmsEndpoint();
		String wmsPreEndpoint = configuration.getWmsPreEndpoint();
		String wmsArchiveEndpoint = configuration.getWmsArchiveEndpoint();
		boolean isWmsEndpointConfigured = isConfigured(wmsEndpoint);
		boolean isWmsPreEndpointConfigured = isConfigured(wmsPreEndpoint);
		boolean isWmsArchiveEndpointConfigured = isConfigured(wmsArchiveEndpoint);
		if (IN_AUFSTELLUNG.equals(planStatus) && isWmsPreEndpointConfigured)
			return wmsPreEndpoint;
		if (ARCHIVIERT.equals(planStatus) && isWmsArchiveEndpointConfigured)
			return wmsArchiveEndpoint;
		if (isWmsEndpointConfigured)
			return wmsEndpoint;
		if (isWmsPreEndpointConfigured)
			return wmsPreEndpoint;
		if (isWmsArchiveEndpointConfigured)
			return wmsArchiveEndpoint;
		return null;
	}

	private static String createLayerValue(String planType) {
		if ("bp_plan".equals(planType.toLowerCase()))
			return "bp_objekte,bp_raster";
		if ("fp_plan".equals(planType.toLowerCase()))
			return "fp_objekte,fp_raster";
		if ("lp_plan".equals(planType.toLowerCase()))
			return "lp_objekte,lp_raster";
		if ("rp_plan".equals(planType.toLowerCase()))
			return "rp_objekte,rp_raster";
		if ("so_plan".equals(planType.toLowerCase()))
			return "so_objekte,so_raster";
		return null;
	}

	private static String createBboxValue(final Bounds bounds) {
		StringBuilder bboxBuilder = new StringBuilder();
		bboxBuilder.append(bounds.getLowerLeftX());
		bboxBuilder.append(",");
		bboxBuilder.append(bounds.getLowerLeftY());
		bboxBuilder.append(",");
		bboxBuilder.append(bounds.getUpperRightX());
		bboxBuilder.append(",");
		bboxBuilder.append(bounds.getUpperRightY());
		return bboxBuilder.toString();
	}

	private static boolean isConfigured(String string) {
		return string != null && !string.isEmpty();
	}

}
