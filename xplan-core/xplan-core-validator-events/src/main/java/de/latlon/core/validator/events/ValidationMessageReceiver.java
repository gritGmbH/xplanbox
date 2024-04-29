package de.latlon.core.validator.events;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:guillemot@lat-lon.de">Marc Guillemot</a>
 * @since 7.2
 */
public class ValidationMessageReceiver {

	private static final Logger LOG = LoggerFactory.getLogger(ValidationMessageReceiver.class);

	private Consumer<ValidationRequestedEvent> listener;

	void receiveMessage(ValidationRequestedEvent msg) {
		if (listener != null) {
			listener.accept(msg);
		}
		else {
			LOG.warn("No listener registered");
		}
	}

	public void setListener(Consumer<ValidationRequestedEvent> listener) {
		this.listener = listener;
	}

}