/*-
 * #%L
 * xplan-cli-tools - Kommandozeilenwerkzeuge fuer die xPlanBox
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
package de.latlon.xplanbox.cli.validate;

import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;
import de.latlon.xplanbox.cli.validate.config.ValidateFileContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import static de.latlon.xplan.validator.web.shared.ValidationType.GEOMETRIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SEMANTIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SYNTACTIC;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "file", description = "Validate a XPlanArchive or XPlanGML file.",
		subcommands = { CommandLine.HelpCommand.class })
public class ValidateFileSubcommand implements Callable<Integer> {

	private static final Logger LOG = LoggerFactory.getLogger(ValidateFileSubcommand.class);

	@CommandLine.Option(names = { "-f", "--file" }, required = true)
	private File file;

	@CommandLine.Option(names = { "-n", "--name" })
	private String validationName;

	@CommandLine.Option(names = { "-t", "--type" }, split = ",", description = "values: syntax, geometric, semantic")
	private String[] type;

	@CommandLine.Option(names = { "-o", "--option" }, split = ",",
			description = "validation options, possible values are: skip-flaechenschluss, skip-geltungsbereich, skip-laufrichtung")
	private String[] option;

	@Override
	public Integer call() {
		try {
			ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ValidateFileContext.class);
			XPlanValidator xPlanValidator = applicationContext.getBean(XPlanValidator.class);
			LOG.info("Validate file {}", file.getName());
			List<ValidationOption> validationOptions = parseValidationOptions();
			List<ValidationType> validationTypes = parseValidationTypes();
			ValidationSettings settings = new ValidationSettings(validationName, validationTypes, validationOptions);
			xPlanValidator.validate(settings, file, file.getName());
			return 0;
		}
		catch (ValidatorException | IOException | ReportGenerationException e) {
			LOG.error("An error occurred.", e);
			return 1;
		}
	}

	private List<ValidationOption> parseValidationOptions() {
		if (option == null || option.length < 1)
			return Collections.emptyList();
		List<ValidationOption> validationOptions = new ArrayList<>();
		for (String option : option) {
			validationOptions.add(new ValidationOption(option, "true"));
		}
		return validationOptions;
	}

	private List<ValidationType> parseValidationTypes() {
		if (type == null || type.length < 1)
			return Arrays.asList(ValidationType.values());
		List<String> types = Arrays.asList(type);
		List<ValidationType> validationTypes = new ArrayList<>();
		if (types.contains("syntax"))
			validationTypes.add(SYNTACTIC);
		if (types.contains("geometric"))
			validationTypes.add(GEOMETRIC);
		if (types.contains("semantic"))
			validationTypes.add(SEMANTIC);
		return validationTypes;
	}

}
