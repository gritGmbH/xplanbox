/*-
 * #%L
 * xplan-manager-api - Software zur Verwaltung von XPlanGML Daten
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
