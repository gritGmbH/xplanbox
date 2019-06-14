package de.latlon.xplan.validator.web.spring.config;

import de.latlon.xplan.validator.web.server.service.ReportProvider;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean(name = {"reportProvider"})
    @Primary
    public ReportProvider mockReportProvider() {
        return Mockito.mock(ReportProvider.class);
    }

}
