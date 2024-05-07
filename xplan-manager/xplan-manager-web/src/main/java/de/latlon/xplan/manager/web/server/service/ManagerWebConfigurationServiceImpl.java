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
package de.latlon.xplan.manager.web.server.service;

import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import de.latlon.xplan.manager.web.client.service.ManagerWebConfigurationService;
import de.latlon.xplan.manager.web.server.configuration.ManagerWebConfigurationRetriever;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;
import de.latlon.xplan.manager.web.shared.RasterLayerConfiguration;
import de.latlon.xplan.manager.web.shared.VectorLayerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static java.lang.String.format;

/**
 * Retrieves configuration for manager web and map preview
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 */
public class ManagerWebConfigurationServiceImpl extends XsrfProtectedServiceServlet
		implements ManagerWebConfigurationService {

	private static final long serialVersionUID = -1687168791255687012L;

	private static final Logger LOG = LoggerFactory.getLogger(ManagerWebConfigurationServiceImpl.class);

	private volatile ManagerWebConfigurationRetriever configurationRetriever;

	public ManagerWebConfigurationServiceImpl() {
		this(new ManagerWebConfigurationRetriever());
	}

	public ManagerWebConfigurationServiceImpl(ManagerWebConfigurationRetriever configurationRetriever) {
		this.configurationRetriever = configurationRetriever;
	}

	@Override
	public ManagerWebConfiguration getManagerWebConfiguration() throws ConfigurationException {
		try {
			ManagerWebConfiguration configuration = configurationRetriever.setupManagerWebConfiguration();
			logManagerWebConfiguration(configuration);
			return configuration;
		}
		catch (ConfigurationException e) {
			LOG.error("Error while retrieving configuration: {}", e.getMessage());
			LOG.debug("Exception: ", e);
			throw e;
		}
	}

	@Override
	public MapPreviewConfiguration getMapPreviewConfiguration() throws ConfigurationException {
		try {
			MapPreviewConfiguration configuration = configurationRetriever.setupMapPreviewConfiguration();
			logMapPreviewConfiguration(configuration);
			logVectorLayerConfiguration(configuration.getVectorLayerConfiguration());
			logRasterLayerConfiguration(configuration.getRasterLayerConfiguration());
			return configuration;
		}
		catch (ConfigurationException e) {
			LOG.warn(format("Error while retrieving configuration: %s", e.getMessage()));
			LOG.debug("Exception: ", e);
			throw e;
		}
	}

	private void logManagerWebConfiguration(ManagerWebConfiguration configuration) {
		LOG.info("-------------------------------------------");
		LOG.info("Configuration of the XPlanManagerWeb");
		LOG.info("-------------------------------------------");
		LOG.info("internal id dialog");
		LOG.info("   - is activated: {}", configuration.getInternalIdActivated());
		LOG.info("-------------------------------------------");
		LOG.info("legislation status dialog");
		LOG.info("   - is activated: {}", configuration.isLegislationStatusActivated());
		LOG.info("-------------------------------------------");
		LOG.info("validity period dialog");
		LOG.info("   - is activated: {}", configuration.isValidityPeriodActivated());
		LOG.info("-------------------------------------------");
		LOG.info("crs dialog");
		LOG.info("   - default crs: {}", configuration.getCrsDialogDefaultCrs());
		LOG.info("   - choose crs: {}", Arrays.toString(configuration.getCrsDialogChooseCrs()));
		LOG.info("-------------------------------------------");
	}

	private void logMapPreviewConfiguration(MapPreviewConfiguration configuration) {
		LOG.info("-------------------------------------------");
		LOG.info("Configuration of the map preview");
		LOG.info("-------------------------------------------");
		LOG.info("Basemap URL: {}", configuration.getBasemapUrl());
		LOG.info("Basemap Name: {}", configuration.getBasemapName());
		LOG.info("Basemap Layer: {}", configuration.getBasemapLayer());
		LOG.info("XPlan WMS URL: {}", configuration.getWmsUrl());
		LOG.info("-------------------------------------------");
	}

	private void logVectorLayerConfiguration(VectorLayerConfiguration configuration) {
		LOG.info("-------------------------------------------");
		LOG.info("Configuration of the vector layers");
		LOG.info("-------------------------------------------");
		LOG.info("XPlan Vector-WMS name: {}", configuration.getVectorWmsName());
		LOG.info("bpVectorLayers: {}", configuration.getBpVectorLayer());
		LOG.info("fpVectorLayers: {}", configuration.getFpVectorLayer());
		LOG.info("lpVectorLayers: {}", configuration.getLpVectorLayer());
		LOG.info("rpVectorLayers: {}", configuration.getRpVectorLayer());
		LOG.info("soVectorLayers: {}", configuration.getSoVectorLayer());
		LOG.info("-------------------------------------------");
	}

	private void logRasterLayerConfiguration(RasterLayerConfiguration configuration) {
		LOG.info("-------------------------------------------");
		LOG.info("Configuration of the raster layers");
		LOG.info("-------------------------------------------");
		LOG.info("XPlan Raster-WMS name: {}", configuration.getRasterWmsName());
		LOG.info("bpRasterLayers: {}", configuration.getBpRasterLayer());
		LOG.info("fpRasterLayers: {}", configuration.getFpRasterLayer());
		LOG.info("lpRasterLayers: {}", configuration.getLpRasterLayer());
		LOG.info("rpRasterLayers: {}", configuration.getRpRasterLayer());
		LOG.info("soRasterLayers: {}", configuration.getSoRasterLayer());
		LOG.info("-------------------------------------------");
	}

}
