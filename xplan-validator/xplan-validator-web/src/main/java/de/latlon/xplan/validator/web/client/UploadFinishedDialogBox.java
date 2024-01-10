/*-
 * #%L
 * xplan-validator-web - Modul zur Gruppierung aller Webapps
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
package de.latlon.xplan.validator.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplanbox.core.gwt.commons.client.ValidatorOptionsDialog;
import de.latlon.xplanbox.core.gwt.commons.client.ValidatorWebCommonsMessages;
import de.latlon.xplanbox.core.gwt.commons.client.report.ReportDownloadFinishedListener;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationConfigService;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationConfigServiceAsync;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationConfig;

import static de.latlon.xplanbox.core.gwt.commons.client.report.ReportDownloadFinishedListener.FinishStatus.NEXT;

/**
 * Extends the {@link DialogBox} with a button to close the dialog
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
class UploadFinishedDialogBox extends DialogBox {

	private final ValidatorWebCommonsMessages messages = GWT.create( ValidatorWebCommonsMessages.class);

	private final ValidationConfigServiceAsync validationConfigService = GWT.create(ValidationConfigService.class);

	private final String fileName;

	/**
	 * @param xPlanValidatorWeb never <code>null</code>
	 * @param htmlMessage to show as content
	 */
	public UploadFinishedDialogBox(XPlanValidatorWeb xPlanValidatorWeb, String htmlMessage, String fileName) {
		super(false);
		this.fileName = fileName;
		setText(messages.uploadSucessTitle());
		initDialog(xPlanValidatorWeb, htmlMessage);
	}

	private void initDialog(XPlanValidatorWeb xPlanValidatorWeb, String htmlMessage) {
		HTML html = new HTML(htmlMessage);
		SimplePanel contentPanel = new SimplePanel(html);
		VerticalPanel dialogBoxContent = new VerticalPanel();
		dialogBoxContent.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		dialogBoxContent.add(contentPanel);
		createAndAddCloseButton(dialogBoxContent, xPlanValidatorWeb);
		setWidget(dialogBoxContent);
	}

	private void createAndAddCloseButton(VerticalPanel dialogBoxContent, XPlanValidatorWeb xPlanValidatorWeb) {
		Panel holder = createButtonsPanel(xPlanValidatorWeb);
		dialogBoxContent.add(holder);
	}

	private Panel createButtonsPanel(XPlanValidatorWeb xPlanValidatorWeb) {
		Button nextButton = createNextButton(xPlanValidatorWeb);
		Button cancelButton = createCancelButton();

		HorizontalPanel buttonsPanel = new HorizontalPanel();
		buttonsPanel.setSpacing(20);
		buttonsPanel.add(cancelButton);
		buttonsPanel.add(nextButton);
		return buttonsPanel;
	}

	private Button createNextButton(final XPlanValidatorWeb xPlanValidatorWeb) {
		Button nextButton = new Button();
		nextButton.setText(messages.uploadFinishedNextButton());
		ClickHandler nextListener = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				validationConfigService.retrieveValidationConfig(new AsyncCallback<ValidationConfig>() {
					@Override
					public void onFailure(Throwable throwable) {
						Window.alert("Profile konnten nicht abgerufen werden: " + throwable.getMessage());
						validate(new ValidationConfig());
					}

					@Override
					public void onSuccess(ValidationConfig validationConfig) {
						validate(validationConfig);
					}

					private void validate(ValidationConfig validationConfig) {
						UploadFinishedDialogBox.this.hide();
						ClickHandler cancelHandler = new ClickHandler() {
							@Override
							public void onClick(ClickEvent clickEvent) {
								xPlanValidatorWeb.resetPanelToUpload();
							}
						};
						ValidatorOptionsDialog xPlanValidatorSettings = new ValidatorOptionsDialog( validationConfig,
                                                                                                    new ReportDownloadFinishedListener() {
									@Override
									public void downloadFinished(FinishStatus finishStatus) {
										if (NEXT.equals(finishStatus))
											xPlanValidatorWeb.resetPanelToUpload();
									}

								}, fileName, true, cancelHandler, true);
						xPlanValidatorWeb.setPanel(xPlanValidatorSettings);
					}
				});

			}
		};
		nextButton.addClickHandler(nextListener);
		return nextButton;
	}

	private Button createCancelButton() {
		Button nextButton = new Button();
		nextButton.setText(messages.uploadFinishedCancelButton());
		ClickHandler cancelListener = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				UploadFinishedDialogBox.this.hide();
			}
		};
		nextButton.addClickHandler(cancelListener);
		return nextButton;
	}

}
