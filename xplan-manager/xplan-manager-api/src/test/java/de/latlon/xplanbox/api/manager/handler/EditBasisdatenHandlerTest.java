/*-
 * #%L
 * xplan-manager-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.manager.handler;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.HsqlJpaContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanToEdit;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationContext.class, JpaContext.class, HsqlJpaContext.class, TestContext.class })
class EditBasisdatenHandlerTest {

	@Autowired
	private EditBasisdatenHandler editHandler;

	@Test
	void verifyThat_findPlanById_FP_51() throws Exception {
		XPlan plan = editHandler.findPlanById("4");
		assertThat(plan.getType()).isEqualTo("FP_Plan");
	}

	@Test
	void verifyThat_findPlanById_LP_51_shouldNotBeEditable() throws Exception {
		assertThrows(InvalidPlanToEdit.class, () -> {
			editHandler.findPlanById("5");
		});
	}

	@Test
	void verifyThat_findPlanById_SO_41_shouldNotBeEditable() throws Exception {
		assertThrows(InvalidPlanToEdit.class, () -> {
			editHandler.findPlanById("6");
		});
	}

}
