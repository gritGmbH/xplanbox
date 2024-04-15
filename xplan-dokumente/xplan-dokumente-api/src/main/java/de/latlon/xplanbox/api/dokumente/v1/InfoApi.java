/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumenteAPI
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
package de.latlon.xplanbox.api.dokumente.v1;

import de.latlon.xplanbox.api.dokumente.handler.ConfigHandler;
import de.latlon.xplanbox.api.dokumente.v1.model.SystemConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Controller class for handling access to the application info resource.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @since 7.0
 */
@Path("/info")
public class InfoApi {

	@Autowired
	private ConfigHandler configHandler;

	@GET
	@Produces({ "application/json" })
	@Operation(summary = "Show system and application configuration",
			description = "Returns the system and application configuration",
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = SystemConfig.class))),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Response showConfig() {
		return Response.ok().entity(configHandler.describeSystem()).build();
	}

}
