/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.spring.config;

import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.inspire.plu.transformation.hale.HaleCliInspirePluTransformator;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.transformation.HaleXplan41ToXplan51Transformer;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.server.service.ManagerReportProvider;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.DeegreeWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
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
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadataParser;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplan.validator.web.server.service.ReportProvider;
import org.deegree.commons.config.DeegreeWorkspace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLANSYN_WMS_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLAN_MANAGER_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;
import static java.nio.file.Paths.get;

/**
 * Basic XPlanManagerWeb Application Configuration.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@Configuration
public class BasicSpringConfig {

	private static final String RULES_DIRECTORY = "/rules";

	@Bean
	public SyntacticValidator syntacticValidator() {
		return new SyntacticValidatorImpl();
	}

	@Bean
	public GeometricValidator geometricValidator() {
		return new GeometricValidatorImpl(true);
	}

	@Bean
	public SemanticValidator semanticValidator(ManagerConfiguration managerConfiguration, Path rulesPath)
			throws URISyntaxException, ValidatorException {
		RulesMetadataParser rulesMetadataParser = new RulesMetadataParser();
		RulesMetadata rulesMetadata = rulesMetadataParser.parserMetadata(rulesPath);
		XQuerySemanticValidatorConfigurationRetriever configRetriever = new XQuerySemanticValidatorConfigurationRetriever(
				rulesPath, rulesMetadata);
		return new XQuerySemanticValidator(configRetriever,
				managerConfiguration.getSemanticConformityLinkConfiguration());
	}

	@Bean
	public List<SemanticValidator> profileValidators(ValidatorConfiguration validatorConfiguration)
			throws ValidatorException {
		List<SemanticValidator> semanticValidators = new ArrayList<>();
		for (ValidatorProfile validatorProfile : validatorConfiguration.getValidatorProfiles()) {
			RulesMetadata rulesMetadata = new RulesMetadata(validatorProfile.getName(),
					validatorProfile.getDescription(), null, null);
			Path rulesPath = Paths.get(validatorProfile.getXqueryRulesDirectory());
			XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever = new XQuerySemanticValidatorConfigurationRetriever(
					rulesPath, rulesMetadata);
			XQuerySemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(
					xQuerySemanticValidatorConfigurationRetriever);
			semanticValidators.add(xQuerySemanticValidator);
		}
		return semanticValidators;
	}

	@Bean
	public XPlanValidator xplanValidator(GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
			SemanticValidator semanticValidator, List<SemanticValidator> profileValidators,
			ReportArchiveGenerator reportArchiveGenerator) {
		return new XPlanValidator(geometricValidator, syntacticValidator, semanticValidator, profileValidators,
				reportArchiveGenerator);
	}

	@Bean
	public XPlanDao xPlanDao(ManagerWorkspaceWrapper managerWorkspaceWrapper, CategoryMapper categoryMapper,
			ManagerConfiguration managerConfiguration) {
		return new XPlanDao(managerWorkspaceWrapper, categoryMapper, managerConfiguration);
	}

	@Bean
	public DeegreeWorkspaceWrapper deegreeWorkspaceWrapper() {
		return new DeegreeWorkspaceWrapper(DEFAULT_XPLANSYN_WMS_WORKSPACE);
	}

	@Bean
	public WmsWorkspaceWrapper wmsWorkspaceWrapper(DeegreeWorkspaceWrapper deegreeWorkspaceWrapper)
			throws WorkspaceException {
		return new WmsWorkspaceWrapper(deegreeWorkspaceWrapper.getWorkspaceInstance());
	}

	@Bean
	public XPlanManager xPlanManager(XPlanDao xPlanDao, XPlanArchiveCreator archiveCreator,
			ManagerWorkspaceWrapper managerWorkspaceWrapper, WorkspaceReloader workspaceReloader,
			InspirePluTransformator inspirePluTransformator, XPlanGmlTransformer xPlanGmlTransformer,
			WmsWorkspaceWrapper wmsWorkspaceWrapper) throws Exception {
		return new XPlanManager(xPlanDao, archiveCreator, managerWorkspaceWrapper, workspaceReloader,
				inspirePluTransformator, xPlanGmlTransformer, wmsWorkspaceWrapper);
	}

	@Bean
	public InternalIdRetriever internalIdRetriever(ManagerConfiguration managerConfiguration) throws Exception {
		return new InternalIdRetriever(managerConfiguration.getInternalIdRetrieverConfiguration());
	}

	@Bean
	public ReportArchiveGenerator reportArchiveGenerator() throws IOException, ConfigurationException {
		return new ReportArchiveGenerator(validatorConfiguration());
	}

	@Bean
	public ReportWriter reportWriter() {
		return new ReportWriter();
	}

	@Bean
	public ReportProvider reportProvider() {
		return new ManagerReportProvider();
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

	@Bean
	public XPlanArchiveCreator archiveCreator(CategoryMapper categoryMapper) throws ConfigurationException {
		return new XPlanArchiveCreator(categoryMapper);
	}

	@Bean
	public CategoryMapper categoryMapper(ManagerConfiguration managerConfiguration) throws ConfigurationException {
		return new CategoryMapper(managerConfiguration);
	}

	@Bean
	public ManagerConfiguration managerConfiguration(PropertiesLoader managerPropertiesLoader)
			throws ConfigurationException {
		return new ManagerConfiguration(managerPropertiesLoader);
	}

	@Bean
	public ManagerWorkspaceWrapper managerWorkspaceWrapper(ManagerConfiguration managerConfiguration)
			throws WorkspaceException {
		DeegreeWorkspace managerWorkspace = instantiateWorkspace(DEFAULT_XPLAN_MANAGER_WORKSPACE);
		ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper(managerWorkspace,
				managerConfiguration);
		return managerWorkspaceWrapper;
	}

	@Bean
	public WorkspaceReloader workspaceReloader() {
		return new WorkspaceReloader();
	}

	@Bean
	public InspirePluTransformator inspirePluTransformator(ManagerConfiguration managerConfiguration) {
		String pathToHaleCli = managerConfiguration.getPathToHaleCli();
		Path pathToHaleProjectDirectory = managerConfiguration.getPathToHaleProjectDirectory();
		if (pathToHaleCli != null && pathToHaleProjectDirectory != null)
			return new HaleCliInspirePluTransformator(pathToHaleCli, pathToHaleProjectDirectory);
		return null;
	}

	@Bean
	public XPlanGmlTransformer xPlanGmlTransformer(ManagerConfiguration managerConfiguration) {
		String pathToHaleCli = managerConfiguration.getPathToHaleCli();
		Path pathToHaleProjectDirectory = managerConfiguration.getPathToHaleProjectDirectory();
		if (pathToHaleCli != null && pathToHaleProjectDirectory != null) {
			HaleXplan41ToXplan51Transformer haleXplan41ToXplan51Transformer = new HaleXplan41ToXplan51Transformer(
					pathToHaleCli, pathToHaleProjectDirectory);
			return new XPlanGmlTransformer(haleXplan41ToXplan51Transformer);
		}
		return null;
	}

	@Bean
	public ValidatorConfiguration validatorConfiguration() throws IOException, ConfigurationException {
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		return validatorConfigurationParser.parse(new SystemPropertyPropertiesLoader(ValidatorConfiguration.class));
	}

	@Bean
	public PropertiesLoader managerPropertiesLoader() {
		return new SystemPropertyPropertiesLoader(ManagerConfiguration.class);
	}

	@Bean
	public Path rulesPath(ValidatorConfiguration validatorConfiguration) throws URISyntaxException {
		Path validationRulesDirectory = validatorConfiguration.getValidationRulesDirectory();
		if (validationRulesDirectory != null)
			return validationRulesDirectory;
		URI rulesPath = BasicSpringConfig.class.getResource(RULES_DIRECTORY).toURI();
		return get(rulesPath);
	}

}
