/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
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
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.FP_Plan;
import static de.latlon.xplan.commons.XPlanType.LP_Plan;
import static de.latlon.xplan.commons.XPlanType.RP_Plan;
import static de.latlon.xplan.commons.XPlanType.SO_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.DOKUMENT;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.PLANMITGEOREFERENZ;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.IMAGE_PNG;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEGRUENDUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.INFORMELL;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.RECHTSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.VERORDNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.SO_SONSTIGES;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_FESTSETZUNGBPLAN;
import static org.hamcrest.CoreMatchers.containsString;
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
	public void testCreateXPlanToEdit_XPlan52_multipleBereiche() throws Exception {
		FeatureCollection featureCollection = readXPlanArchive(XPLAN_52, "xplan52/BPlan001_5-2_Bereiche.zip");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_52, BP_Plan), featureCollection);

		assertThat(xPlanToEdit.isHasBereich(), is(true));

		List<RasterBasis> rasterBasis = xPlanToEdit.getRasterBasis();
		assertThat(rasterBasis.size(), is(2));

		// Bereich 0
		String bereichNummer0 = "0";
		RasterBasis rasterBasis0 = getByBereichNummer(rasterBasis, bereichNummer0);
		List<RasterReference> rasterReferences0 = rasterBasis0.getRasterReferences();

		assertThat(rasterBasis0.getFeatureId(), is(nullValue()));
		assertThat(rasterReferences0.size(), is(1));
		assertThat(rasterReferences0.get(0).getBereichNummer(), is(bereichNummer0));
		assertThat(rasterReferences0.get(0).getReference(), is("BPlan001_5-2_Bereich0.png"));

		// Bereich 1
		String bereichNummer1 = "1";
		RasterBasis rasterBasis1 = getByBereichNummer(rasterBasis, bereichNummer1);
		List<RasterReference> rasterReferences1 = rasterBasis1.getRasterReferences();

		assertThat(rasterBasis1.getFeatureId(), is(nullValue()));
		assertThat(rasterReferences1.size(), is(1));
		assertThat(rasterReferences1.get(0).getBereichNummer(), is(bereichNummer1));
		assertThat(rasterReferences1.get(0).getReference(), is("BPlan001_5-2_Bereich1.png"));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan51_refScan() throws Exception {
		FeatureCollection featureCollection = readXPlanGml(XPLAN_51, "xplan51/V4_1_ID_103_refScan.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_51, BP_Plan), featureCollection);

		assertThat(xPlanToEdit.isHasBereich(), is(true));

		String bereichNummer = "0";
		List<RasterBasis> allRasterBasis = xPlanToEdit.getRasterBasis();
		assertThat(allRasterBasis.size(), is(1));

		RasterBasis rasterBasis = allRasterBasis.get(0);
		assertThat(rasterBasis.getFeatureId(), is(nullValue()));
		assertThat(rasterBasis.getBereichNummer(), is(bereichNummer));

		List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();
		assertThat(rasterReferences.size(), is(1));
		assertThat(rasterReferences.get(0).getBereichNummer(), is(bereichNummer));
		assertThat(rasterReferences.get(0).getReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tif"));
		assertThat(rasterReferences.get(0).getGeoReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tfw"));

	}

	@Test
	public void testCreateXPlanToEdit_XPlan51_rasterdarstellung() throws Exception {
		FeatureCollection featureCollection = readXPlanGml(XPLAN_51, "xplan51/V4_1_ID_103.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_51, BP_Plan), featureCollection);

		assertThat(xPlanToEdit.isHasBereich(), is(true));

		String bereichNummer = "0";
		List<RasterBasis> allRasterBasis = xPlanToEdit.getRasterBasis();
		assertThat(allRasterBasis.size(), is(1));

		RasterBasis rasterBasis = allRasterBasis.get(0);
		assertThat(rasterBasis.getFeatureId(), is("FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d6"));
		assertThat(rasterBasis.getBereichNummer(), is(bereichNummer));

		List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();
		assertThat(rasterReferences.size(), is(1));
		assertThat(rasterReferences.get(0).getBereichNummer(), is(bereichNummer));
		assertThat(rasterReferences.get(0).getReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tif"));
		assertThat(rasterReferences.get(0).getGeoReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tfw"));

		assertThat(xPlanToEdit.getTexts().size(), is(2));
		assertThat(xPlanToEdit.getReferences().size(), is(4));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan50_BaseData_Changes() throws Exception {
		FeatureCollection featureCollection = readXPlanGml(XPLAN_50, "xplan50/BP2070.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_50, BP_Plan), featureCollection);

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

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_41, BP_Plan), featureCollection);

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
	public void testCreateXPlanToEdit_References_Texts_V4_1_ID_103_41() throws Exception {
		XPlanVersion version = XPLAN_41;
		FeatureCollection featureCollection = readXPlanGml(version, "xplan41/V4_1_ID_103.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(version, BP_Plan), featureCollection);

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

		String bereichNummer = "0";
		List<RasterBasis> allRasterBasis = xPlanToEdit.getRasterBasis();
		assertThat(allRasterBasis.size(), is(1));

		RasterBasis rasterBasis = allRasterBasis.get(0);
		assertThat(rasterBasis.getBereichNummer(), is(bereichNummer));
		assertThat(rasterBasis.getFeatureId(), is("FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d6"));

		List<RasterReference> rasterBasisReferences = rasterBasis.getRasterReferences();
		assertThat(rasterBasisReferences.size(), is(2));

		RasterReference scan = getByType(rasterBasisReferences, SCAN);
		assertThat(scan, is(notNullValue()));
		assertThat(scan.getFeatureId(), nullValue());
		assertThat(scan.getBereichNummer(), is(bereichNummer));
		assertThat(scan.getGeoReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tfw"));
		assertThat(scan.getReference(), is("B-Plan_Klingmuehl_Heideweg_Karte.tif"));
		assertThat(scan.getReferenzName(), is("B-Plan_Klingmuehl_Heideweg_Karte"));
		assertThat(scan.getArt(), is(PLANMITGEOREFERENZ));

		RasterReference legend = getByType(rasterBasisReferences, LEGEND);
		assertThat(legend, is(notNullValue()));
		assertThat(legend.getFeatureId(), nullValue());
		assertThat(legend.getBereichNummer(), is(bereichNummer));
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
	public void testCreateXPlanToEdit_References_Texts_BPlan004_50() throws Exception {
		XPlanVersion version = XPLAN_50;
		FeatureCollection featureCollection = readXPlanArchive(version, "xplan50/BPlan004_5-0.zip");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(version, BP_Plan), featureCollection);

		BaseData baseData = xPlanToEdit.getBaseData();
		assertThat(baseData.getPlanName(), is("BPlan004_5-0"));
		assertThat(baseData.getLegislationStatusCode(), is(3000));
		assertThat(baseData.getPlanTypeCode(), is(3000));
		assertThat(baseData.getCreationDate(), is(asDate("2017-03-20")));

		List<Change> changes = xPlanToEdit.getChanges();
		assertThat(changes.size(), is(0));

		List<Reference> references = xPlanToEdit.getReferences();
		assertThat(references.size(), is(1));

		Reference firstReference = references.get(0);
		assertThat(firstReference.getGeoReference(), is(nullValue()));
		assertThat(firstReference.getReference(), is("BPlan004_5-0.pdf"));
		assertThat(firstReference.getReferenzName(), is(nullValue()));
		assertThat(firstReference.getType(), is(INFORMELL));

		List<Text> texts = xPlanToEdit.getTexts();
		assertThat(texts.size(), is(9));

		String featureIdOfText = "GML_c27ab7dd-8e16-4f88-abae-6b23d49e7a90";
		Text text = texts.stream().filter(t -> featureIdOfText.equals(t.getFeatureId())).findFirst().get();
		assertThat(text.getFeatureId(), is(featureIdOfText));
		assertThat(text.getKey(), is("§2 Nr.4"));
		assertThat(text.getBasis(), is(nullValue()));
		assertThat(text.getText(), is(
				"Im allgemeinen Wohngebiet darf die festgesetzte Grundflächenzahl\nfür Tiefgaragen bis zu einer Grundflächenzahl\nvon 1,0 überschritten werden."));

		String bereichNummer = "0";
		List<RasterBasis> allRasterBasis = xPlanToEdit.getRasterBasis();
		assertThat(allRasterBasis.size(), is(1));

		RasterBasis rasterBasis = allRasterBasis.get(0);
		assertThat(rasterBasis.getBereichNummer(), is(bereichNummer));
		assertThat(rasterBasis.getFeatureId(), is("Gml_FEC4F42F-5D66-4A59-9A47-6E03D1A3139A"));

		List<RasterReference> rasterBasisReferences = rasterBasis.getRasterReferences();
		assertThat(rasterBasisReferences.size(), is(1));

		RasterReference scan = getByType(rasterBasisReferences, SCAN);
		assertThat(scan, is(notNullValue()));
		assertThat(scan.getFeatureId(), nullValue());
		assertThat(scan.getBereichNummer(), is(bereichNummer));
		assertThat(scan.getGeoReference(), is("BPlan004_5-0.pgw"));
		assertThat(scan.getReference(), is("BPlan004_5-0.png"));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan53_withoutBereich() throws Exception {
		FeatureCollection featureCollection = readXPlanGml(XPLAN_53, "xplan53/BPlan_ohneBereich.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_53, BP_Plan), featureCollection);

		assertThat(xPlanToEdit.isHasBereich(), is(false));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan51_FPlan() throws Exception {
		FeatureCollection featureCollection = readXPlanArchive(XPLAN_51, "xplan51/FPlan.zip");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_51, FP_Plan), featureCollection);
		assertThat(xPlanToEdit.isHasBereich(), is(true));

		BaseData baseData = xPlanToEdit.getBaseData();
		assertThat(baseData.getPlanName(), is("FPlan Bad Liebenwerda"));
		assertThat(baseData.getPlanTypeCode(), is(9999));
		assertThat(baseData.getCreationDate(), is(asDate("2004-12-01")));

		assertThat(xPlanToEdit.getRasterBasis().size(), is(1));
		assertThat(xPlanToEdit.getRasterBasis().get(0).getRasterReferences().size(), is(0));
		assertThat(xPlanToEdit.getTexts().size(), is(0));
		assertThat(xPlanToEdit.getReferences().size(), is(0));
		assertThat(xPlanToEdit.getChanges().size(), is(0));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan60_LPlan() throws Exception {
		FeatureCollection featureCollection = readXPlanArchive(XPLAN_60, "xplan60/LP-Test_60.zip");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_60, LP_Plan), featureCollection);
		assertThat(xPlanToEdit.isHasBereich(), is(true));

		BaseData baseData = xPlanToEdit.getBaseData();
		assertThat(baseData.getPlanName(), is("LP-Test 60"));
		assertThat(baseData.getPlanTypeCode(), is(3000));

		assertThat(xPlanToEdit.getRasterBasis().size(), is(1));
		assertThat(xPlanToEdit.getRasterBasis().get(0).getRasterReferences().size(), is(0));

		List<Text> texts = xPlanToEdit.getTexts();
		assertThat(texts.size(), is(1));

		Text text = texts.get(0);
		assertThat(text.getFeatureId(), is("Gml_1234"));
		assertThat(text.getText(), is("Test"));
		assertThat(text.getRechtscharakter(), is(XP_FESTSETZUNGBPLAN));

		assertThat(xPlanToEdit.getReferences().size(), is(0));
		assertThat(xPlanToEdit.getChanges().size(), is(0));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan51_SOPlan() throws Exception {
		FeatureCollection featureCollection = readXPlanArchive(XPLAN_51, "xplan51/StErhVO_Heidberg_51.zip");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(XPLAN_51, SO_Plan, featureCollection);
		assertThat(xPlanToEdit.isHasBereich(), is(true));

		BaseData baseData = xPlanToEdit.getBaseData();
		assertThat(baseData.getPlanName(), is("StErhVO_Heidberg"));
		assertThat(baseData.getDescription(), is("siehe Lageplan"));
		assertThat(baseData.getPlanTypeCode(), is(17200));
		assertThat(baseData.getCreationDate(), is(asDate("2019-06-07")));

		assertThat(xPlanToEdit.getRasterBasis().size(), is(1));
		assertThat(xPlanToEdit.getRasterBasis().get(0).getRasterReferences().size(), is(0));

		List<Text> texts = xPlanToEdit.getTexts();
		assertThat(texts.size(), is(3));

		Text firstText = texts.get(0);
		assertThat(firstText.getFeatureId(), is("Gml_2413F6CD-7CCF-4F69-B2A9-9C34D6FB5EFA"));
		assertThat(firstText.getKey(), is("1"));
		assertThat(firstText.getText(), containsString(
				"Diese Verordnung gilt für die in der anliegenden Karte durch eine schwarze Linie abgegrenzte"));
		assertThat(firstText.getGeoReference(), is(nullValue()));
		assertThat(firstText.getRechtscharakter(), is(SO_SONSTIGES));

		Text secondText = texts.get(1);
		assertThat(secondText.getFeatureId(), is("Gml_A903B546-00F6-47CD-8627-E50D9B0DD250"));
		assertThat(secondText.getKey(), is("2"));
		assertThat(secondText.getText(), containsString(
				"die Errichtung baulicher Anlagen der Genehmigung, und zwar auch dann, wenn nach den bauordnungsrechtlichen Vorschriften"));
		assertThat(secondText.getGeoReference(), is(nullValue()));
		assertThat(secondText.getRechtscharakter(), is(SO_SONSTIGES));

		Text thirdText = texts.get(2);
		assertThat(thirdText.getFeatureId(), is("Gml_C5992598-EF43-4EA2-AA00-2D431081A4F7"));
		assertThat(thirdText.getKey(), is("3"));
		assertThat(thirdText.getText(), containsString("Es wird auf Folgendes hingewiesen:"));
		assertThat(thirdText.getGeoReference(), is(nullValue()));
		assertThat(thirdText.getRechtscharakter(), is(SO_SONSTIGES));

		List<Reference> references = xPlanToEdit.getReferences();
		assertThat(references.size(), is(1));

		Reference reference = references.get(0);
		assertThat(reference.getGeoReference(), is(nullValue()));
		assertThat(reference.getReference(), is("StErhVO_Heidberg.pdf"));
		assertThat(reference.getReferenzName(), is(nullValue()));
		assertThat(reference.getType(), is(VERORDNUNG));

		assertThat(xPlanToEdit.getChanges().size(), is(0));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan51_RPlan() throws Exception {
		FeatureCollection featureCollection = readXPlanArchive(XPLAN_51, "xplan51/RROP_Landkreis_Test_51.zip");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_51, RP_Plan), featureCollection);
		assertThat(xPlanToEdit.isHasBereich(), is(true));

		BaseData baseData = xPlanToEdit.getBaseData();
		assertThat(baseData.getPlanName(), is("Regionales Raumordnungsprogramm Landkreis Test 2019"));
		assertThat(baseData.getDescription(), is(nullValue()));
		assertThat(baseData.getPlanTypeCode(), is(1000));
		assertThat(baseData.getCreationDate(), is(nullValue()));
		assertThat(baseData.getMethodCode(), is(3000));

		assertThat(xPlanToEdit.getRasterBasis().size(), is(1));
		assertThat(xPlanToEdit.getRasterBasis().get(0).getRasterReferences().size(), is(0));
		assertThat(xPlanToEdit.getTexts().size(), is(0));
		assertThat(xPlanToEdit.getReferences().size(), is(0));
		assertThat(xPlanToEdit.getChanges().size(), is(0));
	}

	@Test
	public void testCreateXPlanToEdit_XPlan54_incompleteRefScan() throws Exception {
		FeatureCollection featureCollection = readXPlanGml(XPLAN_54, "xplan54/BPlan002_5-4.gml");

		XPlanToEdit xPlanToEdit = factory.createXPlanToEdit(mockXPlan(XPLAN_54, BP_Plan), featureCollection);
		assertThat(xPlanToEdit.isHasBereich(), is(true));

		List<RasterBasis> rasterBasis = xPlanToEdit.getRasterBasis();
		assertThat(rasterBasis.size(), is(1));
		assertThat(rasterBasis.get(0).getRasterReferences().size(), is(0));
	}

	private RasterReference getByType(List<RasterReference> rasterBasisReferences, RasterReferenceType type) {
		for (RasterReference rasterReference : rasterBasisReferences) {
			if (type.equals(rasterReference.getType()))
				return rasterReference;
		}
		return null;
	}

	private RasterBasis getByBereichNummer(List<RasterBasis> rasterBasis, String bereichNummer) {
		Optional<RasterBasis> rasterBasisWithNumber = rasterBasis.stream()
			.filter(rb -> rb.getBereichNummer().equals(bereichNummer))
			.findFirst();
		return rasterBasisWithNumber.isPresent() ? rasterBasisWithNumber.get() : null;
	}

	private FeatureCollection readXPlanGml(XPlanVersion xplanVersion, String plan) throws Exception {
		InputStream xplanGml = this.getClass().getResourceAsStream(plan);
		return XPlanGmlParserBuilder.newBuilder().build().parseFeatureCollection(xplanGml, xplanVersion);
	}

	private FeatureCollection readXPlanArchive(XPlanVersion xplanVersion, String resource)
			throws IOException, XMLStreamException, UnknownCRSException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		InputStream inputStream = ResourceAccessor.readResourceStream(resource);
		XPlanArchive xPlanArchiveFromZip = archiveCreator.createXPlanArchiveFromZip(resource, inputStream);
		InputStream mainFileInputStream = xPlanArchiveFromZip.getMainFileInputStream();
		return XPlanGmlParserBuilder.newBuilder().build().parseFeatureCollection(mainFileInputStream, xplanVersion);
	}

	private Date asDate(String string) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.parse(string);
	}

	private XPlan mockXPlan(XPlanVersion version, XPlanType xPlanType) {
		XPlan mock = mock(XPlan.class);
		when(mock.getVersion()).thenReturn(version.toString());
		when(mock.getType()).thenReturn(xPlanType.name());
		return mock;
	}

}
