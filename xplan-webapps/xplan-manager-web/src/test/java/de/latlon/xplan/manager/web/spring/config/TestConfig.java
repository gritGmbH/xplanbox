package de.latlon.xplan.manager.web.spring.config;

import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;
import de.latlon.xplan.validator.web.server.service.ReportProvider;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean(name = { "manager", "xplanManager" })
    @Primary
    public XPlanManager mockManager() {
        return Mockito.mock( XPlanManager.class );
    }

    @Bean(name = { "reportProvider" })
    @Primary
    public ReportProvider mockReportProvider() {
        return Mockito.mock( ReportProvider.class );
    }

    @Bean(name = { "authorizationManager" })
    @Primary
    public AuthorizationManager mockAuthorizationManager() {
        return Mockito.mock( AuthorizationManager.class );
    }

    @Bean(name = { "internalIdRetriever" })
    @Primary
    public InternalIdRetriever mockInternalIdRetriever() {
        return Mockito.mock( InternalIdRetriever.class );
    }

}
