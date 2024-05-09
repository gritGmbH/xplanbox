/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.validator.configuration;

import java.nio.file.Path;
import java.util.List;

/**
 * Encapsulates the validator configuration.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class ValidatorConfiguration {

	private final Path validationReportDirectory;

	private final Path validationRulesDirectory;

	private String validatorWmsEndpoint;

	private List<ValidatorProfile> validatorProfiles;

	/**
	 * @param validationReportDirectory directory where validation reports are saved,
	 * never <code>null</code>
	 * @param validationRulesDirectory directory where validation rules are stored, never
	 * <code>null</code>
	 * @param validatorWmsEndpoint XPlanValidatorWMS endpoint, may be <code>null</code>
	 * @param validatorProfiles configured validator profiles, may be <code>empty</code>
	 * but never <code>null</code>
	 */
	public ValidatorConfiguration(Path validationReportDirectory, Path validationRulesDirectory,
			String validatorWmsEndpoint, List<ValidatorProfile> validatorProfiles) {
		this.validatorWmsEndpoint = validatorWmsEndpoint;
		checkParameters(validationReportDirectory);
		this.validationReportDirectory = validationReportDirectory;
		this.validationRulesDirectory = validationRulesDirectory;
		this.validatorProfiles = validatorProfiles;
	}

	/**
	 * Returns the directory where validation reports are saved.
	 * @return directory where validation reports are saved, never <code>null</code>
	 */
	public Path getValidationReportDirectory() {
		return validationReportDirectory;
	}

	/**
	 * Returns the directory containing the semantic validation rules.
	 * @return directory containing the semantic validation rules, may be
	 * <code>null</code>
	 */
	public Path getValidationRulesDirectory() {
		return validationRulesDirectory;
	}

	/**
	 * Returns the configured XPlanValidatorWMS endpoint.
	 * @return XPlanValidatorWMS endpoint, may be <code>null</code>
	 */
	public String getValidatorWmsEndpoint() {
		return validatorWmsEndpoint;
	}

	/**
	 * Returns the configured validator profiles
	 * @return validatorProfiles may be <code>empty</code> but never <code>null</code>
	 */
	public List<ValidatorProfile> getValidatorProfiles() {
		return validatorProfiles;
	}

	private void checkParameters(Path validationReportDirectory) {
		if (validationReportDirectory == null)
			throw new IllegalArgumentException("validationReportDirectory must not be null!");
	}

	@Override
	public String toString() {
		return "ValidatorConfiguration{" + "validationReportDirectory=" + validationReportDirectory
				+ ", validationRulesDirectory=" + validationRulesDirectory + ", validatorWmsEndpoint='"
				+ validatorWmsEndpoint + '\'' + '}';
	}

}
