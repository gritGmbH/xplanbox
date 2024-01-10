/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.client.service.ManagerWebConfigurationService;
import de.latlon.xplan.manager.web.client.service.ManagerWebConfigurationServiceAsync;
import de.latlon.xplan.manager.web.client.service.SecurityService;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationConfigService;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationConfigServiceAsync;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationConfig;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * Main Web UI class.
 *
 * @author Florian Bingel
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @version $Revision: $, $Date: $
 */
public class ManagerWebEntryPoint implements EntryPoint {

	private final ManagerWebConfigurationServiceAsync configurationService = GWT
		.create(ManagerWebConfigurationService.class);

	private final ValidationConfigServiceAsync validationConfigService = GWT.create(ValidationConfigService.class);

	private final XPlanWebMessages messages = GWT.create(XPlanWebMessages.class);

	static {
		// due to problems with date parsing, see:
		// https://github.com/resty-gwt/resty-gwt/issues/53
		Defaults.setDateFormat(null);
	}

	@Override
	public void onModuleLoad() {
		createMainPanel();
	}

	private void createMainPanel() {
		configurationService.getManagerWebConfiguration(new AsyncCallback<ManagerWebConfiguration>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(messages.loadingConfigurationFailed());
			}

			@Override
			public void onSuccess(final ManagerWebConfiguration configuration) {
				SecurityService.Util.getService().retrieveAuthorizationInfo(new MethodCallback<AuthorizationInfo>() {

					@Override
					public void onFailure(Method method, Throwable throwable) {
						Window.alert(messages.loadingAuthorizationInfoFailed());
					}

					@Override
					public void onSuccess(Method method, AuthorizationInfo authorizationInfo) {
						validationConfigService.retrieveValidationConfig(new AsyncCallback<ValidationConfig>() {
							@Override
							public void onFailure(Throwable throwable) {
								Window.alert("Profile konnten nicht abgerufen werden: " + throwable.getMessage());
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

}
