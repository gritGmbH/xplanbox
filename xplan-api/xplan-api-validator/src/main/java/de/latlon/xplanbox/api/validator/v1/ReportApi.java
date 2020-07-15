package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplanbox.api.validator.v1.handler.ValidationHandler;
import de.latlon.xplanbox.api.validator.v1.model.ValidationReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/report")

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJAXRSSpecServerCodegen", date = "2020-07-13T10:21:48.389+02:00[Europe/Berlin]")
public class ReportApi {

    @Autowired
    private ValidationHandler validationHandler;

    @GET
    @Path("/{id}")
    @Produces({ "text/html", "application/xml", "text/xml", "application/json", "application/pdf", "application/zip" })
    @Operation(summary = "Download report by id", description = "", tags = { "validate" })
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ValidationReport.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid tag value") })
    public Response report(
                            @PathParam("id")

                            @Parameter(description = "Id of the validation")
                                                    String id )
                            throws IOException {
        java.nio.file.Path report = validationHandler.writeReport( id, id );
        return Response.ok( FileUtils.readFileToByteArray( report.toFile() ) ).type( "application/zip" ).header(
                                "Content-Disposition", "attachment; filename=\"" + id + ".zip\"" ).build();
    }

}
