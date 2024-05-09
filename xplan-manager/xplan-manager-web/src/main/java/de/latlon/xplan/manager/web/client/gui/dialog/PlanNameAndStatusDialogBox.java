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
package de.latlon.xplan.manager.web.client.gui.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.PlanStatus;

import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanNameAndStatusDialogBox extends WizardDialogBox {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	/**
	 * @param planNameAndStatus the name and status of the plan, never <code>null</code>
	 */
	public PlanNameAndStatusDialogBox(Map<String, PlanStatus> planNameAndStatus) {
		super(MESSAGES.planNameAndStatusDialogHeader());
		if (planNameAndStatus.size() == 1) {
			String planName = planNameAndStatus.keySet().iterator().next();
			setContent(createMessageContent(planName, planNameAndStatus.get(planName)));
		}
		else {
			setContent(createMessageContent(planNameAndStatus));
		}
	}

	private Panel createMessageContent(String planName, PlanStatus planStatus) {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setSpacing(20);
		Label label = new Label(MESSAGES.duplicatePlanName(planName, planStatus.getMessage()));
		label.setWordWrap(true);
		mainPanel.add(label);
		return mainPanel;
	}

	private Panel createMessageContent(Map<String, PlanStatus> planNameAndStatus) {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setSpacing(15);
		Label label = new Label(MESSAGES.duplicatePlanNames());
		label.setWordWrap(true);
		mainPanel.add(label);
		for (String planName : planNameAndStatus.keySet()) {
			PlanStatus planStatus = planNameAndStatus.get(planName);
			String planStatusText = planStatus != null ? planStatus.getMessage() : "-";
			String text = "   - " + planName + ": " + planStatusText;
			Label duplicateNameEntry = new Label(text);
			mainPanel.add(duplicateNameEntry);
		}
		return mainPanel;
	}

}
