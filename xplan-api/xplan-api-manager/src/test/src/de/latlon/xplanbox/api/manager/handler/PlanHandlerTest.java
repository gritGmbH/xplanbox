package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith( SpringRunner.class )
@ContextConfiguration( classes = { ApplicationContext.class, TestContext.class } )
public class PlanHandlerTest {

    @Autowired
    private PlanHandler planHandler;

    @Test
    public void importPlan() {
    }

    @Test
    public void deletePlan() {
    }

    @Test
    public void exportPlan() {
    }

    @Test
    public void findPlanById() {
    }

    @Test
    public void findPlanByName() {
    }

    @Test
    public void findPlansByName() {
    }

    @Test
    public void findPlans() {
    }
}