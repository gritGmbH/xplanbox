/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.spring.config;

import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;
import de.latlon.xplan.validator.web.server.service.ReportProvider;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Profile("test")
@Configuration
public class TestConfig {

	@Bean(name = { "manager", "xplanManager" })
	@Primary
	public XPlanManager mockManager() {
		return Mockito.mock(XPlanManager.class);
	}

	@Bean(name = { "reportProvider" })
	@Primary
	public ReportProvider mockReportProvider() {
		return Mockito.mock(ReportProvider.class);
	}

	@Bean(name = { "authorizationManager" })
	@Primary
	public AuthorizationManager mockAuthorizationManager() {
		return Mockito.mock(AuthorizationManager.class);
	}

	@Bean(name = { "internalIdRetriever" })
	@Primary
	public InternalIdRetriever mockInternalIdRetriever() {
		return Mockito.mock(InternalIdRetriever.class);
	}

}
