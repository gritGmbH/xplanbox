package de.latlon.xplan.manager.synthesizer;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.gml.property.Property;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanSynthesizerTest {

    @Test
    public void testSynthesize_DefaultConfiguration()
                            throws Exception {
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        XPlanFeatureCollection xplanFc = parseFeatureCollection( archive );
        xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );

        assertThat( xPlanSynthesizer.getRules().size(), is( 7884 ) );
    }

    @Test
    public void testSynthesize_ConfigDirectoryIsInvalid()
                            throws Exception {
        Path notExist = Paths.get( "/tmp/notExistDir" );
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer( notExist );

        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        XPlanFeatureCollection xplanFc = parseFeatureCollection( archive );
        xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );

        assertThat( xPlanSynthesizer.getRules().size(), is( 7884 ) );
    }

    @Test
    public void testSynthesize_ConfigDirectoryIsFile()
                            throws Exception {
        Path configFile = Files.createTempFile( "synConfig", "" );
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer( configFile );

        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        XPlanFeatureCollection xplanFc = parseFeatureCollection( archive );
        xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );

        assertThat( xPlanSynthesizer.getRules().size(), is( 7884 ) );
    }

    @Test
    public void testSynthesize_ConfigDirectoryDoesNotContainRules()
                            throws Exception {
        Path configDirectory = createTmpDirectory();
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer( configDirectory );

        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        XPlanFeatureCollection xplanFc = parseFeatureCollection( archive );
        xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );

        assertThat( xPlanSynthesizer.getRules().size(), is( 7884 ) );
    }

    @Test
    public void testSynthesize_ConfigDirectoryWithRule()
                            throws Exception {
        Path configDirectory = createTmpDirectoryAndCopyRuleFile( "xplan41.syn", "XP_BesondereArtDerBaulNutzung.xml" );
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer( configDirectory );

        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        XPlanFeatureCollection xplanFc = parseFeatureCollection( archive );
        FeatureCollection synthesizedFeatures = xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );

        assertThat( xPlanSynthesizer.getRules().size(), is( 7885 ) );
        Iterator<Feature> it = synthesizedFeatures.iterator();
        while ( it.hasNext() ) {
            Feature feature = it.next();
            if ( "BP_BaugebietsTeilFlaeche".equals( feature.getName().getLocalPart() ) ) {
                List<Property> properties = feature.getProperties( new QName( feature.getName().getNamespaceURI(),
                                                                              "besondereArtDerBaulNutzung" ) );

                assertThat( properties.get( 0 ).getValue().toString(),
                            anyOf( is( "AllgWohngebietTest" ), is( "MischgebietTest" ) ) );
            }

        }
    }

    @Test
    public void testSynthesize_Synthesize_Enumeration_XP_BesondereArtDerBaulNutzung()
                            throws Exception {
        Path configDirectory = createTmpDirectoryAndCopyRuleFile( "xplan41_XP_BesondereArtDerBaulNutzung.syn",
                                                                  "xplan_XP_BesondereArtDerBaulNutzung.xml" );
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer( configDirectory );

        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        XPlanFeatureCollection xplanFc = parseFeatureCollection( archive );
        FeatureCollection synthesizedFeatures = xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );

        assertThat( xPlanSynthesizer.getRules().size(), is( 7884 ) );

        String firstPropertyValue = valueOfFirstProperty( synthesizedFeatures, "BP_BaugebietsTeilFlaeche", "besondereArtDerBaulNutzung" );
        assertThat( firstPropertyValue, is("Art2") );
    }

    @Test
    public void testSynthesize_Synthesize_Codelist_BP_DetailArtDerBaulNutzung()
                            throws Exception {
        Path configDirectory = createTmpDirectoryAndCopyRuleFile( "xplan41_BP_DetailArtDerBaulNutzung.syn",
                                                                  "xplan_BP_DetailArtDerBaulNutzung.xml" );
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer( configDirectory );

        XPlanArchive archive = getTestArchive( "xplan41/BP2070-detailierteArtDerBaulNutzung.zip" );
        XPlanFeatureCollection xplanFc = parseFeatureCollection( archive );
        FeatureCollection synthesizedFeatures = xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );

        assertThat( xPlanSynthesizer.getRules().size(), is( 7884 ) );

        String firstPropertyValue = valueOfFirstProperty( synthesizedFeatures, "BP_BaugebietsTeilFlaeche", "detaillierteArtDerBaulNutzung" );
        assertThat( firstPropertyValue, is("Wohngebiet11") );
    }

    private XPlanFeatureCollection parseFeatureCollection( XPlanArchive archive )
                            throws Exception {
        XPlanFeatureCollection xplanFc = readFeatures( archive );
        int id = 1;
        for ( Feature feature : xplanFc.getFeatures() ) {
            feature.setId( "FEATURE_" + id++ );
        }
        return xplanFc;
    }

    private XPlanFeatureCollection readFeatures( XPlanArchive archive )
                            throws Exception {
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( archive.getVersion(), archive.getAde() );
        ICRS crs = CRSManager.lookup( "EPSG:31467" );
        if ( archive.getCrs() != null ) {
            crs = archive.getCrs();
        }
        return ( new GeometricValidatorImpl() ).retrieveGeometricallyValidXPlanFeatures( archive, crs, schema, true,
                                                                                         null );
    }

    private XPlanArchive getTestArchive( String name )
                            throws URISyntaxException, IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

    private Path createTmpDirectoryAndCopyRuleFile( String synFile, String codelistFile )
                            throws IOException {
        Path tmpDirectory = createTmpDirectory();
        System.out.println( "Tmp dir: " + tmpDirectory );
        copyFile( tmpDirectory, synFile, "xplan41.syn" );
        copyFile( tmpDirectory, codelistFile, codelistFile );
        return tmpDirectory;
    }

    private void copyFile( Path tmpDirectory, String fileName, String targetFileName )
                            throws IOException {
        Path targetFile = tmpDirectory.resolve( targetFileName );
        InputStream resourceAsStream = XPlanSynthesizerTest.class.getResourceAsStream( fileName );
        try {
            Files.copy( resourceAsStream, targetFile );
        } finally {
            closeQuietly( resourceAsStream );
        }
    }

    private Path createTmpDirectory()
                            throws IOException {
        return Files.createTempDirectory( "configDirectory" );
    }

    private String valueOfFirstProperty( FeatureCollection synthesizedFeatures, String featureType, String propertyName ) {
        Iterator<Feature> it = synthesizedFeatures.iterator();
        while ( it.hasNext() ) {
            Feature feature = it.next();
            if ( featureType.equals( feature.getName().getLocalPart() ) ) {
                List<Property> properties = feature.getProperties( new QName( feature.getName().getNamespaceURI(),
                                                                              propertyName) );

                    return properties.get( 0 ).getValue().toString();
            }

        }
        return null;
    }

}