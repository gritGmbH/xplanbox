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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Retrieves a configuration from a specified path.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ConfigurationDirectoryPropertiesLoader extends AbstractPropertiesLoader {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigurationDirectoryPropertiesLoader.class);

	private final Path directoryContainingTheConfig;

	private final Class<?> defaultBaseClass;

	/**
	 * Instantiates a {@link ConfigurationDirectoryPropertiesLoader} loading properties
	 * from files in the specified directory.
	 * @param directoryContainingTheConfig the directory containing the configuration, may
	 * be <code>null</code> if the configuration should be retrieved from classpath
	 * @param defaultBaseClass fallback to retrieve properties file from, if system
	 * property is not available, may be <code>null</code> (this class is used then)
	 */
	public ConfigurationDirectoryPropertiesLoader(Path directoryContainingTheConfig, Class<?> defaultBaseClass) {
		this.directoryContainingTheConfig = directoryContainingTheConfig;
		if (defaultBaseClass != null)
			this.defaultBaseClass = defaultBaseClass;
		else
			this.defaultBaseClass = this.getClass();
	}

	/**
	 * Instantiates a {@link ConfigurationDirectoryPropertiesLoader} loading properties
	 * from files in the specified directory.
	 * @param directoryContainingTheConfig the directory containing the configuration, may
	 * be <code>null</code> if the configuration should be retrieved from classpath
	 */
	public ConfigurationDirectoryPropertiesLoader(Path directoryContainingTheConfig) {
		this(directoryContainingTheConfig, null);
	}

	@Override
	InputStream retrieveAsStream(String configurationFileName) {
		if (directoryContainingTheConfig != null) {
			LOG.info("Configuration directory is {}", directoryContainingTheConfig);
			Path pathToConfigFile = directoryContainingTheConfig.resolve(configurationFileName);
			LOG.info("Configuration is read from file {}", pathToConfigFile);
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
		return directoryContainingTheConfig;
	}

}
