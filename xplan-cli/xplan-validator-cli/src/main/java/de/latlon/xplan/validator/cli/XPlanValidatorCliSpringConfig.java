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
package de.latlon.xplan.validator.cli;

import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.configuration.ValidatorConfigurationParser;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.report.ReportArchiveGenerator;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.SemanticRulesConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.profile.SemanticProfileValidator;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static java.nio.file.Paths.get;

/**
 * Defines the application context for the validator CLI
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 */
@Configuration
public class XPlanValidatorCliSpringConfig {

	@Bean
	@Lazy
	@Scope("prototype")
	public SemanticValidator semanticValidator(Path rulesPath) throws ConfigurationException {
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(rulesPath);
		XQuerySemanticValidatorConfigurationRetriever semanticValidatorConfigurationRetriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration);
		return new XQuerySemanticValidator(semanticValidatorConfigurationRetriever);
	}

	@Bean
	public List<SemanticProfileValidator> profileValidators(ValidatorConfiguration validatorConfiguration)
			throws ValidatorException {
		return Collections.emptyList();
	}

	@Bean
	public GeometricValidator geometricValidator() {
		return new GeometricValidatorImpl();
	}

	@Bean
	public SyntacticValidator syntacticValidator() {
		return new SyntacticValidatorImpl();
	}

	@Bean
	public XPlanValidator xplanValidator(GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
			SemanticValidator semanticValidator, List<SemanticProfileValidator> profileValidators,
			ReportArchiveGenerator reportArchiveGenerator) {
		return new XPlanValidator(geometricValidator, syntacticValidator, semanticValidator, profileValidators,
				reportArchiveGenerator);
	}

	@Bean
	public ReportArchiveGenerator reportArchiveGenerator(ValidatorConfiguration validatorConfiguration) {
		return new ReportArchiveGenerator(validatorConfiguration);
	}

	@Bean
	public Path rulesPath(ValidatorConfiguration validatorConfiguration) throws URISyntaxException {
		Path validationRulesDirectory = validatorConfiguration.getValidationRulesDirectory();
		if (validationRulesDirectory != null)
			return validationRulesDirectory;
		URL pathToRules = XPlanValidatorCliSpringConfig.class.getProtectionDomain().getCodeSource().getLocation();
		return get(pathToRules.toURI()).getParent().getParent().resolve("etc/rules");
	}

	@Bean
	public ValidatorConfiguration validatorConfiguration()
			throws IOException, ConfigurationException, URISyntaxException {
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		return validatorConfigurationParser.parse(validatorPropertiesLoader());
	}

	private PropertiesLoader validatorPropertiesLoader() throws URISyntaxException {
		return new ConfigurationDirectoryPropertiesLoader(retrieveEtcPath(), ValidatorConfiguration.class);
	}

	private Path retrieveEtcPath() throws URISyntaxException {
		URL jarPath = XPlanValidatorCliSpringConfig.class.getProtectionDomain().getCodeSource().getLocation();
		return get(jarPath.toURI()).getParent().getParent().resolve("etc");
	}

}
