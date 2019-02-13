package de.latlon.xplan.validator.geometric;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.types.AppSchema;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
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
@RunWith(JUnitParamsRunner.class)
public class ParameterizedGeometricValidatorImplTest {

    private static final String NULL = "null";

    @FileParameters("src/test/resources/de/latlon/xplan/validator/geometric/geometricValidatorImplTest-validateGeometry-input.csv")
    @Test
    public void testValidateGeometry( String testResource, boolean expectedValidationResult,
                                      int expectedNumberOfErrors, int expectedNumberOfWarnings,
                                      int expectedNumberOBadGeometries )
                            throws Exception {
        XPlanArchive archive = getTestArchive( testResource );
        ValidatorResult report = validateGeometryAndReturnReport( archive, Collections.emptyList() );
        GeometricValidatorResult geometricReport = (GeometricValidatorResult) report;
        int numberOfErrors = geometricReport.getErrors().size();
        int numberOfWarnings = geometricReport.getWarnings().size();
        int numberOfBadGeometries = geometricReport.getBadGeometries().size();

        assertThat( report.isValid(), is( expectedValidationResult ) );
        assertThat( numberOfErrors, is( expectedNumberOfErrors ) );
        assertThat( numberOfWarnings, is( expectedNumberOfWarnings ) );
        assertThat( numberOfBadGeometries, is( expectedNumberOBadGeometries ) );
    }

    @FileParameters("src/test/resources/de/latlon/xplan/validator/geometric/geometricValidatorImplTest-retrieveFeatures-input.csv")
    @Test
    public void testRetrieveGeometricallyValidXPlanFeatures( String testResource, String expectedPlanName,
                                                             String expectedPlanGz, String expectedPlanNumber,
                                                             int expectedNumberOfFeatures )
                            throws Exception {
        XPlanArchive archive = getTestArchive( testResource );
        XPlanFeatureCollection fc = readFeaturesAndAssertGeometryValidity( archive );
        if ( !NULL.equals( expectedPlanName ) )
            assertThat( fc.getPlanName(), is( expectedPlanName ) );
        if ( NULL.equals( expectedPlanGz ) )
            assertThat( fc.getPlanGkz(), is( nullValue() ) );
        else
            assertThat( fc.getPlanGkz(), is( expectedPlanGz ) );
        if ( NULL.equals( expectedPlanNumber ) )
            assertThat( fc.getPlanNummer(), is( nullValue() ) );
        else
            assertThat( fc.getPlanNummer(), is( expectedPlanNumber ) );
        assertThat( fc.getFeatures().size(), is( expectedNumberOfFeatures ) );
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

}
