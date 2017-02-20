package de.latlon.xplan.manager.web.client.gui.filter;

/**
 * Executor to apply filter provider.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public interface FilterExecutor {

    /**
     * Executes all registered filters.
     */
    void doFilter();

    /**
     * Registers a filter provider.
     * 
     * @param filterProvider
     */
    void addFilterProvider( FilterProvider filterProvider );

}
