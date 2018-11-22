package de.latlon.xplan.validator.cli;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Tests the application context - sees if it loads
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */

public class XPlanValidatorCliSpringConfigTest {

    @Before
    public void createMissingDirectory()
                            throws URISyntaxException {
        URL path = XPlanValidatorCliSpringConfigTest.class.getProtectionDomain().getCodeSource().getLocation();
        File parentFile = new File( path.toURI() ).getParentFile();
        createSubDirectories( parentFile );
    }

    @Test
    public void testLoadApplicationContextAndInitializeBeans() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register( XPlanValidatorCliSpringConfig.class );
        context.refresh();
        context.close();
    }

    private void createSubDirectories( File parent ) {
        File etc = createEtcDirectory( parent );
        createRulesDirectory( etc );
    }

    private File createEtcDirectory( File parent ) {
        File newEtc = new File( parent, "etc" );
        if ( !newEtc.exists() )
            newEtc.mkdir();
        return newEtc;
    }

    private void createRulesDirectory( File etc ) {
        File rules = new File( etc, "rules" );
        if ( !rules.exists() ) {
            rules.mkdir();
        }
    }

}