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
package de.latlon.xplan.manager.web.client.gui.dialog;

import static de.latlon.xplan.manager.web.client.utils.WmsUrlUtils.createPlanwerkWmsUrl;
import static de.latlon.xplan.manager.web.client.utils.WmsUrlUtils.createUrl;
import static de.latlon.xplan.manager.web.client.utils.WmsUrlUtils.determineWmsUrl;

import com.google.gwt.http.client.URL;
import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Projection;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.control.ScaleLine;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSOptions;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.client.service.ManagerWebConfigurationService;
import de.latlon.xplan.manager.web.client.service.ManagerWebConfigurationServiceAsync;
import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterLayerConfiguration;
import de.latlon.xplan.manager.web.shared.VectorLayerConfiguration;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * PopUp window containing the preview of the plan as openlayers map.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @version $Revision: $, $Date: $
 */
public class MapPreviewDialog extends DialogBox {

	private static final boolean CLOSEST = false;

	private static final String IMAGE_FORMAT = "image/png";

	private static final String URL_BUTTON_REQUESTED_WIDTH = "750";

	private static final String URL_BUTTON_REQUESTED_HEIGHT = "750";

	private final ManagerWebConfigurationServiceAsync configrationService = GWT
		.create(ManagerWebConfigurationService.class);

	private final XPlanWebMessages messages = GWT.create(XPlanWebMessages.class);

	public MapPreviewDialog(String planName, String planType, PlanStatus planStatus, XPlanEnvelope bbox) {
		super(false);
		initDialog(planName, planType, planStatus, bbox);
	}

	private void initDialog(String planName, String planType, PlanStatus planStatus, XPlanEnvelope bbox) {
		setText(messages.mapPreviewDialogTitle(planName));
		VerticalPanel dialogBoxContent = createDialogBoxContent();
		createAndAddMapAndUrlButton(dialogBoxContent, planName, planType, planStatus, bbox);
		createAndAddCloseButton(dialogBoxContent);
		setWidget(dialogBoxContent);
	}

	private VerticalPanel createDialogBoxContent() {
		VerticalPanel dialogBoxContent = new VerticalPanel();
		dialogBoxContent.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		dialogBoxContent.setSpacing(20);
		return dialogBoxContent;
	}

