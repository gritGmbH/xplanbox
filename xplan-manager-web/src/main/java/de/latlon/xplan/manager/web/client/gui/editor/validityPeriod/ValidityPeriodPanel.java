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
package de.latlon.xplan.manager.web.client.gui.editor.validityPeriod;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.client.gui.widget.ValidityPeriodInput;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.edit.ValidityPeriod;

/**
 * CaptionPanel with editor for the validity period section.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class ValidityPeriodPanel extends CaptionPanel implements Validable {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private final ValidityPeriodInput validityPeriodInput = new ValidityPeriodInput();

    public ValidityPeriodPanel() {
        setCaptionText( MESSAGES.editCaptionValidityPeriod() );
        add( createValidityPeriodPanelLayout() );
    }

    @Override
    public boolean isValid() {
        return validityPeriodInput.isValid();
    }

    public void setValidityPeriod( ValidityPeriod validityPeriod ) {
        if ( validityPeriod != null ) {
            validityPeriodInput.setStartDateTime( validityPeriod.getStart() );
            validityPeriodInput.setEndDateTime( validityPeriod.getEnd() );
        }
    }

    public ValidityPeriod retrieveValidityPeriodToEdit() {
        Date startDate = validityPeriodInput.retrieveStartDateTime();
        Date endDate = validityPeriodInput.retrieveEndDateTime();
        return new ValidityPeriod( startDate, endDate );
    }

    private Widget createValidityPeriodPanelLayout() {
        VerticalPanel panel = new VerticalPanel();
        panel.setHorizontalAlignment( ALIGN_CENTER );
        panel.add( validityPeriodInput );
        return panel;
    }

}