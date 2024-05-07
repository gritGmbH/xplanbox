package de.latlon.core.validator.events;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * @author <a href="mailto:guillemot@lat-lon.de">Marc Guillemot</a>
 * @since 7.2
 */
@Configuration
@Import({ RabbitConfig.class })
public class RabbitReceiverConfig {

	@Bean
	@Profile("rabbit")
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter, RabbitSettings rabbitSettings) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(rabbitSettings.getInternalQueueName());
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	@Profile("rabbit")
	MessageListenerAdapter listenerAdapter(ValidationMessageReceiver receiver,
			Jackson2JsonMessageConverter msgConverter) {
		MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(receiver, "receiveMessage");
		listenerAdapter.setMessageConverter(msgConverter);
		return listenerAdapter;
	}

	@Bean
	ValidationMessageReceiver validationMessageReceiver() {
		return new ValidationMessageReceiver();
	}

}
