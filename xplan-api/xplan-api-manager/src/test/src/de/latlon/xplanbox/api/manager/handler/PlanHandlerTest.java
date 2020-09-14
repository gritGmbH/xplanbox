package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
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
        planHandler.importPlan(file, "noName", validationSettings, "noInternalId", FESTGESTELLT.toString());
        //TODO
    }

    @Test
    public void verifyThat_deletePlan() throws Exception {
        planHandler.deletePlan("123");
        //TODO
    }

    @Test
    public void verifyThat_exportPlan() throws Exception {
        planHandler.exportPlan("123");
        //TODO
    }

    @Test
    public void verifyThat_findPlanById() throws Exception {
        planHandler.findPlanById("123");
        //TODO
    }

    @Test
    public void verifyThat_findPlanByName() throws Exception {
        planHandler.findPlanByName("bplan_41");
        //TODO
    }

    @Test
    public void verifyThat_findPlansByName() throws Exception {
        planHandler.findPlansByName("xplan");
        //TODO

    }

    @Test
    public void verifyThat_findPlans() throws Exception {
        planHandler.findPlans("");
        //TODO
    }
}