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
package de.latlon.xplan.manager.web.shared.edit;

import java.util.Date;

/**
 * Encapsulates the base data of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class BaseData {

	private String planName;

	private String description;

	private Date creationDate;

	private Date lossDate;

	private int planTypeCode = -1;

	private int otherPlanTypeCode = -1;

	private int methodCode = -1;

	private int legislationStatusCode = -1;

	private Date regulationDate;

	public BaseData() {
	}

	/**
	 * @param planName may be <code>null</code>
	 * @param description may be <code>null</code>
	 * @param creationDate may be <code>null</code>
	 * @param lossDate may be <code>null</code>
	 * @param planTypeCode may be <code>null</code>
	 * @param otherPlanTypeCode may be <code>null</code>
	 * @param verfahren may be <code>null</code>
	 * @param rechtsstand may be <code>null</code>
	 * @param regulationDate may be <code>null</code>
	 */
	public BaseData(String planName, String description, Date creationDate, Date lossDate, int planTypeCode,
			int otherPlanTypeCode, int methodCode, int legislationStatusCode, Date regulationDate) {
		this.planName = planName;
		this.description = description;
		this.creationDate = creationDate;
		this.lossDate = lossDate;
		this.planTypeCode = planTypeCode;
		this.otherPlanTypeCode = otherPlanTypeCode;
		this.methodCode = methodCode;
		this.legislationStatusCode = legislationStatusCode;
		this.regulationDate = regulationDate;
	}

	/**
	 * @return the planName, may be <code>null</code>
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * @param planName the planName to set, may be <code>null</code>
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/**
	 * @return the description, may be <code>null</code>
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set, may be <code>null</code>
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the creationDate, may be <code>null</code>
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set, may be <code>null</code>
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the lossDate, may be <code>null</code>
	 */
	public Date getLossDate() {
		return lossDate;
	}

	/**
	 * @param lossDate the lossDate to set, may be <code>null</code>
	 */
	public void setLossDate(Date lossDate) {
		this.lossDate = lossDate;
	}

	/**
	 * @return the planTypeCode, may be <code>null</code>
	 */
	public int getPlanTypeCode() {
		return planTypeCode;
	}

	/**
	 * @param planTypeCode the planTypeCode to set, may be <code>null</code>
	 */
	public void setPlanTypeCode(int planTypeCode) {
		this.planTypeCode = planTypeCode;
	}

	/**
	 * @return the otherPlanTypeCode, may be <code>null</code>
	 */
	public int getOtherPlanTypeCode() {
		return otherPlanTypeCode;
	}

	/**
	 * @param otherPlanTypeCode the otherPlanTypeCode to set, may be <code>null</code>
	 */
	public void setOtherPlanTypeCode(int otherPlanTypeCode) {
		this.otherPlanTypeCode = otherPlanTypeCode;
	}

	/**
	 * @return the methodCode, may be <code>null</code>
	 */
	public int getMethodCode() {
		return methodCode;
	}

	/**
	 * @param verfahren the method to set, may be <code>null</code>
	 */
	public void setMethodCode(int methodCode) {
		this.methodCode = methodCode;
	}

	/**
	 * @return the legislationStatusCode, may be <code>null</code>
	 */
	public int getLegislationStatusCode() {
		return legislationStatusCode;
	}

	/**
	 * @param legislationStatusCode the legislationStatusCode to set, may be
	 * <code>null</code>
	 */
	public void setLegislationStatusCode(int legislationStatusCode) {
		this.legislationStatusCode = legislationStatusCode;
	}

	/**
	 * @return the regulationDate, may be <code>null</code>
	 */
	public Date getRegulationDate() {
		return regulationDate;
	}

	/**
	 * @param regulationDate the regulationDate to set, may be <code>null</code>
	 */
	public void setRegulationDate(Date regulationDate) {
		this.regulationDate = regulationDate;
	}

	@Override
	public String toString() {
		return "BaseData [planName=" + planName + ", description=" + description + ", creationDate=" + creationDate
				+ ", lossDate=" + lossDate + ", planTypeCode=" + planTypeCode + ", otherPlanTypeCode="
				+ otherPlanTypeCode + ", methodCode=" + methodCode + ", legislationStatusCode=" + legislationStatusCode
				+ ", regulationDate=" + regulationDate + "]";
	}

}
