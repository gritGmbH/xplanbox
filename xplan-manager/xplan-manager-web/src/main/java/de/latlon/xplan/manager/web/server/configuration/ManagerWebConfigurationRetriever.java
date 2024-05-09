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
package de.latlon.xplan.manager.web.server.configuration;

import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;
import de.latlon.xplan.manager.web.shared.RasterLayerConfiguration;
import de.latlon.xplan.manager.web.shared.VectorLayerConfiguration;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;

import java.util.Properties;

import static java.lang.Double.parseDouble;

/**
 * Reads the managerWebConfiguration.properties configuration file.
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 */
public class ManagerWebConfigurationRetriever {

	private static final String MANAGER_WEB_CONFIGURATION_PROPERTIES = "managerWebConfiguration.properties";

	/**
	 * Sets up an instance of <link>ManagerWebConfiguration</link>
	 * @return the web configuration, never <code>null</code>
	 * @throws ConfigurationException if the configuration could not be parsed
	 */
	public ManagerWebConfiguration setupManagerWebConfiguration() throws ConfigurationException {
		SystemPropertyPropertiesLoader configurationRetriever = new SystemPropertyPropertiesLoader(this.getClass());
		Properties props = configurationRetriever.loadProperties(MANAGER_WEB_CONFIGURATION_PROPERTIES);
		checkIfPropsNotNull(props);
		return createManagerWebConfigurationFromProperties(props);
	}

	/**
	 * Sets up an instance of <link>MapPreviewConfiguration</link>
	 * @return the web configuration, never <code>null</code>
	 * @throws ConfigurationException if the configuration could not be parsed
	 */
	public MapPreviewConfiguration setupMapPreviewConfiguration() throws ConfigurationException {
		SystemPropertyPropertiesLoader configurationRetriever = new SystemPropertyPropertiesLoader(this.getClass());
		Properties props = configurationRetriever.loadProperties(MANAGER_WEB_CONFIGURATION_PROPERTIES);
		checkIfPropsNotNull(props);
		return createMapPreviewConfigurationFromProperties(props);
	}

	private ManagerWebConfiguration createManagerWebConfigurationFromProperties(Properties props)
			throws ConfigurationException {
		boolean internalIdActivated = parseActivateInternalIdDialog(props);
		boolean legislationStatusActivated = parseActivateLegislationStatusDialog(props);
		boolean validityPeriodActivated = parseActivateValidityPeriodDialog(props);
		boolean editorActivated = parseActivateEditor(props);
		boolean publishingInspirePluActivated = parseActivatePublishingInspirePlu(props);

		String defaultCrs = retrieveMandatoryPropertyValue(props, "defaultCrs");
		String[] chooseCrs = parseChooseCrs(props);
		String[] categoryFilterValues = parseCategoryFilterValues(props);
		String[] hiddenColumns = parseHiddenColumns(props);
		return new ManagerWebConfiguration(internalIdActivated, legislationStatusActivated, validityPeriodActivated,
				editorActivated, publishingInspirePluActivated, defaultCrs, chooseCrs, categoryFilterValues,
				hiddenColumns);
	}

	private MapPreviewConfiguration createMapPreviewConfigurationFromProperties(Properties props)
			throws ConfigurationException {
		String basemapUrl = retrieveMandatoryPropertyValue(props, "basemapUrl");
		String basemapName = retrieveMandatoryPropertyValue(props, "basemapName");
		String basemapLayer = retrieveMandatoryPropertyValue(props, "basemapLayer");
		String wmsUrl = retrieveMandatoryPropertyValue(props, "wmsUrl");
		String wmsEndpoint = props.getProperty("wmsEndpoint");
		String wmsPreEndpoint = props.getProperty("wmsPreEndpoint");
		String wmsArchiveEndpoint = props.getProperty("wmsArchiveEndpoint");
		XPlanEnvelope maxExtent = parseMaxExtent(props);
		VectorLayerConfiguration vectorLayerConfiguration = createVectorLayerConfigurationFromProperties(props);
		RasterLayerConfiguration rasterLayerConfiguration = createRasterLayerConfigurationFromProperties(props);
		return new MapPreviewConfiguration(basemapUrl, basemapName, basemapLayer, wmsUrl, wmsEndpoint, wmsPreEndpoint,
				wmsArchiveEndpoint, maxExtent, vectorLayerConfiguration, rasterLayerConfiguration);
	}

