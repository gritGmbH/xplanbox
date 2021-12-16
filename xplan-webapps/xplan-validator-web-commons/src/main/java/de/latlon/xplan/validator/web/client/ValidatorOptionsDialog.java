/*-
 * #%L
 * xplan-validator-web-commons - Modul zur Gruppierung aller Webapps
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.validator.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.validator.web.client.report.ReportDialog;
import de.latlon.xplan.validator.web.client.report.ReportDownloadFinishedListener;
import de.latlon.xplan.validator.web.client.service.MapPreviewConfigService;
import de.latlon.xplan.validator.web.client.service.MapPreviewConfigServiceAsync;
import de.latlon.xplan.validator.web.client.service.ValidationService;
import de.latlon.xplan.validator.web.client.service.ValidationServiceAsync;
import de.latlon.xplan.validator.web.shared.MapPreviewMetadata;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationSummary;
import de.latlon.xplan.validator.web.shared.ValidationType;

import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.validator.web.shared.ValidationType.GEOMETRIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SEMANTIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SYNTACTIC;

/**
 * Encapsulates a view with the settings for a validation run and a button to start the
 * validation run.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ValidatorOptionsDialog extends FormPanel {

	private static final ValidatorWebCommonsMessages messages = GWT.create(ValidatorWebCommonsMessages.class);

	private static final String SKIP_FLAECHENSCHLUSS = "skip-flaechenschluss";

	private static final String SKIP_GELTUNGSBEREICH = "skip-geltungsbereich";

	private final ValidationServiceAsync validationService = GWT.create(ValidationService.class);

	private final MapPreviewConfigServiceAsync mapPreviewConfigService = GWT.create(MapPreviewConfigService.class);

	private final TextBox validationName = new TextBox();

	private final CheckBox validationTypeSyn = new CheckBox(messages.selectionValidationTypeSyn());

	private final CheckBox validationTypeSem = new CheckBox(messages.selectionValidationTypeSem());

	private final CheckBox validationTypeGeom = new CheckBox(messages.selectionValidationTypeGeom());

	private CheckBox skipFlaechenschluss = new CheckBox(messages.skipFlaechenschluss());

	private CheckBox skipGeltungsbereich = new CheckBox(messages.skipGeltungsbereich());

	private final ReportDownloadFinishedListener reportDownloadFinishedListener;

	private DialogBox validating;

	private String reportCloseButtonTitle;

	private String reportNextButtonTitle;

	private boolean showMapPreview = false;

	/**
	 * @param reportDownloadFinishedListener informed when the validation report dialog is
	 * closed or next is clicked, never <code>null</code>
	 * @param fileName name of the file to validate, may be <code>null</code>
	 * @param showMapPreview <code>true</code> if map preview is enabled,
	 * <code>false</code> otherwise
	 * @param cancelHandler triggered when options dialog is canceled, never
	 * <code>null</code>
	 * @param enabledGeomValidation <code>true</code> if the geometrische validation can
	 * be activated/deactivated, <code>false</code> otherwise
	 */
	public ValidatorOptionsDialog(ReportDownloadFinishedListener reportDownloadFinishedListener, String fileName,
			boolean showMapPreview, ClickHandler cancelHandler, boolean enabledGeomValidation) {
		this(reportDownloadFinishedListener, messages.reportButtonCloseTitle(), messages.reportButtonNextTitle(),
				fileName, cancelHandler, enabledGeomValidation);
		this.showMapPreview = showMapPreview;
	}

	/**
	 * @param reportDownloadFinishedListener informed when the validation report dialog is
	 * closed or next is clicked, never <code>null</code>
	 * @param reportCloseButtonTitle title of the close button in the report dialog
	 * @param reportNextButtonTitle title of the next button in the report dialog
	 * @param fileName name of the file to validate, may be <code>null</code>
	 * @param cancelHandler triggered when options dialog is canceled, never
	 * <code>null</code>
	 * @param enabledGeomValidation <code>true</code> if the geometrische validation can
	 * be activated/deactivated, <code>false</code> otherwise
	 */
	public ValidatorOptionsDialog(ReportDownloadFinishedListener reportDownloadFinishedListener,
			String reportCloseButtonTitle, String reportNextButtonTitle, String fileName, ClickHandler cancelHandler,
			boolean enabledGeomValidation) {
		this.reportDownloadFinishedListener = reportDownloadFinishedListener;
		this.reportCloseButtonTitle = reportCloseButtonTitle;
		this.reportNextButtonTitle = reportNextButtonTitle;
		initFormFields(fileName, enabledGeomValidation);
		initSettingsForm(cancelHandler);
		this.setStyleName("valOptionsForm");
	}

	private void initSettingsForm(ClickHandler cancelHandler) {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setSpacing(5);
		mainPanel.setStyleName("valOptionsPanel");
		mainPanel.setWidth("450px");

		mainPanel.add(createTitel());
		mainPanel.add(createLabel(messages.fieldLabelRunName()));
		mainPanel.add(validationName);
		mainPanel.add(createLabel(messages.selectionValidationTypeLabel()));
		mainPanel.add(validationTypeSem);
		mainPanel.add(validationTypeGeom);
		mainPanel.add(skipFlaechenschluss);
		mainPanel.add(skipGeltungsbereich);
		mainPanel.add(validationTypeSyn);
		mainPanel.add(createButtonsPanel(cancelHandler));
		add(mainPanel);
	}

	private void initFormFields(String fileName, boolean enabledGeomValidation) {
		validationName.setText(fileName != null && !fileName.isEmpty() ? fileName : messages.defaultRunName());
		validationName.setWidth("400px");
		validationTypeSyn.setEnabled(false);
		validationTypeSyn.setValue(true);
		validationTypeSem.setValue(true);
		validationTypeGeom.setEnabled(enabledGeomValidation);
		validationTypeGeom.setValue(true);

		skipFlaechenschluss.setStyleName("valOption");
		skipGeltungsbereich.setStyleName("valOption");
	}

	private Label createTitel() {
		Label label = new Label(messages.validationOptionTitle());
		label.setStyleName("valOptionTitle");
		return label;
	}

	private Label createLabel(String text) {
		Label label = new Label(text);
		label.setStyleName("valOptionLabel");
		return label;
	}

	private Panel createButtonsPanel(ClickHandler cancelHandler) {
		HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setSpacing(20);
		buttonBar.add(createValidateCancelButton(cancelHandler));
		buttonBar.add(createValidationStartButton());
		return buttonBar;
	}

	private Button createValidationStartButton() {
		return new Button(messages.startValidationButton(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				boolean validForm = validateForm();
				if (validForm) {
					startValidation();
				}
			}
		});
	}

	private Button createValidateCancelButton(ClickHandler cancelHandler) {
		return new Button(messages.cancelValidationButton(), cancelHandler);
	}

	private List<ValidationType> retrieveValidationTypes() {
		List<ValidationType> validationTypes = new ArrayList<ValidationType>();
		if (validationTypeSyn.getValue())
			validationTypes.add(SYNTACTIC);
		if (validationTypeGeom.getValue())
			validationTypes.add(GEOMETRIC);
		if (validationTypeSem.getValue())
			validationTypes.add(SEMANTIC);
		return validationTypes;
	}

	private ValidationSettings createValidationSettings() {
		String name = validationName.getText();
		List<ValidationType> validationType = retrieveValidationTypes();
		List<ValidationOption> options = new ArrayList<ValidationOption>();
		if (skipFlaechenschluss.getValue())
			options.add(new ValidationOption(SKIP_FLAECHENSCHLUSS, Boolean.TRUE.toString()));
		if (skipGeltungsbereich.getValue())
			options.add(new ValidationOption(SKIP_GELTUNGSBEREICH, Boolean.TRUE.toString()));
		return new ValidationSettings(name, validationType, options);
	}

	private boolean validateForm() {
		boolean validForm = true;
		if ("".equals(validationName.getText())) {
			validForm = false;
			Window.alert(messages.correctInputText());
		}
		if (!validationTypeSyn.isChecked() && !validationTypeGeom.isChecked() && !validationTypeSem.isChecked()) {
			Window.alert(messages.correctValidationType());
			validForm = false;
		}
		return validForm;
	}

	private void startValidation() {
		showValidatingDialogBox();
		ValidationSettings validationSettings = createValidationSettings();
		final ReportDialog reportDialog = new ReportDialog();
		validationService.validate(validationSettings, new AsyncCallback<ValidationSummary>() {

			@Override
			public void onSuccess(ValidationSummary validationSummary) {
				hideValidatingDialogBox();
				reportDialog.init(validationSummary, reportCloseButtonTitle, reportNextButtonTitle, showMapPreview);
				reportDialog.addReportDownloadFinishedListener(reportDownloadFinishedListener);
				reportDialog.show();
			}

			@Override
			public void onFailure(Throwable caught) {
				hideValidatingDialogBox();
				Window.alert("Fehler bei der Validierung: " + caught.getMessage());
			}
		});
		mapPreviewConfigService.isMapPreviewAvaialable(new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean isMapPreviewAvailable) {
				if (isMapPreviewAvailable) {
					mapPreviewConfigService.createMapPreviewConfig(new AsyncCallback<MapPreviewMetadata>() {

						@Override
						public void onSuccess(MapPreviewMetadata mapPreviewMetadata) {
							reportDialog.setMapPreviewMetadata(mapPreviewMetadata);
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler beim erstellen der Konfiguration der Kartenvorschau: "
									+ caught.getMessage());
						}
					});
				}
			}

			@Override
			public void onFailure(Throwable throwable) {
				// nothing to do
			}
		});
	}

	private void showValidatingDialogBox() {
		validating = new DialogBox(false, true);
		validating.setText(messages.validatingStatus());
		validating.center();
		validating.show();
	}

	private void hideValidatingDialogBox() {
		if (validating != null)
			validating.hide();
	}

}
