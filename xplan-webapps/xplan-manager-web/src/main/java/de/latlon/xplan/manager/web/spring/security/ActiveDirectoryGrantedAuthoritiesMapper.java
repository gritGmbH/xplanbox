package de.latlon.xplan.manager.web.spring.security;

import static de.latlon.xplan.manager.web.spring.security.XPlanAuthorizationRole.ROLE_EDITOR;
import static de.latlon.xplan.manager.web.spring.security.XPlanAuthorizationRole.ROLE_SUPERUSER;
import static de.latlon.xplan.manager.web.spring.security.XPlanAuthorizationRole.ROLE_USER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.access.hierarchicalroles.NullRoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.core.GrantedAuthority;

/**
 * Maps AD groups to the XPlan user with district authorities.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ActiveDirectoryGrantedAuthoritiesMapper extends RoleHierarchyAuthoritiesMapper {

    private final List<String> superUserGroups;

    private final List<String> editorGroups;

    private final Map<String, List<String>> groupToPlanDistricts;

    /**
     * 
     * Instantiates a {@link ActiveDirectoryGrantedAuthoritiesMapper} without hierarchical roles.
     * 
     * @param superUserGroups
     *            list of all groups giving super user permissions, if <code>null</code>, an empty map is used.
     * @param editorGroups
     *            list of all groups giving editor permissions, if <code>null</code>, an empty map is used.
     * @param groupToPlanDistricts
     *            Maps groups to plan districts, if <code>null</code>, an empty map is used.
     */
    public ActiveDirectoryGrantedAuthoritiesMapper( List<String> superUserGroups, List<String> editorGroups,
                                                    Map<String, List<String>> groupToPlanDistricts ) {
        this( superUserGroups, editorGroups, groupToPlanDistricts, new NullRoleHierarchy() );
    }

    /**
     * @param superUserGroups
     *            list of all groups giving super user permissions, if <code>null</code>, an empty map is used.
     * @param editorGroups
     *            list of all groups giving editor permissions, if <code>null</code>, an empty map is used.
     * @param groupToPlanDistricts
     *            Maps groups to plan districts, if <code>null</code>, an empty map is used.
     * @param roleHierarchy
     *            role hierarchy, never <code>null</code>
     */
    public ActiveDirectoryGrantedAuthoritiesMapper( List<String> superUserGroups, List<String> editorGroups,
                                                    Map<String, List<String>> groupToPlanDistricts,
                                                    RoleHierarchy roleHierarchy ) {
        super( roleHierarchy );
        if ( superUserGroups != null )
            this.superUserGroups = superUserGroups;
        else
            this.superUserGroups = new ArrayList<>();
        if ( editorGroups != null )
            this.editorGroups = editorGroups;
        else
            this.editorGroups = new ArrayList<>();
        if ( groupToPlanDistricts != null )
            this.groupToPlanDistricts = groupToPlanDistricts;
        else
            this.groupToPlanDistricts = new HashMap<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities( Collection<? extends GrantedAuthority> authorities ) {
        Collection<? extends GrantedAuthority> hierarchicalAuthorities = super.mapAuthorities( authorities );
        Set<GrantedAuthority> roles = new HashSet<>();
        for ( GrantedAuthority grantedAuthority : hierarchicalAuthorities ) {
            String authority = grantedAuthority.getAuthority();
            if ( authority != null ) {
                if ( superUserGroups.contains( authority ) )
                    roles.add( new DistrictGrantedAuthority( ROLE_SUPERUSER.toString() ) );
                if ( editorGroups.contains( authority ) )
                    roles.add( new DistrictGrantedAuthority( ROLE_EDITOR.toString() ) );
                if ( groupToPlanDistricts.keySet().contains( authority ) )
                    roles.add( new DistrictGrantedAuthority( ROLE_USER.toString(),
                                    groupToPlanDistricts.get( authority ) ) );
            }
        }
        return roles;
    }

}