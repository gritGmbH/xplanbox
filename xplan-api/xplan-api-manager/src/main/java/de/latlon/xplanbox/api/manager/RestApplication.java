package de.latlon.xplanbox.api.manager;

import de.latlon.xplanbox.api.commons.ObjectMapperContextResolver;
import de.latlon.xplanbox.api.manager.v1.DefaultApi;
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

import javax.ws.rs.ApplicationPath;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Application configuration for XPlanManager REST API.
 * Example mapping for proxy mapping:
 * http://xplanbox.lat-lon.de/xmanager/api/vi/ -> http://host:8080/xplan-api-validator/xmanager/api/v1/
 * Public address: http://xplanbox.lat-lon.de/xmanager/
 * Internal address: http://host:8080/xplan-api-validator/xmanager/
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@ApplicationPath("/xmanager/api/v1")
public class RestApplication extends ResourceConfig {

    private static final Logger LOG = getLogger( RestApplication.class );

    public RestApplication() {
        super();
        register( new ObjectMapperContextResolver() );
        packages( "de.latlon.xplanbox.api.manager.v1" );
        packages( "de.latlon.xplanbox.api.commons.exception" );
        OpenAPI openApi = new OpenAPI();
        openApi.setInfo( new Info().title( "XPlanManagerAPI" ).version( "0.1.1" ).description(
                                "XPlanManager REST API" ).termsOfService( "http://xplanbox.lat-lon.de/terms/" ).contact(
                                new Contact().email( "info@lat-lon.de" ) ).license(
                                new License().name( "Apache 2.0" ).url(
                                                        "http://www.apache.org/licenses/LICENSE-2.0.html" ) ) );
        openApi.servers( servers() );
        Tag tag = new Tag().name( "manage" ).description( "Manage XPlanGML documents" ).externalDocs(
                                new ExternalDocumentation().description( "xPlanBox" ).url(
                                                        "http://xplanbox.lat-lon.de" ) );
        openApi.tags( Collections.singletonList( tag ) );

        DefaultApi openApiResource = new DefaultApi();
        SwaggerConfiguration oasConfig = new SwaggerConfiguration().openAPI( openApi ).prettyPrint(
                                true ).resourcePackages(
                                Stream.of( "de.latlon.xplanbox.api.manager.v1" ).collect( Collectors.toSet() ) );

        openApiResource.setOpenApiConfiguration( oasConfig );
        register( openApiResource );
        LOG.info( "XPlanApiValidator successfully initialized" );
    }

    private List<Server> servers() {
        // TODO
        Server server = new Server().url( "http://localhost:8081/xplan-api-manager/xmanager/api/v1" );
        return Collections.singletonList( server );
    }

}
