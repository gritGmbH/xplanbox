package de.latlon.xplan.manager.web.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;

/**
 * Interface for manager web configuration services.
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
@RemoteServiceRelativePath("config")
public interface ManagerWebConfigurationService extends RemoteService {

    /**
     * Retrieve the {@link ManagerWebConfiguration}
     * 
     * @return the configuration of the manager web
     */
    ManagerWebConfiguration getManagerWebConfiguration()
                            throws ConfigurationException;

    /**
     * Retrieve the {@link de.latlon.xplan.manager.web.shared.MapPreviewConfiguration}
     * 
     * @return the configuration of the map preview
     */
    MapPreviewConfiguration getMapPreviewConfiguration()
                            throws ConfigurationException;

}