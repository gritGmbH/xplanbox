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
package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.commons.exception.XPlanApiExceptionMapper;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import org.apache.http.HttpHeaders;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanBasisdatenApiTest extends JerseyTest {

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		final ResourceConfig resourceConfig = new ResourceConfig(PlanBasisdatenApi.class);
		resourceConfig.register(XPlanApiExceptionMapper.class);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class,
				TestContext.class);
		resourceConfig.property("contextConfig", context);
		return resourceConfig;
	}

	@Test
	public void verifyThat_getBasisdaten_returnsCorrectStatusCodeForValidMediaType() {
		Response response = target("/plan/2/basisdaten").request(APPLICATION_JSON).get();

		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE), is(APPLICATION_JSON));
	}

	@Test
	public void verifyThat_replaceBasisdaten_returnsCorrectStatusCodeForValidMediaType()
			throws URISyntaxException, IOException {
		final byte[] data = Files.readAllBytes(Paths.get(getClass().getResource("basisdatenmodel.json").toURI()));

		Response response = target("/plan/2/basisdaten").request().put(Entity.entity(data, APPLICATION_JSON_TYPE));
		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE), is(APPLICATION_JSON));
	}

}
