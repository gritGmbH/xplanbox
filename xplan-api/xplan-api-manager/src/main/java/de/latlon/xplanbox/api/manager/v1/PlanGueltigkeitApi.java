package de.latlon.xplanbox.api.manager.v1;

/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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

import de.latlon.xplanbox.api.manager.handler.EditGueltigkeitHandler;
import de.latlon.xplanbox.api.manager.v1.model.Zeitraum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * Controller class for handling access to the gueltigkeit resource of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 4.4
 */
@Path("/plan/{planId}/gueltigkeit")
public class PlanGueltigkeitApi {

	@Autowired
	private EditGueltigkeitHandler editGueltigkeitHandler;

	@GET
	@Produces({ "application/json" })
	@Operation(operationId = "getGueltigkeit", tags = { "edit", },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Zeitraum.class))),
					@ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
					@ApiResponse(responseCode = "400",
							description = "Unsupported Plan version or Plan ID is not a valid int value") })
	public Zeitraum getGueltigkeit(
			@PathParam("planId") @Parameter(description = "planId of the plan gueltigkeit to be returned",
					example = "123") String planId)
			throws Exception {
		return editGueltigkeitHandler.retrieveGueltigkeit(planId);
	}

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Operation(operationId = "replaceGueltigkeit", tags = { "edit", },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Zeitraum.class))),
					@ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
					@ApiResponse(responseCode = "400",
							description = "Unsupported Plan version or Plan ID is not a valid int value") },
			requestBody = @RequestBody(content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Zeitraum.class)) }))
	public Zeitraum replaceGueltigkeit(
			@PathParam("planId") @Parameter(description = "planId of the plan to be returned",
					example = "123") String planId,
			@Valid Zeitraum zeitraum) throws Exception {
		return editGueltigkeitHandler.replaceGueltigkeit(planId, zeitraum);
	}

}
