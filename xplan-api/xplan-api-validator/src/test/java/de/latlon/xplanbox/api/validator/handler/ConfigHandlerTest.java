package de.latlon.xplanbox.api.validator.handler;

import de.latlon.xplanbox.api.validator.config.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith( SpringRunner.class )
@ContextConfiguration( classes = { ApplicationContext.class } )
public class ConfigHandlerTest {

    @Autowired
    private ConfigHandler configHandler;

    @Test
    public void verifyThat_SystemConfig_IsNotNull() {
        assertNotNull( configHandler );
    }

    @Test
    public void verifyThat_SystemConfig_ContainsValidationRulesMetadata() throws IOException {
        assertFalse( configHandler.describeSystem().getSupportedXPlanGmlVersions().isEmpty() );
    }
}