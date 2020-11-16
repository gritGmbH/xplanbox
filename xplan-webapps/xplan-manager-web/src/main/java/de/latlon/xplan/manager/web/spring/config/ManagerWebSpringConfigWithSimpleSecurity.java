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

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Imports the basic {@link ManagerWebSpringConfig} and simple security
 * configuration. The security configuration can be configured in security-simple.xml.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 *
 * @version $Revision: $, $Date: $
 */
@Configuration
@Import(BasicSpringConfig.class)
@ImportResource("classpath:/de/latlon/xplan/manager/web/spring/security-simple.xml")
public class ManagerWebSpringConfigWithSimpleSecurity {

}
