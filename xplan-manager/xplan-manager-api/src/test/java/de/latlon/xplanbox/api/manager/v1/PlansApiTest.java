/*-
 * #%L
 * xplan-manager-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.manager.v1;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.HsqlJpaContext;
import de.latlon.xplanbox.api.manager.config.TestContext;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
class PlansApiTest extends JerseyTest {

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
	void verifyThat_GetPlansByName_ReturnCorrectStatus() {
		Response response = target("/plans").queryParam("planName", "bplan_41")
			.request()
			.accept(APPLICATION_JSON)
			.get();
		assertThat(response.getStatus()).isEqualTo(200);
		assertThat(response.readEntity(String.class))
			.contains("{\"id\":123,\"type\":\"BP_Plan\",\"version\":\"XPLAN_41\",");
	}

	@Test
	void verifyThat_GetPlansById_ReturnCorrectStatus() {
		Response response = target("/plans").queryParam("planId", 123)
			.queryParam("planId", 2)
			.request()
			.accept(APPLICATION_JSON)
			.get();
		assertThat(response.getStatus()).isEqualTo(200);
		String responseEntity = response.readEntity(String.class);
		assertThat(responseEntity).contains("{\"id\":123,\"type\":\"BP_Plan\",\"version\":\"XPLAN_41\",");
		assertThat(responseEntity).contains("{\"id\":2,\"type\":\"BP_Plan\",\"version\":\"XPLAN_51\",");
	}

}
