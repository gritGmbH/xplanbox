package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.handler.EditTextHandler;
import de.latlon.xplanbox.api.manager.v1.model.Text;
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
 * Controller class for handling access to the text resource of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 4.4
 */
@Path("/plan/{planId}/text")
public class PlanTextApi {

    @Autowired
    private EditTextHandler editTextHandler;

    @GET
    @Produces({ "application/json" })
    @Operation(operationId = "getTexte", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Text.class)))) })
    public List<Text> getTexte(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned") String planId )
                    throws Exception {
        return editTextHandler.retrieveTexte( planId );
    }

    @POST
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "addText", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Text.class))) }, requestBody = @RequestBody(content = @Content(mediaType = "multipart/form-data", encoding = {
                    @Encoding(name = "textmodel", contentType = "application/json"),
                    @Encoding(name = "datei", contentType = "application/pdf, application/msword, application/odt") })))
    public Text addText( @PathParam("planId")
                         @Parameter(description = "ID of the plan to add dokumente", example = "123")
                                         String planId,
                         @FormDataParam("textmodel") FormDataBodyPart textmodel,
                         @FormDataParam("datei") File file )
                    throws Exception {
        Text text = textmodel.getValueAs( Text.class );
        return editTextHandler.addText( planId, text, file );
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "getTextById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Text.class))) })
    public Text getTextById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned") String planId,
                    @PathParam("id") @Parameter(description = "id of the Text to be returned (GML Id of the feature)") String id )
                    throws Exception {
        return editTextHandler.retrieveText( planId, id );
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "replaceTextById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Text.class))) }, requestBody = @RequestBody(content = @Content(mediaType = "multipart/form-data", encoding = {
                    @Encoding(name = "textmodel", contentType = "application/json"),
                    @Encoding(name = "datei", contentType = "application/pdf, application/msword, application/odt") })))
    public Text replaceTextById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be updated", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the Text to be updated (GML Id of the feature)") String id,
                    @FormDataParam("textmodel") FormDataBodyPart textmodel,
                    @FormDataParam("datei") File file )
                    throws Exception {
        Text text = textmodel.getValueAs( Text.class );
        return editTextHandler.replaceText( planId, id, text, file );
    }

    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "deleteTextById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Text.class))) })
    public Text deleteTextById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be deleted") String planId,
                    @PathParam("id") @Parameter(description = "id of the Text to be deleted (GML Id of the feature)") String id )
                    throws Exception {
        return editTextHandler.deleteText( planId, id );
    }
}
