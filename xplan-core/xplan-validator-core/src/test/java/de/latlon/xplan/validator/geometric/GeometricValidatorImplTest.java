package de.latlon.xplan.validator.geometric;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.deegree.feature.types.AppSchema;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;

/**
 * <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 */
public class GeometricValidatorImplTest {

    @Test
    public void testValidateGeometryWithValidVoOptions()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/FPlan.zip" );
        List<ValidationOption> validVoOptions = createValidVoOptions();
        ValidatorResult report = validateGeometryAndReturnReport( archive, validVoOptions );
        assertNotEquals( null, report );
    }

    @Test
    public void testValidateGeometryWithInvalidVoOptions()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/FPlan.zip" );
        List<ValidationOption> invalidVoOptions = createInvalidVoOptions();
        ValidatorResult report = validateGeometryAndReturnReport( archive, invalidVoOptions );
        assertNotEquals( null, report );
    }

    @Test
    public void testValidateGeometryWithNullVoOptions()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/FPlan.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, null );
        assertNotEquals( null, report );
    }

    @Test
    public void testValidateGeometryWithXPlanNSMShouldReturnAnInvalidResultWithFiveErrors()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/nsm/nsm_niedersachsen_lrop_small.zip" );
        List<ValidationOption> validVoOptions = createValidVoOptions();
        ValidatorResult report = validateGeometryAndReturnReport( archive, validVoOptions );
        int numberOfErrors = ( (GeometricValidatorResult) report ).getErrors().size();

        assertThat( report.isValid(), is( false ) );
        assertThat( numberOfErrors, is( 5 ) );
    }

    @Test
    public void testValidateGeometryWithErroneousXlinkShouldReturnAnInvalidResultWithSixErrors()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/nsm/nsm_niedersachsen_lrop_small_erroneous_xlink.zip" );
        List<ValidationOption> validVoOptions = createValidVoOptions();
        ValidatorResult report = validateGeometryAndReturnReport( archive, validVoOptions );
        int numberOfErrors = ( (GeometricValidatorResult) report ).getErrors().size();

        assertThat( report.isValid(), is( false ) );
        assertThat( numberOfErrors, is( 6 ) );
    }

    @Test
    public void testValidateGeometryWithBrokenGeometry()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/Eidelstedt_4_V4-broken-geometry.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( false ) );
        assertThat( numberOfErrors, is( 1 ) );
        assertThat( numberOfWarnings, is( 8 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    private XPlanArchive getTestArchive( String name )
                            throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }


    private ValidatorResult validateGeometryAndReturnReport( XPlanArchive archive, List<ValidationOption> voOptions )
                            throws ValidatorException {
        XPlanVersion version = archive.getVersion();
        XPlanAde ade = archive.getAde();
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( version, ade );
        return new GeometricValidatorImpl().validateGeometry( archive, archive.getCrs(), schema, true, voOptions );
    }

    private List<ValidationOption> createValidVoOptions() {
        List<ValidationOption> voOptions = new ArrayList<ValidationOption>();
        voOptions.add( new ValidationOption( "ignore-orientation" ) );
        voOptions.add( new ValidationOption( "ignore-self-intersection " ) );
        voOptions.add( new ValidationOption( "min-node-distance", "1" ) );
        return voOptions;
    }

    private List<ValidationOption> createInvalidVoOptions() {
        List<ValidationOption> voOptions = new ArrayList<ValidationOption>();
        voOptions.add( new ValidationOption( "invalid" ) );
        voOptions.add( new ValidationOption( "min-node-distance", "invalid" ) );
        return voOptions;
    }

}
