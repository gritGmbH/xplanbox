/*-
 * #%L
 * xplan-api-validator - Modul zur Gruppierung der REST-API
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplanbox.api.validator;

import de.latlon.xplanbox.api.commons.ObjectMapperContextResolver;
import de.latlon.xplanbox.api.commons.openapi.OpenApiFilter;
import de.latlon.xplanbox.api.validator.config.ValidatorApiConfiguration;
import de.latlon.xplanbox.api.validator.v1.DefaultApi;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Application configuration for XPlanValidator REST API. Example mapping for proxy
 * mapping: http://xplanbox.lat-lon.de/xvalidator/api/v1/ ->
 * http://host:8080/xplan-api-validator/xvalidator/api/v1/ Public address:
 * http://xplanbox.lat-lon.de/xvalidator/ Internal address:
 * http://host:8080/xplan-api-validator/xvalidator/
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@ApplicationPath("/xvalidator/api/v1")
public class ApplicationPathConfig extends ResourceConfig {

	private static final Logger LOG = getLogger(ApplicationPathConfig.class);

	private static final String APP_PATH = "xvalidator/api/v1";

	public ApplicationPathConfig(@Context ServletContext servletContext,
			ValidatorApiConfiguration validatorApiConfiguration) {
		super();
		register(new ObjectMapperContextResolver());
		packages("de.latlon.xplanbox.api.validator.config");
		packages("de.latlon.xplanbox.api.validator.handler");
		packages("de.latlon.xplanbox.api.validator.v1");
		packages("de.latlon.xplanbox.api.commons.exception");
		OpenAPI openApi = new OpenAPI();
		openApi.setInfo(new Info().title("XPlanValidatorAPI").version("1.0.0").description("XPlanValidator REST API")
				.termsOfService(getTermsOfService(validatorApiConfiguration))
				.license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")));
		addContact(openApi, validatorApiConfiguration);
		openApi.servers(servers(servletContext, validatorApiConfiguration));
		Tag tag = createTag(validatorApiConfiguration);
		openApi.tags(Collections.singletonList(tag));

		DefaultApi openApiResource = new DefaultApi();
		SwaggerConfiguration oasConfig = new SwaggerConfiguration().openAPI(openApi)
				.filterClass(OpenApiFilter.class.getCanonicalName()).prettyPrint(true)
				.resourcePackages(Stream.of("de.latlon.xplanbox.api.validator.v1").collect(Collectors.toSet()));

		openApiResource.setOpenApiConfiguration(oasConfig);
		register(openApiResource);
		LOG.info("XPlanApiValidator successfully initialized");
	}

	private Tag createTag(ValidatorApiConfiguration validatorApiConfiguration) {
		Tag tag = new Tag().name("validate").description("Validate XPlanGML documents");
		if (validatorApiConfiguration != null && validatorApiConfiguration.getDocumentationUrl() != null) {
			tag.externalDocs(new ExternalDocumentation().description("xPlanBox")
					.url(validatorApiConfiguration.getDocumentationUrl()));
		}
		return tag;
	}

	private void addContact(OpenAPI openApi, ValidatorApiConfiguration validatorApiConfiguration) {
		if (validatorApiConfiguration != null && validatorApiConfiguration.getContactEMailAdress() != null) {
			String contactEMailAdress = validatorApiConfiguration.getContactEMailAdress();
			openApi.getInfo().setContact(new Contact().email(contactEMailAdress));
		}
	}

	private List<Server> servers(ServletContext servletContext, ValidatorApiConfiguration validatorApiConfiguration) {
		String serverUrl = getServerUrl(servletContext, validatorApiConfiguration);
		Server server = new Server().url(serverUrl);
		return Collections.singletonList(server);
	}

	private String getServerUrl(ServletContext servletContext, ValidatorApiConfiguration validatorApiConfiguration) {
		StringBuilder serverUrl = new StringBuilder();
		if (validatorApiConfiguration != null && validatorApiConfiguration.getApiUrl() != null) {
			String apiEndpoint = validatorApiConfiguration.getApiUrl().toString();
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

	private String getTermsOfService(ValidatorApiConfiguration validatorApiConfiguration) {
		if (validatorApiConfiguration != null)
			return validatorApiConfiguration.getTermsOfServiceUrl();
		return null;
	}

}
