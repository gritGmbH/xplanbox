package de.latlon.xplanbox.api.manager.openapi;

import io.swagger.v3.core.model.ApiDescription;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Encoding;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ManagerOpenApiFilter extends de.latlon.xplanbox.api.commons.openapi.OpenApiFilter {

    @Override
    public Optional<RequestBody> filterRequestBody( RequestBody requestBody, Operation operation, ApiDescription api,
                                                    Map<String, List<String>> params, Map<String, String> cookies,
                                                    Map<String, List<String>> headers ) {
        Content content = requestBody.getContent();
        if ( content.containsKey( "multipart/form-data" ) ) {
            MediaType mediaType = content.get( "multipart/form-data" );
            if ( mediaType != null && mediaType.getSchema() != null ) {
                Schema schema = mediaType.getSchema();
                Map<String, Schema> properties = schema.getProperties();
                if ( properties.containsKey( "datei" ) ) {
                    mediaType.addEncoding( "datei",
                                           new Encoding().contentType(
                                                           "application/pdf, application/msword, application/odt" ) );
                }
                if ( properties.containsKey( "rasterdatei" ) ) {
                    mediaType.addEncoding( "rasterdatei",
                                           new Encoding().contentType(
                                                           "image/tiff, image/png" ) );
                }
                if ( properties.containsKey( "georeferenzdatei" ) ) {
                    mediaType.addEncoding( "georeferenzdatei",
                                           new Encoding().contentType(
                                                           "text/plain" ) );
                }
                if ( properties.containsKey( "textmodel" ) ) {
                    mediaType.addEncoding( "textmodel",
                                           new Encoding().contentType(
                                                           "application/json" ) );
                }
                if ( properties.containsKey( "dokumentmodel" ) ) {
                    mediaType.addEncoding( "dokumentmodel",
                                           new Encoding().contentType(
                                                           "application/json" ) );
                }
                if ( properties.containsKey( "rasterbasismodel" ) ) {
                    mediaType.addEncoding( "rasterbasismodel",
                                           new Encoding().contentType(
                                                           "application/json" ) );
                }
            }
        }
        return Optional.of( requestBody );
    }

}
