package de.latlon.core.validator.events;

import de.latlon.xplan.validator.web.shared.ValidationSettings;

/**
 * @author <a href="mailto:guillemot@lat-lon.de">Marc Guillemot</a>
 * @since 7.2
 */
public class ValidationRequestedEvent {

	private ValidationSettings settings;

	public ValidationRequestedEvent() {

	}

	public ValidationRequestedEvent(ValidationSettings settings) {
		this.settings = settings;
	}

	public ValidationSettings getSettings() {
		return settings;
	}

	@Override
	public String toString() {
		return settings.toString();
	}

}
