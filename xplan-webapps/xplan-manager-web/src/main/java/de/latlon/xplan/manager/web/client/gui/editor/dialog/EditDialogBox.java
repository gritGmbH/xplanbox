package de.latlon.xplan.manager.web.client.gui.editor.dialog;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType;
import de.latlon.xplan.manager.web.client.gui.widget.CodeListBox;
import de.latlon.xplan.manager.web.client.gui.widget.MandatoryTextBox;
import de.latlon.xplan.manager.web.client.gui.widget.StrictDateBox;
import de.latlon.xplan.manager.web.client.gui.widget.StrictDateBoxFormat;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

/**
 * Extends the {@link DialogBox} with a button to close the dialog and a button to save the content.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public abstract class EditDialogBox extends DialogBox {

    protected static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private static final String DEFAULT_WIDTH = "200px";

    private final List<SavedHandler> savedHandlers = new ArrayList<SavedHandler>();

    private Button button;

    /**
     * Instantiates a new {@link DialogBox} without content. Invoke initDialog() and pass the content.
     * 
     * @param title
     *            the title of the dialog, may be <code>null</code>
     */
    public EditDialogBox( String title ) {
        super( false );
        setText( title );
    }

    public void addSaveHandler( SavedHandler saveHandlerToAdd ) {
        savedHandlers.add( saveHandlerToAdd );
    }

    protected void initDialog( Widget contentPanel ) {
        VerticalPanel dialogBoxContent = new VerticalPanel();
        dialogBoxContent.setHorizontalAlignment( VerticalPanel.ALIGN_CENTER );
        dialogBoxContent.add( contentPanel );
        dialogBoxContent.add( createButtonBar() );
        setWidget( dialogBoxContent );
    }

    protected void informSaveHandler() {
        for ( SavedHandler savedHandler : savedHandlers ) {
            savedHandler.changesSaved();
        }
    }

    protected void save() {
        informSaveHandler();
    }

    protected TextBox createTextInput() {
        TextBox textBox = new TextBox();
        textBox.setWidth( DEFAULT_WIDTH );
        return textBox;
    }

    protected MandatoryTextBox createMandatoryTextInput() {
        MandatoryTextBox textBox = new MandatoryTextBox();
        textBox.setWidth( DEFAULT_WIDTH );
        return textBox;
    }

    protected TextArea createTextAreaInput() {
        TextArea textArea = new TextArea();
        textArea.setWidth( DEFAULT_WIDTH );
        textArea.setHeight( "150px" );
        return textArea;
    }

    protected StrictDateBox createDateInput() {
        StrictDateBox dateBox = new StrictDateBox( new StrictDateBoxFormat() );
        dateBox.setWidth( DEFAULT_WIDTH );
        return dateBox;
    }

    protected CodeListBox createMandatoryCodeListInput( EditVersion version, CodelistType codelistType ) {
        return createCodeListInput( version, codelistType, true );
    }

    protected CodeListBox createCodeListInput( EditVersion version, CodelistType codelistType ) {
        return createCodeListInput( version, codelistType, false );
    }

    protected ListBox createListInput() {
        ListBox listBox = new ListBox();
        listBox.setWidth( DEFAULT_WIDTH );
        return listBox;
    }

    private Widget createButtonBar() {
        HorizontalPanel buttonBar = new HorizontalPanel();
        buttonBar.setSpacing( 10 );
        buttonBar.add( createCancelButton() );
        buttonBar.add( createSaveButton() );
        return buttonBar;
    }

    private CodeListBox createCodeListInput( EditVersion version, CodelistType codelistType, boolean isManadatory ) {
        CodeListBox listBox = new CodeListBox( version, codelistType, isManadatory );
        listBox.setWidth( DEFAULT_WIDTH );
        return listBox;
    }

    private Button createCancelButton() {
        button = new Button();
        button.setText( MESSAGES.editCancelButton() );
        button.addClickHandler( createCancelListener() );
        return button;
    }

    private Button createSaveButton() {
        button = new Button();
        button.setText( MESSAGES.editSaveButton() );
        button.addClickHandler( createSaveListener() );
        return button;
    }

    private ClickHandler createCancelListener() {
        ClickHandler listener = new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                EditDialogBox.this.hide();
            }
        };
        return listener;
    }

    private ClickHandler createSaveListener() {
        return new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                save();
            }
        };
    }

}