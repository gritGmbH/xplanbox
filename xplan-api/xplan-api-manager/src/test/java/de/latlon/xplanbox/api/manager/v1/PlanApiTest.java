package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.commons.exception.XPlanApiExceptionMapper;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import de.latlon.xplanbox.api.manager.v1.model.PlanStatusEnum;
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

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.jayway.jsonpath.matchers.JsonPathMatchers.isJson;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_X_ZIP;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_X_ZIP_COMPRESSED;
import static de.latlon.xplanbox.api.commons.XPlanBoxMediaType.APPLICATION_ZIP;
import static de.latlon.xplanbox.api.manager.v1.model.PlanStatusEnum.FESTGESTELLT;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;
import static org.xmlmatchers.transform.XmlConverters.the;

public class PlanApiTest extends JerseyTest {


    @Override
    protected Application configure() {
        enable( TestProperties.LOG_TRAFFIC );
        final ResourceConfig resourceConfig = new ResourceConfig( PlanApi.class );
        resourceConfig.register( XPlanApiExceptionMapper.class );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ApplicationContext.class,
                TestContext.class );
        resourceConfig.property("contextConfig", context );
        return resourceConfig;
    }

    @Test
    public void verifyThat_PostPlanOctetStream_ReturnsCorrectStatusCodeForValidMediaType() throws IOException, URISyntaxException {
        final byte[] data = Files.readAllBytes( Paths.get(
                                PlanApiTest.class.getResource( "/bplan_valid_41.zip" ).toURI() ) );
        final Response response = target( "/plan" ).request().
                                accept( APPLICATION_JSON ).post( Entity.entity( data, APPLICATION_OCTET_STREAM ) );
        assertThat( response.getStatus(), is( Response.Status.CREATED.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
        assertThat( response.getHeaderString( HttpHeaders.LOCATION ), is( notNullValue() ) );
    }

    @Test
    public void verifyThat_PostPlanZip_ReturnsCorrectStatusCodeForValidMediaType() throws IOException, URISyntaxException {
        final byte[] data = Files.readAllBytes( Paths.get(
                                PlanApiTest.class.getResource( "/bplan_valid_41.zip" ).toURI() ) );
        final Response response = target( "/plan" ).request().
                                accept( APPLICATION_JSON ).post( Entity.entity( data, APPLICATION_ZIP ) );
        assertThat( response.getStatus(), is( Response.Status.CREATED.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
        assertThat( response.getHeaderString( HttpHeaders.LOCATION ), is( notNullValue() ) );
    }

    @Test
    public void verifyThat_PostPlanXZip_ReturnsCorrectStatusCodeForValidMediaType() throws IOException, URISyntaxException {
        final byte[] data = Files.readAllBytes( Paths.get(
                                PlanApiTest.class.getResource( "/bplan_valid_41.zip" ).toURI() ) );
        final Response response = target( "/plan" ).request().
                                accept( APPLICATION_JSON ).post( Entity.entity( data, APPLICATION_X_ZIP ) );
        assertThat( response.getStatus(), is( Response.Status.CREATED.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
        assertThat( response.getHeaderString( HttpHeaders.LOCATION ), is( notNullValue() ) );
    }

    @Test
    public void verifyThat_PostPlanXZipCompressed_ReturnsCorrectStatusCodeForValidMediaType() throws IOException, URISyntaxException {
        final byte[] data = Files.readAllBytes( Paths.get(
                                PlanApiTest.class.getResource( "/bplan_valid_41.zip" ).toURI() ) );
        final Response response = target( "/plan" ).request().
                                accept( APPLICATION_JSON ).post( Entity.entity( data, APPLICATION_X_ZIP_COMPRESSED ) );
        assertThat( response.getStatus(), is( Response.Status.CREATED.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
        assertThat( response.getHeaderString( HttpHeaders.LOCATION ), is( notNullValue() ) );
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
        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    @Test
    public void verifyThat_GetPlanById_AsJson_ReturnsCorrectStatusAndContent() {
        final Response response = target( "/plan/123" ).request().
                                accept( APPLICATION_JSON ).get();
        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );

        String responseBody = response.readEntity( String.class );

        assertThat( responseBody, isJson() );
        assertThat( responseBody, hasJsonPath( "$.version", is( XPLAN_41.name() ) ) );
        assertThat( responseBody, hasJsonPath( "$.planStatus", is( FESTGESTELLT.name() ) ) );
    }

    @Test
    public void verifyThat_GetPlanById_AsXml_ReturnsCorrectStatusAndContent() {
        final Response response = target( "/plan/123" ).request().
                                accept( APPLICATION_XML ).get();
        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_XML ) );

        String responseBody = response.readEntity( String.class );
        assertThat( the( responseBody ), hasXPath( "/planInfo/version", is( XPLAN_41.name() ) ) );
        assertThat( the( responseBody ), hasXPath( "/planInfo/planStatus", is( FESTGESTELLT.name() ) ) );
    }

    @Test
    public void verifyThat_GetPlanById_AsZip_ReturnsCorrectStatusAndContent() {
        final Response response = target( "/plan/123" ).request().
                                accept( APPLICATION_ZIP ).get();
        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_ZIP ) );
    }

    @Test
    public void verifyThat_GetPlanById_ReturnsCorrectStatusCodeForWrongId() {
        final Response response = target( "/plan/42" ).request().
                accept( APPLICATION_ZIP ).get();
        assertThat( response.getStatus(), is( Response.Status.NOT_FOUND.getStatusCode() ) );
    }

    @Test
    public void verifyThat_GetPlanByName_ReturnsCorrectStatus() {
        final Response response = target( "/plan/name/bplan_41" ).request().
                accept( APPLICATION_JSON ).get();
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }
}