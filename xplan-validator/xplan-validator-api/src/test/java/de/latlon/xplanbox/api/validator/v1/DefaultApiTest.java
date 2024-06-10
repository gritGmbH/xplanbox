/*-
 * #%L
 * xplan-validator-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.validator.v1;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;

import org.apache.http.HttpHeaders;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.latlon.xplan.commons.configuration.DefaultPropertiesLoader;
import de.latlon.xplanbox.api.validator.config.ApplicationContext;
import de.latlon.xplanbox.api.validator.config.JerseyConfig;
import de.latlon.xplanbox.api.validator.config.ValidatorApiConfiguration;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
class DefaultApiTest extends JerseyTest {

	@TempDir
	static Path tempFolder;

	@BeforeAll
	static void setupFakedWorkspace() throws IOException {
		Path workspace = tempFolder.resolve("xplan-webservices-validator-wms-memory-workspace");
		Files.createDirectories(workspace);
		System.setProperty("DEEGREE_WORKSPACE_ROOT", workspace.getParent().toString());
	}

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);

		ResourceConfig resourceConfig;
		ServletContext mockServletContext = Mockito.mock(ServletContext.class);
		Mockito.when(mockServletContext.getContextPath()).thenReturn("");

		try {
			ValidatorApiConfiguration validatorConfig = new ValidatorApiConfiguration(
					new DefaultPropertiesLoader(getClass()));
			resourceConfig = new JerseyConfig(mockServletContext, validatorConfig);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class);
		resourceConfig.property("contextConfig", context);
		return resourceConfig;
	}

	@Test
	void verifyThat_Response_ContainsCorrectStatusCodeAndMediaType() {
		final Response response = target("/").request(APPLICATION_JSON).get();

		assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_JSON);
	}

	@Test
	void verifyThat_Response_ContainsOpenApiDocument() {
		final String response = target("/").request(APPLICATION_JSON).get(String.class);

		BasicJsonTester json = new BasicJsonTester(getClass());
		assertThat(json.from(response)).extractingJsonPathStringValue("$.openapi").isEqualTo("3.0.1");
	}

	@Test
	void verifyThat_Response_ContainsLicence() {
		final String response = target("/").request(APPLICATION_JSON).get(String.class);

		BasicJsonTester json = new BasicJsonTester(getClass());
		assertThat(json.from(response)).extractingJsonPathStringValue("$.info.license.name").isEqualTo("Apache 2.0");
	}

	@Test
	void verifyThat_Response_Paths() {
		final String response = target("/").request(APPLICATION_JSON).get(String.class);

		BasicJsonTester json = new BasicJsonTester(getClass());
		assertThat(json.from(response)).extractingJsonPathMapValue("$.paths")
			.containsOnlyKeys("/", "/info", "/validate");
	}

	@Test
	void verifyThat_Response_ContainsTermsOfService() {
		final String response = target("/").request(APPLICATION_JSON).get(String.class);

		BasicJsonTester json = new BasicJsonTester(getClass());
		assertThat(json.from(response)).extractingJsonPathStringValue("$.info.termsOfService")
			.isEqualTo("http://xplanbox/api-validator/terms");
	}

	@Test
	void verifyThat_Response_ContainsServersUrl() {
		final String response = target("/").request(APPLICATION_JSON).get(String.class);

		BasicJsonTester json = new BasicJsonTester(getClass());
		assertThat(json.from(response)).extractingJsonPathStringValue("$.servers[0].url")
			.isEqualTo("http://xplanbox-api-validator/xvalidator/api/v1");
	}

}
