/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
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

@Profile("test")
@Configuration
public class TestConfig {

    @Bean(name = { "manager", "xplanManager" })
    @Primary
    public XPlanManager mockManager() {
        return Mockito.mock( XPlanManager.class );
    }

    @Bean(name = { "reportProvider" })
    @Primary
    public ReportProvider mockReportProvider() {
        return Mockito.mock( ReportProvider.class );
    }

    @Bean(name = { "authorizationManager" })
    @Primary
    public AuthorizationManager mockAuthorizationManager() {
        return Mockito.mock( AuthorizationManager.class );
    }

    @Bean(name = { "internalIdRetriever" })
    @Primary
    public InternalIdRetriever mockInternalIdRetriever() {
        return Mockito.mock( InternalIdRetriever.class );
    }

}
