package de.latlon.xplan.validator.report.shapefile;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import org.deegree.feature.types.AppSchema;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_OPTIONS;
import static java.lang.String.format;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author bingel
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ShapefileGeneratorTest {

    @Rule
    public TemporaryFolder tempDir = new TemporaryFolder();

    @Test
    public void testShapeGeneration()
                    throws Exception {
        ValidatorReport validatorReport = createArchive();
        File directoryToCreateShapes = tempDir.newFolder();

        ShapefileGenerator shapefileGenerator = new ShapefileGenerator();
        shapefileGenerator.generateReport( validatorReport, "testShapeGenerator", directoryToCreateShapes );

        assertThat( directoryToCreateShapes, containsFile( ".shp", 2 ) );
        assertThat( directoryToCreateShapes, containsFile( ".shx", 2 ) );
        assertThat( directoryToCreateShapes, containsFile( ".dbf", 2 ) );
    }

    @Test
    public void testGeneratorHasBadGeometry()
                    throws Exception {

        ValidatorReport validatorReport = createArchive();
        ShapefileGenerator shapefileGenerator = new ShapefileGenerator();
        assertTrue( shapefileGenerator.hasBadGeometry( validatorReport ) );
    }

    private BaseMatcher<File> containsFile( final String fileEnding, final int numberOfOccurences ) {
        return new BaseMatcher<File>() {
            @Override
            public boolean matches( Object item ) {
                File directory = (File) item;
                File[] files = directory.listFiles();
                int countOccurences = 0;
                if ( files == null )
                    return false;
                for ( File file : files ) {
                    if ( file.getName().endsWith( fileEnding ) )
                        countOccurences++;
                }
                return numberOfOccurences == countOccurences;
            }

            @Override
            public void describeTo( Description description ) {
                String text = format( "Directory must contain exactly %s files with ending %s.", numberOfOccurences,
                                      fileEnding );
                description.appendText( text );
            }
        };
    }

    private ValidatorReport createArchive()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/FPlan.zip" );
        GeometricValidatorResult result = (GeometricValidatorResult) validateGeometryAndReturnReport( archive, SKIP_OPTIONS );
        ValidatorReport validatorReport = new ValidatorReport();
        validatorReport.setGeometricValidatorResult( result );
        return validatorReport;
    }

    private XPlanArchive getTestArchive( String name )
                    throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchiveFromZip( name, ResourceAccessor.readResourceStream( name ) );
    }

    private ValidatorResult validateGeometryAndReturnReport( XPlanArchive archive, List<ValidationOption> voOptions )
                    throws ValidatorException {
        XPlanVersion version = archive.getVersion();
        XPlanAde ade = archive.getAde();
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( version, ade );
        return ( new GeometricValidatorImpl() ).validateGeometry( archive, archive.getCrs(), schema, true, voOptions );
    }

}