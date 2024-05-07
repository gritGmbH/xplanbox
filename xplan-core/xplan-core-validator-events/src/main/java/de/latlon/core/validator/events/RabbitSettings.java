package de.latlon.core.validator.events;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author <a href="mailto:guillemot@lat-lon.de">Marc Guillemot</a>
 * @since 7.2
 */
class RabbitSettings {

	private static final String topicExchangeName = "-internal-exchange";

	private static final String queueName = "-internal-queue";

	@Value("${xplanbox.rabbitmq.internal.prefix}")
	private String internalPrefix;

	public String getInternalQueueName() {
		return internalPrefix + queueName;
	}

	public String getInternalExchangeName() {
		return internalPrefix + topicExchangeName;
	}

}
