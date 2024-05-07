/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.configuration;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CoupledResourceConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(CoupledResourceConfiguration.class);

	private final String planWerkWmsBaseUrl;

	private final Map<XPlanType, String> planWerkWmsGetMapLayers = new HashMap<>();

	private final Map<XPlanType, String> planWerkWmsGetMapStyles = new HashMap<>();

	private final int planWerkWmsGetMapWidth;

	private final int planWerkWmsGetMapHeight;

	private final String cswUrlProvidingDatasetMetadata;

	private final Path metadataConfigDirectory;

	private final Path directoryToStoreMetadata;

	/**
	 * Instantiates the configuration and checks mandatory parameters.
	 * @param cswUrlProvidingDatasetMetadata never <code>null</code>
	 * @param metadataConfigDirectory never <code>null</code> and must exist
	 * @param directoryToStoreMetadata never <code>null</code> and must exist
	 * @param planWerkWmsBaseUrl never <code>null</code>
	 * @param planWerkWmsGetMapWidth default: 750 if less or equal than 0
	 * @param planWerkWmsGetMapHeight default: 750 if less or equal than 0
	 * @throws IllegalArgumentException if at least one of the mandatory parameters is
	 * null or one of the directories does not exist
	 */
	public CoupledResourceConfiguration(String cswUrlProvidingDatasetMetadata, Path metadataConfigDirectory,
			Path directoryToStoreMetadata, String planWerkWmsBaseUrl, int planWerkWmsGetMapWidth,
			int planWerkWmsGetMapHeight) {
		checkParameter(cswUrlProvidingDatasetMetadata, planWerkWmsBaseUrl, metadataConfigDirectory,
				directoryToStoreMetadata);
		this.cswUrlProvidingDatasetMetadata = cswUrlProvidingDatasetMetadata;
		this.metadataConfigDirectory = metadataConfigDirectory;
		this.directoryToStoreMetadata = directoryToStoreMetadata;
		this.planWerkWmsBaseUrl = planWerkWmsBaseUrl;
		this.planWerkWmsGetMapWidth = planWerkWmsGetMapWidth >= 0 ? planWerkWmsGetMapWidth : 750;
		this.planWerkWmsGetMapHeight = planWerkWmsGetMapHeight >= 0 ? planWerkWmsGetMapHeight : 750;
	}

	public String getCswUrlProvidingDatasetMetadata() {
		return cswUrlProvidingDatasetMetadata;
	}

	public Path getMetadataConfigDirectory() {
		return metadataConfigDirectory;
	}

	public Path getDirectoryToStoreMetadata() {
		return directoryToStoreMetadata;
	}

	public String getPlanWerkWmsBaseUrl() {
		return planWerkWmsBaseUrl;
	}

	public int getPlanWerkWmsGetMapWidth() {
		return planWerkWmsGetMapWidth;
	}

	public int getPlanWerkWmsGetMapHeight() {
		return planWerkWmsGetMapHeight;
	}

	public String getLayerByType(XPlanType type) {
		return this.planWerkWmsGetMapLayers.get(type);
	}

	public String getStyleByType(XPlanType type) {
		return this.planWerkWmsGetMapStyles.get(type);
	}

	public void addPlanWerkWmsGetMapLayer(XPlanType type, String layer) {
		this.planWerkWmsGetMapLayers.put(type, layer);

	}

	public void addPlanWerkWmsGetMapStyle(XPlanType type, String style) {
		this.planWerkWmsGetMapStyles.put(type, style);
	}

	/**
	 * Logs the configuration on info level.
	 * @param log to log into, never <code>null</code>
	 */
	public void logConfiguration(final Logger log) {
		log.info("  CoupledResourceConfiguration");

		log.info("   - planWerkWmsBaseUrl: {}", planWerkWmsBaseUrl);
		log.info("   - planWerkWmsGetMapWidth: {}", planWerkWmsGetMapWidth);
		log.info("   - planWerkWmsGetMapHeight: {}", planWerkWmsGetMapHeight);
		for (Map.Entry<XPlanType, String> layer : planWerkWmsGetMapLayers.entrySet()) {
			log.info("   - Layer of type {}: {}", layer.getKey(), layer.getValue());
		}
		for (Map.Entry<XPlanType, String> style : planWerkWmsGetMapStyles.entrySet()) {
			log.info("   - Style of type {}: {}", style.getKey(), style.getValue());
		}
		log.info("   - CSW Url: {}", cswUrlProvidingDatasetMetadata);
		log.info("   - Metadata config directory: {}", metadataConfigDirectory);
		log.info("   - Directory to store dataset metadata: {}", directoryToStoreMetadata);
	}

	public static CoupledResourceConfiguration parseCoupledResourceConfiguration(PropertiesLoader propertiesLoader,
			Properties properties) {
		String cswUrlProvidingDatasetMetadata = properties.getProperty("cswUrlProvidingDatasetMetadata");
		Path directoryToStoreMetadata = getDirectoryToStoreMetadata(properties);
		Path metadataConfigDirectory = propertiesLoader.resolveDirectory("metadata");
		String planWerkWmsBaseUrl = properties.getProperty("planWerkWmsBaseUrl");
		int planWerkWmsGetMapWidth = parseInteger(properties, "planWerkWmsGetMapWidth", 750);
		int planWerkWmsGetMapHeight = parseInteger(properties, "planWerkWmsGetMapHeight", 750);

		try {
			CoupledResourceConfiguration configuration = new CoupledResourceConfiguration(
					cswUrlProvidingDatasetMetadata, metadataConfigDirectory, directoryToStoreMetadata,
					planWerkWmsBaseUrl, planWerkWmsGetMapWidth, planWerkWmsGetMapHeight);

			for (XPlanType type : XPlanType.values()) {
				String layerKey = "planWerkWmsGetMapLayers_" + type;
				String layer = properties.getProperty(layerKey, getDefaultValue(type));
				configuration.addPlanWerkWmsGetMapLayer(type, layer);
				String styleKey = "planWerkWmsGetMapStyles_" + type;
				String style = properties.getProperty(styleKey, "");
				configuration.addPlanWerkWmsGetMapStyle(type, style);
			}
			return configuration;
		}
		catch (IllegalArgumentException e) {
			LOG.warn("Data-Service-Coupling configuration is not configured or contains invalid entries: {}",
					e.getMessage());
		}
		return null;
	}

	private void checkParameter(String cswUrlProvidingDatasetMetadata, String planWerkWmsBaseUrl,
			Path metadataConfigDirectory, Path directoryToStoreMetadata) {
		if (cswUrlProvidingDatasetMetadata == null || cswUrlProvidingDatasetMetadata.isEmpty())
			throw new IllegalArgumentException("Property cswUrlProvidingDatasetMetadata is null or empty");
		if (planWerkWmsBaseUrl == null || planWerkWmsBaseUrl.isEmpty())
			throw new IllegalArgumentException("Property planWerkWmsBaseUrl is null or empty");
		if (metadataConfigDirectory == null)
			throw new IllegalArgumentException("Property metadataConfigDirectory is missing");
		if (!Files.exists(metadataConfigDirectory))
			throw new IllegalArgumentException(
					"metadataConfigDirectory (" + metadataConfigDirectory + ") does not exist");
		if (directoryToStoreMetadata == null)
			throw new IllegalArgumentException("Property directoryToStoreMetadata is missing");
		if (!Files.exists(directoryToStoreMetadata))
			throw new IllegalArgumentException(
					"directoryToStoreMetadata (" + directoryToStoreMetadata + ") does not exist");
	}

	private static String getDefaultValue(XPlanType type) {
		switch (type) {
			case RP_Plan:
				return "RP_Planvektor,RP_Planraster";
			case LP_Plan:
				return "LP_Planvektor,LP_Planraster";
			case FP_Plan:
				return "FP_Planvektor,FP_Planraster";
			case SO_Plan:
				return "SO_Planvektor,SO_Planraster";
			case BP_Plan:
			default:
				return "BP_Planvektor,BP_Planraster";
		}

	}

	@SuppressFBWarnings(value = "PATH_TRAVERSAL_IN")
	private static Path getDirectoryToStoreMetadata(Properties properties) {
		String directoryToStoreMetadata = properties.getProperty("directoryToStoreMetadata");
		if (directoryToStoreMetadata != null)
			return Paths.get(directoryToStoreMetadata);
		return null;
	}

	private static int parseInteger(Properties loadProperties, String propName, int defaultValue) {
		String property = loadProperties.getProperty(propName);
		if (property == null || "".equals(property))
			return defaultValue;
		return Integer.parseInt(property);
	}

}