	private VectorLayerConfiguration createVectorLayerConfigurationFromProperties(Properties props)
			throws ConfigurationException {
		String vectorWmsName = retrieveMandatoryPropertyValue(props, "vectorWmsName");
		String bpVectorLayer = retrieveMandatoryPropertyValue(props, "bpVectorLayer");
		String fpVectorLayer = retrieveMandatoryPropertyValue(props, "fpVectorLayer");
		String lpVectorLayer = retrieveMandatoryPropertyValue(props, "lpVectorLayer");
		String rpVectorLayer = retrieveMandatoryPropertyValue(props, "rpVectorLayer");
		String soVectorLayer = retrieveMandatoryPropertyValue(props, "soVectorLayer");
		return new VectorLayerConfiguration(vectorWmsName, bpVectorLayer, fpVectorLayer, lpVectorLayer, rpVectorLayer,
				soVectorLayer);
	}

	private RasterLayerConfiguration createRasterLayerConfigurationFromProperties(Properties props)
			throws ConfigurationException {
		String rasterWmsName = retrieveMandatoryPropertyValue(props, "rasterWmsName");
		String bpRasterLayer = retrieveMandatoryPropertyValue(props, "bpRasterLayer");
		String fpRasterLayer = retrieveMandatoryPropertyValue(props, "fpRasterLayer");
		String lpRasterLayer = retrieveMandatoryPropertyValue(props, "lpRasterLayer");
		String rpRasterLayer = retrieveMandatoryPropertyValue(props, "rpRasterLayer");
		String soRasterLayer = retrieveMandatoryPropertyValue(props, "soRasterLayer");
		return new RasterLayerConfiguration(rasterWmsName, bpRasterLayer, fpRasterLayer, lpRasterLayer, rpRasterLayer,
				soRasterLayer);
	}

	private boolean parseActivateInternalIdDialog(Properties props) throws ConfigurationException {
		return "true".equals(retrieveMandatoryPropertyValue(props, "activateInternalIdDialog"));
	}

	private boolean parseActivateLegislationStatusDialog(Properties props) throws ConfigurationException {
		return "true".equals(props.getProperty("activateLegislationStatusDialog"));
	}

	private boolean parseActivateValidityPeriodDialog(Properties props) throws ConfigurationException {
		return "true".equals(props.getProperty("activateValidityPeriodDialog"));
	}

	private boolean parseActivateEditor(Properties props) throws ConfigurationException {
		return "true".equals(props.getProperty("activateEditor"));
	}

	private boolean parseActivatePublishingInspirePlu(Properties props) throws ConfigurationException {
		return "true".equals(props.getProperty("activatePublishingInspirePlu"));
	}

	private String[] parseChooseCrs(Properties props) throws ConfigurationException {
		return parseAsArray(props, "chooseCrs");
	}

	private String[] parseCategoryFilterValues(Properties props) throws ConfigurationException {
		return parseAsArray(props, "categoryFilterValues");
	}

	private String[] parseHiddenColumns(Properties props) throws ConfigurationException {
		if (props.containsKey("hiddenColumns"))
			return parseAsArray(props, "hiddenColumns");
		return new String[] {};
	}

	private String[] parseAsArray(Properties props, String key) throws ConfigurationException {
		String chooseCrs = retrieveMandatoryPropertyValue(props, key);
		return chooseCrs.split(",");
	}

	private XPlanEnvelope parseMaxExtent(Properties props) throws ConfigurationException {
		String maxExtent = retrieveMandatoryPropertyValue(props, "mapExtent");
		String[] split = maxExtent.split(",");
		double minX = parseDouble(split[0].trim());
		double minY = parseDouble(split[1].trim());
		double maxX = parseDouble(split[2].trim());
		double maxY = parseDouble(split[3].trim());
		String mapCrs = retrieveMandatoryPropertyValue(props, "mapCrs");
		return new XPlanEnvelope(minX, minY, maxX, maxY, mapCrs);
	}

	private void checkIfPropsNotNull(Properties props) throws ConfigurationException {
		if (props == null)
			throw new ConfigurationException(
					"Configuration properties could not be loaded! Is the configuration file missing?");
	}

	private String retrieveMandatoryPropertyValue(Properties props, String propName) throws ConfigurationException {
		String prop = props.getProperty(propName);
		if (prop == null)
			throw new ConfigurationException(
					"Property " + propName + " could not be found! Please check the configuration file.");
		return prop;
	}

}
