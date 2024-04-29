package de.latlon.xplanbox.api.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import de.latlon.core.validator.events.RabbitReceiverConfig;

@Configuration
@Import({ RabbitReceiverConfig.class })
public class RabbitConfiguration {

}
