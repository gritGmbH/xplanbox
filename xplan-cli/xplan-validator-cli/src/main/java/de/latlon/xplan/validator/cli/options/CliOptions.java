/*-
 * #%L
 * xplan-validator-cli - Kommandozeilentool des XPlan Validators
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
package de.latlon.xplan.validator.cli.options;

import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationType;

import java.io.File;
import java.util.List;

/**
 * Encapsulates the CLI options
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class CliOptions {

	private final String validationName;

	private final List<ValidationOption> voOptions;

	private final List<ValidationType> validationTypes;

	private final File archive;

	public CliOptions(String validationName, List<ValidationOption> voOptions, File archive,
			List<ValidationType> validationTypes) {
		this.validationName = validationName;
		this.voOptions = voOptions;
		this.archive = archive;
		this.validationTypes = validationTypes;
	}

	public String getValidationName() {
		return validationName;
	}

	public List<ValidationOption> getVoOptions() {
		return voOptions;
	}

	public File getArchive() {
		return archive;
	}

	public List<ValidationType> getValidationTypes() {
		return validationTypes;
	}

}
