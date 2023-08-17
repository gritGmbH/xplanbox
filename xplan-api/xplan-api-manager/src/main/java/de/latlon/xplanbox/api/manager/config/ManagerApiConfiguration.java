/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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
package de.latlon.xplanbox.api.manager.config;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplanbox.api.commons.config.ApiConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ManagerApiConfiguration extends ApiConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(ManagerApiConfiguration.class);

	private static final String MANAGER_API_CONFIGURATION_PROPERTIES = "managerApiConfiguration.properties";

	private static final String WMS_URL = "wmsUrl";

	private URI wmsUrl;

	private DefaultValidationConfiguration defaultValidationConfiguration;

	public ManagerApiConfiguration(PropertiesLoader propertiesLoader) throws ConfigurationException {
		super(propertiesLoader, MANAGER_API_CONFIGURATION_PROPERTIES);
	}

	/**
	 * @return the configured WMS url, may be <code>null</code>
	 */
	public URI getWmsUrl() {
		return this.wmsUrl;
	}

	/**
	 * @return the default validation configuration, never <code>null</code>
	 */
	public DefaultValidationConfiguration getDefaultValidationConfiguration() {
		return defaultValidationConfiguration;
	}

	@Override
	protected void loadProperties(Properties properties) throws ConfigurationException {
		wmsUrl = parseUri(properties, WMS_URL);
		defaultValidationConfiguration = parseDefaultValidationConfiguration(properties);
	}

	@Override
	protected void loadDefaultProperties() {
		defaultValidationConfiguration = new DefaultValidationConfiguration();
	}

	protected void logProperties() {
		LOG.info("-------------------------------------------");
		LOG.info("Configuration of the XPlanManagerApi:");
		LOG.info("-------------------------------------------");
		LOG.info("  API URL: {}", getApiUrl());
		LOG.info("  WMS URL: {}", wmsUrl);
		LOG.info("-------------------------------------------");
		LOG.info("  default validation configuration");
		LOG.info("   - skip semantisch: {}", defaultValidationConfiguration.isSkipSemantisch());
		LOG.info("   - skip geometrisch: {}", defaultValidationConfiguration.isSkipGeometrisch());
		LOG.info("   - skip Flaechenschluss: {}", defaultValidationConfiguration.isSkipFlaechenschluss());
		LOG.info("   - skip Geltungsbereich: {}", defaultValidationConfiguration.isSkipGeltungsbereich());
		LOG.info("   - skip Laufrichtung: {}", defaultValidationConfiguration.isSkipLaufrichtung());
		LOG.info("-------------------------------------------");
	}

	private DefaultValidationConfiguration parseDefaultValidationConfiguration(Properties loadProperties) {
		boolean skipSemantisch = parseBoolean(loadProperties, "skipSemantisch", false);
		boolean skipGeometrisch = parseBoolean(loadProperties, "skipGeometrisch", false);
		boolean skipFlaechenschluss = parseBoolean(loadProperties, "skipFlaechenschluss", false);
		boolean skipGeltungsbereich = parseBoolean(loadProperties, "skipGeltungsbereich", false);
		boolean skipLaufrichtung = parseBoolean(loadProperties, "skipLaufrichtung", false);
		return new DefaultValidationConfiguration(skipSemantisch, skipGeometrisch, skipFlaechenschluss,
				skipGeltungsbereich, skipLaufrichtung);
	}

}
