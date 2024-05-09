/*-
 * #%L
 * xplan-core-api - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplanbox.api.commons.config;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class ApiConfiguration {

	private static final String API_URL = "apiUrl";

	private static final String CONTACT_EMAIL = "contactEMailAddress";

	private static final String TERMS_OF_SERVICES_URL = "termsOfServiceUrl";

	private static final String DOCUMENTATION_URL = "documentationUrl";

	private final String propertiesFileName;

	private URI apiUrl;

	private String contactEMailAddress;

	private String termsOfServiceUrl;

	private String documentationUrl;

	/**
	 * @param propertiesLoader loader to retrieve the properties, never <code>null</code>
	 * @param propertiesFileName name of the properties file, never <code>null</code>
	 * @throws ConfigurationException
	 */
	public ApiConfiguration(PropertiesLoader propertiesLoader, String propertiesFileName)
			throws ConfigurationException {
		this.propertiesFileName = propertiesFileName;
		loadProperties(propertiesLoader);
		validateProperties();
		logProperties();
	}

	/**
	 * @return the configured api url, may be <code>null</code>
	 */
	public URI getApiUrl() {
		return apiUrl;
	}

	/**
	 * @return the configured e-mail address, may be <code>null</code>
	 */
	public String getContactEMailAddress() {
		return contactEMailAddress;
	}

	/**
	 * @return the configured terms of services url, may be <code>null</code>
	 */
	public String getTermsOfServiceUrl() {
		return termsOfServiceUrl;
	}

	/**
	 * @return the configured documentation url, may be <code>null</code>
	 */
	public String getDocumentationUrl() {
		return documentationUrl;
	}

	private void loadProperties(PropertiesLoader propertiesLoader) throws ConfigurationException {
		if (propertiesLoader != null) {
			Properties properties = propertiesLoader.loadProperties(propertiesFileName);
			if (properties != null) {
				apiUrl = parseUri(properties, API_URL);
				contactEMailAddress = properties.getProperty(CONTACT_EMAIL);
				termsOfServiceUrl = properties.getProperty(TERMS_OF_SERVICES_URL);
				documentationUrl = properties.getProperty(DOCUMENTATION_URL);
				loadProperties(properties);
			}
			else {
				loadDefaultProperties();
			}
		}
		else {
			loadDefaultProperties();
		}
	}

	/**
	 * Loads the properties from the properties.
	 * @param properties never <code>null</code>
	 * @throws ConfigurationException
	 */
	protected void loadProperties(Properties properties) throws ConfigurationException {
	}

	/**
	 * Loads the default properties if no properties file is available
	 */
	protected void loadDefaultProperties() {
	}

	/**
	 * Validates the loaded properties.
	 * @throws ConfigurationException if the properties are not valid
	 */
	protected void validateProperties() throws ConfigurationException {
	}

	/**
	 * Logs the properties.
	 */
	protected abstract void logProperties();

	/**
	 * Parse the property as URL.
	 * @param properties never <code>null</code>
	 * @param propName the name of the property, never <code>null</code>
	 * @return the parsed url, <code>null</code> if no property is set
	 * @throws ConfigurationException if the property is not a valid URL
	 */
	protected URI parseUri(Properties properties, String propName) throws ConfigurationException {
		String property = properties.getProperty(propName);
		if (property == null || "".equals(property))
			return null;
		try {
			return new URI(property);
		}
		catch (URISyntaxException e) {
			throw new ConfigurationException("Could not parse property " + property + " as URI.", e);
		}
	}

	/**
	 * @param properties never <code>null</code>
	 * @param propName the name of the property, never <code>null</code>
	 * @param defaultValue to return if the property is not configured
	 * @return the parsed value as boolean, the default value if no property is set
	 */
	protected boolean parseBoolean(Properties properties, String propName, boolean defaultValue) {
		String property = properties.getProperty(propName);
		if (property == null || "".equals(property))
			return defaultValue;
		return Boolean.parseBoolean(property);
	}

}
