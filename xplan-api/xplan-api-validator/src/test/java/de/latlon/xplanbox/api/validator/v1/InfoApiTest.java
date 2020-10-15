package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplanbox.api.validator.config.ApplicationContext;
import de.latlon.xplanbox.api.validator.config.TestContext;
import org.apache.http.HttpHeaders;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InfoApiTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable( TestProperties.LOG_TRAFFIC );
        final ResourceConfig resourceConfig = new ResourceConfig( InfoApi.class );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ApplicationContext.class,
                TestContext.class );
        resourceConfig.property("contextConfig", context );
        return resourceConfig;
    }

    @Test
    public void verifyThat_Response_ContainsCorrectStatusCodeAndMediaType() {
        final Response response = target( "/info" ).request( APPLICATION_JSON ).get();

        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    @Test
    public void verifyThat_Response_ContainsSupportedXplanGmlVersions() {
        final String response = target( "/info" ).request( APPLICATION_JSON ).get( String.class );

        assertThat( response, containsString( "supportedXPlanGmlVersions" ) );
    }
}