/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.web.client.gui.editor.change;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_41;
import static de.latlon.xplan.manager.web.client.gui.validation.ValidationUtils.areComponentsValid;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.EditDialogBox;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.TypeCodeListBox;
import de.latlon.xplan.manager.web.client.gui.widget.MandatoryTextBox;
import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.ChangeType;

/**
 * Dialog to edit an existing or create a new {@link Change} of XPlan version 3.0.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ChangesXplan30Dialog extends EditDialogBox implements Validable {

	private final MandatoryTextBox changeText = createMandatoryTextInput();

	private final TypeCodeListBox<ChangeType> changeType = new TypeCodeListBox<ChangeType>(ChangeType.class);

	/**
	 * Instantiates a {@link ChangesXplan30Dialog} to edit an existing {@link Change} for
	 * XPlan version 3,0
	 * @param changeToEdit the change to edit, should not <code>null</code> (a new change
	 * is created)
	 */
	public ChangesXplan30Dialog(Change changeToEdit) {
		this(XPLAN_41, MESSAGES.editCaptionChangesDialogEdit());
		setChange(changeToEdit);
	}

	/**
	 * Instantiates a {@link ChangesXplan30Dialog} to create a new {@link Change}
	 */
	public ChangesXplan30Dialog(EditVersion version) {
		this(version, MESSAGES.editCaptionChangesDialogNew());
	}

	@Override
	public boolean isValid() {
		return areComponentsValid(changeText);
	}

	/**
	 * @return the actual edited {@link Change}, may be <code>null</code>
	 */
	public Change getChange() {
		Change change = new Change();
		change.setPlanName(changeText.getText());
		change.setType(changeType.getValueAsEnum());
		return change;
	}

	private ChangesXplan30Dialog(EditVersion version, String title) {
		super(title);
		initDialog(createGui());
	}

	private Widget createGui() {
		FlexTable layout = new FlexTable();
		FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
		formatter.setHorizontalAlignment(1, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(2, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(3, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(4, 1, ALIGN_LEFT);

		layout.setWidget(1, 1, new Label(MESSAGES.editCaptionChangesText()));
		layout.setWidget(1, 2, changeText);
		layout.setWidget(4, 1, new Label(MESSAGES.editCaptionChangesType()));
		layout.setWidget(4, 2, changeType);

		return layout;
	}

	private void setChange(Change change) {
		if (change != null) {
			changeText.setText(change.getPlanName());
			changeType.selectItem(change.getType());
		}
	}

}
