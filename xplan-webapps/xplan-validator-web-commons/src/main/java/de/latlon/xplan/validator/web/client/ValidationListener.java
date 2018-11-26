package de.latlon.xplan.validator.web.client;

import de.latlon.xplan.validator.web.shared.ValidationSettings;

/**
 * Listener informed when a validation was invoked.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public interface ValidationListener {

    /**
     * @param validationSettings
     *            encapsulation the settings of the invoked validation run, never <code>null</code>
     */
    void validationStarted( ValidationSettings validationSettings );

}