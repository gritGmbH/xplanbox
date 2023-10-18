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
package de.latlon.xplan.manager.web.client.gui.editor.reference;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.EditDialogBoxWithRasterUpload;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.TypeCodeListBox;
import de.latlon.xplan.manager.web.client.gui.editor.text.TextDialog;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;

import java.util.ArrayList;
import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_60;

/**
 * Dialog to edit an existing or create a new {@link Reference}
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ReferenceDialog extends EditDialogBoxWithRasterUpload {

	private final TypeCodeListBox<ReferenceType> refType;

	/**
	 * Instantiates a {@link TextDialog} to create a new {@link Change}
	 */
	public ReferenceDialog(EditVersion version) {
		this(version, MESSAGES.editCaptionReferencesDialogNew());
	}

	private ReferenceDialog(EditVersion version, String title) {
		super(version, title);
		refType = createRefType(version);
		initDialog(createFormContent());
	}

	/**
	 * @return the actual edited {@link Reference}, may be <code>null</code>
	 */
	public Reference getReference() {
		Reference ref = new Reference();
		if (reference.isFileSelected()) {
			ref.setReference(reference.getFilename());
		}
		else {
			ref.setReference(referenceLink.getValue());
		}
		ref.setGeoReference(georeference.getFilename());
		ref.setType(refType.getValueAsEnum());
		if (XPLAN_60.equals(version)) {
			String referenzName = parseReferenzNameFromReferenzUrl();
			ref.setReferenzName(referenzName);
		}
		return ref;
	}

	@Override
	public boolean isValid() {
		boolean valid = super.isValid();
		String refLinkValue = this.referenceLink.getValue();
		List<String> validationFailures = new ArrayList<String>();
		if (isNullOrEmpty(refLinkValue) && !this.reference.isFileSelected()) {
			valid = false;
			validationFailures.add(MESSAGES.editCaptionReferenceUrlOrFile());
		}
		else if (!isNullOrEmpty(refLinkValue) && this.reference.isFileSelected()) {
			valid = false;
			validationFailures.add(MESSAGES.editCaptionRasterBasisReferenceNameOrUrl());
		}
		return valid;
	}

	@Override
	protected boolean isReferenceUrlMandatory() {
		return false;
	}

	private Widget createFormContent() {
		FlexTable layout = new FlexTable();
		FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
		formatter.setHorizontalAlignment(1, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(2, 1, ALIGN_LEFT);

		layout.setWidget(1, 1, new Label(MESSAGES.editCaptionReferencesReference()));
		layout.setWidget(1, 2, reference);
		layout.setWidget(2, 1, new Label(MESSAGES.editCaptionReferencesReferenceLink()));
		layout.setWidget(2, 2, referenceLink);
		// #3305 - georeference is not needed.
		// layout.setWidget( 2, 1, new Label( MESSAGES.editCaptionReferencesGeoReference()
		// ) );
		// layout.setWidget( 2, 2, georeference );
		layout.setWidget(3, 1, new Label(MESSAGES.editCaptionReferencesType()));
		layout.setWidget(3, 2, refType);

		return layout;
	}

	private TypeCodeListBox<ReferenceType> createRefType(EditVersion version) {
		List<ReferenceType> unsupportedReferenceTypes = new ArrayList<ReferenceType>();
		for (ReferenceType referenceType : ReferenceType.values()) {
			if (!referenceType.isXPlanVersionSupported(version.name()))
				unsupportedReferenceTypes.add(referenceType);
		}
		return new TypeCodeListBox<ReferenceType>(ReferenceType.class, unsupportedReferenceTypes, false);
	}

}
