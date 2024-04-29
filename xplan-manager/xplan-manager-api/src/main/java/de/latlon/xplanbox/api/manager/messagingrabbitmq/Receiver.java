package de.latlon.xplanbox.api.manager.messagingrabbitmq;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import de.latlon.core.validator.events.ValidationMessageReceiver;
import de.latlon.core.validator.events.ValidationRequestedEvent;

@Component
public class Receiver implements Consumer<ValidationRequestedEvent> {

	private static final Logger LOG = getLogger(Receiver.class);

	Receiver(ValidationMessageReceiver msgReceiver) {
		msgReceiver.setListener(this);
	}

	@Override
	public void accept(ValidationRequestedEvent e) {
		LOG.info("Received event: " + e);
	}

}