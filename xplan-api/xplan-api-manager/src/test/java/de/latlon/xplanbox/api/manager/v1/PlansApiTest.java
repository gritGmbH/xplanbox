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

import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.HsqlJpaContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
public class PlansApiTest extends JerseyTest {

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		final ResourceConfig resourceConfig = new ResourceConfig(PlansApi.class);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class,
				JpaContext.class, HsqlJpaContext.class, TestContext.class);
		resourceConfig.property("contextConfig", context);
		return resourceConfig;
	}

	@Test
	public void verifyThat_GetPlansByName_ReturnCorrectStatus() {
		Response response = target("/plans").queryParam("planName", "bplan_41").request().accept(APPLICATION_JSON)
				.get();
		assertThat(response.getStatus(), is(200));
		assertThat(response.readEntity(String.class),
				containsString("{\"id\":123,\"type\":\"BP_Plan\",\"version\":\"XPLAN_41\","));
	}

	@Test
	public void verifyThat_GetPlansById_ReturnCorrectStatus() {
		Response response = target("/plans").queryParam("planId", 123).queryParam("planId", 2).request()
				.accept(APPLICATION_JSON).get();
		assertThat(response.getStatus(), is(200));
		String responseEntity = response.readEntity(String.class);
		assertThat(responseEntity, containsString("{\"id\":123,\"type\":\"BP_Plan\",\"version\":\"XPLAN_41\","));
		assertThat(responseEntity, containsString("{\"id\":2,\"type\":\"BP_Plan\",\"version\":\"XPLAN_51\","));
	}

}
