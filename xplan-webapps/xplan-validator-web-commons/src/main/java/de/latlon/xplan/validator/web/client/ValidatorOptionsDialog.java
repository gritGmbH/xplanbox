package de.latlon.xplan.validator.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.validator.web.client.report.ReportDialog;
import de.latlon.xplan.validator.web.client.report.ReportDownloadFinishedListener;
import de.latlon.xplan.validator.web.client.service.ValidationService;
import de.latlon.xplan.validator.web.client.service.ValidationServiceAsync;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationSummary;
import de.latlon.xplan.validator.web.shared.ValidationType;

import java.util.List;

import static de.latlon.xplan.validator.web.shared.ValidationType.GEOMETRIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SEMANTIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SYNTACTIC;

/**
 * Encapsulates a view with the settings for a validation run and a button to start the validation run.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ValidatorOptionsDialog extends FormPanel {

    private static final ValidatorWebCommonsMessages messages = GWT.create( ValidatorWebCommonsMessages.class );

    private final ValidationServiceAsync validationService = GWT.create( ValidationService.class );

    private final TextBox validationName = new TextBox();

    private final RadioButton validationTypeSyn = new RadioButton( "VALTYPE", messages.selectionValidationTypeSyn() );

    private final RadioButton validationTypeSem = new RadioButton( "VALTYPE", messages.selectionValidationTypeSem() );

    private final RadioButton validationTypeGeom = new RadioButton( "VALTYPE", messages.selectionValidationTypeGeom() );

    private final ExtendedOptionsPanel extendedOptions = new ExtendedOptionsPanel();

    private final ReportDownloadFinishedListener reportDownloadFinishedListener;

    private DialogBox validating;

    private String reportCloseButtonTitle;

    private String reportNextButtonTitle;

    /**
     * @param reportDownloadFinishedListener
     *                         informed when the validation report dialog is closed or next is clicked, never <code>null</code>
     */
    public ValidatorOptionsDialog( ReportDownloadFinishedListener reportDownloadFinishedListener ) {
        this( reportDownloadFinishedListener, messages.reportButtonCloseTitle(), messages.reportButtonNextTitle() );
    }

    /**
     * @param reportDownloadFinishedListener
     *                         informed when the validation report dialog is closed or next is clicked, never <code>null</code>
     * @param reportCloseButtonTitle
     *                         title of the close button in the report dialog
     * @param reportNextButtonTitle
     *                         title of the next button in the report dialog
     */
    public ValidatorOptionsDialog( ReportDownloadFinishedListener reportDownloadFinishedListener,
                                   String reportCloseButtonTitle, String reportNextButtonTitle ) {
        this.reportDownloadFinishedListener = reportDownloadFinishedListener;
        this.reportCloseButtonTitle = reportCloseButtonTitle;
        this.reportNextButtonTitle = reportNextButtonTitle;
        initFormFiels();
        initSettingsForm();
        this.setStyleName( "valOptionsForm" );
    }

    private void initSettingsForm() {
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setSpacing( 5 );
        mainPanel.setStyleName( "valOptionsPanel" );

        mainPanel.add( createLabel( messages.fieldLabelRunName() ) );
        mainPanel.add( validationName );
        mainPanel.add( createLabel( messages.selectionValidationTypeLabel() ) );
        mainPanel.add( validationTypeSem );
        mainPanel.add( validationTypeGeom );
        mainPanel.add( validationTypeSyn );
        mainPanel.add( createLabel( "Einstellungen" ) );
        mainPanel.add( extendedOptions );
        mainPanel.add( createValidationStartButton() );
        add( mainPanel );
    }

    private void initFormFiels() {
        validationName.setText( messages.defaultRunName() );
        validationTypeSyn.setTitle( messages.tooltipValidationTypeSyn() );
        validationTypeGeom.setTitle( messages.tooltipValidationTypeGeom() );
        validationTypeSem.setTitle( messages.tooltipValidationTypeSem() );
        validationTypeSem.setChecked( true );
    }

    private Label createLabel( String text ) {
        Label label = new Label( text );
        label.setStyleName( "valOptionLabel" );
        return label;
    }

    private Button createValidationStartButton() {
        return new Button( messages.startValidationButton(), new ClickHandler() {
            public void onClick( ClickEvent event ) {
                boolean validForm = validateForm();
                if ( validForm ) {
                    startValidation();
                }
            }
        } );
    }

    private HorizontalPanel createRow() {
        HorizontalPanel panel = new HorizontalPanel();
        panel.setHorizontalAlignment( HasHorizontalAlignment.ALIGN_CENTER );
        panel.setWidth( "400px" );
        panel.setHeight( "40px" );
        return panel;
    }

    private ValidationType retrieveValidationType() {
        if ( validationTypeSyn.isChecked() )
            return SYNTACTIC;
        if ( validationTypeGeom.isChecked() )
            return GEOMETRIC;
        if ( validationTypeSem.isChecked() )
            return SEMANTIC;
        return null;
    }

    private ValidationSettings createValidationSettings() {
        String name = validationName.getText();
        ValidationType validationType = retrieveValidationType();
        List<ValidationOption> options = extendedOptions.retrieveExtendedOptionsStatus();
        return new ValidationSettings( name, validationType, options );
    }

    private boolean validateForm() {
        boolean validForm = true;
        if ( "".equals( validationName.getText() ) ) {
            validForm = false;
            Window.alert( messages.correctInputText() );
        }
        if ( !validationTypeSyn.isChecked() && !validationTypeGeom.isChecked() && !validationTypeSem.isChecked() ) {
            Window.alert( messages.correctValidationType() );
            validForm = false;
        }
        return validForm;
    }

    private void startValidation() {
        showValidatingDialogBox();
        ValidationSettings validationSettings = createValidationSettings();
        validationService.validate( validationSettings, new AsyncCallback<ValidationSummary>() {

            @Override
            public void onSuccess( ValidationSummary validationSummary ) {
                hideValidatingDialogBox();
                ReportDialog reportDialog = new ReportDialog( validationSummary, reportCloseButtonTitle,
                                                              reportNextButtonTitle );
                reportDialog.addReportDownloadFinishedListener( reportDownloadFinishedListener );
                reportDialog.show();
            }

            @Override
            public void onFailure( Throwable caught ) {
                hideValidatingDialogBox();
                Window.alert( "Fehler bei der Validierung: " + caught.getMessage() );
            }
        } );
    }

    private void showValidatingDialogBox() {
        validating = new DialogBox( false, true );
        validating.setText( messages.validatingStatus() );
        validating.center();
        validating.show();
    }

    private void hideValidatingDialogBox() {
        if ( validating != null )
            validating.hide();
    }

}