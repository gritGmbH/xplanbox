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
package de.latlon.xplan.manager.web.client.gui.filter;

import com.google.gwt.user.client.ui.SimplePanel;

import de.latlon.xplan.manager.web.client.filter.PlanFilter;

/**
 * Abstract GUI component for filter panels.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:goltz@lat-lon.de">Dirk Stenger</a>
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
