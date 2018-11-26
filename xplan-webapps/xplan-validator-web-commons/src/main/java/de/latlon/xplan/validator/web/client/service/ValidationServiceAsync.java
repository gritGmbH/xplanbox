package de.latlon.xplan.validator.web.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationSummary;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public interface ValidationServiceAsync {

    void validate( ValidationSettings validationSettings, AsyncCallback<ValidationSummary> callback );

}
