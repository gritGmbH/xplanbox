package de.latlon.xplan.validator.geometric;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.types.AppSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(Parameterized.class)
public class ParameterizedGeometricValidatorImplTest {

    private ResourceUnderTest resourceUnderTest;

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add( new Object[] { new ResourceUnderTest( "xplan2/BP2070.zip", true, 0, 12, 0, null, "4011000", null, 409 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan2/BP2135.zip", true, 0, 10, 0, "Bebauungsplan 2135",
                                                        "4011000", "2135", 386 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan2/FPlan.zip", false, 109, 33, 106, "FPlan 1", "1234567",
                                                        null, 3850 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan2/LA22.zip", false, 2, 213, 2, "Langenhorn 22",
                                                        "20000000", "LA 22", 1978 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan2/LA67.zip", true, 0, 5, 0, "Bebauungsplan LA 67",
                                                        "2000000", "LA 67", 165 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan3/BP2070.zip", true, 0, 12, 0, null, "4011000", null, 351 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan3/BP2135.zip", true, 0, 10, 0, "Bebauungsplan 2135",
                                                        "4011000", "2135", 241 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan3/FPlan.zip", false, 109, 33, 106,
                                                        "FPlan Bad Liebenwerda", "12062024", null, 3828 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan41/BP2070.zip", true, 0, 12, 0, null, "4011000", null,
                                                        314 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan41/BP2135.zip", true, 0, 10, 0, "Bebauungsplan 2135",
                                                        "4011000", "2135", 241 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan41/Demo.zip", true, 0, 2, 0, "BPlan Demo-Gemeinde",
                                                        "1234567", null, 20 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan41/Eidelstedt_4_V4.zip", true, 0, 8, 0, "Eidelstedt 4",
                                                        "02000000", null, 56 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan41/FPlan.zip", false, 109, 31, 106,
                                                        "FPlan Bad Liebenwerda", "12062024", null, 3602 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan41/LA22.zip", false, 4, 214, 4, "Bebauungsplan LA 22",
                                                        "02000000", "LA 22", 1349 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan41/LA67.zip", true, 0, 5, 0, "Bebauungsplan LA 22",
                                                        "1234567", "LA 22", 146 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan41/V4_1_ID_103.zip", true, 0, 0, 0, "\"Heideweg\"",
                                                        "12062425", "Nr.1", 500 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan40/V4_1_ID_66.zip", true, 0, 0, 0, "\"Weinbergstrasse\"",
                                                        "12062425", "Nr. 2", 545 ) } );
        data.add( new Object[] { new ResourceUnderTest( "xplan41/PlanWithComplexCurve.zip", true, 0, 8, 0, "test5", "",
                                                        null, 1 ) } );
        return data;
    }

    public ParameterizedGeometricValidatorImplTest( ResourceUnderTest resourceUnderTest ) {
        this.resourceUnderTest = resourceUnderTest;
    }

    @Test
    public void testValidateGeometry()
                            throws Exception {

        XPlanArchive archive = getTestArchive( resourceUnderTest.testReource );
        ValidatorResult report = validateGeometryAndReturnReport( archive, Collections.emptyList() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( resourceUnderTest.expectedValidationResult ) );
        assertThat( numberOfErrors, is( resourceUnderTest.expectedNumberOfErrors ) );
        assertThat( numberOfWarnings, is( resourceUnderTest.expectedNumberOfWarnings ) );
        assertThat( numberOfBadGeometries, is( resourceUnderTest.expectedNumberOBadGeometries ) );
    }

    @Test
    public void testRetrieveGeometricallyValidXPlanFeatures()
                            throws Exception {
        XPlanArchive archive = getTestArchive( resourceUnderTest.testReource );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        if ( resourceUnderTest.expectedPlanName != null )
            assertThat( fc.getPlanName(), is( resourceUnderTest.expectedPlanName ) );
        assertThat( fc.getPlanGkz(), is( resourceUnderTest.expectedPlanGz ) );
        assertThat( fc.getPlanNummer(), is( resourceUnderTest.expectedPlanNumber ) );
        assertThat( fc.getFeatures().size(), is( resourceUnderTest.expectedNumberOfFeatures ) );
    }

    private ValidatorResult validateGeometryAndReturnReport( XPlanArchive archive, List<ValidationOption> voOptions )
                            throws ValidatorException {
        XPlanVersion version = archive.getVersion();
        XPlanAde ade = archive.getAde();
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( version, ade );
        return new GeometricValidatorImpl().validateGeometry( archive, archive.getCrs(), schema, true, voOptions );
    }

    private XPlanFeatureCollection readFeaturesAndAssertGeometryValidity( XPlanArchive archive )
                            throws XMLStreamException, UnknownCRSException, ValidatorException {
        XPlanVersion version = archive.getVersion();
        XPlanAde ade = archive.getAde();
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( version, ade );
        return new GeometricValidatorImpl().retrieveGeometricallyValidXPlanFeatures( archive, archive.getCrs(), schema,
                                                                                     true, null );
    }

    private XPlanArchive getTestArchive( String name )
                            throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

    private static class ResourceUnderTest {

        String testReource;

        boolean expectedValidationResult;

        int expectedNumberOfErrors;

        int expectedNumberOfWarnings;

        int expectedNumberOBadGeometries;

        private String expectedPlanName;

        String expectedPlanGz;

        String expectedPlanNumber;

        int expectedNumberOfFeatures;

        ResourceUnderTest( String testReource, boolean expectedValidationResult, int expectedNumberOfErrors,
                           int expectedNumberOfWarnings, int expectedNumberOBadGeometries, String expectedPlanName,
                           String expectedPlanGz, String expectedPlanNumber, int expectedNumberOfFeatures ) {
            this.testReource = testReource;
            this.expectedValidationResult = expectedValidationResult;
            this.expectedNumberOfErrors = expectedNumberOfErrors;
            this.expectedNumberOfWarnings = expectedNumberOfWarnings;
            this.expectedNumberOBadGeometries = expectedNumberOBadGeometries;
            this.expectedPlanName = expectedPlanName;
            this.expectedPlanGz = expectedPlanGz;
            this.expectedPlanNumber = expectedPlanNumber;
            this.expectedNumberOfFeatures = expectedNumberOfFeatures;
        }
    }

}
