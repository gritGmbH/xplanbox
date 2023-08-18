/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumentenAPI
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
package de.latlon.xplanbox.api.dokumente.config;

import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplanbox.api.dokumente.handler.DocumentHandler;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeader;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeaderWithStream;
import de.latlon.xplanbox.api.dokumente.v1.DefaultApi;
import de.latlon.xplanbox.api.dokumente.v1.DokumentApi;
import de.latlon.xplanbox.api.dokumente.v1.InfoApi;
import de.latlon.xplanbox.api.dokumente.v1.StatusApi;
import de.latlon.xplanbox.api.dokumente.v1.model.Document;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.ws.rs.core.StreamingOutput;
import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @since 7.0
 */
@Configuration
@Import(HsqlJpaContext.class)
public class TestContext {

	@Bean
	ResourceConfig resourceConfig() {
		ResourceConfig jerseyConfig = new ResourceConfig();
		jerseyConfig.register(DokumentApi.class);
		jerseyConfig.register(InfoApi.class);
		jerseyConfig.register(DefaultApi.class);
		jerseyConfig.register(StatusApi.class);
		return jerseyConfig;
	}

	@Bean
	@Primary
	public DocumentHandler documentHandler() throws Exception {
		DocumentHandler documentHandler = mock(DocumentHandler.class);
		when(documentHandler.listDocuments("1"))
			.thenReturn(Collections.singletonList(new Document().fileName("test.png")));
		when(documentHandler.headDocument("1", "test.png")).thenReturn(new DocumentHeader(5, "image/png"));
		StreamingOutput stream = outputStream -> IOUtils.write("test", outputStream);
		when(documentHandler.getDocument("1", "test.png"))
			.thenReturn(new DocumentHeaderWithStream(5, "image/png", stream));
		return documentHandler;
	}

	@Bean
	@Primary
	public ManagerWorkspaceWrapper managerWorkspaceWrapper() {
		return mock(ManagerWorkspaceWrapper.class);
	}

}
