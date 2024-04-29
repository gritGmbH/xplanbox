package de.latlon.xplanbox.api.validator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import de.latlon.core.validator.events.RabbitEmitterConfig;

@Configuration
@Import({ RabbitEmitterConfig.class })
public class RabbitConfiguration {

}
