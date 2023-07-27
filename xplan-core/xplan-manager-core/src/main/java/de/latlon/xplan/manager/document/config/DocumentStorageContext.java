/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.manager.document.config;

import de.latlon.xplan.manager.document.DocumentStorage;
import de.latlon.xplan.manager.document.XPlanDocumentManager;
import de.latlon.xplan.manager.document.s3.config.AmazonS3DocumentStorageContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Optional;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Configuration
@Import({ AmazonS3DocumentStorageContext.class })
public class DocumentStorageContext {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Bean
	public XPlanDocumentManager xPlanDocumentManager(Optional<DocumentStorage> documentStorage) {
		if (documentStorage.isPresent())
			return new XPlanDocumentManager(documentStorage.get(), applicationEventPublisher);
		return null;
	}

}
