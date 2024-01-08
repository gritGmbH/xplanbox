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

import de.latlon.xplanbox.api.commons.ObjectMapperContextResolver;
import de.latlon.xplanbox.api.commons.config.ApiConfiguration;
import de.latlon.xplanbox.api.commons.converter.StringListConverterProvider;
import de.latlon.xplanbox.api.commons.exception.ConstraintViolationExceptionMapper;
import de.latlon.xplanbox.api.commons.exception.UnsupportedContentTypeExceptionMapper;
import de.latlon.xplanbox.api.commons.exception.ValidatorExceptionMapper;
import de.latlon.xplanbox.api.commons.exception.XPlanApiExceptionMapper;
import de.latlon.xplanbox.api.commons.openapi.OpenApiFilter;
import de.latlon.xplanbox.api.dokumente.v1.InfoApi;
import de.latlon.xplanbox.api.dokumente.v1.StatusApi;
import de.latlon.xplanbox.api.dokumente.v1.DefaultApi;
import de.latlon.xplanbox.api.dokumente.v1.DokumentApi;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Application configuration for XPlanDokumenten REST API. Example mapping for proxy
 * mapping: http://xplanbox.lat-lon.de/xdokumente/api/v1/ ->
 * http://host:8080/xplan-api-dokumente/xdokumente/api/v1/ Public address:
 * http://xplanbox.lat-lon.de/xdokumente/ Internal address:
 * http://host:8080/xplan-api-dokumente/xdokumente/
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@ApplicationPath("/xdokumente/api/v1")
@Configuration
public class JerseyConfig extends ResourceConfig {

	private static final Logger LOG = getLogger(JerseyConfig.class);

	private static final String APP_PATH = "xdokumente/api/v1";

	public JerseyConfig(@Context ServletContext servletContext, DokumentenApiConfiguration apiConfiguration) {
		property(ServerProperties.WADL_FEATURE_DISABLE, true);

		register(new ObjectMapperContextResolver());

		register(InfoApi.class);
		register(DokumentApi.class);
		register(StatusApi.class);
		register(ConstraintViolationExceptionMapper.class);
		register(UnsupportedContentTypeExceptionMapper.class);
		register(ValidatorExceptionMapper.class);
		register(XPlanApiExceptionMapper.class);
		register(StringListConverterProvider.class);

		OpenAPI openApi = new OpenAPI();
		openApi.setInfo(new Info().title("XPlanDokumentenAPI")
			.version("1.0.0")
			.description("XPlanDokumenten REST API")
			.termsOfService(getTermsOfService(apiConfiguration))
			.license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")));
		addContact(openApi, apiConfiguration);
		openApi.servers(servers(servletContext, apiConfiguration));
		Tag tag = createTag(apiConfiguration);
		openApi.tags(Collections.singletonList(tag));

		DefaultApi openApiResource = new DefaultApi();
		SwaggerConfiguration oasConfig = new SwaggerConfiguration().openAPI(openApi)
			.filterClass(OpenApiFilter.class.getCanonicalName())
			.prettyPrint(true)
			.resourcePackages(Stream.of("de.latlon.xplanbox.api.dokumente.v1").collect(Collectors.toSet()));

		openApiResource.setOpenApiConfiguration(oasConfig);
		register(openApiResource);
		LOG.info("XPlanDokumentenAPI successfully initialized");
	}

	private Tag createTag(ApiConfiguration apiConfiguration) {
		Tag tag = new Tag().name("download").description("Download XPlanDokumente");
		if (apiConfiguration != null && apiConfiguration.getDocumentationUrl() != null) {
			tag.externalDocs(
					new ExternalDocumentation().description("xPlanBox").url(apiConfiguration.getDocumentationUrl()));
		}
		return tag;
	}

	private void addContact(OpenAPI openApi, ApiConfiguration apiConfiguration) {
		if (apiConfiguration != null && apiConfiguration.getContactEMailAddress() != null) {
			String contactEMailAddress = apiConfiguration.getContactEMailAddress();
			openApi.getInfo().setContact(new Contact().email(contactEMailAddress));
		}
	}

	private List<Server> servers(ServletContext servletContext, ApiConfiguration apiConfiguration) {
		String serverUrl = getServerUrl(servletContext, apiConfiguration);
		Server server = new Server().url(serverUrl);
		return Collections.singletonList(server);
	}

	private String getServerUrl(ServletContext servletContext, ApiConfiguration apiConfiguration) {
		StringBuilder serverUrl = new StringBuilder();
		if (apiConfiguration != null && apiConfiguration.getApiUrl() != null) {
			String apiEndpoint = apiConfiguration.getApiUrl().toString();
			serverUrl.append(apiEndpoint);
		}
		else {
			serverUrl.append(servletContext.getContextPath());
		}
		if (!serverUrl.toString().endsWith("/"))
			serverUrl.append("/");
		serverUrl.append(APP_PATH);
		return serverUrl.toString();
	}

	private String getTermsOfService(ApiConfiguration apiConfiguration) {
		if (apiConfiguration != null)
			return apiConfiguration.getTermsOfServiceUrl();
		return null;
	}

}
