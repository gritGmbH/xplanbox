package de.latlon.xplanbox.api.validator.v1;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@ApplicationPath("/")
public class XPlanApiValidator extends ResourceConfig {

    public XPlanApiValidator() {
        packages( "de.latlon.xplanbox.api.validator.v1" );

        OpenApiResource openApiResource = new OpenApiResource();
        register( openApiResource );
    }

}