/*-
 * #%L
 * xplan-wms - deegree XPlan WebMapService
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
package de.latlon.xplan.planwerkwms;

import de.latlon.xplan.planwerkwms.jaxb.Planwerk;
import org.apache.commons.fileupload.FileItem;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.io.WKTReader;
import org.deegree.geometry.metadata.SpatialMetadata;
import org.deegree.layer.metadata.LayerMetadata;
import org.deegree.services.controller.utils.HttpResponseBuffer;
import org.deegree.services.jaxb.controller.DeegreeServiceControllerType;
import org.deegree.services.jaxb.metadata.DeegreeServicesMetadataType;
import org.deegree.services.jaxb.wms.DeegreeWMS;
import org.deegree.services.metadata.OWSMetadataProvider;
import org.deegree.services.metadata.provider.OWSMetadataProviderProvider;
import org.deegree.services.wms.controller.WMSController;
import org.deegree.services.wms.controller.capabilities.theme.DefaultMetadataMerger;
import org.deegree.services.wms.controller.capabilities.theme.MetadataMerger;
import org.deegree.theme.Theme;
import org.deegree.workspace.Workspace;
import org.locationtech.jts.io.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkController extends WMSController {

	private static final Logger LOG = LoggerFactory.getLogger(PlanwerkController.class);

	private static final String PARAM_NAME_MANAGERID = "PLANWERK_MANAGERID";

	private final Planwerk planwerk;

	public PlanwerkController(PlanwerkMetadata metadata, Workspace workspace, DeegreeWMS deegreeWMSConfig,
			Planwerk planwerk) {
		super(metadata, workspace, deegreeWMSConfig);
		this.planwerk = planwerk;
	}

	@Override
	public void init(DeegreeServicesMetadataType serviceMetadata, DeegreeServiceControllerType mainConfig,
			Object controllerConf) {
		super.init(serviceMetadata, mainConfig, controllerConf);
		OWSMetadataProvider wmsMetadataProvider = workspace.getResource(OWSMetadataProviderProvider.class,
				planwerk.getPlanwerkWms() + "_metadata");
		MetadataProviderWrapper metadataProvider = new MetadataProviderWrapper(wmsMetadataProvider, planwerk);
		setMetadataProvider(metadataProvider);
	}

	@Override
	public void doKVP(Map<String, String> map, HttpServletRequest request, HttpResponseBuffer response,
			List<FileItem> multiParts) throws ServletException, IOException {
		addManagerIdParameter(map);
		super.doKVP(map, request, response, multiParts);
	}

	@Override
	public MetadataMerger getMetadataMerger() {
		return new MetadataMerger() {
			@Override
			public SpatialMetadata mergeSpatialMetadata(List<Theme> themes) {
				return createPlanwerkSpatialMetadata(themes);
			}

			@Override
			public LayerMetadata mergeLayerMetadata(Theme theme) {
				DefaultMetadataMerger defaultMetadataMerger = new DefaultMetadataMerger();
				LayerMetadata layerMetadata = defaultMetadataMerger.mergeLayerMetadata(theme);
				layerMetadata.setSpatialMetadata(createPlanwerkSpatialMetadata(theme));
				return layerMetadata;
			}

			private SpatialMetadata createPlanwerkSpatialMetadata(Theme theme) {
				List<ICRS> coordinateSystems = theme.getLayerMetadata().getSpatialMetadata().getCoordinateSystems();
				return new SpatialMetadata(parseBboxFromWkt(planwerk.getEnvelope()), coordinateSystems);
			}

			private SpatialMetadata createPlanwerkSpatialMetadata(List<Theme> themes) {
				List<ICRS> coordinateSystems = getCrs(themes);
				return new SpatialMetadata(parseBboxFromWkt(planwerk.getEnvelope()), coordinateSystems);
			}

			private List<ICRS> getCrs(List<Theme> themes) {
				if (themes.isEmpty())
					return Collections.emptyList();
				Theme firstTheme = themes.get(0);
				return firstTheme.getLayerMetadata().getSpatialMetadata().getCoordinateSystems();
			}
		};
	}

	public Planwerk getPlanwerk() {
		return planwerk;
	}

	private void addManagerIdParameter(Map<String, String> map) {
		List<Integer> managerIds = planwerk.getManagerId();
		String ids = managerIds.stream().map(managerId -> managerId.toString()).collect(joining(","));
		map.put(PARAM_NAME_MANAGERID, ids);
	}

	private Envelope parseBboxFromWkt(String bboxAsWkt) {
		if (bboxAsWkt != null && !bboxAsWkt.isEmpty()) {
			try {
				String crs = "epsg:4326";
				WKTReader reader = new WKTReader(CRSManager.lookup(crs));
				Geometry geometry = reader.read(bboxAsWkt);
				return geometry.getEnvelope();
			}
			catch (UnknownCRSException | ParseException e) {
				LOG.error("Could not create envelope from " + bboxAsWkt, e);
			}
		}
		return null;
	}

}
