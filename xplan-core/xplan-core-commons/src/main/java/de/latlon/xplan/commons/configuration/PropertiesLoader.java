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

import java.nio.file.Path;
import java.util.Properties;

import de.latlon.xplan.manager.web.shared.ConfigurationException;

/**
 * Loads properties file.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public interface PropertiesLoader {

	/**
	 * Loads a properties file with the specified name.
	 * @param propertiesFileName the name of the properties file, never <code>null</code>
	 * @return the properties loaded from specified file, <code>null</code> if the file
	 * was not found
	 * @throws ConfigurationException - properties file could not be load
	 */
	Properties loadProperties(String propertiesFileName) throws ConfigurationException;

	/**
	 * Retrieves the directory containing the configuration.
	 * @return the directory containing the configuration, may be <code>null</code> if the
	 * configuration os read from classpath
	 */
	Path getConfigDirectory();

	/**
	 * Resolves the passed subdirectory relative to the config directory
	 * @param subdirectory never <code>null</code>
	 * @return the resolved directory or <code>null</code> if the config directory is
	 * <code>null</code>
	 */
	Path resolveDirectory(String subdirectory);

}
