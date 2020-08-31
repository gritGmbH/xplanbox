package de.latlon.xplanbox.api.validator.v1;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Application configuration for XPlanValidator REST API.
 * Example mapping for proxy mapping:
 * http://xplanbox.lat-lon.de/xvalidator/api/vi/ -> http://host:8080/xplan-api-validator/xvalidator/api/v1/
 * Public address: http://xplanbox.lat-lon.de/xvalidator/
 * Internal address: http://host:8080/xplan-api-validator/xvalidator/
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@ApplicationPath("/xvalidator/api/v1")
public class XPlanApiValidator extends ResourceConfig {

    public XPlanApiValidator() {
        packages( "de.latlon.xplanbox.api.validator.v1" );

        OpenApiResource openApiResource = new OpenApiResource();
        register( openApiResource );
    }

}