package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.handler.EditRasterbasisHandler;
import de.latlon.xplanbox.api.manager.v1.model.Rasterbasis;
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
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Path("/plan/{planId}/rasterbasis")
public class PlanRasterbasisApi {

    @Autowired
    private EditRasterbasisHandler editRasterbasisHandler;

    @GET
    @Produces({ "application/json" })
    @Operation(operationId = "getRasterBasis", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Rasterbasis.class)))) })
    public List<Rasterbasis> getRasterBasis(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned") String planId )
                    throws Exception {
        return editRasterbasisHandler.retrieveRasterbasis( planId );
    }

    @POST
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "addRasterBasis", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Rasterbasis.class))) }, requestBody = @RequestBody(content = @Content(mediaType = "multipart/form-data", encoding = {
                    @Encoding(name = "rasterbasismodel", contentType = "application/json"),
                    @Encoding(name = "datei", contentType = "application/pdf, application/msword, application/odt") })))
    public Rasterbasis addRasterBasis( @PathParam("planId")
                                       @Parameter(description = "ID of the plan to add dokumente", example = "123")
                                                       String planId,
                                       @FormDataParam("rasterbasismodel") FormDataBodyPart rasterbasismodel,
                                       @FormDataParam("rasterdatei") File rasterdatei,
                                       @FormDataParam("georeferenzdatei") File georeferenzdatei )
                    throws Exception {
        Rasterbasis rasterbasis = rasterbasismodel.getValueAs( Rasterbasis.class );
        return editRasterbasisHandler.addRasterbasis( planId, rasterbasis, rasterdatei, georeferenzdatei );
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "getRasterbasisById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Rasterbasis.class))) })
    public Rasterbasis getRasterbasisById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned") String planId,
                    @PathParam("id") @Parameter(description = "id of the GML element to be returned") String id )
                    throws Exception {
        return editRasterbasisHandler.retrieveRasterbasis( planId, id );
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "replaceRasterbasisById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Rasterbasis.class))) }, requestBody = @RequestBody(content = @Content(mediaType = "multipart/form-data", encoding = {
                    @Encoding(name = "rasterbasismodel", contentType = "application/json"),
                    @Encoding(name = "rasterdatei", contentType = "application/pdf, application/msword, application/odt"),
                    @Encoding(name = "georeferenzdatei", contentType = "text/plain") })))
    public Rasterbasis replaceRasterbasisById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be updated", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the GML element to be updated") String id,
                    @FormDataParam("rasterbasismodel") FormDataBodyPart rasterbasismodel,
                    @FormDataParam("rasterdatei") File rasterdatei,
                    @FormDataParam("georeferenzdatei") File georeferenzdatei )
                    throws Exception {
        Rasterbasis rasterbasis = rasterbasismodel.getValueAs( Rasterbasis.class );
        return editRasterbasisHandler.replaceRasterbasis( planId, id, rasterbasis, rasterdatei, georeferenzdatei );
    }

    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "deleteRasterbasisById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Rasterbasis.class))) })
    public Rasterbasis deleteRasterbasisById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be deleted") String planId,
                    @PathParam("id") @Parameter(description = "id of the GML element to be deleted") String id )
                    throws Exception {
        return editRasterbasisHandler.deleteRasterbasis( planId, id );
    }

}
