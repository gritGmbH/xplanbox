package de.latlon.xplan.manager.web.spring.security;

/**
 * Enum for active directory roles.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public enum XPlanAuthorizationRole {

    ROLE_SUPERUSER( "ROLE_XPLAN_SUPERUSER" ), ROLE_USER( "ROLE_XPLAN_USER" ), ROLE_EDITOR( "ROLE_XPLAN_EDITOR" );

    private final String roleName;

    private XPlanAuthorizationRole( String roleName ) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }

}