package de.latlon.xplanbox.api.validator.v1;

import de.latlon.xplanbox.api.validator.config.ApplicationContext;
import de.latlon.xplanbox.api.validator.config.TestContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith( SpringRunner.class )
@ContextConfiguration( classes = { TestContext.class, ApplicationContext.class } )
public class DefaultApiTest {

    @Autowired
    private DefaultApi controller;

    @Test
    public void verifyThatDefaultApiIsNotNull() {
        assertNotNull(controller);
    }
}