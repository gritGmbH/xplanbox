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
