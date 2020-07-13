package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplanbox.api.validator.v1.model.UploadReport;
import de.latlon.xplanbox.api.validator.v1.model.ValidationReport;
import de.latlon.xplanbox.api.validator.v1.model.XPlanGML;

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

@Path("/validate")

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJAXRSSpecServerCodegen", date = "2020-07-13T10:21:48.389+02:00[Europe/Berlin]")
public class ValidateApi {

    @POST
    @Consumes({ "application/xml", "text/xml", "application/zip", "application/gml+xml" })
    @Produces({ "application/xml", "application/json", "text/xml" })
    @Operation(summary = "Upload XPlanGML or XPlanArchive", description = "Upload XPlanGML document", tags={ "validate" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful upload", content = @Content(schema = @Schema(implementation = UploadReport.class))),
        @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    public Response upload(@Valid XPlanGML body) {
        return Response.ok().entity("magic!").build();
    }
    @GET
    @Path("/{id}")
    @Produces({ "text/html", "application/xml", "text/xml", "application/json", "application/pdf", "application/zip" })
    @Operation(summary = "Validate uploaded XPlanGML or XPlanArchive", description = "Multiple", tags={ "validate" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful validation", content = @Content(schema = @Schema(implementation = ValidationReport.class))),
        @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    public Response validate( @PathParam("id")

 @Parameter(description = "Id of the validation") String id
,  @QueryParam("name") 

 @Parameter(description = "Name of the validation")  String name
,  @QueryParam("skipGeometrisch") @DefaultValue("false") 

 @Parameter(description = "Geometrische Validierung ueberspringen")  Boolean skipGeometrisch
,  @QueryParam("skipSemantisch") @DefaultValue("false") 

 @Parameter(description = "Semantische Validierung ueberspringen")  Boolean skipSemantisch
,  @QueryParam("skipFlaechenschluss") @DefaultValue("false") 

 @Parameter(description = "Ueberpruefung des Flaechenschluss ueberspringen")  Boolean skipFlaechenschluss
,  @QueryParam("skipGeltungsbereich") @DefaultValue("false") 

 @Parameter(description = "Ueberpruefung des Geltungsbereich ueberspringen")  Boolean skipGeltungsbereich
) {
        return Response.ok().entity("magic!").build();
    }}
