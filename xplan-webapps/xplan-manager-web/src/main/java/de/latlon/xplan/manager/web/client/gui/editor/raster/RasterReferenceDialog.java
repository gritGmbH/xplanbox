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
package de.latlon.xplan.manager.web.client.gui.editor.raster;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.EditDialogBoxWithRasterUpload;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.TypeCodeListBox;
import de.latlon.xplan.manager.web.client.gui.widget.MandatoryTextBox;
import de.latlon.xplan.manager.web.client.gui.widget.PatternTextArea;
import de.latlon.xplan.manager.web.client.gui.widget.PatternTextBox;
import de.latlon.xplan.manager.web.client.gui.widget.StrictDateBox;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt;
import de.latlon.xplan.manager.web.shared.edit.MimeTypes;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;

import java.util.ArrayList;
import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static de.latlon.xplan.commons.util.TextPatternConstants.DESCRIPTION_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.L_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.M_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.NAME_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.URL_PATTERN;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_41;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_50;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_51;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_52;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_53;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_54;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_60;
import static de.latlon.xplan.manager.web.client.gui.validation.ValidationUtils.areComponentsValid;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.DOKUMENT;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.TEXT;

/**
 * Dialog to edit an existing or create a new {@link RasterReference}
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class RasterReferenceDialog extends EditDialogBoxWithRasterUpload {

	private final ListBox bereichNummer;

	private List<Bereich> bereiche;

	private final TypeCodeListBox<RasterReferenceType> refType;

	private final TypeCodeListBox<MimeTypes> refMimeType;

	private final TypeCodeListBox<MimeTypes> georefMimeType;

	private final TypeCodeListBox<ExterneReferenzArt> artType;

	private final TextBox referenzName;

	private final PatternTextBox informationssystemURL = createPatternTextInput(URL_PATTERN, M_LENGTH);

	private final PatternTextArea beschreibung = createPatternTextAreaInput(DESCRIPTION_PATTERN, L_LENGTH);

	private final StrictDateBox datum = createDateInput();

	private final RasterReference originalRasterReference;

	public RasterReferenceDialog(EditVersion version, List<Bereich> bereiche) {
		this(version, bereiche, null, MESSAGES.editCaptionRasterBasisDialogNew(), true);
	}

	public RasterReferenceDialog(EditVersion version, List<Bereich> bereiche, RasterReference rasterReference) {
		this(version, bereiche, rasterReference, MESSAGES.editCaptionRasterBasisDialogEdit(), false);
	}

	private RasterReferenceDialog(EditVersion version, List<Bereich> bereiche, RasterReference rasterReference,
			String title, boolean isBereichNummerEditable) {
		super(version, title);
		this.bereiche = bereiche;
		this.bereichNummer = createBereichNummer(isBereichNummerEditable);
		this.refType = createRefType();
		this.refMimeType = createMimeTypeType();
		this.georefMimeType = createMimeTypeType();
		this.referenzName = createRasterName(version);
		this.artType = new TypeCodeListBox<ExterneReferenzArt>(ExterneReferenzArt.class, true);
		this.originalRasterReference = rasterReference;
		addChangeHandlers();
		initDialog(createFormContent());
		setRasterReferenceValues();
	}

	@Override
	protected boolean isGeoreferenceUrlMandatory() {
		return false;
	}

	@Override
	protected boolean isReferenceUrlMandatory() {
		return false;
	}

	@Override
	public boolean isValid() {
		return areComponentsValid(informationssystemURL, beschreibung) & validate(true);
	}

	public RasterReference getEditedRasterReference() {
		RasterReference rasterReference;
		if (originalRasterReference != null)
			rasterReference = new RasterReference(originalRasterReference);
		else
			rasterReference = new RasterReference();
		rasterReference.setBereichNummer(bereichNummer.getSelectedValue());
		rasterReference.setType(refType.getValueAsEnum());
		rasterReference.setReference(reference.getFilename());
		rasterReference.setReferenzMimeType(refMimeType.getValueAsEnum());
		rasterReference.setGeoReference(georeference.getFilename());
		rasterReference.setGeorefMimeType(georefMimeType.getValueAsEnum());
		rasterReference.setArt(artType.getValueAsEnum());
		rasterReference.setBeschreibung(beschreibung.getValue());
		rasterReference.setDatum(datum.getValue());
		rasterReference.setInformationssystemURL(informationssystemURL.getValue());
		rasterReference.setReferenzName(referenzName.getValue());
		return rasterReference;
	}

	private Widget createFormContent() {
		FlexTable layout = new FlexTable();
		FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
		formatter.setHorizontalAlignment(1, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(2, 1, ALIGN_LEFT);

		int rowIndex = 1;
		if (XPLAN_51.equals(version) || XPLAN_52.equals(version) || XPLAN_53.equals(version)
				|| XPLAN_54.equals(version)) {
			layout.setText(rowIndex++, 2, MESSAGES.editHintRasterBasisType());
		}
		layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisBereichNummer()));
		layout.setWidget(rowIndex++, 2, bereichNummer);
		layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisType()));
		layout.setWidget(rowIndex++, 2, refType);
		layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisReference()));
		layout.setWidget(rowIndex++, 2, reference);
		layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisReferenzMimeType()));
		layout.setWidget(rowIndex++, 2, refMimeType);
		layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisGeoReference()));
		layout.setWidget(rowIndex++, 2, georeference);
		if (!XPLAN_60.equals(version)) {
			layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisGeorefMimeType()));
			layout.setWidget(rowIndex++, 2, georefMimeType);
		}
		layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisArt()));
		layout.setWidget(rowIndex++, 2, artType);
		if (!XPLAN_60.equals(version)) {
			layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisInformationssystemURL()));
			layout.setWidget(rowIndex++, 2, informationssystemURL);
		}
		layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisReferenzName()));
		layout.setWidget(rowIndex++, 2, referenzName);
		layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisBeschreibung()));
		layout.setWidget(rowIndex++, 2, beschreibung);
		layout.setWidget(rowIndex, 1, new Label(MESSAGES.editCaptionRasterBasisDatum()));
		layout.setWidget(rowIndex++, 2, datum);
		return layout;
	}

	private void addChangeHandlers() {
		artType.addChangeHandler(new ClearValidationErrorsCH());
		georeference.addChangeHandler(new ClearValidationErrorsCH());
		georefMimeType.addChangeHandler(new ClearValidationErrorsCH());
		referenzName.addValueChangeHandler(new ClearValidationErrorsVCH<String>());
		reference.addChangeHandler(new ClearValidationErrorsCH());
	}

	private void setRasterReferenceValues() {
		if (originalRasterReference != null) {
			bereichNummer.setSelectedIndex(findIndex(originalRasterReference.getBereichNummer()));
			refType.selectItem(originalRasterReference.getType());
			reference.setNameOfExistingFile(originalRasterReference.getReference());
			refMimeType.selectItem(originalRasterReference.getReferenzMimeType());
			georeference.setNameOfExistingFile(originalRasterReference.getGeoReference());
			georefMimeType.selectItem(originalRasterReference.getGeorefMimeType());
			artType.selectItem(originalRasterReference.getArt());
			referenzName.setValue(originalRasterReference.getReferenzName());
			informationssystemURL.setValue(originalRasterReference.getInformationssystemURL());
			beschreibung.setValue(originalRasterReference.getBeschreibung());
			datum.setValue(originalRasterReference.getDatum());
		}
	}

	private ListBox createBereichNummer(boolean isBereichNummerEditable) {
		ListBox listBox = new ListBox();
		for (Bereich bereich : bereiche) {
			listBox.addItem(bereich.getName() != null ? bereich.getNummer() + "(" + bereich.getName() + ")"
					: bereich.getNummer(), bereich.getNummer());
		}
		listBox.setEnabled(isBereichNummerEditable);
		return listBox;
	}

	private int findIndex(String bereichNummer) {
		int index = 0;
		for (Bereich bereich : bereiche) {
			if (bereich.getNummer().equals(bereichNummer)) {
				return index;
			}
			index++;
		}
		return 0;
	}

	private TypeCodeListBox<RasterReferenceType> createRefType() {
		if (XPLAN_41.equals(version) || XPLAN_50.equals(version)) {
			TypeCodeListBox<RasterReferenceType> codeListBox = new TypeCodeListBox<RasterReferenceType>(
					RasterReferenceType.class);
			codeListBox.selectItem(RasterReferenceType.SCAN);
			return codeListBox;
		}
		List<RasterReferenceType> disabledItems = new ArrayList<RasterReferenceType>();
		disabledItems.add(RasterReferenceType.LEGEND);
		disabledItems.add(TEXT);
		TypeCodeListBox<RasterReferenceType> codeListBox = new TypeCodeListBox<RasterReferenceType>(
				RasterReferenceType.class, disabledItems, false);
		codeListBox.selectItem(RasterReferenceType.SCAN);
		return codeListBox;
	}

	private TypeCodeListBox<MimeTypes> createMimeTypeType() {
		return new TypeCodeListBox<>(MimeTypes.class, true);
	}

	private TextBox createRasterName(EditVersion version) {
		if (XPLAN_60.equals(version)) {
			return createMandatoryTextInput(NAME_PATTERN, S_LENGTH);
		}
		return createPatternTextInput(NAME_PATTERN, S_LENGTH);
	}

	private boolean validate(boolean includeReferences) {
		boolean valid = super.isValid();
		List<String> validationFailures = new ArrayList<String>();

		if (XPLAN_60.equals(version)) {
			if (referenzName instanceof MandatoryTextBox && !((MandatoryTextBox) referenzName).isValid()) {
				valid = false;
				validationFailures.add(MESSAGES.editCaptionRasterBasisReferenceNameMissing());
			}
			if (!reference.isFileSelected()) {
				valid = false;
				validationFailures.add(MESSAGES.editCaptionRasterBasisReferenceUrlMissing());
			}
		}
		else {
			if ((referenzName.getValue() == null || !(referenzName.getValue().length() > 0))
					&& !reference.isFileSelected()) {
				valid = false;
				validationFailures.add(MESSAGES.editCaptionRasterBasisReferenceNameOrUrl());
			}
		}
		if (artType.getValueAsEnum() != null && DOKUMENT.equals(artType.getValueAsEnum())) {
			if (georefMimeType.getValueAsEnum() != null) {
				valid = false;
				validationFailures.add(MESSAGES.editCaptionRasterBasisGeoReferenceNotAllowed());
			}
			if (georeference.isFileSelected()) {
				valid = false;
				validationFailures.add(MESSAGES.editCaptionRasterBasisGeoReferenceMimeTypeNotAllowed());
			}
		}
		if (includeReferences && !validateReferenceAndGeoreference(validationFailures)) {
			valid = false;
		}

		if (!datum.isValid()) {
			validationFailures.add(MESSAGES.editInvalidDate());
			valid = false;
		}
		showValidationError(validationFailures);

		return valid;
	}

	private class ClearValidationErrorsCH implements ChangeHandler {

		@Override
		public void onChange(ChangeEvent changeEvent) {
			validationErrors.setText("");
			validate(false);
		}

	}

	private class ClearValidationErrorsVCH<T> implements ValueChangeHandler<T> {

		@Override
		public void onValueChange(ValueChangeEvent<T> event) {
			validationErrors.setText("");
			validate(false);
		}

	}

}
