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

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.HsqlJpaContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import de.latlon.xplanbox.api.commons.exception.InvalidPlanId;
import de.latlon.xplanbox.api.manager.v1.model.StatusMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.util.List;

import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class, JpaContext.class, HsqlJpaContext.class, TestContext.class })
public class PlanHandlerTest {

	@Autowired
	private PlanHandler planHandler;

	@Autowired
	private XPlanArchiveCreator archiveCreator;

	@Test
	public void verifyThat_importPlanZip() throws Exception {
		final File file = new File(PlanHandlerTest.class.getResource("/bplan_valid_41.zip").toURI());
		final ValidationSettings validationSettings = Mockito.mock(ValidationSettings.class);
		XPlanArchive xPlanArchive = archiveCreator.createXPlanArchiveFromZip(file);
		List<XPlan> xPlan = planHandler.importPlan(xPlanArchive, "noName", validationSettings, "noInternalId",
				FESTGESTELLT.toString());
		assertThat(xPlan.size(), is(1));
		assertThat(xPlan.get(0).getId(), is("123"));
	}

	@Test
	public void verifyThat_deletePlan_IsNotThrowingException() throws Exception {
		StatusMessage statusMessage = planHandler.deletePlan("123");
		assertThat(statusMessage, is(notNullValue()));
		assertThat(statusMessage.getMessage(), containsString("123"));
	}

	@Test
	public void verifyThat_exportPlan() throws Exception {
		StreamingOutput planAsStream = planHandler.exportPlan("123");
		assertThat(planAsStream, notNullValue());
	}

	@Test(expected = InvalidPlanId.class)
	public void verifyThat_exportPlan_WithWrongIdThrowsException() throws Exception {
		StreamingOutput planAsStream = planHandler.exportPlan("42");
		assertThat(planAsStream, notNullValue());
	}

	@Test
	public void verifyThat_findPlanById() throws Exception {
		XPlan plan = planHandler.findPlanById("123");
		assertThat(plan.getId(), is("123"));
	}

	@Test(expected = InvalidPlanId.class)
	public void verifyThat_findPlanById_WithWrongIdFails() throws Exception {
		planHandler.findPlanById("42");
	}

	@Test
	public void verifyThat_findPlanByName() throws Exception {
		List<XPlan> plans = planHandler.findPlansByName("bplan_41");
		assertThat(plans.size(), is(1));
		assertThat(plans.get(0).getId(), is("123"));
	}

	@Test
	public void verifyThat_findPlans() throws Exception {
		List<XPlan> planList = planHandler.findPlans("bplan_41");
		assertThat(planList, hasItem(new XPlan("bplan_41", "123", "BP_Plan", "XPLAN_41")));
	}

	@Test
	public void verifyThat_findPlans_ReturnsEmptyList() throws Exception {
		List<XPlan> planList = planHandler.findPlans("xplan");
		assertThat(planList.size(), is(0));
	}

	@Test
	public void verifyThat_findPlansWithNullName() throws Exception {
		List<XPlan> planList = planHandler.findPlans(null);
		assertThat(planList.isEmpty(), is(false));
	}

}
