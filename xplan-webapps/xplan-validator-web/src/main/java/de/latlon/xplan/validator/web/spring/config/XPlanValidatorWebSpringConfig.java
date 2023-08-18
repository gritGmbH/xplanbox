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
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.configuration.ValidatorConfigurationParser;
import de.latlon.xplan.validator.configuration.ValidatorProfile;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.report.ReportArchiveGenerator;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.message.FileRulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersion;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersionParser;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.profile.DelegatingSemanticProfileValidator;
import de.latlon.xplan.validator.semantic.profile.SemanticProfileValidator;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.file.Paths.get;

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

	@Bean
	public SemanticValidator semanticValidator(XQuerySemanticValidatorConfigurationRetriever configurationRetriever)
			throws ValidatorException {
		return new XQuerySemanticValidator(configurationRetriever);
	}

	@Bean
	public Map<ValidatorProfile, RulesMetadata> profilesAndMetadata(ValidatorConfiguration validatorConfiguration,
			PropertiesLoader validatorPropertiesLoader) throws ValidatorException {
		Map<ValidatorProfile, RulesMetadata> profilesAndMetadata = new HashMap<>();
		for (ValidatorProfile validatorProfile : validatorConfiguration.getValidatorProfiles()) {
			String profileId = validatorProfile.getId();
			Path rulesDirectory = validatorPropertiesLoader.resolveDirectory("profiles").resolve(profileId);
			RulesVersionParser rulesVersionParser = new RulesVersionParser();
			RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion(rulesDirectory);
			RulesMetadata newRulesMetadata = new RulesMetadata(profileId, validatorProfile.getName(),
					validatorProfile.getDescription(), rulesVersion.getVersion(), rulesVersion.getSource());
			profilesAndMetadata.put(validatorProfile, newRulesMetadata);
		}
		return profilesAndMetadata;
	}

	@Bean
	public List<RulesMetadata> profileMetadata(Map<ValidatorProfile, RulesMetadata> profilesAndMetadata) {
		return profilesAndMetadata.values().stream().collect(Collectors.toList());
	}

	@Bean
	public List<SemanticProfileValidator> profileValidators(Map<ValidatorProfile, RulesMetadata> profilesAndMetadata,
			PropertiesLoader validatorPropertiesLoader) throws ValidatorException {
		List<SemanticProfileValidator> semanticValidators = new ArrayList<>();
		for (Map.Entry<ValidatorProfile, RulesMetadata> profileAndMetadata : profilesAndMetadata.entrySet()) {
			RulesMetadata rulesMetadata = profileAndMetadata.getValue();
			ValidatorProfile validatorProfile = profileAndMetadata.getKey();
			String profileId = validatorProfile.getId();
			Path rulesDirectory = validatorPropertiesLoader.resolveDirectory("profiles").resolve(profileId);
			FileRulesMessagesAccessor messagesAccessor = new FileRulesMessagesAccessor(rulesDirectory);
			XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever = new XQuerySemanticValidatorConfigurationRetriever(
					rulesDirectory, rulesMetadata, messagesAccessor);
			XQuerySemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(
					xQuerySemanticValidatorConfigurationRetriever);
			semanticValidators
				.add(new DelegatingSemanticProfileValidator(rulesMetadata.getId(), xQuerySemanticValidator));
		}
		return semanticValidators;
	}

	@Bean
	public XQuerySemanticValidatorConfigurationRetriever configurationRetriever(Path rulesPath) {
		RulesVersionParser rulesVersionParser = new RulesVersionParser();
		RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion(rulesPath);
		RulesMetadata rulesMetadata = new RulesMetadata(rulesVersion);
		return new XQuerySemanticValidatorConfigurationRetriever(rulesPath, rulesMetadata);
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

	@Bean
	public Path rulesPath(ValidatorConfiguration validatorConfiguration) throws URISyntaxException {
		Path validationRulesDirectory = validatorConfiguration.getValidationRulesDirectory();
		if (validationRulesDirectory != null)
			return validationRulesDirectory;
		URI rulesPath = XPlanValidatorWebSpringConfig.class.getResource(RULES_DIRECTORY).toURI();
		return get(rulesPath);
	}

}
