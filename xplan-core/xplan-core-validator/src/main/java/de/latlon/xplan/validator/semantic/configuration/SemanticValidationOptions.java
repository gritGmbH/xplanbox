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
package de.latlon.xplan.validator.semantic.configuration;

import de.latlon.xplan.validator.web.shared.ValidationOption;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public enum SemanticValidationOptions {

	IGNORE_XP(new ValidationOption("ignore-xp"), "xp"), IGNORE_SO(new ValidationOption("ignore-so"), "so"),
	NONE(new ValidationOption(), null);

	private ValidationOption option;

	private String directorName;

	private SemanticValidationOptions(ValidationOption option, String directorName) {
		this.option = option;
		this.directorName = directorName;
	}

	/**
	 * @return the name of the options, never <code>null</code>
	 */
	public String getOptionName() {
		return option.getName();
	}

	/**
	 * @param directoryName the name of the directory a rules is inside if ignored, never
	 * <code>null</code>
	 * @return the {@link SemanticValidationOptions} with the passed directory name or
	 * NONE option if no option exists
	 * @throws IllegalArgumentException if the optionName is <code>null</code>
	 */
	public static SemanticValidationOptions getByDirectoryName(String directoryName) {
		checkDirectoryNameParameter(directoryName);
		for (SemanticValidationOptions option : values()) {
			if (directoryName.equals(option.directorName))
				return option;
		}
		return NONE;
	}

	/**
	 * @param validationOption the name of the option, never <code>null</code>
	 * @return the {@link SemanticValidationOptions} with the passed option name or NONE
	 * option if no option exists
	 * @throws IllegalArgumentException if the optionName is <code>null</code>
	 */
	public static SemanticValidationOptions getByOption(ValidationOption validationOption) {
		checkOptionParameter(validationOption);
		for (SemanticValidationOptions option : values()) {
			if (validationOption.equals(option.option))
				return option;
		}
		return NONE;
	}

	private static void checkDirectoryNameParameter(String directoryName) {
		if (directoryName == null)
			throw new IllegalArgumentException("directory name must not be null!");
	}

	private static void checkOptionParameter(ValidationOption validationOption) {
		if (validationOption == null)
			throw new IllegalArgumentException("validationOption name must not be null!");
	}

}
