package de.latlon.xplan.manager.web.client.gui.filter;

import com.google.gwt.user.client.ui.SimplePanel;

import de.latlon.xplan.manager.web.client.filter.PlanFilter;

/**
 * Abstract GUI component for filter panels.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:goltz@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public abstract class AbstractFilterPanel extends SimplePanel implements FilterProvider {

    private final FilterExecutor filterExecutor;

    private PlanFilter planFilter;

    public AbstractFilterPanel( FilterExecutor filterExecutor ) {
        this.filterExecutor = filterExecutor;
        filterExecutor.addFilterProvider( this );
    }

    @Override
    public PlanFilter provideFilter() {
        return planFilter;
    }

    /**
     * Updates the filter and executes all registered filters.
     * 
     * @param filter
     *            filter to be updated
     */
    protected void updateAndExecuteFilter( PlanFilter filter ) {
        planFilter = filter;
        filterExecutor.doFilter();
    }

    /**
     * Updates the filter but does not execute the registered filters.
     * 
     * @param filter
     *            filter to be updated
     */
    protected void updateFilter( PlanFilter filter ) {
        planFilter = filter;
    }

}