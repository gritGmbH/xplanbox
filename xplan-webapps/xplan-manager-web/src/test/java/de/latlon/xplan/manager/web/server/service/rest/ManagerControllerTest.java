package de.latlon.xplan.manager.web.server.service.rest;

import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.spring.config.ManagerWebSpringConfig;
import de.latlon.xplan.manager.web.spring.config.TestConfig;
import de.latlon.xplan.manager.web.spring.config.XPlanManagerWebContextConfig;
import org.junit.Before;
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

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, XPlanManagerWebContextConfig.class, ManagerWebSpringConfig.class})
@ActiveProfiles(profiles = {"test"})
@WebAppConfiguration
public class ManagerControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private XPlanManager mockManager;

    @Autowired
    private ManagerController managerController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void verifyThatManagerReturnList_WhenValidRequestIsSend() throws Exception {
        Mockito.when(mockManager.list(false)).thenReturn(new ArrayList<XPlan>());
        assertThat(this.managerController, is(notNullValue()));
        mockMvc.perform(get("/manager/plans")).andExpect(status().isOk());
        verify(mockManager, times(1)).list(any(Boolean.class));
    }
}
