/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumentenAPI
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
package de.latlon.xplanbox.api.dokumente.config;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplanbox.api.commons.config.ApiConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class DokumentenApiConfiguration extends ApiConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(DokumentenApiConfiguration.class);

	private static final String API_CONFIGURATION_PROPERTIES = "dokumentenApiConfiguration.properties";

	public DokumentenApiConfiguration(PropertiesLoader propertiesLoader) throws ConfigurationException {
		super(propertiesLoader, API_CONFIGURATION_PROPERTIES);
	}

	protected void logProperties() {
		LOG.info("-------------------------------------------");
		LOG.info("Configuration of the XPlanDokumentenApi:");
		LOG.info("-------------------------------------------");
		LOG.info("  API URL: {}", getApiUrl());
		LOG.info("  Terms of Services URL: {}", getTermsOfServiceUrl());
		LOG.info("  Documentation URL: {}", getDocumentationUrl());
		LOG.info("  Contact E-Mail: {}", getContactEMailAddress());
		LOG.info("-------------------------------------------");
	}

}
