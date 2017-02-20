package de.latlon.xplan.manager.web.client.filter;

import de.latlon.xplan.manager.web.shared.XPlan;

/**
 * Common interface for all filters, checking if a plan is visible or not.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public interface PlanFilter {

    /**
     * Checks if the passed plan matches this filter or not.
     * 
     * @param plan
     *            to check, never <code>null</code>
     * @return <code>true</code> if the plan matches this filter, <code>false</code> otherwise
     * @throws NullPointerException
     *             - plan is <code>null</code>
     */
    boolean isMatching( XPlan plan );

}