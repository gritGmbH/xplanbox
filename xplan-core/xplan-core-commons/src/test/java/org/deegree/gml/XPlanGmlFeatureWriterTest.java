/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package org.deegree.gml;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import org.junit.jupiter.api.Test;
import org.xmlunit.assertj3.XmlAssert;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class XPlanGmlFeatureWriterTest {

	@Test
	void testWrite() throws Exception {
		XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection("xplan41/Eidelstedt_4_V4.zip");
		String xPlanFeatureCollection = writeXPlanFeatureCollection(fc);
		XmlAssert.assertThat(xPlanFeatureCollection).withNamespaceContext(nsContext()).hasXPath("/xplan:XPlanAuszug");
	}

	private String writeXPlanFeatureCollection(XPlanFeatureCollection xPlanFeatureCollection) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		XMLStreamWriter xmlStream = XMLOutputFactory.newFactory().createXMLStreamWriter(os);
		GMLStreamWriter gmlStreamWriter = new XPlanGmlWriter(XPLAN_41, xmlStream);
		gmlStreamWriter.write(xPlanFeatureCollection.getFeatures());
		gmlStreamWriter.close();
		xmlStream.close();
		return os.toString();
	}

	private XPlanFeatureCollection getMainFileAsXplanFeatureCollection(String name) throws Exception {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(name,
				getClass().getResourceAsStream("/testdata/" + name));
		return XPlanGmlParserBuilder.newBuilder().build().parseXPlanFeatureCollection(archive);
	}

	private Map<String, String> nsContext() {
		Map<String, String> nsContext = new HashMap<>();
		nsContext.put("xplan", XPLAN_41.getNamespace());
		return nsContext;
	}

}
