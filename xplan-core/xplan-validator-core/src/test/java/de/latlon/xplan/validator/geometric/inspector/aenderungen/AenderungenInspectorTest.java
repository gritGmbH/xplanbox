package de.latlon.xplan.validator.geometric.inspector.aenderungen;

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

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.geometric.inspector.aenderungen.AenderungenInspector;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.junit.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AenderungenInspectorTest {

	@Test
	public void test_InspectAendertAndWurdeGeaendertVonReferences() throws Exception {
		AenderungenInspector aenderungenInspector = readFeatures("aendertUndWurdeGeandertVon.gml");

		List<String> aendertAndWurdeGeandertVonReferences = aenderungenInspector
				.getLokalAendertAndWurdeGeandertVonReferences();
		assertThat(aendertAndWurdeGeandertVonReferences.size(), is(2));
	}

	private AenderungenInspector readFeatures(String resource) throws XMLStreamException, UnknownCRSException {
		InputStream resourceAsStream = getClass().getResourceAsStream(resource);
		XMLStreamReader xmlStreamReader = XMLInputFactory.newFactory().createXMLStreamReader(resourceAsStream);
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(xmlStreamReader, null);
		XPlanVersion version = XPlanVersion.XPLAN_50;
		GMLVersion gmlVersion = version.getGmlVersion();
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version);

		GeometryFactory geomFac = new GeometryFactory();
		GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader(gmlVersion, xmlStream);
		gmlStream.setGeometryFactory(geomFac);
		gmlStream.setApplicationSchema(schema);
		gmlStream.setSkipBrokenGeometries(true);
		AenderungenInspector aenderungenInspector = new AenderungenInspector();
		gmlStream.addInspector(aenderungenInspector);
		gmlStream.readFeature();

		return aenderungenInspector;
	}

}
