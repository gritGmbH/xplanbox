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
package de.latlon.xplan.validator.geometric.inspector.flaechenschluss;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
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
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OptimisedFlaechenschlussInspectorTest {

	@Test
	public void testCheckFlaechenschluss() throws Exception {
		XPlanArchive archive = getTestArchive("xplan51/V4_1_ID_103.zip");
		OptimisedFlaechenschlussInspector flaechenschlussInspector = readFeatures(archive);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheckFlaechenschluss_wirksamkeit() throws Exception {
		XPlanArchive archive = getTestArchive("xplan51/V4_1_ID_103_wirksamkeit.zip");
		OptimisedFlaechenschlussInspector flaechenschlussInspector = readFeatures(archive);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheckFlaechenschluss_invalid() throws Exception {
		XPlanArchive archive = getTestArchive("xplan51/V4_1_ID_103_kein-flaechenschluss.zip");
		OptimisedFlaechenschlussInspector flaechenschlussInspector = readFeatures(archive);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(false));
	}

	@Test
	public void testCheckFlaechenschluss_equalFlaechenschlussGeometries() throws Exception {
		XPlanArchive archive = getLocalTestArchive("equalFlaechenschlussGeometries.gml");
		OptimisedFlaechenschlussInspector flaechenschlussInspector = readFeatures(archive);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(flaechenschlussInspector.getErrors().size(), is(1));
	}

	@Test
	public void testCheckFlaechenschluss_LueckeGeltungsbereich() throws Exception {
		XPlanArchive archive = getLocalTestArchive("xplan52_Flaechenschlussfehler_Luecke_Geltungsbereich.gml");
		OptimisedFlaechenschlussInspector flaechenschlussInspector = readFeatures(archive);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(3));
	}

	private OptimisedFlaechenschlussInspector readFeatures(XPlanArchive archive)
			throws XMLStreamException, UnknownCRSException {
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(archive.getMainFileXmlReader(), null);
		XPlanVersion version = archive.getVersion();
		GMLVersion gmlVersion = version.getGmlVersion();
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version);

		GeometryFactory geomFac = new GeometryFactory();
		GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader(gmlVersion, xmlStream);
		gmlStream.setGeometryFactory(geomFac);
		gmlStream.setApplicationSchema(schema);
		gmlStream.setSkipBrokenGeometries(true);
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(
				archive.getVersion());
		gmlStream.addInspector(flaechenschlussInspector);
		gmlStream.readFeature();

		return flaechenschlussInspector;
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

	private XPlanArchive getLocalTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromGml(name,
				OptimisedFlaechenschlussInspector.class.getResourceAsStream(name));
	}

}
