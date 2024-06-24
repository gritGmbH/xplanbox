/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.configuration;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SemanticConformityLinkConfiguration;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationSource;
import de.latlon.xplan.manager.workspace.WorkspaceReloadAction;
import de.latlon.xplan.manager.workspace.WorkspaceReloaderConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationSource.geotiff;
import static de.latlon.xplan.manager.workspace.WorkspaceReloadAction.ALL;

/**
 * Provides access to the manager configuration.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ManagerConfiguration {

	static final String RASTER_CONFIG_CRS = "rasterConfigurationCrs";

	static final String RASTER_LAYER_SCALE_DENOMINATOR_MIN = "rasterLayerMinScaleDenominator";

	static final String RASTER_LAYER_SCALE_DENOMINATOR_MAX = "rasterLayerMaxScaleDenominator";

	static final String RASTER_CONFIG_TYPE = "rasterConfigurationType";

	static final String WORKSPACE_RELOAD_URLS = "workspaceReloadUrls";

	static final String WORKSPACE_RELOAD_API_KEY = "workspaceReloadApiKey";

	static final String WORKSPACE_RELOAD_USER = "workspaceReloadUser";

	static final String WORKSPACE_RELOAD_PASSWORD = "workspaceReloadPassword";

	static final String WORKSPACE_RELOAD_ACTION = "workspaceReloadAction";

	static final String PATH_TO_HALE_CLI = "pathToHaleCli";

	private static final Logger LOG = LoggerFactory.getLogger(ManagerConfiguration.class);

	private static final String MANAGER_CONFIGURATION = "managerConfiguration.properties";

	private final SortConfiguration sortConfiguration = new SortConfiguration();

	private final Map<String, String> environmentVariables;

	private String rasterConfigurationCrs;

	private RasterConfigurationSource rasterConfigurationType = geotiff;

	private double rasterLayerMinScaleDenominator = Double.NaN;

	private double rasterLayerMaxScaleDenominator = Double.NaN;

	private WorkspaceReloaderConfiguration workspaceReloaderConfiguration = new WorkspaceReloaderConfiguration();

	private SemanticConformityLinkConfiguration semanticConformityLinkConfiguration = new SemanticConformityLinkConfiguration();

	private Path synthesizerConfigDirectory;

	private String pathToHaleCli;

	private Path pathToHaleProjectDirectory;

	private CoupledResourceConfiguration coupledResourceConfiguration;

	public ManagerConfiguration(PropertiesLoader propertiesLoader) throws ConfigurationException {
		this(propertiesLoader, Collections.emptyMap());
	}

	public ManagerConfiguration(PropertiesLoader propertiesLoader, Map<String, String> environmentVariables)
			throws ConfigurationException {
		this.environmentVariables = environmentVariables;
		loadProperties(propertiesLoader);
		verifyConfiguration();
		logConfiguration();
	}

	/**
	 * @return the crs to use in the raster configuration, may be <code>null</code>
	 */
	public String getRasterConfigurationCrs() {
		return rasterConfigurationCrs;
	}

	/**
	 * @return the type to use in the raster configuration (geotiff or mapserver), never
	 * <code>null</code>
	 */
	public RasterConfigurationSource getRasterConfigurationType() {
		return rasterConfigurationType;
	}

	/**
	 * @return the max scale denominator the raster layer is visible (a value less than 0
	 * means the visibility is not limited)
	 */
	public double getRasterLayerMaxScaleDenominator() {
		return rasterLayerMaxScaleDenominator;
	}

	/**
	 * @return the min scale denominator the raster layer is visible (a value less than 0
	 * means the visibility is not limited)
	 */
	public double getRasterLayerMinScaleDenominator() {
		return rasterLayerMinScaleDenominator;
	}

	/**
	 * @return configuration for
	 * {@link de.latlon.xplan.manager.workspace.WorkspaceReloader}, never
	 * <code>null</code>
	 */
	public WorkspaceReloaderConfiguration getWorkspaceReloaderConfiguration() {
		return workspaceReloaderConfiguration;
	}

	/**
	 * @return the {@link SemanticConformityLinkConfiguration}, never <code>null</code>
	 */
	public SemanticConformityLinkConfiguration getSemanticConformityLinkConfiguration() {
		return semanticConformityLinkConfiguration;
	}

	/**
	 * @return the {@link SortConfiguration}, never <code>null</code>
	 */
	public SortConfiguration getSortConfiguration() {
		return sortConfiguration;
	}

	/**
	 * @return the directory containing the synthesizer configuration, may be
	 * <code>null</code>
	 */
	public Path getSynthesizerConfigurationDirectory() {
		return synthesizerConfigDirectory;
	}

	/**
	 * @return the absolute path to the hale cli
	 */
	public String getPathToHaleCli() {
		return pathToHaleCli;
	}

	/**
	 * @return the path to the hale project directory
	 */
	public Path getPathToHaleProjectDirectory() {
		return pathToHaleProjectDirectory;
	}

	/**
	 * @return the configuration to process coupled resources, may be <code>null</code>
	 */
	public CoupledResourceConfiguration getCoupledResourceConfiguration() {
		return coupledResourceConfiguration;
	}

	/**
	 * @param key of the environment variable, must not tbe <code>null</code>
	 * @return the value of the environment variable, may be <code>null</code> if not
	 * available
	 */
	public String getEnvironmentVariableValue(String key) {
		return environmentVariables.get(key);
	}

	private void loadProperties(PropertiesLoader propertiesLoader) throws ConfigurationException {
		if (propertiesLoader != null) {
			Properties loadProperties = propertiesLoader.loadProperties(MANAGER_CONFIGURATION);
			if (loadProperties != null) {
				rasterConfigurationCrs = parseRasterConfigurationCrs(loadProperties, RASTER_CONFIG_CRS);
				rasterConfigurationType = parseRasterConfigurationType(loadProperties);
				rasterLayerMinScaleDenominator = parseScaleDenominator(loadProperties,
						RASTER_LAYER_SCALE_DENOMINATOR_MIN);
				rasterLayerMaxScaleDenominator = parseScaleDenominator(loadProperties,
						RASTER_LAYER_SCALE_DENOMINATOR_MAX);
				workspaceReloaderConfiguration = parseWorkspaceReloaderConfiguration(loadProperties);
				parseSortConfiguration(loadProperties);
				parseSemanticConformityLinkConfiguration(loadProperties);
				pathToHaleCli = loadProperties.getProperty(PATH_TO_HALE_CLI);
				pathToHaleProjectDirectory = parsePathToHaleProjectDirectory(propertiesLoader);
				coupledResourceConfiguration = CoupledResourceConfiguration
					.parseCoupledResourceConfiguration(propertiesLoader, loadProperties);
			}
			synthesizerConfigDirectory = propertiesLoader.resolveDirectory("synthesizer");
		}
	}

	private void verifyConfiguration() {
		if (Double.isNaN(rasterLayerMinScaleDenominator) && Double.isNaN(rasterLayerMaxScaleDenominator))
			return;
		if (rasterLayerMinScaleDenominator < 0)
			throw new IllegalArgumentException("rasterLayerMinScaleDenominator should not be a negative value");
		if (rasterLayerMaxScaleDenominator < 0)
			throw new IllegalArgumentException("rasterLayerMaxScaleDenominator should not be a negative value");
		if (rasterLayerMinScaleDenominator >= rasterLayerMaxScaleDenominator)
			throw new IllegalArgumentException(
					"rasterLayerMinScaleDenominator must be less than rasterLayerMaxScaleDenominator");
	}

	private void logConfiguration() {
		LOG.info("-------------------------------------------");
		LOG.info("Configuration of the XPlanManager:");
		LOG.info("-------------------------------------------");
		LOG.info("  directory containing the synthesizer configuration: {}",
				synthesizerConfigDirectory != null && Files.exists(synthesizerConfigDirectory)
						? synthesizerConfigDirectory : "not configured");
		LOG.info("-------------------------------------------");
		LOG.info("  raster configuration");
		LOG.info("   - crs: {}", rasterConfigurationCrs);
		LOG.info("   - type: {}", rasterConfigurationType);
		LOG.info("   - min scale denominator: {}", rasterLayerMinScaleDenominator);
		LOG.info("   - max scale denominator: {}", rasterLayerMaxScaleDenominator);
		LOG.info("-------------------------------------------");
		LOG.info("  workspace reloader configuration");
		LOG.info("   - urls of service to reload: {}", workspaceReloaderConfiguration.getUrls().toString());
		if (workspaceReloaderConfiguration.isApiKeyConfigured())
			LOG.info("   - apiKey used for authentication: {}",
					replaceWithX(workspaceReloaderConfiguration.getApiKey()));
		else
			LOG.info("   - user/password used for authentication: {}/{}", workspaceReloaderConfiguration.getUser(),
					replaceWithX(workspaceReloaderConfiguration.getPassword()));
		LOG.info("-------------------------------------------");
		LOG.info("  path to HALE CLI: {}", pathToHaleCli);
		LOG.info("  path to HALE project: {}", pathToHaleProjectDirectory);
		LOG.info("-------------------------------------------");
		LOG.info("  CoupledResource");
		if (coupledResourceConfiguration == null) {
			LOG.info("   - not configured");
		}
		else {
			coupledResourceConfiguration.logConfiguration(LOG);
		}
		LOG.info("-------------------------------------------");
		sortConfiguration.logConfiguration(LOG);
		LOG.info("-------------------------------------------");
		semanticConformityLinkConfiguration.logConfiguration(LOG);
		LOG.info("-------------------------------------------");
		LOG.info("Additional environment variables (contains only the variables available via manager configuration)");
		environmentVariables.entrySet().forEach(entry -> LOG.info("   - {}: {}", entry.getKey(), entry.getValue()));
		LOG.info("-------------------------------------------");
	}

	private String parseRasterConfigurationCrs(Properties loadProperties, String rasterConfigCrs) {
		String rasterConfigurationCrsPropertyValue = loadProperties.getProperty(RASTER_CONFIG_CRS);
		if (rasterConfigurationCrsPropertyValue != null && !rasterConfigurationCrsPropertyValue.trim().isEmpty()) {
			return rasterConfigurationCrsPropertyValue;
		}
		return null;
	}

	private RasterConfigurationSource parseRasterConfigurationType(Properties loadProperties) {
		String rasterConfigTypePropertyValue = loadProperties.getProperty(RASTER_CONFIG_TYPE);
		if (rasterConfigTypePropertyValue != null) {
			try {
				return RasterConfigurationSource.valueOf(rasterConfigTypePropertyValue);
			}
			catch (IllegalArgumentException e) {
			}
		}
		return geotiff;
	}

	private WorkspaceReloaderConfiguration parseWorkspaceReloaderConfiguration(Properties loadProperties) {
		String urls = loadProperties.getProperty(WORKSPACE_RELOAD_URLS);
		String apiKey = loadProperties.getProperty(WORKSPACE_RELOAD_API_KEY);
		String user = loadProperties.getProperty(WORKSPACE_RELOAD_USER);
		String password = loadProperties.getProperty(WORKSPACE_RELOAD_PASSWORD);
		if (urls != null && !"".equals(urls) && (apiKey != null || (user != null && password != null))) {
			List<String> urlList = Arrays.asList(urls.split(","));
			WorkspaceReloadAction workspaceReloadAction = parseWorkspaceReloadAction(loadProperties);
			return new WorkspaceReloaderConfiguration(urlList, apiKey, user, password, workspaceReloadAction);
		}
		return new WorkspaceReloaderConfiguration();
	}

	private static WorkspaceReloadAction parseWorkspaceReloadAction(Properties loadProperties) {
		String workspaceReloadAction = loadProperties.getProperty(WORKSPACE_RELOAD_ACTION);
		if (workspaceReloadAction == null)
			return ALL;
		return WorkspaceReloadAction.valueOf(workspaceReloadAction);
	}

	private void parseSortConfiguration(Properties properties) {
		for (XPlanType type : XPlanType.values()) {
			for (XPlanVersion version : XPlanVersion.values()) {
				String key = "wmsSortDate_" + type + "_" + version;
				String property = properties.getProperty(key);
				if (property != null && !property.isEmpty()) {
					String[] split = property.split(",");
					if (split.length != 2) {
						LOG.warn("Property with key {} cannot be parsed as wmsSortDate-Configuration. "
								+ "The property value must contain the FeatureType and PropertyName comma-seperated.");
					}
					else {
						sortConfiguration.addSortField(type, version, split[0], split[1]);
					}
				}
			}
		}
	}

	private void parseSemanticConformityLinkConfiguration(Properties properties) {
		for (XPlanVersion version : XPlanVersion.values()) {
			String key = "linkSemanticConformity_" + version;
			String property = properties.getProperty(key);
			if (property != null && !property.isEmpty()) {
				semanticConformityLinkConfiguration.addLink(version, property);
			}
		}
	}

	private Path parsePathToHaleProjectDirectory(PropertiesLoader propertiesLoader) {
		Path haleProject = propertiesLoader.resolveDirectory("hale");
		if (directoryExistsAndIsDirectory(haleProject))
			return haleProject;
		return null;
	}

	private boolean directoryExistsAndIsDirectory(Path directory) {
		return directory != null && Files.exists(directory) && Files.isDirectory(directory);
	}

	private Double parseScaleDenominator(Properties properties, String propName) {
		String propertyValue = properties.getProperty(propName);
		if (propertyValue == null || "".equals(propertyValue))
			return Double.NaN;
		return Double.parseDouble(propertyValue);
	}

	private String replaceWithX(String apiKey) {
		int length = apiKey.length();
		return "X".repeat(length);
	}

}
