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
package de.latlon.xplan.validator.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static java.nio.file.Files.createTempDirectory;

/**
 * Parses validator configuration and returns {@link ValidatorConfiguration}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class ValidatorConfigurationParser {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorConfigurationParser.class);

	private static final String VALIDATOR_CONFIGURATION_PROPERTIES = "validatorConfiguration.properties";

	private static final String VALIDATION_REPORT_DIRECTORY = "validationReportDirectory";

	private static final String VALIDATION_RULES_DIRECTORY = "validationRulesDirectory";

	private static final String VALIDATOR_WMS_ENDPOINT = "validatorWmsEndpoint";

	/**
	 * Parse validator configuration.
	 * @param propertiesLoader properties loader used to load properties from
	 * configuration file, never <code>null</code>
	 * @return validator configuration, never <code>null</code>
	 * @throws ConfigurationException if the properties file could not be loaded
	 * @throws IOException if the validation report directory could not be created
	 * @throws IllegalArgumentException if the propertiesLoader is <code>null</code>
	 */
	public ValidatorConfiguration parse(PropertiesLoader propertiesLoader) throws ConfigurationException, IOException {
		checkParameters(propertiesLoader);

		ValidatorConfiguration configuration = parseConfiguration(propertiesLoader);
		logConfiguration(configuration);
		return configuration;
	}

	private ValidatorConfiguration parseConfiguration(PropertiesLoader propertiesLoader)
			throws IOException, ConfigurationException {
		Properties properties = propertiesLoader.loadProperties(VALIDATOR_CONFIGURATION_PROPERTIES);
		Path reportDirectory = createReportDirectory(properties);
		Path rulesDirectory = createRulesDirectory(properties);
		String validatorWmsEndpoint = parseValidatorWmsEndpoint(properties);
		List<ValidatorProfile> validatorProfiles = parseValidatorProfiles(propertiesLoader);
		return new ValidatorConfiguration(reportDirectory, rulesDirectory, validatorWmsEndpoint, validatorProfiles);
	}

	private void logConfiguration(ValidatorConfiguration configuration) {
		LOG.info("-------------------------------------------");
		LOG.info("Configuration of the XPlanValidator:");
		LOG.info("-------------------------------------------");
		LOG.info("  validation report directory");
		LOG.info("   - {}", configuration.getValidationReportDirectory());
		LOG.info("  validation rules directory");
		LOG.info("   - {}", configuration.getValidationRulesDirectory() != null
				? configuration.getValidationRulesDirectory() : "internal rules are used");
		LOG.info("  XPlanValidatorWMS Endpoint");
		LOG.info("   - {}", configuration.getValidatorWmsEndpoint());
		LOG.info("-------------------------------------------");
		if (!configuration.getValidatorProfiles().isEmpty()) {
			LOG.info("  validation profiles");
			configuration.getValidatorProfiles().forEach(profile -> {
				LOG.info("   - {} ({}): {}", profile.getName(), profile.getId(), profile.getDescription());
			});
			LOG.info("-------------------------------------------");
		}
	}

	private Path createReportDirectory(Properties properties) throws IOException {
		String validationReportDirectory = properties.getProperty(VALIDATION_REPORT_DIRECTORY);
		if (validationReportDirectory == null || validationReportDirectory.isEmpty())
			return createTempDirectory("validationReport");
		else
			return Paths.get(validationReportDirectory);
	}

	private Path createRulesDirectory(Properties properties) {
		String validationRulesDirectory = properties.getProperty(VALIDATION_RULES_DIRECTORY);
		if (validationRulesDirectory != null && !validationRulesDirectory.isEmpty())
			return Paths.get(validationRulesDirectory);
		return null;
	}

	private String parseValidatorWmsEndpoint(Properties properties) {
		String validatorWmsEndpoint = properties.getProperty(VALIDATOR_WMS_ENDPOINT);
		return validatorWmsEndpoint == null || validatorWmsEndpoint.trim().isEmpty() ? null : validatorWmsEndpoint;
	}

	private List<ValidatorProfile> parseValidatorProfiles(PropertiesLoader propertiesLoader)
			throws IOException, ConfigurationException {
		Path profileDirectory = propertiesLoader.resolveDirectory("profiles");
		List<ValidatorProfile> validatorProfiles = new ArrayList<>();
		if (profileDirectory != null && Files.exists(profileDirectory)) {
			List<Path> profileConfigs = Files.find(profileDirectory, 1, (path, basicFileAttributes) -> {
				File file = path.toFile();
				return file.isFile() && file.getName().endsWith("yaml");
			}).collect(Collectors.toList());
			for (Path profileConfig : profileConfigs) {
				List<ValidatorProfile> profiles = parseProfiles(profileConfig);
				checkProfiles(profiles);
				validatorProfiles.addAll(profiles);
			}
		}
		return validatorProfiles;
	}

	private List<ValidatorProfile> parseProfiles(Path profileConfig) throws IOException {
		try (InputStream configFile = Files.newInputStream(profileConfig)) {
			ObjectMapper om = new ObjectMapper(new YAMLFactory());
			CollectionType javaType = om.getTypeFactory().constructCollectionType(List.class, ValidatorProfile.class);
			return om.readValue(configFile, javaType);
		}
	}

	private void checkProfiles(List<ValidatorProfile> profiles) throws ConfigurationException {
		List<String> configuredProfileIds = new ArrayList<>();
		List<String> configuredProfileNames = new ArrayList<>();
		for (ValidatorProfile profile : profiles) {
			String profileId = profile.getId();
			String profileName = profile.getName();
			if (StringUtils.isEmpty(profileId) || StringUtils.isEmpty(profileName)
					|| StringUtils.isEmpty(profile.getDescription()))
				throw new ConfigurationException("Profile id, name, description must not be null");
			if (configuredProfileIds.contains(profileId)) {
				throw new ConfigurationException("Profile id " + profileId
						+ " is configured multiple times. The ids of all configured profiles must be unique.");
			}
			configuredProfileIds.add(profileId);
			if (configuredProfileNames.contains(profileName)) {
				throw new ConfigurationException("Profile name " + profileName
						+ " is configured multiple times. The names of all configured profiles must be unique.");
			}
			configuredProfileNames.add(profileName);
		}
	}

	private void checkParameters(PropertiesLoader propertiesLoader) {
		if (propertiesLoader == null)
			throw new IllegalArgumentException("propertiesLoader must not be null!");
	}

}
