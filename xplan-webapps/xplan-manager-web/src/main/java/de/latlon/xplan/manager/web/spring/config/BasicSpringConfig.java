/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.spring.config;

import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.core.manager.db.repository.ArtefactRepository;
import de.latlon.xplan.core.manager.db.repository.PlanRepository;
import de.latlon.xplan.core.manager.db.repository.PlanwerkWmsMetadataRepository;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.inspire.plu.transformation.hale.HaleCliInspirePluTransformator;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDbAdapter;
import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.document.XPlanDocumentManager;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.inspireplu.InspirePluPublisher;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import de.latlon.xplan.manager.metadata.MetadataCouplingHandler;
import de.latlon.xplan.manager.storage.StorageCleanUpManager;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import de.latlon.xplan.manager.transaction.AttachmentUrlHandler;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanEditManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.transaction.service.XPlanDeleteService;
import de.latlon.xplan.manager.transaction.service.XPlanEditService;
import de.latlon.xplan.manager.transaction.service.XPlanInsertService;
import de.latlon.xplan.manager.web.server.service.ManagerReportProvider;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.wmsconfig.raster.config.RasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.RasterEvaluation;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.XPlanRasterEvaluator;
import de.latlon.xplan.manager.wmsconfig.raster.storage.RasterStorage;
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
import de.latlon.xplan.validator.web.server.service.ReportProvider;
import org.deegree.commons.config.DeegreeWorkspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
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
 * Spring Application Context for initialising XPlanManagerWeb components.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Configuration
public class BasicSpringConfig {

	private static final String RULES_DIRECTORY = "/rules";

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private PlanwerkWmsMetadataRepository planwerkWmsMetadataRepository;

	@Autowired
	private ArtefactRepository artefactRepository;

	@Bean
	public SyntacticValidator syntacticValidator() {
		return new SyntacticValidatorImpl();
	}

	@Bean
	public GeometricValidator geometricValidator() {
		return new GeometricValidatorImpl(true);
	}

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Bean
	public SemanticValidator semanticValidator(ManagerConfiguration managerConfiguration,
			XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever)
			throws ValidatorException {
		return new XQuerySemanticValidator(xQuerySemanticValidatorConfigurationRetriever,
				managerConfiguration.getSemanticConformityLinkConfiguration());
	}

	@Bean
	public XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever(Path rulesPath) {
		RulesVersionParser rulesVersionParser = new RulesVersionParser();
		RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion(rulesPath);
		RulesMetadata rulesMetadata = new RulesMetadata(rulesVersion);
		return new XQuerySemanticValidatorConfigurationRetriever(rulesPath, rulesMetadata);
	}

