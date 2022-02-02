/*-
 * #%L
 * xplan-api-validator - Modul zur Gruppierung der REST-API
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
package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.ValidationReportBuilder;
import de.latlon.xplanbox.api.commons.exception.InvalidXPlanGmlOrArchive;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import de.latlon.xplanbox.api.validator.handler.ValidationHandler;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static de.latlon.xplanbox.api.commons.ValidatorConverter.createValidationSettings;
import static de.latlon.xplanbox.api.commons.ValidatorConverter.detectOrCreateValidationName;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_PDF;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_PDF_TYPE;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML_TYPE;

@Path("/validate")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-26T09:59:16.298+02:00[Europe/Berlin]")
public class ValidateApi {

	@Autowired
	private ValidationHandler validationHandler;

	@POST
	@Consumes({ "text/xml", "application/gml+xml" })
	@Produces({ "application/json", "application/xml", "text/xml", "application/pdf", "application/zip" })
	@Operation(summary = "Validate XPlanGML or XPlanArchive", description = "Validates XPlanGML or XPlanArchive file",
			tags = { "validate" },
			responses = {
					@ApiResponse(responseCode = "200", description = "ValidationReport", content = {
							@Content(mediaType = APPLICATION_JSON,
									schema = @Schema(implementation = ValidationReport.class)),
							@Content(mediaType = APPLICATION_XML,
									schema = @Schema(implementation = ValidationReport.class)),
							@Content(mediaType = TEXT_XML, schema = @Schema(implementation = ValidationReport.class)),
							@Content(mediaType = APPLICATION_PDF, schema = @Schema(type = "string", format = "binary")),
							@Content(mediaType = APPLICATION_ZIP,
									schema = @Schema(type = "string", format = "binary",
											description = "XPlanGML or XPlanArchive (application/zip) file to upload",
											implementation = Object.class)) }),
					@ApiResponse(responseCode = "400", description = "Invalid input"),
					@ApiResponse(responseCode = "406",
							description = "Invalid content only XPlanGML or ZIP with XPlanGML is accepted") },
			requestBody = @RequestBody(content = {
					@Content(mediaType = "application/octet-stream",
							schema = @Schema(type = "string", format = "binary",
									description = "XPlanGML or XPlanArchive (application/zip) file to upload")),
					@Content(mediaType = "application/zip",
							schema = @Schema(type = "string", format = "binary",
									description = "XPlanGML or XPlanArchive (application/zip) file to upload")),
					@Content(mediaType = "application/x-zip",
							schema = @Schema(type = "string", format = "binary",
									description = "XPlanGML or XPlanArchive (application/zip) file to upload")),
					@Content(mediaType = "application/x-zip-compressed",
							schema = @Schema(type = "string", format = "binary",
									description = "XPlanGML or XPlanArchive (application/zip) file to upload")),
					@Content(mediaType = "text/xml",
							schema = @Schema(type = "string", format = "binary", description = "XPlanGML to upload")),
					@Content(mediaType = "application/gml+xml", schema = @Schema(type = "string", format = "binary",
							description = "XPlanGML to upload")) }))
	public Response validate(@Context Request request, @Valid File body,
			@HeaderParam("X-Filename") @Parameter(description = "Name of the file to be uploaded",
					example = "File names such as xplan.gml, xplan.xml, xplan.zip",
					schema = @Schema(pattern = "^[A-Za-z0-9.()_-]*$")) String xFilename,
			@QueryParam("name") @Parameter(description = "Name of the validation",
					example = "xplan-1, Prüfbericht_Torstrasse_10, report#4223") String name,
			@QueryParam("skipSemantisch") @DefaultValue("false") @Parameter(
					description = "skip semantische Validierung") Boolean skipSemantisch,
			@QueryParam("skipGeometrisch") @DefaultValue("false") @Parameter(
					description = "skip geometrische Validierung") Boolean skipGeometrisch,
			@QueryParam("skipFlaechenschluss") @DefaultValue("false") @Parameter(
					description = "skip Flaechenschluss Ueberpruefung") Boolean skipFlaechenschluss,
			@QueryParam("skipGeltungsbereich") @DefaultValue("false") @Parameter(
					description = "skip Geltungsbereich Ueberpruefung") Boolean skipGeltungsbereich)
			throws IOException, ValidatorException, URISyntaxException, InvalidXPlanGmlOrArchive {
		String validationName = detectOrCreateValidationName(xFilename, name);
		XPlanArchive archive = validationHandler.createArchiveFromGml(body, validationName);

		return validate(request, xFilename, validationName, skipSemantisch, skipGeometrisch, skipFlaechenschluss,
				skipGeltungsbereich, archive);
	}

	@POST
	@Consumes({ "application/octet-stream", "application/zip", "application/x-zip", "application/x-zip-compressed" })
	@Produces({ "application/json", "application/xml", "text/xml", "application/pdf", "application/zip" })
	@Hidden
	public Response validateZip(@Context Request request, @Valid File body, @HeaderParam("X-Filename") String xFilename,
			@QueryParam("name") String name,
			@QueryParam("skipSemantisch") @DefaultValue("false") Boolean skipSemantisch,
			@QueryParam("skipGeometrisch") @DefaultValue("false") Boolean skipGeometrisch,
			@QueryParam("skipFlaechenschluss") @DefaultValue("false") Boolean skipFlaechenschluss,
			@QueryParam("skipGeltungsbereich") @DefaultValue("false") Boolean skipGeltungsbereich)
			throws IOException, ValidatorException, URISyntaxException, InvalidXPlanGmlOrArchive {
		String validationName = detectOrCreateValidationName(xFilename, name);
		XPlanArchive archive = validationHandler.createArchiveFromZip(body, validationName);

		return validate(request, xFilename, validationName, skipSemantisch, skipGeometrisch, skipFlaechenschluss,
				skipGeltungsbereich, archive);
	}

	private Response validate(Request request, String xFileName, String validationName, Boolean skipSemantisch,
			Boolean skipGeometrisch, Boolean skipFlaechenschluss, Boolean skipGeltungsbereich, XPlanArchive archive)
			throws ValidatorException, IOException {
		MediaType mediaType = detectRequestedMediaType(request);

		ValidationSettings settings = createValidationSettings(validationName, skipGeometrisch, skipSemantisch,
				skipFlaechenschluss, skipGeltungsbereich);
		ValidatorReport validatorReport = validationHandler.validate(archive, settings);
		if (APPLICATION_ZIP_TYPE.equals(mediaType)) {
			java.nio.file.Path report = validationHandler.zipReports(validatorReport);
			return Response.ok(FileUtils.readFileToByteArray(report.toFile())).type(APPLICATION_ZIP)
					.header("Content-Disposition", "attachment; filename=\"" + validationName + ".zip\"").build();
		}
		if (APPLICATION_PDF_TYPE.equals(mediaType)) {
			File report = validationHandler.writePdfReport(validatorReport);
			return Response.ok(FileUtils.readFileToByteArray(report)).type(APPLICATION_PDF)
					.header("Content-Disposition", "attachment; filename=\"" + validationName + ".pdf\"").build();
		}
		URI wmsUrl = validationHandler.addToWms(archive);
		ValidationReport validationReport = new ValidationReportBuilder().validatorReport(validatorReport)
				.filename(xFileName).wmsUrl(wmsUrl).build();
		return Response.ok(validationReport).build();
	}

	private MediaType detectRequestedMediaType(Request request) {
		Variant.VariantListBuilder acceptedMediaTypes = Variant.mediaTypes(APPLICATION_JSON_TYPE, APPLICATION_XML_TYPE,
				TEXT_XML_TYPE, APPLICATION_PDF_TYPE, APPLICATION_ZIP_TYPE);
		Variant selectVariant = request.selectVariant(acceptedMediaTypes.build());
		if (selectVariant == null)
			return APPLICATION_JSON_TYPE;
		return selectVariant.getMediaType();
	}

}
