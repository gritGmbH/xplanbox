/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.wms;

import de.latlon.xplan.commons.XPlanType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllBytes;
import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MasterportalConfigWriter {

	private static final Logger LOG = LoggerFactory.getLogger(MasterportalConfigWriter.class);

	private static final String CONFIG_TEMPLATE = "config.template.json";

	private static final String SERVICE_TEMPLATE = "service.template.json";

	private static final String SERVICES_TEMPLATE = "services-internet.template.json";

	private static final String SERVICES_JSON = "services-internet.json";

	private static final String CONFIG_FILENAME_TEMPLATE = "config.%s.json";

	private final String configTemplate;

	private final String serviceTemplate;

	private final String servicesTemplate;

	private final String validatorWmsEndpoint;

	private final Path configDirectory;

	private final Path servicesConfigFile;

	private final ConcurrentMap<String, String> idToServiceConfig = new ConcurrentHashMap<>();

	public MasterportalConfigWriter(String validatorWmsEndpoint) throws MapPreviewCreationException {
		this.validatorWmsEndpoint = validatorWmsEndpoint;
		Path masterportalDirectory = getMasterportalDirectory();
		this.configDirectory = getConfigDirectory(masterportalDirectory);
		this.servicesConfigFile = getServicesConfigFile(this.configDirectory);
		Path templateDirectory = getTemplateDirectory(masterportalDirectory);
		this.configTemplate = readTemplate(templateDirectory, CONFIG_TEMPLATE);
		this.serviceTemplate = readTemplate(templateDirectory, SERVICE_TEMPLATE);
		this.servicesTemplate = readTemplate(templateDirectory, SERVICES_TEMPLATE);
	}

	String createMasterportalConfig(int managerId, XPlanType type) throws MapPreviewCreationException {
		String id = UUID.randomUUID().toString();
		Path configFile = configDirectory.resolve(String.format(CONFIG_FILENAME_TEMPLATE, id));
		createConfigJson(id, configFile);
		addToServicesJson(id, managerId, type);
		return configFile.getFileName().toString();
	}

	private void createConfigJson(String id, Path configFile) throws MapPreviewCreationException {
		LOG.info("Write config file {}", configFile);
		OutputStream out = null;
		try {
			Files.createFile(configFile);
			out = Files.newOutputStream(configFile);
			Files.write(configFile, configTemplate.replace("${PLANID}", id).getBytes());
		}
		catch (Exception e) {
			throw new MapPreviewCreationException("Could not write config file " + configFile, e);
		}
		finally {
			closeQuietly(out, null);
		}
	}

	private void addToServicesJson(String id, int managerId, XPlanType type) throws MapPreviewCreationException {
		try {
			String serviceConfigSection = createServiceConfigFromTemplate(id, managerId, type);
			idToServiceConfig.put(id, serviceConfigSection);
			String servicesConfigSection = createServicesConfigFromTemplate();
			synchronized (servicesConfigFile) {
				LOG.info("Append service config to {}", servicesConfigFile);
				Files.write(servicesConfigFile, servicesConfigSection.getBytes());
			}
		}
		catch (IOException e) {
			throw new MapPreviewCreationException("Could not add service config", e);
		}
	}

	private String createServiceConfigFromTemplate(String id, int managerId, XPlanType type) {
		String bp_planvektor = getLayerNameByType(type);
		return serviceTemplate.replace("${PLANID}", id)
			.replace("${WMSURL}", validatorWmsEndpoint)
			.replace("${MANAGERID}", Integer.toString(managerId))
			.replace("${LAYERS}", bp_planvektor);
	}

	private String getLayerNameByType(XPlanType type) {
		switch (type) {
			case FP_Plan:
				return "FP_Planvektor";
			case LP_Plan:
				return "LP_Planvektor";
			case RP_Plan:
				return "RP_Planvektor";
			case SO_Plan:
				return "SO_Planvektor";
			default:
				return "BP_Planvektor";
		}
	}

	private String createServicesConfigFromTemplate() {
		String allServiceConfigs = idToServiceConfig.values().stream().collect(Collectors.joining(","));
		return servicesTemplate.replace("${SERVICESCONFIG}", allServiceConfigs);
	}

	private Path getMasterportalDirectory() throws MapPreviewCreationException {
		try {
			URL masterportal = MasterportalConfigWriter.class.getResource("../../../../../../../masterportal");
			if (masterportal == null)
				throw new MapPreviewCreationException("masterportal directory does not exist");
			Path path = Paths.get(masterportal.toURI());
			if (!Files.exists(path))
				throw new MapPreviewCreationException("masterportal directory does not exist");
			return path;
		}
		catch (URISyntaxException e) {
			throw new MapPreviewCreationException("Config directory in masterportal is not available");
		}
	}

	private Path getConfigDirectory(Path masterportalDirectory) throws MapPreviewCreationException {
		Path path = masterportalDirectory.resolve("config");
		if (!Files.exists(path))
			throw new MapPreviewCreationException("Config directory in masterportal does not exist");
		return path;
	}

	private Path getServicesConfigFile(Path configDirectory) throws MapPreviewCreationException {
		Path servicesConfig = configDirectory.resolve(SERVICES_JSON);
		if (!Files.exists(servicesConfig))
			throw new MapPreviewCreationException("File " + servicesConfig + " does not exist");
		return servicesConfig;
	}

	private Path getTemplateDirectory(Path masterportalDirectory) throws MapPreviewCreationException {
		Path path = masterportalDirectory.resolve("template");
		if (!Files.exists(path))
			throw new MapPreviewCreationException("Config directory in masterportal does not exist");
		return path;
	}

	private String readTemplate(Path templateirectory, String fileName) throws MapPreviewCreationException {
		Path template = templateirectory.resolve(fileName);
		if (template == null)
			throw new MapPreviewCreationException("Template " + fileName + " does not exist.");
		try {
			byte[] bytes = readAllBytes(template);
			return new String(bytes);
		}
		catch (IOException e) {
			throw new MapPreviewCreationException("Template " + fileName + " could not be read", e);
		}
	}

}
