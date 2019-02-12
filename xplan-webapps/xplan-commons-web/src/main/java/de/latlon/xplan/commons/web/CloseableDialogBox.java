package de.latlon.xplan.commons.web;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.commons.web.i18n.XPlanMessages;

/**
 * Extends the {@link DialogBox} with a button to close the dialog
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class CloseableDialogBox extends DialogBox {

    private final XPlanMessages messages = GWT.create( XPlanMessages.class );

    private Button button;

    /**
     * @param title
     *            the title of the dialog, may be <code>null</code>
     */
    public CloseableDialogBox( String title ) {
        super( false );
        setText( title );
        initDialog( null );
    }

    /**
     * @param title
     *            the title of the dialog, may be <code>null</code>
     * @param contentPanel
     *            the panel with the content to render in the box
     */
    public CloseableDialogBox( String title, Panel contentPanel ) {
        super( false );
        setText( title );
        initDialog( contentPanel );
    }

    /**
     * 
     * @param title
     *            the title of the dialog, may be <code>null</code>
     * @param message
     *            to show as content
     */
    public CloseableDialogBox( String title, String message ) {
        super( false );
        setText( title );

        SimplePanel contentPanel = createSimpleTextPanel( message );
        initDialog( contentPanel );
    }

    /**
     * Set the text of the close button.
     * 
     * @param text
     */
    public void setCloseButtonText( String text ) {
        button.setText( text );
    }

    private SimplePanel createSimpleTextPanel( String message ) {
        SimplePanel contentPanel = new SimplePanel();
        contentPanel.add( new Label( message ) );
        return contentPanel;
    }

    public void setContent( Panel contentPanel ) {
        initDialog( contentPanel );
    }

    private void initDialog( Panel contentPanel ) {
        VerticalPanel dialogBoxContent = new VerticalPanel();
        dialogBoxContent.setHorizontalAlignment( VerticalPanel.ALIGN_CENTER );
        if ( contentPanel != null )
            dialogBoxContent.add( contentPanel );
        createAndAddCloseButton( dialogBoxContent );
        setWidget( dialogBoxContent );
    }

    private void createAndAddCloseButton( VerticalPanel dialogBoxContent ) {
        SimplePanel holder = createCloseButtonPanel();
        dialogBoxContent.add( holder );
    }

    private SimplePanel createCloseButtonPanel() {
        button = new Button();
        button.setText( messages.closeButton() );
        button.addClickHandler( createCloseListener() );

        SimplePanel holder = new SimplePanel();
        holder.add( button );
        return holder;
    }

    protected ClickHandler createCloseListener() {
        ClickHandler listener = new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                CloseableDialogBox.this.hide();
            }
        };
        return listener;
    }

}
