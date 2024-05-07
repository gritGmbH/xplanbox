package de.latlon.core.validator.events;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author <a href="mailto:guillemot@lat-lon.de">Marc Guillemot</a>
 * @since 7.2
 */
class RabbitValidationRequestNotifier implements ValidationRequestNotifier {

	private static final Logger LOG = getLogger(RabbitValidationRequestNotifier.class);

	private RabbitTemplate rabbitTemplate;

	public RabbitValidationRequestNotifier(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendEvent(ValidationRequestedEvent event) {
		LOG.info("Sending message: " + event);
		rabbitTemplate.convertAndSend("foo.bar.baz", event);
	}

}
