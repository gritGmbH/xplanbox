/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplanbox.api.manager.config;

import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.inspire.plu.transformation.hale.HaleCliInspirePluTransformator;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.transformation.HaleXplan41ToXplan51Transformer;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.DeegreeWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.configuration.ValidatorConfigurationParser;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.report.ReportArchiveGenerator;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplanbox.api.commons.handler.SystemConfigHandler;
import org.deegree.commons.config.DeegreeWorkspace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLANSYN_WMS_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLAN_MANAGER_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;
import static java.nio.file.Paths.get;

@Configuration
@ComponentScan(basePackages = { "de.latlon.xplanbox.api.manager" })
public class ApplicationContext {

    private static final String RULES_DIRECTORY = "/rules";

    @Bean
    public SystemConfigHandler systemConfigHandler(
                            XQuerySemanticValidatorConfigurationRetriever configurationRetriever ) {
        return new SystemConfigHandler( configurationRetriever );
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
    public SemanticValidator semanticValidator( ManagerConfiguration managerConfiguration,
                                                XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever )
                            throws URISyntaxException, ValidatorException {
        return new XQuerySemanticValidator( xQuerySemanticValidatorConfigurationRetriever,
                                            managerConfiguration.getSemanticConformityLinkConfiguration() );
    }

    @Bean
    public XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever(
                            Path rulesPath ) {
        return new XQuerySemanticValidatorConfigurationRetriever( rulesPath );
    }

    @Bean
    public XPlanValidator xplanValidator( GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
                                          SemanticValidator semanticValidator,
                                          ReportArchiveGenerator reportArchiveGenerator ) {
        return new XPlanValidator( geometricValidator, syntacticValidator, semanticValidator, reportArchiveGenerator );
    }

    @Bean
    public XPlanDao xPlanDao( CategoryMapper categoryMapper, ManagerWorkspaceWrapper managerWorkspaceWrapper,
                              ManagerConfiguration managerConfiguration ) {
        return new XPlanDao( managerWorkspaceWrapper, categoryMapper, managerConfiguration );
    }

    @Bean
    public ManagerWorkspaceWrapper managerWorkspaceWrapper( ManagerConfiguration managerConfiguration )
                            throws WorkspaceException {
        DeegreeWorkspace managerWorkspace = instantiateWorkspace( DEFAULT_XPLAN_MANAGER_WORKSPACE );
        ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper(
                                managerWorkspace.getNewWorkspace(), managerConfiguration );
        return managerWorkspaceWrapper;
    }

    @Bean
    public XPlanRasterManager xPlanRasterManager( ManagerConfiguration managerConfiguration )
                            throws WorkspaceException {
        DeegreeWorkspaceWrapper wmsWorkspace = new DeegreeWorkspaceWrapper( DEFAULT_XPLANSYN_WMS_WORKSPACE );
        WmsWorkspaceWrapper wmsWorkspaceWrapper = new WmsWorkspaceWrapper( wmsWorkspace.getWorkspaceInstance() );
        return new XPlanRasterManager( wmsWorkspaceWrapper, managerConfiguration );
    }

    @Bean
    public XPlanInsertManager xPlanInsertManager( XPlanDao xPlanDao, XPlanExporter xPlanExporter,
                                                  ManagerWorkspaceWrapper managerWorkspaceWrapper,
                                                  XPlanRasterManager xPlanRasterManager,
                                                  ManagerConfiguration managerConfiguration,
                                                  WorkspaceReloader workspaceReloader,
                                                  XPlanGmlTransformer xPlanGmlTransformer )
                            throws Exception {
        SortConfiguration sortConfiguration = createSortConfiguration( managerConfiguration );
        SortPropertyReader sortPropertyReader = new SortPropertyReader( sortConfiguration );

        return new XPlanInsertManager( xPlanSynthesizer( managerConfiguration ), xPlanGmlTransformer, xPlanDao,
                                       xPlanExporter, xPlanRasterManager, workspaceReloader, managerConfiguration,
                                       managerWorkspaceWrapper, sortPropertyReader );
    }

    @Bean
    public XPlanExporter xPlanExporter( ManagerConfiguration managerConfiguration ) {
        return new XPlanExporter( managerConfiguration );
    }

    @Bean
    public XPlanDeleteManager xPlanDeleteManager( XPlanDao xPlanDao, WorkspaceReloader workspaceReloader,
                                                  XPlanRasterManager xPlanRasterManager,
                                                  ManagerConfiguration managerConfiguration ) {
        return new XPlanDeleteManager( xPlanDao, xPlanRasterManager, workspaceReloader, managerConfiguration );
    }

    @Bean
    public InternalIdRetriever internalIdRetriever( ManagerConfiguration managerConfiguration )
                            throws Exception {
        return new InternalIdRetriever( managerConfiguration.getInternalIdRetrieverConfiguration() );
    }

    @Bean
    public ReportArchiveGenerator reportArchiveGenerator()
                            throws IOException, ConfigurationException {
        return new ReportArchiveGenerator( validatorConfiguration() );
    }

    @Bean
    public ReportWriter reportWriter() {
        return new ReportWriter();
    }

    @Bean
    public XPlanArchiveCreator archiveCreator( CategoryMapper categoryMapper )
                            throws ConfigurationException {
        return new XPlanArchiveCreator( categoryMapper );
    }

    @Bean
    public CategoryMapper categoryMapper( ManagerConfiguration managerConfiguration )
                            throws ConfigurationException {
        return new CategoryMapper( managerConfiguration );
    }

    @Bean
    public ManagerConfiguration managerConfiguration( PropertiesLoader managerPropertiesLoader )
                            throws ConfigurationException {
        return new ManagerConfiguration( managerPropertiesLoader );
    }

    @Bean
    public ManagerApiConfiguration managerApiConfiguration( PropertiesLoader managerPropertiesLoader )
                            throws ConfigurationException {
        return new ManagerApiConfiguration( managerPropertiesLoader );
    }

    @Bean
    public WorkspaceReloader workspaceReloader() {
        return new WorkspaceReloader();
    }

    @Bean
    public InspirePluTransformator inspirePluTransformator( ManagerConfiguration managerConfiguration ) {
        String pathToHaleCli = managerConfiguration.getPathToHaleCli();
        Path pathToHaleProjectDirectory = managerConfiguration.getPathToHaleProjectDirectory();
        if ( pathToHaleCli != null && pathToHaleProjectDirectory != null )
            return new HaleCliInspirePluTransformator( pathToHaleCli, pathToHaleProjectDirectory );
        return null;
    }

    @Bean
    public XPlanGmlTransformer xPlanGmlTransformer( ManagerConfiguration managerConfiguration ) {
        String pathToHaleCli = managerConfiguration.getPathToHaleCli();
        Path pathToHaleProjectDirectory = managerConfiguration.getPathToHaleProjectDirectory();
        if ( pathToHaleCli != null && pathToHaleProjectDirectory != null ) {
            HaleXplan41ToXplan51Transformer haleXplan41ToXplan51Transformer = new HaleXplan41ToXplan51Transformer(
                                    pathToHaleCli, pathToHaleProjectDirectory );
            return new XPlanGmlTransformer( haleXplan41ToXplan51Transformer );
        }
        return null;
    }

    @Bean
    public ValidatorConfiguration validatorConfiguration()
                            throws IOException, ConfigurationException {
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        return validatorConfigurationParser.parse( new SystemPropertyPropertiesLoader( ValidatorConfiguration.class ) );
    }

    @Bean
    public PropertiesLoader managerPropertiesLoader() {
        return new SystemPropertyPropertiesLoader( ManagerConfiguration.class );
    }

    @Bean
    public Path rulesPath( ValidatorConfiguration validatorConfiguration )
                            throws URISyntaxException {
        Path validationRulesDirectory = validatorConfiguration.getValidationRulesDirectory();
        if ( validationRulesDirectory != null )
            return validationRulesDirectory;
        URI rulesPath = getClass().getResource( RULES_DIRECTORY ).toURI();
        return get( rulesPath );
    }

    private SortConfiguration createSortConfiguration( ManagerConfiguration managerConfiguration ) {
        if ( managerConfiguration != null )
            return managerConfiguration.getSortConfiguration();
        return new SortConfiguration();
    }

    private XPlanSynthesizer xPlanSynthesizer( ManagerConfiguration managerConfiguration ) {
        if ( managerConfiguration != null )
            return new XPlanSynthesizer( managerConfiguration.getSynthesizerConfigurationDirectory() );
        return new XPlanSynthesizer();
    }

}