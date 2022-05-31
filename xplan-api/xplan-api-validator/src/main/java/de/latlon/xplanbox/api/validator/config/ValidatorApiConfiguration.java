/*-
 * #%L
 * xplan-api-validator - Modul zur Gruppierung der REST-API
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
package de.latlon.xplanbox.api.validator.config;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplanbox.api.commons.config.ApiConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidatorApiConfiguration extends ApiConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorApiConfiguration.class);

	private static final String VALIDATOR_API_CONFIGURATION_PROPERTIES = "validatorApiConfiguration.properties";

	public ValidatorApiConfiguration(PropertiesLoader propertiesLoader) throws ConfigurationException {
		super(propertiesLoader, VALIDATOR_API_CONFIGURATION_PROPERTIES);
	}

	protected void logProperties() {
		LOG.info("-------------------------------------------");
		LOG.info("Configuration of the XPlanValidatorApi:");
		LOG.info("-------------------------------------------");
		LOG.info("  API URL: {}", getApiUrl());
		LOG.info("-------------------------------------------");
	}

}
