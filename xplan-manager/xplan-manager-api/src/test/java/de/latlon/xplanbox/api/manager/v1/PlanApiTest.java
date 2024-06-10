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

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_X_ZIP;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_X_ZIP_COMPRESSED;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP;
import static de.latlon.xplanbox.api.manager.XPlanBoxContentTypes.XPLANBOX_V2_JSON;
import static de.latlon.xplanbox.api.manager.v1.model.PlanStatusEnum.FESTGESTELLT;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static jakarta.ws.rs.core.MediaType.APPLICATION_XML;
import static jakarta.ws.rs.core.MediaType.TEXT_HTML;
import static jakarta.ws.rs.core.MediaType.TEXT_XML;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;

import org.apache.http.HttpHeaders;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.xmlunit.assertj.XmlAssert;

import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplanbox.api.commons.exception.XPlanApiExceptionMapper;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.HsqlJpaContext;
import de.latlon.xplanbox.api.manager.config.TestContext;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
class PlanApiTest extends JerseyTest {

	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		final ResourceConfig resourceConfig = new ResourceConfig(PlanApi.class);
		resourceConfig.register(XPlanApiExceptionMapper.class);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class,
				JpaContext.class, HsqlJpaContext.class, TestContext.class);
		resourceConfig.property("contextConfig", context);
		return resourceConfig;
	}

	@Test
	void verifyThat_PostPlanOctetStream_ReturnsCorrectStatusCodeForValidMediaType()
			throws IOException, URISyntaxException {
		final byte[] data = Files.readAllBytes(Paths.get(PlanApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/plan").request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, APPLICATION_OCTET_STREAM));
		assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_JSON);
		assertThat(response.getHeaderString(HttpHeaders.LOCATION)).isNotNull();
	}

	@Test
	void verifyThat_PostPlanOctetStream_ReturnsCorrectStatusCodeForValidMediaType_v2()
			throws IOException, URISyntaxException {
		final byte[] data = Files.readAllBytes(Paths.get(PlanApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/plan").request()
			.accept(XPLANBOX_V2_JSON)
			.post(Entity.entity(data, APPLICATION_OCTET_STREAM));
		assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(XPLANBOX_V2_JSON);
		assertThat(response.getHeaderString(HttpHeaders.LOCATION)).isNotNull();
	}

	@Test
	void verifyThat_PostPlanZip_ReturnsCorrectStatusCodeForValidMediaType() throws IOException, URISyntaxException {
		final byte[] data = Files.readAllBytes(Paths.get(PlanApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/plan").request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, APPLICATION_ZIP));
		assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_JSON);
		assertThat(response.getHeaderString(HttpHeaders.LOCATION)).isNotNull();
	}

	@Test
	void verifyThat_PostPlanXZip_ReturnsCorrectStatusCodeForValidMediaType() throws IOException, URISyntaxException {
		final byte[] data = Files.readAllBytes(Paths.get(PlanApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/plan").request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, APPLICATION_X_ZIP));
		assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_JSON);
		assertThat(response.getHeaderString(HttpHeaders.LOCATION)).isNotNull();
	}

	@Test
	void verifyThat_PostPlanXZipCompressed_ReturnsCorrectStatusCodeForValidMediaType()
			throws IOException, URISyntaxException {
		final byte[] data = Files.readAllBytes(Paths.get(PlanApiTest.class.getResource("/bplan_valid_41.zip").toURI()));
		final Response response = target("/plan").request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, APPLICATION_X_ZIP_COMPRESSED));
		assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_JSON);
		assertThat(response.getHeaderString(HttpHeaders.LOCATION)).isNotNull();
	}

	@Test
	void verifyThat_PostPlanXml_ReturnsCorrectStatusCodeForValidMediaType() throws IOException, URISyntaxException {
		final byte[] data = Files.readAllBytes(Paths.get(PlanApiTest.class.getResource("/xplan.gml").toURI()));
		final Response response = target("/plan").queryParam("skipLaufrichtung", "true")
			.request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, TEXT_XML));
		assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_JSON);
		assertThat(response.getHeaderString(HttpHeaders.LOCATION)).isNotNull();
	}

	@Test
	void verifyThat_PostPlanGml_ReturnsCorrectStatusCodeForValidMediaType() throws IOException, URISyntaxException {
		final byte[] data = Files.readAllBytes(Paths.get(PlanApiTest.class.getResource("/xplan.gml").toURI()));
		final Response response = target("/plan").queryParam("skipLaufrichtung", "true")
			.request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, "application/gml+xml"));
		assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_JSON);
		assertThat(response.getHeaderString(HttpHeaders.LOCATION)).isNotNull();
	}

	@Test
	void verifyThat_PostPlanGml_ReturnsCorrectStatusCodeForInvalidXFilename() throws IOException, URISyntaxException {
		final byte[] data = Files.readAllBytes(Paths.get(PlanApiTest.class.getResource("/xplan.gml").toURI()));
		final Response response = target("/plan").queryParam("skipLaufrichtung", "true")
			.request()
			.header("X-Filename", "invalid.filename with blanks")
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, "application/gml+xml"));
		assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void verifyThat_PostPlanGml_ReturnsCorrectStatusCodeForInvalidInternalId() throws IOException, URISyntaxException {
		final byte[] data = Files.readAllBytes(Paths.get(PlanApiTest.class.getResource("/xplan.gml").toURI()));
		final Response response = target("/plan").queryParam("internalId", "a23 7D8")
			.request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, "application/gml+xml"));
		assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	void verifyThat_PostPlan_ReturnsCorrectStatusCodeForInvalidMediaType() throws IOException, URISyntaxException {
		final String data = new String(
				Files.readAllBytes(Paths.get(PlanApiTest.class.getResource("/xplan.gml").toURI())));
		final Response response = target("/plan").request()
			.accept(APPLICATION_JSON)
			.post(Entity.entity(data, TEXT_HTML));
		assertThat(response.getStatus()).isEqualTo(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode());
	}

	@Test
	void verifyThat_DeletePlan_ReturnsCorrectStatus() {
		final Response response = target("/plan/123").request().accept(APPLICATION_JSON).delete();
		assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_JSON);
	}

	@Test
	void verifyThat_GetPlanById_AsJson_ReturnsCorrectStatusAndContent() {
		final Response response = target("/plan/123").request().accept(APPLICATION_JSON).get();
		assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_JSON);

		String responseBody = response.readEntity(String.class);

		BasicJsonTester json = new BasicJsonTester(getClass());
		assertThat(json.from(responseBody)).extractingJsonPathStringValue("$.version").isEqualTo(XPLAN_41.name());
		assertThat(json.from(responseBody)).extractingJsonPathStringValue("$.planStatus")
			.isEqualTo(FESTGESTELLT.name());
		assertThat(json.from(responseBody)).hasJsonPath(
				"$.links[?(@.rel=='self' && @.href=='http:\\/\\/localhost:8080\\/xplan-api-manager\\/xmanager\\/api\\/v1\\/plan\\/123')]");
	}

	@Test
	void verifyThat_GetPlanById_AsXml_ReturnsCorrectStatusAndContent() {
		final Response response = target("/plan/123").request().accept(APPLICATION_XML).get();
		assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_XML);

		String responseBody = response.readEntity(String.class);
		XmlAssert.assertThat(responseBody).valueByXPath("/planInfo/version").isEqualTo(XPLAN_41.name());
		XmlAssert.assertThat(responseBody).valueByXPath("/planInfo/planStatus").isEqualTo(FESTGESTELLT.name());
		XmlAssert.assertThat(responseBody)
			.valueByXPath("/planInfo/links[rel='SELF']/href")
			.isEqualTo("http://localhost:8080/xplan-api-manager/xmanager/api/v1/plan/123");
	}

	@Test
	void verifyThat_GetPlanById_AsZip_ReturnsCorrectStatusAndContent() {
		final Response response = target("/plan/123").request().accept(APPLICATION_ZIP).get();
		assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_ZIP);
	}

	@Test
	void verifyThat_GetPlanById_ReturnsCorrectStatusCodeForWrongId() {
		final Response response = target("/plan/42").request().accept(APPLICATION_ZIP).get();
		assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
	}

	@Test
	void verifyThat_GetPlanByName_ReturnsCorrectStatus() {
		final Response response = target("/plan/name/bplan_41").request().accept(APPLICATION_JSON).get();
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_JSON);
	}

	@Test
	void verifyThat_GetPlanArchiveById_ReturnsCorrectStatusAndContent() {
		final Response response = target("/plan/123/archive").request().accept(APPLICATION_ZIP).get();
		assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
		assertThat(response.getHeaderString(HttpHeaders.CONTENT_TYPE)).isEqualTo(APPLICATION_ZIP);
	}

	@Test
	void verifyThat_GetPlanArchiveById_ReturnsCorrectStatusCodeForInvalidAcceptHeader() {
		final Response response = target("/plan/123/archive").request().accept(APPLICATION_JSON).get();
		assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_ACCEPTABLE.getStatusCode());
	}

}
