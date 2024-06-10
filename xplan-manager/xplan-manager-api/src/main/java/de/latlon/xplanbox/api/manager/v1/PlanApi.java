/*-
 * #%L
 * xplan-manager-api - Software zur Verwaltung von XPlanGML Daten
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.exception.InvalidXPlanGmlOrArchive;
import de.latlon.xplanbox.api.commons.exception.UnsupportedParameterValue;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import de.latlon.xplanbox.api.manager.PlanInfoBuilder;
import de.latlon.xplanbox.api.manager.config.DefaultValidationConfiguration;
import de.latlon.xplanbox.api.manager.config.JerseyConfig;
import de.latlon.xplanbox.api.manager.config.ManagerApiConfiguration;
import de.latlon.xplanbox.api.manager.exception.InvalidApiVersion;
import de.latlon.xplanbox.api.manager.handler.PlanHandler;
import de.latlon.xplanbox.api.manager.v1.model.Bereich;
import de.latlon.xplanbox.api.manager.v1.model.Link;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;
import de.latlon.xplanbox.api.manager.v1.model.StatusMessage;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.StreamingOutput;
import jakarta.ws.rs.core.Variant;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.util.ContentTypeChecker.checkContentTypesOfXPlanArchiveOrGml;
import static de.latlon.xplan.commons.util.TextPatternConstants.INTERNALID_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.SIMPLE_NAME_PATTERN;
import static de.latlon.xplanbox.api.commons.ValidatorConverter.createValidationSettings;
import static de.latlon.xplanbox.api.commons.ValidatorConverter.detectOrCreateValidationName;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP_TYPE;
import static de.latlon.xplanbox.api.manager.XPlanBoxContentTypes.XPLANBOX_NO_VERSION_JSON;
import static de.latlon.xplanbox.api.manager.XPlanBoxContentTypes.XPLANBOX_NO_VERSION_JSON_TYPE;
import static de.latlon.xplanbox.api.manager.XPlanBoxContentTypes.XPLANBOX_V1_JSON;
import static de.latlon.xplanbox.api.manager.XPlanBoxContentTypes.XPLANBOX_V1_JSON_TYPE;
import static de.latlon.xplanbox.api.manager.XPlanBoxContentTypes.XPLANBOX_V2_JSON;
import static de.latlon.xplanbox.api.manager.XPlanBoxContentTypes.XPLANBOX_V2_JSON_TYPE;
import static de.latlon.xplanbox.api.manager.v1.model.Link.RelEnum.SELF;
import static io.swagger.v3.oas.annotations.enums.Explode.FALSE;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static jakarta.ws.rs.core.MediaType.APPLICATION_XML;
import static jakarta.ws.rs.core.MediaType.APPLICATION_XML_TYPE;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Controller class for handling access to a plan identified by it's id.
 *
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @since 4.0
 */
