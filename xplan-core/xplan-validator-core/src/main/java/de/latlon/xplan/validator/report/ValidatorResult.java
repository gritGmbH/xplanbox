/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.report;

import de.latlon.xplan.validator.report.ReportUtils.SkipCode;

/**
 * Encapsulates a single validation result.
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public abstract class ValidatorResult {

    private boolean isValid;

    private SkipCode skipCode;

    private ValidatorDetail validatorDetails;

    /**
     * Instantiates a {@link ValidatorResult} which is invalid and without detailsHint.
     */
    public ValidatorResult() {
        this( (ValidatorDetail) null );
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
     * Instantiates a {@link ValidatorResult} for a skipped validation.
     *
     * @param skipCode
     *            the reason why the validation was skipped, never <code>null</code>
     */
    public ValidatorResult( SkipCode skipCode ) {
        this.skipCode = skipCode;
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
     * @return <code>true</code> if the validation was skipped, <code>false</code> otherwise
     */
    public boolean isSkipped() {
        return skipCode != null;
    }

    /**
     * @return the reason why the validation was skipped, may be <code>null</code> (if not skipped)
     */
    public SkipCode getSkipCode() {
        return skipCode;
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

    @Override
    public String toString() {
        if ( skipCode != null )
            return "Skipped: " + skipCode;
        return "ValidatorResult{" + "isValid=" + isValid + ", validatorDetails=" + validatorDetails + '}';
    }

}
