package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.commons.exception.XPlanApiExceptionMapper;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import org.apache.http.HttpHeaders;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanGueltigkeitApiTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable( TestProperties.LOG_TRAFFIC );
        final ResourceConfig resourceConfig = new ResourceConfig( PlanGueltigkeitApi.class );
        resourceConfig.register( XPlanApiExceptionMapper.class );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ApplicationContext.class,
                                                                                             TestContext.class );
        resourceConfig.property( "contextConfig", context );
        return resourceConfig;
    }

    @Test
    public void verifyThat_getGueltigkeit_returnsCorrectStatusCodeForValidMediaType() {
        Response response = target( "/plan/1/gueltigkeit" ).request( APPLICATION_JSON ).get();

        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    @Test
    public void verifyThat_replaceGueltigkeit_returnsCorrectStatusCodeForValidMediaType()
                    throws URISyntaxException, IOException {
        final byte[] data = Files.readAllBytes( Paths.get( getClass().getResource( "gueltigkeit.json" ).toURI() ) );

        Response response = target( "/plan/1/gueltigkeit" ).request()
                                                           .put( Entity.entity( data, APPLICATION_JSON_TYPE ) );
        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

}
