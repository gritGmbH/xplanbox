package de.latlon.xplan.manager.synthesizer;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.copy;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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
import de.latlon.xplan.commons.XPlanFeatureCollection;
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
        File notExist = new File( "/tmp/notExistDir" );
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer( notExist );

        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        XPlanFeatureCollection xplanFc = parseFeatureCollection( archive );
        xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );

        assertThat( xPlanSynthesizer.getRules().size(), is( 7884 ) );
    }

    @Test
    public void testSynthesize_ConfigDirectoryIsFile()
                            throws Exception {
        File configFile = File.createTempFile( "synConfig", "" );
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer( configFile );

        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        XPlanFeatureCollection xplanFc = parseFeatureCollection( archive );
        xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );

        assertThat( xPlanSynthesizer.getRules().size(), is( 7884 ) );
    }

    @Test
    public void testSynthesize_ConfigDirectoryDoesNotContainRules()
                            throws Exception {
        File configDirectory = createTmpDirectory();
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer( configDirectory );

        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        XPlanFeatureCollection xplanFc = parseFeatureCollection( archive );
        xPlanSynthesizer.synthesize( archive.getVersion(), xplanFc );

        assertThat( xPlanSynthesizer.getRules().size(), is( 7884 ) );
    }

    @Test
    public void testSynthesize_ConfigDirectoryWithRule()
                            throws Exception {
        File notExist = createTmpDirectoryAndCopyRuleFile();
        XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer( notExist );

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

    private File createTmpDirectoryAndCopyRuleFile()
                            throws IOException {
        File tmpDirectory = createTmpDirectory();
        copyFile( tmpDirectory, "xplan41.syn" );
        copyFile( tmpDirectory, "XP_BesondereArtDerBaulNutzung.xml" );
        return tmpDirectory;
    }

    private void copyFile( File tmpDirectory, String fileName )
                            throws IOException {
        File targetFile = new File( tmpDirectory, fileName );
        targetFile.createNewFile();
        FileOutputStream targetOutputStream = new FileOutputStream( targetFile );
        InputStream resourceAsStream = XPlanSynthesizerTest.class.getResourceAsStream( fileName );
        try {
            copy( resourceAsStream, targetOutputStream );
        } finally {
            closeQuietly( resourceAsStream );
            closeQuietly( targetOutputStream );
        }
    }

    private File createTmpDirectory()
                            throws IOException {
        File notExist = File.createTempFile( "configDirectory", "" );
        notExist.delete();
        notExist.mkdir();
        return notExist;
    }

}