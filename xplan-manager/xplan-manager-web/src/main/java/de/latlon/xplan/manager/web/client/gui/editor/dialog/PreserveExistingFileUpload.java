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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

/**
 * Encapsulates a {@link Label} to show the currently existing file and a
 * {@link FileUpload} to allow the upload of a new file replacing the existing one.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class PreserveExistingFileUpload extends VerticalPanel implements Validable {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private final FileUpload selectedFile;

	private final Button removeButton;

	private final boolean isMandatory;

	private final Label existingFile = new Label();

	private boolean isNewFileUploaded = false;

	/**
	 * @param nameOfTheFileUploadField the name of the {@link FileUpload} element used to
	 * upload a new file, never <code>null</code>
	 * @param isMandatory <code>true</code> if a delete button should be added to remove a
	 * selected file, <code>false</code> otherwise
	 */
	public PreserveExistingFileUpload(String nameOfTheFileUploadField, boolean isMandatory) {
		this.isMandatory = isMandatory;
		this.selectedFile = createFileUpload(nameOfTheFileUploadField);
		removeButton = createRemoveButton();
		initPanel();
	}

	/**
	 * @param nameOfExistingFile the name of the file already existing, may be
	 * <code>null</code> if no file exists
	 */
	public void setNameOfExistingFile(String nameOfExistingFile) {
		existingFile.setText(nameOfExistingFile);
	}

	/**
	 * @return the name of the file, this may be the one of the existing or selected file
	 * for upload, <code>null</code> if no file exists or is selected
	 */
	public String getFilename() {
		return existingFile.getText();
	}

	@Override
	public boolean isValid() {
		if (isMandatory) {
			return isFileSelected();
		}
		return true;
	}

	/**
	 * @return true if a file is selected, false otherwise
	 */
	public boolean isFileSelected() {
		String filename = getFilename();
		return filename != null && filename.length() > 0;
	}

	/**
	 * @param changeHandler the ChangeHandler to add
	 */
	public void addChangeHandler(ChangeHandler changeHandler) {
		selectedFile.addChangeHandler(changeHandler);
		selectedFile.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent changeEvent) {
				isNewFileUploaded = true;
			}
		});
	}

	/**
	 * @return <code>true</code> if a new file was uploaded, <code>false</code> otherwise
	 */
	public boolean isNewFileUploaded() {
		return isNewFileUploaded;
	}

	private void initPanel() {
		HorizontalPanel existingPanel = new HorizontalPanel();
		existingPanel.setSpacing(10);
		existingPanel.add(new Label(MESSAGES.editCaptionReferencesCurrentFile()));
		existingPanel.add(existingFile);
		if (!isMandatory)
			existingPanel.add(removeButton);
		add(existingPanel);
		add(selectedFile);
	}

	private Button createRemoveButton() {
		Button button = new Button();
		button.setTitle(MESSAGES.editCaptionReferencesRemoveFileTooltip());
		button.setStyleName("removeButtonColumn");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				existingFile.setText(null);
			}
		});
		return button;
	}

	private FileUpload createFileUpload(String name) {
		FileUpload fileUpload = new FileUpload();
		fileUpload.setName(name);
		fileUpload.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				String fileName = retrieveFileName();
				setNameOfExistingFile(fileName);
			}
		});
		return fileUpload;
	}

	private String retrieveFileName() {
		String fileName = selectedFile.getFilename();
		if (fileName.contains("\\"))
			return fileName.substring(fileName.lastIndexOf("\\") + 1);
		if (fileName.contains("/"))
			return fileName.substring(fileName.lastIndexOf("/") + 1);
		return fileName;
	}

}
