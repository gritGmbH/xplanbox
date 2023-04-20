/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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
package de.latlon.xplanbox.api.manager.config;

import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplan.core.manager.db.repository.ArtefactRepository;
import de.latlon.xplan.core.manager.db.repository.PlanRepository;
import de.latlon.xplan.core.manager.db.repository.PlanwerkWmsMetadataRepository;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.database.XPlanDbAdapter;
import de.latlon.xplan.manager.document.XPlanDocumentManager;
import de.latlon.xplan.manager.document.config.DocumentStorageContext;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.storage.StorageCleanUpManager;
import de.latlon.xplan.manager.storage.config.StorageCleanUpContext;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.config.RasterStorageContext;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.wmsconfig.raster.config.RasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.RasterEvaluation;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.XPlanRasterEvaluator;
import de.latlon.xplan.manager.wmsconfig.raster.storage.RasterStorage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.s3.config.AmazonS3RasterStorageContext;
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
import de.latlon.xplanbox.api.commons.handler.SystemConfigHandler;
import org.deegree.commons.config.DeegreeWorkspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLANSYN_WMS_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLAN_MANAGER_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;
import static java.nio.file.Paths.get;

/**
 * Spring Application Context for initialising XPlanManagerAPI components.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Configuration
@ComponentScan(basePackages = { "de.latlon.xplanbox.api.manager" })
@Import({ JpaContext.class, RasterStorageContext.class, AmazonS3RasterStorageContext.class,
		DocumentStorageContext.class, StorageCleanUpContext.class })
public class ApplicationContext {

	private static final String RULES_DIRECTORY = "/rules";

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private PlanwerkWmsMetadataRepository planwerkWmsMetadataRepository;

	@Autowired
	private ArtefactRepository artefactRepository;

	@Bean
	public XPlanDbAdapter xPlanDbAdapter(CategoryMapper categoryMapper) {
		return new XPlanDbAdapter(categoryMapper, planRepository, planwerkWmsMetadataRepository, artefactRepository);
	}

	@Bean
	public XPlanManager xPlanManager(XPlanSynthesizer xPlanSynthesizer, XPlanDao xPlanDao,
			XPlanArchiveCreator archiveCreator, ManagerWorkspaceWrapper managerWorkspaceWrapper,
			WorkspaceReloader workspaceReloader, WmsWorkspaceWrapper wmsWorkspaceWrapper,
			XPlanRasterEvaluator xPlanRasterEvaluator, XPlanRasterManager xPlanRasterManager,
			Optional<XPlanDocumentManager> xPlanDocumentManager, StorageCleanUpManager storageCleanUpManager)
			throws Exception {
		return new XPlanManager(xPlanSynthesizer, xPlanDao, archiveCreator, managerWorkspaceWrapper, workspaceReloader,
				null, wmsWorkspaceWrapper, xPlanRasterEvaluator, xPlanRasterManager, xPlanDocumentManager.orElse(null),
				storageCleanUpManager);
	}

	@Bean
	public SystemConfigHandler systemConfigHandler(XQuerySemanticValidatorConfigurationRetriever configurationRetriever,
			List<RulesMetadata> profileMetadata) {
		return new SystemConfigHandler(configurationRetriever, profileMetadata);
	}

	@Bean
	public SyntacticValidator syntacticValidator() {
		return new SyntacticValidatorImpl();
	}

	@Bean
	public GeometricValidator geometricValidator() {
		return new GeometricValidatorImpl(true);
	}

	@Bean
	public SemanticValidator semanticValidator(ManagerConfiguration managerConfiguration,
			XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever)
			throws URISyntaxException, ValidatorException {
		return new XQuerySemanticValidator(xQuerySemanticValidatorConfigurationRetriever,
				managerConfiguration.getSemanticConformityLinkConfiguration());
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
	public XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever(Path rulesPath) {
		RulesVersionParser rulesMetadataParser = new RulesVersionParser();
		RulesVersion rulesVersion = rulesMetadataParser.parserRulesVersion(rulesPath);
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
	public XPlanDao xPlanDao(CategoryMapper categoryMapper, ManagerWorkspaceWrapper managerWorkspaceWrapper,
			XPlanDbAdapter xPlanDbAdapter) {
		return new XPlanDao(managerWorkspaceWrapper, xPlanDbAdapter);
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
	public WmsWorkspaceWrapper wmsWorkspaceWrapper() throws WorkspaceException {
		DeegreeWorkspaceWrapper wmsWorkspace = new DeegreeWorkspaceWrapper(DEFAULT_XPLANSYN_WMS_WORKSPACE);
		return new WmsWorkspaceWrapper(wmsWorkspace.getWorkspaceInstance());
	}

	@Bean
	public XPlanRasterEvaluator xPlanRasterEvaluator(RasterEvaluation rasterEvaluation) {
		return new XPlanRasterEvaluator(rasterEvaluation);
	}

	@Bean
	public XPlanRasterManager xPlanRasterManager(RasterStorage rasterStorage, RasterConfigManager rasterConfigManager)
			throws WorkspaceException {
		return new XPlanRasterManager(rasterStorage, rasterConfigManager);
	}

	@Bean
	public XPlanInsertManager xPlanInsertManager(XPlanSynthesizer xPlanSynthesizer, XPlanDao xPlanDao,
			XPlanExporter xPlanExporter, ManagerWorkspaceWrapper managerWorkspaceWrapper,
			XPlanRasterManager xPlanRasterManager, Optional<XPlanDocumentManager> xPlanDocumentManager,
			ManagerConfiguration managerConfiguration, WorkspaceReloader workspaceReloader) throws Exception {
		SortConfiguration sortConfiguration = createSortConfiguration(managerConfiguration);
		SortPropertyReader sortPropertyReader = new SortPropertyReader(sortConfiguration);

		return new XPlanInsertManager(xPlanSynthesizer, xPlanDao, xPlanExporter, xPlanRasterManager,
				xPlanDocumentManager.orElse(null), workspaceReloader, managerConfiguration, managerWorkspaceWrapper,
				sortPropertyReader);
	}

	@Bean
	public XPlanExporter xPlanExporter() {
		return new XPlanExporter();
	}

	@Bean
	public XPlanDeleteManager xPlanDeleteManager(XPlanDao xPlanDao, WorkspaceReloader workspaceReloader,
			XPlanRasterManager xPlanRasterManager, StorageCleanUpManager storageCleanUpManager) {
		return new XPlanDeleteManager(xPlanDao, xPlanRasterManager, storageCleanUpManager, workspaceReloader);
	}

	@Bean
	public InternalIdRetriever internalIdRetriever(ManagerConfiguration managerConfiguration) throws Exception {
		return new InternalIdRetriever(managerConfiguration.getInternalIdRetrieverConfiguration());
	}

	@Bean
	public ReportArchiveGenerator reportArchiveGenerator(ValidatorConfiguration validatorConfiguration)
			throws IOException, ConfigurationException {
		return new ReportArchiveGenerator(validatorConfiguration);
	}

	@Bean
	public ReportWriter reportWriter() {
		return new ReportWriter();
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
	public ManagerApiConfiguration managerApiConfiguration(PropertiesLoader managerPropertiesLoader)
			throws ConfigurationException {
		return new ManagerApiConfiguration(managerPropertiesLoader);
	}

	@Bean
	public WorkspaceReloader workspaceReloader(ManagerConfiguration managerConfiguration) {
		return new WorkspaceReloader(managerConfiguration.getWorkspaceReloaderConfiguration());
	}

	@Bean
	public ValidatorConfiguration validatorConfiguration(PropertiesLoader validatorPropertiesLoader)
			throws IOException, ConfigurationException {
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		return validatorConfigurationParser.parse(validatorPropertiesLoader);
	}

	@Bean
	public PropertiesLoader managerPropertiesLoader() {
		return new SystemPropertyPropertiesLoader(ManagerConfiguration.class);
	}

	@Bean
	public PropertiesLoader validatorPropertiesLoader() {
		return new SystemPropertyPropertiesLoader(ValidatorConfiguration.class);
	}

	@Bean
	public Path rulesPath(ValidatorConfiguration validatorConfiguration) throws URISyntaxException {
		Path validationRulesDirectory = validatorConfiguration.getValidationRulesDirectory();
		if (validationRulesDirectory != null)
			return validationRulesDirectory;
		URI rulesPath = getClass().getResource(RULES_DIRECTORY).toURI();
		return get(rulesPath);
	}

	@Bean
	public XPlanSynthesizer xPlanSynthesizer(SynRulesAccessor synRulesAccessor) {
		return new XPlanSynthesizer(synRulesAccessor);
	}

	@Bean
	public SynRulesAccessor synRulesAccessor(ManagerConfiguration managerConfiguration) {
		if (managerConfiguration != null)
			return new SynRulesAccessor(managerConfiguration.getSynthesizerConfigurationDirectory());
		return new SynRulesAccessor();
	}

	private SortConfiguration createSortConfiguration(ManagerConfiguration managerConfiguration) {
		if (managerConfiguration != null)
			return managerConfiguration.getSortConfiguration();
		return new SortConfiguration();
	}

}
