/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Retrieves a configuration from a path specified with a system property or classpath.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class SystemPropertyPropertiesLoader extends AbstractPropertiesLoader {

	private static final Logger LOG = LoggerFactory.getLogger(SystemPropertyPropertiesLoader.class);

	public static final String CONFIG_SYSTEM_PROPERTY = "XPLANBOX_CONFIG";

	protected static final String DEFAULT_SUB_DIIRECTOY = "xplanbox";

	private final Path configurationDirectory;

	private final Class<?> defaultBaseClass;

	/**
	 * Instantiates a {@link SystemPropertyPropertiesLoader} loading properties from files
	 * specified by a system property.
	 * @param defaultBaseClass fallback to retrieve properties file from, if system
	 * property is not available, may be <code>null</code> (this class is used then)
	 */
	public SystemPropertyPropertiesLoader(Class<?> defaultBaseClass) {
		this.configurationDirectory = findConfigDirectory();
		if (defaultBaseClass != null)
			this.defaultBaseClass = defaultBaseClass;
		else
			this.defaultBaseClass = this.getClass();
	}

	/**
	 * Instantiates a {@link SystemPropertyPropertiesLoader} loading properties from files
	 * specified with by a system property.
	 * @param configFilePath path to the configFile, never <code>null</code>
	 */
	public SystemPropertyPropertiesLoader(String configFilePath) {
		this.configurationDirectory = findConfigDirectory(configFilePath);
		this.defaultBaseClass = this.getClass();
	}

	/**
	 * Instantiates a {@link SystemPropertyPropertiesLoader} loading properties from files
	 * specified with by a system property.
	 */
	public SystemPropertyPropertiesLoader() {
		this.configurationDirectory = findConfigDirectory();
		this.defaultBaseClass = this.getClass();
	}

	@Override
	InputStream retrieveAsStream(String configurationFileName) {
		if (configurationDirectory != null) {
			Path pathToConfigFile = configurationDirectory.resolve(configurationFileName);
			LOG.info("Configuration {} is read from file {}", configurationFileName, pathToConfigFile);
			try {
				return Files.newInputStream(pathToConfigFile);
			}
			catch (IOException e) {
				LOG.info("Configuration does not exist: {}", e.getMessage());
				LOG.info("Internal {} configuration is used.", configurationFileName);
				return defaultBaseClass.getResourceAsStream(configurationFileName);
			}
		}
		LOG.info("Internal {} configuration is used.", configurationFileName);
		return defaultBaseClass.getResourceAsStream(configurationFileName);
	}

	@Override
	public Path getConfigDirectory() {
		return configurationDirectory;
	}

	private Path findConfigDirectory() {
		LOG.info("Try to receive configuration set with system property {}", CONFIG_SYSTEM_PROPERTY);
		String configFilePath = System.getProperty(CONFIG_SYSTEM_PROPERTY);
		if (configFilePath != null)
			return findConfigDirectory(configFilePath);
		LOG.info("Try to receive configuration from default directory ${user.home}/xplanbox");
		Path defaultConfigFilePath = Paths.get(System.getProperty("user.home"), DEFAULT_SUB_DIIRECTOY);
		if (Files.isDirectory(defaultConfigFilePath) && Files.exists(defaultConfigFilePath))
			return defaultConfigFilePath;
		LOG.info("Configuration directory {} does not exist or is not a directory.", defaultConfigFilePath);
		return null;
	}

	private Path findConfigDirectory(String configFilePath) {
		LOG.info("Configuration directory is {}", configFilePath);
		if (configFilePath != null) {
			Path configDirectory = Paths.get(configFilePath);
			if (Files.isDirectory(configDirectory) && Files.exists(configDirectory))
				return configDirectory;
			else
				LOG.info("Configuration directory {} does not exist or is not a directory.", configFilePath);
		}
		return null;
	}

}
