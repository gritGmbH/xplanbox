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

import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplan.manager.document.config.DocumentStorageContext;
import de.latlon.xplan.manager.storage.config.StorageCleanUpContext;
import de.latlon.xplan.manager.wmsconfig.config.RasterStorageContext;
import de.latlon.xplan.manager.wmsconfig.raster.storage.s3.config.AmazonS3RasterStorageContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Imports the basic {@link ManagerWebSpringConfig} and active directory ldap security
 * configuration. The security configuration can be configured in security.properties.
 *
 * @deprecated This class be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@Configuration
@Import({ BasicSpringConfig.class, JpaContext.class, RasterStorageContext.class, AmazonS3RasterStorageContext.class,
		DocumentStorageContext.class, StorageCleanUpContext.class })
@ImportResource("classpath:/de/latlon/xplan/manager/web/spring/security-ad.xml")
@Deprecated
public class ManagerWebSpringConfigWithAdLdapSecurity {

}
