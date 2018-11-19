//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.edit;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.XPlanMetadata;
import de.latlon.xplan.manager.web.shared.edit.BaseData;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterWithReferences;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.Text;
import de.latlon.xplan.manager.web.shared.edit.ValidityPeriod;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.TEXT;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GREEN_STRUCTURES_PLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.LEGISLATION_PLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.REASON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@RunWith(JUnitParamsRunner.class)
public class XPlanToEditFactoryTest {

    private XPlanToEditFactory factory = new XPlanToEditFactory();

    @Test
    public void testCreateXPlanToEdit_XPlan50_BaseData_Changes()
                    throws Exception {
        FeatureCollection featureCollection = readXPlanGml( XPLAN_50, "xplan50/BP2070.gml" );

        XPlanToEdit xPlanToEdit = factory.createXPlanToEdit( null, featureCollection );

        BaseData baseData = xPlanToEdit.getBaseData();
        assertThat( baseData.getPlanName(), is( "BP2070" ) );
        assertThat( baseData.getDescription(), is( "Testdatensatz XPlabGML" ) );

        assertThat( baseData.getLegislationStatusCode(), is( -1 ) );
        assertThat( baseData.getPlanTypeCode(), is( 1000 ) );
        assertThat( baseData.getMethodCode(), is( -1 ) );
        assertThat( baseData.getOtherPlanTypeCode(), is( -1 ) );

        assertThat( baseData.getCreationDate(), is( asDate( "2001-08-06" ) ) );
        assertThat( baseData.getLossDate(), nullValue() );
        assertThat( baseData.getRegulationDate(), nullValue() );

        List<Change> changes = xPlanToEdit.getChanges();
        assertThat( changes.size(), is( 0 ) );
    }

    @Test
    public void testCreateXPlanToEdit_XPlan41_BaseData_Changes()
                    throws Exception {
        FeatureCollection featureCollection = readXPlanGml( XPLAN_41, "Eidelstedt_4_V4-Blankenese.gml" );

        XPlanToEdit xPlanToEdit = factory.createXPlanToEdit( null, featureCollection );

        BaseData baseData = xPlanToEdit.getBaseData();
        assertThat( baseData.getPlanName(), is( "Eidelstedt 4" ) );
        assertThat( baseData.getDescription(), is( "Beschreibung von Eidelstedt 4" ) );

        assertThat( baseData.getLegislationStatusCode(), is( 4000 ) );
        assertThat( baseData.getPlanTypeCode(), is( 1000 ) );
        assertThat( baseData.getMethodCode(), is( 2000 ) );
        assertThat( baseData.getOtherPlanTypeCode(), is( -1 ) );

        assertThat( baseData.getCreationDate(), is( asDate( "2010-10-07" ) ) );
        assertThat( baseData.getLossDate(), is( asDate( "2020-10-07" ) ) );
        assertThat( baseData.getRegulationDate(), is( asDate( "1970-01-01" ) ) );

        List<Change> changes = xPlanToEdit.getChanges();
        assertThat( changes.size(), is( 2 ) );

        Change firstChange = changes.get( 0 );
        assertThat( firstChange.getPlanName(), is( "Eidelstedt 3 (alt)" ) );
        assertThat( firstChange.getLegalNatureCode(), is( 1100 ) );
        assertThat( firstChange.getNumber(), is( "3" ) );
        assertThat( firstChange.getType(), is( CHANGES ) );

        Change secondChange = changes.get( 1 );
        assertThat( secondChange.getPlanName(), is( "Eidelstedt 4 (textliche Änderung)" ) );
        assertThat( secondChange.getLegalNatureCode(), is( 1000 ) );
        assertThat( secondChange.getNumber(), is( nullValue() ) );
        assertThat( secondChange.getType(), is( CHANGED_BY ) );
    }

