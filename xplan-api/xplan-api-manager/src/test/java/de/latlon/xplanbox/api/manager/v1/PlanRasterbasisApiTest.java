/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.commons.exception.XPlanApiExceptionMapper;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import org.apache.http.HttpHeaders;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URISyntaxException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PlanRasterbasisApiTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable( TestProperties.LOG_TRAFFIC );
        final ResourceConfig resourceConfig = new ResourceConfig( PlanRasterbasisApi.class );
        resourceConfig.register( XPlanApiExceptionMapper.class );
        resourceConfig.packages( "org.glassfish.jersey.examples.multipart" );
        resourceConfig.register( MultiPartFeature.class );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ApplicationContext.class,
                                                                                             TestContext.class );
        resourceConfig.property( "contextConfig", context );
        return resourceConfig;
    }

    @Override
    protected void configureClient( ClientConfig config ) {
        config.register( MultiPartFeature.class );
    }

    @Test
    public void verifyThat_getRasterbasise_returnsCorrectStatusCodeForValidMediaType() {
        Response response = target( "/plan/1/rasterbasis" ).request( APPLICATION_JSON ).get();

        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    @Test
    public void verifyThat_addRasterbasis_returnsCorrectStatusCodeForValidMediaType()
                    throws URISyntaxException {
        FileDataBodyPart rasterbasismodel = createFileDataBodyPart( "rasterbasismodel", "rasterbasismodel.json",
                                                                    APPLICATION_JSON_TYPE );
        FileDataBodyPart rasterFilePart = createFileDataBodyPart( "rasterdatei", "datei.pdf", null );
        FileDataBodyPart geoRefFilePart = createFileDataBodyPart( "georeferenzdatei", "georeferenz.txt",
                                                                  TEXT_PLAIN_TYPE );
        FormDataMultiPart multipart = (FormDataMultiPart) new FormDataMultiPart()
                        .bodyPart( rasterbasismodel ).bodyPart( rasterFilePart ).bodyPart( geoRefFilePart );

        Response response = target( "/plan/1/rasterbasis" ).request()
                                                           .post( Entity.entity( multipart,
                                                                                 multipart.getMediaType() ) );
        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );

    }

    @Test
    public void verifyThat_getRasterbasisById_returnsCorrectStatusCodeForValidMediaType() {
        Response response = target( "/plan/1/rasterbasis/1" ).request( APPLICATION_JSON ).get();

        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    @Test
    public void verifyThat_replaceRasterbasisById_returnsCorrectStatusCodeForValidMediaType()
                    throws URISyntaxException {
        FileDataBodyPart rasterbasismodel = createFileDataBodyPart( "rasterbasismodel", "rasterbasismodel.json",
                                                                    APPLICATION_JSON_TYPE );
        FileDataBodyPart rasterFilePart = createFileDataBodyPart( "rasterdatei", "datei.pdf", null );
        FileDataBodyPart geoRefFilePart = createFileDataBodyPart( "georeferenzdatei", "georeferenz.txt",
                                                                  TEXT_PLAIN_TYPE );
        FormDataMultiPart multipart = (FormDataMultiPart) new FormDataMultiPart()
                        .bodyPart( rasterbasismodel ).bodyPart( rasterFilePart ).bodyPart( geoRefFilePart );

        Response response = target( "/plan/1/rasterbasis/1" ).request()
                                                             .put( Entity.entity( multipart,
                                                                                  multipart.getMediaType() ) );
        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    @Test
    public void verifyThat_deleteRasterbasisById_returnsCorrectStatusCodeForValidMediaType() {
        Response response = target( "/plan/1/rasterbasis/1" ).request( APPLICATION_JSON ).delete();

        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    private FileDataBodyPart createFileDataBodyPart( String name, String resource, MediaType mediaType )
                    throws URISyntaxException {
        File datei = new File( getClass().getResource( resource ).toURI() );
        return new FileDataBodyPart( name, datei, mediaType );
    }

}
