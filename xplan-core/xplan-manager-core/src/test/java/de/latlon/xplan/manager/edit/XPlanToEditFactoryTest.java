/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.manager.edit;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.BaseData;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.Text;
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

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.DOKUMENT;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.PLANMITGEOREFERENZ;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.IMAGE_PNG;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEGRUENDUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.RECHTSPLAN;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@RunWith(JUnitParamsRunner.class)
public class XPlanToEditFactoryTest {

	private XPlanToEditFactory factory = new XPlanToEditFactory();

	@Test
	public void testCreateXPlanToEdit_XPlan51_refScan() throws Exception {
		FeatureCollection featureCollection = readXPlanGml(XPLAN_51, "xplan51/V4_1_ID_103_refScan.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_51), featureCollection);

		assertThat(xPlanToEdit.isHasBereich(), is(true));

		RasterBasis rasterBasis = xPlanToEdit.getRasterBasis();
		List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();

		assertThat(rasterBasis.getFeatureId(), is(nullValue()));
		assertThat(rasterReferences.size(), is(1));
		assertThat(rasterReferences.get(0).getReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tif"));
		assertThat(rasterReferences.get(0).getGeoReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tfw"));

	}

	@Test
	public void testCreateXPlanToEdit_XPlan51_rasterdarstellung() throws Exception {
		FeatureCollection featureCollection = readXPlanGml(XPLAN_51, "xplan51/V4_1_ID_103.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_51), featureCollection);

		assertThat(xPlanToEdit.isHasBereich(), is(true));

		RasterBasis rasterBasis = xPlanToEdit.getRasterBasis();
		List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();

		assertThat(rasterBasis.getFeatureId(), is("FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d6"));
		assertThat(rasterReferences.size(), is(1));
		assertThat(rasterReferences.get(0).getReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tif"));
		assertThat(rasterReferences.get(0).getGeoReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tfw"));

		assertThat(xPlanToEdit.getTexts().size(), is(2));
		assertThat(xPlanToEdit.getReferences().size(), is(4));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan50_BaseData_Changes() throws Exception {
		FeatureCollection featureCollection = readXPlanGml(XPLAN_50, "xplan50/BP2070.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_50), featureCollection);

		BaseData baseData = xPlanToEdit.getBaseData();
		assertThat(baseData.getPlanName(), is("BP2070"));
		assertThat(baseData.getDescription(), is("Testdatensatz XPlabGML"));

		assertThat(baseData.getLegislationStatusCode(), is(-1));
		assertThat(baseData.getPlanTypeCode(), is(1000));
		assertThat(baseData.getMethodCode(), is(-1));
		assertThat(baseData.getOtherPlanTypeCode(), is(-1));

		assertThat(baseData.getCreationDate(), is(asDate("2001-08-06")));
		assertThat(baseData.getLossDate(), nullValue());
		assertThat(baseData.getRegulationDate(), nullValue());

		List<Change> changes = xPlanToEdit.getChanges();
		assertThat(changes.size(), is(0));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan41_BaseData_Changes() throws Exception {
		FeatureCollection featureCollection = readXPlanGml(XPLAN_41, "xplan41/Eidelstedt_4_V4-Blankenese.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_41), featureCollection);

		BaseData baseData = xPlanToEdit.getBaseData();
		assertThat(baseData.getPlanName(), is("Eidelstedt 4"));
		assertThat(baseData.getDescription(), is("Beschreibung von Eidelstedt 4"));

		assertThat(baseData.getLegislationStatusCode(), is(4000));
		assertThat(baseData.getPlanTypeCode(), is(1000));
		assertThat(baseData.getMethodCode(), is(2000));
		assertThat(baseData.getOtherPlanTypeCode(), is(-1));

		assertThat(baseData.getCreationDate(), is(asDate("2010-10-07")));
		assertThat(baseData.getLossDate(), is(asDate("2020-10-07")));
		assertThat(baseData.getRegulationDate(), is(asDate("1970-01-01")));

		List<Change> changes = xPlanToEdit.getChanges();
		assertThat(changes.size(), is(2));

		Change firstChange = changes.get(0);
		assertThat(firstChange.getPlanName(), is("Eidelstedt 3 (alt)"));
		assertThat(firstChange.getLegalNatureCode(), is(1100));
		assertThat(firstChange.getNumber(), is("3"));
		assertThat(firstChange.getType(), is(CHANGES));

		Change secondChange = changes.get(1);
		assertThat(secondChange.getPlanName(), is("Eidelstedt 4 (textliche Änderung)"));
		assertThat(secondChange.getLegalNatureCode(), is(1000));
		assertThat(secondChange.getNumber(), is(nullValue()));
		assertThat(secondChange.getType(), is(CHANGED_BY));
	}

	@Test
	@Parameters({ "xplan41/V4_1_ID_103.gml, XPLAN_41", "xplan50/V4_1_ID_103.gml, XPLAN_50" })
	public void testCreateXPlanToEdit_References_Texts(String planResource, String xplanVersion) throws Exception {
		XPlanVersion version = XPlanVersion.valueOf(xplanVersion);
		FeatureCollection featureCollection = readXPlanGml(version, planResource);

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(version), featureCollection);

		BaseData baseData = xPlanToEdit.getBaseData();
		assertThat(baseData.getPlanName(), is("\"Heideweg\""));
		assertThat(baseData.getLegislationStatusCode(), is(4000));
		assertThat(baseData.getPlanTypeCode(), is(1000));
		assertThat(baseData.getCreationDate(), is(asDate("2001-08-27")));

		List<Change> changes = xPlanToEdit.getChanges();
		assertThat(changes.size(), is(0));

		List<Reference> references = xPlanToEdit.getReferences();
		assertThat(references.size(), is(3));

		Reference firstReference = references.get(0);
		assertThat(firstReference.getGeoReference(), is(nullValue()));
		assertThat(firstReference.getReference(), is("B-Plan_Klingmuehl_Heideweg_Leg.pdf"));
		assertThat(firstReference.getReferenzName(), is("B-Plan_Klingmuehl_Heideweg_Leg"));
		assertThat(firstReference.getType(), is(BEGRUENDUNG));

		Reference secondReference = references.get(1);
		assertThat(secondReference.getGeoReference(), is(nullValue()));
		assertThat(secondReference.getReference(), is("B-Plan_Klingmuehl_Heideweg.tif"));
		assertThat(secondReference.getReferenzName(), is("B-Plan_Klingmuehl_Heideweg"));
		assertThat(secondReference.getType(), is(RECHTSPLAN));

		Reference thirdReference = references.get(2);
		assertThat(thirdReference.getGeoReference(), is("B-Plan_Klingmuehl_Heideweg_Gruen.pgw"));
		assertThat(thirdReference.getReference(), is("B-Plan_Klingmuehl_Heideweg_Gruen.png"));
		assertThat(thirdReference.getReferenzName(), is("B-Plan_Klingmuehl_Heideweg_Gruen"));
		assertThat(thirdReference.getType(), is(GRUENORDNUNGSPLAN));

		List<Text> texts = xPlanToEdit.getTexts();
		assertThat(texts.size(), is(1));

		Text text = texts.get(0);
		assertThat(text.getFeatureId(), is("FEATURE_0f870967-bd6f-4367-9150-8a255f0290ad"));
		assertThat(text.getKey(), is("key"));
		assertThat(text.getBasis(), is("base"));
		assertThat(text.getText(), is("Beschreibungstext"));
		assertThat(text.getGeoReference(), is(nullValue()));
		assertThat(text.getReference(), is("B-Plan_Klingmuehl_Heideweg_Text.pdf"));

		RasterBasis rasterBasis = xPlanToEdit.getRasterBasis();
		assertThat(rasterBasis.getFeatureId(), is("FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d6"));

		List<RasterReference> rasterBasisReferences = rasterBasis.getRasterReferences();
		assertThat(rasterBasisReferences.size(), is(2));

		RasterReference scan = getByType(rasterBasisReferences, SCAN);
		assertThat(scan, is(notNullValue()));
		assertThat(scan.getFeatureId(), nullValue());
		assertThat(scan.getGeoReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tfw"));
		assertThat(scan.getReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tif"));
		assertThat(scan.getReferenzName(), is("B-Plan_Klingmuehl_Heideweg_Karte"));
		assertThat(scan.getArt(), is(PLANMITGEOREFERENZ));

		RasterReference legend = getByType(rasterBasisReferences, LEGEND);
		assertThat(legend, is(notNullValue()));
		assertThat(scan.getFeatureId(), nullValue());
		assertThat(legend.getReference(), is("B-Plan_Klingmuehl_Heideweg_Legende.png"));
		assertThat(legend.getReferenzMimeType(), is(IMAGE_PNG));
		assertThat(legend.getGeoReference(), is(nullValue()));
		assertThat(legend.getGeorefMimeType(), is(nullValue()));
		assertThat(legend.getInformationssystemURL(), is("informationssystemURL"));
		assertThat(legend.getReferenzName(), is("B-Plan_Klingmuehl_Heideweg_Legende"));
		assertThat(legend.getBeschreibung(), is("beschreibung"));
		assertThat(legend.getDatum(), is(asDate("2018-03-01")));
		assertThat(legend.getArt(), is(DOKUMENT));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan53_withoutBereich() throws Exception {
		FeatureCollection featureCollection = readXPlanGml(XPLAN_53, "xplan53/BPlan_ohneBereich.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_53), featureCollection);

		assertThat(xPlanToEdit.isHasBereich(), is(false));
	}

	private RasterReference getByType(List<RasterReference> rasterBasisReferences, RasterReferenceType type) {
		for (RasterReference rasterReference : rasterBasisReferences) {
			if (type.equals(rasterReference.getType()))
				return rasterReference;
		}
		return null;
	}

	private FeatureCollection readXPlanGml(XPlanVersion xplanVersion, String plan) throws Exception {
		InputStream xplanGml = this.getClass().getResourceAsStream(plan);
		XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(xplanGml);
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(reader, null);

		GeometryFactory geomFac = new GeometryFactory();
		GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader(xplanVersion.getGmlVersion(), xmlStream);
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(xplanVersion);
		gmlStream.setApplicationSchema(schema);
		gmlStream.setGeometryFactory(geomFac);
		return (FeatureCollection) gmlStream.readFeature();
	}

	private Date asDate(String string) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.parse(string);
	}

	private XPlan mockXPlan(XPlanVersion version) {
		XPlan mock = mock(XPlan.class);
		when(mock.getVersion()).thenReturn(version.toString());
		return mock;
	}

	private XPlan mockXPlan(XPlanVersion version, Date startDateTime, Date endDateTime) {
		AdditionalPlanData xplanMetadata = new AdditionalPlanData(startDateTime, endDateTime);
		XPlan mock = mock(XPlan.class);
		when(mock.getVersion()).thenReturn(version.toString());
		when(mock.getXplanMetadata()).thenReturn(xplanMetadata);
		return mock;
	}

}
