package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.config.ApplicationContext;
import de.latlon.xplanbox.api.manager.config.TestContext;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.junit.Assert.*;

public class PlansApiTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable( TestProperties.LOG_TRAFFIC );
        final ResourceConfig resourceConfig = new ResourceConfig( PlanApi.class );
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( ApplicationContext.class,
                TestContext.class );
        resourceConfig.property("contextConfig", context );
        return resourceConfig;
    }

    @Test
    public void verifyThat_GetPlansByName_ReturnCorrectStatus() {
        final Response response = target( "/plans" ).queryParam("planName", "bplan_41").request().
                accept( APPLICATION_JSON ).get();
        //TODO
    }
}