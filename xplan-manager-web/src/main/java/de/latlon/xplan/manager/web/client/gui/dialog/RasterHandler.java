package de.latlon.xplan.manager.web.client.gui.dialog;

/**
 * Handler to process a confirmed import.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 *
 * @version $Revision: $, $Date: $
 */
public interface RasterHandler {

    /**
     * Called when import was confirmed
     */
    void onConfirmImport();

    /**
     * Called when import with force was confirmed
     */
    void onConfirmForceImport();

}