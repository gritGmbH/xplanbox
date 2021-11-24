package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.handler.EditAenderungenHandler;
import de.latlon.xplanbox.api.manager.v1.model.Aenderungen;
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
 * Controller class for handling access to the aenderungen resource of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @since 4.4
 */
@Path("/plan/{planId}/aenderungen")
public class PlanAenderungenApi {

	@Autowired
	private EditAenderungenHandler editAenderungenHandler;

	@GET
	@Produces({ "application/json" })
	@Operation(operationId = "getAenderung", tags = { "edit", },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Aenderungen.class))),
					@ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
					@ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
	public Aenderungen getAenderung(@PathParam("planId") @Parameter(description = "planId of the plan to be returned",
			example = "123") String planId) throws Exception {
		return editAenderungenHandler.retrieveAenderungen(planId);
	}

	@PUT
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Operation(operationId = "replaceAenderung", tags = { "edit", },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Aenderungen.class))),
					@ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
					@ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") },
			requestBody = @RequestBody(content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Aenderungen.class)) }))
	public Aenderungen replaceAenderung(
			@PathParam("planId") @Parameter(description = "planId of the plan to be returned",
					example = "123") String planId,
			@Valid Aenderungen aenderungen) throws Exception {
		return editAenderungenHandler.replaceAenderungen(planId, aenderungen);
	}

}
