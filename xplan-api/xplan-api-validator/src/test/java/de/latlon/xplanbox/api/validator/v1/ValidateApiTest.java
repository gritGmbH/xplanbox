/*-
 * #%L
 * xplan-api-validator - Modul zur Gruppierung der REST-API
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
package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplanbox.api.validator.config.ApplicationContext;
import de.latlon.xplanbox.api.validator.config.TestContext;
import org.apache.http.HttpHeaders;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_X_ZIP;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_X_ZIP_COMPRESSED;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
public class ValidateApiTest extends JerseyTest {

	@ClassRule
	public final static TemporaryFolder tempFolder = new TemporaryFolder();

	@BeforeClass
	public static void setupFakedWorkspace() throws IOException {
		File workspace = tempFolder.newFolder("xplan-validator-wms-memory-workspace");
		System.setProperty("DEEGREE_WORKSPACE_ROOT", workspace.getParentFile().toString());
	}

	@Autowired
	private List<RulesMetadata> profileMetadata;

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		final ResourceConfig resourceConfig = new ResourceConfig(ValidateApi.class);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class,
				TestContext.class);
		resourceConfig.property("contextConfig", context);
		resourceConfig.register(this);
		resourceConfig.packages("de.latlon.xplanbox.api.commons.converter");
		resourceConfig.packages("de.latlon.xplanbox.api.commons.exception");
		return resourceConfig;
	}

	@Test
	public void verifyThat_Response_ContainsCorrectStatusCodeAndMediaType() throws IOException, URISyntaxException {
		final String data = new String(
				Files.readAllBytes(Paths.get(ValidateApiTest.class.getResource("/xplan.gml").toURI())));
		final Response response = target("/validate").request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, TEXT_XML));

		assertThat(response.getStatus(), is(Response.Status.OK.getStatusCode()));
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE), is(APPLICATION_JSON));
	}

	@Test
	public void verifyThat_validationOctetStream_Response_ContainsXmlEncoding() throws URISyntaxException, IOException {
		final byte[] data = Files
			.readAllBytes(Paths.get(ValidateApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/validate").request()
			.accept(APPLICATION_XML)
			.post(Entity.entity(data, APPLICATION_OCTET_STREAM));

		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE), is(APPLICATION_XML));
		assertThat(response.readEntity(String.class), containsString("valid"));
	}

	@Test
	public void verifyThat_validationZip_Response_ContainsXmlEncoding() throws URISyntaxException, IOException {
		final byte[] data = Files
			.readAllBytes(Paths.get(ValidateApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/validate").request()
			.accept(APPLICATION_XML)
			.post(Entity.entity(data, APPLICATION_ZIP));

		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE), is(APPLICATION_XML));
		assertThat(response.readEntity(String.class), containsString("valid"));
	}

	@Test
	public void verifyThat_validationXZip_Response_ContainsXmlEncoding() throws URISyntaxException, IOException {
		final byte[] data = Files
			.readAllBytes(Paths.get(ValidateApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/validate").request()
			.accept(APPLICATION_XML)
			.post(Entity.entity(data, APPLICATION_X_ZIP));

		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE), is(APPLICATION_XML));
		assertThat(response.readEntity(String.class), containsString("valid"));
	}

	@Test
	public void verifyThat_validationXZipCompressed_Response_ContainsXmlEncoding()
			throws URISyntaxException, IOException {
		final byte[] data = Files
			.readAllBytes(Paths.get(ValidateApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/validate").request()
			.accept(APPLICATION_XML)
			.post(Entity.entity(data, APPLICATION_X_ZIP_COMPRESSED));

		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE), is(APPLICATION_XML));
		assertThat(response.readEntity(String.class), containsString("valid"));
	}

	@Test
	public void verifyThat_validationXZipCompressedWithProfile_Response_ContainsJsonEncoding()
			throws URISyntaxException, IOException {
		final byte[] data = Files
			.readAllBytes(Paths.get(ValidateApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/validate").queryParam("profiles", profileMetadata.get(0).getId())
			.request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, APPLICATION_X_ZIP_COMPRESSED));

		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE), is(APPLICATION_JSON));
		String actual = response.readEntity(String.class);
		assertThat(actual, containsString("profil"));
	}

	@Test
	public void verifyThat_validationXZipCompressedWithProfiles_Response_ContainsJsonEncoding()
			throws URISyntaxException, IOException {
		final byte[] data = Files
			.readAllBytes(Paths.get(ValidateApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/validate").queryParam("profiles", profileMetadata.get(0).getId())
			.queryParam("profiles", profileMetadata.get(1).getId())
			.request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, APPLICATION_X_ZIP_COMPRESSED));

		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE), is(APPLICATION_JSON));
		String actual = response.readEntity(String.class);
		assertThat(actual, containsString("profil"));
	}

	@Test
	public void verifyThat_validationXZipCompressedWithProfilesCommaseparated_Response_ContainsJsonEncoding()
			throws URISyntaxException, IOException {
		final byte[] data = Files
			.readAllBytes(Paths.get(ValidateApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/validate")
			.queryParam("profiles", profileMetadata.get(0).getId() + "," + profileMetadata.get(1).getId())
			.request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, APPLICATION_X_ZIP_COMPRESSED));

		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE), is(APPLICATION_JSON));
		String actual = response.readEntity(String.class);
		assertThat(actual, containsString("profil"));
	}

	@Test
	public void verifyThat_validationWithInvalidXFileName_Response_IsStatusCode400()
			throws URISyntaxException, IOException {
		final byte[] data = Files
			.readAllBytes(Paths.get(ValidateApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/validate").request()
			.header("X-Filename", "invalid.filename with blanks")
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, APPLICATION_X_ZIP_COMPRESSED));

		assertThat(response.getStatus(), is(Response.Status.BAD_REQUEST.getStatusCode()));
	}

	@Test
	public void verifyThat_validationWithInvalidName_Response_IsStatusCode400() throws URISyntaxException, IOException {
		final byte[] data = Files
			.readAllBytes(Paths.get(ValidateApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/validate").queryParam("name", "invalid.name with blanks")
			.request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, APPLICATION_X_ZIP_COMPRESSED));

		assertThat(response.getStatus(), is(Response.Status.BAD_REQUEST.getStatusCode()));
	}

}
