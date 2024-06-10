/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.server.service.rest;

import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.spring.config.TestConfig;
import de.latlon.xplan.manager.web.spring.config.XPlanManagerWebContextConfig;
import de.latlon.xplanbox.core.gwt.commons.server.service.ReportProvider;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { XPlanManagerWebContextConfig.class, TestConfig.class })
@ActiveProfiles(profiles = { "test" })
@WebAppConfiguration
@Ignore
public class ManagerControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private XPlanManager mockManager;

	@Autowired
	private ManagerController managerController;

	@Autowired
	private ReportProvider mockReportProvider;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void verifyThatManagerReturnList_WhenValidRequestIsSend() throws Exception {
		doNothing().when(mockReportProvider)
			.writeHtmlReport(isA(HttpServletResponse.class), isA(String.class), isA(String.class));
		Mockito.when(mockManager.list()).thenReturn(new ArrayList<XPlan>());
		assertNotNull(this.managerController);
		mockMvc.perform(get("/manager/plans")).andExpect(status().isOk());
		verify(mockManager, times(1)).list();
	}

}
