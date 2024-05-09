/*-
 * #%L
 * xplan-core-api - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplanbox.api.commons.openapi;

import io.swagger.v3.core.filter.AbstractSpecFilter;
import io.swagger.v3.core.model.ApiDescription;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OpenApiFilter extends AbstractSpecFilter {

	@Override
	public Optional<OpenAPI> filterOpenAPI(OpenAPI openAPI, Map<String, List<String>> params,
			Map<String, String> cookies, Map<String, List<String>> headers) {
		filterPath(openAPI);
		addOpenApiPath(openAPI);
		return super.filterOpenAPI(openAPI, params, cookies, headers);
	}

	@Override
	public Optional<Parameter> filterParameter(Parameter parameter, Operation operation, ApiDescription api,
			Map<String, List<String>> params, Map<String, String> cookies, Map<String, List<String>> headers) {
		if ("query".equals(parameter.getIn()) && "profiles".equals(parameter.getName()))
			parameter.setExplode(false);
		return super.filterParameter(parameter, operation, api, params, cookies, headers);
	}

	private void addOpenApiPath(OpenAPI openAPI) {
		ApiResponses apiResponses = new ApiResponses()
			.addApiResponse("200",
					new ApiResponse().description("successful operation")
						.content(new Content().addMediaType("application/json",
								new MediaType().schema(new Schema().type("object")))))
			.addApiResponse("406", new ApiResponse().description("Requested format is not available"));
		PathItem openApiPath = new PathItem().get(new Operation().operationId("openApi")
			.summary("OpenAPI document")
			.description("API documentation")
			.responses(apiResponses));
		openAPI.getPaths().addPathItem("/", openApiPath);

	}

	private void filterPath(OpenAPI openAPI) {
		Paths paths = openAPI.getPaths();
		Map<String, PathItem> filteredPathItems = new HashMap<>();
		paths.forEach((path, pathItem) -> {
			String newKey = createNewKey(path);
			if (!"/".equals(newKey))
				filteredPathItems.put(newKey, pathItem);
		});
		paths.clear();
		paths.putAll(filteredPathItems);
	}

	private String createNewKey(String path) {
		Pattern pattern = Pattern.compile("\\/(xvalidator|xmanager|xdokumente)\\/api\\/v[\\d_\\-\\.]*(\\/|)");
		Matcher matcher = pattern.matcher(path);
		return matcher.replaceFirst("/");
	}

}
