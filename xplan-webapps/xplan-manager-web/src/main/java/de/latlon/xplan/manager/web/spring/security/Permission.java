package de.latlon.xplan.manager.web.spring.security;

import org.springframework.security.core.Authentication;

/**
 * Interface common to all permissions.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public interface Permission {

    boolean isAllowed( Authentication authentication, Object targetDomainObject );

}
