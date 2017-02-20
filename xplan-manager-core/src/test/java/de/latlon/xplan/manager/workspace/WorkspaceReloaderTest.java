package de.latlon.xplan.manager.workspace;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;

/**
 * Tests for {@link WorkspaceReloader}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 *
 * @version $Revision: $, $Date: $
 */
public class WorkspaceReloaderTest {

    private final WorkspaceReloader workspaceReloader = new WorkspaceReloader();

    @Test
    public void testReloadWorkspaceWithInvalidConfigurationShouldFail()
                    throws Exception {
        WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration();
        boolean isReloadSuccessful = workspaceReloader.reloadWorkspace( configuration );

        assertThat( isReloadSuccessful, is( false ) );
    }

    @Test
    public void testReloadWorkspaceWithInvalidUrlShouldFail()
                    throws Exception {
        List<String> urlList = singletonList( "http://invalid-url" );
        WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration( urlList, "user",
                        "password" );
        boolean isReloadSuccessful = workspaceReloader.reloadWorkspace( configuration );

        assertThat( isReloadSuccessful, is( false ) );
    }

    @Test
    public void testReloadWorkspaceWithTwoInvalidUrlsShouldFail()
                    throws Exception {
        List<String> urlList = asList( "http://invalid-url1", "http://invalid-url2" );
        WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration( urlList, "user",
                        "password" );
        boolean isReloadSuccessful = workspaceReloader.reloadWorkspace( configuration );

        assertThat( isReloadSuccessful, is( false ) );
    }

    @Test
    public void testReloadWorkspaceWithThreeInvalidUrlsShouldFail()
                    throws Exception {
        List<String> urlList = asList( "http://invalid-url1", "http://invalid-url2", "http://invalid-url3" );
        WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration( urlList, "user",
                        "password" );
        boolean isReloadSuccessful = workspaceReloader.reloadWorkspace( configuration );

        assertThat( isReloadSuccessful, is( false ) );
    }

}