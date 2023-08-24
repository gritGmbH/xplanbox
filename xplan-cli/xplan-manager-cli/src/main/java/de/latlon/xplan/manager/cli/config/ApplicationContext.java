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
package de.latlon.xplan.manager.cli.config;

import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
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
import de.latlon.xplan.manager.cli.ServiceMetadataRecordCreator;
import de.latlon.xplan.manager.cli.XPlanManagerCLI;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDbAdapter;
import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.document.XPlanDocumentManager;
import de.latlon.xplan.manager.document.config.DocumentStorageContext;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import de.latlon.xplan.manager.metadata.MetadataCouplingHandler;
import de.latlon.xplan.manager.storage.StorageCleanUpManager;
import de.latlon.xplan.manager.storage.config.StorageCleanUpContext;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import de.latlon.xplan.manager.transaction.AttachmentUrlHandler;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanEditManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.transaction.service.XPlanDeleteService;
import de.latlon.xplan.manager.transaction.service.XPlanEditService;
import de.latlon.xplan.manager.transaction.service.XPlanInsertService;
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
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.configuration.ValidatorConfigurationParser;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.report.ReportArchiveGenerator;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersion;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersionParser;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.profile.SemanticProfiles;
import de.latlon.xplan.validator.semantic.profile.SemanticProfilesCreator;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import org.deegree.commons.config.DeegreeWorkspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLANSYN_WMS_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLAN_MANAGER_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;
import static java.nio.file.Paths.get;

/**
 * Spring Application Context for initialising XPlanManagerAPI components.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Configuration
@ComponentScan(basePackages = { "de.latlon.xplan.manager.cli" })
@Import({ JpaContext.class, RasterStorageContext.class, AmazonS3RasterStorageContext.class,
		DocumentStorageContext.class, StorageCleanUpContext.class })
public class ApplicationContext {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationContext.class);

	private static final String RULES_DIRECTORY = "/rules";

	@Autowired
	@Lazy
	private PlanRepository planRepository;

	@Autowired
	@Lazy
	private PlanwerkWmsMetadataRepository planwerkWmsMetadataRepository;

	@Autowired
	@Lazy
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

	@Autowired
	private ResourceLoader resourceLoader;

	@Bean
	public SemanticValidator semanticValidator(ManagerConfiguration managerConfiguration,
			XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever)
			throws ConfigurationException {
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
	public SemanticProfiles semanticProfiles(ValidatorConfiguration validatorConfiguration,
			PropertiesLoader validatorPropertiesLoader) throws ConfigurationException {
		SemanticProfilesCreator semanticProfilesCreator = new SemanticProfilesCreator(validatorConfiguration,
				validatorPropertiesLoader, resourceLoader);
		return semanticProfilesCreator.createSemanticProfiles();
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
	public ManagerWorkspaceWrapper managerWorkspaceWrapper() throws WorkspaceException {
		DeegreeWorkspace managerWorkspace = instantiateWorkspace(DEFAULT_XPLAN_MANAGER_WORKSPACE);
		return new ManagerWorkspaceWrapper(managerWorkspace);
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
			ManagerConfiguration managerConfiguration, WmsWorkspaceWrapper wmsWorkspaceWrapper,
			XPlanExporter xPlanExporter, XPlanRasterEvaluator xPlanRasterEvaluator,
			XPlanRasterManager xPlanRasterManager, SortPropertyReader sortPropertyReader,
			XPlanInsertManager xPlanInsertManager, XPlanEditManager xPlanEditManager,
			XPlanDeleteManager xPlanDeleteManager) throws Exception {
		return new XPlanManager(xPlanManagerDao, archiveCreator, managerConfiguration, wmsWorkspaceWrapper,
				xPlanExporter, xPlanRasterEvaluator, xPlanRasterManager, sortPropertyReader, null, xPlanInsertManager,
				xPlanEditManager, xPlanDeleteManager);
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
			XPlanExporter xPlanExporter, ManagerConfiguration managerConfiguration, WorkspaceReloader workspaceReloader,
			XPlanRasterManager xPlanRasterManager, SortPropertyReader sortPropertyReader,
			XPlanEditService xPlanEditService, MetadataCouplingHandler metadataCouplingHandler,
			Optional<AttachmentUrlHandler> attachmentUrlHandler) {
		return new XPlanEditManager(xPlanSynthesizer, xPlanManagerDao, xPlanExporter, xPlanRasterManager,
				workspaceReloader, managerConfiguration, sortPropertyReader, xPlanEditService, metadataCouplingHandler,
				attachmentUrlHandler.orElse(null));
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
	public XPlanValidator xplanValidator(GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
			SemanticValidator semanticValidator, SemanticProfiles semanticProfiles,
			ReportArchiveGenerator reportArchiveGenerator) {
		return new XPlanValidator(geometricValidator, syntacticValidator, semanticValidator,
				semanticProfiles.getProfileValidators(), reportArchiveGenerator);
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
	public ValidatorConfiguration validatorConfiguration(PropertiesLoader validatorPropertiesLoader)
			throws IOException, ConfigurationException {
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		return validatorConfigurationParser.parse(validatorPropertiesLoader);
	}

	@Bean
	public PropertiesLoader managerPropertiesLoader(
			@Value("${xplanbox.config:#{environment.XPLANBOX_CONFIG}}") String configFilePath) {
		if (configFilePath != null) {
			LOG.info("Using {}/managerConfiguration.properties", configFilePath);
			return new SystemPropertyPropertiesLoader(configFilePath);
		}
		LOG.info("Using etc/managerConfiguration.properties");
		return new ConfigurationDirectoryPropertiesLoader(etcDirectory());
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

	@Bean
	public ServiceMetadataRecordCreator serviceMetadataRecordCreator(ManagerConfiguration managerConfiguration,
			XPlanManagerDao xPlanManagerDao, MetadataCouplingHandler metadataCouplingHandler) {
		try {
			return new ServiceMetadataRecordCreator(xPlanManagerDao, managerConfiguration, metadataCouplingHandler);
		}
		catch (Exception e) {
			LOG.warn("Could not initialize ServiceMetadataRecordCreator: {}", e.getMessage());
		}
		return null;
	}

	private SortConfiguration createSortConfiguration(ManagerConfiguration managerConfiguration) {
		if (managerConfiguration != null)
			return managerConfiguration.getSortConfiguration();
		return new SortConfiguration();
	}

	private Path etcDirectory() {
		String path = XPlanManagerCLI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		File jarLocation = new File(path);
		return Paths.get(jarLocation.getParentFile().getParent()).resolve("etc");
	}

}
