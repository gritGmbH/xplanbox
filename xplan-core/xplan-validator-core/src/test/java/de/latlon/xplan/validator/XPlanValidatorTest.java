package de.latlon.xplan.validator;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.validator.geometric.GemetricValidatorParsingResult;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ReportArchiveGenerator;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.report.RuleResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;
import org.apache.commons.io.IOUtils;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.types.AppSchema;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_SO;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_XP;
import static de.latlon.xplan.validator.web.shared.ValidationType.GEOMETRIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SEMANTIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SYNTACTIC;
import static java.util.Collections.singletonList;
import static org.deegree.cs.persistence.CRSManager.lookup;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Tests for <link>XPlanValidator</link>
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class XPlanValidatorTest {

    private GeometricValidator geoVal;

    private SemanticValidator semVal;

    private SyntacticValidator synVal;

    private static File planToValidate;

    @Before
    public void resetMocks()
                    throws Exception {
        geoVal = mockGeometricValidator();
        semVal = mockSemanticValidator();
        synVal = mockSyntacticValidator();
    }

    @BeforeClass
    public static void initFileToValidate()
                            throws IOException {
        InputStream input = ResourceAccessor.readResourceStream( "xplan51/V4_1_ID_103.gml" );
        Path xPlanGml = Files.createTempFile( "XPlanValidatorTest", ".gml" );
        FileOutputStream output = new FileOutputStream( xPlanGml.toFile() );
        IOUtils.copy( input, output );
        input.close();
        output.close();
        planToValidate = xPlanGml.toFile();
    }

    @Test
    public void testValidateNotWriteReportNoSettings()
                    throws Exception {
        ValidationSettings settings = new ValidationSettings();
        executeValidator( geoVal, semVal, synVal, settings );

        verify( synVal, times( 1 ) ).validateSyntax( archive() );
        verify( geoVal, times( 0 ) ).validateGeometry( archive(), crs(), schema(), anyBoolean(), list() );
        verifyZeroInteractions( semVal );
        verify( geoVal, times( 1 ) ).retrieveGeometricallyValidXPlanFeatures( archive(), crs(), schema(), anyBoolean(),
                                                                              anyString() );
    }

    @Test
    public void testValidateNotWriteReportTypeSyntax()
                    throws Exception {
        ValidationSettings settings = new ValidationSettings( "", singletonList( SYNTACTIC ), emptyList() );
        executeValidator( geoVal, semVal, synVal, settings );

        verify( synVal, times( 1 ) ).validateSyntax( archive() );
        verify( geoVal, times( 0 ) ).validateGeometry( archive(), crs(), schema(), anyBoolean(), list() );
        verifyZeroInteractions( semVal );
        verify( geoVal, times( 1 ) ).retrieveGeometricallyValidXPlanFeatures( archive(), crs(), schema(), anyBoolean(),
                                                                              anyString() );
    }

    @Test
    public void testValidateNotWriteReportTypeGeometry()
                    throws Exception {
        ValidationSettings settings = new ValidationSettings( "", singletonList( GEOMETRIC ), emptyList() );
        executeValidator( geoVal, semVal, synVal, settings );

        verify( synVal, times( 1 ) ).validateSyntax( archive() );
        verify( geoVal, times( 1 ) ).validateGeometry( archive(), crs(), schema(), anyBoolean(), list() );
        verify( geoVal, times( 1 ) ).retrieveGeometricallyValidXPlanFeatures( archive(), crs(), schema(), anyBoolean(),
                                                                              anyString() );
        verifyZeroInteractions( semVal );
    }

    @Test
    public void testValidateNotWriteReportTypeSemantic()
                    throws Exception {
        ValidationSettings settings = new ValidationSettings( "", singletonList( SEMANTIC ), emptyList() );
        executeValidator( geoVal, semVal, synVal, settings );

        verify( synVal, times( 1 ) ).validateSyntax( archive() );
        verify( geoVal, times( 0 ) ).validateGeometry( archive(), crs(), schema(), anyBoolean(), list() );
        verify( semVal, times( 1 ) ).validateSemantic( archive(), list() );
        verify( geoVal, times( 1 ) ).retrieveGeometricallyValidXPlanFeatures( archive(), crs(), schema(), anyBoolean(),
                                                                              anyString() );
    }

    @Test
    public void testValidateNotWriteReportAllTypes()
                            throws Exception {
        List<ValidationType> validationTypes = Arrays.asList( new ValidationType[] { SYNTACTIC, SEMANTIC, GEOMETRIC } );
        ValidationSettings settings = new ValidationSettings( "", validationTypes, emptyList() );
        executeValidator( geoVal, semVal, synVal, settings );

        verify( synVal, times( 1 ) ).validateSyntax( archive() );
        verify( geoVal, times( 1 ) ).validateGeometry( archive(), crs(), schema(), anyBoolean(), list() );
        verify( semVal, times( 1 ) ).validateSemantic( archive(), list() );
        verify( geoVal, times( 1 ) ).retrieveGeometricallyValidXPlanFeatures( archive(), crs(), schema(), anyBoolean(),
                                                                              anyString() );
    }

    @Test
    public void testValidateNotWriteReportTypeEmpty()
                    throws Exception {
        ValidationSettings settings = new ValidationSettings( "", emptyList(), emptyList() );
        executeValidator( geoVal, semVal, synVal, settings );

        verify( synVal, times( 1 ) ).validateSyntax( archive() );
        verify( geoVal, times( 0 ) ).validateGeometry( archive(), crs(), schema(), anyBoolean(), list() );
        verify( semVal, times( 0 ) ).validateSemantic( archive(), list() );
        verify( geoVal, times( 1 ) ).retrieveGeometricallyValidXPlanFeatures( archive(), crs(), schema(), anyBoolean(),
                                                                              anyString() );
    }

    @Test
    public void testWriteReport_Valid()
                    throws Exception {
        ValidationSettings semanticSettings = new ValidationSettings( "", singletonList( SEMANTIC ), emptyList() );
        ValidatorReport semanticReportNotValid = executeValidator( geoVal, semVal, synVal, semanticSettings );

        assertThat( semanticReportNotValid.isReportValid(), is( false ) );

        assertThat( semanticReportNotValid.getSemanticValidatorResult(), containsSemanticResult( "message", "name" ) );
        assertThat( semanticReportNotValid.getSyntacticValidatorResult(), containsSyntaticResult( "message" ) );
    }

    @Test
    public void testWriteReport_Invalid()
                            throws Exception {
        List<ValidationType> validationTypes = Arrays.asList( new ValidationType[] { SYNTACTIC, SEMANTIC, GEOMETRIC } );
        ValidationSettings settings = new ValidationSettings( "", validationTypes, emptyList() );
        ValidatorReport report = executeValidator( geoVal, semVal, synVal, settings );

        assertThat( report.isReportValid(), is( false ) );

        assertThat( report.getSemanticValidatorResult(), containsSemanticResult( "message", "name" ) );
        assertThat( report.getSyntacticValidatorResult(), containsSyntaticResult( "message" ) );
        assertThat( report.getGeometricValidatorResult(), containsGeometricResult() );
    }

    private Matcher<SyntacticValidatorResult> containsSyntaticResult( final String messageToCheck ) {
        return new TypeSafeMatcher<SyntacticValidatorResult>() {
            @Override
            public boolean matchesSafely( SyntacticValidatorResult result ) {
                if ( messageToCheck != null ) {
                    String firstMessage = result.getMessages().get( 0 );
                    boolean doesTypeMatch = "Syntaktische Validierung".equals( result.getType() );
                    boolean doesMessageMatch = firstMessage.equals( "message" );
                    return doesMessageMatch && doesTypeMatch;
                } else
                    return true;
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Report should contain a SyntacticValidatorResult" );
                description.appendText( " With message " + messageToCheck );
            }
        };
    }

    private Matcher<SemanticValidatorResult> containsSemanticResult( final String messageToCheck,
                                                                     final String nameToCheck ) {
        return new TypeSafeMatcher<SemanticValidatorResult>() {
            @Override
            public boolean matchesSafely( SemanticValidatorResult result ) {
                RuleResult firstRule = result.getRules().get( 0 );
                String firstMessage = firstRule.getMessage();
                if ( !firstMessage.equals( messageToCheck ) )
                    return false;

                String firstName = firstRule.getName();
                if ( !firstName.equals( nameToCheck ) )
                    return false;
                return "Semantische Validierung".equals( result.getType() );
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Report should contain a SemanticRuleResult" );
                description.appendText( " With message " ).appendValue( messageToCheck );
                description.appendText( " With name " ).appendValue( nameToCheck );
            }
        };
    }

    private Matcher<GeometricValidatorResult> containsGeometricResult() {
        return new TypeSafeMatcher<GeometricValidatorResult>() {
            @Override
            public boolean matchesSafely( GeometricValidatorResult result ) {
                boolean doesTypeMatch = "Geometrische Validierung".equals( result.getType() );
                boolean areErrorsAndWarningsEmpty = result.getWarnings().size() == 0 && result.getErrors().size() == 0;
                return doesTypeMatch && areErrorsAndWarningsEmpty;
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Report should contain a GeometricValidatorResult" );
            }
        };
    }

    private ValidatorReport executeValidator( GeometricValidator geomVal, SemanticValidator semVal,
                                              SyntacticValidator synVal, ValidationSettings settings )
                            throws IOException, ValidatorException, ParseException, ReportGenerationException {
        XPlanValidator validator = new XPlanValidator( geomVal, synVal, semVal, mock( ReportArchiveGenerator.class ) );
        return validator.validate( settings, planToValidate, "planname" );
    }

    private SemanticValidator mockSemanticValidator() {
        SemanticValidator mock = mock( SemanticValidator.class );
        SemanticValidatorResult toBeReturned = new SemanticValidatorResult();
        toBeReturned.addRule( "name", "message", Collections.emptyList() );
        doReturn( toBeReturned ).when( mock ).validateSemantic( archive(), list() );
        return mock;
    }

    private SyntacticValidator mockSyntacticValidator() {
        SyntacticValidator mock = mock( SyntacticValidator.class );
        SyntacticValidatorResult toBeReturned = new SyntacticValidatorResult( singletonList( "message" ), true, null );
        doReturn( toBeReturned ).when( mock ).validateSyntax( archive() );
        return mock;
    }

    private GeometricValidator mockGeometricValidator()
                    throws Exception {
        GeometricValidator geomVal = spy( new GeometricValidatorImpl() );
        GeometricValidatorResult result = new GeometricValidatorResult( emptyList(), emptyList(), emptyList(),
                        lookup( "epsg:4326" ), true );
        GemetricValidatorParsingResult gemetricValidatorParsingResult = mock( GemetricValidatorParsingResult.class );
        doReturn( result ).when( gemetricValidatorParsingResult ).getValidatorResult();
        doReturn( gemetricValidatorParsingResult ).when( geomVal ).validateGeometry( archive(), crs(), schema(),
                                                                                     anyBoolean(), list() );
        return geomVal;
    }

    private List emptyList() {
        return Collections.emptyList();
    }

    private List list() {
        return any( List.class );
    }

    private ICRS crs() {
        return any( ICRS.class );
    }

    private List<ValidationOption> singleOption( String name ) {
        return singletonList( new ValidationOption( name ) );
    }

    private AppSchema schema() {
        return any( AppSchema.class );
    }

    private XPlanArchive archive() {
        return any( XPlanArchive.class );
    }
}
