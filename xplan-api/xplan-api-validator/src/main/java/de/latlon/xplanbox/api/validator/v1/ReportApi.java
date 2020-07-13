package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplanbox.api.validator.v1.model.ValidationReport;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/report")

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJAXRSSpecServerCodegen", date = "2020-07-13T10:21:48.389+02:00[Europe/Berlin]")
public class ReportApi {

    @GET
    @Path("/{id}")
    @Produces({ "text/html", "application/xml", "text/xml", "application/json", "application/pdf", "application/zip" })
    @Operation(summary = "Download report by id", description = "", tags={ "validate" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ValidationReport.class))),
        @ApiResponse(responseCode = "400", description = "Invalid tag value")
    })
    public Response report( @PathParam("id")

 @Parameter(description = "Id of the validation") String id
) {
        return Response.ok().entity("magic!").build();
    }}