    @Test
    @Parameters({ /*"V4_1_ID_103.gml, XPLAN_41",*/ "xplan50/V4_1_ID_103.gml, XPLAN_50" })
    public void testCreateXPlanToEdit_References_Texts( String planResource, String xplanVersion )
                    throws Exception {
        XPlanVersion version = XPlanVersion.valueOf( xplanVersion );
        FeatureCollection featureCollection = readXPlanGml( version, planResource );

        XPlanToEdit xPlanToEdit = factory.createXPlanToEdit( null, featureCollection );

        BaseData baseData = xPlanToEdit.getBaseData();
        assertThat( baseData.getPlanName(), is( "\"Heideweg\"" ) );
        assertThat( baseData.getLegislationStatusCode(), is( 4000 ) );
        assertThat( baseData.getPlanTypeCode(), is( 1000 ) );
        assertThat( baseData.getCreationDate(), is( asDate( "2001-08-27" ) ) );

        List<Change> changes = xPlanToEdit.getChanges();
        assertThat( changes.size(), is( 0 ) );

        List<Reference> references = xPlanToEdit.getReferences();
        assertThat( references.size(), is( 3 ) );

        Reference firstReference = references.get( 0 );
        assertThat( firstReference.getGeoReference(), is( nullValue() ) );
        assertThat( firstReference.getReference(), is( "B-Plan_Klingmuehl_Heideweg_Leg.pdf" ) );
        assertThat( firstReference.getType(), is( REASON ) );

        Reference secondReference = references.get( 1 );
        assertThat( secondReference.getGeoReference(), is( nullValue() ) );
        assertThat( secondReference.getReference(), is( "B-Plan_Klingmuehl_Heideweg.tif" ) );
        assertThat( secondReference.getType(), is( LEGISLATION_PLAN ) );

        Reference thirdReference = references.get( 2 );
        assertThat( thirdReference.getGeoReference(), is( "B-Plan_Klingmuehl_Heideweg_Gruen.pgw" ) );
        assertThat( thirdReference.getReference(), is( "B-Plan_Klingmuehl_Heideweg_Gruen.png" ) );
        assertThat( thirdReference.getType(), is( GREEN_STRUCTURES_PLAN ) );

        List<Text> texts = xPlanToEdit.getTexts();
        assertThat( texts.size(), is( 1 ) );

        Text text = texts.get( 0 );
        assertThat( text.getFeatureId(), is( "FEATURE_0f870967-bd6f-4367-9150-8a255f0290ad" ) );
        assertThat( text.getKey(), is( "key" ) );
        assertThat( text.getBasis(), is( "base" ) );
        assertThat( text.getText(), is( "Beschreibungstext" ) );
        assertThat( text.getGeoReference(), is( nullValue() ) );
        assertThat( text.getReference(), is( "B-Plan_Klingmuehl_Heideweg_Text.pdf" ) );

        RasterWithReferences rasterBasis = xPlanToEdit.getRasterBasis();
        assertThat( rasterBasis.getFeatureId(), is( "FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d6" ) );

        List<RasterReference> rasterBasisReferences = rasterBasis.getRasterReferences();
        assertThat( rasterBasisReferences.size(), is( 1 ) );

        RasterReference rasterBase = rasterBasisReferences.get( 0 );
        assertThat( rasterBase.getFeatureId(), nullValue() );
        assertThat( rasterBase.getGeoReference(), is( "B-Plan_Klingmuehl_Heideweg_Karte.tfw" ) );
        assertThat( rasterBase.getReference(), is( "B-Plan_Klingmuehl_Heideweg_Karte.tif" ) );
        assertThat( rasterBase.getType(), is( SCAN ) );
    }

    @Test
    public void testCreateXPlanToEdit_Xplan41_RasterPlanChanges()
                    throws Exception {
        FeatureCollection featureCollection = readXPlanGml( XPLAN_41, "V4_1_ID_103.gml" );

        XPlanToEdit xPlanToEdit = factory.createXPlanToEdit( null, featureCollection );

        List<RasterWithReferences> rasterPlanChanges = xPlanToEdit.getRasterPlanChanges();
        assertThat( rasterPlanChanges.size(), is( 1 ) );
        RasterWithReferences rasterPlanChange = rasterPlanChanges.get( 0 );
        assertThat( rasterPlanChange.getFeatureId(), is( "FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d7" ) );

        List<RasterReference> rasterPlanChangeReferences = rasterPlanChange.getRasterReferences();
        assertThat( rasterPlanChangeReferences.size(), is( 2 ) );
        RasterReference firstRasterPlanChange = rasterPlanChangeReferences.get( 0 );
        assertThat( firstRasterPlanChange.getFeatureId(), nullValue() );
        assertThat( firstRasterPlanChange.getGeoReference(), is( "B-Plan_Klingmuehl_Heideweg_KarteAenderung.pgw" ) );
        assertThat( firstRasterPlanChange.getReference(), is( "B-Plan_Klingmuehl_Heideweg_KarteAenderung.png" ) );
        assertThat( firstRasterPlanChange.getType(), is( SCAN ) );

        RasterReference secondRasterPlanChange = rasterPlanChangeReferences.get( 1 );
        assertThat( secondRasterPlanChange.getFeatureId(), nullValue() );
        assertThat( secondRasterPlanChange.getGeoReference(), is( nullValue() ) );
        assertThat( secondRasterPlanChange.getReference(), is( "B-Plan_Klingmuehl_Heideweg_Aenderung.pdf" ) );
        assertThat( secondRasterPlanChange.getType(), is( TEXT ) );
    }

