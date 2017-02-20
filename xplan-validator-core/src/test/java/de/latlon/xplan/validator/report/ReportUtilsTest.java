package de.latlon.xplan.validator.report;

import org.junit.Test;

import static de.latlon.xplan.validator.report.ReportUtils.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ReportUtilsTest {

    @Test
    public void testCreateLabelLabelFromTrue() {
        String validLabel = createValidLabel( true );
        assertThat( validLabel, is( LABEL_VALID ) );
    }

    @Test
    public void testCreateLabelLabelFromFalse() {
        String validLabel = createValidLabel( false );
        assertThat( validLabel, is( LABEL_INVALID ) );
    }

}