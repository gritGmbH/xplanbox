package de.latlon.core.validator.events;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author <a href="mailto:guillemot@lat-lon.de">Marc Guillemot</a>
 * @since 7.2
 */
@Configuration
@Profile("rabbit")
public class RabbitConfig {

	@Bean
	RabbitSettings rabbitSettings() {
		return new RabbitSettings();
	}

	@Bean
	Queue queue(RabbitSettings rabbitSettings) {
		return new Queue(rabbitSettings.getInternalQueueName(), true);
	}

	@Bean
	TopicExchange exchange(RabbitSettings rabbitSettings) {
		return new TopicExchange(rabbitSettings.getInternalExchangeName());
	}

	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}

	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() {
		Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
		return jsonConverter;
	}

}