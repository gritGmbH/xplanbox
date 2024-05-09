/*-
 * #%L
 * xplan-core-validator-events - Modul zur Gruppierung der Kernmodule
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
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
