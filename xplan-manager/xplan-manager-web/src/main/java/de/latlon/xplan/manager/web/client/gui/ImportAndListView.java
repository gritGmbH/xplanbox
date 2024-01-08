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

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.validator.web.shared.ValidationConfig;

/**
 * Summarizes the PlanListPanel and UploadPanel in one view.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ImportAndListView extends VerticalPanel {

	private final PlanListPanel planListPanel;

	/**
	 * @param eventBus required to control overall view events, never <code>null</code>
	 * @param configuration never <code>null</code>
	 * @param validationConfig
	 * @param authorizationInfo never <code>null</code>
	 */
	public ImportAndListView(HandlerManager eventBus, final ManagerWebConfiguration configuration,
			ValidationConfig validationConfig, AuthorizationInfo authorizationInfo) {
		planListPanel = new PlanListPanel(eventBus, configuration, authorizationInfo);
		UploadPanel uploadPanel = new UploadPanel(configuration, validationConfig, planListPanel);
		createGUI(planListPanel, uploadPanel);
	}

	void updatePlanList() {
		planListPanel.reload(false);
	}

	private void createGUI(PlanListPanel planListPanel, UploadPanel uploadPanel) {
		setWidth("100%");
		setHorizontalAlignment(ALIGN_CENTER);
		setSpacing(15);
		add(uploadPanel);
		add(planListPanel);
	}

}
