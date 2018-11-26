package de.latlon.xplan.manager.web.client.gui.dialog;

/**
 * Handler to process a selected crs.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public interface CrsSelectedHandler {

    /**
     * Called when a crs was selected.
     * 
     * @param crs
     *            selected crs
     */
    void onCrsSelected( String crs );

}