package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.v1.model.Dokument;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Path("/plan/{planId}/dokument")
public class PlanDokumentApi {

    @GET
    @Produces({ "application/json" })
    @Operation(operationId = "getDokumente", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Dokument.class)))) })
    public List<Dokument> getDokumente(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned") String planId ) {
        ArrayList<Dokument> dokumente = new ArrayList<>();
        dokumente.add( new Dokument() );
        return dokumente;
    }

    @POST
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "addDokument", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Dokument.class))) }, requestBody = @RequestBody(content = @Content(mediaType = "multipart/form-data", encoding = @Encoding(name = "files", contentType = "application/pdf, application/msword, application/odt"))))
    public Dokument addDokument( @PathParam("planId")
                                  @Parameter(description = "ID of the plan to add dokumente", example = "123")
                                                  String planId,
                                  @FormDataParam("dokumentmodel") FormDataBodyPart dokumentmodel,
                                  @FormDataParam("datei") FormDataBodyPart datei ) {
        return dokumentmodel.getValueAs( Dokument.class );
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "getDokumentById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Dokument.class))) })
    public Dokument getDokumentById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned") String planId,
                    @PathParam("id") @Parameter(description = "id of the GML element to be returned") String id ) {
        return new Dokument();
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "application/pdf" })
    @Produces({ "application/json" })
    @Operation(operationId = "replaceDokumentById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Dokument.class)))
    })
    public Dokument replaceDokumentById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be updated", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the GML element to be updated") String id,
                    @Valid File datei ) {
        return new Dokument();
    }

    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "deleteDokumentById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Dokument.class))) })
    public Dokument deleteDokumentById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be deleted") String planId,
                    @PathParam("id") @Parameter(description = "id of the GML element to be deleted") String id ) {
        return new Dokument();
    }
}
