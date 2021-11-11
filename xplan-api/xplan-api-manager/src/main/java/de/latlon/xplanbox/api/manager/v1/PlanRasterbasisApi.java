package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.exception.MissingRequestEntity;
import de.latlon.xplanbox.api.manager.handler.EditRasterbasisHandler;
import de.latlon.xplanbox.api.manager.v1.model.Rasterbasis;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
 * Controller class for handling access to the rasterbasis resource of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 4.4
 */
@Path("/plan/{planId}/rasterbasis")
public class PlanRasterbasisApi {

    @Autowired
    private EditRasterbasisHandler editRasterbasisHandler;

    @GET
    @Produces({ "application/json" })
    @Operation(operationId = "getRasterBasis", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Rasterbasis.class)))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
    public List<Rasterbasis> getRasterBasis(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned", example = "123") String planId )
                    throws Exception {
        return editRasterbasisHandler.retrieveRasterbasis( planId );
    }

    @POST
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "addRasterBasis", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Rasterbasis.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version or rasterbasismodel is missing") })
    public Rasterbasis addRasterBasis( @PathParam("planId")
                                       @Parameter(description = "ID of the plan to add dokumente", example = "123")
                                                       String planId,
                                       @Parameter(schema = @Schema(implementation = Rasterbasis.class), required = true)
                                       @FormDataParam("rasterbasismodel") FormDataBodyPart rasterbasismodel,
                                       @Parameter(schema = @Schema(type = "string", format = "binary"))
                                       @FormDataParam("rasterdatei") File rasterdatei,
                                       @Parameter(schema = @Schema(type = "string", format = "binary"))
                                       @FormDataParam("georeferenzdatei") File georeferenzdatei )
                    throws Exception {
        if ( rasterbasismodel == null ) {
            throw new MissingRequestEntity( "Multipart attachment 'rasterbasismodel' is missing." );
        }
        Rasterbasis rasterbasis = rasterbasismodel.getValueAs( Rasterbasis.class );
        return editRasterbasisHandler.addRasterbasis( planId, rasterbasis, rasterdatei, georeferenzdatei );
    }

    @GET
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "getRasterbasisById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Rasterbasis.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID or Rasterbasis ID, plan or Rasterbasis not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
    public Rasterbasis getRasterbasisById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be returned", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the Rasterbasis to be returned (Pattern of the ID: referenzName-referenzURL, other characters than a-z, A-Z, 0-9, _, - are removed", example = "Referenz123-") String id )
                    throws Exception {
        return editRasterbasisHandler.retrieveRasterbasis( planId, id );
    }

    @PUT
    @Path("/{id}")
    @Consumes({ "multipart/form-data" })
    @Produces({ "application/json" })
    @Operation(operationId = "replaceRasterbasisById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Rasterbasis.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID or Rasterbasis ID, plan or Rasterbasis not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version or rasterbasismodel is missing") })
    public Rasterbasis replaceRasterbasisById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be updated", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the Rasterbasis to be updated (Pattern of the ID: referenzName-referenzURL, other characters than a-z, A-Z, 0-9, _, - are removed", example = "Referenz123-") String id,
                    @Parameter(schema = @Schema(implementation = Rasterbasis.class), required = true)
                    @FormDataParam("rasterbasismodel") FormDataBodyPart rasterbasismodel,
                    @Parameter(schema = @Schema(type = "string", format = "binary"))
                    @FormDataParam("rasterdatei") File rasterdatei,
                    @Parameter(schema = @Schema(type = "string", format = "binary"))
                    @FormDataParam("georeferenzdatei") File georeferenzdatei )
                    throws Exception {
        if ( rasterbasismodel == null ) {
            throw new MissingRequestEntity( "Multipart attachment 'rasterbasismodel' is missing." );
        }
        Rasterbasis rasterbasis = rasterbasismodel.getValueAs( Rasterbasis.class );
        return editRasterbasisHandler.replaceRasterbasis( planId, id, rasterbasis, rasterdatei, georeferenzdatei );
    }

    @DELETE
    @Path("/{id}")
    @Produces({ "application/json" })
    @Operation(operationId = "deleteRasterbasisById", tags = { "edit" }, responses = {
                    @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Rasterbasis.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid plan ID or Rasterbasis ID, plan or Rasterbasis not found"),
                    @ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
    public Rasterbasis deleteRasterbasisById(
                    @PathParam("planId") @Parameter(description = "planId of the plan to be deleted", example = "123") String planId,
                    @PathParam("id") @Parameter(description = "id of the Rasterbasis to be deleted (Pattern of the ID: referenzName-referenzURL, other characters than a-z, A-Z, 0-9, _, - are removed", example = "Referenz123-") String id )
                    throws Exception {
        return editRasterbasisHandler.deleteRasterbasis( planId, id );
    }

}
