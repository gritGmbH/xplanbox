/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.manager.web.shared.edit;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

import static de.latlon.xplan.commons.util.TextPatternConstants.L_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.TEXT_PATTERN;

/**
 * Encapsulates the base data of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class BaseData implements Serializable {

	@Size(max = S_LENGTH)
	@Pattern(regexp = TEXT_PATTERN)
	@Valid
	private String planName;

	@Size(max = L_LENGTH)
	@Pattern(regexp = TEXT_PATTERN)
	@Valid
	private String description;

	@Valid
	private Date creationDate;

	@Valid
	private Date lossDate;

	@Valid
	private int planTypeCode = -1;

	@Valid
	private int otherPlanTypeCode = -1;

	@Valid
	private int methodCode = -1;

	@Valid
	private int legislationStatusCode = -1;

	@Valid
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
	 * @param methodCode may be <code>null</code>
	 * @param legislationStatusCode may be <code>null</code>
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
	 * @param methodCode the method to set, may be <code>null</code>
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
		return "BaseData {planName=" + planName + ", description=" + description + ", creationDate=" + creationDate
				+ ", lossDate=" + lossDate + ", planTypeCode=" + planTypeCode + ", otherPlanTypeCode="
				+ otherPlanTypeCode + ", methodCode=" + methodCode + ", legislationStatusCode=" + legislationStatusCode
				+ ", regulationDate=" + regulationDate + "}";
	}

}
