/*-
 * #%L
 * xplan-validator-web - Webanwendung XPlanValidatorWeb
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
package de.latlon.xplan.validator.web.server.service;

import de.latlon.xplanbox.core.gwt.commons.server.service.ReportController;
import de.latlon.xplanbox.core.gwt.commons.server.service.ReportProvider;
import de.latlon.xplan.validator.web.spring.config.TestConfig;
import de.latlon.xplan.validator.web.spring.config.XPlanValidatorWebContextConfig;
import de.latlon.xplan.validator.web.spring.config.XPlanValidatorWebSpringConfig;
import de.latlon.xplan.validator.wms.config.ValidatorWmsContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jakarta.servlet.http.HttpServletResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { XPlanValidatorWebContextConfig.class, XPlanValidatorWebSpringConfig.class,
		ValidatorWmsContext.class, TestConfig.class })
@ActiveProfiles(profiles = "test")
@WebAppConfiguration
public class ReportControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private ReportController reportController;

	@Autowired
	private ReportProvider mockReportProvider;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void verifyThatControllerResponseIsOk_WhenValidRequestIsSend() throws Exception {
		doNothing().when(mockReportProvider)
			.writeHtmlReport(isA(HttpServletResponse.class), isA(String.class), isA(String.class));
		assertThat(this.reportController, is(notNullValue()));
		mockMvc.perform(get("/report/html/42?validationName=foo")).andExpect(status().isOk());
		verify(mockReportProvider, times(1)).writeHtmlReport(any(HttpServletResponse.class), eq("42"), eq(("foo")));
	}

}
