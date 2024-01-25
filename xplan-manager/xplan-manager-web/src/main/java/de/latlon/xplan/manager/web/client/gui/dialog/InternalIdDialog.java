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

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

/**
 * Dialog to select an internal id.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class InternalIdDialog extends WizardDialogBox {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private final ListBox listBox;

	public InternalIdDialog(Map<String, String> result) {
		super(MESSAGES.internalIdDialogBoxTitle());
		listBox = createListBox(result);
		setContent(createPanel());
	}

	/**
	 * @return the selected internal id, <code>null</code> if no entry is selected
	 */
	public String retrieveSelectedInternalId() {
		int selectedIndex = listBox.getSelectedIndex();
		if (selectedIndex >= 0) {
			String internalIdFromBox = listBox.getValue(selectedIndex);
			return internalIdFromBox.substring(internalIdFromBox.indexOf(": ") + 2);
		}
		return null;
	}

	private Panel createPanel() {
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(20);
		panel.add(listBox);
		return panel;
	}

	private ListBox createListBox(Map<String, String> result) {
		final ListBox listBox = new ListBox();
		for (Map.Entry<String, String> id2name : result.entrySet()) {
			String optionName = id2name.getValue() + ": " + id2name.getKey();
			listBox.addItem(optionName);
		}
		return listBox;
	}

}
