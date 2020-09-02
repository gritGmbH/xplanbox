package de.latlon.xplanbox.api.validator.v1.handler;

import de.latlon.xplanbox.api.validator.v1.config.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith( SpringRunner.class )
@ContextConfiguration( classes = { ApplicationContext.class } )
public class ConfigHandlerTest {

    @Autowired
    private ConfigHandler configHandler;

    @Test
    public void verifyThatSystemConfigIsNotNull() {
        assertNotNull( configHandler );
    }
}