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
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.latlon.xplan.validator.cli.options.CliOptionsParser.CLIParams.NAME;
import static de.latlon.xplan.validator.cli.options.CliOptionsParser.CLIParams.VALIDATE;
import static de.latlon.xplan.validator.cli.options.CliOptionsParser.CLIParams.VALIDATE_TYPE;
import static de.latlon.xplan.validator.cli.options.CliOptionsParser.CLIParams.VO;
import static de.latlon.xplan.validator.cli.options.CliOptionsParser.CLIParams.is;
import static de.latlon.xplan.validator.web.shared.ValidationType.GEOMETRIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SEMANTIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SYNTACTIC;

/**
 * Parses CLI Options from argument array
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class CliOptionsParser {

	private static final boolean HAS_ARG = true;

	/**
	 * Parse command line options for the XPlanValidator CLI
	 * @param args never <code>null</code>, may contain the following name: the name of
	 * the validation run validate: path to the archive to validate vo: validation options
	 * vtype[semantic, geometric, syntactic]: type of validation, defaults to semantic
	 * @return CliOptions parsed from the arguments
	 * @throws ParseException
	 */
	public CliOptions parse(String[] args) throws ParseException {
		CommandLine commandLine = (new BasicParser()).parse(createOptions(), args);
		File archive = parseArchive(commandLine);
		String validationName = parseValidationName(commandLine, archive);
		List<ValidationOption> voOptions = parseValidationOptions(commandLine.getOptionValues(is(VO)));
		List<ValidationType> validationTypes = parseValidationTypeSemanticIfNone(
				commandLine.getOptionValue(is(CLIParams.VALIDATE_TYPE)));
		return new CliOptions(validationName, voOptions, archive, validationTypes);
	}

	private File parseArchive(CommandLine commandLine) {
		return new File(commandLine.getOptionValue(is(VALIDATE)));
	}

	private String parseValidationName(CommandLine commandLine, File defaultValue) {
		String name = commandLine.getOptionValue(is(NAME));
		if (name != null)
			return name;
		String fileName = defaultValue.getName();
		return fileName.substring(0, fileName.indexOf("."));
	}

	public Options createOptions() {
		Options options = new Options();
		Option zipFileNameOption = new Option(is(VALIDATE), HAS_ARG, "zip file path");
		Option nameOption = new Option(is(NAME), HAS_ARG, "name of the validation");
		Option voOption = new Option(is(VO), HAS_ARG,
				"validation options, possible values are: skip-flaechenschluss=true, skip-geltungsbereich=true, skip-laufrichtung=true. Each value must be passed as single options, e.g: -vo skip-geltungsbereich=true -vo skip-laufrichtung=true");
		Option vTypeOption = new Option(is(VALIDATE_TYPE), HAS_ARG,
				"values: syntax, geometric, semantic. multiple types must be comma separated");
		nameOption.setRequired(false);
		zipFileNameOption.setRequired(true);
		voOption.setRequired(false);
		vTypeOption.setRequired(false);
		options.addOption(zipFileNameOption).addOption(nameOption).addOption(vTypeOption).addOption(voOption);
		return options;
	}

	private List<ValidationType> parseValidationTypeSemanticIfNone(String s) {
		if (s == null) {
			return Arrays.asList(ValidationType.values());
		}
		List<ValidationType> validationTypes = new ArrayList<>();
		if (s.contains("syntax"))
			validationTypes.add(SYNTACTIC);
		if (s.contains("geometric"))
			validationTypes.add(GEOMETRIC);
		if (s.contains("semantic"))
			validationTypes.add(SEMANTIC);
		return validationTypes;
	}

	/**
	 * Parses validation related CLI options
	 * @param optionValues the cli options, never <code>null</code>
	 * @return List of validation options, never <code>null</code>
	 */
	List<ValidationOption> parseValidationOptions(String[] optionValues) {
		List<ValidationOption> validationOptions = new ArrayList<>();
		if (optionValues != null)
			for (String optionValue : optionValues) {
				if (optionValue.contains("=")) {
					String[] nameAndArgument = optionValue.split("=", 2);
					validationOptions.add(new ValidationOption(nameAndArgument[0], nameAndArgument[1]));
				}
				else {
					validationOptions.add(new ValidationOption(optionValue));
				}
			}
		return validationOptions;
	}

	/**
	 * Discriminates CLI params
	 */
	enum CLIParams {

		NAME("name"), VALIDATE("validate"), VO("vo"), VALIDATE_TYPE("vtype");

		private final String option;

		CLIParams(String option) {
			this.option = option;
		}

		public String option() {
			return option;
		}

		static String is(CLIParams param) {
			return param.option();
		}

	}

}
