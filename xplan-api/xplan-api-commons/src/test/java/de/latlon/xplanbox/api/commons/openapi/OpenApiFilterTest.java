/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
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
package de.latlon.xplanbox.api.commons.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OpenApiFilterTest {

	@Test
	public void verifyFilterOpenAPI_ThatPathItemKeyIsCorrected_xmanager() {
		OpenApiFilter openApiFilter = new OpenApiFilter();
		OpenAPI openApi = new OpenAPI();
		openApi.setPaths(new Paths());
		openApi.getPaths().addPathItem("/xmanager/api/v1/plans", new PathItem());
		openApiFilter.filterOpenAPI(openApi, null, null, null);

		assertThat(openApi.getPaths().get("/plans"), is(notNullValue()));
		assertThat(openApi.getPaths().get("/"), is(notNullValue()));
	}

	@Test
	public void verifyFilterOpenAPI_ThatPathItemKeyIsCorrected_xvalidator() {
		OpenApiFilter openApiFilter = new OpenApiFilter();
		OpenAPI openApi = new OpenAPI();
		openApi.setPaths(new Paths());
		openApi.getPaths().addPathItem("/xvalidator/api/v1.5-3_1/info/test", new PathItem());
		openApiFilter.filterOpenAPI(openApi, null, null, null);

		assertThat(openApi.getPaths().get("/info/test"), is(notNullValue()));
		assertThat(openApi.getPaths().get("/"), is(notNullValue()));
	}

	@Test
	public void verifyFilterOpenAPI_ThatPathItemKeyIsCorrected_noPath() {
		OpenApiFilter openApiFilter = new OpenApiFilter();
		OpenAPI openApi = new OpenAPI();
		openApi.setPaths(new Paths());
		openApi.getPaths().addPathItem("/xmanager/api/v1.5-3_1", new PathItem());
		openApiFilter.filterOpenAPI(openApi, null, null, null);

		assertThat(openApi.getPaths().get("/"), is(notNullValue()));
		assertThat(openApi.getPaths().get(""), is(nullValue()));
	}

	@Test
	public void verifyFilterOpenAPI_ThatPathItemKeyIsNotCorrected() {
		OpenApiFilter openApiFilter = new OpenApiFilter();
		OpenAPI openApi = new OpenAPI();
		openApi.setPaths(new Paths());
		openApi.getPaths().addPathItem("/info/test", new PathItem());
		openApiFilter.filterOpenAPI(openApi, null, null, null);

		assertThat(openApi.getPaths().get("/info/test"), is(notNullValue()));
		assertThat(openApi.getPaths().get("/"), is(notNullValue()));
	}

}
