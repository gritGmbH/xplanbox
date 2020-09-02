package de.latlon.xplanbox.api.validator.v1;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;

import javax.ws.rs.ApplicationPath;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Application configuration for XPlanValidator REST API.
 * Example mapping for proxy mapping:
 * http://xplanbox.lat-lon.de/xvalidator/api/v1/ -> http://host:8080/xplan-api-validator/xvalidator/api/v1/
 * Public address: http://xplanbox.lat-lon.de/xvalidator/
 * Internal address: http://host:8080/xplan-api-validator/xvalidator/
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@ApplicationPath("/xvalidator/api/v1")
public class XPlanApiValidator extends ResourceConfig {

    private static final Logger LOG = getLogger( XPlanApiValidator.class );

    public XPlanApiValidator() {
        super();
        packages( "de.latlon.xplanbox.api.validator.v1" );
        OpenAPI openApi = new OpenAPI();
        openApi.setInfo( new Info().title( "XPlanValidatorAPI" ).version( "0.0.5" ).description(
                                "XPlanValidator REST API" ).termsOfService(
                                "http://xplanbox.lat-lon.de/terms/" ).contact(
                                new Contact().email( "info@lat-lon.de" ) ).license(
                                new License().name( "Apache 2.0" ).url(
                                                        "http://www.apache.org/licenses/LICENSE-2.0.html" ) ) );
        //openApi.servers(  );
        Tag tag = new Tag().name( "validate" ).description( "Validate XPlanGML documents" ).externalDocs(
                                new ExternalDocumentation().description( "xPlanBox" ).url(
                                                        "http://xplanbox.lat-lon.de" ) );
        openApi.tags( Collections.singletonList( tag ) );

        OpenApiResource openApiResource = new OpenApiResource();
        SwaggerConfiguration oasConfig = new SwaggerConfiguration().openAPI( openApi ).prettyPrint(
                                true ).resourcePackages(
                                Stream.of( "de.latlon.xplanbox.api.validator.v1" ).collect(
                                                        Collectors.toSet() ) );

        openApiResource.setOpenApiConfiguration( oasConfig );
        register( openApiResource );
        LOG.info( "XPlanApiValidator successfully initialized" );
    }

}