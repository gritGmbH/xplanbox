/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.edit.BaseData;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
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
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.DOKUMENT;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.PLANMITGEOREFERENZ;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.IMAGE_PNG;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.IMAGE_TIFF;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.RECHTSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEGRUENDUNG;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@RunWith(JUnitParamsRunner.class)
public class XPlanToEditFactoryTest {

    private XPlanToEditFactory factory = new XPlanToEditFactory();

    @Test
    public void testCreateXPlanToEdit_XPlan51_refScan()
                            throws Exception {
        FeatureCollection featureCollection = readXPlanGml( XPLAN_51, "xplan51/V4_1_ID_103_refScan.gml" );

        XPlanToEdit xPlanToEdit = factory.createXPlanToEdit( null, featureCollection );

        RasterBasis rasterBasis = xPlanToEdit.getRasterBasis();
        List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();

        assertThat( rasterBasis.getFeatureId(), is( nullValue() ) );
        assertThat( rasterReferences.size(), is( 1 ) );
        assertThat( rasterReferences.get( 0 ).getReference(), is( "B-Plan_Klingmuehl_Heideweg_Karte.tif" ) );
        assertThat( rasterReferences.get( 0 ).getGeoReference(), is( "B-Plan_Klingmuehl_Heideweg_Karte.tfw" ) );

    }

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
        FeatureCollection featureCollection = readXPlanGml( XPLAN_41, "xplan41/Eidelstedt_4_V4-Blankenese.gml" );

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
    @Parameters({ "xplan41/V4_1_ID_103.gml, XPLAN_41", "xplan50/V4_1_ID_103.gml, XPLAN_50" })
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
        assertThat( firstReference.getReferenzName(), is( "B-Plan_Klingmuehl_Heideweg_Leg" ) );
        assertThat( firstReference.getType(), is( BEGRUENDUNG ) );

        Reference secondReference = references.get( 1 );
        assertThat( secondReference.getGeoReference(), is( nullValue() ) );
        assertThat( secondReference.getReference(), is( "B-Plan_Klingmuehl_Heideweg.tif" ) );
        assertThat( secondReference.getReferenzName(), is( "B-Plan_Klingmuehl_Heideweg" ) );
        assertThat( secondReference.getType(), is( RECHTSPLAN ) );

        Reference thirdReference = references.get( 2 );
        assertThat( thirdReference.getGeoReference(), is( "B-Plan_Klingmuehl_Heideweg_Gruen.pgw" ) );
        assertThat( thirdReference.getReference(), is( "B-Plan_Klingmuehl_Heideweg_Gruen.png" ) );
        assertThat( thirdReference.getReferenzName(), is( "B-Plan_Klingmuehl_Heideweg_Gruen" ) );
        assertThat( thirdReference.getType(), is( GRUENORDNUNGSPLAN ) );

        List<Text> texts = xPlanToEdit.getTexts();
        assertThat( texts.size(), is( 1 ) );

        Text text = texts.get( 0 );
        assertThat( text.getFeatureId(), is( "FEATURE_0f870967-bd6f-4367-9150-8a255f0290ad" ) );
        assertThat( text.getKey(), is( "key" ) );
        assertThat( text.getBasis(), is( "base" ) );
        assertThat( text.getText(), is( "Beschreibungstext" ) );
        assertThat( text.getGeoReference(), is( nullValue() ) );
        assertThat( text.getReference(), is( "B-Plan_Klingmuehl_Heideweg_Text.pdf" ) );

        RasterBasis rasterBasis = xPlanToEdit.getRasterBasis();
        assertThat( rasterBasis.getFeatureId(), is( "FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d6" ) );

        List<RasterReference> rasterBasisReferences = rasterBasis.getRasterReferences();
        assertThat( rasterBasisReferences.size(), is( 2 ) );

        RasterReference scan = getByType( rasterBasisReferences, SCAN );
        assertThat( scan, is( notNullValue() ) );
        assertThat( scan.getFeatureId(), nullValue() );
        assertThat( scan.getGeoReference(), is( "B-Plan_Klingmuehl_Heideweg_Karte.tfw" ) );
        assertThat( scan.getReference(), is( "B-Plan_Klingmuehl_Heideweg_Karte.tif" ) );
        assertThat( scan.getReferenzName(), is( "B-Plan_Klingmuehl_Heideweg_Karte" ) );
        assertThat( scan.getArt(), is( PLANMITGEOREFERENZ ) );

