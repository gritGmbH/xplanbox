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
package de.latlon.xplan.manager.web.client.gui.widget;

import static de.latlon.xplan.manager.web.client.gui.StyleNames.EDITOR_VALIDATION_ERROR;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;

import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.Code;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistProvider;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

/**
 * {@link ListBox} representing only Codes.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class CodeListBox extends ListBox implements Validable {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private static final CodelistProvider CODELISTPROVIDER = new CodelistProvider();

    private boolean isMandatory;

    /**
     * Instantiates a CodeListBox which is not mandatory.
     * 
     * @param version
     *            of the XPlan, never <code>null</code>
     * @param codelistType
     *            of this CodeListBox, never <code>null</code>
     */
    public CodeListBox( EditVersion version, CodelistType codelistType ) {
        this( version, codelistType, false );
    }

    /**
     * @param version
     *            of the XPlan, never <code>null</code>
     * @param codelistType
     *            of this CodeListBox, never <code>null</code>
     * @param isMandatory
     *            <code>true</code> if a selection is required, <code>false</code> otherwise
     */
    public CodeListBox( EditVersion version, CodelistType codelistType, boolean isMandatory ) {
        this.isMandatory = isMandatory;
        addMandatoryChangeHandler();
        initListBoxItems( version, codelistType );
        selectItem( -1 );
    }

    /**
     * @return retrieve the selected item as code value, -1 if nothing is selected.
     */
    public int getValueAsCode() {
        int selectedIndex = getSelectedIndex();
        if ( selectedIndex > 0 ) {
            String value = getValue( selectedIndex );
            try {
                return Integer.valueOf( value );
            } catch ( NumberFormatException e ) {
            }
        }
        return -1;
    }

    /**
     * @param codeToSelect
     *            select the item with this code
     */
    public void selectItem( int codeToSelect ) {
        int indexToSelect = findIndexToSelect( codeToSelect );
        setSelectedIndex( indexToSelect );
        validate();
    }

    @Override
    public boolean isValid() {
        return validate();
    }

    private void initListBoxItems( EditVersion version, CodelistType codelistType ) {
        clear();
        List<Code> items = CODELISTPROVIDER.retrieveItems( version, codelistType );
        addItem( "Keine Auswahl" );
        for ( Code item : items ) {
            addItem( item.getItem(), item.getCode() );
        }
    }

    private void addMandatoryChangeHandler() {
        addChangeHandler( new ChangeHandler() {
            @Override
            public void onChange( ChangeEvent event ) {
                validate();
            }
        } );
    }

    private boolean validate() {
        if ( isMandatory ) {
            int selectedIndex = getSelectedIndex();
            if ( selectedIndex == 0 ) {
                addStyleName( EDITOR_VALIDATION_ERROR );
                setTitle( MESSAGES.editInputRequired() );
                return false;
            } else {
                removeStyleName( EDITOR_VALIDATION_ERROR );
                setTitle( "" );
                return true;
            }
        }
        return true;
    }

    private int findIndexToSelect( int codeToSelect ) {
        int numberOfItems = getItemCount();
        for ( int itemIndex = 0; itemIndex < numberOfItems; itemIndex++ ) {
            String itemValue = getValue( itemIndex );
            if ( itemValue.equals( Integer.toString( codeToSelect ) ) )
                return itemIndex;
        }
        return 0;
    }

}
