package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplanbox.api.validator.v1.model.ValidationReport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/validate")
@Api(description = "the validate API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-26T09:59:16.298+02:00[Europe/Berlin]")
public class ValidateApi {

    @POST
    @Consumes({ "application/octet-stream", "text/xml", "application/gml+xml" })
    @Produces({ "application/json", "application/xml", "text/xml", "text/html", "application/pdf", "application/zip" })
    @ApiOperation(value = "Validate XPlanGML or XPlanArchive", notes = "Validates XPlanGML or XPlanArchive file", response = ValidationReport.class, tags={ "validate" })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "ValidationReport", response = ValidationReport.class),
        @ApiResponse(code = 400, message = "Invalid input", response = Void.class),
        @ApiResponse(code = 405, message = "Invalid method", response = Void.class),
        @ApiResponse(code = 406, message = "Invalid content only XPlanGML or ZIP with XPlanGML is accepted", response = Void.class),
        @ApiResponse(code = 415, message = "Unsupported Media Type, only XML or ZIP is accepted", response = Void.class),
        @ApiResponse(code = 200, message = "Unexpected error", response = Void.class)
    })
    public Response validate(@Valid File body,@HeaderParam("X-Filename")    @ApiParam("Name of the file to be uploaded") String xFilename,@QueryParam("name")   @ApiParam("Name of the validation")  String name,@QueryParam("skipSemantisch")  @DefaultValue("false")  @ApiParam("Semantische Validierung ueberspringen")  Boolean skipSemantisch,@QueryParam("skipGeometrisch")  @DefaultValue("false")  @ApiParam("Geometrische Validierung ueberspringen")  Boolean skipGeometrisch,@QueryParam("skipFlaechenschluss")  @DefaultValue("false")  @ApiParam("Ueberpruefung des Flaechenschluss ueberspringen")  Boolean skipFlaechenschluss,@QueryParam("skipGeltungsbereich")  @DefaultValue("false")  @ApiParam("Ueberpruefung des Geltungsbereich ueberspringen")  Boolean skipGeltungsbereich) {
        return Response.ok().entity("magic!").build();
    }
}
