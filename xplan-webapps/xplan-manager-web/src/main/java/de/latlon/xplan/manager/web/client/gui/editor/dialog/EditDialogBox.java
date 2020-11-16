/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.client.gui.editor.dialog;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
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

    protected final HTML validationErrors = new HTML();

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
        dialogBoxContent.add( validationErrors );
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

    protected void showValidationError( List<String> validationFailures ) {
        StringBuilder htmlMsg = new StringBuilder();
        htmlMsg.append( "<div>" );
        htmlMsg.append( "<ul>" );
        for ( String validationFailure : validationFailures ) {
            htmlMsg.append( "<li class=\"validationError\">" ).append( validationFailure ).append( "</li>" );
        }
        htmlMsg.append( "</ul>" );
        htmlMsg.append( "</div>" );
        validationErrors.setHTML( htmlMsg.toString() );
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
