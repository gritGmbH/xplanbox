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

import de.latlon.xplanbox.api.dokumente.v1.DefaultApi;
import de.latlon.xplanbox.api.dokumente.v1.DokumentApi;
import de.latlon.xplanbox.api.dokumente.v1.InfoApi;
import de.latlon.xplanbox.api.dokumente.v1.StatusApi;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @since 6.1
 */
@Configuration
public class TestContext {

	@Bean
	@Profile("jaxrs")
	ResourceConfig resourceConfig() {
		ResourceConfig jerseyConfig = new ResourceConfig();
		jerseyConfig.register(DokumentApi.class);
		jerseyConfig.register(InfoApi.class);
		jerseyConfig.register(DefaultApi.class);
		jerseyConfig.register(StatusApi.class);
		return jerseyConfig;
	}

}
