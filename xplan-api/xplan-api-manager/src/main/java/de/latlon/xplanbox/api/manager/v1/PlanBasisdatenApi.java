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

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
    @Operation(operationId = "getBasisdaten", tags = { "edit", }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Basisdaten.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
    public Basisdaten getBasisdaten(
                    @PathParam("planId") @Parameter(description = "planId of the plan basisdaten to be returned", example = "123") String planId )
                    throws Exception {
        return editBasisdatenHandler.retrieveBasisdaten( planId );
    }

    @PUT
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "replaceBasisdaten", tags = { "edit", }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Basisdaten.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") }, requestBody = @RequestBody(content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Basisdaten.class)) }))
    public Basisdaten replaceBasisdaten(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned", example = "123") String planId,
                    @Valid Basisdaten basisdaten )
                    throws Exception {
        return editBasisdatenHandler.replaceBasisdaten( planId, basisdaten );
    }

}
