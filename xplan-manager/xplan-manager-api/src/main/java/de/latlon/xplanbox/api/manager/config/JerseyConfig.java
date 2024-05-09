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
package de.latlon.xplanbox.api.manager.config;

import de.latlon.xplanbox.api.commons.ObjectMapperContextResolver;
import de.latlon.xplanbox.api.commons.converter.StringListConverterProvider;
import de.latlon.xplanbox.api.commons.exception.ConstraintViolationExceptionMapper;
import de.latlon.xplanbox.api.commons.exception.UnsupportedContentTypeExceptionMapper;
import de.latlon.xplanbox.api.commons.exception.ValidatorExceptionMapper;
import de.latlon.xplanbox.api.commons.exception.XPlanApiExceptionMapper;
import de.latlon.xplanbox.api.manager.exception.AmbiguousBereichNummernExceptionMapper;
import de.latlon.xplanbox.api.manager.exception.PlanNotFoundExceptionMapper;
import de.latlon.xplanbox.api.manager.exception.UnsupportedPlanExceptionMapper;
import de.latlon.xplanbox.api.manager.openapi.ManagerOpenApiFilter;
import de.latlon.xplanbox.api.manager.v1.DefaultApi;
import de.latlon.xplanbox.api.manager.v1.InfoApi;
import de.latlon.xplanbox.api.manager.v1.PlanAenderungenApi;
import de.latlon.xplanbox.api.manager.v1.PlanApi;
import de.latlon.xplanbox.api.manager.v1.PlanBasisdatenApi;
import de.latlon.xplanbox.api.manager.v1.PlanDokumentApi;
import de.latlon.xplanbox.api.manager.v1.PlanGueltigkeitApi;
import de.latlon.xplanbox.api.manager.v1.PlanRasterbasisApi;
import de.latlon.xplanbox.api.manager.v1.PlanTextApi;
import de.latlon.xplanbox.api.manager.v1.PlansApi;
import de.latlon.xplanbox.api.manager.v1.Status;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Application configuration for XPlanManager REST API. Example mapping for proxy mapping:
 * http://xplanbox.lat-lon.de/xmanager/api/v1/ ->
 * http://host:8080/xplan-api-manager/xmanager/api/v1/ Public address:
 * http://xplanbox.lat-lon.de/xmanager/api/v1 Internal address:
 * http://host:8080/xplan-api-manager/xmanager/api/v1
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@ApplicationPath("/xmanager/api/v1")
@Configuration
public class JerseyConfig extends ResourceConfig {

	private static final Logger LOG = getLogger(JerseyConfig.class);

	public static final String APP_PATH = "xmanager/api/v1";

	public JerseyConfig(@Context ServletContext servletContext,
			@Context ManagerApiConfiguration managerApiConfiguration) {
		property(ServerProperties.WADL_FEATURE_DISABLE, true);
		register(new ObjectMapperContextResolver());

		register(InfoApi.class);
		register(PlanAenderungenApi.class);
		register(PlanApi.class);
		register(PlanBasisdatenApi.class);
		register(PlanDokumentApi.class);
		register(PlanGueltigkeitApi.class);
		register(PlanRasterbasisApi.class);
		register(PlansApi.class);
		register(PlanTextApi.class);
		register(Status.class);
		register(ConstraintViolationExceptionMapper.class);
		register(UnsupportedContentTypeExceptionMapper.class);
		register(ValidatorExceptionMapper.class);
		register(XPlanApiExceptionMapper.class);
		register(StringListConverterProvider.class);

		register(AmbiguousBereichNummernExceptionMapper.class);
		register(PlanNotFoundExceptionMapper.class);
		register(UnsupportedPlanExceptionMapper.class);

		// packages("org.glassfish.jersey.examples.multipart");
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		OpenAPI openApi = new OpenAPI();
		openApi.setInfo(new Info().title("XPlanManagerAPI")
			.version("1.4.0")
			.description("XPlanManager REST API")
			.termsOfService(getTermsOfService(managerApiConfiguration))
			.license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")));
		addContact(openApi, managerApiConfiguration);
		openApi.servers(servers(servletContext, managerApiConfiguration));
		List<Tag> tags = createTags(managerApiConfiguration);
		openApi.tags(tags);

		DefaultApi openApiResource = new DefaultApi();
		SwaggerConfiguration oasConfig = new SwaggerConfiguration().openAPI(openApi)
			.filterClass(ManagerOpenApiFilter.class.getCanonicalName())
			.prettyPrint(true)
			.resourcePackages(Stream.of("de.latlon.xplanbox.api.manager.v1").collect(Collectors.toSet()));

		openApiResource.setOpenApiConfiguration(oasConfig);
		register(openApiResource);
		register(MultiPartFeature.class);
		LOG.info("XPlanApiManager successfully initialized");
	}

	private List<Tag> createTags(ManagerApiConfiguration managerApiConfiguration) {
		List<Tag> tags = new ArrayList<>();
		Tag manageTag = new Tag().name("manage").description("Manage XPlanGML documents");
		if (managerApiConfiguration != null && managerApiConfiguration.getDocumentationUrl() != null) {
			manageTag.externalDocs(new ExternalDocumentation().description("xPlanBox")
				.url(managerApiConfiguration.getDocumentationUrl()));
		}
		Tag searchTag = new Tag().name("search").description("Search for XPlanGML documents");
		if (managerApiConfiguration != null && managerApiConfiguration.getDocumentationUrl() != null) {
			searchTag.externalDocs(new ExternalDocumentation().description("xPlanBox")
				.url(managerApiConfiguration.getDocumentationUrl()));
		}
		tags.add(manageTag);
		tags.add(searchTag);
		return tags;
	}

	private void addContact(OpenAPI openApi, ManagerApiConfiguration managerApiConfiguration) {
		if (managerApiConfiguration != null && managerApiConfiguration.getContactEMailAddress() != null) {
			String contactEMailAddress = managerApiConfiguration.getContactEMailAddress();
			openApi.getInfo().setContact(new Contact().email(contactEMailAddress));
		}
	}

	private List<Server> servers(ServletContext servletContext, ManagerApiConfiguration managerApiConfiguration) {
		String serverUrl = getServerUrl(servletContext, managerApiConfiguration);
		Server server = new Server().url(serverUrl);
		return Collections.singletonList(server);
	}

	private String getServerUrl(ServletContext servletContext, ManagerApiConfiguration managerApiConfiguration) {
		StringBuilder serverUrl = new StringBuilder();
		if (managerApiConfiguration != null && managerApiConfiguration.getApiUrl() != null) {
			String apiEndpoint = managerApiConfiguration.getApiUrl().toString();
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

	private String getTermsOfService(ManagerApiConfiguration managerApiConfiguration) {
		if (managerApiConfiguration != null)
			return managerApiConfiguration.getTermsOfServiceUrl();
		return null;
	}

}
