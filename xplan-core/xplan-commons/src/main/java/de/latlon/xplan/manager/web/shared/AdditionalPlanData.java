/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.web.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Contains some more information about a xplan, which are not stored in the plan.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class AdditionalPlanData implements Serializable {

    private static final long serialVersionUID = -5015118140629279806L;

    private PlanStatus planStatus;

    private Date startDateTime;

    private Date endDateTime;

    /**
     * Instantiates a {@link AdditionalPlanData} with null values.
     */
    public AdditionalPlanData() {
    }

    /**
     * @param planStatus
     *            the status of the plan, may be <code>null</code>. The plan status decides about the feature store the
     *            plan is stored in, if <code>null</code> the default feature store should is used.
     */
    public AdditionalPlanData( PlanStatus planStatus ) {
        this.planStatus = planStatus;
    }

    /**
     * @param startDateTime
     *            beginning of the validity period, may be <code>null</code>
     * @param endDateTime
     *            end of the validity period, may be <code>null</code>
     */
    public AdditionalPlanData( Date startDateTime, Date endDateTime ) {
        this( null, startDateTime, endDateTime );
    }

    /**
     * @param planStatus
     *            the status of the plan, may be <code>null</code>. The plan status decides about the feature store the
     *            plan is stored in, if <code>null</code> the default feature store should is used.
     * @param startDateTime
     *            beginning of the validity period, may be <code>null</code>
     * @param endDateTime
     *            end of the validity period, may be <code>null</code>
     */
    public AdditionalPlanData( PlanStatus planStatus, Date startDateTime, Date endDateTime ) {
        this.planStatus = planStatus;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * @return the status of the plan, may be <code>null</code>. The plan status decides about the feature store the
     *         plan is stored in, if <code>null</code> the default feature store should is used.
     */
    public PlanStatus getPlanStatus() {
        return planStatus;
    }

    /**
     * @param planStatus
     *            the status of the plan, may be <code>null</code>. The plan status decides about the feature store the
     *            plan is stored in, if <code>null</code> the default feature store should is used.
     */
    public void setPlanStatus( PlanStatus planStatus ) {
        this.planStatus = planStatus;
    }

    /**
     * @return the beginning of the validity period, may be <code>null</code>
     */
    public Date getStartDateTime() {
        return startDateTime;
    }

    /**
     * @param startDateTime
     *            the beginning of the validity period, may be <code>null</code>
     */
    public void setStartDateTime( Date startDateTime ) {
        this.startDateTime = startDateTime;
    }

    /**
     * @return the end of the validity period, may be <code>null</code>
     */
    public Date getEndDateTime() {
        return endDateTime;
    }

    /**
     * @param endDateTime
     *            the end of the validity period, may be <code>null</code>
     */
    public void setEndDateTime( Date endDateTime ) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "XPlanMetadata [planStatus=" + planStatus + ", startDateTime=" + startDateTime + ", endDateTime="
               + endDateTime + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( endDateTime == null ) ? 0 : endDateTime.hashCode() );
        result = prime * result + ( ( planStatus == null ) ? 0 : planStatus.hashCode() );
        result = prime * result + ( ( startDateTime == null ) ? 0 : startDateTime.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        AdditionalPlanData other = (AdditionalPlanData) obj;
        if ( endDateTime == null ) {
            if ( other.endDateTime != null )
                return false;
        } else if ( !endDateTime.equals( other.endDateTime ) )
            return false;
        if ( planStatus != other.planStatus )
            return false;
        if ( startDateTime == null ) {
            if ( other.startDateTime != null )
                return false;
        } else if ( !startDateTime.equals( other.startDateTime ) )
            return false;
        return true;
    }

}
