package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.manager.PlanInfoBuilder;
import de.latlon.xplanbox.api.manager.handler.PlanHandler;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;
import de.latlon.xplanbox.api.manager.v1.model.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Variant;
import java.io.File;

import static de.latlon.xplanbox.api.commons.ValidatorConverter.createValidationSettings;
import static de.latlon.xplanbox.api.commons.ValidatorConverter.detectOrCreateValidationName;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;

@Path("/plan")
@Api(description = "the plan API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class PlanApi {

    @Autowired
    private PlanHandler planHandler;

    @Context
    private ManagerConfiguration managerConfiguration;

    @Context
    private Request request;

    @POST
    @Consumes({ "application/octet-stream" })
    @Produces({ "application/json" })
    @ApiOperation(value = "Import the plan", notes = "Imports the plan", response = Status.class, tags = { "manage", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Status.class),
                            @ApiResponse(code = 400, message = "Invalid input", response = Void.class),
                            @ApiResponse(code = 405, message = "Invalid method", response = Void.class),
                            @ApiResponse(code = 406, message = "Invalid content only ZIP with XPlanGML is accepted", response = Void.class),
                            @ApiResponse(code = 415, message = "Unsupported Media Type, only ZIP is accepted", response = Void.class),
                            @ApiResponse(code = 200, message = "Unexpected error", response = Void.class) })
    public Response callImport( @Valid File body,
                                @HeaderParam("X-Filename")
                                @ApiParam("Name of the file to be uploaded")
                                                        String xFilename,
                                @QueryParam("skipSemantisch")
                                @DefaultValue("false")
                                @ApiParam("skip semantische Validierung")
                                                        Boolean skipSemantisch,
                                @QueryParam("skipGeometrisch")
                                @DefaultValue("false")
                                @ApiParam("skip geometrische Validierung")
                                                        Boolean skipGeometrisch,
                                @QueryParam("skipFlaechenschluss")
                                @DefaultValue("false")
                                @ApiParam("skip Flaechenschluss Ueberpruefung")
                                                        Boolean skipFlaechenschluss,
                                @QueryParam("skipGeltungsbereich")
                                @DefaultValue("false")
                                @ApiParam("skip Geltungsbereich Ueberpruefung")
                                                        Boolean skipGeltungsbereich,
                                @QueryParam("internalId")
                                @ApiParam("internalId links to VerfahrensId")
                                                        String internalId,
                                @QueryParam("planStatus")
                                @ApiParam("target for data storage, overrides the default derived from xplan:rechtsstand")
                                                        String planStatus )
                            throws Exception {
        String validationName = detectOrCreateValidationName( xFilename );
        ValidationSettings validationSettings = createValidationSettings( validationName, skipGeometrisch,
                                                                          skipSemantisch, skipFlaechenschluss,
                                                                          skipGeltungsbereich );

        Status status = planHandler.importPlan( body, validationName, validationSettings, internalId, planStatus );
        return Response.ok().entity( status ).build();
    }

    @DELETE
    @Path("/{planId}")
    @ApiOperation(value = "Delete plan identified by the given plan ID", notes = "Deletes an existing plan identified by the given plan ID, limited to plans in status \"in Aufstellung\"", response = Void.class, tags = {
                            "manage", })
    @ApiResponses(value = { @ApiResponse(code = 204, message = "successful operation", response = Void.class),
                            @ApiResponse(code = 400, message = "Invalid tag value", response = Void.class),
                            @ApiResponse(code = 404, message = "Invalid plan ID, not found", response = Void.class) })
    public Response delete(
                            @PathParam("planId")
                            @ApiParam("ID of the plan to be removed")
                                                    String planId )
                            throws Exception {
        planHandler.deletePlan( planId );
        return Response.noContent().build();
    }

    @GET
    @Path("/{planId}")
    @Produces({ "application/json", "application/xml", "application/zip" })
    @ApiOperation(value = "Get plan identified by the given plan ID", notes = "Returns an existing plan identified by the given plan ID", response = PlanInfo.class, tags = {
                            "manage", "search", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = PlanInfo.class),
                            @ApiResponse(code = 400, message = "Invalid tag value", response = Void.class),
                            @ApiResponse(code = 404, message = "Invalid plan ID, not found", response = Void.class) })
    public Response getById(
                            @Context
                                                    UriInfo uriInfo,
                            @PathParam("planId")
                            @ApiParam("ID of the plan to be returned")
                                                    String planId )
                            throws Exception {
        if ( isZipRequested() ) {
            StreamingOutput plan = planHandler.exportPlan( planId );
            return Response.ok( plan ).type( APPLICATION_ZIP ).header( "Content-Disposition",
                                                                       "attachment; filename=\"" + planId
                                                                       + ".zip\"" ).build();
        }
        XPlan planById = planHandler.findPlanById( planId );
        PlanInfo planInfo = new PlanInfoBuilder( planById, uriInfo ).wmsEndpoint(
                                managerConfiguration.getwmsEndpoint() ).build();
        return Response.ok().entity( planInfo ).build();
    }

    @GET
    @Path("/name/{planName}")
    @Produces({ "application/json", "application/xml", "application/zip" })
    @ApiOperation(value = "Get plan identified by the given planName", notes = "Returns an existing plan identified by the given planName", response = PlanInfo.class, tags = {
                            "search" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PlanInfo.class),
                            @ApiResponse(code = 404, message = "Invalid plan name, not found", response = Void.class) })
    public Response getByName(
                            @Context
                                                    UriInfo uriInfo,
                            @PathParam("planName")
                            @Pattern(regexp = "^[A-Za-z0-9_-]*$")
                            @ApiParam("planName of the plan to be return")
                                                    String planName )
                            throws Exception {
        XPlan xPlan = planHandler.findPlanByName( planName );
        if ( isZipRequested() ) {
            StreamingOutput plan = planHandler.exportPlan( xPlan.getId() );
            return Response.ok( plan ).type( APPLICATION_ZIP ).header( "Content-Disposition",
                                                                       "attachment; filename=\"" + planName
                                                                       + ".zip\"" ).build();
        }
        PlanInfo planInfo = new PlanInfoBuilder( xPlan, uriInfo ).wmsEndpoint(
                                managerConfiguration.getwmsEndpoint() ).build();
        return Response.ok().entity( planInfo ).build();
    }

    private boolean isZipRequested() {
        Variant.VariantListBuilder acceptedMediaTypes = Variant.mediaTypes( APPLICATION_JSON_TYPE, APPLICATION_XML_TYPE,
                                                                            APPLICATION_ZIP_TYPE );
        Variant selectVariant = request.selectVariant( acceptedMediaTypes.build() );
        if ( selectVariant != null )
            return APPLICATION_ZIP_TYPE.equals( selectVariant );
        return false;
    }

}
