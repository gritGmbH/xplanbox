package de.latlon.xplan.manager.web.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;

/**
 * Async interface for manager web configuration services.
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public interface ManagerWebConfigurationServiceAsync {

    /**
     * Retrieve the configuration of the manager web
     * 
     * @param async
     */
    void getManagerWebConfiguration( AsyncCallback<ManagerWebConfiguration> async );

    /**
     * Retrieve the configuration of the map preview
     * 
     * @param async
     */
    void getMapPreviewConfiguration( AsyncCallback<MapPreviewConfiguration> async );

}
