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
import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
@Configuration
@ComponentScan(basePackages = { "de.latlon.xplanbox.api.dokumente.handler", "de.latlon.xplanbox.api.dokumente.v1" })
public class ApplicationContext {

	@Bean
	public PropertiesLoader propertiesLoader() {
		return new SystemPropertyPropertiesLoader();
	}

	@Bean
	public DokumentenApiConfiguration dokumentenApiConfiguration(PropertiesLoader propertiesLoader)
			throws ConfigurationException {
		return new DokumentenApiConfiguration(propertiesLoader);
	}

}
