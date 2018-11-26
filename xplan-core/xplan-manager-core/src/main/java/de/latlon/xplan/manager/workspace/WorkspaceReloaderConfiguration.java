package de.latlon.xplan.manager.workspace;

import static java.util.Collections.emptyList;

import java.util.List;

/**
 * Container object for {@link WorkspaceReloader} configuration.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class WorkspaceReloaderConfiguration {

    private final List<String> urls;

    private final String user;

    private final String password;

    /**
     * Invalid workspace reloader configuration.
     */
    public WorkspaceReloaderConfiguration() {
        this.urls = emptyList();
        this.user = "";
        this.password = "";
    }

    /**
     * Valid workspace reloader configuration.
     * 
     * @param urls
     *            URLs of deegree services to reload, never <code>null</code>
     * @param user
     *            user used for authentication, never <code>null</code>
     * @param password
     *            password used for authentication, never <code>null</code>
     */
    public WorkspaceReloaderConfiguration( List<String> urls, String user, String password ) {
        this.urls = urls;
        this.user = user;
        this.password = password;
    }

    /**
     * @return URL of deegree service to reload, never <code>null</code>
     */
    public List<String> getUrls() {
        return urls;
    }

    /**
     * @return user used for authentication, never <code>null</code>
     */
    public String getUser() {
        return user;
    }

    /**
     * @return password used for authentication, never <code>null</code>
     */
    public String getPassword() {
        return password;
    }

}