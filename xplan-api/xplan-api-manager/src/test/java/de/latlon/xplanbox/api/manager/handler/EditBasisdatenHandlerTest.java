/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanToEdit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class, TestContext.class })
public class EditBasisdatenHandlerTest {

	@Autowired
	private EditBasisdatenHandler editHandler;

	@Test
	public void verifyThat_findPlanById_FP_51() throws Exception {
		XPlan plan = editHandler.findPlanById("4");
		assertThat(plan.getType(), is("FP_Plan"));
	}

	@Test(expected = InvalidPlanToEdit.class)
	public void verifyThat_findPlanById_LP_51_shouldNotBeEditable() throws Exception {
		editHandler.findPlanById("5");
	}

	@Test(expected = InvalidPlanToEdit.class)
	public void verifyThat_findPlanById_SO_41_shouldNotBeEditable() throws Exception {
		editHandler.findPlanById("6");
	}

}
