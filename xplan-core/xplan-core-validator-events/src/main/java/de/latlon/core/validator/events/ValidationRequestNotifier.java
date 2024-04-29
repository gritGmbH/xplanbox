package de.latlon.core.validator.events;

/**
 * @author <a href="mailto:guillemot@lat-lon.de">Marc Guillemot</a>
 * @since 7.2
 */
public interface ValidationRequestNotifier {

	void sendEvent(ValidationRequestedEvent event);

}
