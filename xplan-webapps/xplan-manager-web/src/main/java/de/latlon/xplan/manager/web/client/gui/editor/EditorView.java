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
package de.latlon.xplan.manager.web.client.gui.editor;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

/**
 * Encapsulated the view of the editor.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class EditorView extends VerticalPanel {

    private HandlerManager eventBus;

    /**
     * @param eventBus
     *            required to control overall view events, never <code>null</code>
     */
    public EditorView( final HandlerManager eventBus ) {
        this.eventBus = eventBus;
        setWidth( "100%" );
        setHorizontalAlignment( ALIGN_CENTER );
        setSpacing( 15 );
    }

    /**
     * @param planId
     *            of the plan to edit
     * @param version
     *            of the xplan to edit. never <code>null</code>
     * @param xPlantoEdit
     *            the xplan to edit, never <code>null</code>
     */
    public void setXPlanToEdit( String planId, EditVersion version, XPlanToEdit xPlantoEdit ) {
        clear();
        EditorPanel editorPanel = new EditorPanel( version, eventBus );
        add( editorPanel );
        editorPanel.setXPlanToEdit( planId, xPlantoEdit );
    }

}
