package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplanbox.api.validator.v1.handler.ValidationHandler;
import de.latlon.xplanbox.api.validator.v1.model.UploadReport;
import de.latlon.xplanbox.api.validator.v1.model.ValidationReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.io.InputStream;

@Path("/validate")

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJAXRSSpecServerCodegen", date = "2020-07-13T10:21:48.389+02:00[Europe/Berlin]")
@Component
public class ValidateApi {

    @Autowired
    private ValidationHandler validationHandler;

    @POST
    @Consumes({ "application/xml", "text/xml", "application/zip", "application/gml+xml" })
    @Produces({ "application/xml", "application/json", "text/xml" })
    @Operation(summary = "Upload XPlanGML or XPlanArchive", description = "Upload XPlanGML document", tags = {
                            "validate" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful upload", content = @Content(schema = @Schema(implementation = UploadReport.class))),
                            @ApiResponse(responseCode = "405", description = "Invalid input") })
    public UploadReport upload( InputStream plan )
                            throws IOException {
        String fileName = "ABC";
        return validationHandler.upload( plan, fileName );
    }

    @GET
    @Path("/{id}")
    @Produces({ "text/html", "application/xml", "text/xml", "application/json", "application/pdf", "application/zip" })
    @Operation(summary = "Validate uploaded XPlanGML or XPlanArchive", description = "Multiple", tags = { "validate" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful validation", content = @Content(schema = @Schema(implementation = ValidationReport.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid status value") })
    public ValidatorReport validate(
                            @PathParam("id")

                            @Parameter(description = "Id of the validation")
                                                    String id,
                            @QueryParam("name")

                            @Parameter(description = "Name of the validation")
                                                    String name,
                            @QueryParam("skipGeometrisch")
                            @DefaultValue("false")

                            @Parameter(description = "Geometrische Validierung ueberspringen")
                                                    Boolean skipGeometrisch,
                            @QueryParam("skipSemantisch")
                            @DefaultValue("false")

                            @Parameter(description = "Semantische Validierung ueberspringen")
                                                    Boolean skipSemantisch,
                            @QueryParam("skipFlaechenschluss")
                            @DefaultValue("false")

                            @Parameter(description = "Ueberpruefung des Flaechenschluss ueberspringen")
                                                    Boolean skipFlaechenschluss,
                            @QueryParam("skipGeltungsbereich")
                            @DefaultValue("false")

                            @Parameter(description = "Ueberpruefung des Geltungsbereich ueberspringen")
                                                    Boolean skipGeltungsbereich )
                            throws IOException, ValidatorException {
        String validationName = "ABC";
        return validationHandler.validate( id, validationName, skipGeometrisch, skipSemantisch, skipFlaechenschluss,
                                           skipGeltungsbereich );
    }
}
