package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import de.latlon.xplanbox.api.manager.v1.model.Status;
import de.latlon.xplanbox.api.manager.v1.model.StatusMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.StreamingOutput;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

@RunWith( SpringRunner.class )
@ContextConfiguration( classes = { ApplicationContext.class, TestContext.class } )
public class PlanHandlerTest {

    @Autowired
    private PlanHandler planHandler;

    @Test
    public void verifyThat_importPlan() throws Exception {
        final File file = new File( PlanHandlerTest.class.getResource( "/bplan_valid_41.zip" ).toURI() );
        final ValidationSettings validationSettings = Mockito.mock( ValidationSettings.class );
        Status status = planHandler.importPlan(file, "noName", validationSettings, "noInternalId", FESTGESTELLT.toString());
        assertThat(status.getValidationReport().getValid(), is(true));
        assertThat(status.getPlanId(), is(123));
    }

    @Test
    public void verifyThat_deletePlan_IsNotThrowingException()
                            throws Exception {
        StatusMessage statusMessage = planHandler.deletePlan( "123" );
        assertThat( statusMessage, is( notNullValue() ) );
        assertThat( statusMessage.getMessage(), containsString("123") );
    }

    @Test
    public void verifyThat_exportPlan() throws Exception {
        StreamingOutput planAsStream = planHandler.exportPlan("123");
        assertThat(planAsStream, notNullValue());
    }

    @Test
    public void verifyThat_findPlanById() throws Exception {
        XPlan plan = planHandler.findPlanById("123");
        assertThat(plan.getId(), is("123"));
    }

    @Test
    public void verifyThat_findPlanByName() throws Exception {
        XPlan plan = planHandler.findPlanByName("bplan_41");
        assertThat(plan.getId(), is("123"));
    }

    @Test
    public void verifyThat_findPlans() throws Exception {
        List<XPlan> planList = planHandler.findPlans("bplan_41");
        assertThat(planList, hasItem( new XPlan("bplan_41","123","B_PLAN", "XPLAN_41")));
    }

    @Test
    public void verifyThat_findPlans_ReturnsEmptyList() throws Exception {
        List<XPlan> planList = planHandler.findPlans("xplan");
        assertThat(planList.size(), is(0));
    }

    @Test
    public void verifyThat_findPlansWithNullName() throws Exception {
        List<XPlan> planList = planHandler.findPlans(null );
        assertThat(planList.isEmpty(), is(false));
    }
}