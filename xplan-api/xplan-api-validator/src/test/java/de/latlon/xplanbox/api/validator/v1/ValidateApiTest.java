package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplanbox.api.validator.config.ApplicationContext;
import de.latlon.xplanbox.api.validator.config.TestContext;
import de.latlon.xplanbox.api.validator.handler.ValidationHandlerTest;
import org.apache.http.HttpHeaders;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Valid;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ValidateApiTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable( TestProperties.LOG_TRAFFIC );
        final ResourceConfig resourceConfig = new ResourceConfig( ValidateApi.class );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ApplicationContext.class );
        resourceConfig.property("contextConfig", context );
        return resourceConfig;
    }

    @Test
    public void verifyThat_Response_ContainsCorrectStatusCodeAndMediaType() throws IOException, URISyntaxException {
        final String data = new String( Files.readAllBytes( Paths.get(
                ValidateApiTest.class.getResource( "/xplan.gml" ).toURI() ) ) );
        final Response response = target( "/validate" ).request().
                accept( APPLICATION_JSON ).post( Entity.entity( data, TEXT_XML ) );

        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    @Test
    public void verifyThat_Response_ContainsXmlEncoding() throws URISyntaxException, IOException {
        final String data = new String( Files.readAllBytes( Paths.get(
                ValidateApiTest.class.getResource( "/bplan_valid_41.zip" ).toURI() ) ) );
        final Response response  = target( "/validate" ).request().accept( APPLICATION_XML ).
                post( Entity.entity( data, APPLICATION_OCTET_STREAM ) );

        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_XML ) );
        assertThat( response.getEntity().toString(), containsString( "valid" ) );
    }
}