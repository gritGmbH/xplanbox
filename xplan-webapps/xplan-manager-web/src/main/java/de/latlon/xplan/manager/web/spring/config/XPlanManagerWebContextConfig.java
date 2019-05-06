package de.latlon.xplan.manager.web.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "de.latlon.xplan.validator.web.server.service" ,
                                "de.latlon.xplan.manager.web.server.service.rest"})
public class XPlanManagerWebContextConfig {
}
