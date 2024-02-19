/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
class PlanStatusTest {

	@Test
	void testFindByMessage() {
		PlanStatus planStatus = PlanStatus.FESTGESTELLT;
		PlanStatus foundPlanStatus = PlanStatus.findByMessage(planStatus.getMessage());

		assertThat(foundPlanStatus).isEqualTo(planStatus);
	}

	@Test
	void testFindByMessageUnknownMessage() {
		assertThrows(IllegalArgumentException.class, () -> PlanStatus.findByMessage("UNKNOWN_MESSAGE"));
	}

	@Test
	void testFindByMessageNullMessage() {
		assertThrows(IllegalArgumentException.class, () -> PlanStatus.findByMessage(null));
	}

}