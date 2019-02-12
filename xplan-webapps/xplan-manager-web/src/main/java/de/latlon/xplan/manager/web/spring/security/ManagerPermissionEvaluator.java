package de.latlon.xplan.manager.web.spring.security;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

/**
 * Evaluates if an authentication with {@link DistrictGrantedAuthority} has permission to a XPlanArchive.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ManagerPermissionEvaluator implements PermissionEvaluator {

    private static final Logger LOG = LoggerFactory.getLogger( ManagerPermissionEvaluator.class );

    private final Map<String, Permission> permissionNameToPermissions;

    public ManagerPermissionEvaluator( Map<String, Permission> permissionNameToPermissionMap ) {
        if ( permissionNameToPermissionMap != null )
            this.permissionNameToPermissions = permissionNameToPermissionMap;
        else
            this.permissionNameToPermissions = new HashMap<String, Permission>();
    }

    @Override
    public boolean hasPermission( Authentication authentication, Object targetDomainObject, Object permission ) {
        if ( canHandle( authentication, targetDomainObject, permission ) ) {
            LOG.info( "Check permission for authentication {} and target {}", authentication, targetDomainObject );
            return checkPermission( authentication, targetDomainObject, (String) permission );
        }
        return false;
    }

    @Override
    public boolean hasPermission( Authentication authentication, Serializable targetId, String targetType,
                                  Object permission ) {
        throw new UnsupportedOperationException( "Call method without targetId!" );
    }

    private boolean canHandle( Authentication authentication, Object targetDomainObject, Object permission ) {
        return targetDomainObject != null && authentication != null && permission instanceof String;
    }

    private boolean checkPermission( Authentication authentication, Object targetDomainObject, String permissionKey ) {
        verifyPermissionIsDefined( permissionKey );
        Permission permission = permissionNameToPermissions.get( permissionKey );
        return permission.isAllowed( authentication, targetDomainObject );
    }

    private void verifyPermissionIsDefined( String permissionKey ) {
        if ( !permissionNameToPermissions.containsKey( permissionKey ) ) {
            throw new UnsupportedOperationException( "The permission key is not defined! " );
        }

    }

}