    @Test
    public void testCreateXPlanToEdit_XPlan3()
                    throws Exception {
        FeatureCollection featureCollection = readXPlanGml( XPLAN_3, "Wuerdenhain.gml" );

        XPlanToEdit xPlanToEdit = factory.createXPlanToEdit( null, featureCollection );

        BaseData baseData = xPlanToEdit.getBaseData();
        assertThat( baseData.getPlanName(), is( "Klarstellungs-u..." ) );
        assertThat( baseData.getDescription(), is( "BPlan Wuerdenhain" ) );

        assertThat( baseData.getLegislationStatusCode(), is( 3000 ) );
        assertThat( baseData.getPlanTypeCode(), is( 40000 ) );
        assertThat( baseData.getMethodCode(), is( -1 ) );
        assertThat( baseData.getOtherPlanTypeCode(), is( -1 ) );

        assertThat( baseData.getCreationDate(), is( asDate( "2000-07-20" ) ) );
        assertThat( baseData.getLossDate(), is( asDate( "2020-07-20" ) ) );
        assertThat( baseData.getRegulationDate(), is( asDate( "1988-01-01" ) ) );

        List<Change> changes = xPlanToEdit.getChanges();
        assertThat( changes.size(), is( 2 ) );

        Change firstChange = changes.get( 0 );
        assertThat( firstChange.getPlanName(), is( "aendertText" ) );
        assertThat( firstChange.getLegalNatureCode(), is( -1 ) );
        assertThat( firstChange.getNumber(), is( nullValue() ) );
        assertThat( firstChange.getType(), is( CHANGES ) );

        Change secondChange = changes.get( 1 );
        assertThat( secondChange.getPlanName(), is( "wurdeGeaendertVonText" ) );
        assertThat( secondChange.getLegalNatureCode(), is( -1 ) );
        assertThat( secondChange.getNumber(), is( nullValue() ) );
        assertThat( secondChange.getType(), is( CHANGED_BY ) );

        List<Reference> references = xPlanToEdit.getReferences();
        assertThat( references.size(), is( 2 ) );

        Reference firstReference = references.get( 0 );
        assertThat( firstReference.getGeoReference(), is( "Klarstellungssatzung_Haida_Begruendung.tfw" ) );
        assertThat( firstReference.getReference(), is( "Klarstellungssatzung_Haida_Begruendung.tif" ) );
        assertThat( firstReference.getType(), is( REASON ) );

        Reference secondReference = references.get( 1 );
        assertThat( secondReference.getGeoReference(), is( nullValue() ) );
        assertThat( secondReference.getReference(), is( "Klarstellungssatzung_Haida_Rechtsplan.tif" ) );
        assertThat( secondReference.getType(), is( LEGISLATION_PLAN ) );

        List<Text> texts = xPlanToEdit.getTexts();
        assertThat( texts.size(), is( 10 ) );

        Text firstText = texts.get( 0 );
        assertThat( firstText.getFeatureId(), is( "GML_05BD3F6F-70E4-4921-9399-42E5FBDFB6B4" ) );
        assertThat( firstText.getKey(), is( "Wuerdenhain" ) );
        assertThat( firstText.getBasis(), is( "Gesetz ABC" ) );
        assertThat( firstText.getText(), is( "Dies beschreibt..." ) );
        assertThat( firstText.getGeoReference(), is( nullValue() ) );
        assertThat( firstText.getReference(), is( "Klarstellungssatzung_Haida_cut_v4.tif" ) );

        RasterWithReferences rasterBasis = xPlanToEdit.getRasterBasis();
        assertThat( rasterBasis.getFeatureId(), is( "GML_F042504B-0875-4470-A25D-DAFD0595E8FD" ) );

        List<RasterReference> rasterBasisReferences = rasterBasis.getRasterReferences();
        assertThat( rasterBasisReferences.size(), is( 1 ) );

        RasterReference rasterBase = rasterBasisReferences.get( 0 );
        assertThat( rasterBase.getFeatureId(), is( "GML_1D000019-0DE0-4667-A19C-6EC6ABDF000B" ) );
        assertThat( rasterBase.getGeoReference(), is( "Klarstellungssatzung_Wuerdenhain_cut_ergb.tfw" ) );
        assertThat( rasterBase.getReference(), is( "Klarstellungssatzung_Wuerdenhain_cut_ergb.tif" ) );
        assertThat( rasterBase.getType(), is( SCAN ) );

        List<RasterWithReferences> rasterPlanChanges = xPlanToEdit.getRasterPlanChanges();
        assertThat( rasterPlanChanges.size(), is( 1 ) );
        RasterWithReferences rasterPlanChange = rasterPlanChanges.get( 0 );
        assertThat( rasterPlanChange.getFeatureId(), is( "GML_F042504B-0875-4470-A25D-DAFD0595E8FE" ) );

        List<RasterReference> rasterPlanChangeReferences = rasterPlanChange.getRasterReferences();
        assertThat( rasterPlanChangeReferences.size(), is( 3 ) );

        RasterReference firstRasterPlanChange = rasterPlanChangeReferences.get( 0 );
        assertThat( firstRasterPlanChange.getFeatureId(), is( "GML_1D000019-0DE0-4667-A19C-6EC6ABDF000C" ) );
        assertThat( firstRasterPlanChange.getGeoReference(),
                    is( "Klarstellungssatzung_Wuerdenhain_cut_ergb_scan1.tfw" ) );
        assertThat( firstRasterPlanChange.getReference(), is( "Klarstellungssatzung_Wuerdenhain_cut_ergb_scan1.tif" ) );
        assertThat( firstRasterPlanChange.getType(), is( SCAN ) );

        RasterReference secondRasterPlanChange = rasterPlanChangeReferences.get( 1 );
        assertThat( secondRasterPlanChange.getFeatureId(), is( "GML_1D000019-0DE0-4667-A19C-6EC6ABDF000D" ) );
        assertThat( secondRasterPlanChange.getGeoReference(), is( nullValue() ) );
        assertThat( secondRasterPlanChange.getReference(),
                    is( "Klarstellungssatzung_Wuerdenhain_cut_ergb_scan2.tif" ) );
        assertThat( secondRasterPlanChange.getType(), is( SCAN ) );

        RasterReference thirdRasterPlanChange = rasterPlanChangeReferences.get( 2 );
        assertThat( thirdRasterPlanChange.getFeatureId(), is( "GML_1D000019-0DE0-4667-A19C-6EC6ABDF000E" ) );
        assertThat( thirdRasterPlanChange.getGeoReference(),
                    is( "Klarstellungssatzung_Wuerdenhain_cut_ergb_legende.tfw" ) );
        assertThat( thirdRasterPlanChange.getReference(),
                    is( "Klarstellungssatzung_Wuerdenhain_cut_ergb_legende.tif" ) );
        assertThat( thirdRasterPlanChange.getType(), is( LEGEND ) );
    }

