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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.client.utils.ReferenceNameUtils;

import java.util.List;

import static de.latlon.xplan.manager.web.client.gui.utils.ValidationUtils.areComponentsValid;

/**
 * Extends the {@link EditDialogBox} with a two {@link PreserveExistingFileUpload} gui
 * elements to allow the user to select a reference and geo reference.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public abstract class EditDialogBoxWithRasterUpload extends EditDialogBox implements Validable {

	protected final PreserveExistingFileUpload reference = new PreserveExistingFileUpload("referenceArtefact",
			isReferenceUrlMandatory());

	protected final PreserveExistingFileUpload georeference = new PreserveExistingFileUpload("geoReferenceArtefact",
			isGeoreferenceUrlMandatory());

	protected final UrlTextBox referenceLink = createReferenceLink();

	final FormPanel form = new FormPanel();

	private final DialogBox uploading = createUploadingDialogBox();

	protected EditVersion version;

	/**
	 * @param title the title of the dialog, never <code>null</code>
	 */
	public EditDialogBoxWithRasterUpload(EditVersion version, String title) {
		super(title);
		this.version = version;
	}

	@Override
	protected void initDialog(Widget contentPanel) {
		super.initDialog(createFormPanel(contentPanel));
	}

	@Override
	protected void save() {
		if (isValid()) {
			showUploadingDialogBox();
			form.submit();
		}
		else {
			Window.alert(MESSAGES.editInvalidInput());
		}
	}

	@Override
	public boolean isValid() {
		return areComponentsValid(reference, georeference, referenceLink);
	}

	protected Widget createFormPanel(Widget contentPanel) {
		form.setAction(GWT.getHostPageBaseURL() + GWT.getModuleName() + "/rest/manager/edit/plan/artefact");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
				if (event.getResults().contains("Content type"))
					Window.alert(MESSAGES.uploadSecurityException());
				else
					informSaveHandler();
				uploading.hide();
			}
		});

		form.add(contentPanel);
		return form;
	}

	/**
	 * Override this to allow optional reference URLs.
	 * @return <code>true</code> if the referenceUrl is mandatory, <code>false</code>
	 * otherwise
	 */
	protected boolean isReferenceUrlMandatory() {
		return true;
	}

	/**
	 * Override this to allow optional reference URLs.
	 * @return <code>true</code> if the referenceUrl is mandatory, <code>false</code>
	 * otherwise
	 */
	protected boolean isGeoreferenceUrlMandatory() {
		return false;
	}

	protected boolean validateReferenceAndGeoreference(List<String> validationFailures) {
		boolean newReference = reference.isNewFileUploaded();
		boolean newGeoreference = georeference.isNewFileUploaded();
		String referenceName = reference.getFilename();
		String georeferenceName = georeference.getFilename();
		boolean isValid = true;
		if (newReference) {
			if (newGeoreference) {
				if (notTheSameName(referenceName, georeferenceName)) {
					validationFailures.add(MESSAGES.rasterNameAndGeoreferencNameNotSame());
					isValid = false;
				}
			}
			else {
				if (georeferenceName == null || georeferenceName.isEmpty()) {
					return true;
				}
				else {
					validationFailures.add(MESSAGES.rasterAndGeoreferencNotChanged());
					isValid = false;
				}
			}
		}
		else {
			if (!newGeoreference) {
				return true;
			}
			else {
				validationFailures.add(MESSAGES.rasterAndGeoreferencNotChanged());
				isValid = false;
			}
		}
		return isValid;
	}

	protected boolean isNullOrEmpty(String value) {
		return value == null || value.isEmpty();
	}

	protected String parseReferenzNameFromReferenzUrl() {
		if (reference.isFileSelected()) {
			return ReferenceNameUtils.extractFilenameFromFile(reference.getFilename());
		}
		return ReferenceNameUtils.extractFilenameFromUrl(referenceLink.getValue());
	}

	private boolean notTheSameName(String referenceName, String georeferenceFilename) {
		String referenceNameWithoutSuffix = referenceName.substring(0, referenceName.lastIndexOf("."));
		String georeferenceNameWithoutSuffix = georeferenceFilename.substring(0, georeferenceFilename.lastIndexOf("."));
		return !referenceNameWithoutSuffix.equals(georeferenceNameWithoutSuffix);
	}

	private DialogBox createUploadingDialogBox() {
		DialogBox uploading = new DialogBox(false, true);
		uploading.setText(MESSAGES.editingUploading());
		return uploading;
	}

	private void showUploadingDialogBox() {
		uploading.center();
		uploading.show();
	}

	private UrlTextBox createReferenceLink() {
		UrlTextBox textBox = new UrlTextBox();
		textBox.setWidth("200px");
		return textBox;
	}

}
