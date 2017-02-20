package de.latlon.xplan.manager.web.spring.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.manager.web.shared.XPlan;

/**
 * Checks if the requesting user has the permission to the archive with the district.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class DistrictPermission implements Permission {

    @Override
    public boolean isAllowed( Authentication authentication, Object targetDomainObject ) {
        if ( canHandleXPlanArchive( authentication, targetDomainObject ) ) {
            String district = ( (XPlanArchive) targetDomainObject ).getDistrict();
            return checkPermission( authentication, district );
        } else if ( canHandleXPlan( authentication, targetDomainObject ) ) {
            String district = ( (XPlan) targetDomainObject ).getDistrict();
            return checkPermission( authentication, district );
        }
        return false;
    }

    private boolean canHandleXPlanArchive( Authentication authentication, Object targetDomainObject ) {
        return authentication != null && targetDomainObject instanceof XPlanArchive;
    }

    private boolean canHandleXPlan( Authentication authentication, Object targetDomainObject ) {
        return authentication != null && targetDomainObject instanceof XPlan;
    }

    private boolean checkPermission( Authentication authentication, String planDistrict ) {
        if ( planDistrict != null ) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for ( GrantedAuthority grantedAuthority : authorities ) {
                if ( grantedAuthority instanceof DistrictGrantedAuthority ) {
                    List<String> userDistricts = ( (DistrictGrantedAuthority) grantedAuthority ).getDistricts();
                    if ( userDistricts != null && userDistricts.contains( planDistrict ) )
                        return true;
                }
            }
        }
        return false;
    }
}