	@Bean
	public Map<ValidatorProfile, RulesMetadata> profilesAndMetadata(ValidatorConfiguration validatorConfiguration,
			PropertiesLoader validatorPropertiesLoader) {
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
	public XPlanValidator xplanValidator(GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
			SemanticValidator semanticValidator, List<SemanticProfileValidator> profileValidators,
			ReportArchiveGenerator reportArchiveGenerator) {
		return new XPlanValidator(geometricValidator, syntacticValidator, semanticValidator, profileValidators,
				reportArchiveGenerator);
	}

	@Bean
	public XPlanDbAdapter xPlanDbAdapter(CategoryMapper categoryMapper) {
		return new XPlanDbAdapter(categoryMapper, planRepository, planwerkWmsMetadataRepository, artefactRepository);
	}

	@Bean
	public XPlanManagerDao xPlanManagerDao(ManagerWorkspaceWrapper managerWorkspaceWrapper,
			XPlanDbAdapter xPlanDbAdapter, XPlanSynthesizer xPlanSynthesizer, XPlanExporter xPlanExporter,
			Optional<AttachmentUrlHandler> attachmentUrlHandler) {
		return new XPlanManagerDao(managerWorkspaceWrapper, xPlanDbAdapter, xPlanSynthesizer,
				attachmentUrlHandler.orElse(null), xPlanExporter, applicationEventPublisher);
	}

	@Bean
	public ManagerWorkspaceWrapper managerWorkspaceWrapper(ManagerConfiguration managerConfiguration)
			throws WorkspaceException {
		DeegreeWorkspace managerWorkspace = instantiateWorkspace(DEFAULT_XPLAN_MANAGER_WORKSPACE);
		return new ManagerWorkspaceWrapper(managerWorkspace, managerConfiguration);
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
		return new XPlanRasterManager(rasterStorage, rasterConfigManager, applicationEventPublisher);
	}

	@Bean
	public XPlanManager xPlanManager(XPlanManagerDao xPlanManagerDao, XPlanArchiveCreator archiveCreator,
			ManagerWorkspaceWrapper managerWorkspaceWrapper, WmsWorkspaceWrapper wmsWorkspaceWrapper,
			XPlanExporter xPlanExporter, XPlanRasterEvaluator xPlanRasterEvaluator,
			XPlanRasterManager xPlanRasterManager, SortPropertyReader sortPropertyReader,
			InspirePluPublisher inspirePluPublisher, XPlanInsertManager xPlanInsertManager,
			XPlanEditManager xPlanEditManager, XPlanDeleteManager xPlanDeleteManager) throws Exception {
		return new XPlanManager(xPlanManagerDao, archiveCreator, managerWorkspaceWrapper, wmsWorkspaceWrapper,
				xPlanExporter, xPlanRasterEvaluator, xPlanRasterManager, sortPropertyReader, inspirePluPublisher,
				xPlanInsertManager, xPlanEditManager, xPlanDeleteManager);
	}

	@Bean
	public XPlanInsertManager xPlanInsertManager(XPlanSynthesizer xPlanSynthesizer, XPlanManagerDao xPlanManagerDao,
			XPlanRasterManager xPlanRasterManager, ManagerConfiguration managerConfiguration,
			WorkspaceReloader workspaceReloader, SortPropertyReader sortPropertyReader,
			XPlanInsertService xPlanInsertService, Optional<MetadataCouplingHandler> metadataCouplingHandler) {
		return new XPlanInsertManager(xPlanSynthesizer, xPlanManagerDao, xPlanRasterManager, workspaceReloader,
				managerConfiguration, sortPropertyReader, xPlanInsertService, metadataCouplingHandler.orElse(null));
	}

	@Bean
	public XPlanInsertService xPlanInsertService(XPlanManagerDao xPlanManagerDao,
			Optional<XPlanDocumentManager> xPlanDocumentManager) {
		return new XPlanInsertService(xPlanManagerDao, xPlanDocumentManager.orElse(null));
	}

	@Bean
	public XPlanEditManager xPlanEditManager(XPlanSynthesizer xPlanSynthesizer, XPlanManagerDao xPlanManagerDao,
			XPlanExporter xPlanExporter, ManagerWorkspaceWrapper managerWorkspaceWrapper,
			WorkspaceReloader workspaceReloader, XPlanRasterManager xPlanRasterManager,
			SortPropertyReader sortPropertyReader, XPlanEditService xPlanEditService,
			MetadataCouplingHandler metadataCouplingHandler, Optional<AttachmentUrlHandler> attachmentUrlHandler) {
		return new XPlanEditManager(xPlanSynthesizer, xPlanManagerDao, xPlanExporter, xPlanRasterManager,
				workspaceReloader, managerWorkspaceWrapper.getConfiguration(), sortPropertyReader, xPlanEditService,
				metadataCouplingHandler, attachmentUrlHandler.orElse(null));
	}

	@Bean
	public XPlanEditService xPlanEditService(XPlanManagerDao xPlanManagerDao,
			Optional<XPlanDocumentManager> xPlanDocumentManager) {
		return new XPlanEditService(xPlanManagerDao, xPlanDocumentManager.orElse(null));
	}

	@Bean
	public XPlanDeleteManager xPlanDeleteManager(WorkspaceReloader workspaceReloader,
			XPlanRasterManager xPlanRasterManager, XPlanDeleteService xPlanDeleteService) {
		return new XPlanDeleteManager(xPlanRasterManager, workspaceReloader, xPlanDeleteService);
	}

	@Bean
	public XPlanDeleteService xPlanDeleteService(XPlanManagerDao xPlanManagerDao,
			StorageCleanUpManager storageCleanUpManager) {
		return new XPlanDeleteService(xPlanManagerDao, storageCleanUpManager, applicationEventPublisher);
	}

	@Bean
	public AttachmentUrlHandler attachmentUrlHandler(ManagerConfiguration managerConfiguration) {
		String documentUrl = managerConfiguration.getEnvironmentVariableValue("XPLAN_DOCUMENT_URL_PUBLIC");
		if (documentUrl != null)
			return new AttachmentUrlHandler(documentUrl);
		return null;
	}

	@Bean
	public MetadataCouplingHandler metadataCouplingHandler(XPlanManagerDao xPlanManagerDao,
			ManagerConfiguration managerConfiguration) throws DataServiceCouplingException {
		if (managerConfiguration != null && managerConfiguration.getCoupledResourceConfiguration() != null)
			return new MetadataCouplingHandler(xPlanManagerDao, managerConfiguration.getCoupledResourceConfiguration());
		return null;
	}

	@Bean
	public XPlanExporter xPlanExporter() {
		return new XPlanExporter();
	}

	@Bean
	public SortPropertyReader sortPropertyReader(ManagerConfiguration managerConfiguration) {
		SortConfiguration sortConfiguration = createSortConfiguration(managerConfiguration);
		return new SortPropertyReader(sortConfiguration);
	}

	@Bean
	public InternalIdRetriever internalIdRetriever(ManagerConfiguration managerConfiguration) {
		return new InternalIdRetriever(managerConfiguration.getInternalIdRetrieverConfiguration());
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
	public XPlanArchiveCreator archiveCreator(CategoryMapper categoryMapper) {
		return new XPlanArchiveCreator(categoryMapper);
	}

	@Bean
	public CategoryMapper categoryMapper(ManagerConfiguration managerConfiguration) {
		return new CategoryMapper(managerConfiguration);
	}

	@Bean
	public ManagerConfiguration managerConfiguration(PropertiesLoader managerPropertiesLoader,
			@Value("#{environment.XPLAN_DOCUMENT_URL_PUBLIC}") String downloadUrl) throws ConfigurationException {
		Map<String, String> environmentVariables = Collections.singletonMap("XPLAN_DOCUMENT_URL_PUBLIC", downloadUrl);
		return new ManagerConfiguration(managerPropertiesLoader, environmentVariables);
	}

	@Bean
	public WorkspaceReloader workspaceReloader(ManagerConfiguration managerConfiguration) {
		return new WorkspaceReloader(managerConfiguration.getWorkspaceReloaderConfiguration());
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
	public InspirePluTransformator inspirePluTransformator(ManagerConfiguration managerConfiguration) {
		String pathToHaleCli = managerConfiguration.getPathToHaleCli();
		Path pathToHaleProjectDirectory = managerConfiguration.getPathToHaleProjectDirectory();
		if (pathToHaleCli != null && pathToHaleProjectDirectory != null)
			return new HaleCliInspirePluTransformator(pathToHaleCli, pathToHaleProjectDirectory);
		return null;
	}

	@Bean
	public InspirePluPublisher InspirePluPublisher(XPlanManagerDao xPlanManagerDao,
			Optional<InspirePluTransformator> inspirePluTransformator) {
		if (inspirePluTransformator.isPresent())
			return new InspirePluPublisher(xPlanManagerDao, inspirePluTransformator.get());
		return null;
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
	public Path rulesPath(ValidatorConfiguration validatorConfiguration) throws URISyntaxException, IOException {
		Path validationRulesDirectory = validatorConfiguration.getValidationRulesDirectory();
		if (validationRulesDirectory != null)
			return validationRulesDirectory;

		String aResourceInRulesJar = RULES_DIRECTORY + "/xplangml60/2.1.5.xq";
		URL uri = getClass().getResource(aResourceInRulesJar);
		if ("jar".equals(uri.getProtocol())) {
			String jarPath = uri.getFile().replaceFirst("file:(.*)!.*", "$1");
			if (jarPath != null) {
				FileSystem zipfs = FileSystems.newFileSystem(Path.of(jarPath), getClass().getClassLoader());
				return zipfs.getPath(RULES_DIRECTORY);
			}
		}

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
