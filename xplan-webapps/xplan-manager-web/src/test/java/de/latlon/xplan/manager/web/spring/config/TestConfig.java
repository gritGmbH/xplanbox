package de.latlon.xplan.manager.web.spring.config;

import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.validator.web.server.service.ReportProvider;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean(name = {"manager","xplanManager"})
    @Primary
    public XPlanManager mockManager() {
        return Mockito.mock(XPlanManager.class);
    }

    @Bean(name = {"reportProvider"})
    @Primary
    public ReportProvider mockReportProvider() {
        return Mockito.mock(ReportProvider.class);
    }

    @Bean(name = {"authorizationManager"})
    @Primary
    public AuthorizationManager mockAuthorizationManager() {
        return Mockito.mock(AuthorizationManager.class);
    }

    @Bean(name = {"internalIdRetriever"})
    @Primary
    public InternalIdRetriever mockInternalIdRetriever() {
        return Mockito.mock(InternalIdRetriever.class);
    }

    /*
    public XPlanManager manager(CategoryMapper categoryMapper, XPlanArchiveCreator archiveCreator,
                                     ManagerConfiguration managerConfiguration, WorkspaceReloader workspaceReloader,
                                     InspirePluTransformator inspirePluTransformator,
                                     XPlanGmlTransformer xPlanGmlTransformer )
            throws Exception {
        Path workspacesBaseDir = Paths.get(TestConfig.class.getResource("/manager.properties").toURI())
                .getFileName().toAbsolutePath().getParent().resolve("xplan-workspaces");
        File workspaceDir = workspacesBaseDir.resolve("xplan-manager-workspace/src/main/workspace").toFile();
        File wmsWorkspaceDir = workspacesBaseDir.resolve("xplansyn-wms-workspace/src/main/workspace").toFile();
        return new XPlanManager( categoryMapper, archiveCreator, managerConfiguration, workspaceDir, wmsWorkspaceDir,
                workspaceReloader, inspirePluTransformator, xPlanGmlTransformer );
    }
    */
}
