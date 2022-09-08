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
package de.latlon.xplan.manager.configuration;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SemanticConformityLinkConfiguration;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType;
import de.latlon.xplan.manager.workspace.WorkspaceReloadAction;
import de.latlon.xplan.manager.workspace.WorkspaceReloaderConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static de.latlon.xplan.manager.workspace.WorkspaceReloadAction.ALL;

/**
 * Provides access to the manager configuration.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ManagerConfiguration {

	static final String CATEGORIES_TO_PARTS_KEY = "categoriesToParts";

	static final String RASTER_CONFIG_CRS = "rasterConfigurationCrs";

	static final String RASTER_LAYER_SCALE_DENOMINATOR_MIN = "rasterLayerMinScaleDenominator";

	static final String RASTER_LAYER_SCALE_DENOMINATOR_MAX = "rasterLayerMaxScaleDenominator";

	static final String ACTIVATE_SEPARATED_DATAMANAGEMENT = "activateSeparatedDataManagement";

	static final String RASTER_CONFIG_TYPE = "rasterConfigurationType";

	static final String WORKSPACE_RELOAD_URLS = "workspaceReloadUrls";

	static final String WORKSPACE_RELOAD_USER = "workspaceReloadUser";

	static final String WORKSPACE_RELOAD_PASSWORD = "workspaceReloadPassword";

	static final String WORKSPACE_RELOAD_ACTION = "workspaceReloadAction";

	static final String PATH_TO_HALE_CLI = "pathToHaleCli";

	private static final Logger LOG = LoggerFactory.getLogger(ManagerConfiguration.class);

	private static final String MANAGER_CONFIGURATION = "managerConfiguration.properties";

	private final Map<String, List<String>> categoriesToParts = new HashMap<>();

	private final SortConfiguration sortConfiguration = new SortConfiguration();

	private String rasterConfigurationCrs;

	private RasterConfigurationType rasterConfigurationType;

	private double rasterLayerMinScaleDenominator = Double.NaN;

	private double rasterLayerMaxScaleDenominator = Double.NaN;

	private WorkspaceReloaderConfiguration workspaceReloaderConfiguration = new WorkspaceReloaderConfiguration();

	private InternalIdRetrieverConfiguration internalIdRetrieverConfiguration = new InternalIdRetrieverConfiguration();

	private SemanticConformityLinkConfiguration semanticConformityLinkConfiguration = new SemanticConformityLinkConfiguration();

	private Path synthesizerConfigDirectory;

	private String pathToHaleCli;

	private Path pathToHaleProjectDirectory;

	private CoupledResourceConfiguration coupledResourceConfiguration;

	public ManagerConfiguration(PropertiesLoader propertiesLoader) throws ConfigurationException {
		loadProperties(propertiesLoader);
		verifyConfiguration();
		logConfiguration();
	}

	/**
	 * Retrieves the category mappings (category assigned to a list of parts).
	 * @return the category mapping, may be empty but never <code>null</code>
	 */
	public Map<String, List<String>> getCategoryMapping() {
		return categoriesToParts;
	}

	/**
	 * @return the crs to use in the raster configuration, may be <code>null</code>
	 */
	public String getRasterConfigurationCrs() {
		return rasterConfigurationCrs;
	}

	/**
	 * @return the type to use in the raster configuration (gdal or geotiff), never
	 * <code>null</code>
	 */
	public RasterConfigurationType getRasterConfigurationType() {
		return rasterConfigurationType;
	}

	/**
	 * @return the max scale denominator the raster layer is visible (a value less then 0
	 * means the visibility is not limited)
	 */
	public double getRasterLayerMaxScaleDenominator() {
		return rasterLayerMaxScaleDenominator;
	}

	/**
	 * @return the min scale denominator the raster layer is visible (a value less then 0
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
	 * @return the {@link InternalIdRetrieverConfiguration}, never <code>null</code>
	 */
	public InternalIdRetrieverConfiguration getInternalIdRetrieverConfiguration() {
		return internalIdRetrieverConfiguration;
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

	private void loadProperties(PropertiesLoader propertiesLoader) throws ConfigurationException {
		if (propertiesLoader != null) {
			Properties loadProperties = propertiesLoader.loadProperties(MANAGER_CONFIGURATION);
			if (loadProperties != null) {
				String categoriesToPartsProperty = loadProperties.getProperty(CATEGORIES_TO_PARTS_KEY);
				if (categoriesToPartsProperty != null && !"".equals(categoriesToPartsProperty)) {
					String[] categoriesWithParts = categoriesToPartsProperty.split(";");
					parseCategories(categoriesWithParts);
				}
				rasterConfigurationCrs = loadProperties.getProperty(RASTER_CONFIG_CRS);
				rasterConfigurationType = parseRasterConfigurationType(loadProperties);
				rasterLayerMinScaleDenominator = parseScaleDenominator(loadProperties,
						RASTER_LAYER_SCALE_DENOMINATOR_MIN);
				rasterLayerMaxScaleDenominator = parseScaleDenominator(loadProperties,
						RASTER_LAYER_SCALE_DENOMINATOR_MAX);
				workspaceReloaderConfiguration = parseWorkspaceReloaderConfiguration(loadProperties);
				internalIdRetrieverConfiguration = parseInternalIdRetrieverConfiguration(loadProperties);
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
		LOG.info("-------------------------------------------");
		LOG.info("  InternalIdRetriever");
		LOG.info("   - workspace: {}", internalIdRetrieverConfiguration.getWorkspaceName());
		LOG.info("   - jdbc connection id: {}", internalIdRetrieverConfiguration.getJdbcConnectionId());
		LOG.info("   - internalId label: {}", internalIdRetrieverConfiguration.getInternalIdLabel());
		LOG.info("   - internalName label: {}", internalIdRetrieverConfiguration.getInternalNameLabel());
		LOG.info("   - SQL Matching Ids: {}", internalIdRetrieverConfiguration.getSelectMatchingIdsSql());
		LOG.info("   - SQL All: {}", internalIdRetrieverConfiguration.getSelectAllSql());
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
	}

	private void parseCategories(String[] categoriesWithParts) throws ConfigurationException {
		for (String categoryWithParts : categoriesWithParts) {
			String categoryName = parseCategoryName(categoryWithParts);
			List<String> partsAsList = parseParts(categoryWithParts);
			categoriesToParts.put(categoryName, partsAsList);
		}
	}

	private String parseCategoryName(String categoryWithParts) throws ConfigurationException {
		if (categoryWithParts.contains("(")) {
			int indexOfCategoryEnd = categoryWithParts.indexOf("(");
			return categoryWithParts.substring(0, indexOfCategoryEnd);
		}
		throw new ConfigurationException("Categories was not correctly configured!");
	}

	private RasterConfigurationType parseRasterConfigurationType(Properties loadProperties) {
		String rasterConfigTypePropertyValue = loadProperties.getProperty(RASTER_CONFIG_TYPE);
		if (rasterConfigTypePropertyValue != null) {
			try {
				return RasterConfigurationType.valueOf(rasterConfigTypePropertyValue);
			}
			catch (IllegalArgumentException e) {
			}
		}
		return RasterConfigurationType.gdal;
	}

	private List<String> parseParts(String categoryWithParts) {
		int indexOfPartsBegin = categoryWithParts.indexOf("(") + 1;
		int indexOfPartsEnd = categoryWithParts.indexOf(")");
		String allParts = categoryWithParts.substring(indexOfPartsBegin, indexOfPartsEnd);
		String[] parts = allParts.split(",");
		return cleanupParts(parts);
	}

	private List<String> cleanupParts(String[] parts) {
		List<String> partsAsList = new ArrayList<>();
		for (String part : parts) {
			partsAsList.add(part.trim());
		}
		return partsAsList;
	}

	private WorkspaceReloaderConfiguration parseWorkspaceReloaderConfiguration(Properties loadProperties) {
		String urls = loadProperties.getProperty(WORKSPACE_RELOAD_URLS);
		String user = loadProperties.getProperty(WORKSPACE_RELOAD_USER);
		String password = loadProperties.getProperty(WORKSPACE_RELOAD_PASSWORD);
		if (urls != null && user != null && password != null && !"".equals(urls)) {
			List<String> urlList = Arrays.asList(urls.split(","));
			WorkspaceReloadAction workspaceReloadAction = parseWorkspaceReloadAction(loadProperties);
			return new WorkspaceReloaderConfiguration(urlList, user, password, workspaceReloadAction);
		}
		return new WorkspaceReloaderConfiguration();
	}

	private static WorkspaceReloadAction parseWorkspaceReloadAction(Properties loadProperties) {
		String workspaceReloadAction = loadProperties.getProperty(WORKSPACE_RELOAD_ACTION);
		if (workspaceReloadAction == null)
			return ALL;
		return WorkspaceReloadAction.valueOf(workspaceReloadAction);
	}

	private InternalIdRetrieverConfiguration parseInternalIdRetrieverConfiguration(Properties properties) {
		InternalIdRetrieverConfiguration internalIdRetrieverConfiguration = new InternalIdRetrieverConfiguration();
		String workspaceName = properties.getProperty("workspaceName");
		if (workspaceName != null)
			internalIdRetrieverConfiguration.setWorkspaceName(workspaceName);
		String jdbcConnectionId = properties.getProperty("jdbcConnectionId");
		if (jdbcConnectionId != null)
			internalIdRetrieverConfiguration.setJdbcConnectionId(jdbcConnectionId);
		String internalIdLabel = properties.getProperty("internalIdLabel");
		if (internalIdLabel != null)
			internalIdRetrieverConfiguration.setInternalIdLabel(internalIdLabel);
		String internalNameLabel = properties.getProperty("internalNameLabel");
		if (internalNameLabel != null)
			internalIdRetrieverConfiguration.setInternalNameLabel(internalNameLabel);
		String selectMatchingIdsSql = properties.getProperty("selectMatchingIdsSql");
		if (selectMatchingIdsSql != null)
			internalIdRetrieverConfiguration.setSelectMatchingIdsSql(selectMatchingIdsSql);
		String selectAllIdsSql = properties.getProperty("selectAllIdsSql");
		if (selectAllIdsSql != null)
			internalIdRetrieverConfiguration.setSelectAllSql(selectAllIdsSql);
		return internalIdRetrieverConfiguration;
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

	private boolean parseBoolean(Properties loadProperties, String propName, boolean defaultValue) {
		String property = loadProperties.getProperty(propName);
		if (property == null || "".equals(property))
			return defaultValue;
		return Boolean.parseBoolean(property);
	}

}
