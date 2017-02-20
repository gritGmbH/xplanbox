package de.latlon.xplan.validator.web.shared;

import java.io.Serializable;
import java.util.List;

/**
 * Encapsulates all settings made for a validation run.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ValidationSettings implements Serializable {

    private static final long serialVersionUID = 6120360074178016611L;

    private String validationName;

    private ValidationType validationType;

    private List<ValidationOption> extendedOptions;

    public ValidationSettings() {
    }

    public ValidationSettings( String validationName, ValidationType validationType,
                               List<ValidationOption> extendedOptions ) {
        this.validationName = validationName;
        this.validationType = validationType;
        this.extendedOptions = extendedOptions;
    }

    public String getValidationName() {
        return validationName;
    }

    public void setValidationName( String validationName ) {
        this.validationName = validationName;
    }

    public ValidationType getValidationType() {
        return validationType;
    }

    public void setValidationType( ValidationType validationType ) {
        this.validationType = validationType;
    }

    public List<ValidationOption> getExtendedOptions() {
        return extendedOptions;
    }

    public void setExtendedOptions( List<ValidationOption> extendedOptions ) {
        this.extendedOptions = extendedOptions;
    }

}