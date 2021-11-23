package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.exception.MissingRequestEntity;
import de.latlon.xplanbox.api.manager.handler.EditTextHandler;
import de.latlon.xplanbox.api.manager.v1.model.Text;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
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
import java.io.InputStream;
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
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Text.class)))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
    public List<Text> getTexte(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned", example = "123") String planId )
                    throws Exception {
        return editTextHandler.retrieveTexte( planId );
    }

    @POST
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "addText", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Text.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version or textmodel is missing") })
    public Text addText( @PathParam("planId")
                         @Parameter(description = "ID of the plan to add texte", example = "123")
                                         String planId,
                         @Parameter(schema = @Schema(implementation = Text.class), required = true)
                         @FormDataParam("textmodel") FormDataBodyPart textmodel,
                         @Parameter(schema = @Schema(type = "string", format = "binary"))
                         @FormDataParam("datei") InputStream datei,
                         @Parameter(hidden = true)
                         @FormDataParam("datei") FormDataContentDisposition dateiMeta )
                    throws Exception {
        if ( textmodel == null ) {
            throw new MissingRequestEntity( "Multipart attachment 'textmodel' is missing." );
        }
        Text text = textmodel.getValueAs( Text.class );
        File file = editTextHandler.storeAsFile( datei, dateiMeta );
        return editTextHandler.addText( planId, text, file );
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "getTextById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Text.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID or Text ID, plan or Text not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
    public Text getTextById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the Text to be returned (GML Id of the feature)", example = "GML_ID_123") String id )
                    throws Exception {
        return editTextHandler.retrieveText( planId, id );
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "replaceTextById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Text.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID or Text ID, plan or Text not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version  or textmodel is missing") })
    public Text replaceTextById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be updated", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the Text to be updated (GML Id of the feature)", example = "GML_ID_123") String id,
                    @Parameter(schema = @Schema(implementation = Text.class), required = true)
                    @FormDataParam("textmodel") FormDataBodyPart textmodel,
                    @Parameter(schema = @Schema(type = "string", format = "binary"))
                    @FormDataParam("datei") InputStream datei,
                    @Parameter(hidden = true)
                    @FormDataParam("datei") FormDataContentDisposition dateiMeta
    )
                    throws Exception {
        if ( textmodel == null ) {
            throw new MissingRequestEntity( "Multipart attachment 'textmodel' is missing." );
        }
        Text text = textmodel.getValueAs( Text.class );
        File file = editTextHandler.storeAsFile( datei, dateiMeta );
        return editTextHandler.replaceText( planId, id, text, file );
    }

    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "deleteTextById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Text.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID or Text ID, plan or Text not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
    public Text deleteTextById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be deleted", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the Text to be deleted (GML Id of the feature)", example = "GML_ID_123") String id )
                    throws Exception {
        return editTextHandler.deleteText( planId, id );
    }
}
