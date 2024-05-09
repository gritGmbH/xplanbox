/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import de.latlon.xplan.manager.wmsconfig.raster.config.ConfigWriterUtils;
import org.junit.Test;

import de.latlon.xplan.manager.web.shared.PlanStatus;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ConfigWriterUtilsTest {

	@Test
	public void testDetectType() throws Exception {
		String type = ConfigWriterUtils.detectType(BP_Plan, null);

		assertThat(type, is("bplan"));
	}

	@Test
	public void testDetectTypeWithPlanStatusFestgestellt() throws Exception {
		String type = ConfigWriterUtils.detectType(BP_Plan, FESTGESTELLT);

		assertThat(type, is("bplan"));
	}

	@Test
	public void testDetectTypeWithPlanStatusInAufstellung() throws Exception {
		String type = ConfigWriterUtils.detectType(BP_Plan, PlanStatus.IN_AUFSTELLUNG);

		assertThat(type, is("bplanpre"));
	}

}