        RasterReference legend = getByType( rasterBasisReferences, LEGEND );
        assertThat( legend, is( notNullValue() ) );
        assertThat( scan.getFeatureId(), nullValue() );
        assertThat( legend.getReference(), is( "B-Plan_Klingmuehl_Heideweg_Legende.png" ) );
        assertThat( legend.getReferenzMimeType(), is( IMAGE_PNG ) );
        assertThat( legend.getGeoReference(), is( nullValue() ) );
        assertThat( legend.getGeorefMimeType(), is( nullValue() ) );
        assertThat( legend.getInformationssystemURL(), is( "informationssystemURL" ) );
        assertThat( legend.getReferenzName(), is( "B-Plan_Klingmuehl_Heideweg_Legende" ) );
        assertThat( legend.getBeschreibung(), is( "beschreibung" ) );
        assertThat( legend.getDatum(), is( asDate( "2018-03-01" ) ) );
        assertThat( legend.getArt(), is( DOKUMENT ) );
    }

    @Test
    public void testCreateXPlanToEdit_XPlan3()
                            throws Exception {
        FeatureCollection featureCollection = readXPlanGml( XPLAN_3, "xplan30/Wuerdenhain.gml" );

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
        assertThat( firstReference.getReferenzName(), is( "Klarstellungssatzung_Haida_Begruendung" ) );
        assertThat( firstReference.getType(), is( BEGRUENDUNG ) );

        Reference secondReference = references.get( 1 );
        assertThat( secondReference.getGeoReference(), is( nullValue() ) );
        assertThat( secondReference.getReference(), is( "Klarstellungssatzung_Haida_Rechtsplan.tif" ) );
        assertThat( secondReference.getReferenzName(), is( "Klarstellungssatzung_Haida_Rechtsplan" ) );
        assertThat( secondReference.getType(), is( RECHTSPLAN ) );

        List<Text> texts = xPlanToEdit.getTexts();
        assertThat( texts.size(), is( 10 ) );

        Text firstText = texts.get( 0 );
        assertThat( firstText.getFeatureId(), is( "GML_05BD3F6F-70E4-4921-9399-42E5FBDFB6B4" ) );
        assertThat( firstText.getKey(), is( "Wuerdenhain" ) );
        assertThat( firstText.getBasis(), is( "Gesetz ABC" ) );
        assertThat( firstText.getText(), is( "Dies beschreibt..." ) );
        assertThat( firstText.getGeoReference(), is( nullValue() ) );
        assertThat( firstText.getReference(), is( "Klarstellungssatzung_Haida_cut_v4.tif" ) );

        RasterBasis rasterBasis = xPlanToEdit.getRasterBasis();
        assertThat( rasterBasis.getFeatureId(), is( "GML_F042504B-0875-4470-A25D-DAFD0595E8FD" ) );

        List<RasterReference> rasterBasisReferences = rasterBasis.getRasterReferences();
        assertThat( rasterBasisReferences.size(), is( 2 ) );

        RasterReference scan = getByType( rasterBasisReferences, SCAN );
        assertThat( scan, is( notNullValue() ) );
        assertThat( scan.getFeatureId(), is( "GML_1D000019-0DE0-4667-A19C-6EC6ABDF000B" ) );
        assertThat( scan.getReference(), is( "Klarstellungssatzung_Wuerdenhain_cut_ergb.tif" ) );
        assertThat( scan.getGeoReference(), is( "Klarstellungssatzung_Wuerdenhain_cut_ergb.tfw" ) );

        RasterReference legend = getByType( rasterBasisReferences, LEGEND );
        assertThat( legend, is( notNullValue() ) );
        assertThat( legend.getFeatureId(), is( "GML_1D000019-0DE0-4667-A19C-6EC6ABDF000F" ) );
        assertThat( legend.getReference(), is( "Klarstellungssatzung_Wuerdenhain_cut_ergb_legende.tif" ) );
        assertThat( legend.getReferenzMimeType(), is( IMAGE_TIFF ) );
        assertThat( legend.getGeoReference(), is( nullValue() ) );
        assertThat( legend.getGeorefMimeType(), is( nullValue() ) );
        assertThat( legend.getInformationssystemURL(), is( "informationssystemURL" ) );
        assertThat( legend.getReferenzName(), is( "Klarstellungssatzung_Wuerdenhain_cut_ergb_legende" ) );
        assertThat( legend.getBeschreibung(), is( "beschreibung" ) );
        assertThat( legend.getDatum(), is( nullValue() ) );
        assertThat( legend.getArt(), is( nullValue() ) );
    }

    @Test
    public void testCreateXPlanToEdit_ValidityPeriod()
                            throws Exception {
        FeatureCollection featureCollection = readXPlanGml( XPLAN_3, "xplan30/Wuerdenhain.gml" );

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
        FeatureCollection featureCollection = readXPlanGml( XPLAN_3, "xplan30/Wuerdenhain.gml" );

        XPlan xPlan = new XPlan();
        XPlanToEdit xPlanToEdit = factory.createXPlanToEdit( xPlan, featureCollection );
        ValidityPeriod validityPeriod = xPlanToEdit.getValidityPeriod();

        assertThat( validityPeriod.getStart(), is( nullValue() ) );
        assertThat( validityPeriod.getEnd(), is( nullValue() ) );
    }

    private RasterReference getByType( List<RasterReference> rasterBasisReferences, RasterReferenceType type ) {
        for ( RasterReference rasterReference : rasterBasisReferences ) {
            if ( type.equals( rasterReference.getType() ) )
                return rasterReference;
        }
        return null;
    }

    private XPlan createXPlan( Date startDateTime, Date endDateTime ) {
        XPlan xPlan = new XPlan();
        AdditionalPlanData xplanMetadata = new AdditionalPlanData( startDateTime, endDateTime );
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
        return (FeatureCollection) gmlStream.readFeature();
    }

    private Date asDate( String string )
                            throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        return simpleDateFormat.parse( string );
    }

}
