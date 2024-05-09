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

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.RootPanel;
import de.latlon.xplan.manager.web.client.gui.editor.EditorView;
import de.latlon.xplan.manager.web.client.gui.event.EditorCanceledEvent;
import de.latlon.xplan.manager.web.client.gui.event.EditorCanceledEventHandler;
import de.latlon.xplan.manager.web.client.gui.event.EditorFinishedEvent;
import de.latlon.xplan.manager.web.client.gui.event.EditorFinishedEventHandler;
import de.latlon.xplan.manager.web.client.gui.event.EditorStartedEvent;
import de.latlon.xplan.manager.web.client.gui.event.EditorStartedEventHandler;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationConfig;

/**
 * Controller controlling the main views (like plan list with upload and edit views).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ViewController {

	private final HandlerManager eventBus;

	private final DeckPanel mainView;

	private EditorView editPanel;

	private ImportAndListView importAndOverviewPanel;

	/**
	 * @param eventBus required to control overall view events, never <code>null</code>
	 * @param configuration never <code>null</code>
	 * @param validationConfig never <code>null</code>
	 * @param authorizationInfo never <code>null</code>
	 */
	public ViewController(HandlerManager eventBus, ManagerWebConfiguration configuration,
			ValidationConfig validationConfig, AuthorizationInfo authorizationInfo) {
		this.eventBus = eventBus;
		this.mainView = new DeckPanel();
		createGui(eventBus, configuration, validationConfig, authorizationInfo);
		bind();
	}

	/**
	 * Inits the Controller with the passed rootPanel.
	 * @param rootPanel
	 */
	void init(RootPanel rootPanel) {
		RootPanel contentPanel = detectContentPanel(rootPanel);
		contentPanel.add(mainView);
		showImportAndPlanList();
	}

	private RootPanel detectContentPanel(RootPanel rootPanel) {
		RootPanel contentPanel = RootPanel.get("content");
		if (contentPanel != null)
			return contentPanel;
		return rootPanel;
	}

	private void createGui(HandlerManager eventBus, ManagerWebConfiguration configuration,
			ValidationConfig validationConfig, AuthorizationInfo authorizationInfo) {
		importAndOverviewPanel = new ImportAndListView(eventBus, configuration, validationConfig, authorizationInfo);
		editPanel = new EditorView(eventBus);

		mainView.add(importAndOverviewPanel);
		mainView.add(editPanel);
	}

	private void bind() {
		eventBus.addHandler(EditorStartedEvent.TYPE, new EditorStartedEventHandler() {

			@Override
			public void onEditorStarted(EditorStartedEvent event) {
				editPanel.setXPlanToEdit(event.getPlanId(), event.getBereiche(), event.getVersion(),
						event.getPlanType(), event.getxPlantoEdit());
				showEditModule();
			}
		});

		eventBus.addHandler(EditorFinishedEvent.TYPE, new EditorFinishedEventHandler() {

			@Override
			public void onEditorFinished(EditorFinishedEvent event) {
				importAndOverviewPanel.updatePlanList();
				showImportAndPlanList();
			}
		});

		eventBus.addHandler(EditorCanceledEvent.TYPE, new EditorCanceledEventHandler() {

			@Override
			public void onEditorCanceled(EditorCanceledEvent event) {
				showImportAndPlanList();
			}
		});
	}

	private void showEditModule() {
		mainView.showWidget(1);
	}

	private void showImportAndPlanList() {
		mainView.showWidget(0);
	}

}
