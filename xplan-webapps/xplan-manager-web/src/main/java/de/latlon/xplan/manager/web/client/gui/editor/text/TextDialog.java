/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.web.client.gui.editor.text;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.EditDialogBoxWithRasterUpload;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.TypeCodeListBox;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.Text;
import de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType;

import java.util.ArrayList;
import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_41;

/**
 * Dialog to edit an existing or create a new {@link Text}
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class TextDialog extends EditDialogBoxWithRasterUpload {

	private final TextBox key = createTextInput();

	private final TextArea text = createTextAreaInput();

	private final TextBox basis = createTextInput();

	private final Text textToEdit;

	private TypeCodeListBox<TextRechtscharacterType> rechtscharakterType;

	/**
	 * Instantiates a {@link TextDialog} to edit an existing {@link Change}
	 * @param textToEdit the text to edit, should not <code>null</code> (a new change is
	 * created)
	 */
	public TextDialog(EditVersion version, Text textToEdit) {
		this(version, textToEdit, MESSAGES.editCaptionTextsDialogEdit());
	}

	/**
	 * Instantiates a {@link TextDialog} to create a new {@link Change}
	 */
	public TextDialog(EditVersion version) {
		this(version, null, MESSAGES.editCaptionTextsDialogNew());
	}

	/**
	 * @return the actual edited {@link Text}, may be <code>null</code>
	 */
	public Text getEditedText() {
		Text editedText = new Text();
		if (textToEdit != null)
			editedText.setFeatureId(textToEdit.getFeatureId());
		editedText.setKey(key.getValue());
		editedText.setBasis(basis.getValue());
		editedText.setText(text.getValue());
		if (reference.isFileSelected()) {
			editedText.setReference(reference.getFilename());
		}
		else {
			editedText.setReference(referenceLink.getValue());
		}
		editedText.setGeoReference(georeference.getFilename());
		if (rechtscharakterType != null) {
			editedText.setRechtscharakter(rechtscharakterType.getValueAsEnum());
		}
		return editedText;
	}

	@Override
	protected boolean isReferenceUrlMandatory() {
		return false;
	}

	@Override
	public boolean isValid() {
		return validate();
	}

	private boolean validate() {
		boolean valid = super.isValid();
		List<String> validationFailures = new ArrayList<String>();
		if (reference.isFileSelected() && !isNullOrEmpty(referenceLink.getValue())) {
			valid = false;
			validationFailures.add(MESSAGES.editCaptionTextsDokumentOrLink());
		}
		boolean textIsEntered = !isNullOrEmpty(text.getValue());
		boolean refTextIsEntered = reference.isFileSelected() || !isNullOrEmpty(referenceLink.getValue());
		if ((textIsEntered && refTextIsEntered) || (!textIsEntered && !refTextIsEntered)) {
			valid = false;
			validationFailures.add(MESSAGES.editCaptionTextsTextOrUrl());
		}
		showValidationError(validationFailures);
		return valid;
	}

	private TextDialog(EditVersion version, Text textToEdit, String title) {
		super(version, title);
		this.textToEdit = textToEdit;
		if (!XPLAN_41.equals(version)) {
			this.rechtscharakterType = new TypeCodeListBox<TextRechtscharacterType>(TextRechtscharacterType.class);
		}
		initDialog(createFormContent());
		setText(textToEdit);
	}

	private FlexTable createFormContent() {
		FlexTable layout = new FlexTable();
		FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
		formatter.setHorizontalAlignment(1, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(2, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(3, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(4, 1, ALIGN_LEFT);

		int index = 1;
		layout.setWidget(index, 1, new Label(MESSAGES.editCaptionTextsKey()));
		layout.setWidget(index++, 2, key);
		layout.setWidget(index, 1, new Label(MESSAGES.editCaptionTextsBasis()));
		layout.setWidget(index++, 2, basis);
		layout.setWidget(index, 1, new Label(MESSAGES.editCaptionTextsText()));
		layout.setWidget(index++, 2, text);
		if (rechtscharakterType != null) {
			layout.setWidget(index, 1, new Label(MESSAGES.editCaptionTextsRechtscharakter()));
			layout.setWidget(index++, 2, rechtscharakterType);
		}
		layout.setWidget(index, 1, new Label(MESSAGES.editCaptionTextsReference()));
		layout.setWidget(index++, 2, reference);
		layout.setWidget(index, 1, new Label(MESSAGES.editCaptionTextsReferenceLink()));
		layout.setWidget(index++, 2, referenceLink);
		// #3305 - georeference is not needed.
		// layout.setWidget( 5, 1, new Label( MESSAGES.editCaptionTextsGeoReference() ) );
		// layout.setWidget( 5, 2, georeference );
		return layout;
	}

	private void setText(Text textToSet) {
		if (textToSet != null) {
			key.setText(textToSet.getKey());
			basis.setText(textToSet.getBasis());
			text.setText(textToSet.getText());
			String reference = textToSet.getReference();
			if (reference != null && reference.startsWith("http")) {
				this.referenceLink.setValue(reference);
			}
			else {
				this.reference.setNameOfExistingFile(reference);
			}
			georeference.setNameOfExistingFile(textToSet.getGeoReference());
			if (rechtscharakterType != null) {
				rechtscharakterType.selectItem(textToSet.getRechtscharakter());
			}
		}
	}

}
