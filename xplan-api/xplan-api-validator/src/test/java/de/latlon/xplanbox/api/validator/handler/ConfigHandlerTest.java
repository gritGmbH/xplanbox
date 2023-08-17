/*-
 * #%L
 * xplan-api-validator - Modul zur Gruppierung der REST-API
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
package de.latlon.xplanbox.api.validator.handler;

import de.latlon.xplanbox.api.validator.config.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class })
public class ConfigHandlerTest {

	@Autowired
	private ConfigHandler configHandler;

	@Test
	public void verifyThat_SystemConfig_IsNotNull() {
		assertNotNull(configHandler);
	}

	@Test
	public void verifyThat_SystemConfig_ContainsValidationRulesMetadata() throws IOException {
		assertFalse(configHandler.describeSystem().getSupportedXPlanGmlVersions().isEmpty());
	}

}
