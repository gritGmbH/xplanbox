package de.latlon.xplan.manager.web.spring.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

/**
 * Contains the role and districts the user is authorized.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class DistrictGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = 6443157754173821834L;

    private final String role;

    private final List<String> districts;

    /**
     * 
     * @param role
     *            role, such as user or administrator
     */
    public DistrictGrantedAuthority( String role ) {
        this( role, null );
    }

    /**
     * 
     * @param role
     *            role, such as user or administrator
     * @param districts
     *            districts, if <code>null</code>, an empty list is used.
     */
    public DistrictGrantedAuthority( String role, List<String> districts ) {
        this.role = role;
        if ( districts != null )
            this.districts = districts;
        else
            this.districts = new ArrayList<>();
    }

    @Override
    public String getAuthority() {
        return role;
    }

    /**
     * 
     * @return districts
     */
    public List<String> getDistricts() {
        return districts;
    }

    @Override
    public String toString() {
        return "DistrictGrantedAuthority [role=" + role + ", districts=" + districts + "]";
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        DistrictGrantedAuthority that = (DistrictGrantedAuthority) o;

        if ( !districts.equals( that.districts ) ) {
            return false;
        }
        if ( role != null ? !role.equals( that.role ) : that.role != null ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + districts.hashCode();
        return result;
    }

}
