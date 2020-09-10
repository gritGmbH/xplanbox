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

    @Test @Ignore
    public void verifyThat_Response_ContainsCorrectStatusCodeAndMediaType() throws IOException, URISyntaxException {
        final String data = new String( Files.readAllBytes( Paths.get(
                PlanApiTest.class.getResource( "/xplan.gml" ).toURI() ) ) );
        final Response response = target( "/plan" ).request().
                accept( APPLICATION_JSON ).post( Entity.entity( data, TEXT_XML ) );
        //TODO check for valid status code
        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    @Test
    public void callImport() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void getByName() {
    }

    @Test
    public void testCallImport() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testGetById() {
    }

    @Test
    public void testGetByName() {
    }
}