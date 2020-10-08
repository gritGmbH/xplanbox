package de.latlon.xplanbox.api.validator;

import de.latlon.xplanbox.api.commons.ObjectMapperContextResolver;
import de.latlon.xplanbox.api.commons.openapi.OpenApiFilter;
import de.latlon.xplanbox.api.validator.config.ValidatorApiConfiguration;
import de.latlon.xplanbox.api.validator.v1.DefaultApi;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import java.util.Collections;
import java.util.List;
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
public class ApplicationPathConfig extends ResourceConfig {

    private static final Logger LOG = getLogger( ApplicationPathConfig.class );

    private static final String APP_PATH = "xvalidator/api/v1";

    public ApplicationPathConfig(
                            @Context
                                                    ServletContext servletContext,
                            ValidatorApiConfiguration validatorApiConfiguration ) {
        super();
        register( new ObjectMapperContextResolver() );
        packages( "de.latlon.xplanbox.api.validator.config" );
        packages( "de.latlon.xplanbox.api.validator.handler" );
        packages( "de.latlon.xplanbox.api.validator.v1" );
        packages( "de.latlon.xplanbox.api.commons.exception" );
        OpenAPI openApi = new OpenAPI();
        openApi.setInfo( new Info().title( "XPlanValidatorAPI" ).version( "0.1.1" ).description(
                                "XPlanValidator REST API" ).termsOfService(
                                "http://xplanbox.lat-lon.de/terms/" ).contact(
                                new Contact().email( "info@lat-lon.de" ) ).license(
                                new License().name( "Apache 2.0" ).url(
                                                        "http://www.apache.org/licenses/LICENSE-2.0.html" ) ) );
        openApi.servers( servers( servletContext, validatorApiConfiguration ) );
        Tag tag = new Tag().name( "validate" ).description( "Validate XPlanGML documents" ).externalDocs(
                                new ExternalDocumentation().description( "xPlanBox" ).url(
                                                        "http://xplanbox.lat-lon.de" ) );
        openApi.tags( Collections.singletonList( tag ) );

        DefaultApi openApiResource = new DefaultApi();
        SwaggerConfiguration oasConfig = new SwaggerConfiguration().openAPI( openApi ).filterClass(
                                OpenApiFilter.class.getCanonicalName() ).prettyPrint( true ).resourcePackages(
                                Stream.of( "de.latlon.xplanbox.api.validator.v1" ).collect( Collectors.toSet() ) );

        openApiResource.setOpenApiConfiguration( oasConfig );
        register( openApiResource );
        LOG.info( "XPlanApiValidator successfully initialized" );
    }

    private List<Server> servers( ServletContext servletContext, ValidatorApiConfiguration validatorApiConfiguration ) {
        String serverUrl = getServerUrl( servletContext, validatorApiConfiguration );
        Server server = new Server().url( serverUrl );
        return Collections.singletonList( server );
    }

    private String getServerUrl( ServletContext servletContext, ValidatorApiConfiguration validatorApiConfiguration ) {
        StringBuilder serverUrl = new StringBuilder();
        if ( validatorApiConfiguration != null && validatorApiConfiguration.getApiEndpoint() != null ) {
            String apiEndpoint = validatorApiConfiguration.getApiEndpoint().toString();
            serverUrl.append( apiEndpoint );
        } else {
            serverUrl.append( servletContext.getContextPath() );
        }
        if ( !serverUrl.toString().endsWith( "/" ) )
            serverUrl.append( "/" );
        serverUrl.append( APP_PATH );
        return serverUrl.toString();
    }

}