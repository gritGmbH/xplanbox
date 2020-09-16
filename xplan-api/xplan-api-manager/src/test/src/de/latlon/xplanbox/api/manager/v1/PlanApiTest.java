package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import org.apache.http.HttpHeaders;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Ignore;
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
import static javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlanApiTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable( TestProperties.LOG_TRAFFIC );
        final ResourceConfig resourceConfig = new ResourceConfig( PlanApi.class );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ApplicationContext.class,
                TestContext.class );
        resourceConfig.property("contextConfig", context );
        return resourceConfig;
    }

    @Test
    public void verifyThat_PostPlan_ReturnsCorrectStatusCodeForValidMediaType() throws IOException, URISyntaxException {
        final byte[] data = Files.readAllBytes( Paths.get(
                PlanApiTest.class.getResource( "/bplan_valid_41.zip" ).toURI() ) );
        final Response response = target( "/plan" ).request().
                accept( APPLICATION_JSON ).post( Entity.entity( data, APPLICATION_OCTET_STREAM ) );
        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    @Test
    public void verifyThat_PostPlan_ReturnsCorrectStatusCodeForInvalidMediaType() throws IOException, URISyntaxException {
        final String data = new String( Files.readAllBytes( Paths.get(
                PlanApiTest.class.getResource( "/xplan.gml" ).toURI() ) ) );
        final Response response = target( "/plan" ).request().
                accept( APPLICATION_JSON ).post( Entity.entity( data, TEXT_XML ) );
        assertThat( response.getStatus(), is( Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode() ) );
    }

    @Test
    public void verifyThat_DeletePlan_ReturnsCorrectStatus() {
        final Response response = target( "/plan/123" ).request().
                accept( APPLICATION_JSON ).delete();
        //TODO
    }

    @Test
    public void verifyThat_GetPlanById_ReturnCorrectStatus() {
        final Response response = target( "/plan/123" ).request().
                accept( APPLICATION_JSON ).get();
        //TODO
    }

    @Test
    public void verifyThat_GetPlanByName_ReturnsCorrectStatus() {
        final Response response = target( "/plan/name/bplan_41" ).request().
                accept( APPLICATION_JSON ).get();
        //TODO
    }
}