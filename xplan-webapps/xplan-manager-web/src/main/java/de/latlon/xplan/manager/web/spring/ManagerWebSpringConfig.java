package de.latlon.xplan.manager.web.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import de.latlon.xplan.manager.web.server.service.rest.SecurityController;
import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;

/**
 * XPlanManagerWeb Application Configuration.
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
@Configuration
@Import(BasicSpringConfig.class)
public class ManagerWebSpringConfig {

    @Bean
    public SecurityController securityController( AuthorizationManager securityManager ) {
        return new SecurityController( securityManager );
    }

    @Bean
    public AuthorizationManager securityManager() {
        return new AuthorizationManager( false );
    }

}