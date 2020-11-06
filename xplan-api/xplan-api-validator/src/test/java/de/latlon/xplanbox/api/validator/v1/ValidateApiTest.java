package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplanbox.api.validator.config.ApplicationContext;
import de.latlon.xplanbox.api.validator.config.TestContext;
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

import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_X_ZIP;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_X_ZIP_COMPRESSED;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ValidateApiTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable( TestProperties.LOG_TRAFFIC );
        final ResourceConfig resourceConfig = new ResourceConfig( ValidateApi.class );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ApplicationContext.class,
                TestContext.class );
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
    public void verifyThat_validationOctetStream_Response_ContainsXmlEncoding() throws URISyntaxException, IOException {
        final byte[] data = Files.readAllBytes(
                                Paths.get( ValidateApiTest.class.getResource( "/bplan_valid_41.zip" ).toURI() ) );
        final Response response  = target( "/validate" ).request().accept( APPLICATION_XML ).
                post( Entity.entity( data, APPLICATION_OCTET_STREAM ) );

        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_XML ) );
        assertThat( response.readEntity( String.class ), containsString( "valid" ) );
    }

    @Test
    public void verifyThat_validationZip_Response_ContainsXmlEncoding() throws URISyntaxException, IOException {
        final byte[] data = Files.readAllBytes(
                                Paths.get( ValidateApiTest.class.getResource( "/bplan_valid_41.zip" ).toURI() ) );
        final Response response  = target( "/validate" ).request().accept( APPLICATION_XML ).
                                post( Entity.entity( data, APPLICATION_ZIP ) );

        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_XML ) );
        assertThat( response.readEntity( String.class ), containsString( "valid" ) );
    }

    @Test
    public void verifyThat_validationXZip_Response_ContainsXmlEncoding() throws URISyntaxException, IOException {
        final byte[] data = Files.readAllBytes(
                                Paths.get( ValidateApiTest.class.getResource( "/bplan_valid_41.zip" ).toURI() ) );
        final Response response  = target( "/validate" ).request().accept( APPLICATION_XML ).
                                post( Entity.entity( data, APPLICATION_X_ZIP ) );

        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_XML ) );
        assertThat( response.readEntity( String.class ), containsString( "valid" ) );
    }

    @Test
    public void verifyThat_validationXZipCompressed_Response_ContainsXmlEncoding() throws URISyntaxException, IOException {
        final byte[] data = Files.readAllBytes(
                                Paths.get( ValidateApiTest.class.getResource( "/bplan_valid_41.zip" ).toURI() ) );
        final Response response  = target( "/validate" ).request().accept( APPLICATION_XML ).
                                post( Entity.entity( data, APPLICATION_X_ZIP_COMPRESSED ) );

        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_XML ) );
        assertThat( response.readEntity( String.class ), containsString( "valid" ) );
    }
}