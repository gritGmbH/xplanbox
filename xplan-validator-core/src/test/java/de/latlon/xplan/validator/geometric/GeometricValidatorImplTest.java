package de.latlon.xplan.validator.geometric;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.types.AppSchema;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanFeatureCollection;
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

    /**
     * Test retrieveGeometricallyValidXPlanFeatures
     */
    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithBP2070XPlan2()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/BP2070.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "4011000", fc.getPlanGkz() );
        assertNull( fc.getPlanNummer() );
        assertEquals( 409, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithBP2135XPlan2()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/BP2135.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "4011000", fc.getPlanGkz() );
        assertEquals( "Bebauungsplan 2135", fc.getPlanName() );
        assertEquals( "2135", fc.getPlanNummer() );
        assertEquals( 386, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithFPlanXPlan2()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/FPlan.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "FPlan 1", fc.getPlanName() );
        assertNull( fc.getPlanNummer() );
        assertEquals( "1234567", fc.getPlanGkz() );
        assertEquals( 3850, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithLA22XPlan2()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/LA22.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "20000000", fc.getPlanGkz() );
        assertEquals( "Langenhorn 22", fc.getPlanName() );
        assertEquals( "LA 22", fc.getPlanNummer() );
        assertEquals( 1978, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithLA67XPlan2()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/LA67.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "2000000", fc.getPlanGkz() );
        assertEquals( "Bebauungsplan LA 67", fc.getPlanName() );
        assertEquals( "LA 67", fc.getPlanNummer() );
        assertEquals( 165, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithBP2070XPlan3()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan3/BP2070.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "4011000", fc.getPlanGkz() );
        assertNull( fc.getPlanNummer() );
        assertEquals( 351, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithBP2135XPlan3()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan3/BP2135.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "4011000", fc.getPlanGkz() );
        assertEquals( "Bebauungsplan 2135", fc.getPlanName() );
        assertEquals( "2135", fc.getPlanNummer() );
        assertEquals( 241, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithFPlanXPlan3()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan3/FPlan.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "FPlan Bad Liebenwerda", fc.getPlanName() );
        assertNull( fc.getPlanNummer() );
        assertEquals( "12062024", fc.getPlanGkz() );
        assertEquals( 3828, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithBP2070XPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/BP2070.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "4011000", fc.getPlanGkz() );
        assertEquals( 314, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithBP2135XPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/BP2135.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "4011000", fc.getPlanGkz() );
        assertEquals( "Bebauungsplan 2135", fc.getPlanName() );
        assertEquals( "2135", fc.getPlanNummer() );
        assertEquals( 241, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithDemoXPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/Demo.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "1234567", fc.getPlanGkz() );
        assertEquals( "BPlan Demo-Gemeinde", fc.getPlanName() );
        assertNull( fc.getPlanNummer() );
        assertEquals( 20, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithEidelstedt_4_V4XPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/Eidelstedt_4_V4.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "02000000", fc.getPlanGkz() );
        assertEquals( "Eidelstedt 4", fc.getPlanName() );
        assertNull( fc.getPlanNummer() );
        assertEquals( 56, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithFPlanXPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/FPlan.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "12062024", fc.getPlanGkz() );
        assertEquals( "FPlan Bad Liebenwerda", fc.getPlanName() );
        assertNull( fc.getPlanNummer() );
        assertEquals( 3602, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithLA22XPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "02000000", fc.getPlanGkz() );
        assertEquals( "Bebauungsplan LA 22", fc.getPlanName() );
        assertEquals( "LA 22", fc.getPlanNummer() );
        assertEquals( 1349, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithLA67XPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/LA67.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "1234567", fc.getPlanGkz() );
        assertEquals( "Bebauungsplan LA 22", fc.getPlanName() );
        assertEquals( "LA 22", fc.getPlanNummer() );
        assertEquals( 146, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithV4_1_ID_103_41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/V4_1_ID_103.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "12062425", fc.getPlanGkz() );
        assertEquals( "\"Heideweg\"", fc.getPlanName() );
        assertEquals( "Nr.1", fc.getPlanNummer() );
        assertEquals( 500, fc.getFeatures().size() );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithV4_1_ID_66_40()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan40/V4_1_ID_66.zip" );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        assertEquals( "12062425", fc.getPlanGkz() );
        assertEquals( "\"Weinbergstrasse\"", fc.getPlanName() );
        assertEquals( "Nr. 2", fc.getPlanNummer() );
        assertEquals( 545, fc.getFeatures().size() );
    }

    /**
     * Test validateGeometry
     */
    @Test
    public void testValidateGeometryWithBP2070XPlan2()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/BP2070.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 12 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithBP2135XPlan2()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/BP2135.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 10 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithFPlanXPlan2()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/FPlan.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( false ) );
        assertThat( numberOfErrors, is( 109 ) );
        assertThat( numberOfWarnings, is( 33 ) );
        assertThat( numberOfBadGeometries, is( 106 ) );
    }

    @Test
    public void testValidateGeometryWithLA22XPlan2()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/LA22.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( false ) );
        assertThat( numberOfErrors, is( 2 ) );
        assertThat( numberOfWarnings, is( 213 ) );
        assertThat( numberOfBadGeometries, is( 2 ) );
    }

    @Test
    public void testValidateGeometryWithLA67XPlan2()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan2/LA67.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 5 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithBP2070XPlan3()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan3/BP2070.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 12 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithBP2135XPlan3()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan3/BP2135.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 10 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithFPlanXPlan3()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan3/FPlan.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( false ) );
        assertThat( numberOfErrors, is( 109 ) );
        assertThat( numberOfWarnings, is( 33 ) );
        assertThat( numberOfBadGeometries, is( 106 ) );
    }

    @Test
    public void testValidateGeometryWithBP2070XPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/BP2070.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 12 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithBP2135XPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/BP2135.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 10 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithDemoXPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/Demo.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 2 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithEidelstedt_4_V4XPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/Eidelstedt_4_V4.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 8 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithFPlanXPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/FPlan.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( false ) );
        assertThat( numberOfErrors, is( 109 ) );
        assertThat( numberOfWarnings, is( 31 ) );
        assertThat( numberOfBadGeometries, is( 106 ) );
    }

    @Test
    public void testValidateGeometryWithLA22XPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( false ) );
        assertThat( numberOfErrors, is( 4 ) );
        assertThat( numberOfWarnings, is( 214 ) );
        assertThat( numberOfBadGeometries, is( 4 ) );
    }

    @Test
    public void testValidateGeometryWithLA67XPlan41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/LA67.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 5 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithV4_1_ID_103_41()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/V4_1_ID_103.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 0 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithV4_1_ID_66_40()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan40/V4_1_ID_66.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 0 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

    @Test
    public void testValidateGeometryWithComplexCurve()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/PlanWithComplexCurve.zip" );
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( true ) );
        assertThat( numberOfErrors, is( 0 ) );
        assertThat( numberOfWarnings, is( 8 ) );
        assertThat( numberOfBadGeometries, is( 0 ) );
    }

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
        ValidatorResult report = validateGeometryAndReturnReport( archive, new ArrayList<ValidationOption>() );
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

    private XPlanFeatureCollection readFeaturesAndAssertGeometryValidity( XPlanArchive archive )
                    throws XMLStreamException, UnknownCRSException, ValidatorException {
        XPlanVersion version = archive.getVersion();
        XPlanAde ade = archive.getAde();
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( version, ade );
        return new GeometricValidatorImpl().retrieveGeometricallyValidXPlanFeatures( archive, archive.getCrs(), schema,
                                                                                     true, null );
    }

    private ValidatorResult validateGeometryAndReturnReport( XPlanArchive archive, List<ValidationOption> voOptions )
                    throws ValidatorException {
        XPlanVersion version = archive.getVersion();
        XPlanAde ade = archive.getAde();
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( version, ade );
        return ( new GeometricValidatorImpl() ).validateGeometry( archive, archive.getCrs(), schema, true, voOptions );
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
