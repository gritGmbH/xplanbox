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

import de.latlon.xplanbox.api.manager.handler.EditBasisdatenHandler;
import de.latlon.xplanbox.api.manager.v1.model.Basisdaten;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

/**
 * Controller class for handling access to the basisdaten resource of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @since 4.4
 */
@Path("/plan/{planId}/basisdaten")
public class PlanBasisdatenApi {

	@Autowired
	private EditBasisdatenHandler editBasisdatenHandler;

	@GET
	@Produces({ "application/json" })
	@Operation(operationId = "getBasisdaten", tags = { "edit", },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Basisdaten.class))),
					@ApiResponse(responseCode = "400",
							description = "Unsupported plan version or planID is not a valid int value"),
					@ApiResponse(responseCode = "404", description = "Invalid planID, plan not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Basisdaten getBasisdaten(@PathParam("planId") @Parameter(
			description = "ID of the plan basisdaten to be returned", example = "123") String planId) throws Exception {
		return editBasisdatenHandler.retrieveBasisdaten(planId);
	}

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Operation(operationId = "replaceBasisdaten", tags = { "edit", },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Basisdaten.class))),
					@ApiResponse(responseCode = "400",
							description = "Unsupported plan version or planID is not a valid int value"),
					@ApiResponse(responseCode = "404", description = "Invalid planID, plan not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available"),
					@ApiResponse(responseCode = "422", description = "Request body contains invalid content"), },
			requestBody = @RequestBody(content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Basisdaten.class)) }))
	public Basisdaten replaceBasisdaten(@PathParam("planId") @Parameter(description = "ID of the plan to be returned",
			example = "123") String planId, @Valid Basisdaten basisdaten) throws Exception {
		return editBasisdatenHandler.replaceBasisdaten(planId, basisdaten);
	}

}
