/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.Text;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xmlunit.builder.Input;
import org.xmlunit.matchers.HasXPathMatcher;
import org.xmlunit.matchers.ValidationMatcher;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.PLANMITGEOREFERENZ;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.IMAGE_PNG;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.TEXT_HTML;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEGRUENDUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.RECHTSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.HINWEIS;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.VERMERK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.xmlunit.matchers.EvaluateXPathMatcher.hasXPath;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
@RunWith(JUnitParamsRunner.class)
public class XPlanManipulatorTest {

	private final XPlanToEditFactory factory = new XPlanToEditFactory();

	private final XPlanManipulator planManipulator = new XPlanManipulator();

	@Test
	@Parameters({ "xplan51/BP2070.gml, XPLAN_51", "xplan50/BP2070.gml, XPLAN_50",
			"xplan41/Eidelstedt_4_V4-Blankenese.gml, XPLAN_41" })
	public void testModifyXPlan(String planResource, String xplanVersion) throws Exception {
		XPlanVersion version = XPlanVersion.valueOf(xplanVersion);
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, planResource, schema);

		String planName = "newPlanName";
		String description = "newDescription";
		Date creationDate = asDate("2010-01-01");
		Date lossDate = asDate("2020-01-01");
		Date regulationDate = asDate("2006-01-01");
		int legislationStatusCode = 3000;
		int methodCode = 1000;
		int planTypeCode = 10000;
		XPlanToEdit editedXplan = createEditedXplan(planName, description, creationDate, lossDate, regulationDate,
				legislationStatusCode, methodCode, -1, planTypeCode);

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		assertThat(featureCollection, hasProperty(version, "BP_Plan", "name", planName));
		assertThat(featureCollection, hasProperty(version, "BP_Plan", "beschreibung", description));
		assertThat(featureCollection, hasProperty(version, "BP_Plan", "technHerstellDatum", creationDate));
		assertThat(featureCollection, hasProperty(version, "BP_Plan", "untergangsDatum", lossDate));
		assertThat(featureCollection, hasProperty(version, "BP_Plan", "rechtsverordnungsDatum", regulationDate));
		assertThat(featureCollection, hasProperty(version, "BP_Plan", "rechtsverordnungsDatum", regulationDate));
		assertThat(featureCollection, hasProperty(version, "BP_Plan", "rechtsstand", legislationStatusCode));
		assertThat(featureCollection, hasProperty(version, "BP_Plan", "verfahren", methodCode));
		assertThat(featureCollection, hasNoProperty(version, "BP_Plan", "sonstPlanArt"));
		assertThat(featureCollection, hasProperty(version, "BP_Plan", "planArt", planTypeCode));

