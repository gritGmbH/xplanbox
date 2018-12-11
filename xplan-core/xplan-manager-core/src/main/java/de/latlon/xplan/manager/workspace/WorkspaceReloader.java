package de.latlon.xplan.manager.workspace;

import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reloads a workspace via HTTP.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class WorkspaceReloader {

    private final Logger LOG = LoggerFactory.getLogger( WorkspaceReloader.class );

    /**
     * Reloads a workspace.
     * 
     * @param configuration
     *            configuration for {@link WorkspaceReloader}, if {@link WorkspaceReloaderConfiguration} is invalid,
     *            reload is skipped, never <code>null</code>
     * 
     * @return true if all workspace reloads were successful, false otherwise.
     */
    public boolean reloadWorkspace( WorkspaceReloaderConfiguration configuration ) {
        boolean isValid = checkConfiguration( configuration );
        if ( isValid ) {
            LOG.info( "Workspace reloader configuration is valid." );
            return reload( configuration );
        } else {
            LOG.info( "Workspace reloader configuration is invalid. Reload of workspace is skipped!" );
            return false;
        }
    }

    private boolean reload( WorkspaceReloaderConfiguration configuration ) {
        List<String> urls = configuration.getUrls();
        boolean isReloadSuccessfulForAllWorkspaces = true;
        for ( String url : urls ) {
            boolean reloadResult = reloadWorkspace( configuration, url );
            isReloadSuccessfulForAllWorkspaces = isReloadSuccessfulForAllWorkspaces && reloadResult;
        }
        return isReloadSuccessfulForAllWorkspaces;
    }

    private boolean reloadWorkspace( WorkspaceReloaderConfiguration configuration, String url ) {
        try {
            String reloadUrl = retrieveWorkspaceReloadUrl( url );
            LOG.info( "Attempting to reload workspace with URL {}", reloadUrl );
            HttpGet httpGet = retrieveConfiguredHttpGet( reloadUrl, configuration.getUser(),
                                                         configuration.getPassword() );
            DefaultHttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute( httpGet );
            if ( isResponseCodeOk( response ) ) {
                LOG.info( "Reload completed successfully." );
                return true;
            } else {
                LOG.info( "Error while reloading workspace: {}", response.getStatusLine().getReasonPhrase() );
                return false;
            }
        } catch ( IOException e ) {
            LOG.info( "Reload of workspace failed: {}", e.getMessage() );
            LOG.trace( "Reload of workspace failed!", e );
            return false;
        }
    }

    private String retrieveWorkspaceReloadUrl( String url ) {
        if ( url.contains( "config/restart" ) )
            return url;
        if ( !url.endsWith( "/" ) )
            url = url.concat( "/" );
        return url.concat( "config/restart" );
    }

    private HttpGet retrieveConfiguredHttpGet( String reloadUrl, String user, String password ) {
        HttpGet httpGet = new HttpGet( reloadUrl );
        byte[] basicAuth = ( user + ":" + password ).getBytes();
        String basicAuthEncoded = new String( Base64.encodeBase64( basicAuth ) );
        httpGet.addHeader( "Authorization", "Basic " + basicAuthEncoded );
        return httpGet;
    }

    private boolean isResponseCodeOk( HttpResponse response ) {
        return response.getStatusLine().getStatusCode() == 200;
    }

    private boolean checkConfiguration( WorkspaceReloaderConfiguration configuration ) {
        List<String> url = configuration.getUrls();
        String user = configuration.getUser();
        String password = configuration.getPassword();
        return isNotNullOrEmpty( url ) && isNotNullOrEmpty( user ) && isNotNullOrEmpty( password );
    }

    private boolean isNotNullOrEmpty( String configValue ) {
        return configValue != null && !configValue.isEmpty();
    }

    private boolean isNotNullOrEmpty( List<String> configValue ) {
        return configValue != null && !configValue.isEmpty();
    }

}