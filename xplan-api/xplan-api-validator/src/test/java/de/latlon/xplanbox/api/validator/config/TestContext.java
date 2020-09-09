package de.latlon.xplanbox.api.validator.config;

import de.latlon.xplanbox.api.validator.v1.DefaultApi;
import de.latlon.xplanbox.api.validator.XPlanApiValidator;
import de.latlon.xplanbox.api.validator.v1.InfoApi;
import de.latlon.xplanbox.api.validator.v1.ValidateApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Indented to register the JAX-RS resources within Spring Application Context.
 * TODO Resources not configured automatically. Using JerseyTest instead.
 */
@Configuration
public class TestContext {

    @Bean
    ResourceConfig resourceConfig() {
        ResourceConfig jerseyConfig = new ResourceConfig();
        jerseyConfig.register( ValidateApi.class );
        jerseyConfig.register( InfoApi.class );
        jerseyConfig.register( DefaultApi.class );
        return jerseyConfig;
    }
}
