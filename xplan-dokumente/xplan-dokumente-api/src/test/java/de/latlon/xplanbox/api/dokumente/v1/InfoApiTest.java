/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumenteAPI
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
package de.latlon.xplanbox.api.dokumente.v1;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.apache.http.HttpHeaders;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.latlon.xplanbox.api.dokumente.config.ApplicationContext;
import de.latlon.xplanbox.api.dokumente.config.TestContext;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @since 7.0
 */
class InfoApiTest extends JerseyTest {

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		final ResourceConfig resourceConfig = new ResourceConfig(InfoApi.class);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class,
				TestContext.class);
		resourceConfig.property("contextConfig", context);
		return resourceConfig;
	}

	@Test
	void verifyThat_Response_ContainsCorrectStatusCodeAndMediaType() {
		final Response response = target("/info").request(APPLICATION_JSON).get();

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		assertEquals(APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}

	@Test
	void verifyThat_Response_ContainsVersion() {
		final String response = target("/info").request(APPLICATION_JSON).get(String.class);

		assertThat(response).contains("version");
	}

}
