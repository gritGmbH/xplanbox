package de.latlon.xplan.validator.web.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.web.shared.ValidationException;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationSummary;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
@RemoteServiceRelativePath("validation")
public interface ValidationService extends RemoteService {

    /**
     * Start a validation run with the plan from the session.
     * 
     * @param validationSettings
     *            used for the validation, never <code>null</code>
     * @return the uuid of the plan
     * @throws ValidatorException
     *             if an exception occurred during validation
     */
    ValidationSummary validate( ValidationSettings validationSettings )
                    throws ValidationException, IllegalArgumentException;

}