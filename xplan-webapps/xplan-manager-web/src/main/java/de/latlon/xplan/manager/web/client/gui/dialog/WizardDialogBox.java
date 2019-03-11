//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.web.client.gui.dialog;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

/**
 * Dialog with next and cancel button which can be used in an wizard.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public abstract class WizardDialogBox extends DialogBox {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private Button cancelButton = createCancelButton();

    private Button nextButton = createNextButton();

    private List<NextSubmittedHandler> nextHandlers = new ArrayList<NextSubmittedHandler>();

    /**
     * @param title
     *            the title of the dialog, may be <code>null</code>
     */
    public WizardDialogBox( String title ) {
        super( false );
        setText( title );
    }

    /**
     * @param contentPanel
     *            the panel with the content to render in the box
     */
    public void setContent( Panel contentPanel ) {
        initDialog( contentPanel );
    }

    /**
     * Add a new {@link NextSubmittedHandler} invoked if the next button was clicked (and submitted).
     * {@link NextSubmittedHandler}s are submitted in the order of add.
     * 
     * @param nextHandler
     *            to add, never <code>null</code>
     */
    public void addNextSubmittedHandler( NextSubmittedHandler nextHandler ) {
        nextHandlers.add( nextHandler );
    }

    /**
     * @return a new {@link ClickHandler} invoked if the user clicked on the next button. By default all
     *         {@link NextSubmittedHandler}s are invoked. Override this to allow the implementation to decide about
     *         submitting.
     */
    protected ClickHandler createNextDialogHandler() {
        return new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                informNextHandlers();
            }
        };
    }

    /**
     * Invokes onNext() of al regisered {@link NextSubmittedHandler}s in the order of add.
     */
    protected void informNextHandlers() {
        for ( NextSubmittedHandler nextHandler : nextHandlers ) {
            nextHandler.onNextSubmitted();
        }
    }

    private void initDialog( Panel contentPanel ) {
        VerticalPanel dialogBoxContent = new VerticalPanel();
        dialogBoxContent.setHorizontalAlignment( VerticalPanel.ALIGN_CENTER );
        if ( contentPanel != null )
            dialogBoxContent.add( contentPanel );
        dialogBoxContent.add( createButtonBar() );
        setWidget( dialogBoxContent );
    }

    private Widget createButtonBar() {
        HorizontalPanel buttonBar = new HorizontalPanel();
        buttonBar.setSpacing( 10 );
        buttonBar.add( cancelButton );
        buttonBar.add( nextButton );
        return buttonBar;
    }

    private Button createCancelButton() {
        Button cancelButton = new Button();
        cancelButton.setText( MESSAGES.cancelButton() );
        cancelButton.addClickHandler( createCloseDialogListener() );
        return cancelButton;
    }

    private Button createNextButton() {
        Button nextButton = new Button();
        nextButton.setText( MESSAGES.nextButton() );
        nextButton.addClickHandler( createNextDialogHandler() );
        return nextButton;
    }

    private ClickHandler createCloseDialogListener() {
        ClickHandler listener = new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                WizardDialogBox.this.hide();
            }
        };
        return listener;
    }

}