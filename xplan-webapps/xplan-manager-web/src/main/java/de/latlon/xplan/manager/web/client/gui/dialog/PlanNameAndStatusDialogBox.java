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
package de.latlon.xplan.manager.web.client.gui.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.PlanStatus;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanNameAndStatusDialogBox extends WizardDialogBox {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    /**
     * @param planName
     *                 the name of the plan, never <code>null</code>
     * @param planStatus
     *                 the status of the plan, never <code>null</code>
     */
    public PlanNameAndStatusDialogBox( String planName, PlanStatus planStatus ) {
        super( MESSAGES.planNameAndStatusDialogHeader() );
        setContent( createMessageContent( planName, planStatus ) );
    }

    private Panel createMessageContent( String planName, PlanStatus planStatus ) {
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setSpacing( 20 );
        Label label = new Label( MESSAGES.duplicatePlanName( planName, planStatus.getMessage() ) );
        label.setWordWrap( true );
        mainPanel.add( label );
        return mainPanel;
    }

}
