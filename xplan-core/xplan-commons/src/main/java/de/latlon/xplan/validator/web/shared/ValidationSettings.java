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
package de.latlon.xplan.validator.web.shared;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Encapsulates all settings made for a validation run.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ValidationSettings implements Serializable {

	private static final long serialVersionUID = 6120360074178016611L;

	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9.()_-]*$")
	private String validationName;

	private List<ValidationType> validationTypes;

	private List<ValidationOption> extendedOptions;

	private List<String> profiles;

	public ValidationSettings() {
	}

	public ValidationSettings(String validationName, List<ValidationType> validationTypes,
			List<ValidationOption> extendedOptions) {
		this(validationName, validationTypes, Collections.emptyList(), extendedOptions);
	}

	public ValidationSettings(String validationName, List<ValidationType> validationTypes, List<String> profiles,
			List<ValidationOption> extendedOptions) {
		this.validationName = validationName;
		this.validationTypes = validationTypes;
		this.profiles = profiles;
		this.extendedOptions = extendedOptions;
	}

	public String getValidationName() {
		return validationName;
	}

	public void setValidationName(String validationName) {
		this.validationName = validationName;
	}

	public List<ValidationType> getValidationTypes() {
		return validationTypes;
	}

	public void setValidationTypes(List<ValidationType> validationTypes) {
		this.validationTypes = validationTypes;
	}

	public List<ValidationOption> getExtendedOptions() {
		return extendedOptions;
	}

	public void setExtendedOptions(List<ValidationOption> extendedOptions) {
		this.extendedOptions = extendedOptions;
	}

	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}

	public List<String> getProfiles() {
		if (profiles == null)
			return Collections.emptyList();
		return profiles;
	}

	@Override
	public String toString() {
		return "ValidationSettings{" + "validationName='" + validationName + '\'' + ", validationTypes="
				+ validationTypes + ", extendedOptions=" + extendedOptions + ", profiles=" + profiles + '}';
	}

}
