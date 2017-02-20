package de.latlon.xplan.manager.web.client.gui;

import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.client.service.ManagerWebConfigurationService;
import de.latlon.xplan.manager.web.client.service.ManagerWebConfigurationServiceAsync;
import de.latlon.xplan.manager.web.client.service.SecurityService;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;

/**
 * Main Web UI class.
 * 
 * @author Florian Bingel
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public class ManagerWebEntryPoint implements EntryPoint {

    private final ManagerWebConfigurationServiceAsync configurationService = GWT.create( ManagerWebConfigurationService.class );

    private final XPlanWebMessages messages = GWT.create( XPlanWebMessages.class );

    static {
        // due to problems with date parsing, see: https://github.com/resty-gwt/resty-gwt/issues/53
        Defaults.setDateFormat( null );
    }

    @Override
    public void onModuleLoad() {
        createMainPanel();
    }

    private void createMainPanel() {
        configurationService.getManagerWebConfiguration( new AsyncCallback<ManagerWebConfiguration>() {

            @Override
            public void onFailure( Throwable caught ) {
                Window.alert( messages.loadingConfigurationFailed() );
            }

            @Override
            public void onSuccess( final ManagerWebConfiguration configuration ) {
                SecurityService.Util.getService().retrieveAuthorizationInfo( new MethodCallback<AuthorizationInfo>() {

                    @Override
                    public void onFailure( Method method, Throwable throwable ) {
                        Window.alert( messages.loadingAuthorizationInfoFailed() );
                    }

                    @Override
                    public void onSuccess( Method method, AuthorizationInfo authorizationInfo ) {
                        HandlerManager eventBus = new HandlerManager( null );
                        ViewController viewController = new ViewController( eventBus, configuration, authorizationInfo );
                        viewController.init( RootPanel.get() );
                    }
                } );
            }
        } );
    }

}
