/*-
 * #%L
 * xplan-validator-web - Modul zur Gruppierung aller Webapps
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
package de.latlon.xplan.validator.web.spring.config;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.configuration.ValidatorConfigurationParser;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.report.ReportArchiveGenerator;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.SemanticRulesConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.profile.SemanticProfiles;
import de.latlon.xplan.validator.semantic.profile.SemanticProfilesCreator;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplan.validator.web.server.service.ReportProvider;
import de.latlon.xplan.validator.web.server.service.ValidatorReportProvider;
import de.latlon.xplan.validator.wms.MapPreviewCreationException;
import de.latlon.xplan.validator.wms.MapPreviewManager;
import de.latlon.xplan.validator.wms.ValidatorWmsManager;
import de.latlon.xplan.validator.wms.config.ValidatorWmsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * XPlanValidatorWeb Application Configuration.
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 */
@Configuration
@Import(ValidatorWmsContext.class)
public class XPlanValidatorWebSpringConfig {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanValidatorWebSpringConfig.class);

	private static final String RULES_DIRECTORY = "/rules";

	@Bean
	public SyntacticValidator syntacticValidator() {
		return new SyntacticValidatorImpl();
	}

	@Bean
	public GeometricValidator geometricValidator() {
		return new GeometricValidatorImpl();
	}

	@Autowired
	private ResourceLoader resourceLoader;

	@Bean
	public SemanticValidator semanticValidator(XQuerySemanticValidatorConfigurationRetriever configurationRetriever)
			throws ConfigurationException {
		return new XQuerySemanticValidator(configurationRetriever);
	}

	@Bean
	public XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever(
			SemanticRulesConfiguration semanticRulesConfiguration) {
		return new XQuerySemanticValidatorConfigurationRetriever(semanticRulesConfiguration);
	}

	@Bean
	public SemanticRulesConfiguration semanticRulesConfiguration(ValidatorConfiguration validatorConfiguration) {
		Path validationRulesDirectory = validatorConfiguration.getValidationRulesDirectory();
		return new SemanticRulesConfiguration(validationRulesDirectory);
	}

	@Bean
	public SemanticProfiles semanticProfiles(ValidatorConfiguration validatorConfiguration,
			PropertiesLoader validatorPropertiesLoader,
			@Value("#{environment.XPLAN_VALIDATOR_PROFILES}") String activatedProfiles) throws ConfigurationException {
		List<String> activatedProfilesList = activatedProfiles != null ? Arrays.asList(activatedProfiles.split(","))
				: Collections.emptyList();
		SemanticProfilesCreator semanticProfilesCreator = new SemanticProfilesCreator(validatorConfiguration,
				validatorPropertiesLoader, resourceLoader);
		return semanticProfilesCreator.createSemanticProfiles(activatedProfilesList);
	}

	@Bean
	public ReportArchiveGenerator reportArchiveGenerator(ValidatorConfiguration validatorConfiguration) {
		return new ReportArchiveGenerator(validatorConfiguration);
	}

	@Bean
	public ReportWriter reportWriter() {
		return new ReportWriter();
	}

	@Bean
	public ReportProvider reportProvider() {
		return new ValidatorReportProvider();
	}

	@Bean
	public ValidatorConfiguration validatorConfiguration(PropertiesLoader validatorPropertiesLoader)
			throws IOException, ConfigurationException {
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		return validatorConfigurationParser.parse(validatorPropertiesLoader);
	}

	@Bean
	public XPlanValidator xplanValidator(GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
			SemanticValidator semanticValidator, SemanticProfiles semanticProfiles,
			ReportArchiveGenerator reportArchiveGenerator) {
		return new XPlanValidator(geometricValidator, syntacticValidator, semanticValidator,
				semanticProfiles.getProfileValidators(), reportArchiveGenerator);
	}

	@Bean
	public PropertiesLoader validatorPropertiesLoader() {
		return new SystemPropertyPropertiesLoader(ValidatorConfiguration.class);
	}

	@Bean
	public MapPreviewManager mapPreviewManager(Optional<ValidatorWmsManager> validatorWmsManager,
			GeometricValidator geometricValidator, ValidatorConfiguration validatorConfiguration) {
		if (!validatorWmsManager.isPresent()) {
			LOG.warn("ValidatorWmsManager is not configured. Map preview will not be available.");
			return null;
		}
		String validatorWmsEndpoint = validatorConfiguration.getValidatorWmsEndpoint();
		if (validatorWmsEndpoint == null) {
			LOG.warn("XPlanValidatorWMS endpoint URL is not configured. Map preview will not be available.");
			return null;
		}
		try {
			return new MapPreviewManager(validatorWmsManager.get(), geometricValidator, validatorWmsEndpoint);
		}
		catch (IllegalArgumentException | MapPreviewCreationException e) {
			LOG.error("Could not initialise ValidatorWmsManager. WMS resources cannot be created. Reason: {}",
					e.getMessage(), e);
		}
		return null;
	}

}
