/*-
 * #%L
 * xplan-core-gwt - Modul zur Gruppierung von GWT Komponenten
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
package de.latlon.xplanbox.core.gwt.commons.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.regexp.shared.RegExp;
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
import de.latlon.xplanbox.core.gwt.commons.client.report.ReportDialog;
import de.latlon.xplanbox.core.gwt.commons.client.report.ReportDownloadFinishedListener;
import de.latlon.xplanbox.core.gwt.commons.client.service.MapPreviewConfigService;
import de.latlon.xplanbox.core.gwt.commons.client.service.MapPreviewConfigServiceAsync;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationService;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationServiceAsync;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationConfig;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationSummary;
import de.latlon.xplan.validator.web.shared.MapPreviewMetadata;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationProfile;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;

import java.util.ArrayList;
import java.util.Comparator;
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

	private static final String SKIP_LAUFRICHTUNG = "skip-laufrichtung";

	private final ValidationServiceAsync validationService = GWT.create(ValidationService.class);

	private final MapPreviewConfigServiceAsync mapPreviewConfigService = GWT.create(MapPreviewConfigService.class);

	private final TextBox validationName = new TextBox();

	private final CheckBox validationTypeSyn = new CheckBox(messages.selectionValidationTypeSyn());

	private final CheckBox validationTypeSem = new CheckBox(messages.selectionValidationTypeSem());

	private final CheckBox validationTypeGeom = new CheckBox(messages.selectionValidationTypeGeom());

	private CheckBox skipFlaechenschluss = new CheckBox(messages.skipFlaechenschluss());

	private CheckBox skipGeltungsbereich = new CheckBox(messages.skipGeltungsbereich());

	private CheckBox skipLaufrichtung = new CheckBox(messages.skipLaufrichtung());

	private List<CheckBox> profileCheckBoxes = new ArrayList<>();

	private final ReportDownloadFinishedListener reportDownloadFinishedListener;

	private DialogBox validating;

	private String reportCloseButtonTitle;

	private String reportNextButtonTitle;

	private ValidationConfig validationConfig;

	private boolean showMapPreview = false;

	private PollingTextBox pollingTextBox;

	/**
	 * @param validationConfig the validation config, never <code>null</code>
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
	public ValidatorOptionsDialog(ValidationConfig validationConfig,
			ReportDownloadFinishedListener reportDownloadFinishedListener, String fileName, boolean showMapPreview,
			ClickHandler cancelHandler, boolean enabledGeomValidation) {
		this(validationConfig, reportDownloadFinishedListener, messages.reportButtonCloseTitle(),
				messages.reportButtonNextTitle(), fileName, cancelHandler, enabledGeomValidation);
		this.showMapPreview = showMapPreview;
	}

	/**
	 * @param validationConfig the validation config, never <code>null</code>
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
	public ValidatorOptionsDialog(ValidationConfig validationConfig,
			ReportDownloadFinishedListener reportDownloadFinishedListener, String reportCloseButtonTitle,
			String reportNextButtonTitle, String fileName, ClickHandler cancelHandler, boolean enabledGeomValidation) {
		this.validationConfig = validationConfig;
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
		mainPanel.add(skipLaufrichtung);
		mainPanel.add(validationTypeSyn);
		if (!profileCheckBoxes.isEmpty()) {
			mainPanel.add(createLabel(messages.selectionProfileLabel()));
			for (CheckBox profileCheckBox : profileCheckBoxes) {
				mainPanel.add(profileCheckBox);
			}
		}
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
		skipLaufrichtung.setStyleName("valOption");
		if (validationConfig != null && !validationConfig.getProfiles().isEmpty()) {
			validationConfig.getProfiles().sort(new Comparator<ValidationProfile>() {
				@Override
				public int compare(ValidationProfile o1, ValidationProfile o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (ValidationProfile profile : validationConfig.getProfiles()) {
				CheckBox checkBox = new CheckBox(profile.getName());
				checkBox.setTitle(profile.getDescription());
				checkBox.setName(profile.getId().toString());
				profileCheckBoxes.add(checkBox);
			}
		}
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
		if (skipLaufrichtung.getValue())
			options.add(new ValidationOption(SKIP_LAUFRICHTUNG, Boolean.TRUE.toString()));
		List<String> selectedProfiles = new ArrayList<String>();
		for (CheckBox profileCheckBox : profileCheckBoxes) {
			if (profileCheckBox.getValue()) {
				selectedProfiles.add(profileCheckBox.getName());
			}
		}
		return new ValidationSettings(name, validationType, selectedProfiles, options);
	}

	private boolean validateForm() {
		boolean validForm = true;
		if (!validationNameIsValid(validationName.getText())) {
			validForm = false;
			Window.alert(messages.correctValidationName());
		}
		if (!validationTypeSyn.getValue() && !validationTypeGeom.getValue() && !validationTypeSem.getValue()) {
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
		mapPreviewConfigService.isMapPreviewAvailable(new AsyncCallback<Boolean>() {
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
							Window.alert("Fehler beim Erstellen der Konfiguration der Kartenvorschau: "
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
		pollingTextBox = new PollingTextBox(validationService);
		pollingTextBox.setReadOnly(true);
		pollingTextBox.setValue(".");
		validating.add(pollingTextBox);
		validating.center();
		validating.show();
	}

	private void hideValidatingDialogBox() {
		if (validating != null) {
			validating.hide();
			pollingTextBox.stop();
		}
	}

	private boolean validationNameIsValid(String validationName) {
		if (validationName == null || "".equals(validationName))
			return false;
		RegExp regExp = RegExp.compile("^[A-Za-z0-9.()_-]*$");
		return regExp.test(validationName);
	}

}
