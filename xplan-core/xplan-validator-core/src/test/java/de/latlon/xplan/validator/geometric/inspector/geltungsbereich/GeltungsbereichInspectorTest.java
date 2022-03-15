/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.inspector.geltungsbereich;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichInspectorTest {

	@Test
	public void testCheck() throws Exception {
		XPlanArchive archive = getTestArchive("xplan51/V4_1_ID_103_geltungsbereich-erfuellt.zip");
		long startTimeMillis = System.currentTimeMillis();
		GeltungsbereichInspector geltungsbereichInspector = readFeatures(archive);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		long endTimeMillis = System.currentTimeMillis();
		assertThat(isValid, is(true));
		assertThat(geltungsbereichInspector.getErrors().size(), is(0));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(0));

		System.out.println(String.format("Geltungsbereich check needed %s [ms]", endTimeMillis - startTimeMillis));
	}

	@Test
	public void testCheck_invalid() throws Exception {
		XPlanArchive archive = getTestArchive("xplan51/V4_1_ID_103.zip");
		GeltungsbereichInspector geltungsbereichInspector = readFeatures(archive);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));

		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getOriginalGeometry(), is(notNullValue()));
	}

	@Test
	public void testCheck_invalid_41() throws Exception {
		XPlanArchive archive = getTestArchive("xplan41/V4_1_ID_103.zip");
		GeltungsbereichInspector geltungsbereichInspector = readFeatures(archive);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));

		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getOriginalGeometry(), is(notNullValue()));
	}

	@Test
	public void testCheck_invalid_withLine() throws Exception {
		XPlanArchive archive = getTestArchive("xplan52/BP2070.zip");
		GeltungsbereichInspector geltungsbereichInspector = readFeatures(archive);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(2));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(2));

		BadGeometry badGeometry1 = geltungsbereichInspector.getBadGeometries().get(0);
		assertThat(badGeometry1.getOriginalGeometry(), is(notNullValue()));
		assertThat(badGeometry1.getMarkerGeometries().size(), is(1));

		BadGeometry badGeometry2 = geltungsbereichInspector.getBadGeometries().get(1);
		assertThat(badGeometry2.getOriginalGeometry(), is(notNullValue()));
		assertThat(badGeometry2.getMarkerGeometries().size(), is(1));
	}

	@Test
	public void testCheck_valid_tolerance() throws Exception {
		XPlanArchive archive = getTestArchive("xplan51/V4_1_ID_103_withtolerance.zip");
		GeltungsbereichInspector geltungsbereichInspector = readFeatures(archive);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheck_MultipePlanNoBereich() throws Exception {
		XPlanArchive archive = getLokalArchive("HafenCity11_HafenCity14_Bereich_ohne_Geometrie.gml");
		GeltungsbereichInspector geltungsbereichInspector = readFeatures(archive);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	private GeltungsbereichInspector readFeatures(XPlanArchive archive) throws XMLStreamException, UnknownCRSException {
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(archive.getMainFileXmlReader(), null);
		XPlanVersion version = archive.getVersion();
		GMLVersion gmlVersion = version.getGmlVersion();
		XPlanAde ade = archive.getAde();
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, ade);

		GeometryFactory geomFac = new GeometryFactory();
		GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader(gmlVersion, xmlStream);
		gmlStream.setGeometryFactory(geomFac);
		gmlStream.setApplicationSchema(schema);
		gmlStream.setSkipBrokenGeometries(true);
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector();
		gmlStream.addInspector(geltungsbereichInspector);
		gmlStream.readFeature();

		return geltungsbereichInspector;
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

	private XPlanArchive getLokalArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromGml(name,
				GeltungsbereichInspectorTest.class.getResourceAsStream(name));
	}

}