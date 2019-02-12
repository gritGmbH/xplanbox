package de.latlon.xplan.manager.web.shared;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Holds information about the authorization.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public class AuthorizationInfo implements Serializable {

    private static final long serialVersionUID = -8979723934544833665L;

    private List<String> authorizedDistricts;

    private boolean superUser;

    private boolean editor;

    /**
     * An empty constructor is mandatory for GWT applications.
     */
    public AuthorizationInfo() {
    }

    /**
     * Instantiates a new {@link AuthorizationInfo} with empty district list and without editor permissions (if not
     * super user).
     * 
     * @param isSuperUser
     *            true if user is a super user, false otherwise
     */
    public AuthorizationInfo( boolean isSuperUser ) {
        this( Collections.<String>emptyList(), isSuperUser, false );
    }

    /**
     * 
     * @param authorizedDistricts
     *            list may be empty, never <code>null</code>
     * @param isSuperUser
     *            true if user is a super user, false otherwise
     * @param isEditor
     *            true if user is editor, false otherwise
     */
    public AuthorizationInfo( List<String> authorizedDistricts, boolean isSuperUser, boolean isEditor ) {
        this.authorizedDistricts = authorizedDistricts;
        this.superUser = isSuperUser;
        this.editor = isEditor;
    }

    /**
     * @return list of authorized districts, may be empty, never <code>null</code>
     */
    public List<String> getAuthorizedDistricts() {
        return authorizedDistricts;
    }

    /**
     * Setter is mandatory for GWT applications.
     * 
     * @param authorizedDistricts
     *            list of authorized districts, may be empty, never <code>null</code>
     */
    public void setAuthorizedDistricts( List<String> authorizedDistricts ) {
        this.authorizedDistricts = authorizedDistricts;
    }

    /**
     * @return true if super user, false otherwise
     */
    public boolean isSuperUser() {
        return superUser;
    }

    /**
     * Setter is mandatory for GWT applications.
     * 
     * @param isSuperUser
     *            is super user
     */
    public void setSuperUser( boolean isSuperUser ) {
        this.superUser = isSuperUser;
    }

    /**
     * @return true if editor, false otherwise
     */
    public boolean isEditor() {
        return editor;
    }

    /**
     * Setter is mandatory for GWT applications.
     * 
     * @param isEditor
     *            is editor
     */
    public void setEditor( boolean isEditor ) {
        this.editor = isEditor;
    }

}