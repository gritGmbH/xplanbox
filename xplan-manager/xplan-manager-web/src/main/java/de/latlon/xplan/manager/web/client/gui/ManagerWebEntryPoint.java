/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.manager.web.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.RootPanel;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.client.service.ManagerWebConfigurationService;
import de.latlon.xplan.manager.web.client.service.ManagerWebConfigurationServiceAsync;
import de.latlon.xplan.manager.web.client.service.SecurityService;
import de.latlon.xplan.manager.web.client.service.SecurityServiceAsync;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationConfigService;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationConfigServiceAsync;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationConfig;

/**
 * Main Web UI class.
 *
 * @author Florian Bingel
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @version $Revision: $, $Date: $
 */
public class ManagerWebEntryPoint implements EntryPoint {

	private final XPlanWebMessages messages = GWT.create(XPlanWebMessages.class);

	@Override
	public void onModuleLoad() {
		Cookies.setCookie("JSESSIONID", "empty");
		createMainPanel();
	}

	private void createMainPanel() {
		XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync) GWT.create(XsrfTokenService.class);
		((ServiceDefTarget) xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
		xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
			public void onSuccess(XsrfToken token) {
				ManagerWebConfigurationServiceAsync configurationService = GWT
					.create(ManagerWebConfigurationService.class);
				((HasRpcToken) configurationService).setRpcToken(token);
				configurationService.getManagerWebConfiguration(new AsyncCallback<ManagerWebConfiguration>() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert(messages.loadingConfigurationFailed());
					}

					@Override
					public void onSuccess(final ManagerWebConfiguration configuration) {
						SecurityServiceAsync securityService = GWT.create(SecurityService.class);
						((HasRpcToken) securityService).setRpcToken(token);
						securityService.retrieveAuthorizationInfo(new AsyncCallback<AuthorizationInfo>() {

							@Override
							public void onFailure(Throwable throwable) {
								Window.alert(messages.loadingAuthorizationInfoFailed());
							}

							@Override
							public void onSuccess(AuthorizationInfo authorizationInfo) {
								ValidationConfigServiceAsync validationConfigService = GWT
									.create(ValidationConfigService.class);
								((HasRpcToken) validationConfigService).setRpcToken(token);
								validationConfigService.retrieveValidationConfig(new AsyncCallback<ValidationConfig>() {
									@Override
									public void onFailure(Throwable throwable) {
										Window
											.alert("Profile konnten nicht abgerufen werden: " + throwable.getMessage());
										init(new ValidationConfig());
									}

									@Override
									public void onSuccess(ValidationConfig validationConfig) {
										init(validationConfig);
									}

									private void init(ValidationConfig validationConfig) {
										HandlerManager eventBus = new HandlerManager(null);
										ViewController viewController = new ViewController(eventBus, configuration,
												validationConfig, authorizationInfo);
										viewController.init(RootPanel.get());
									}
								});
							}
						});
					}
				});
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

}
