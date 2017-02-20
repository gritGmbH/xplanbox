package de.latlon.xplan.manager.wmsconfig;

import java.io.File;

import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class WmsWorkspaceManagerTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateWmsWorkspaceWithUnknownWmsWorkspace()
                    throws Exception {
        new WmsWorkspaceManager( new File( "/NOT/EXISTING" ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInstantiateWmsWorkspaceWithNullWmsWorkspace()
                    throws Exception {
        new WmsWorkspaceManager( null );
    }

}