package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.v1.model.Zeitraum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Path("/plan/{planId}/gueltigkeit")
public class PlanGueltigkeitApi {

    @GET
    @Produces({ "application/json" })
    @Operation(operationId = "getGueltigkeit", tags = { "edit", }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Zeitraum.class))) })
    public Zeitraum getGueltigkeit(
                    @PathParam("planId") @Parameter(description = "planId of the plan basisdaten to be returned") String planId ) {
        return new Zeitraum();
    }

    @PUT
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(operationId = "replaceGueltigkeit", tags = { "edit", }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Zeitraum.class))) }, requestBody = @RequestBody(content = {
                    @Content(mediaType = "application/json", schema = @Schema(type = "string", format = "binary", description = "XPlanArchive (application/zip) file to upload")) }))
    public Zeitraum replaceGueltigkeit(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned") String planId,
                    @Valid Zeitraum zeitraum ) {
        return zeitraum;
    }

}
