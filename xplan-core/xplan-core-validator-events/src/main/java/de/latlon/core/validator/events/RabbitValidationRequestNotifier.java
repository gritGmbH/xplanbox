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
