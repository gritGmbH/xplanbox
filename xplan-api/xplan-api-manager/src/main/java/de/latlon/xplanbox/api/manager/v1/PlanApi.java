/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import de.latlon.xplanbox.api.manager.PlanInfoBuilder;
import de.latlon.xplanbox.api.manager.config.DefaultValidationConfiguration;
import de.latlon.xplanbox.api.manager.config.ManagerApiConfiguration;
import de.latlon.xplanbox.api.manager.handler.PlanHandler;
import de.latlon.xplanbox.api.manager.v1.model.Link;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;
import de.latlon.xplanbox.api.manager.v1.model.StatusMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import static de.latlon.xplanbox.api.manager.v1.model.Link.RelEnum.SELF;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;
import static javax.ws.rs.core.MediaType.TEXT_XML;

@Path("/plan")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class PlanApi {

    private final static MediaType[] MEDIA_TYPES_SEARCH = { APPLICATION_JSON_TYPE, APPLICATION_XML_TYPE,
                                                            APPLICATION_ZIP_TYPE };

    @Autowired
    private PlanHandler planHandler;

    @Context
    private ManagerApiConfiguration managerApiConfiguration;

    @POST
    @Consumes({ "application/octet-stream", "application/zip", "application/x-zip", "application/x-zip-compressed" })
    @Produces({ "application/json" })
    @Operation(operationId = "import", summary = "Import the plan", description = "Imports the plan", tags = {
                            "manage", }, responses = {
                            @ApiResponse(responseCode = "201", description = "successful operation", content = @Content(schema = @Schema(implementation = PlanInfo.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ValidationReport.class))),
                            @ApiResponse(responseCode = "406", description = "Invalid content only ZIP with XPlanGML is accepted") }, requestBody = @RequestBody(content = {
                            @Content(mediaType = "application/octet-stream", schema = @Schema(type = "string", format = "binary", description = "XPlanArchive (application/zip) file to upload")),
                            @Content(mediaType = "application/zip", schema = @Schema(type = "string", format = "binary", description = "XPlanArchive (application/zip) file to upload")),
                            @Content(mediaType = "application/x-zip", schema = @Schema(type = "string", format = "binary", description = "XPlanArchive (application/zip) file to upload")),
                            @Content(mediaType = "application/x-zip-compressed", schema = @Schema(type = "string", format = "binary", description = "XPlanArchive (application/zip) file to upload")) }, required = true))
    public Response callImport(
                            @Context
                                                    Request request, @Valid File body,
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
        DefaultValidationConfiguration validationConfig = managerApiConfiguration.getDefaultValidationConfiguration();
        ValidationSettings validationSettings = createValidationSettings( validationName,
                                                                          overwriteByRequest( skipGeometrisch,
                                                                                              validationConfig.isSkipGeometrisch() ),
                                                                          overwriteByRequest( skipSemantisch,
                                                                                              validationConfig.isSkipSemantisch() ),
                                                                          overwriteByRequest( skipFlaechenschluss,
                                                                                              validationConfig.isSkipFlaechenschluss() ),
                                                                          overwriteByRequest( skipGeltungsbereich,
                                                                                              validationConfig.isSkipGeltungsbereich() ) );
        XPlan xPlan = planHandler.importPlan( body, xFilename, validationSettings, internalId, planStatus );
        MediaType requestedMediaType = requestedMediaType( request );
        PlanInfo planInfo = createPlanInfo( requestedMediaType, xPlan );
        return Response.created( getSelfLink( planInfo ) ).entity( planInfo ).build();
    }

    @DELETE
    @Path("/{planId}")
    @Produces({ "application/json", "application/xml" })
    @Operation(summary = "Delete plan identified by the given plan ID", description = "Deletes an existing plan identified by the given plan ID", tags = {
                            "manage", }, responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = StatusMessage.class))),
                            @ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found") })
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
                            @ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found") })
    public Response getById(
                            @Context
                                                    Request request,
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
        PlanInfo planInfo = createPlanInfo( requestedMediaType, planById );
        return Response.ok().entity( planInfo ).build();
    }

    @GET
    @Path("/name/{planName}")
    @Produces({ "application/json", "application/xml" })
    @Operation(summary = "Get plan identified by the given planName", description = "Returns a list of plans which match exactly the given planName", tags = {
                            "search" }, responses = { @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PlanInfo.class))) }),
                                                      @ApiResponse(responseCode = "404", description = "Invalid plan name, plan not found") })
    public Response getByName(
                            @Context
                                                    Request request,
                            @PathParam("planName")
                            @Parameter(description = "planName of the plan to be returned", example = "bplan_123, fplan-123, rplan20200803", schema = @Schema(pattern = "^[A-Za-z0-9_-]*$"))
                                                    String planName )
                            throws Exception {
        List<XPlan> plans = planHandler.findPlansByName( planName );
        List<PlanInfo> planInfos = plans.stream().map( xPlan -> {
            return new PlanInfoBuilder( xPlan, managerApiConfiguration )
                                    .selfMediaType( APPLICATION_JSON )
                                    .alternateMediaType( Arrays.asList( APPLICATION_XML, APPLICATION_ZIP ) )
                                    .build();
        } ).collect( Collectors.toList() );
        return Response.ok().entity( planInfos ).build();
    }

    private PlanInfo createPlanInfo( MediaType requestedMediaType, XPlan planById )
                            throws URISyntaxException {
        List<String> alternateMediaTypes = alternateMediaTypes( requestedMediaType );
        return new PlanInfoBuilder( planById, managerApiConfiguration ).selfMediaType(
                                requestedMediaType.toString() ).alternateMediaType( alternateMediaTypes ).build();
    }

    private URI getSelfLink( PlanInfo planInfo ) {
        for ( Link link : planInfo.getLinks() ) {
            if ( SELF.equals( link.getRel() ) )
                return link.getHref();
        }
        return null;
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