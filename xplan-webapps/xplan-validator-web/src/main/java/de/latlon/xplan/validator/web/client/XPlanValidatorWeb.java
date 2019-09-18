package de.latlon.xplan.validator.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.commons.web.CloseableDialogBox;

/**
 * Entry point of the validation, containing the file upload and a button to navigate to the options view (
 * {@link XPlanValidatorOptions})
 * 
 * @author <a href="mailto:wilden@lat-lon.de">Johannes Wilden</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class XPlanValidatorWeb implements EntryPoint {

    private final XPlanValidatorWebMessages messages = GWT.create( XPlanValidatorWebMessages.class );

    private DialogBox uploading;

    @Override
    public void onModuleLoad() {
        FormPanel mainPanel = createFormPanel();
        RootPanel.get().add( mainPanel );
    }

    /**
     * replaces the current content with the passed panel
     * 
     * @param panel
     *            never <code>null</code>
     */
    public void setPanel( Widget panel ) {
        RootPanel.get().clear();
        RootPanel.get().add( panel );
    }

    /**
     * Shows the upload panel
     */
    public void resetPanelToUpload() {
        setPanel( createFormPanel() );
    }

    private FormPanel createFormPanel() {
        FormPanel form = new FormPanel();
        form.setAction( GWT.getModuleBaseURL() + "upload" );
        form.setEncoding( FormPanel.ENCODING_MULTIPART );
        form.setMethod( FormPanel.METHOD_POST );

        FileUpload uploadItem = createUploadItem();
        Panel uploadPanel = createUploadPanel( uploadItem );
        Panel openButtonPanel = createOpenButtonPanel( form, uploadItem );

        addFormSubmitHandler( form, uploadItem );
        addFormSubmitCompleteHandler( form );

        VerticalPanel mainPanel = createMainPanel( uploadPanel, openButtonPanel );
        form.add( mainPanel );
        return form;
    }

    private VerticalPanel createMainPanel( Panel uploadPanel, Panel openButtonPanel ) {
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setWidth( "100%" );
        mainPanel.add( uploadPanel );
        mainPanel.add( openButtonPanel );
        return mainPanel;
    }

    private Panel createUploadPanel( FileUpload uploadItem ) {
        Label uploadLabel = new Label( messages.uploadLabel() );

        HorizontalPanel uploadPanel = new HorizontalPanel();
        layoutPanel( uploadPanel );
        uploadPanel.add( uploadLabel );
        uploadPanel.add( uploadItem );

        return uploadPanel;
    }

    private FileUpload createUploadItem() {
        FileUpload upload = new FileUpload();
        upload.setName( "uploadPlanItem" );
        return upload;
    }

    private Panel createOpenButtonPanel( final FormPanel form, final FileUpload upload ) {
        SimplePanel buttonPanel = new SimplePanel();
        layoutPanel( buttonPanel );

        Button openOptionsButton = new Button( messages.validationOptionsOpen(), new ClickHandler() {

            @Override
            public void onClick( ClickEvent event ) {
                if ( !upload.getFilename().toLowerCase().endsWith( ".zip" ) )
                    showWrongFileEndingDialog();
                else {
                    form.submit();
                    showUploadDialogBox();
                }
            }

            private void showWrongFileEndingDialog() {
                DialogBox errorUpload = new CloseableDialogBox( messages.errorTitle(),
                                                                messages.fileNameMustEndWithZip() );
                errorUpload.center();
                errorUpload.show();
            }

            private void showUploadDialogBox() {
                uploading = new DialogBox( false, true );
                uploading.setText( messages.uploadingFile() );
                uploading.center();
                uploading.show();
            }

        } );

        buttonPanel.add( openOptionsButton );
        return buttonPanel;

    }

    private void layoutPanel( Panel panel ) {
        panel.setHeight( "50px" );
    }

    private void addFormSubmitHandler( FormPanel form, final FileUpload uploadItem ) {
        form.addSubmitHandler( new FormPanel.SubmitHandler() {
            @Override
            public void onSubmit( SubmitEvent event ) {
                if ( uploadItem.getFilename().length() == 0 ) {
                    uploading.hide();
                    Window.alert( messages.uploadFailed() );
                    event.cancel();
                }
            }
        } );
    }

    private void addFormSubmitCompleteHandler( FormPanel form ) {
        form.addSubmitCompleteHandler( new FormPanel.SubmitCompleteHandler() {
            @Override
            public void onSubmitComplete( SubmitCompleteEvent event ) {
                uploading.hide();
                showSucessfulUploadedDialog( event );
            }

            private void showSucessfulUploadedDialog( SubmitCompleteEvent event ) {
                UploadFinishedDialogBox dialogBox = new UploadFinishedDialogBox( XPlanValidatorWeb.this,
                                                                                 event.getResults() );
                dialogBox.center();
                dialogBox.show();
            }
        } );
    }

}