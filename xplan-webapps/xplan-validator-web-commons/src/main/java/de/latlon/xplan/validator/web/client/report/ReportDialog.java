package de.latlon.xplan.validator.web.client.report;

import static de.latlon.xplan.validator.web.client.report.ReportDownloadFinishedListener.FinishStatus.CLOSE;
import static de.latlon.xplan.validator.web.client.report.ReportDownloadFinishedListener.FinishStatus.NEXT;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.validator.web.client.ValidatorWebCommonsMessages;
import de.latlon.xplan.validator.web.client.report.ReportDownloadFinishedListener.FinishStatus;
import de.latlon.xplan.validator.web.shared.ValidationSummary;

/**
 * Contains the HTML-Report and allows download of report artefacts as zip.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ReportDialog extends DialogBox {

    private static final ValidatorWebCommonsMessages messages = GWT.create( ValidatorWebCommonsMessages.class );

    private final ReportUrlBuilder urlBuilder = new ReportUrlBuilder();

    private final List<ReportDownloadFinishedListener> finishedListeners = new ArrayList<ReportDownloadFinishedListener>();

    private final ValidationSummary validationSummary;

    private final String closeButtonTitle;

    private final String nextButtonTitle;

    /**
     * 
     * @param validationSummary
     *            encapsulates some informations about the validation run presented in this dialog, never
     * @param closeButtonTitle
     *            title of the close button
     * @param nextButtonTitle
     *            title of the next button
     */
    public ReportDialog( ValidationSummary validationSummary, String closeButtonTitle, String nextButtonTitle ) {
        super( false, false );
        this.validationSummary = validationSummary;
        this.closeButtonTitle = closeButtonTitle;
        this.nextButtonTitle = nextButtonTitle;
        setText( messages.reportDialogTitle() );
        initDialog();
    }

    /**
     * @param finishedListener
     *            {@link ReportDownloadFinishedListener} if the user clicked cancel or close, never <code>null</code>
     */
    public void addReportDownloadFinishedListener( ReportDownloadFinishedListener finishedListener ) {
        finishedListeners.add( finishedListener );
    }

    private void initDialog() {
        HorizontalPanel mainPanel = new HorizontalPanel();
        mainPanel.setSpacing( 10 );
        mainPanel.add( createHtmlReportPanel() );
        mainPanel.add( createRightPanel() );
        add( mainPanel );
    }

    private Widget createHtmlReportPanel() {
        Frame frame = new Frame();
        frame.setHeight( "600px" );
        frame.setWidth( "500px" );
        String url = urlBuilder.createHtmlUrl( validationSummary );
        GWT.log( "Requested URL to receive the html report: " + url );
        frame.setUrl( url );
        return frame;
    }

    private Widget createRightPanel() {
        VerticalPanel rightPanel = new VerticalPanel();
        rightPanel.setSpacing( 10 );
        rightPanel.add( createCloseButton() );
        rightPanel.add( createNextButton() );
        rightPanel.add( createDownloadBox() );
        return rightPanel;
    }

    private Widget createCloseButton() {
        Button button = new Button();
        button.setText( closeButtonTitle );
        button.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                hide();
                informFinishListeners( CLOSE );
            }
        } );
        return button;
    }

    private Widget createNextButton() {
        Button button = new Button();
        button.setText( nextButtonTitle );
        button.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                hide();
                informFinishListeners( NEXT );
            }
        } );
        return button;
    }

    private Widget createDownloadBox() {
        return new ReportDownloadPanel( validationSummary );
    }

    private void informFinishListeners( FinishStatus status ) {
        for ( ReportDownloadFinishedListener finishedListener : finishedListeners ) {
            finishedListener.downloadFinished( status );
        }
    }

}