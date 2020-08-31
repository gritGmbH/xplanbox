package de.latlon.xplanbox.api.manager;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Application configuration for XPlanManager REST API.
 * Example mapping for proxy mapping:
 * http://xplanbox.lat-lon.de/xmanager/api/vi/ -> http://host:8080/xplan-api-validator/xmanager/api/v1/
 * Public address: http://xplanbox.lat-lon.de/xmanager/
 * Internal address: http://host:8080/xplan-api-validator/xmanager/
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@ApplicationPath("/xmanager/api/v1")
public class RestApplication extends Application {

}
