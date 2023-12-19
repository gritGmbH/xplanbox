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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;

/**
 * Wizard to select a crs.
 *
 * @deprecated will be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 */
@Deprecated
public class CrsDialog extends WizardDialogBox {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private final ManagerWebConfiguration configuration;

	private final RadioButton defaultCrsRadioButton;

	private final RadioButton selectCrsRadioButton;

	private final ListBox crsSelection;

	public CrsDialog(ManagerWebConfiguration configuration) {
		super(MESSAGES.crsDialogHeader());
		this.configuration = configuration;
		defaultCrsRadioButton = createRadioButton(MESSAGES.crsDialogDefaultCrs(), true);
		selectCrsRadioButton = createRadioButton(MESSAGES.crsDialogSelectCrs(), false);
		crsSelection = createCrsSelectionListBox();
		setContent(createGui());
	}

	/**
	 * @return the CRS selected by the user, may be <code>null</code>
	 */
	public String retrieveSelectedCrs() {
		if (selectCrsRadioButton.getValue()) {
			return retrieveSelectedCrsFromSelectListBox();
		}
		else if (defaultCrsRadioButton.getValue())
			return configuration.getCrsDialogDefaultCrs();
		return null;
	}

	private Panel createGui() {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setSpacing(10);
		mainPanel.setWidth("100%");
		mainPanel.setHorizontalAlignment(ALIGN_LEFT);
		mainPanel.add(new Label(MESSAGES.crsDialogDescription()));
		mainPanel.add(defaultCrsRadioButton);
		mainPanel.add(createSelectCrsPanel());
		return mainPanel;
	}

	private Widget createSelectCrsPanel() {
		HorizontalPanel selectCrsPanel = new HorizontalPanel();
		selectCrsPanel.add(selectCrsRadioButton);
		selectCrsPanel.add(crsSelection);
		return selectCrsPanel;
	}

	private ListBox createCrsSelectionListBox() {
		ListBox listBox = new ListBox();
		listBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				selectCrsRadioButton.setValue(true);
			}
		});
		for (String crs : configuration.getCrsDialogChooseCrs())
			listBox.addItem(crs);
		return listBox;
	}

	private String retrieveSelectedCrsFromSelectListBox() {
		int selectedIndex = crsSelection.getSelectedIndex();
		if (selectedIndex > -1)
			return crsSelection.getValue(selectedIndex);
		return null;
	}

	private RadioButton createRadioButton(String text, boolean isSelected) {
		RadioButton radioButton = new RadioButton("crsSelect", text);
		radioButton.setValue(isSelected);
		return radioButton;
	}

}
