package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplan.manager.configuration.DefaultValidationConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import de.latlon.xplanbox.api.manager.PlanInfoBuilder;
import de.latlon.xplanbox.api.manager.handler.PlanHandler;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;
import de.latlon.xplanbox.api.manager.v1.model.Status;
import de.latlon.xplanbox.api.manager.v1.model.StatusMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Variant;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplanbox.api.commons.ValidatorConverter.createValidationSettings;
import static de.latlon.xplanbox.api.commons.ValidatorConverter.detectOrCreateValidationName;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;

@Path("/plan")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class PlanApi {

    private final static MediaType[] MEDIA_TYPES_SEARCH = { APPLICATION_JSON_TYPE, APPLICATION_XML_TYPE,
                                                            APPLICATION_ZIP_TYPE };

    @Autowired
    private PlanHandler planHandler;

    @Context
    private ManagerConfiguration managerConfiguration;

    @POST
    @Consumes({ "application/octet-stream", "application/zip", "application/x-zip", "application/x-zip-compressed" })
    @Produces({ "application/json" })
    @Operation(operationId = "import", summary = "Import the plan", description = "Imports the plan", tags = {
                            "manage", }, responses = {
                            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(schema = @Schema(implementation = Status.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ValidationReport.class))),
                            @ApiResponse(responseCode = "406", description = "Invalid content only ZIP with XPlanGML is accepted") }, requestBody = @RequestBody(content = @Content(mediaType = "application/octet-stream", schema = @Schema(type = "string", format = "binary", description = "XPlanArchive (application/zip) file to upload")), required = true))
    public Response callImport( @Context UriInfo uriInfo,
                                @Valid File body,
                                @HeaderParam("X-Filename")
                                @Parameter(description = "Name of the file to be uploaded", example = "File names such as xplan.gml, xplan.xml, xplan.zip")
                                                        String xFilename,
                                @QueryParam("skipSemantisch")
                                @DefaultValue("false")
                                @Parameter(description = "skip semantische Validierung")
                                                        Boolean skipSemantisch,
                                @QueryParam("skipGeometrisch")
                                @DefaultValue("false")
                                @Parameter(description = "skip geometrische Validierung")
                                                        Boolean skipGeometrisch,
                                @QueryParam("skipFlaechenschluss")
                                @DefaultValue("false")
                                @Parameter(description = "skip Flaechenschluss Ueberpruefung")
                                                        Boolean skipFlaechenschluss,
                                @QueryParam("skipGeltungsbereich")
                                @DefaultValue("false")
                                @Parameter(description = "skip Geltungsbereich Ueberpruefung")
                                                        Boolean skipGeltungsbereich,
                                @QueryParam("internalId")
                                @Parameter(description = "internalId links to VerfahrensId")
                                                        String internalId,
                                @QueryParam("planStatus")
                                @Parameter(description = "target for data storage, overrides the default derived from xplan:rechtsstand", schema = @Schema(allowableValues = {
                                                        "IN_AUFSTELLUNG", "FESTGESTELLT",
                                                        "ARCHIVIERT" }, example = "FESTGESTELLT"))
                                                        String planStatus )
                            throws Exception {
        String validationName = detectOrCreateValidationName( xFilename );
        DefaultValidationConfiguration validationConfig = managerConfiguration.getDefaultValidationConfiguration();
        ValidationSettings validationSettings = createValidationSettings( validationName,
                                                                          overwriteByRequest( skipGeometrisch,
                                                                                              validationConfig.isSkipGeometrisch() ),
                                                                          overwriteByRequest( skipSemantisch,
                                                                                              validationConfig.isSkipSemantisch() ),
                                                                          overwriteByRequest( skipFlaechenschluss,
                                                                                              validationConfig.isSkipFlaechenschluss() ),
                                                                          overwriteByRequest( skipGeltungsbereich,
                                                                                              validationConfig.isSkipGeltungsbereich() ) );
        Status status = planHandler.importPlan( body, xFilename, validationSettings, internalId, planStatus );
        URI selfRef = uriInfo.getBaseUriBuilder().path( "plan" ).path( status.getPlanId().toString() ).build();
        return Response.created( selfRef ).entity( status ).build();
    }

    @DELETE
    @Path("/{planId}")
    @Operation(summary = "Delete plan identified by the given plan ID", description = "Deletes an existing plan identified by the given plan ID, limited to plans in status \"in Aufstellung\"", tags = {
                            "manage", }, responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = StatusMessage.class))),
                            @ApiResponse(responseCode = "404", description = "Invalid plan ID, not found") })
    public Response delete(
                            @PathParam("planId")
                            @Parameter(description = "ID of the plan to be removed", example = "123")
                                                    String planId )
                            throws Exception {
        StatusMessage statusMessage = planHandler.deletePlan( planId );
        return Response.ok().entity( statusMessage ).build();
    }

    @GET
    @Path("/{planId}")
    @Produces({ "application/json", "application/xml", "application/zip" })
    @Operation(summary = "Get plan identified by the given plan ID", description = "Returns an existing plan identified by the given plan ID", tags = {
                            "manage", "search", }, responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                                                    @Content(mediaType = "application/json", schema = @Schema(implementation = PlanInfo.class)),
                                                    @Content(mediaType = "application/xml", schema = @Schema(implementation = PlanInfo.class)),
                                                    @Content(mediaType = "application/zip", schema = @Schema(type = "string", format = "binary")) }),
                            @ApiResponse(responseCode = "404", description = "Invalid plan ID, not found") })
    public Response getById(
                            @Context
                                                    Request request,
                            @Context
                                                    UriInfo uriInfo,
                            @PathParam("planId")
                            @Parameter(description = "ID of the plan to be returned", example = "123")
                                                    String planId )
                            throws Exception {
        MediaType requestedMediaType = requestedMediaType( request );
        if ( APPLICATION_ZIP_TYPE.equals( requestedMediaType ) ) {
            StreamingOutput plan = planHandler.exportPlan( planId );
            return Response.ok( plan ).type( APPLICATION_ZIP ).header( "Content-Disposition",
                                                                       "attachment; filename=\"" + planId
                                                                       + ".zip\"" ).build();
        }
        XPlan planById = planHandler.findPlanById( planId );
        PlanInfo planInfo = createPlanInfo( uriInfo, requestedMediaType, planById );
        return Response.ok().entity( planInfo ).build();
    }

    @GET
    @Path("/name/{planName}")
    @Produces({ "application/json", "application/xml", "application/zip" })
    @Operation(summary = "Get plan identified by the given planName", description = "Returns an existing plan identified by the given planName", tags = {
                            "search" }, responses = { @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PlanInfo.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = PlanInfo.class)),
                            @Content(mediaType = "application/zip", schema = @Schema(type = "string", format = "binary")) }),
                                                      @ApiResponse(responseCode = "404", description = "Invalid plan name, not found") })
    public Response getByName(
                            @Context
                                                    Request request,
                            @Context
                                                    UriInfo uriInfo,
                            @PathParam("planName")
                            @Parameter(description = "planName of the plan to be return", example = "bplan_123, fplan-123, rplan20200803", schema = @Schema(pattern = "^[A-Za-z0-9_-]*$"))
                                                    String planName )
                            throws Exception {
        XPlan xPlan = planHandler.findPlanByName( planName );
        MediaType requestedMediaType = requestedMediaType( request );
        if ( APPLICATION_ZIP_TYPE.equals( requestedMediaType ) ) {
            StreamingOutput plan = planHandler.exportPlan( xPlan.getId() );
            return Response.ok( plan ).type( APPLICATION_ZIP ).header( "Content-Disposition",
                                                                       "attachment; filename=\"" + planName
                                                                       + ".zip\"" ).build();
        }
        PlanInfo planInfo = createPlanInfo( uriInfo, requestedMediaType, xPlan );
        return Response.ok().entity( planInfo ).build();
    }

    private PlanInfo createPlanInfo( UriInfo uriInfo, MediaType requestedMediaType, XPlan planById )
                            throws URISyntaxException {
        List<String> alternateMediaTypes = alternateMediaTypes( requestedMediaType );
        return new PlanInfoBuilder( planById, uriInfo ).wmsEndpoint(
                                managerConfiguration.getWmsEndpoint() ).requestedMediaType(
                                requestedMediaType.toString() ).alternateMediaType( alternateMediaTypes ).build();
    }

    private MediaType requestedMediaType( Request request ) {
        Variant.VariantListBuilder acceptedMediaTypes = Variant.mediaTypes( MEDIA_TYPES_SEARCH );
        Variant selectVariant = request.selectVariant( acceptedMediaTypes.build() );
        if ( selectVariant == null )
            return APPLICATION_JSON_TYPE;
        return selectVariant.getMediaType();
    }

    private List<String> alternateMediaTypes( MediaType requestedMediaType ) {
        return Arrays.stream( MEDIA_TYPES_SEARCH ).filter( mediaType -> !mediaType.equals( requestedMediaType ) ).map(
                                mediaType -> mediaType.toString() ).collect( Collectors.toList() );
    }

    private boolean overwriteByRequest( Boolean requested, boolean configured ) {
        if ( requested != null )
            return requested;
        return configured;
    }

}
