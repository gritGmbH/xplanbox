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
package de.latlon.xplan.manager.web.client.gui.dialog;

import static de.latlon.xplan.manager.web.shared.PlanStatus.ARCHIVIERT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.LegislationStatus;
import de.latlon.xplan.manager.web.shared.PlanStatus;

/**
 * Dialog to select the legislation status of a plan.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class LegislationStatusDialog extends WizardDialogBox {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private final ListBox legislationStatusSelectBox;

    /**
     * @param legislationStatus
     *            the status from the plan, may be <code>null</code> if not set
     */
    public LegislationStatusDialog( LegislationStatus legislationStatus ) {
        super( MESSAGES.legislationStatusDialogTitle() );
        setWidth( "425px" );
        this.legislationStatusSelectBox = createLegislationStatusListBox( legislationStatus.getCodeNumber() );
        setContent( createContentPanel( legislationStatus.getTranslatedCode() ) );
    }

    /**
     * @return the selected legislation status, <code>null</code> if no entry is selected
     */
    public PlanStatus retrieveSelectedLegislationStatus() {
        int selectedIndex = legislationStatusSelectBox.getSelectedIndex();
        if ( selectedIndex == 0 )
            return FESTGESTELLT;
        else if ( selectedIndex == 1 )
            return IN_AUFSTELLUNG;
        else if ( selectedIndex == 2 )
            return ARCHIVIERT;
        return null;
    }

    private ListBox createLegislationStatusListBox( int legislationStatusCode ) {
        final ListBox legislationStatusListBox = new ListBox();
        int selectedIndex = 0;
        if ( legislationStatusCode < 0 ) {
            legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogFestgestelltOption() );
            legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogInAufstellungOption() );
            legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogArchiviertOption() );
        } else {
            if ( legislationStatusCode == 3000 || legislationStatusCode == 4000 ) {
                legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogFestgestelltSelectedOption() );
                legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogInAufstellungOption() );
                legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogArchiviertOption() );
            } else if ( legislationStatusCode == 4500 || legislationStatusCode == 5000 || legislationStatusCode == 50000
                        || legislationStatusCode == 50001 ) {
                legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogFestgestelltOption() );
                legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogInAufstellungOption() );
                legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogArchiviertSelectedOption() );
                selectedIndex = 2;
            } else {
                legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogFestgestelltOption() );
                legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogInAufstellungSelectedOption() );
                legislationStatusListBox.addItem( MESSAGES.legislationStatusDialogArchiviertOption() );
                selectedIndex = 1;
            }
        }
        legislationStatusListBox.setSelectedIndex( selectedIndex );
        return legislationStatusListBox;
    }

    private Panel createContentPanel( String translatedLegislationStatus ) {
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing( 20 );
        panel.add( new Label( retrieveText( translatedLegislationStatus ) ) );
        panel.add( legislationStatusSelectBox );
        return panel;
    }

    private String retrieveText( String translatedLegislationStatus ) {
        if ( translatedLegislationStatus != null )
            return MESSAGES.legislationStatusDialogText( translatedLegislationStatus );
        else
            return MESSAGES.legislationStatusDialogTextWithoutLegislationStatus();
    }

}
