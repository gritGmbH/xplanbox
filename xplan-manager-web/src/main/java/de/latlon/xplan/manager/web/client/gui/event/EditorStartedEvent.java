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
package de.latlon.xplan.manager.web.client.gui.event;

import com.google.gwt.event.shared.GwtEvent;

import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

/**
 * Indicates that the editing was started by the user.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class EditorStartedEvent extends GwtEvent<EditorStartedEventHandler> {

    public static Type<EditorStartedEventHandler> TYPE = new Type<EditorStartedEventHandler>();

    private final String planId;

    private final EditVersion version;

    private final XPlanToEdit xPlantoEdit;

    /**
     * @param planId
     *            of the plan to edit
     * @param version
     *            of the plan to edit, never <code>null</code>
     * @param xPlantoEdit
     *            never <code>null</code>
     */
    public EditorStartedEvent( String planId, EditVersion version, XPlanToEdit xPlantoEdit ) {
        this.planId = planId;
        this.version = version;
        this.xPlantoEdit = xPlantoEdit;
    }

    @Override
    public Type<EditorStartedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch( EditorStartedEventHandler handler ) {
        handler.onEditorStarted( this );
    }

    /**
     * @return the id of the plan to edit
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * @return the version of the plan to edit, never <code>null</code>
     */
    public EditVersion getVersion() {
        return version;
    }

    /**
     * @return the plan to edit, never <code>null</code>
     */
    public XPlanToEdit getxPlantoEdit() {
        return xPlantoEdit;
    }

}