	private void createAndAddMapAndUrlButton(final VerticalPanel dialogBoxContent, final String planName,
			final String planType, final PlanStatus planStatus, final XPlanEnvelope bbox) {
		final SimplePanel mapPanel = new SimplePanel();
		final SimplePanel urlButtonPanel = new SimplePanel();
		final SimplePanel capabilitiesButtonPanel = new SimplePanel();
		configrationService.getMapPreviewConfiguration(new AsyncCallback<MapPreviewConfiguration>() {
			@Override
			public void onSuccess(MapPreviewConfiguration configuration) {
				Bounds bounds = createAndAddMap(mapPanel, configuration, planType, planStatus, bbox);
				createAndAddUrlButton(urlButtonPanel, configuration, planType, planStatus, bounds);
				createAndAddCapabilitiesButton(capabilitiesButtonPanel, configuration, planName, planStatus);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
		dialogBoxContent.add(mapPanel);
		dialogBoxContent.add(urlButtonPanel);
		dialogBoxContent.add(capabilitiesButtonPanel);
	}

	private Bounds createAndAddMap(SimplePanel contentPanel, MapPreviewConfiguration configuration, String planType,
			PlanStatus planStatus, XPlanEnvelope bbox) {
		MapWidget mapWidget = createMapWidget(configuration);
		Bounds bounds = createMap(mapWidget, planType, planStatus, bbox, configuration);
		// force the map to fall behind popups
		mapWidget.getElement().getFirstChildElement().getStyle().setZIndex(0);
		contentPanel.add(mapWidget);
		return bounds;
	}

	private MapWidget createMapWidget(MapPreviewConfiguration configuration) {
		XPlanEnvelope mapExtent = configuration.getMapExtent();
		String mapCrs = mapExtent.getCrs();

		MapOptions defaultMapOptions = new MapOptions();
		defaultMapOptions.setProjection(mapCrs);
		defaultMapOptions.setDisplayProjection(new Projection(mapCrs));
		Bounds maxExtent = new Bounds(mapExtent.getMinX(), mapExtent.getMinX(), mapExtent.getMaxX(),
				mapExtent.getMaxY());
		defaultMapOptions.setMaxExtent(maxExtent);
		defaultMapOptions.setMaxResolutionToAuto();
		// defaultMapOptions.setUnits( "m" );
		defaultMapOptions.setNumZoomLevels(16);
		return new MapWidget("500px", "500px", defaultMapOptions);
	}

	private Bounds createMap(MapWidget mapWidget, String planType, PlanStatus planStatus, XPlanEnvelope bbox,
			MapPreviewConfiguration configuration) {
		WMS syncWms = createSyncWms(planType, planStatus, configuration);
		WMS rasterWms = createRasterWms(planType, planStatus, configuration);
		WMS basemapWms = createBasemapWms(configuration);

		Map map = mapWidget.getMap();
		map.addLayer(basemapWms);
		map.addLayer(syncWms);
		map.addLayer(rasterWms);

		map.addControl(new LayerSwitcher());
		map.addControl(new ScaleLine());

		return zoomToBBox(bbox, map, configuration);
	}

	private Bounds zoomToBBox(XPlanEnvelope bbox, Map map, MapPreviewConfiguration configuration) {
		LonLat min = new LonLat(bbox.getMinX(), bbox.getMinY());
		LonLat max = new LonLat(bbox.getMaxX(), bbox.getMaxY());
		transformIfRequired(bbox, min, max, configuration);

		Bounds bounds = new Bounds(min.lon(), min.lat(), max.lon(), max.lat());
		int zoomLevel = map.getZoomForExtent(bounds, CLOSEST);
		map.setCenter(bounds.getCenterLonLat(), zoomLevel);
		return map.getExtent();
	}

	private void transformIfRequired(XPlanEnvelope bbox, LonLat min, LonLat max,
			MapPreviewConfiguration configuration) {
		String mapCrs = configuration.getMapExtent().getCrs();
		String bboxCrs = bbox.getCrs();
		if (!bboxCrs.equalsIgnoreCase(mapCrs)) {
			min.transform(bboxCrs, mapCrs);
			max.transform(bboxCrs, mapCrs);
		}
	}

	private WMS createBasemapWms(MapPreviewConfiguration configuration) {
		String wmsUrl = configuration.getBasemapUrl();
		String wmsName = configuration.getBasemapName();
		String layers = configuration.getBasemapLayer();
		return createWms(wmsUrl, wmsName, layers, true);
	}

	private WMS createRasterWms(String planType, PlanStatus planStatus, MapPreviewConfiguration configuration) {
		String wmsUrl = determineWmsUrl(planStatus, configuration);
		String wmsName = configuration.getRasterLayerConfiguration().getRasterWmsName();
		String layer = retrieveRasterLayerNames(planType, configuration);
		return createWms(wmsUrl, wmsName, layer, false);
	}

	private WMS createSyncWms(String planType, PlanStatus planStatus, MapPreviewConfiguration configuration) {
		String wmsUrl = determineWmsUrl(planStatus, configuration);
		String wmsName = configuration.getVectorLayerConfiguration().getVectorWmsName();
		String layers = retrieveVectorLayerNames(planType, configuration);
		return createWms(wmsUrl, wmsName, layers, false);
	}

	private WMS createWms(String wmsUrl, String wmsName, String layers, boolean isBasemap) {
		WMSParams wmsParams = new WMSParams();
		wmsParams.setFormat(IMAGE_FORMAT);
		wmsParams.setTransparent(!isBasemap);
		wmsParams.setLayers(layers);
		wmsParams.setStyles("");

		WMSOptions wmsLayerParams = new WMSOptions();
		wmsLayerParams.setUntiled();
		wmsLayerParams.setIsBaseLayer(isBasemap);
		wmsLayerParams.setTransitionEffect(TransitionEffect.RESIZE);

		return new WMS(wmsName, wmsUrl, wmsParams, wmsLayerParams);
	}

	private String retrieveVectorLayerNames(String planType, MapPreviewConfiguration configuration) {
		VectorLayerConfiguration vectorLayerConfiguration = configuration.getVectorLayerConfiguration();
		if ("bp_plan".equals(planType.toLowerCase()))
			return vectorLayerConfiguration.getBpVectorLayer();
		else if ("fp_plan".equals(planType.toLowerCase()))
			return vectorLayerConfiguration.getFpVectorLayer();
		else if ("lp_plan".equals(planType.toLowerCase()))
			return vectorLayerConfiguration.getLpVectorLayer();
		else if ("rp_plan".equals(planType.toLowerCase()))
			return vectorLayerConfiguration.getRpVectorLayer();
		else if ("so_plan".equals(planType.toLowerCase()))
			return vectorLayerConfiguration.getSoVectorLayer();
		else
			return "wrong planType: " + planType;
	}

	private String retrieveRasterLayerNames(String planType, MapPreviewConfiguration configuration) {
		RasterLayerConfiguration rasterLayerConfiguration = configuration.getRasterLayerConfiguration();
		if ("bp_plan".equals(planType.toLowerCase()))
			return rasterLayerConfiguration.getBpRasterLayer();
		else if ("fp_plan".equals(planType.toLowerCase()))
			return rasterLayerConfiguration.getFpRasterLayer();
		else if ("lp_plan".equals(planType.toLowerCase()))
			return rasterLayerConfiguration.getLpRasterLayer();
		else if ("rp_plan".equals(planType.toLowerCase()))
			return rasterLayerConfiguration.getRpRasterLayer();
		else if ("so_plan".equals(planType.toLowerCase()))
			return rasterLayerConfiguration.getSoRasterLayer();
		else
			return "wrong planType: " + planType;
	}

	private void createAndAddUrlButton(SimplePanel contentPanel, MapPreviewConfiguration configuration, String planType,
			PlanStatus planStatus, Bounds bounds) {
		SimplePanel buttonPanel = createButtonPanel(messages.urlButton(),
				createUrlHandler(configuration, planType, planStatus, bounds));
		contentPanel.add(buttonPanel);
	}

	private void createAndAddCapabilitiesButton(SimplePanel contentPanel, MapPreviewConfiguration configuration,
			String planName, PlanStatus planStatus) {
		SimplePanel buttonPanel = createButtonPanel(messages.capabilitiesButton(),
				createCapabilitiesHandler(configuration, planName, planStatus));
		contentPanel.add(buttonPanel);
	}

	private ClickHandler createUrlHandler(final MapPreviewConfiguration configuration, final String planType,
			final PlanStatus planStatus, final Bounds bounds) {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String url = createUrl(configuration, planType, planStatus, bounds, URL_BUTTON_REQUESTED_WIDTH,
						URL_BUTTON_REQUESTED_HEIGHT);
				Window.open(url, "_blank", "");
			}
		};
	}

	private ClickHandler createCapabilitiesHandler(final MapPreviewConfiguration configuration, final String planName,
			final PlanStatus planStatus) {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String normalizedPlanName = planName.replace("/", "");
				String url = createPlanwerkWmsUrl(URL.encode(normalizedPlanName), configuration, planStatus);
				Window.open(url, "_blank", "");
			}
		};
	}

	private void createAndAddCloseButton(VerticalPanel dialogBoxContent) {
		SimplePanel simplePanel = createButtonPanel(messages.closeButton(), createCloseHandler());
		dialogBoxContent.add(simplePanel);
	}

	private ClickHandler createCloseHandler() {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MapPreviewDialog.this.hide();
			}
		};
	}

	private SimplePanel createButtonPanel(String message, ClickHandler clickHandler) {
		Button button = new Button();
		button.setText(message);
		button.addClickHandler(clickHandler);
		return addButtonToSimplePanel(button);
	}

	private SimplePanel addButtonToSimplePanel(Button button) {
		SimplePanel simplePanel = new SimplePanel();
		simplePanel.add(button);
		return simplePanel;
	}

}
