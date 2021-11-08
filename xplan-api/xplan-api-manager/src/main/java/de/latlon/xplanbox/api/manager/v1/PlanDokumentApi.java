package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.handler.EditDokumentHandler;
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
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.File;
import java.util.List;

/**
 * Controller class for handling access to the dokument resource of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 4.4
 */
@Path("/plan/{planId}/dokument")
public class PlanDokumentApi {

    @Autowired
    private EditDokumentHandler editHandler;

    @GET
    @Produces({ "application/json" })
    @Operation(operationId = "getDokumente", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Dokument.class)))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found") })
    public List<Dokument> getDokumente(
                    @PathParam("planId") @Parameter(description = "planId of the plan to return dokumente", example = "123") String planId )
                    throws Exception {
        return editHandler.retrieveDokumente( planId );
    }

    @POST
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "addDokument", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Dokument.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID or dokument ID, plan or dokument not found") }, requestBody = @RequestBody(content = @Content(mediaType = "multipart/form-data", encoding = {
                    @Encoding(name = "dokumentmodel", contentType = "application/json"),
                    @Encoding(name = "datei", contentType = "application/pdf, application/msword, application/odt") })))
    public Dokument addDokument( @PathParam("planId")
                                 @Parameter(description = "ID of the plan to add dokumente", example = "123")
                                                 String planId,
                                 @FormDataParam("dokumentmodel") FormDataBodyPart dokumentmodel,
                                 @FormDataParam("datei") File file )
                    throws Exception {
        Dokument dokument = dokumentmodel.getValueAs( Dokument.class );
        return editHandler.addDokument( planId, dokument, file );
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "getDokumentById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Dokument.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID or dokument ID, plan or dokument not found") })
    public Dokument getDokumentById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to get dokument", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the Dokument to be returned (Pattern of the ID: referenzName-referenzURL, other characters than a-z, A-Z, 0-9, _, - are removed)", example = "Legende123-") String id )
                    throws Exception {
        return editHandler.retrieveDokument( planId, id );
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "replaceDokumentById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Dokument.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID or dokument ID, plan or dokument not found")
    }, requestBody = @RequestBody(content = @Content(mediaType = "multipart/form-data", encoding = {
                    @Encoding(name = "dokumentmodel", contentType = "application/json"),
                    @Encoding(name = "datei", contentType = "application/pdf, application/msword, application/odt")
    })))
    public Dokument replaceDokumentById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to replace dokument", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the Dokument to be updated (Pattern of the ID: referenzName-referenzURL, other characters than a-z, A-Z, 0-9, _, - are removed)", example = "Legende123-") String id,
                    @FormDataParam("dokumentmodel") FormDataBodyPart dokumentmodel,
                    @FormDataParam("datei") File file
    )
                    throws Exception {
        Dokument dokument = dokumentmodel.getValueAs( Dokument.class );
        return editHandler.replaceDokument( planId, id, dokument, file );
    }

    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "deleteDokumentById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Dokument.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID or dokument ID, plan or dokument not found") })
    public Dokument deleteDokumentById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to delete dokument", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the Dokument to be deleted (Pattern of the ID: referenzName-referenzURL, other characters than a-z, A-Z, 0-9, _, - are removed)", example = "Legende123-") String id )
                    throws Exception {
        return editHandler.deleteDokument( planId, id );
    }

}
