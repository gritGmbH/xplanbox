package de.latlon.xplan.manager.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Imports the basic {@link ManagerWebSpringConfig} and simple security
 * configuration. The security configuration can be configured in security-simple.xml.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 *
 * @version $Revision: $, $Date: $
 */
@Configuration
@Import(BasicSpringConfig.class)
@ImportResource("classpath:/de/latlon/xplan/manager/web/spring/security-simple.xml")
public class ManagerWebSpringConfigWithSimpleSecurity {

}