package de.latlon.xplan.manager.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Imports the basic {@link ManagerWebSpringConfig} and active directory ldap security configuration. The security
 * configuration can be configured in security.properties.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
@Configuration
@Import(BasicSpringConfig.class)
@ImportResource("classpath:/de/latlon/xplan/manager/web/spring/security-ad.xml")
public class ManagerWebSpringConfigWithAdLdapSecurity {

}