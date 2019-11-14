package de.latlon.xplan.validator.wms;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import org.apache.commons.io.IOUtils;
import org.deegree.feature.types.AppSchema;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xmlmatchers.namespace.SimpleNamespaceContext;

import javax.xml.namespace.NamespaceContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;
import static org.xmlmatchers.transform.XmlConverters.the;
import static org.xmlmatchers.xpath.XpathReturnType.returningANumber;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidatorWmsManagerTest {

    private static Path workspaceLocation;

    @BeforeClass
    public static void initWorkspace()
                            throws IOException {
        workspaceLocation = Files.createTempDirectory( "ValidatorWmsManagerTest" );
        Path xplanSynFile = workspaceLocation.resolve( "datasources/feature/xplansyn.xml" );
        Files.createDirectories( xplanSynFile.getParent() );
        Files.createFile( xplanSynFile );

        OutputStream outputStream = Files.newOutputStream( xplanSynFile );
        IOUtils.copy( ValidatorWmsManagerTest.class.getResourceAsStream( "xplansyn.xml" ), outputStream );
    }

    @Test
    public void testInsert()
                            throws Exception {
        XPlanSynthesizer synthesizer = new XPlanSynthesizer();
        ValidatorWmsManager validatorWmsManager = new ValidatorWmsManager( synthesizer, workspaceLocation );

        XPlanFeatureCollection featureCollection = parseFeatureCollection( "xplan51/BP2070.zip" );
        validatorWmsManager.insert( featureCollection );

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IOUtils.copy( Files.newInputStream( workspaceLocation.resolve( "datasources/feature/xplansyn.xml" ) ), bos );
        assertThat( the( bos.toString() ),
                    hasXPath( "count(/m:MemoryFeatureStore/m:GMLSchema)", nsBindings(), returningANumber(),
                              is( 1d ) ) );
        List<Path> dataFiles = Files.list( workspaceLocation.resolve( "data" ) ).collect( Collectors.toList() );
        assertThat( dataFiles.size(), is( 1 ) );
    }

    private NamespaceContext nsBindings() {
        SimpleNamespaceContext simpleNamespaceContext = new SimpleNamespaceContext();
        simpleNamespaceContext.bind( "m", "http://www.deegree.org/datasource/feature/memory" );
        return simpleNamespaceContext;
    }

    protected XPlanFeatureCollection parseFeatureCollection( String name )
                            throws Exception {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip( name, ResourceAccessor.readResourceStream( name ) );
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( archive.getVersion(), archive.getAde() );
        GeometricValidatorImpl geometricValidator = new GeometricValidatorImpl();
        return geometricValidator.retrieveGeometricallyValidXPlanFeatures( archive, archive.getCrs(), schema, true,
                                                                           null );

    }

}