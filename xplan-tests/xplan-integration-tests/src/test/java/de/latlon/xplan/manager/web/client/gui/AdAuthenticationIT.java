package de.latlon.xplan.manager.web.client.gui;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration tests for Active Directory authentication.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class AdAuthenticationIT {

    private final String adUrl = System.getProperty( "url" ) + System.getProperty( "adContextPath" );

    @Test
    public void testAdAuthenticationWithCorrectCredentialsShouldReturn200()
                            throws Exception {
        URL url = new URL( adUrl );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty( "Authorization", "Basic dGVzdC1zdXBlcjpBRFNlcnZlciE=" );
        int responseCode = connection.getResponseCode();

        assertThat( responseCode, is( 200 ) );
    }

    @Test
    public void testAdAuthenticationWithWrongCredentialsShouldReturn401()
                            throws Exception {
        URL url = new URL( adUrl );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty( "Authorization", "Basic dGVzdDp0ZXN0" );
        int responseCode = connection.getResponseCode();

        assertThat( responseCode, is( 401 ) );
    }

    @Test
    public void testAdAuthenticationWithMissingCredentialsShouldReturn401()
                            throws Exception {
        URL url = new URL( adUrl );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();

        assertThat( responseCode, is( 401 ) );
    }

}