    @Test
    public void testCreateXPlanToEdit_ValidityPeriod()
                    throws Exception {
        FeatureCollection featureCollection = readXPlanGml( XPLAN_3, "Wuerdenhain.gml" );

        Date startDateTime = asDate( "2002-01-01" );
        Date endDateTime = asDate( "2010-01-01" );
        XPlan xPlan = createXPlan( startDateTime, endDateTime );
        XPlanToEdit xPlanToEdit = factory.createXPlanToEdit( xPlan, featureCollection );
        ValidityPeriod validityPeriod = xPlanToEdit.getValidityPeriod();

        assertThat( validityPeriod.getStart(), is( startDateTime ) );
        assertThat( validityPeriod.getEnd(), is( endDateTime ) );
    }

    @Test
    public void testCreateXPlanToEdit_ValidityPeriod_Missing()
                    throws Exception {
        FeatureCollection featureCollection = readXPlanGml( XPLAN_3, "Wuerdenhain.gml" );

        XPlan xPlan = new XPlan();
        XPlanToEdit xPlanToEdit = factory.createXPlanToEdit( xPlan, featureCollection );
        ValidityPeriod validityPeriod = xPlanToEdit.getValidityPeriod();

        assertThat( validityPeriod.getStart(), is( nullValue() ) );
        assertThat( validityPeriod.getEnd(), is( nullValue() ) );
    }

    private XPlan createXPlan( Date startDateTime, Date endDateTime ) {
        XPlan xPlan = new XPlan();
        XPlanMetadata xplanMetadata = new XPlanMetadata( startDateTime, endDateTime );
        xPlan.setXplanMetadata( xplanMetadata );
        return xPlan;
    }

    private FeatureCollection readXPlanGml( XPlanVersion xplanVersion, String plan )
                    throws Exception {
        InputStream xplanGml = this.getClass().getResourceAsStream( plan );
        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader( xplanGml );
        XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper( reader, null );

        GeometryFactory geomFac = new GeometryFactory();
        GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader( xplanVersion.getGmlVersion(), xmlStream );
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( xplanVersion, null );
        gmlStream.setApplicationSchema( schema );
        gmlStream.setGeometryFactory( geomFac );
        return (FeatureCollection) gmlStream.readFeature( true );
    }

    private Date asDate( String string )
                    throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        return simpleDateFormat.parse( string );
    }

}