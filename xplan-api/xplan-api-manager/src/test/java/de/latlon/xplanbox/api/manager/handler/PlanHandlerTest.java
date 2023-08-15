/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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
package de.latlon.xplanbox.api.manager.handler;

import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.List;

import javax.ws.rs.core.StreamingOutput;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.exception.InvalidPlanId;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.HsqlJpaContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import de.latlon.xplanbox.api.manager.v1.model.StatusMessage;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationContext.class, JpaContext.class, HsqlJpaContext.class, TestContext.class })
class PlanHandlerTest {

	@Autowired
	private PlanHandler planHandler;

	@Autowired
	private XPlanArchiveCreator archiveCreator;

	@Test
	void verifyThat_importPlanZip() throws Exception {
		final File file = new File(PlanHandlerTest.class.getResource("/bplan_valid_41.zip").toURI());
		final ValidationSettings validationSettings = Mockito.mock(ValidationSettings.class);
		XPlanArchive xPlanArchive = archiveCreator.createXPlanArchiveFromZip(file);
		List<XPlan> xPlan = planHandler.importPlan(xPlanArchive, "noName", validationSettings, "noInternalId",
				FESTGESTELLT.toString());
		assertThat(xPlan).hasSize(1);
		assertThat(xPlan.get(0).getId()).isEqualTo("123");
	}

	@Test
	void verifyThat_deletePlan_IsNotThrowingException() throws Exception {
		StatusMessage statusMessage = planHandler.deletePlan("123");
		assertThat(statusMessage).isNotNull();
		assertThat(statusMessage.getMessage()).contains("123");
	}

	@Test
	void verifyThat_exportPlan() throws Exception {
		StreamingOutput planAsStream = planHandler.exportPlan("123");
		assertThat(planAsStream).isNotNull();
	}

	@Test
	void verifyThat_exportPlan_WithWrongIdThrowsException() throws Exception {
		assertThrows(InvalidPlanId.class, () -> {
			StreamingOutput planAsStream = planHandler.exportPlan("42");
			assertThat(planAsStream).isNotNull();
		});
	}

	@Test
	void verifyThat_findPlanById() throws Exception {
		XPlan plan = planHandler.findPlanById("123");
		assertThat(plan.getId()).isEqualTo("123");
	}

	@Test
	void verifyThat_findPlanById_WithWrongIdFails() throws Exception {
		assertThrows(InvalidPlanId.class, () -> {
			planHandler.findPlanById("42");
		});
	}

	@Test
	void verifyThat_findPlanByName() throws Exception {
		List<XPlan> plans = planHandler.findPlansByName("bplan_41");
		assertThat(plans).hasSize(1);
		assertThat(plans.get(0).getId()).isEqualTo("123");
	}

	@Test
	void verifyThat_findPlans() throws Exception {
		List<XPlan> planList = planHandler.findPlans("bplan_41");
		assertThat(planList).contains(new XPlan("bplan_41", "123", "BP_Plan", "XPLAN_41"));
	}

	@Test
	void verifyThat_findPlans_ReturnsEmptyList() throws Exception {
		List<XPlan> planList = planHandler.findPlans("xplan");
		assertThat(planList).hasSize(0);
	}

	@Test
	void verifyThat_findPlansWithNullName() throws Exception {
		List<XPlan> planList = planHandler.findPlans(null);
		assertThat(planList).isNotEmpty();
	}

}
