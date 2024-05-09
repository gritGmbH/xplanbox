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
package de.latlon.xplan.manager.web.client.gui.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import de.latlon.xplan.manager.web.client.gui.editor.EditPlanType;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.Code;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistProvider;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

import java.util.List;

import static de.latlon.xplan.manager.web.client.gui.StyleNames.EDITOR_VALIDATION_ERROR;

/**
 * {@link ListBox} representing only Codes.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class CodeListBox extends ListBox implements Validable {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private static final CodelistProvider CODELISTPROVIDER = new CodelistProvider();

	private boolean isMandatory;

	/**
	 * @param version of the XPlan, never <code>null</code>
	 * @param codelistType of this CodeListBox, never <code>null</code>
	 * @param isMandatory <code>true</code> if a selection is required, <code>false</code>
	 * otherwise
	 */
	public CodeListBox(EditVersion version, EditPlanType editPlanType, CodelistType codelistType, boolean isMandatory) {
		this.isMandatory = isMandatory;
		addMandatoryChangeHandler();
		initListBoxItems(version, editPlanType, codelistType);
		selectItem(-1);
	}

	/**
	 * @return retrieve the selected item as code value, -1 if nothing is selected.
	 */
	public int getValueAsCode() {
		int selectedIndex = getSelectedIndex();
		if (selectedIndex > 0) {
			String value = getValue(selectedIndex);
			try {
				return Integer.valueOf(value);
			}
			catch (NumberFormatException e) {
			}
		}
		return -1;
	}

	/**
	 * @param codeToSelect select the item with this code
	 */
	public void selectItem(int codeToSelect) {
		int indexToSelect = findIndexToSelect(codeToSelect);
		setSelectedIndex(indexToSelect);
		validate();
	}

	@Override
	public boolean isValid() {
		return validate();
	}

	private void initListBoxItems(EditVersion version, EditPlanType editPlanType, CodelistType codelistType) {
		clear();
		List<Code> items = CODELISTPROVIDER.retrieveItems(version, editPlanType, codelistType);
		addItem("Keine Auswahl");
		for (Code item : items) {
			addItem(item.getItem(), item.getCode());
		}
	}

	private void addMandatoryChangeHandler() {
		addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				validate();
			}
		});
	}

	private boolean validate() {
		if (isMandatory) {
			int selectedIndex = getSelectedIndex();
			if (selectedIndex == 0) {
				addStyleName(EDITOR_VALIDATION_ERROR);
				setTitle(MESSAGES.editInputRequired());
				return false;
			}
			else {
				removeStyleName(EDITOR_VALIDATION_ERROR);
				setTitle("");
				return true;
			}
		}
		return true;
	}

	private int findIndexToSelect(int codeToSelect) {
		int numberOfItems = getItemCount();
		for (int itemIndex = 0; itemIndex < numberOfItems; itemIndex++) {
			String itemValue = getValue(itemIndex);
			if (itemValue.equals(Integer.toString(codeToSelect)))
				return itemIndex;
		}
		return 0;
	}

}
