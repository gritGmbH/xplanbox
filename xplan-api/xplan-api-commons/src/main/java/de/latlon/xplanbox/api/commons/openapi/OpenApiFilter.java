/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.ordinalIndexOf;

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

	private void addOpenApiPath(OpenAPI openAPI) {
		ApiResponses apiResponses = new ApiResponses().addApiResponse("200",
				new ApiResponse().description("successful operation").content(new Content()
						.addMediaType("application/json", new MediaType().schema(new Schema().type("object")))));
		PathItem openApiPath = new PathItem().get(new Operation().operationId("openApi").summary("OpenAPI document")
				.description("API documentation").responses(apiResponses));
		openAPI.getPaths().addPathItem("/", openApiPath);

	}

	private void filterPath(OpenAPI openAPI) {
		Paths paths = openAPI.getPaths();
		Map<String, PathItem> filteredPathItems = new HashMap<>();
		paths.forEach((s, pathItem) -> {
			String newKey = createNewKey(s);
			filteredPathItems.put(newKey, pathItem);
		});
		paths.clear();
		paths.putAll(filteredPathItems);
	}

	private String createNewKey(String s) {
		if (s.startsWith("/xvalidator/api/v") || s.startsWith("/xmanager/api/v")) {
			int index = ordinalIndexOf(s, "/", 4);
			return s.substring(index);
		}
		return s;
	}

}
