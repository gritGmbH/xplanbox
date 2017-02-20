package de.latlon.xplan.validator.report;

/**
 * Encapsulates a single validation result.
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public abstract class ValidatorResult {

    private boolean isValid;

    private ValidatorDetail validatorDetails;

    /**
     * Instantiates a {@link ValidatorResult} which is invalid and without detailsHint.
     */
    public ValidatorResult() {
        this( null );
    }

    /**
     * Instantiates a {@link ValidatorResult} which is invalid and with detailsHint.
     * 
     * @param validatorDetails
     *            some details about the validation, may be <code>null</code>
     */
    public ValidatorResult( ValidatorDetail validatorDetails ) {
        this.validatorDetails = validatorDetails;
    }

    /**
     * Instantiates a {@link ValidatorResult} without detailsHint.
     * 
     * @param isValid
     *            <code>true</code> if the validation results is valid, <code>false</code> otherwise
     */
    public ValidatorResult( boolean isValid ) {
        this.isValid = isValid;
    }

    /**
     * @param isValid
     *            <code>true</code> if the validation results is valid, <code>false</code> otherwise
     * @param validatorDetails
     *            some details about the validation, may be <code>null</code>
     */
    public ValidatorResult( boolean isValid, ValidatorDetail validatorDetails ) {
        this.isValid = isValid;
        this.validatorDetails = validatorDetails;
    }

    /**
     * @param valid
     *            <code>true</code> if the validation results is valid, <code>false</code> otherwise
     */
    public void setValid( boolean valid ) {
        this.isValid = valid;
    }

    /**
     * @return <code>true</code> if the validation results is valid, <code>false</code> otherwise
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * @return some details about the validation, may be <code>null</code>
     */
    public ValidatorDetail getValidatorDetail() {
        return validatorDetails;
    }

    /**
     * @param validatorDetails
     *            some details about the validation, may be <code>null</code>
     */
    public void setDetailsHint( ValidatorDetail validatorDetails ) {
        this.validatorDetails = validatorDetails;
    }

    public abstract String getType();

}