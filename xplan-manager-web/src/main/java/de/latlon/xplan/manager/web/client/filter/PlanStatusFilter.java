package de.latlon.xplan.manager.web.client.filter;

import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.XPlanMetadata;

/**
 * Checks if the plan is assigned to the plan status or not.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class PlanStatusFilter implements PlanFilter {

    private final String planStatus;

    /**
     * Instantiates a positive filter: a plan matches if the status of the plan is the same (ignoring case) as the given
     * status.
     * 
     * @param planStatus
     *            the expected category, <code>null</code> if the filter is disabled
     */
    public PlanStatusFilter( String planStatus ) {
        this.planStatus = planStatus;
    }

    @Override
    public boolean isMatching( XPlan plan ) {
        if ( planStatus == null || planStatus.isEmpty() )
            return true;
        String planStatusFromPlan = retrievePlanStatusFromPlan( plan );
        return planStatus.equalsIgnoreCase( planStatusFromPlan );
    }

    private String retrievePlanStatusFromPlan( XPlan plan ) {
        XPlanMetadata xplanMetadata = plan.getXplanMetadata();
        if ( xplanMetadata == null )
            return null;
        PlanStatus planStatusFromPlan = xplanMetadata.getPlanStatus();
        if ( planStatusFromPlan == null )
            return null;
        return planStatusFromPlan.getMessage();
    }

}