package de.latlon.xplan.validator.web.shared;

import java.io.Serializable;

/**
 * Summarized informations about the validated plan
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ValidationSummary implements Serializable {

    private static final long serialVersionUID = -3319541573993693239L;

    private String planUuid;

    private String validationName;

    public ValidationSummary() {
    }

    /**
     * @param planUuid
     *            uuid of the plan, may be <code>null</code>
     * @param validationName
     *            name of the validation, may be <code>null</code>
     */
    public ValidationSummary( String planUuid, String validationName ) {
        this.planUuid = planUuid;
        this.validationName = validationName;
    }

    /**
     * @return the uuid of the plan, may be <code>null</code>
     */
    public String getPlanUuid() {
        return planUuid;
    }

    /**
     * @param planUuid
     *            the uuid of the plan, may be <code>null</code>
     */
    public void setPlanUuid( String planUuid ) {
        this.planUuid = planUuid;
    }

    /**
     * @return the name of the validation, may be <code>null</code>
     */
    public String getValidationName() {
        return validationName;
    }

    /**
     * @param validationName
     *            the name of the validation, may be <code>null</code>
     */
    public void setValidationName( String validationName ) {
        this.validationName = validationName;
    }

}