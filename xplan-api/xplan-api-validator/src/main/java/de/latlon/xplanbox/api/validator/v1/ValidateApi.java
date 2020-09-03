package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.ValidationReportBuilder;
import de.latlon.xplanbox.api.validator.v1.handler.ValidationHandler;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import static de.latlon.xplanbox.api.validator.v1.XPlanBoxMediaType.APPLICATION_PDF;
import static de.latlon.xplanbox.api.validator.v1.XPlanBoxMediaType.APPLICATION_PDF_TYPE;
import static de.latlon.xplanbox.api.validator.v1.XPlanBoxMediaType.APPLICATION_ZIP;
import static de.latlon.xplanbox.api.validator.v1.XPlanBoxMediaType.APPLICATION_ZIP_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_XML_TYPE;
import static javax.ws.rs.core.MediaType.TEXT_XML_TYPE;

@Path("/validate")
@Api(description = "the validate API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-26T09:59:16.298+02:00[Europe/Berlin]")
public class ValidateApi {

    @Autowired
    private ValidationHandler validationHandler;

    @Context
    Request request;

    @POST
    @Consumes({ "application/octet-stream", "text/xml", "application/gml+xml" })
    @Produces({ "application/json", "application/xml", "text/xml", "application/pdf", "application/zip" })
    @ApiOperation(value = "Validate XPlanGML or XPlanArchive", notes = "Validates XPlanGML or XPlanArchive file", response = ValidationReport.class, tags = {
                            "validate" })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "ValidationReport", response = ValidationReport.class),
                            @ApiResponse(code = 400, message = "Invalid input", response = Void.class),
                            @ApiResponse(code = 405, message = "Invalid method", response = Void.class),
                            @ApiResponse(code = 406, message = "Invalid content only XPlanGML or ZIP with XPlanGML is accepted", response = Void.class),
                            @ApiResponse(code = 415, message = "Unsupported Media Type, only XML or ZIP is accepted", response = Void.class),
                            @ApiResponse(code = 200, message = "Unexpected error", response = Void.class) })
    public Response validate( @Valid File body,
                              @HeaderParam("X-Filename")
                              @ApiParam("Name of the file to be uploaded")
                                                      String xFilename,
                              @QueryParam("name")
                              @ApiParam("Name of the validation")
                                                      String name,
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
                                                      Boolean skipGeltungsbereich )
                            throws IOException, ValidatorException, URISyntaxException {
        MediaType mediaType = detectRequestedMediaType();

        String validationName = detectOrCreateValidationName( xFilename, name );
        ValidationSettings settings = createValidationSettings( validationName, skipGeometrisch, skipSemantisch,
                                                                skipFlaechenschluss, skipGeltungsbereich );
        ValidatorReport validatorReport = validationHandler.validate( body, validationName, settings );
        if ( APPLICATION_ZIP_TYPE.equals( mediaType ) ) {
            java.nio.file.Path report = validationHandler.zipReports( validatorReport );
            return Response.ok( FileUtils.readFileToByteArray( report.toFile() ) ).type( APPLICATION_ZIP ).header(
                                    "Content-Disposition",
                                    "attachment; filename=\"" + validationName + ".zip\"" ).build();
        }
        if ( APPLICATION_PDF_TYPE.equals( mediaType ) ) {
            File report = validationHandler.writePdfReport( validatorReport );
            return Response.ok( FileUtils.readFileToByteArray( report ) ).type( APPLICATION_PDF ).header(
                                    "Content-Disposition",
                                    "attachment; filename=\"" + validationName + ".pdf\"" ).build();
        }
        URI wmsUrl = validationHandler.addToWms( body );
        ValidationReport validationReport = new ValidationReportBuilder().validatorReport( validatorReport ).filename(
                                xFilename ).wmsUrl( wmsUrl ).build();
        return Response.ok( validationReport ).build();
    }

    private MediaType detectRequestedMediaType() {
        Variant.VariantListBuilder acceptedMediaTypes = Variant.mediaTypes( APPLICATION_JSON_TYPE, APPLICATION_XML_TYPE,
                                                                            TEXT_XML_TYPE, APPLICATION_PDF_TYPE,
                                                                            APPLICATION_ZIP_TYPE );
        Variant selectVariant = request.selectVariant( acceptedMediaTypes.build() );
        if ( selectVariant == null )
            return APPLICATION_JSON_TYPE;
        return selectVariant.getMediaType();
    }

}