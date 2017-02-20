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
package de.latlon.xplan.manager.web.client.gui;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;

/**
 * Summarizes the PlanListPanel and UploadPanel in one view.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ImportAndListView extends VerticalPanel {

    private final PlanListPanel planListPanel;

    /**
     * @param eventBus
     *            required to control overall view events, never <code>null</code>
     * @param configuration
     *            never <code>null</code>
     * @param authorizationInfo
     *            never <code>null</code>
     */
    public ImportAndListView( HandlerManager eventBus, final ManagerWebConfiguration configuration,
                              AuthorizationInfo authorizationInfo ) {
        planListPanel = new PlanListPanel( eventBus, configuration, authorizationInfo );
        UploadPanel uploadPanel = new UploadPanel( configuration, planListPanel );
        createGUI( planListPanel, uploadPanel );
    }

    void updatePlanList() {
        planListPanel.reload( false );
    }

    private void createGUI( PlanListPanel planListPanel, UploadPanel uploadPanel ) {
        setWidth( "100%" );
        setHorizontalAlignment( ALIGN_CENTER );
        setSpacing( 15 );
        add( uploadPanel );
        add( planListPanel );
    }

}