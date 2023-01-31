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
package de.latlon.xplan.manager.web.client.gui.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RechtsstandAndPlanStatus;

import static de.latlon.xplan.manager.web.shared.PlanStatus.ARCHIVIERT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;

/**
 * Dialog to select the legislation status of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class LegislationStatusDialog extends WizardDialogBox {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private final ListBox legislationStatusSelectBox;

	/**
	 * @param legislationStatus the status from the plan, may be <code>null</code> if not
	 * set
	 */
	public LegislationStatusDialog(RechtsstandAndPlanStatus legislationStatus) {
		super(MESSAGES.legislationStatusDialogTitle());
		setWidth("425px");
		this.legislationStatusSelectBox = createLegislationStatusListBox(legislationStatus);
		setContent(createContentPanel(legislationStatus.getRechtsstand().getTranslatedCode()));
	}

	/**
	 * @return the selected legislation status, <code>null</code> if no entry is selected
	 */
	public PlanStatus retrieveSelectedLegislationStatus() {
		int selectedIndex = legislationStatusSelectBox.getSelectedIndex();
		if (selectedIndex == 0)
			return FESTGESTELLT;
		else if (selectedIndex == 1)
			return IN_AUFSTELLUNG;
		else if (selectedIndex == 2)
			return ARCHIVIERT;
		return null;
	}

	private ListBox createLegislationStatusListBox(RechtsstandAndPlanStatus rechtsstandAndPlanStatus) {
		final ListBox legislationStatusListBox = new ListBox();
		int selectedIndex = 0;
		if (rechtsstandAndPlanStatus.getRechtsstand().getCodeNumber() < 0) {
			legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogFestgestelltOption());
			legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogInAufstellungOption());
			legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogArchiviertOption());
		}
		else {
			PlanStatus rechtsstand = rechtsstandAndPlanStatus.getPlanStatus();
			switch (rechtsstand) {
				case FESTGESTELLT:
					legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogFestgestelltSelectedOption());
					legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogInAufstellungOption());
					legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogArchiviertOption());
					break;
				case ARCHIVIERT:
					legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogFestgestelltOption());
					legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogInAufstellungOption());
					legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogArchiviertSelectedOption());
					selectedIndex = 2;
					break;
				case IN_AUFSTELLUNG:
					legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogFestgestelltOption());
					legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogInAufstellungSelectedOption());
					legislationStatusListBox.addItem(MESSAGES.legislationStatusDialogArchiviertOption());
					selectedIndex = 1;
					break;
			}
		}
		legislationStatusListBox.setSelectedIndex(selectedIndex);
		return legislationStatusListBox;
	}

	private Panel createContentPanel(String translatedLegislationStatus) {
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(20);
		panel.add(new Label(retrieveText(translatedLegislationStatus)));
		panel.add(legislationStatusSelectBox);
		return panel;
	}

	private String retrieveText(String translatedLegislationStatus) {
		if (translatedLegislationStatus != null)
			return MESSAGES.legislationStatusDialogText(translatedLegislationStatus);
		else
			return MESSAGES.legislationStatusDialogTextWithoutLegislationStatus();
	}

}
