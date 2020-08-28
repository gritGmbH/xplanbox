package de.latlon.xplanbox.api.manager.v1;

import java.io.File;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;
import de.latlon.xplanbox.api.manager.v1.model.Status;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.swagger.annotations.*;

import java.io.InputStream;
import java.util.Map;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

@Path("/plan")
@Api(description = "the plan API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class PlanApi {

    @POST
    @Consumes({ "application/octet-stream" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Import the plan", notes = "Imports the plan", response = Status.class, tags={ "manage",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Status.class),
        @ApiResponse(code = 400, message = "Invalid input", response = Void.class),
        @ApiResponse(code = 405, message = "Invalid method", response = Void.class),
        @ApiResponse(code = 406, message = "Invalid content only ZIP with XPlanGML is accepted", response = Void.class),
        @ApiResponse(code = 415, message = "Unsupported Media Type, only ZIP is accepted", response = Void.class),
        @ApiResponse(code = 200, message = "Unexpected error", response = Void.class)
    })
    public Response callImport(@Valid File body,@HeaderParam("X-Filename")    @ApiParam("Name of the file to be uploaded") String xFilename,@QueryParam("skipSemantisch")  @DefaultValue("false")  @ApiParam("skip semantische Validierung")  Boolean skipSemantisch,@QueryParam("skipGeometrisch")  @DefaultValue("false")  @ApiParam("skip geometrische Validierung")  Boolean skipGeometrisch,@QueryParam("skipFlaechenschluss")  @DefaultValue("false")  @ApiParam("skip Flaechenschluss Ueberpruefung")  Boolean skipFlaechenschluss,@QueryParam("skipGeltungsbereich")  @DefaultValue("false")  @ApiParam("skip Geltungsbereich Ueberpruefung")  Boolean skipGeltungsbereich,@QueryParam("internalId")   @ApiParam("internalId links to VerfahrensId")  String internalId,@QueryParam("planStatus")   @ApiParam("target for data storage, overrides the default derived from xplan:rechtsstand")  String planStatus) {
        return Response.ok().entity("magic!").build();
    }

    @DELETE
    @Path("/{planId}")
    @ApiOperation(value = "Delete plan identified by the given plan ID", notes = "Deletes an existing plan identified by the given plan ID, limited to plans in status \"in Aufstellung\"", response = Void.class, tags={ "manage",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "successful operation", response = Void.class),
        @ApiResponse(code = 400, message = "Invalid tag value", response = Void.class),
        @ApiResponse(code = 404, message = "Invalid plan ID, not found", response = Void.class)
    })
    public Response delete(@PathParam("planId") @ApiParam("ID of the plan to be removed") String planId) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/{planId}")
    @Produces({ "application/json", "application/xml", "application/zip" })
    @ApiOperation(value = "Get plan identified by the given plan ID", notes = "Returns an existing plan identified by the given plan ID", response = PlanInfo.class, tags={ "manage", "search",  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = PlanInfo.class),
        @ApiResponse(code = 400, message = "Invalid tag value", response = Void.class),
        @ApiResponse(code = 404, message = "Invalid plan ID, not found", response = Void.class)
    })
    public Response getById(@PathParam("planId") @ApiParam("ID of the plan to be returned") String planId) {
        return Response.ok().entity("magic!").build();
    }

    @GET
    @Path("/name/{planName}")
    @Produces({ "application/json", "application/xml", "application/zip" })
    @ApiOperation(value = "Get plan identified by the given planName", notes = "Returns an existing plan identified by the given planName", response = PlanInfo.class, tags={ "search" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = PlanInfo.class),
        @ApiResponse(code = 404, message = "Invalid plan name, not found", response = Void.class)
    })
    public Response getByName(@PathParam("planName") @Pattern(regexp="^[A-Za-z0-9_-]*$") @ApiParam("planName of the plan to be return") String planName) {
        return Response.ok().entity("magic!").build();
    }
}
