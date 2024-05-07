package de.latlon.core.validator.events;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Import({ RabbitConfig.class })
/**
 * @author <a href="mailto:guillemot@lat-lon.de">Marc Guillemot</a>
 * @since 7.2
 */
public class RabbitEmitterConfig {

	@Bean
	@Profile("rabbit")
	public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter msgConverter, ConnectionFactory connectionFactory,
			RabbitSettings rabbitSettings) {
		RabbitTemplate template = new RabbitTemplate();
		template.setMessageConverter(msgConverter);
		template.setConnectionFactory(connectionFactory);
		template.setExchange(rabbitSettings.getInternalExchangeName());

		return template;
	}

	@Bean
	@Profile("rabbit")
	ValidationRequestNotifier validationRequestNotifier(RabbitTemplate rabbitTemplate) {
		return new RabbitValidationRequestNotifier(rabbitTemplate);
	}

	@Bean
	@Profile("!rabbit")
	ValidationRequestNotifier noOpValidationRequestNotifier(RabbitTemplate rabbitTemplate) {
		return (ValidationRequestedEvent event) -> {
		};
	}

}
