/*-
 * #%L
 * xplan-validator-web-commons - Modul zur Gruppierung aller Webapps
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
