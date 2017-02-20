package de.latlon.xplan.commons.archive;

import de.latlon.xplan.ResourceAccessor;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;

import static de.latlon.xplan.commons.XPlanAde.NSM;
import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.FP_Plan;
import static de.latlon.xplan.commons.XPlanType.RP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_2;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class XPlanArchiveCreatorTest {

    @Test
    public void testMetadataBP2070XPlan2()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan2/BP2070.zip" );
        assertEquals( XPLAN_2, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataBP2135XPlan2()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan2/BP2135.zip" );
        assertEquals( XPLAN_2, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertNull( archive.getCrs() );
    }

    @Test
    public void testMetadataFPlanXPlan2()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan2/FPlan.zip" );
        assertEquals( XPLAN_2, archive.getVersion() );
        assertEquals( FP_Plan, archive.getType() );
        assertNull( archive.getCrs() );
    }

    @Test
    public void testMetadataLA22XPlan2()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan2/LA22.zip" );
        assertEquals( XPLAN_2, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataLA67XPlan2()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan2/LA67.zip" );
        assertEquals( XPLAN_2, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertNull( archive.getCrs() );
    }

    @Test
    public void testMetadataBP2070XPlan3()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan3/BP2070.zip" );
        assertEquals( XPLAN_3, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataBP2135XPlan3()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan3/BP2135.zip" );
        assertEquals( XPLAN_3, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataFPlanXPlan3()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan3/FPlan.zip" );
        assertEquals( XPLAN_3, archive.getVersion() );
        assertEquals( FP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataWuerdenhainXPlan3()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan3/BP2070-Finkenwerder.zip" );
        assertEquals( XPLAN_3, archive.getVersion() );
        assertEquals( "Finkenwerder", archive.getDistrict() );
    }

    @Test
    public void testMetadataWuerdenhainXPlan3WithMapper()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchiveWithMapper( "xplan3/BP2070-Finkenwerder.zip" );
        assertEquals( XPLAN_3, archive.getVersion() );
        assertEquals( "Hamburg-Mitte", archive.getDistrict() );
    }

    @Test
    public void testMetadataBP2070XPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/BP2070.zip" );
        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataBP2135XPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/BP2135.zip" );
        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataDemoXPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/Demo.zip" );
        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataEidelstedt_4_V4XPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/Eidelstedt_4_V4.zip" );
        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "urn:adv:crs:ETRS89_UTM32", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataFPlanXPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/FPlan.zip" );
        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( FP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataLA22XPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataLA67XPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/LA67.zip" );
        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataV4_1_ID_103_41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/V4_1_ID_103.zip" );

        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:25833", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataEidelstedt4V4EimsbuettelXPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/Eidelstedt_4_V4-Eimsbuettel.zip" );

        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( "Eimsbüttel", archive.getDistrict() );
    }

    @Test
    public void testMetadataEidelstedt4V4EimsbuettelXPlan41WithMapper()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchiveWithMapper( "xplan41/Eidelstedt_4_V4-Eimsbuettel.zip" );

        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( "Eimsbüttel", archive.getDistrict() );
    }

    @Test
    public void testMetadataV4_1_ID_66_40()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan40/V4_1_ID_66.zip" );
        assertEquals( XPLAN_40, archive.getVersion() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "EPSG:25833", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataV4_1_NSM_Niedersachsen_lrop_Small()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/nsm/nsm_niedersachsen_lrop_small.zip" );
        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( NSM, archive.getAde() );
        assertEquals( RP_Plan, archive.getType() );
        assertEquals( "EPSG:31467", archive.getCrs().getName() );
    }

    @Test
    public void testMetadataEidelstedt_4_V4XPlan41_With_NSM_Namespace()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/Eidelstedt_4_V4_with_nsm_namespace.zip" );
        assertEquals( XPLAN_41, archive.getVersion() );
        assertEquals( null, archive.getAde() );
        assertEquals( BP_Plan, archive.getType() );
        assertEquals( "urn:adv:crs:ETRS89_UTM32", archive.getCrs().getName() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlanWithWrongGmlFileNameShouldThrowIllegalArgumentException()
                    throws Exception {
        getTestArchive( "xplan41/Eidelstedt_4_V4-wrongGmlFileName.zip" );
    }

    private XPlanArchive getTestArchive( String name )
                    throws URISyntaxException, IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

    private XPlanArchive getTestArchiveWithMapper( String name )
                    throws URISyntaxException, IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator( mockMapper() );
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

    private LocalCenterToDistrictMapper mockMapper() {
        LocalCenterToDistrictMapper mock = Mockito.mock( LocalCenterToDistrictMapper.class );
        when( mock.mapToDistrict( "Eimsbüttel" ) ).thenReturn( "Eimsbüttel" );
        when( mock.mapToDistrict( "Finkenwerder" ) ).thenReturn( "Hamburg-Mitte" );
        when( mock.mapToDistrict( null ) ).thenReturn( null );
        return mock;
    }
}