		assertThatPlanIsSchemaValid(featureCollection, version);
	}

	@Test
	@Parameters({ "xplan51/BP2070.gml, XPLAN_51", "xplan50/BP2070.gml, XPLAN_50",
			"xplan41/Eidelstedt_4_V4-Blankenese.gml, XPLAN_41" })
	public void testModifyXPlan_Aenderungen(String planResource, String xplanVersion) throws Exception {
		XPlanVersion version = XPlanVersion.valueOf(xplanVersion);
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, planResource, schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		editedXplan.getChanges().add(new Change("planName1", 1000, "eins", CHANGED_BY));
		editedXplan.getChanges().add(new Change("planName2", 1100, "zwei", CHANGES));
		editedXplan.getChanges().add(new Change("planName3", 2000, "drei", CHANGES));

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "wurdeGeaendertVon", 1));
		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "aendert", 2));

		assertThatPlanIsSchemaValid(featureCollection, version);
	}

	@Test
	@Parameters({ "xplan51/BP2070.gml, XPLAN_51", "xplan50/BP2070.gml, XPLAN_50" })
	public void testModifyXPlan_XPlan5X_Texte(String planResource, String xplanVersion) throws Exception {
		XPlanVersion version = XPlanVersion.valueOf(xplanVersion);
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, planResource, schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		editedXplan.getTexts().add(new Text("id1", "key1", "basis1", "text1", HINWEIS, "reference1", "geoReference1"));
		editedXplan.getTexts().add(new Text("id2", "key2", "basis2", "text2", VERMERK, "reference2", "geoReference2"));

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "texte", 2));
		assertThat(featureCollection, hasFeatureCount(version, "BP_TextAbschnitt", 2));

		assertThatPlanIsSchemaValid(featureCollection, version);
	}

	@Test
	public void testModifyXPlan_XPlan51_ModifyTextKeepFeatureId() throws Exception {
		XPlanVersion version = XPLAN_51;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, "xplan51/V4_1_ID_103.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		String featureIdUnderTest = "FEATURE_0f870967-bd6f-4367-9150-8a255f0290ad";
		editedXplan.getTexts().add(new Text(featureIdUnderTest, "key", "base", "BeschreibungstextNeu", HINWEIS,
				"B-Plan_Klingmuehl_Heideweg_Text", "B-Plan_Klingmuehl_Heideweg_Text.pdf"));

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "texte", 1));
		assertThat(featureCollection, hasFeatureWithId(version, "BP_TextAbschnitt", featureIdUnderTest));

		assertThatPlanIsSchemaValid(featureCollection, version);
	}

	@Test
	public void testModifyXPlan_XPlan41_Texte() throws Exception {
		XPlanVersion version = XPLAN_41;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, "xplan41/V4_1_ID_103.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		editedXplan.getTexts().add(new Text("id1", "key1", "basis1", "text1", "reference1", "geoReference1"));
		editedXplan.getTexts().add(new Text("id2", "key2", "basis2", "text2", "reference2", "geoReference2"));

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "texte", 2));
		assertThat(featureCollection, hasFeatureCount(version, "XP_TextAbschnitt", 2));

		assertThatPlanIsSchemaValid(featureCollection, version);
	}

	@Test
	@Parameters({ "xplan51/BP2070.gml, XPLAN_51", "xplan50/BP2070.gml, XPLAN_50" })
	public void testModifyXPlan_XPlan50_TextWerte(String planResource, String xplanVersion) throws Exception {
		XPlanVersion version = XPlanVersion.valueOf(xplanVersion);
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, planResource, schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		Text text = new Text("id1", "key1", "basis1", "text1", VERMERK, "reference1", "geoReference1");
		editedXplan.getTexts().add(text);

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "texte", 1));
		assertThat(featureCollection, hasFeatureCount(version, "BP_TextAbschnitt", 1));
		assertThat(featureCollection, hasProperty(version, "BP_TextAbschnitt", "schluessel", text.getKey()));
		assertThat(featureCollection,
				hasProperty(version, "BP_TextAbschnitt", "gesetzlicheGrundlage", text.getBasis()));
		assertThat(featureCollection, hasProperty(version, "BP_TextAbschnitt", "text", text.getText()));
		assertThat(featureCollection,
				hasProperty(version, "BP_TextAbschnitt", "rechtscharakter", text.getRechtscharakter().getCode()));
		assertThat(featureCollection, hasPropertyCount(version, "BP_TextAbschnitt", "refText", 1));

		assertThatPlanIsSchemaValid(featureCollection, version);
	}

	@Test
	public void testModifyXPlan_XPlan41_TextWerte() throws Exception {
		XPlanVersion version = XPLAN_41;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, "xplan41/V4_1_ID_103.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		Text text = new Text("id1", "key1", "basis1", "text1", "reference1", "geoReference1");
		editedXplan.getTexts().add(text);

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "texte", 1));
		assertThat(featureCollection, hasFeatureCount(version, "XP_TextAbschnitt", 1));
		assertThat(featureCollection, hasProperty(version, "XP_TextAbschnitt", "schluessel", text.getKey()));
		assertThat(featureCollection,
				hasProperty(version, "XP_TextAbschnitt", "gesetzlicheGrundlage", text.getBasis()));
		assertThat(featureCollection, hasProperty(version, "XP_TextAbschnitt", "text", text.getText()));
		assertThat(featureCollection, hasPropertyCount(version, "XP_TextAbschnitt", "refText", 1));

		assertThatPlanIsSchemaValid(featureCollection, version);
	}

	@Test
	@Parameters({ "xplan51/BP2070.gml, XPLAN_51", "xplan50/BP2070.gml, XPLAN_50" })
	public void testModifyXPlan_XPlan50_Referenzen(String planResource, String xplanVersion) throws Exception {
		XPlanVersion version = XPlanVersion.valueOf(xplanVersion);
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, planResource, schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		editedXplan.getReferences().add(new Reference("ref1", "georef1", GRUENORDNUNGSPLAN));
		editedXplan.getReferences().add(new Reference("ref2", "georef2", RECHTSPLAN));
		editedXplan.getReferences().add(new Reference("ref3", "georef3", BEGRUENDUNG));

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "externeReferenz", 3));

		assertThatPlanIsSchemaValid(featureCollection, version);
	}

	@Test
	public void testModifyXPlan_XPlan41_Referenzen() throws Exception {
		XPlanVersion version = XPLAN_41;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, "xplan41/Eidelstedt_4_V4-Blankenese.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		Reference reference1 = new Reference("ref1", "georef1", GRUENORDNUNGSPLAN);
		editedXplan.getReferences().add(reference1);
		editedXplan.getReferences().add(new Reference("ref2", "georef2", RECHTSPLAN));
		editedXplan.getReferences().add(new Reference("ref3", "georef3", BEGRUENDUNG));

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "refBegruendung", 1));
		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "refRechtsplan", 1));
		assertThat(featureCollection, hasPropertyCount(version, "BP_Plan", "refGruenordnungsplan", 1));

		assertThatPlanIsSchemaValid(featureCollection, version);
	}

	@Test
	public void testModifyXPlan_RasterReferences() throws Exception {
		XPlanVersion version = XPLAN_50;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, "xplan50/V4_1_ID_103.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		RasterReference rasterBasisReference = new RasterReference("ref1", "georef1", SCAN, IMAGE_PNG,
				PLANMITGEOREFERENZ, "informationssystemeURL", "refName", TEXT_HTML, "beschreibung",
				asDate("2018-03-01"));

		RasterBasis rasterBasis = new RasterBasis("FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d6");
		rasterBasis.addRasterReference(rasterBasisReference);
		editedXplan.setRasterBasis(rasterBasis);

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		String exportedPlan = exportPlan(featureCollection, version);

		assertThat(exportedPlan,
				hasXPath("count(//xp:BP_Bereich/xp:rasterBasis)", is("1")).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan,
				hasXPath("count(//xp:XP_Rasterdarstellung)", is("1")).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan, hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:georefURL",
				is(rasterBasisReference.getGeoReference())).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan,
				hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:georefMimeType",
						is(rasterBasisReference.getGeorefMimeType().getCode()))
								.withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan, hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:art",
				is(rasterBasisReference.getArt().getCode())).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan,
				hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:informationssystemURL",
						is(rasterBasisReference.getInformationssystemURL())).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan, hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:referenzName",
				is(rasterBasisReference.getReferenzName())).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan, hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:referenzURL",
				is(rasterBasisReference.getReference())).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan,
				hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:referenzMimeType",
						is(rasterBasisReference.getReferenzMimeType().getCode()))
								.withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan, hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:beschreibung",
				is(rasterBasisReference.getBeschreibung())).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan,
				hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:datum", is("2018-03-01"))
						.withNamespaceContext(nsContext(version)));

		assertThatPlanIsSchemaValid(exportedPlan, version);
	}

	@Test
	public void testModifyXPlan_XPlan50_delete_RasterReferences() throws Exception {
		XPlanVersion xPlanVersion = XPLAN_50;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(xPlanVersion, null);
		FeatureCollection featureCollection = readXPlanGml(xPlanVersion, "xplan50/V4_1_ID_103.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		editedXplan.setRasterBasis(null);

		planManipulator.modifyXPlan(featureCollection, editedXplan, xPlanVersion, BP_Plan, schema);

		String exportedPlan = exportPlan(featureCollection, xPlanVersion);

		assertThat(exportedPlan, hasXPath("count(//xp:BP_Bereich/xp:rasterBasis)", is("0"))
				.withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				hasXPath("count(//xp:XP_Rasterdarstellung)", is("0")).withNamespaceContext(nsContext(xPlanVersion)));
		assertThatPlanIsSchemaValid(exportedPlan, xPlanVersion);
	}

	@Test
	public void testModifyXPlan_XPlan50_new_RasterReferences() throws Exception {
		XPlanVersion xPlanVersion = XPLAN_50;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(xPlanVersion, null);
		FeatureCollection featureCollection = readXPlanGml(xPlanVersion, "xplan50/BP2070.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		RasterReference scan = new RasterReference("scanRef", "scanGeoRef", SCAN, null, null, null, null, null, null,
				null);
		RasterReference legend = new RasterReference("legendRef", null, LEGEND, null, PLANMITGEOREFERENZ,
				"informationssystemeURL", "refName", IMAGE_PNG, "beschreibung", asDate("2018-03-01"));

		RasterBasis rasterBasis = new RasterBasis();
		rasterBasis.addRasterReference(scan);
		rasterBasis.addRasterReference(legend);
		editedXplan.setRasterBasis(rasterBasis);

		planManipulator.modifyXPlan(featureCollection, editedXplan, xPlanVersion, BP_Plan, schema);

		String exportedPlan = exportPlan(featureCollection, xPlanVersion);
		assertThatPlanIsSchemaValid(exportedPlan, xPlanVersion);

		assertThat(exportedPlan, hasXPath("count(//xp:BP_Bereich/xp:rasterBasis)", is("1"))
				.withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				hasXPath("count(//xp:XP_Rasterdarstellung)", is("1")).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:referenzURL",
				is(scan.getReference())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath("//xp:XP_Rasterdarstellung/xp:refScan/xp:XP_ExterneReferenz/xp:georefURL",
				is(scan.getGeoReference())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				not(HasXPathMatcher
						.hasXPath("//xp:XP_Rasterdarstellung/xp:refLegende/xp:XP_ExterneReferenz/xp:georefMimeType")
						.withNamespaceContext(nsContext(xPlanVersion))));
		assertThat(exportedPlan, hasXPath("//xp:XP_Rasterdarstellung/xp:refLegende/xp:XP_ExterneReferenz/xp:art",
				is(legend.getArt().getCode())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				hasXPath("//xp:XP_Rasterdarstellung/xp:refLegende/xp:XP_ExterneReferenz/xp:informationssystemURL",
						is(legend.getInformationssystemURL())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				hasXPath("//xp:XP_Rasterdarstellung/xp:refLegende/xp:XP_ExterneReferenz/xp:referenzName",
						is(legend.getReferenzName())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				hasXPath("//xp:XP_Rasterdarstellung/xp:refLegende/xp:XP_ExterneReferenz/xp:referenzURL",
						is(legend.getReference())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				hasXPath("//xp:XP_Rasterdarstellung/xp:refLegende/xp:XP_ExterneReferenz/xp:referenzMimeType",
						is(legend.getReferenzMimeType().getCode())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				hasXPath("//xp:XP_Rasterdarstellung/xp:refLegende/xp:XP_ExterneReferenz/xp:beschreibung",
						is(legend.getBeschreibung())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				hasXPath("//xp:XP_Rasterdarstellung/xp:refLegende/xp:XP_ExterneReferenz/xp:datum", is("2018-03-01"))
						.withNamespaceContext(nsContext(xPlanVersion)));
	}

	@Test
	public void testModifyXPlan_XPlan41_RasterReferences() throws Exception {
		XPlanVersion xPlanVersion = XPLAN_41;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(xPlanVersion, null);
		FeatureCollection featureCollection = readXPlanGml(xPlanVersion, "xplan41/V4_1_ID_103.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		RasterReference rasterBasisReference = new RasterReference("ref1", "georef1", SCAN, null, null, null, null,
				null, null, null);

		RasterBasis rasterBasis = new RasterBasis("FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d6");
		rasterBasis.addRasterReference(rasterBasisReference);
		editedXplan.setRasterBasis(rasterBasis);

		planManipulator.modifyXPlan(featureCollection, editedXplan, xPlanVersion, BP_Plan, schema);

		String exportedPlan = exportPlan(featureCollection, xPlanVersion);

		assertThat(exportedPlan, hasXPath("count(//xp:BP_Bereich/xp:rasterBasis)", is("1"))
				.withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				hasXPath("count(//xp:XP_RasterplanBasis)", is("1")).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath("//xp:XP_RasterplanBasis/xp:refScan/xp:XP_ExterneReferenz/xp:georefURL",
				is(rasterBasisReference.getGeoReference())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath("//xp:XP_RasterplanBasis/xp:refScan/xp:XP_ExterneReferenz/xp:referenzURL",
				is(rasterBasisReference.getReference())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThatPlanIsSchemaValid(exportedPlan, xPlanVersion);
	}

	@Test
	@Parameters({ "xplan50/BP2070.gml, XPLAN_50", "xplan41/Eidelstedt_4_V4-Blankenese.gml, XPLAN_41" })
	public void testModifyXPlan_XPlan41_NullAndEmptyValue(String planResource, String xplanVersion) throws Exception {
		XPlanVersion version = XPlanVersion.valueOf(xplanVersion);
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, planResource, schema);

		XPlanToEdit editedXplan = createEditedXplan("newPlanName", null, asDate("2010-01-01"), null,
				asDate("2006-01-01"), 3000, -1, -1, 40001);

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		assertThat(featureCollection, hasNoProperty(version, "BP_Plan", "beschreibung"));
		assertThat(featureCollection, hasNoProperty(version, "BP_Plan", "untergangsDatum"));
		assertThat(featureCollection, hasNoProperty(version, "BP_Plan", "verfahren"));

		assertThatPlanIsSchemaValid(featureCollection, version);
	}

	// #3288
	@Test
	public void testModifyXPlan_XPlan41_ValidReferences() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_41, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_41, "xplan41/V4_1_ID_103_references.gml", schema);

		XPlanToEdit editedXplan = factory.createXPlanToEdit(mockXPlan(XPLAN_41), featureCollection);
		editedXplan.getBaseData().setDescription("newDescription");

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_41, BP_Plan, schema);

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_41);
	}

	@Test
	public void testModifyXPlan_XPlan41_TextValues_MultipleReferences_Modify() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_41, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_41, "xplan41/V4_1_ID_103_texts.gml", schema);

		XPlanToEdit editedXplan = factory.createXPlanToEdit(mockXPlan(XPLAN_41), featureCollection);
		retrieveText(editedXplan, "FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6b").setText("newText1");
		retrieveText(editedXplan, "FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6c").setText("newText2");

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_41, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(XPLAN_41, "BP_Plan", "texte", 2));
		assertThat(featureCollection,
				hasHrefAttribue(XPLAN_41, "BP_Plan", "texte", "#FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6b"));
		assertThat(featureCollection,
				hasHrefAttribue(XPLAN_41, "BP_Plan", "texte", "#FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6c"));
		assertThat(featureCollection, hasPropertyCount(XPLAN_41, "BP_BaugebietsTeilFlaeche", "refTextInhalt", 1));
		assertThat(featureCollection, hasHrefAttribue(XPLAN_41, "BP_BaugebietsTeilFlaeche", "refTextInhalt",
				"#FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6b"));
		assertThat(featureCollection, hasFeatureCount(XPLAN_41, "XP_TextAbschnitt", 2));
		assertThat(featureCollection, hasProperty(XPLAN_41, "XP_TextAbschnitt", "text", "newText1"));
		assertThat(featureCollection, hasProperty(XPLAN_41, "XP_TextAbschnitt", "text", "newText2"));
		assertThat(featureCollection,
				hasFeatureWithId(XPLAN_41, "XP_TextAbschnitt", "FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6b"));
		assertThat(featureCollection,
				hasFeatureWithId(XPLAN_41, "XP_TextAbschnitt", "FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6c"));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_41);
	}

	@Test
	public void testModifyXPlan_XPlan41_TextValues_MultipleReferences_Remove() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_41, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_41, "xplan41/V4_1_ID_103_texts.gml", schema);

		XPlanToEdit editedXplan = factory.createXPlanToEdit(mockXPlan(XPLAN_41), featureCollection);
		editedXplan.getTexts().remove(retrieveText(editedXplan, "FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6b"));

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_41, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(XPLAN_41, "BP_Plan", "texte", 1));
		assertThat(featureCollection,
				hasHrefAttribue(XPLAN_41, "BP_Plan", "texte", "#FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6c"));
		assertThat(featureCollection, hasNoProperty(XPLAN_41, "BP_BaugebietsTeilFlaeche", "refTextInhalt"));

		assertThat(featureCollection, hasFeatureCount(XPLAN_41, "XP_TextAbschnitt", 1));
		assertThat(featureCollection,
				hasFeatureWithId(XPLAN_41, "XP_TextAbschnitt", "FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6c"));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_41);
	}

	@Test
	public void testModifyXPlan_XPlan41_TextValues_MultipleReferences_New() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_41, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_41, "xplan41/V4_1_ID_103_texts.gml", schema);

		XPlanToEdit editedXplan = factory.createXPlanToEdit(mockXPlan(XPLAN_41), featureCollection);
		Text newText = new Text(null, "key", "basis", "text", "reference", "geoReference");
		editedXplan.getTexts().add(newText);

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_41, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(XPLAN_41, "BP_Plan", "texte", 3));
		assertThat(featureCollection,
				hasHrefAttribue(XPLAN_41, "BP_Plan", "texte", "#FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6b"));
		assertThat(featureCollection,
				hasHrefAttribue(XPLAN_41, "BP_Plan", "texte", "#FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6c"));
		assertThat(featureCollection, hasPropertyCount(XPLAN_41, "BP_BaugebietsTeilFlaeche", "refTextInhalt", 1));
		assertThat(featureCollection, hasHrefAttribue(XPLAN_41, "BP_BaugebietsTeilFlaeche", "refTextInhalt",
				"#FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6b"));
		assertThat(featureCollection, hasFeatureCount(XPLAN_41, "XP_TextAbschnitt", 3));
		assertThat(featureCollection, hasProperty(XPLAN_41, "XP_TextAbschnitt", "text", "Wiese"));
		assertThat(featureCollection, hasProperty(XPLAN_41, "XP_TextAbschnitt", "text", "Weide"));
		assertThat(featureCollection, hasProperty(XPLAN_41, "XP_TextAbschnitt", "text", "text"));
		assertThat(featureCollection,
				hasFeatureWithId(XPLAN_41, "XP_TextAbschnitt", "FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6b"));
		assertThat(featureCollection,
				hasFeatureWithId(XPLAN_41, "XP_TextAbschnitt", "FEATURE_0453f54f-620f-40d7-8c1b-d842c6291a6c"));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_41);
	}

	@Test
	public void testModifyXPlan_XPlan3() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_3, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_3, "xplan30/Wuerdenhain.gml", schema);

		String planName = "newPlanName";
		String description = "newDescription";
		Date creationDate = asDate("2010-01-01");
		Date lossDate = asDate("2020-01-01");
		Date regulationDate = asDate("2006-01-01");
		int legislationStatusCode = 3000;
		int methodCode = 1000;
		int planTypeCode = 5000;
		XPlanToEdit editedXplan = createEditedXplan(planName, description, creationDate, lossDate, regulationDate,
				legislationStatusCode, methodCode, -1, planTypeCode);

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_3, BP_Plan, schema);

		assertThat(featureCollection, hasProperty(XPLAN_3, "BP_Plan", "name", planName));
		assertThat(featureCollection, hasProperty(XPLAN_3, "BP_Plan", "beschreibung", description));
		assertThat(featureCollection, hasProperty(XPLAN_3, "BP_Plan", "technHerstellDatum", creationDate));
		assertThat(featureCollection, hasProperty(XPLAN_3, "BP_Plan", "untergangsDatum", lossDate));
		assertThat(featureCollection, hasProperty(XPLAN_3, "BP_Plan", "rechtsverordnungsDatum", regulationDate));
		assertThat(featureCollection, hasProperty(XPLAN_3, "BP_Plan", "rechtsverordnungsDatum", regulationDate));
		assertThat(featureCollection, hasProperty(XPLAN_3, "BP_Plan", "rechtsstand", legislationStatusCode));
		assertThat(featureCollection, hasNoProperty(XPLAN_3, "BP_Plan", "sonstPlanArt"));
		assertThat(featureCollection, hasProperty(XPLAN_3, "BP_Plan", "planArt", planTypeCode));

		// not supported by XPlan 3.0
		assertThat(featureCollection, hasNoProperty(XPLAN_3, "BP_Plan", "verfahren"));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_3);
	}

	@Test
	public void testModifyXPlan_XPlan3_Changes() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_3, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_3, "xplan30/Wuerdenhain.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		editedXplan.getChanges().add(new Change("planName1", CHANGED_BY));
		editedXplan.getChanges().add(new Change("planName2", CHANGES));
		editedXplan.getChanges().add(new Change("planName3", CHANGES));

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_3, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_Plan", "wurdeGeaendertVon", 1));
		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_Plan", "aendert", 2));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_3);
	}

	@Test
	public void testModifyXPlan_XPlan3_Texts() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_3, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_3, "xplan30/Wuerdenhain.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		editedXplan.getTexts().add(new Text("id1", "key1", "basis1", "text1", "reference1", "geoReference1"));
		editedXplan.getTexts().add(new Text("id2", "key2", "basis2", "text2", "reference2", "geoReference2"));

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_3, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_Plan", "texte", 2));
		assertThat(featureCollection, hasFeatureCount(XPLAN_3, "XP_TextAbschnitt", 2));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_3);
	}

	@Test
	public void testModifyXPlan_XPlan3_TextValues() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_3, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_3, "xplan30/Wuerdenhain.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		Text text = new Text("id1", "key1", "basis1", "text1", "reference1", "geoReference1");
		editedXplan.getTexts().add(text);

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_3, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_Plan", "texte", 1));
		assertThat(featureCollection, hasFeatureCount(XPLAN_3, "XP_TextAbschnitt", 1));
		assertThat(featureCollection, hasProperty(XPLAN_3, "XP_TextAbschnitt", "schluessel", text.getKey()));
		assertThat(featureCollection,
				hasProperty(XPLAN_3, "XP_TextAbschnitt", "gesetzlicheGrundlage", text.getBasis()));
		assertThat(featureCollection, hasProperty(XPLAN_3, "XP_TextAbschnitt", "text", text.getText()));
		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "XP_TextAbschnitt", "refText", 1));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_3);
	}

	@Test
	public void testModifyXPlan_XPlan3_References() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_3, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_3, "xplan30/Wuerdenhain.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		Reference refRechtsplan = new Reference("ref2", "georef2", RECHTSPLAN);
		Reference refBegruendung = new Reference("ref3", "georef3", BEGRUENDUNG);
		Reference refGruenordnung = new Reference("ref1", "georef1", GRUENORDNUNGSPLAN);
		editedXplan.getReferences().add(refRechtsplan);
		editedXplan.getReferences().add(refBegruendung);
		editedXplan.getReferences().add(refGruenordnung);

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_3, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_Plan", "refBegruendung", 1));
		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_Plan", "refRechtsplan", 1));
		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_Plan", "refGruenordnungsplan", 0));

		assertThat(featureCollection,
				hasProperty(XPLAN_3, "XP_ExterneReferenz", "referenzURL", refRechtsplan.getReference()));
		assertThat(featureCollection,
				hasProperty(XPLAN_3, "XP_ExterneReferenz", "referenzURL", refBegruendung.getReference()));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_3);
	}

	@Test
	public void testModifyXPlan_XPlan3_RasterReferences() throws Exception {
		XPlanVersion xPlanVersion = XPLAN_3;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(xPlanVersion, null);
		FeatureCollection featureCollection = readXPlanGml(xPlanVersion, "xplan30/Wuerdenhain.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		RasterReference rasterReference = new RasterReference("GML_1D000019-0DE0-4667-A19C-6EC6ABDF000B", "ref1",
				"georef1", SCAN, IMAGE_PNG, PLANMITGEOREFERENZ, "informationssystemeURL", "refName", TEXT_HTML,
				"beschreibung", asDate("2018-03-01"));
		RasterBasis rasterBasis = new RasterBasis("GML_F042504B-0875-4470-A25D-DAFD0595E8FD");
		rasterBasis.addRasterReference(rasterReference);
		editedXplan.setRasterBasis(rasterBasis);

		planManipulator.modifyXPlan(featureCollection, editedXplan, xPlanVersion, BP_Plan, schema);

		String exportedPlan = exportPlan(featureCollection, xPlanVersion);

		assertThatPlanIsSchemaValid(exportedPlan, xPlanVersion);
		assertThat(exportedPlan, hasXPath("count(//xp:BP_Bereich/xp:rasterBasis)", is("1"))
				.withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath("count(//xp:XP_RasterplanBasis/xp:refScan)", is("1"))
				.withNamespaceContext(nsContext(xPlanVersion)));

		assertThat(exportedPlan, hasXPath(
				"count(//xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan)",
				is("1")).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:referenzURL",
				is(rasterReference.getReference())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:georefURL",
				is(rasterReference.getGeoReference())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan,
				hasXPath("count(//xp:XP_ExterneReferenzPlan[@gml:id='GML_1D000019-0DE0-4667-A19C-6EC6ABDF000B'])",
						is("0")).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:georefURL",
				is(rasterReference.getGeoReference())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:georefMimeType",
				is(rasterReference.getGeorefMimeType().getCode())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, not(HasXPathMatcher.hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:art")
				.withNamespaceContext(nsContext(xPlanVersion))));
		assertThat(exportedPlan, hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:informationssystemURL",
				is(rasterReference.getInformationssystemURL())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:referenzName",
				is(rasterReference.getReferenzName())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:referenzURL",
				is(rasterReference.getReference())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:referenzMimeType",
				is(rasterReference.getReferenzMimeType().getCode())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:beschreibung",
				is(rasterReference.getBeschreibung())).withNamespaceContext(nsContext(xPlanVersion)));
		assertThat(exportedPlan, not(HasXPathMatcher.hasXPath(
				"//xp:XP_ExterneReferenzPlan[concat('#', @gml:id ) = //xp:XP_RasterplanBasis[concat('#', @gml:id ) = //xp:BP_Bereich/xp:rasterBasis/@xlink:href]/xp:refScan/@xlink:href]/xp:datum")
				.withNamespaceContext(nsContext(xPlanVersion))));
	}

	@Test
	public void testModifyXPlan_XPlan3_NullAndEmptyValue() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_3, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_3, "xplan30/Wuerdenhain.gml", schema);

		XPlanToEdit editedXplan = createEditedXplan("newPlanName", null, asDate("2010-01-01"), null,
				asDate("2006-01-01"), 3000, -1, -1, 40000);

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_3, BP_Plan, schema);

		assertThat(featureCollection, hasNoProperty(XPLAN_3, "BP_Plan", "beschreibung"));
		assertThat(featureCollection, hasNoProperty(XPLAN_3, "BP_Plan", "untergangsDatum"));
		assertThat(featureCollection, hasNoProperty(XPLAN_3, "BP_Plan", "verfahren"));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_3);
	}

	@Test
	public void testModifyXPlan_XPlan3_TextValues_MultipleReferences_Modify() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_3, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_3, "xplan30/Wuerdenhain_texts.gml", schema);

		XPlanToEdit editedXplan = factory.createXPlanToEdit(mockXPlan(XPLAN_3), featureCollection);
		retrieveText(editedXplan, "GML_05BD3F6F-70E4-4921-9399-42E5FBDFB6B4").setText("newText1");
		retrieveText(editedXplan, "GML_B78C5C2F-D80E-4E88-9F21-20233701DB89").setText("newText2");

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_3, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_Plan", "texte", 10));
		assertThat(featureCollection,
				hasHrefAttribue(XPLAN_3, "BP_Plan", "texte", "#GML_05BD3F6F-70E4-4921-9399-42E5FBDFB6B4"));
		assertThat(featureCollection,
				hasHrefAttribue(XPLAN_3, "BP_Plan", "texte", "#GML_B78C5C2F-D80E-4E88-9F21-20233701DB89"));
		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_BaugebietsTeilFlaeche", "refTextInhalt", 1));
		assertThat(featureCollection, hasHrefAttribue(XPLAN_3, "BP_BaugebietsTeilFlaeche", "refTextInhalt",
				"#GML_05BD3F6F-70E4-4921-9399-42E5FBDFB6B4"));
		assertThat(featureCollection, hasFeatureCount(XPLAN_3, "XP_TextAbschnitt", 10));
		assertThat(featureCollection, hasProperty(XPLAN_3, "XP_TextAbschnitt", "text", "newText1"));
		assertThat(featureCollection, hasProperty(XPLAN_3, "XP_TextAbschnitt", "text", "newText2"));
		assertThat(featureCollection,
				hasFeatureWithId(XPLAN_3, "XP_TextAbschnitt", "GML_05BD3F6F-70E4-4921-9399-42E5FBDFB6B4"));
		assertThat(featureCollection,
				hasFeatureWithId(XPLAN_3, "XP_TextAbschnitt", "GML_B78C5C2F-D80E-4E88-9F21-20233701DB89"));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_3);
	}

	@Test
	public void testModifyXPlan_XPlan3_TextValues_MultipleReferences_Remove() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_3, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_3, "xplan30/Wuerdenhain_texts.gml", schema);

		XPlanToEdit editedXplan = factory.createXPlanToEdit(mockXPlan(XPLAN_3), featureCollection);
		editedXplan.getTexts().remove(retrieveText(editedXplan, "GML_05BD3F6F-70E4-4921-9399-42E5FBDFB6B4"));

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_3, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_Plan", "texte", 9));
		assertThat(featureCollection,
				hasHrefAttribue(XPLAN_3, "BP_Plan", "texte", "#GML_B78C5C2F-D80E-4E88-9F21-20233701DB89"));
		assertThat(featureCollection, hasNoProperty(XPLAN_3, "BP_BaugebietsTeilFlaeche", "refTextInhalt"));

		assertThat(featureCollection, hasFeatureCount(XPLAN_3, "XP_TextAbschnitt", 9));
		assertThat(featureCollection,
				hasFeatureWithId(XPLAN_3, "XP_TextAbschnitt", "GML_B78C5C2F-D80E-4E88-9F21-20233701DB89"));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_3);
	}

	@Test
	public void testModifyXPlan_XPlan3_TextValues_MultipleReferences_New() throws Exception {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_3, null);
		FeatureCollection featureCollection = readXPlanGml(XPLAN_3, "xplan30/Wuerdenhain_texts.gml", schema);

		XPlanToEdit editedXplan = factory.createXPlanToEdit(mockXPlan(XPLAN_3), featureCollection);
		Text newText = new Text(null, "key", "basis", "text", "reference", "geoReference");
		editedXplan.getTexts().add(newText);

		planManipulator.modifyXPlan(featureCollection, editedXplan, XPLAN_3, BP_Plan, schema);

		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_Plan", "texte", 11));
		assertThat(featureCollection,
				hasHrefAttribue(XPLAN_3, "BP_Plan", "texte", "#GML_05BD3F6F-70E4-4921-9399-42E5FBDFB6B4"));
		assertThat(featureCollection, hasPropertyCount(XPLAN_3, "BP_BaugebietsTeilFlaeche", "refTextInhalt", 1));
		assertThat(featureCollection, hasHrefAttribue(XPLAN_3, "BP_BaugebietsTeilFlaeche", "refTextInhalt",
				"#GML_05BD3F6F-70E4-4921-9399-42E5FBDFB6B4"));
		assertThat(featureCollection, hasFeatureCount(XPLAN_3, "XP_TextAbschnitt", 11));
		assertThat(featureCollection, hasProperty(XPLAN_3, "XP_TextAbschnitt", "text", "text"));
		assertThat(featureCollection,
				hasFeatureWithId(XPLAN_3, "XP_TextAbschnitt", "GML_05BD3F6F-70E4-4921-9399-42E5FBDFB6B4"));

		assertThatPlanIsSchemaValid(featureCollection, XPLAN_3);
	}

	@Test
	public void testModifyXPlan_RasterBasis_refScan() throws Exception {
		XPlanVersion version = XPlanVersion.XPLAN_51;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, "xplan51/V4_1_ID_103.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();
		RasterReference rasterBasisReference = new RasterReference("FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d6",
				"B-Plan_Klingmuehl_Heideweg_Karte.png", "B-Plan_Klingmuehl_Heideweg_Karte.tfw", SCAN, null,
				PLANMITGEOREFERENZ, null, "B-Plan_Klingmuehl_Heideweg_Karte", null, null, null);

		RasterBasis rasterBasis = new RasterBasis("FEATURE_c2a83b1c-05f4-4dc0-a1b6-feb1a43328d6");
		rasterBasis.addRasterReference(rasterBasisReference);
		editedXplan.setRasterBasis(rasterBasis);

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		String exportedPlan = exportPlan(featureCollection, version);
		System.out.println(exportedPlan);
		assertThat(exportedPlan,
				hasXPath("count(//xp:BP_Bereich/xp:rasterBasis)", is("0")).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan,
				hasXPath("count(//xp:XP_Rasterdarstellung)", is("0")).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan,
				hasXPath("count(//xp:BP_Bereich/xp:refScan)", is("1")).withNamespaceContext(nsContext(version)));
	}

	@Test
	public void testModifyXPlan_RasterBasis_refScan_remove() throws Exception {
		XPlanVersion version = XPlanVersion.XPLAN_51;
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, null);
		FeatureCollection featureCollection = readXPlanGml(version, "xplan51/V4_1_ID_103.gml", schema);

		XPlanToEdit editedXplan = createSimpleXPlan();

		planManipulator.modifyXPlan(featureCollection, editedXplan, version, BP_Plan, schema);

		String exportedPlan = exportPlan(featureCollection, version);

		assertThat(exportedPlan,
				hasXPath("count(//xp:BP_Bereich/xp:rasterBasis)", is("0")).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan,
				hasXPath("count(//xp:BP_Bereich/xp:refScan)", is("0")).withNamespaceContext(nsContext(version)));
		assertThat(exportedPlan,
				hasXPath("count(//xp:XP_Rasterdarstellung)", is("0")).withNamespaceContext(nsContext(version)));
	}

	private XPlanToEdit createSimpleXPlan() {
		XPlanToEdit editedXplan = new XPlanToEdit();
		editedXplan.getBaseData().setPlanName("planName");
		editedXplan.getBaseData().setPlanTypeCode(40000);
		return editedXplan;
	}

	private XPlanToEdit createEditedXplan(String planName, String description, Date creationDate, Date lossDate,
			Date regulationDate, int legislationStatusCode, int methodCode, int otherPlanTypeCode, int planTypeCode) {
		XPlanToEdit editedXplan = new XPlanToEdit();
		editedXplan.getBaseData().setPlanName(planName);
		editedXplan.getBaseData().setDescription(description);
		editedXplan.getBaseData().setCreationDate(creationDate);
		editedXplan.getBaseData().setLossDate(lossDate);
		editedXplan.getBaseData().setRegulationDate(regulationDate);
		editedXplan.getBaseData().setLegislationStatusCode(legislationStatusCode);
		editedXplan.getBaseData().setMethodCode(methodCode);
		editedXplan.getBaseData().setOtherPlanTypeCode(otherPlanTypeCode);
		editedXplan.getBaseData().setPlanTypeCode(planTypeCode);
		return editedXplan;
	}

	private void assertThatPlanIsSchemaValid(FeatureCollection featureCollection, XPlanVersion version)
			throws Exception {
		String exportedPlan = exportPlan(featureCollection, version);
		assertThatPlanIsSchemaValid(exportedPlan, version);
	}

	private void assertThatPlanIsSchemaValid(String exportedPlan, XPlanVersion version) throws Exception {
		assertThat(exportedPlan, ValidationMatcher.valid(Input.fromURI(version.getSchemaUrl().toURI())));
	}

	private String exportPlan(FeatureCollection featureCollection, XPlanVersion version) throws Exception {
		XPlanExporter planExporter = new XPlanExporter(null);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		planExporter.export(outputStream, version, featureCollection, null);
		return new String(outputStream.toByteArray());
	}

	private Map<String, String> nsContext(XPlanVersion version) {
		Map<String, String> nsContext = new HashMap<>();
		nsContext.put("xplan", XPLAN_41.getNamespace());
		nsContext.put("xp", version.getNamespace());
		nsContext.put("gml", version.getGmlVersion().getNamespace());
		nsContext.put("xlink", "http://www.w3.org/1999/xlink");
		return nsContext;
	}

	private Matcher<? super FeatureCollection> hasFeatureCount(final XPlanVersion version, final String expectedFeature,
			final int numberOfOccurences) {
		return new TypeSafeMatcher<FeatureCollection>() {

			@Override
			protected boolean matchesSafely(FeatureCollection featureCollection) {
				Iterator<Feature> iterator = featureCollection.iterator();
				int numberOfExpectedFeatures = 0;
				while (iterator.hasNext()) {
					Feature feature = iterator.next();
					if (feature.getName().equals(new QName(version.getNamespace(), expectedFeature)))
						numberOfExpectedFeatures++;
				}
				return numberOfExpectedFeatures == numberOfOccurences;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Expcted are ");
				description.appendValue(numberOfOccurences);
				description.appendText(" features with name ");
				description.appendValue(expectedFeature);
			}
		};
	}

	private Matcher<FeatureCollection> hasPropertyCount(final XPlanVersion version, final String expectedFeature,
			final String expectedProperty, final int numberOfOccurences) {
		return new TypeSafeMatcher<FeatureCollection>() {

			@Override
			protected boolean matchesSafely(FeatureCollection featureCollection) {
				Iterator<Feature> iterator = featureCollection.iterator();
				while (iterator.hasNext()) {
					Feature feature = iterator.next();
					if (feature.getName().equals(new QName(version.getNamespace(), expectedFeature))) {
						List<Property> properties = feature
								.getProperties(new QName(version.getNamespace(), expectedProperty));
						return properties.size() == numberOfOccurences;
					}
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Expcted is a feature with name ");
				description.appendValue(expectedFeature);
				description.appendText(" with exact ");
				description.appendValue(numberOfOccurences);
				description.appendText(" property/properties with name ");
				description.appendValue(expectedProperty);
			}

		};
	}

	private Matcher<FeatureCollection> hasProperty(final XPlanVersion version, final String expectedFeature,
			final String expectedProperty, final int expectedValue) {
		return hasProperty(version, expectedFeature, expectedProperty, new PropertyMatcher() {

			@Override
			public boolean matches(List<Property> properties) {
				if (properties.size() != 1)
					return false;
				PrimitiveValue value = (PrimitiveValue) properties.get(0).getValue();
				return expectedValue == Integer.parseInt(value.getAsText());
			}

			@Override
			public Object getExpectedValue() {
				return expectedValue;
			}
		});
	}

	private Matcher<FeatureCollection> hasProperty(final XPlanVersion version, final String expectedFeature,
			final String expectedProperty, final Date expectedValue) {
		return hasProperty(version, expectedFeature, expectedProperty, new PropertyMatcher() {

			@Override
			public boolean matches(List<Property> properties) {
				if (properties.size() != 1)
					return false;
				PrimitiveValue value = (PrimitiveValue) properties.get(0).getValue();
				org.deegree.commons.tom.datetime.Date propertyValue = (org.deegree.commons.tom.datetime.Date) value
						.getValue();
				return expectedValue.equals(new Date(propertyValue.getTimeInMilliseconds()));
			}

			@Override
			public Object getExpectedValue() {
				return expectedValue;
			}
		});
	}

	private Matcher<FeatureCollection> hasProperty(final XPlanVersion version, final String expectedFeature,
			final String expectedProperty, final String expectedValue) {
		return hasProperty(version, expectedFeature, expectedProperty, new PropertyMatcher() {

			@Override
			public boolean matches(List<Property> properties) {
				if (properties.size() != 1)
					return false;
				String propertyValue = properties.get(0).getValue().toString();
				return propertyValue.equals(expectedValue);
			}

			@Override
			public Object getExpectedValue() {
				return expectedValue;
			}
		});
	}

	private Matcher<? super FeatureCollection> hasNoProperty(final XPlanVersion version, final String expectedFeature,
			final String expectedProperty) {
		return new TypeSafeMatcher<FeatureCollection>() {

			@Override
			protected boolean matchesSafely(FeatureCollection featureCollection) {
				Iterator<Feature> iterator = featureCollection.iterator();
				while (iterator.hasNext()) {
					Feature feature = iterator.next();
					if (feature.getName().equals(new QName(version.getNamespace(), expectedFeature))) {
						List<Property> properties = feature
								.getProperties(new QName(version.getNamespace(), expectedProperty));
						return properties.isEmpty();
					}
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Expcted is that the feature with name ");
				description.appendValue(expectedFeature);
				description.appendText(" does not have a property ");
				description.appendValue(expectedProperty);
			}

		};
	}

	private Matcher<FeatureCollection> hasProperty(final XPlanVersion version, final String expectedFeature,
			final String expectedProperty, final PropertyMatcher propertyMatcher) {
		return new TypeSafeMatcher<FeatureCollection>() {

			@Override
			protected boolean matchesSafely(FeatureCollection featureCollection) {
				for (Feature feature : featureCollection) {
					if (feature.getName().equals(new QName(version.getNamespace(), expectedFeature))) {

						List<Property> properties = feature
								.getProperties(new QName(version.getNamespace(), expectedProperty));
						if (propertyMatcher.matches(properties))
							return true;
					}
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Expcted is a feature with name ");
				description.appendValue(expectedFeature);
				description.appendText(" with a property/attribute ");
				description.appendValue(expectedProperty);
				description.appendText(" and value ");
				description.appendValue(propertyMatcher.getExpectedValue());
			}

		};
	}

	private Matcher<FeatureCollection> hasFeatureWithId(final XPlanVersion version, final String expectedFeature,
			final String expectedFeatureId) {
		return new TypeSafeMatcher<FeatureCollection>() {

			@Override
			protected boolean matchesSafely(FeatureCollection featureCollection) {
				for (Feature feature : featureCollection) {
					if (feature.getName().equals(new QName(version.getNamespace(), expectedFeature))) {
						String id = feature.getId();
						if (expectedFeatureId.equals(id))
							return true;
					}
				}
				return false;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Expcted is a feature with name ");
				description.appendValue(expectedFeature);
				description.appendText(" with id");
				description.appendValue(expectedFeatureId);
			}

		};
	}

	private Matcher<FeatureCollection> hasHrefAttribue(final XPlanVersion version, final String expectedFeature,
			final String expectedProperty, final String expectedHrefValue) {
		return hasProperty(version, expectedFeature, expectedProperty, new PropertyMatcher() {

			private final QName xlinkHrefAttribute = new QName("http://www.w3.org/1999/xlink", "href");

			@Override
			public boolean matches(List<Property> properties) {
				for (Property property : properties) {
					PrimitiveValue hrefValue = property.getAttributes().get(xlinkHrefAttribute);
					if (hrefValue != null && hrefValue.toString().equals(expectedHrefValue))
						return true;
				}
				return false;
			}

			@Override
			public Object getExpectedValue() {
				return expectedHrefValue;
			}
		});
	}

	private FeatureCollection readXPlanGml(XPlanVersion xplanVersion, String plan, AppSchema schema) throws Exception {
		InputStream xplanGml = this.getClass().getResourceAsStream(plan);
		XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(xplanGml);
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(reader, null);

		GeometryFactory geomFac = new GeometryFactory();
		GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader(xplanVersion.getGmlVersion(), xmlStream);
		gmlStream.setApplicationSchema(schema);
		gmlStream.setGeometryFactory(geomFac);
		return (FeatureCollection) gmlStream.readFeature();
	}

	private Text retrieveText(XPlanToEdit xplanToEdit, String featureId) {
		for (Text text : xplanToEdit.getTexts()) {
			if (featureId.equals(text.getFeatureId()))
				return text;
		}
		return null;
	}

	private XPlan mockXPlan(XPlanVersion version) {
		XPlan mock = mock(XPlan.class);
		when(mock.getVersion()).thenReturn(version.toString());
		return mock;
	}

	private Date asDate(String string) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return simpleDateFormat.parse(string);
	}

	private interface PropertyMatcher {

		Object getExpectedValue();

		boolean matches(List<Property> properties);

	}

}
