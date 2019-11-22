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
import de.latlon.xplan.validator.web.client.service.MapPreviewConfigService;
import de.latlon.xplan.validator.web.client.service.MapPreviewConfigServiceAsync;
import de.latlon.xplan.validator.web.client.service.ValidationService;
import de.latlon.xplan.validator.web.client.service.ValidationServiceAsync;
import de.latlon.xplan.validator.web.shared.MapPreviewMetadata;
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

    private final MapPreviewConfigServiceAsync mapPreviewConfigService = GWT.create( MapPreviewConfigService.class );

    private final TextBox validationName = new TextBox();

    private final RadioButton validationTypeSyn = new RadioButton( "VALTYPE", messages.selectionValidationTypeSyn() );

    private final RadioButton validationTypeSem = new RadioButton( "VALTYPE", messages.selectionValidationTypeSem() );

    private final RadioButton validationTypeGeom = new RadioButton( "VALTYPE", messages.selectionValidationTypeGeom() );

    private final ExtendedOptionsPanel extendedOptions = new ExtendedOptionsPanel();

    private final ReportDownloadFinishedListener reportDownloadFinishedListener;

    private DialogBox validating;

    private String reportCloseButtonTitle;

    private String reportNextButtonTitle;

    private boolean showMapPreview = false;

    /**
     * @param reportDownloadFinishedListener
     *                         informed when the validation report dialog is closed or next is clicked, never <code>null</code>
     */
    public ValidatorOptionsDialog( ReportDownloadFinishedListener reportDownloadFinishedListener, String fileName, boolean showMapPreview ) {
        this( reportDownloadFinishedListener, messages.reportButtonCloseTitle(), messages.reportButtonNextTitle(), fileName );
        this.showMapPreview = showMapPreview;
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
                                   String reportCloseButtonTitle, String reportNextButtonTitle, String fileName ) {
        this.reportDownloadFinishedListener = reportDownloadFinishedListener;
        this.reportCloseButtonTitle = reportCloseButtonTitle;
        this.reportNextButtonTitle = reportNextButtonTitle;
        initFormFields( fileName );
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

    private void initFormFields( String fileName ) {
        validationName.setText( fileName != null && !fileName.isEmpty() ? fileName : messages.defaultRunName() );
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
        final ReportDialog reportDialog = new ReportDialog();
        validationService.validate( validationSettings, new AsyncCallback<ValidationSummary>() {

            @Override
            public void onSuccess( ValidationSummary validationSummary ) {
                hideValidatingDialogBox();
                reportDialog.init( validationSummary, reportCloseButtonTitle,
                                                              reportNextButtonTitle, showMapPreview );
                reportDialog.addReportDownloadFinishedListener( reportDownloadFinishedListener );
                reportDialog.show();
            }

            @Override
            public void onFailure( Throwable caught ) {
                hideValidatingDialogBox();
                Window.alert( "Fehler bei der Validierung: " + caught.getMessage() );
            }
        } );
        mapPreviewConfigService.isMapPreviewAvaialable( new AsyncCallback<Boolean>() {
            @Override
            public void onSuccess( Boolean isMapPreviewAvailable ) {
                if ( isMapPreviewAvailable ) {
                    mapPreviewConfigService.createMapPreviewConfig( new AsyncCallback<MapPreviewMetadata>() {

                        @Override
                        public void onSuccess( MapPreviewMetadata mapPreviewMetadata ) {
                            reportDialog.setMapPreviewMetadata( mapPreviewMetadata );
                        }

                        @Override
                        public void onFailure( Throwable caught ) {
                            Window.alert( "Fehler beim erstellen der Konfiguration der Kartenvorschau: "
                                          + caught.getMessage() );
                        }
                    } );
                }
            }

            @Override
            public void onFailure( Throwable throwable ) {
                // nothing to do
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