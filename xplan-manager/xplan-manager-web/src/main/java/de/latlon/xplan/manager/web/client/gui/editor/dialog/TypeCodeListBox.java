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
package de.latlon.xplan.manager.web.client.gui.editor.dialog;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import com.google.gwt.user.client.ui.ListBox;

import de.latlon.xplan.manager.web.client.gui.editor.codelist.Code;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.TypeCodelistProvider;

/**
 * A {@link ListBox} encapsulating values from an enumeration.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class TypeCodeListBox<T extends Enum<T>> extends ListBox {

	private static final TypeCodelistProvider CODELIST_PROVIDER = new TypeCodelistProvider();

	public static final String NOSELECTION = "NOSELECTION";

	private final Class<T> enumClass;

	private final boolean isOptional;

	/**
	 * @param enumClass the enumeration class encapsulated, never <code>null</code>
	 */
	public TypeCodeListBox(Class<T> enumClass) {
		this(enumClass, false);
	}

	/**
	 * @param enumClass the enumeration class encapsulated, never <code>null</code>
	 * @param isOptional true if the selection is optional (default), false if mandatory
	 */
	public TypeCodeListBox(Class<T> enumClass, boolean isOptional) {
		this(enumClass, Collections.<T>emptyList(), isOptional);
	}

	/**
	 * @param enumClass the enumeration class encapsulated, never <code>null</code>
	 * @param disabledEnumEntries list of enums to exclude from {@link ListBox}, may be
	 * empty but never <code>null</code>
	 */
	public TypeCodeListBox(Class<T> enumClass, List<T> disabledEnumEntries) {
		this(enumClass, disabledEnumEntries, true);
	}

	/**
	 * @param enumClass the enumeration class encapsulated, never <code>null</code>
	 * @param disabledEnumEntries list of enums to exclude from {@link ListBox}, may be
	 * empty but never <code>null</code>
	 * @param isOptional true if the selection is optional (default), false if mandatory
	 */
	public TypeCodeListBox(Class<T> enumClass, List<T> disabledEnumEntries, boolean isOptional) {
		this.enumClass = enumClass;
		this.isOptional = isOptional;
		initListBoxItems(enumClass, disabledEnumEntries);
	}

	/**
	 * Retrieve the selected enumeration, may be <code>null</code>
	 * @return
	 */
	public T getValueAsEnum() {
		int selectedIndex = getSelectedIndex();
		if (isOptional && selectedIndex == 0) {
			return null;
		}
		if (selectedIndex > -1) {
			String value = getValue(selectedIndex);
			for (T en : EnumSet.allOf(enumClass)) {
				if (en.name().equalsIgnoreCase(value)) {
					return en;
				}
			}
		}
		return null;
	}

	/**
	 * @param enumToSelect the enumeration to select, may be <code>null</code> (first item
	 * is selected).
	 */
	public void selectItem(T enumToSelect) {
		if (enumToSelect == null && !isOptional)
			return;

		int indexToSelect;
		if (enumToSelect == null && isOptional)
			indexToSelect = findIndexToSelect(NOSELECTION);
		else
			indexToSelect = findIndexToSelect(enumToSelect);
		setSelectedIndex(indexToSelect);
	}

	private void initListBoxItems(Class<T> enumClass, List<T> disabledEnumEntries) {
		clear();
		List<Code> items = CODELIST_PROVIDER.retrieveItems(enumClass);
		if (isOptional) {
			addItem("Keine Auswahl", NOSELECTION);
		}
		for (Code item : items) {
			if (isEnabled(item, disabledEnumEntries))
				addItem(item.getItem(), item.getCode());
		}
		if (isOptional) {
			int indexOfNoSelection = findIndexToSelect(NOSELECTION);
			setSelectedIndex(indexOfNoSelection);
		}
	}

	private int findIndexToSelect(T enumToSelect) {
		String name = enumToSelect.name();
		return findIndexToSelect(name);
	}

	private int findIndexToSelect(String name) {
		int numberOfItems = getItemCount();
		for (int itemIndex = 0; itemIndex < numberOfItems; itemIndex++) {
			String itemValue = getValue(itemIndex);
			if (itemValue.equals(name))
				return itemIndex;
		}
		return 0;
	}

	private boolean isEnabled(Code item, List<T> disabledEnumEntries) {
		for (T t : disabledEnumEntries) {
			if (t.name().equalsIgnoreCase(item.getCode())) {
				return false;
			}
		}
		return true;
	}

}
