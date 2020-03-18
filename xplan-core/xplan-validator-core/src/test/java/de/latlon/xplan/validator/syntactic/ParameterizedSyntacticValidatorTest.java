package de.latlon.xplan.validator.syntactic;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Tests for <link>SyntacticValidatorImpl</link>
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
@RunWith(Parameterized.class)
public class ParameterizedSyntacticValidatorTest {

    private String testResource;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList( new Object[][] { { "xplan3/BP2070.zip" },
                                               { "xplan3/BP2135.zip" }, { "xplan3/FPlan.zip" },
                                               { "xplan41/BP2070.zip" }, { "xplan41/BP2135.zip" },
                                               { "xplan41/Demo.zip" }, { "xplan41/Eidelstedt_4_V4.zip" },
                                               { "xplan41/FPlan.zip" }, { "xplan41/LA22.zip" }, { "xplan41/LA67.zip" },
                                               { "xplan41/V4_1_ID_103.zip" }, { "xplan40/V4_1_ID_66.zip" },
                                               { "xplan50/BP2070.zip" }, { "xplan50/BP2135.zip" },
                                               { "xplan50/FPlan.zip" }, { "xplan50/LA22.zip" }, { "xplan50/LA67.zip" },
                                               { "xplan51/BP2070.zip" }, { "xplan51/BP2135.zip" },
                                               { "xplan51/FPlan.zip" }, { "xplan51/LA22.zip" },
                                               { "xplan51/LA67.zip" } } );
    }

    public ParameterizedSyntacticValidatorTest( String testResource ) {
        this.testResource = testResource;
    }

    @Test
    public void testValidateSyntax()
                    throws IOException {
        XPlanArchive archive = getTestArchive( testResource );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        validator.validateSyntax( archive );
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    private XPlanArchive getTestArchive( String name )
                    throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchiveFromZip( name, ResourceAccessor.readResourceStream( name ) );
    }

}
