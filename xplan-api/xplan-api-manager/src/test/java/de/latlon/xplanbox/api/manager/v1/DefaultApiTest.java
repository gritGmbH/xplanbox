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

import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
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

public class DefaultApiTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable( TestProperties.LOG_TRAFFIC );
        final ResourceConfig resourceConfig = new ResourceConfig( DefaultApi.class );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ApplicationContext.class,
                TestContext.class );
        resourceConfig.property("contextConfig", context );
        return resourceConfig;
    }

    @Test
    public void verifyThat_Response_ContainsCorrectStatusCodeAndMediaType() {
        final Response response = target( "/" ).request( APPLICATION_JSON ).get();

        assertThat( response.getStatus(), is( Response.Status.OK.getStatusCode() ) );
        assertThat( response.getHeaderString( HttpHeaders.CONTENT_TYPE ), is( APPLICATION_JSON ) );
    }

    @Test
    public void verifyThat_Response_ContainsOpenApiDocument() {
        final String response = target( "/" ).request( APPLICATION_JSON ).get( String.class );

        assertThat( response, containsString( "\"openapi\":\"3.0.1\"" ) );
    }
}