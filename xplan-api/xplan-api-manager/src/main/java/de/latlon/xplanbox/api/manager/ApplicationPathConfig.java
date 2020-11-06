package de.latlon.xplanbox.api.manager;

import de.latlon.xplanbox.api.commons.ObjectMapperContextResolver;
import de.latlon.xplanbox.api.commons.openapi.OpenApiFilter;
import de.latlon.xplanbox.api.manager.config.ManagerApiConfiguration;
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

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
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
public class ApplicationPathConfig extends ResourceConfig {

    private static final Logger LOG = getLogger( ApplicationPathConfig.class );

    public static final String APP_PATH = "xmanager/api/v1";

    public ApplicationPathConfig(
                            @Context
                                                    ServletContext servletContext,
                            @Context
                                                    ManagerApiConfiguration managerApiConfiguration ) {
        super();
        register( new ObjectMapperContextResolver() );
        packages( "de.latlon.xplanbox.api.manager.v1" );
        packages( "de.latlon.xplanbox.api.manager.exception" );
        packages( "de.latlon.xplanbox.api.commons.exception" );
        OpenAPI openApi = new OpenAPI();
        openApi.setInfo( new Info().title( "XPlanManagerAPI" ).version( "0.1.2" ).description(
                                "XPlanManager REST API" ).termsOfService( "http://xplanbox.lat-lon.de/terms/" ).contact(
                                new Contact().email( "info@lat-lon.de" ) ).license(
                                new License().name( "Apache 2.0" ).url(
                                                        "http://www.apache.org/licenses/LICENSE-2.0.html" ) ) );
        openApi.servers( servers( servletContext, managerApiConfiguration ) );
        List<Tag> tags = createTags();
        openApi.tags( tags );

        DefaultApi openApiResource = new DefaultApi();
        SwaggerConfiguration oasConfig = new SwaggerConfiguration().openAPI( openApi ).filterClass(
                                OpenApiFilter.class.getCanonicalName() ).prettyPrint( true ).resourcePackages(
                                Stream.of( "de.latlon.xplanbox.api.manager.v1" ).collect( Collectors.toSet() ) );

        openApiResource.setOpenApiConfiguration( oasConfig );
        register( openApiResource );
        LOG.info( "XPlanApiValidator successfully initialized" );
    }

    private List<Tag> createTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add( new Tag().name( "manage" ).description( "Manage XPlanGML documents" ).externalDocs(
                                new ExternalDocumentation().description( "xPlanBox" ).url(
                                                        "http://xplanbox.lat-lon.de" ) ) );
        tags.add( new Tag().name( "search" ).description( "Search for XPlanGML documents" ).externalDocs(
                                new ExternalDocumentation().description( "xPlanBox" ).url(
                                                        "http://xplanbox.lat-lon.de" ) ) );
        return tags;
    }

    private List<Server> servers( ServletContext servletContext, ManagerApiConfiguration managerApiConfiguration ) {
        String serverUrl = getServerUrl( servletContext, managerApiConfiguration );
        Server server = new Server().url( serverUrl );
        return Collections.singletonList( server );
    }

    private String getServerUrl( ServletContext servletContext, ManagerApiConfiguration managerApiConfiguration ) {
        StringBuilder serverUrl = new StringBuilder();
        if ( managerApiConfiguration != null && managerApiConfiguration.getApiUrl() != null ) {
            String apiEndpoint = managerApiConfiguration.getApiUrl().toString();
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
