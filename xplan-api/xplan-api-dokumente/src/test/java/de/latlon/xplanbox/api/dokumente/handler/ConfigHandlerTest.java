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
package de.latlon.xplanbox.api.dokumente.handler;

import de.latlon.xplanbox.api.dokumente.config.ApplicationContext;
import de.latlon.xplanbox.api.dokumente.config.HsqlJpaContext;
import de.latlon.xplanbox.api.dokumente.v1.model.SystemConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class, HsqlJpaContext.class })
@Ignore
public class ConfigHandlerTest {

	@Autowired
	private ConfigHandler configHandler;

	@Test
	public void verifyThat_SystemConfig_IsNotNull() {
		SystemConfig systemConfig = configHandler.describeSystem();
		assertTrue(systemConfig != null);
	}

}
