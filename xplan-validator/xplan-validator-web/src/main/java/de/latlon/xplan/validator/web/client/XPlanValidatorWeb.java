/*-
 * #%L
 * xplan-validator-web - Webanwendung XPlanValidatorWeb
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
package de.latlon.xplan.validator.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplanbox.core.gwt.commons.client.ValidatorWebCommonsMessages;
import de.latlon.xplanbox.core.gwt.commons.web.CloseableDialogBox;

/**
 * Entry point of the validation, containing the file upload and a button to navigate to
 * the options view.
 *
 * @author <a href="mailto:wilden@lat-lon.de">Johannes Wilden</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanValidatorWeb implements EntryPoint {

	private final ValidatorWebCommonsMessages messages = GWT.create(ValidatorWebCommonsMessages.class);

	private DialogBox uploading;

	@Override
	public void onModuleLoad() {
		Cookies.setCookie("JSESSIONID", "empty");
		resetPanelToUpload();
	}

	/**
	 * replaces the current content with the passed panel
	 * @param panel never <code>null</code>
	 */
	public void setPanel(Panel panel) {
		RootPanel rootPanel = detectContentPanel();
		rootPanel.clear();
		Panel panelWithHelp = createPanelWithHelp(panel);
		rootPanel.add(panelWithHelp);
	}

	private RootPanel detectContentPanel() {
		RootPanel contentPanel = RootPanel.get("content");
		if (contentPanel != null)
			return contentPanel;
		return RootPanel.get();
	}

	/**
	 * Shows the upload panel
	 */
	public void resetPanelToUpload() {
		FormPanel formPanel = createFormPanel();
		setPanel(formPanel);
	}

	private FormPanel createFormPanel() {
		FormPanel form = new FormPanel();
		form.setAction(GWT.getModuleBaseURL() + "upload");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		FileUpload uploadItem = createUploadItem();
		Panel uploadPanel = createUploadPanel(uploadItem);
		Panel openButtonPanel = createOpenButtonPanel(form, uploadItem);

		addFormSubmitHandler(form, uploadItem);
		addFormSubmitCompleteHandler(form, uploadItem);

		VerticalPanel mainPanel = createMainPanel(uploadPanel, openButtonPanel);
		form.add(mainPanel);
		return form;
	}

	private VerticalPanel createPanelWithHelp(Panel panel) {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setWidth("100%");
		mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		Button helpLink = new Button(messages.openUserManual());
		helpLink.setStyleName("helpBt");
		helpLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				Window.open("XPlanValidatorWeb-Benutzerhandbuch/index-xPlanValidator.html", "_blank", "");
			}
		});
		mainPanel.add(helpLink);
		mainPanel.add(panel);
		return mainPanel;
	}

	private VerticalPanel createMainPanel(Panel uploadPanel, Panel openButtonPanel) {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		mainPanel.setWidth("100%");
		mainPanel.add(uploadPanel);
		mainPanel.add(openButtonPanel);
		return mainPanel;
	}

	private Panel createUploadPanel(FileUpload uploadItem) {
		Label uploadLabel = new Label(messages.uploadLabel());

		HorizontalPanel uploadPanel = new HorizontalPanel();
		layoutPanel(uploadPanel);
		uploadPanel.add(uploadLabel);
		uploadPanel.add(uploadItem);

		return uploadPanel;
	}

	private FileUpload createUploadItem() {
		FileUpload upload = new FileUpload();
		upload.setName("uploadPlanItem");
		return upload;
	}

	private Panel createOpenButtonPanel(final FormPanel form, final FileUpload upload) {
		SimplePanel buttonPanel = new SimplePanel();
		layoutPanel(buttonPanel);

		Button openOptionsButton = new Button(messages.validationOptionsOpen(), new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (!isSupportedFileType())
					showInvalidFile(messages.fileNameMustEndWithZip());
				else if (!isValidFileName())
					showInvalidFile(messages.fileNameInvalidCharacters());
				else {
					form.submit();
					showUploadDialogBox();
				}
			}

			private boolean isSupportedFileType() {
				String fileName = upload.getFilename().toLowerCase();
				return fileName.endsWith(".zip") || fileName.endsWith(".xml") || fileName.endsWith(".gml");
			}

			private boolean isValidFileName() {
				String fileName = getFilename(upload);
				return fileName.matches("[a-zA-Z0-9.()_-]*");
			}

			private void showInvalidFile(String message) {
				DialogBox errorUpload = new CloseableDialogBox(messages.errorTitle(), message);
				errorUpload.center();
				errorUpload.show();
			}

			private void showUploadDialogBox() {
				uploading = new DialogBox(false, true);
				uploading.setText(messages.uploadingFile());
				uploading.center();
				uploading.show();
			}

		});

		buttonPanel.add(openOptionsButton);
		return buttonPanel;

	}

	private void layoutPanel(Panel panel) {
		panel.setHeight("50px");
	}

	private void addFormSubmitHandler(FormPanel form, final FileUpload uploadItem) {
		form.addSubmitHandler(new FormPanel.SubmitHandler() {
			@Override
			public void onSubmit(SubmitEvent event) {
				if (uploadItem.getFilename().length() == 0) {
					uploading.hide();
					Window.alert(messages.uploadFailed());
					event.cancel();
				}
			}
		});
	}

	private void addFormSubmitCompleteHandler(FormPanel form, final FileUpload uploadItem) {
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				uploading.hide();
				if (event.getResults().contains("java.io.IOException"))
					Window.alert(messages.uploadSecurityException());
				else
					showSuccessfulUploadedDialog(event);
			}

			private void showSuccessfulUploadedDialog(SubmitCompleteEvent event) {
				String filename = getFilename(uploadItem);
				UploadFinishedDialogBox dialogBox = new UploadFinishedDialogBox(XPlanValidatorWeb.this,
						event.getResults(), filename);
				dialogBox.center();
				dialogBox.show();
			}

		});
	}

	private String getFilename(FileUpload uploadItem) {
		try {
			String filename = uploadItem.getFilename();
			int indexOfSep = filename.lastIndexOf("\\") + 1;
			filename = filename.substring(indexOfSep);
			int indexOfPref = filename.lastIndexOf(".");
			filename = filename.substring(0, indexOfPref);
			return filename;
		}
		catch (Exception e) {
			return null;
		}
	}

}
