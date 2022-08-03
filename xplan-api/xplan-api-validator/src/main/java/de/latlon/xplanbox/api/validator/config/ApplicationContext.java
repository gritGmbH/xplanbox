/*-
 * #%L
 * xplan-api-validator - Modul zur Gruppierung der REST-API
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplanbox.api.validator.config;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.commons.feature.XPlanGmlParser;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
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
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadataParser;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.profile.DelegatingSemanticProfileValidator;
import de.latlon.xplan.validator.semantic.profile.SemanticProfileValidator;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplan.validator.wms.ValidatorWmsManager;
import de.latlon.xplanbox.api.commons.handler.SystemConfigHandler;
import org.deegree.commons.config.DeegreeWorkspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Paths.get;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Configuration
@ComponentScan(basePackages = { "de.latlon.xplanbox.api.validator.handler", "de.latlon.xplanbox.api.validator.v1" })
public class ApplicationContext {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationContext.class);

	private static final String RULES_DIRECTORY = "/rules";

	private static final String XPLAN_GML_WMS_WORKSPACE = "xplan-validator-wms-workspace";

	@Bean
	public SystemConfigHandler systemConfigHandler(XQuerySemanticValidatorConfigurationRetriever configurationRetriever,
			List<RulesMetadata> profileMetadata) {
		return new SystemConfigHandler(configurationRetriever, profileMetadata);
	}

	@Bean
	public Path uploadFolder() throws IOException {
		return createTempDirectory("xplan-validator");
	}

	@Bean
	public XPlanGmlParser xPlanGmlParser() {
		return new XPlanGmlParser();
	}

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
	public XQuerySemanticValidatorConfigurationRetriever configurationRetriever(Path rulesPath) {
		RulesMetadataParser rulesMetadataParser = new RulesMetadataParser();
		RulesMetadata rulesMetadata = rulesMetadataParser.parserMetadata(rulesPath);
		return new XQuerySemanticValidatorConfigurationRetriever(rulesPath, rulesMetadata);
	}

	@Bean
	public Map<ValidatorProfile, RulesMetadata> profilesAndMetadata(ValidatorConfiguration validatorConfiguration)
			throws ValidatorException {
		Map<ValidatorProfile, RulesMetadata> profilesAndMetadata = new HashMap<>();
		for (ValidatorProfile validatorProfile : validatorConfiguration.getValidatorProfiles()) {
			RulesMetadata newRulesMetadata = new RulesMetadata(validatorProfile.getName(),
					validatorProfile.getDescription(), validatorProfile.getVersion(), validatorProfile.getSource());
			profilesAndMetadata.put(validatorProfile, newRulesMetadata);
		}
		return profilesAndMetadata;
	}

	@Bean
	public List<RulesMetadata> profileMetadata(Map<ValidatorProfile, RulesMetadata> profilesAndMetadata) {
		return profilesAndMetadata.values().stream().collect(Collectors.toList());
	}

	@Bean
	public List<SemanticProfileValidator> profileValidators(Map<ValidatorProfile, RulesMetadata> profilesAndMetadata)
			throws ValidatorException {
		List<SemanticProfileValidator> semanticValidators = new ArrayList<>();
		for (Map.Entry<ValidatorProfile, RulesMetadata> profileAndMetadata : profilesAndMetadata.entrySet()) {
			RulesMetadata rulesMetadata = profileAndMetadata.getValue();
			ValidatorProfile validatorProfile = profileAndMetadata.getKey();
			Path rulesDirectory = Paths.get(validatorProfile.getXqueryRulesDirectory());
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
	public PropertiesLoader validatorPropertiesLoader() {
		return new SystemPropertyPropertiesLoader(ValidatorConfiguration.class);
	}

	@Bean
	public ValidatorConfiguration validatorConfiguration(PropertiesLoader validatorPropertiesLoader)
			throws IOException, ConfigurationException {
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		return validatorConfigurationParser.parse(validatorPropertiesLoader);
	}

	@Bean
	public ValidatorApiConfiguration validatorApiConfiguration(PropertiesLoader validatorPropertiesLoader)
			throws ConfigurationException {
		return new ValidatorApiConfiguration(validatorPropertiesLoader);
	}

	@Bean
	public ReportArchiveGenerator reportArchiveGenerator(ValidatorConfiguration validatorConfiguration) {
		return new ReportArchiveGenerator(validatorConfiguration);
	}

	@Bean
	public XPlanValidator xplanValidator(GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
			SemanticValidator semanticValidator, List<SemanticProfileValidator> profileValidators,
			ReportArchiveGenerator reportArchiveGenerator) {
		return new XPlanValidator(geometricValidator, syntacticValidator, semanticValidator, profileValidators,
				reportArchiveGenerator);
	}

	@Bean
	public ReportWriter reportWriter() {
		return new ReportWriter();
	}

	@Bean
	public ValidatorWmsManager validatorWmsManager(ValidatorConfiguration validatorConfiguration) {
		LOG.trace("Using validatorConfiguration: " + validatorConfiguration);
		String validatorWmsEndpoint = validatorConfiguration.getValidatorWmsEndpoint();
		if (validatorWmsEndpoint == null) {
			LOG.warn("XPlanValidatorWMS endpoint URL is not configured. Map preview will not be available.");
			return null;
		}
		try {
			XPlanSynthesizer synthesizer = new XPlanSynthesizer();
			Path workspaceLocation = Paths.get(DeegreeWorkspace.getWorkspaceRoot()).resolve(XPLAN_GML_WMS_WORKSPACE);
			return new ValidatorWmsManager(synthesizer, workspaceLocation);
		}
		catch (IOException | IllegalArgumentException e) {
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
		URI rulesPath = getClass().getResource(RULES_DIRECTORY).toURI();
		return get(rulesPath);
	}

}
