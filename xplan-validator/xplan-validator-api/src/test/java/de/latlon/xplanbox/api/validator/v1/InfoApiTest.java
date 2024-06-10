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
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;

import org.apache.http.HttpHeaders;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.latlon.xplanbox.api.validator.config.ApplicationContext;
import de.latlon.xplanbox.api.validator.config.TestContext;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
class InfoApiTest extends JerseyTest {

	@TempDir
	public static Path tempFolder;

	@BeforeAll
	static void setupFakedWorkspace() throws IOException {
		Path workspace = tempFolder.resolve("xplan-validator-wms-memory-workspace");
		Files.createDirectories(workspace);
		System.setProperty("DEEGREE_WORKSPACE_ROOT", workspace.getParent().toString());
	}

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

		BasicJsonTester json = new BasicJsonTester(getClass());
		assertThat(json.from(response.readEntity(String.class))).extractingJsonPathStringValue("$.rulesMetadata.source")
			.startsWith("https://gitlab.opencode.de/xleitstelle/xplanung/validierungsregeln/standard/-/tree/");

	}

	@Test
	void verifyThat_Response_ContainsSupportedXplanGmlVersionsAndProfiles() {
		final String response = target("/info").request(APPLICATION_JSON).get(String.class);

		assertThat(response).contains("supportedXPlanGmlVersions");
		assertThat(response).contains("profiles");
	}

}
