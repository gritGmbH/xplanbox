package de.latlon.xplanbox.api.validator.config;

import de.latlon.xplanbox.api.validator.v1.DefaultApi;
import de.latlon.xplanbox.api.validator.XPlanApiValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.glassfish.jersey.server.ResourceConfig;

@Configuration
public class TestContext {

    @Bean
    ResourceConfig resourceConfig() {
        ResourceConfig jerseyConfig = new ResourceConfig();
        jerseyConfig.register( XPlanApiValidator.class );
        jerseyConfig.register( DefaultApi.class );
        return jerseyConfig;
    }
}
