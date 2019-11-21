package de.latlon.xplan.validator.web.client.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.validator.web.client.ValidatorWebCommonsMessages;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MasterportalMapPreviewDialog extends DialogBox {

    private static final String FRAME_WIDTH = "800px";

    private static final String FRAME_HEIGHT = "700px";

    private final ValidatorWebCommonsMessages messages = GWT.create( ValidatorWebCommonsMessages.class );

    public MasterportalMapPreviewDialog( String planName ) {
        super( false );
        initDialog( planName );
    }

    private void initDialog( String planName ) {
        setText( messages.mapPreviewDialogTitle( planName ) );
        VerticalPanel dialogBoxContent = createDialogBoxContent();
        createAndAddMap( dialogBoxContent );
        createAndAddCloseButton( dialogBoxContent );
        setWidget( dialogBoxContent );
    }

    private VerticalPanel createDialogBoxContent() {
        VerticalPanel dialogBoxContent = new VerticalPanel();
        dialogBoxContent.setHorizontalAlignment( VerticalPanel.ALIGN_CENTER );
        dialogBoxContent.setSpacing( 20 );
        return dialogBoxContent;
    }

    private void createAndAddMap( final VerticalPanel dialogBoxContent ) {
        Frame mapFrame = new Frame();
        mapFrame.setWidth( FRAME_WIDTH );
        mapFrame.setHeight( FRAME_HEIGHT );
        mapFrame.setUrl( "http://localhost:8081/xplan-validator/masterportal/?zoomToExtent=559766,5943719,560841,5944795&config=config.001.json" );
        dialogBoxContent.add( mapFrame );
    }

    private void createAndAddCloseButton( VerticalPanel dialogBoxContent ) {
        SimplePanel simplePanel = createButtonPanel( messages.validationPopupClose(), createCloseHandler() );
        dialogBoxContent.add( simplePanel );
    }

    private ClickHandler createCloseHandler() {
        return new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                MasterportalMapPreviewDialog.this.hide();
            }
        };
    }

    private SimplePanel createButtonPanel( String message, ClickHandler clickHandler ) {
        Button button = new Button();
        button.setText( message );
        button.addClickHandler( clickHandler );
        return addButtonToSimplePanel( button );
    }

    private SimplePanel addButtonToSimplePanel( Button button ) {
        SimplePanel simplePanel = new SimplePanel();
        simplePanel.add( button );
        return simplePanel;
    }
}