@Path("/plan")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class PlanApi {

	private static final Logger LOG = getLogger(PlanApi.class);

	private final static MediaType[] MEDIA_TYPES_SEARCH = { APPLICATION_JSON_TYPE, XPLANBOX_NO_VERSION_JSON_TYPE,
			XPLANBOX_V1_JSON_TYPE, XPLANBOX_V2_JSON_TYPE, APPLICATION_XML_TYPE, APPLICATION_ZIP_TYPE };

	private static final boolean WITH_GEOMETRISCH_VALIDATION = false;

	@Autowired
	private XPlanArchiveCreator archiveCreator;

	@Autowired
	private PlanHandler planHandler;

	@Context
	private ManagerApiConfiguration managerApiConfiguration;

	@POST
	@Consumes({ "application/octet-stream", "application/zip", "application/x-zip", "application/x-zip-compressed" })
	@Produces({ "application/json", XPLANBOX_NO_VERSION_JSON, XPLANBOX_V1_JSON, XPLANBOX_V2_JSON })
	@Operation(operationId = "import", summary = "Import a XPlanGML or XPlanArchive",
			description = "Imports a XPlanGML or XPlanArchive", tags = { "manage", },
			responses = {
					@ApiResponse(responseCode = "201", description = "successful operation", content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = PlanInfo.class)),
							@Content(mediaType = XPLANBOX_NO_VERSION_JSON,
									schema = @Schema(implementation = PlanInfo.class)),
							@Content(mediaType = XPLANBOX_V1_JSON, schema = @Schema(implementation = PlanInfo.class)),
							@Content(mediaType = XPLANBOX_V2_JSON,
									array = @ArraySchema(schema = @Schema(implementation = PlanInfo.class))) }),
					@ApiResponse(responseCode = "400", description = "Invalid input",
							content = @Content(schema = @Schema(implementation = ValidationReport.class))),
					@ApiResponse(responseCode = "406", description = "Requested format is not available"),
					@ApiResponse(responseCode = "415",
							description = "Unsupported media type or content - only xml/gml, zip are accepted; all zip files entries must also match the supported content types for XPlanArchives"),
					@ApiResponse(responseCode = "422",
							description = "Invalid content - the content of the XPlanGML file must conform to the specification of xPlanBox XPlanGML files") },
			requestBody = @RequestBody(
					content = {
							@Content(mediaType = "application/octet-stream",
									schema = @Schema(type = "string", format = "binary",
											description = "XPlanArchive (application/zip) file to upload")),
							@Content(mediaType = "application/zip",
									schema = @Schema(type = "string", format = "binary",
											description = "XPlanArchive (application/zip) file to upload")),
							@Content(mediaType = "application/x-zip",
									schema = @Schema(type = "string", format = "binary",
											description = "XPlanArchive (application/zip) file to upload")),
							@Content(mediaType = "application/x-zip-compressed",
									schema = @Schema(type = "string", format = "binary",
											description = "XPlanArchive (application/zip) file to upload")),
							@Content(mediaType = "text/xml",
									schema = @Schema(type = "string", format = "binary",
											description = "XPlanGML file to upload")),
							@Content(mediaType = "application/gml+xml", schema = @Schema(type = "string",
									format = "binary", description = "XPlanGML file to upload")) },
					required = true))
	public Response callImportZip(@Context Request request, @Valid File body,
			@HeaderParam("X-Filename") @Parameter(description = "Name of the file to be uploaded",
					example = "File names such as xplan.gml, xplan.xml, xplan.zip",
					schema = @Schema(pattern = SIMPLE_NAME_PATTERN)) String xFilename,
			@QueryParam("skipSemantisch") @DefaultValue("false") @Parameter(
					description = "skip semantische Validierung") Boolean skipSemantisch,
			@QueryParam("skipFlaechenschluss") @DefaultValue("false") @Parameter(
					description = "skip Flaechenschluss Ueberpruefung") Boolean skipFlaechenschluss,
			@QueryParam("skipGeltungsbereich") @DefaultValue("false") @Parameter(
					description = "skip Geltungsbereich Ueberpruefung") Boolean skipGeltungsbereich,
			@QueryParam("skipLaufrichtung") @DefaultValue("false") @Parameter(
					description = "skip Laufrichtung Ueberpruefung") Boolean skipLaufrichtung,
			@QueryParam("profiles") @Parameter(
					description = "Names of profiles which shall be additionaly used for validation",
					explode = FALSE) List<String> profiles,
			@QueryParam("internalId") @Parameter(description = "internalId links to VerfahrensId",
					schema = @Schema(pattern = INTERNALID_PATTERN)) String internalId,
			@QueryParam("planStatus") @Parameter(
					description = "target for data storage, overrides the default derived from xplan:rechtsstand",
					schema = @Schema(allowableValues = { "IN_AUFSTELLUNG", "FESTGESTELLT", "ARCHIVIERT" },
							example = "FESTGESTELLT")) String planStatus)
			throws Exception {
		checkContentTypesOfXPlanArchiveOrGml(body.toPath());
		XPlanArchive xPlanArchive;
		try {
			xPlanArchive = archiveCreator.createXPlanArchiveFromZip(body);
		}
		catch (Exception e) {
			throw new InvalidXPlanGmlOrArchive("Could not read attached file as XPlanArchive", e);
		}
		return callImport(request, xFilename, skipSemantisch, skipFlaechenschluss, skipGeltungsbereich,
				skipLaufrichtung, profiles, internalId, planStatus, xPlanArchive);
	}

	@POST
	@Consumes({ "text/xml", "application/gml+xml" })
	@Produces({ "application/json", XPLANBOX_NO_VERSION_JSON, XPLANBOX_V1_JSON, XPLANBOX_V2_JSON })
	@Hidden
	public Response callImportGml(@Context Request request, @Valid File body,
			@HeaderParam("X-Filename") @Parameter(schema = @Schema(pattern = SIMPLE_NAME_PATTERN)) String xFilename,
			@QueryParam("skipSemantisch") @DefaultValue("false") Boolean skipSemantisch,
			@QueryParam("skipFlaechenschluss") @DefaultValue("false") Boolean skipFlaechenschluss,
			@QueryParam("skipGeltungsbereich") @DefaultValue("false") Boolean skipGeltungsbereich,
			@QueryParam("skipLaufrichtung") @DefaultValue("false") Boolean skipLaufrichtung,
			@QueryParam("profiles") List<String> profiles,
			@QueryParam("internalId") @Parameter(schema = @Schema(pattern = INTERNALID_PATTERN)) String internalId,
			@QueryParam("planStatus") String planStatus) throws Exception {
		checkContentTypesOfXPlanArchiveOrGml(body.toPath());
		checkInternalId(internalId);
		XPlanArchive xPlanArchive;
		try {
			xPlanArchive = archiveCreator.createXPlanArchiveFromGml(body);
		}
		catch (Exception e) {
			throw new InvalidXPlanGmlOrArchive("Could not read attached file as XPlanArchive", e);
		}
		return callImport(request, xFilename, skipSemantisch, skipFlaechenschluss, skipGeltungsbereich,
				skipLaufrichtung, profiles, internalId, planStatus, xPlanArchive);
	}

	@DELETE
	@Path("/{planId}")
	@Produces({ "application/json", "application/xml" })
	@Operation(summary = "Delete plan identified by the given planID",
			description = "Deletes an existing plan identified by the given planID", tags = { "manage", },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = StatusMessage.class))),
					@ApiResponse(responseCode = "400", description = "PlanID is not a valid int value"),
					@ApiResponse(responseCode = "404", description = "Invalid planID, plan not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Response delete(@PathParam("planId") @Parameter(description = "ID of the plan to be removed",
			example = "123") String planId) throws Exception {
		StatusMessage statusMessage = planHandler.deletePlan(planId);
		return Response.ok().entity(statusMessage).build();
	}

	@GET
	@Path("/{planId}")
	@Produces({ "application/json", "application/xml", "application/zip" })
	@Operation(summary = "Get plan identified by the given planID",
			description = "Returns an existing plan identified by the given planID", tags = { "manage", "search", },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation", content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = PlanInfo.class)),
							@Content(mediaType = "application/xml", schema = @Schema(implementation = PlanInfo.class)),
							@Content(mediaType = "application/zip",
									schema = @Schema(type = "string", format = "binary")) }),
					@ApiResponse(responseCode = "400", description = "PlanID is not a valid int value"),
					@ApiResponse(responseCode = "404", description = "Invalid planID, plan not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Response getById(@Context Request request,
			@PathParam("planId") @Parameter(description = "ID of the plan to be returned",
					example = "123") String planId)
			throws Exception {
		MediaType requestedMediaType = requestedMediaType(request);
		if (APPLICATION_ZIP_TYPE.equals(requestedMediaType)) {
			StreamingOutput plan = planHandler.exportPlan(planId);
			return Response.ok(plan)
				.type(APPLICATION_ZIP)
				.header("Content-Disposition", "attachment; filename=\"" + planId + ".zip\"")
				.build();
		}
		XPlan planById = planHandler.findPlanById(planId);
		PlanInfo planInfo = createPlanInfo(requestedMediaType, planById);
		return Response.ok().entity(planInfo).build();
	}

	@GET
	@Path("/{planId}/archive")
	@Produces("application/zip")
	@Operation(summary = "Get plan as XPlanArchive identified by the given planID",
			description = "Returns the XPlanArchive of an existing plan identified by the given planID",
			tags = { "manage", "search", },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = { @Content(mediaType = "application/zip",
									schema = @Schema(type = "string", format = "binary")) }),
					@ApiResponse(responseCode = "400", description = "PlanID is not a valid int value"),
					@ApiResponse(responseCode = "404", description = "Invalid planID, plan not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Response getArchiveById(@Context Request request,
			@PathParam("planId") @Parameter(description = "ID of the plan to be returned",
					example = "123") String planId)
			throws Exception {
		StreamingOutput plan = planHandler.exportPlan(planId);
		return Response.ok(plan)
			.type(APPLICATION_ZIP)
			.header("Content-Disposition", "attachment; filename=\"" + planId + ".zip\"")
			.build();
	}

	@GET
	@Path("/name/{planName}")
	@Produces({ "application/json", "application/xml" })
	@Operation(summary = "Get plan identified by the given planName",
			description = "Returns a list of plans which match exactly the given planName", tags = { "search" },
			responses = {
					@ApiResponse(responseCode = "200", description = "OK",
							content = { @Content(mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = PlanInfo.class))) }),
					@ApiResponse(responseCode = "404", description = "Invalid planName, plan not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Response getByName(@Context Request request,
			@PathParam("planName") @Parameter(description = "name of the plan to be returned",
					example = "bplan_123, fplan-123, rplan20200803") String planName)
			throws Exception {
		List<XPlan> plans = planHandler.findPlansByName(planName);
		List<PlanInfo> planInfos = plans.stream().map(xPlan -> {
			List<Bereich> bereiche = planHandler.findBereiche(xPlan.getId());
			return new PlanInfoBuilder(xPlan, bereiche, managerApiConfiguration).selfMediaType(APPLICATION_JSON)
				.alternateMediaType(Arrays.asList(APPLICATION_XML, APPLICATION_ZIP))
				.build();
		}).collect(Collectors.toList());
		return Response.ok().entity(planInfos).build();
	}

	private Response callImport(Request request, String xFilename, Boolean skipSemantisch, Boolean skipFlaechenschluss,
			Boolean skipGeltungsbereich, Boolean skipLaufrichtung, List<String> profiles, String internalId,
			String planStatus, XPlanArchive xPlanArchive) throws Exception {
		checkInternalId(internalId);
		String validationName = detectOrCreateValidationName(xFilename);
		DefaultValidationConfiguration validationConfig = managerApiConfiguration.getDefaultValidationConfiguration();
		ValidationSettings validationSettings = createValidationSettings(validationName, WITH_GEOMETRISCH_VALIDATION,
				overwriteByRequest(skipSemantisch, validationConfig.isSkipSemantisch()),
				overwriteByRequest(skipFlaechenschluss, validationConfig.isSkipFlaechenschluss()),
				overwriteByRequest(skipGeltungsbereich, validationConfig.isSkipGeltungsbereich()),
				overwriteByRequest(skipLaufrichtung, validationConfig.isSkipLaufrichtung()), profiles);
		List<XPlan> xPlans = planHandler.importPlan(xPlanArchive, xFilename, validationSettings, internalId,
				planStatus);
		MediaType requestedMediaType = requestedMediaType(request);
		if (XPLANBOX_V2_JSON_TYPE.equals(requestedMediaType)) {
			List<PlanInfo> planInfos = createPlanInfo(requestedMediaType, xPlans);
			return Response.created(getSelfLink(planInfos)).entity(planInfos).build();
		}
		if (xPlans.size() > 1) {
			throw new InvalidApiVersion("The imported XPlanArchive contains multiple XPlanGML instances, accept header "
					+ XPLANBOX_V2_JSON + "  must be used to import this plan.");
		}
		PlanInfo planInfo = createPlanInfo(requestedMediaType, xPlans.get(0));
		return Response.created(getSelfLink(planInfo)).entity(planInfo).build();
	}

	private List<PlanInfo> createPlanInfo(MediaType requestedMediaType, List<XPlan> plans) {
		return plans.stream().map(plan -> createPlanInfo(requestedMediaType, plan)).collect(Collectors.toList());
	}

	private PlanInfo createPlanInfo(MediaType requestedMediaType, XPlan planById) {
		List<String> alternateMediaTypes = alternateMediaTypes(requestedMediaType);
		List<Bereich> bereiche = planHandler.findBereiche(planById.getId());
		return new PlanInfoBuilder(planById, bereiche, managerApiConfiguration)
			.selfMediaType(requestedMediaType.toString())
			.alternateMediaType(alternateMediaTypes)
			.build();
	}

	private URI getSelfLink(List<PlanInfo> planInfos) {
		if (planInfos.size() == 1) {
			return getSelfLink(planInfos.get(0));
		}
		if (planInfos.size() > 1) {
			return createRefToMultipleInstances(planInfos);
		}
		return null;
	}

	private URI getSelfLink(PlanInfo planInfo) {
		for (Link link : planInfo.getLinks()) {
			if (SELF.equals(link.getRel()))
				return link.getHref();
		}
		return null;
	}

	private MediaType requestedMediaType(Request request) {
		Variant.VariantListBuilder acceptedMediaTypes = Variant.mediaTypes(MEDIA_TYPES_SEARCH);
		Variant selectVariant = request.selectVariant(acceptedMediaTypes.build());
		if (selectVariant == null)
			return APPLICATION_JSON_TYPE;
		return selectVariant.getMediaType();
	}

	private List<String> alternateMediaTypes(MediaType requestedMediaType) {
		return Arrays.stream(MEDIA_TYPES_SEARCH)
			.filter(mediaType -> !mediaType.equals(requestedMediaType))
			.map(mediaType -> mediaType.toString())
			.collect(Collectors.toList());
	}

	private boolean overwriteByRequest(Boolean requested, boolean configured) {
		if (requested != null)
			return requested;
		return configured;
	}

	private URI createRefToMultipleInstances(List<PlanInfo> planInfos) {
		URI apiUrl = managerApiConfiguration.getApiUrl();
		URIBuilder uriBuilder = new URIBuilder(apiUrl);

		List<String> pathSegments = new ArrayList<>();
		if (apiUrl.getPath() != null && !apiUrl.getPath().isEmpty())
			pathSegments.addAll(Arrays.asList(apiUrl.getPath().split("/")));
		pathSegments.addAll(Arrays.asList(JerseyConfig.APP_PATH.split("/")));
		pathSegments.add("plans");
		uriBuilder.setPathSegments(pathSegments.stream()
			.filter(pathSegment -> pathSegment != null && !pathSegment.isEmpty())
			.collect(Collectors.toList()));
		planInfos.stream().forEach(planInfo -> {
			String id = Integer.toString(planInfo.getId());
			uriBuilder.addParameter("planId", id);
		});
		try {
			return uriBuilder.build();
		}
		catch (URISyntaxException e) {
			LOG.warn("Could not create self reference: " + e.getMessage(), e);
		}
		return null;
	}

	private void checkInternalId(String internalId) throws UnsupportedParameterValue {
		if (internalId == null)
			return;
		Pattern pattern = Pattern.compile(INTERNALID_PATTERN);
		Matcher matcher = pattern.matcher(internalId);
		if (!matcher.matches()) {
			throw new UnsupportedParameterValue("internalId", internalId);
		}

	}

}
