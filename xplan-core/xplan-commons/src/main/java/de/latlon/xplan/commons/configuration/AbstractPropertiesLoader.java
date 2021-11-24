/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.commons.configuration;

import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

import de.latlon.xplan.manager.web.shared.ConfigurationException;

/**
 * Abstract {@link PropertiesLoader}, loading properties from properties file.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public abstract class AbstractPropertiesLoader implements PropertiesLoader {

	@Override
	public Properties loadProperties(String propertiesFileName) throws ConfigurationException {
		InputStream filePath = retrieveAsStream(propertiesFileName);
		if (filePath != null) {
			return loadProperties(filePath);
		}
		return null;
	}

	@Override
	public Path resolveDirectory(String subdirectory) {
		Path configDirectory = getConfigDirectory();
		if (configDirectory != null)
			return configDirectory.resolve(subdirectory);
		return null;
	}

	/**
	 * Retrieves the resource with the given name.
	 * @param propertiesFileName name of the properties file, never <code>null</code>
	 * @return the properties file as stream or <code>null</code> if the properties file
	 * could not be found
	 */
	abstract InputStream retrieveAsStream(String propertiesFileName);

	private Properties loadProperties(InputStream filePath) throws ConfigurationException {
		try {
			Properties props = new Properties();
			props.load(filePath);
			return props;
		}
		catch (IOException e) {
			throw new ConfigurationException(e);
		}
		finally {
			closeQuietly(filePath);
		}
	}

}
