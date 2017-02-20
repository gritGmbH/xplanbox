package de.latlon.xplan.manager.web.client.gui.filter;

import de.latlon.xplan.manager.web.client.filter.PlanFilter;

/**
 * Provider to retrieve a filter.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public interface FilterProvider {

    /**
     * Provides a filter.
     * 
     * @return
     */
    PlanFilter provideFilter();

}
