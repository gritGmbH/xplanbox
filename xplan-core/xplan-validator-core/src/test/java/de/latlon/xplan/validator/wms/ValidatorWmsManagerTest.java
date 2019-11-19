package de.latlon.xplan.validator.wms;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import org.deegree.feature.types.AppSchema;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidatorWmsManagerTest {

    private static Path workspaceLocation;

    @BeforeClass
    public static void initWorkspace()
                            throws IOException {
        workspaceLocation = Files.createTempDirectory( "ValidatorWmsManagerTest" );
    }

    @Test
    public void testInsert()
                            throws Exception {
        XPlanSynthesizer synthesizer = new XPlanSynthesizer();
        ValidatorWmsManager validatorWmsManager = new ValidatorWmsManager( synthesizer, workspaceLocation );

        XPlanFeatureCollection featureCollection = parseFeatureCollection( "xplan51/BP2070.zip" );
        validatorWmsManager.insert( featureCollection );

        List<Path> dataFiles = Files.list( workspaceLocation.resolve( "data" ) ).collect( Collectors.toList() );
        assertThat( dataFiles.size(), is( 1 ) );
    }

    private XPlanFeatureCollection parseFeatureCollection( String name )
                            throws Exception {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip( name,
                                                                         ResourceAccessor.readResourceStream( name ) );
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( archive.getVersion(), archive.getAde() );
        GeometricValidatorImpl geometricValidator = new GeometricValidatorImpl();
        return geometricValidator.retrieveGeometricallyValidXPlanFeatures( archive, archive.getCrs(), schema, true,
                                                                           null );

    }

}