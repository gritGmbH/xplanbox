package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import de.latlon.xplanbox.api.manager.v1.model.Status;
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
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
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
    public void verifyThat_deletePlan_IsNotThrowingException() throws Exception {
        planHandler.deletePlan("123");
        //expected: no exception is thrown, nothing to do
    }

    @Test
    public void verifyThat_exportPlan() throws Exception {
        StreamingOutput planAsStream = planHandler.exportPlan("123");
        //ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //planAsStream.write(outputStream);
        //String string = new String(outputStream.toByteArray(), "UTF-8");
        //System.out.println(string);
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
    public void verifyThat_findPlansByName() throws Exception {
        List<XPlan> planList = planHandler.findPlansByName("bplan_41");
        assertThat(planList, hasItem( new XPlan("bplan_41","123","B_PLAN")));
    }

    @Test
    public void verifyThat_findPlansByName_ReturnsEmptyList() throws Exception {
        List<XPlan> planList = planHandler.findPlansByName("xplan");
        assertThat(planList.size(), is(0));
    }

    @Test
    public void verifyThat_findPlans() throws Exception {
        List<XPlan> planList = planHandler.findPlans(null );
        assertThat(planList.isEmpty(), is(false));
    }
}