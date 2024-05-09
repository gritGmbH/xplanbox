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
package de.latlon.xplan.manager.web.client.gui.editor.change;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.editor.EditPlanType;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.EditDialogBox;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.TypeCodeListBox;
import de.latlon.xplan.manager.web.client.gui.widget.CodeListBox;
import de.latlon.xplan.manager.web.client.gui.widget.MandatoryTextBox;
import de.latlon.xplan.manager.web.client.gui.widget.PatternTextBox;
import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.ChangeType;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.TEXT_PATTERN;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.XP_RechtscharakterPlanaenderung;
import static de.latlon.xplan.manager.web.client.gui.utils.ValidationUtils.areComponentsValid;

/**
 * Dialog to edit an existing or create a new {@link Change} of XPlan version 4.1 or 5.0.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ChangesXplanDialog extends EditDialogBox implements Validable {

	private final MandatoryTextBox planName = createMandatoryTextInput(TEXT_PATTERN, S_LENGTH);

	private final PatternTextBox number = createPatternTextInput(TEXT_PATTERN, S_LENGTH);

	private final CodeListBox legalNature;

	private final TypeCodeListBox<ChangeType> changeType = new TypeCodeListBox<ChangeType>(ChangeType.class);

	/**
	 * Instantiates a {@link ChangesXplanDialog} to edit an existing {@link Change} for
	 * XPlan version 4.1
	 * @param changeToEdit the change to edit, should not <code>null</code> (a new change
	 * is created)
	 */
	public ChangesXplanDialog(EditVersion version, EditPlanType planType, Change changeToEdit) {
		this(version, planType, MESSAGES.editCaptionChangesDialogEdit());
		setChange(changeToEdit);
	}

	/**
	 * Instantiates a {@link ChangesXplanDialog} to create a new {@link Change}
	 */
	public ChangesXplanDialog(EditVersion version, EditPlanType planType) {
		this(version, planType, MESSAGES.editCaptionChangesDialogNew());
	}

	@Override
	public boolean isValid() {
		return areComponentsValid(number, planName, legalNature);
	}

	/**
	 * @return the actual edited {@link Change}, may be <code>null</code>
	 */
	public Change getChange() {
		Change change = new Change();
		change.setPlanName(planName.getText());
		change.setNumber(number.getText());
		change.setLegalNatureCode(legalNature.getValueAsCode());
		change.setType(changeType.getValueAsEnum());
		return change;
	}

	private ChangesXplanDialog(EditVersion version, EditPlanType planType, String title) {
		super(title);
		this.legalNature = createMandatoryCodeListInput(version, planType, XP_RechtscharakterPlanaenderung);
		initDialog(createGui());
	}

	private Widget createGui() {
		FlexTable layout = new FlexTable();
		FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
		formatter.setHorizontalAlignment(1, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(2, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(3, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(4, 1, ALIGN_LEFT);

		layout.setWidget(1, 1, new Label(MESSAGES.editCaptionChangesPlanName()));
		layout.setWidget(1, 2, planName);
		layout.setWidget(2, 1, new Label(MESSAGES.editCaptionChangesLegalNature()));
		layout.setWidget(2, 2, legalNature);
		layout.setWidget(3, 1, new Label(MESSAGES.editCaptionChangesNumber()));
		layout.setWidget(3, 2, number);
		layout.setWidget(4, 1, new Label(MESSAGES.editCaptionChangesType()));
		layout.setWidget(4, 2, changeType);

		return layout;
	}

	private void setChange(Change change) {
		if (change != null) {
			planName.setText(change.getPlanName());
			number.setText(change.getNumber());
			legalNature.selectItem(change.getLegalNatureCode());
			changeType.selectItem(change.getType());
		}
	}

}
