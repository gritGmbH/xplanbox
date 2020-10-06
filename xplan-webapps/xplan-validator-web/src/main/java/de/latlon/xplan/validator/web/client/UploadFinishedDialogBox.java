package de.latlon.xplan.validator.web.client;

import static de.latlon.xplan.validator.web.client.report.ReportDownloadFinishedListener.FinishStatus.NEXT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.latlon.xplan.validator.web.client.report.ReportDownloadFinishedListener;

/**
 * Extends the {@link DialogBox} with a button to close the dialog
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
class UploadFinishedDialogBox extends DialogBox {

    private final ValidatorWebCommonsMessages messages = GWT.create( ValidatorWebCommonsMessages.class );

    private final String fileName;

    /**
     * @param xPlanValidatorWeb
     *            never <code>null</code>
     * @param htmlMessage
     *            to show as content
     */
    public UploadFinishedDialogBox( XPlanValidatorWeb xPlanValidatorWeb, String htmlMessage, String fileName ) {
        super( false );
        this.fileName = fileName;
        setText( messages.uploadSucessTitle() );
        initDialog( xPlanValidatorWeb, htmlMessage );
    }

    private void initDialog( XPlanValidatorWeb xPlanValidatorWeb, String htmlMessage ) {
        HTML html = new HTML( htmlMessage );
        SimplePanel contentPanel = new SimplePanel( html );
        VerticalPanel dialogBoxContent = new VerticalPanel();
        dialogBoxContent.setHorizontalAlignment( VerticalPanel.ALIGN_CENTER );
        dialogBoxContent.add( contentPanel );
        createAndAddCloseButton( dialogBoxContent, xPlanValidatorWeb );
        setWidget( dialogBoxContent );
    }

    private void createAndAddCloseButton( VerticalPanel dialogBoxContent, XPlanValidatorWeb xPlanValidatorWeb ) {
        Panel holder = createButtonsPanel( xPlanValidatorWeb );
        dialogBoxContent.add( holder );
    }

    private Panel createButtonsPanel( XPlanValidatorWeb xPlanValidatorWeb ) {
        Button nextButton = createNextButton( xPlanValidatorWeb );
        Button cancelButton = createCancelButton();

        HorizontalPanel buttonsPanel = new HorizontalPanel();
        buttonsPanel.setSpacing( 20 );
        buttonsPanel.add( cancelButton );
        buttonsPanel.add( nextButton );
        return buttonsPanel;
    }

    private Button createNextButton( final XPlanValidatorWeb xPlanValidatorWeb ) {
        Button nextButton = new Button();
        nextButton.setText( messages.uploadFinishedNextButton() );
        ClickHandler nextListener = new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                UploadFinishedDialogBox.this.hide();
                ClickHandler cancelHandler = new ClickHandler() {
                    @Override
                    public void onClick( ClickEvent clickEvent ) {
                        xPlanValidatorWeb.resetPanelToUpload();
                    }
                };
                ValidatorOptionsDialog xPlanValidatorSettings = new ValidatorOptionsDialog( new ReportDownloadFinishedListener() {
                    @Override
                    public void downloadFinished( FinishStatus finishStatus ) {
                        if ( NEXT.equals( finishStatus ) )
                            xPlanValidatorWeb.resetPanelToUpload();
                    }

                }, fileName, true, cancelHandler );
                xPlanValidatorWeb.setPanel( xPlanValidatorSettings );
            }
        };
        nextButton.addClickHandler( nextListener );
        return nextButton;
    }

    private Button createCancelButton() {
        Button nextButton = new Button();
        nextButton.setText( messages.uploadFinishedCancelButton() );
        ClickHandler cancelListener = new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                UploadFinishedDialogBox.this.hide();
            }
        };
        nextButton.addClickHandler( cancelListener );
        return nextButton;
    }

}