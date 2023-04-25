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
package de.latlon.xplan.manager.export;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;
import org.xmlunit.builder.Input;
import org.xmlunit.matchers.ValidationMatcher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.apache.commons.io.IOUtils.copyLarge;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanExporterTest {

	private XPlanExporter exporter = new XPlanExporter();

	@Test
	public void testExport() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		XPlanArtefactIterator artefacts = mockArtefactIterator();
		XPlanArchiveContent contents = createContents(artefacts);
		exporter.export(outputStream, contents);

		List<String> exportedFiles = readExportedContent(outputStream);

		assertEquals(2, exportedFiles.size());
		assertThat(exportedFiles, hasItems("1.xml", "2.xml"));
	}

	@Test
	public void testExportWithNullManagerConfiguration() throws Exception {
		XPlanExporter xplanExporter = new XPlanExporter();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		XPlanArtefactIterator artefacts = mockArtefactIterator();
		XPlanArchiveContent contents = createContents(artefacts);
		xplanExporter.export(outputStream, contents);

		List<String> exportedFiles = readExportedContent(outputStream);

		assertEquals(2, exportedFiles.size());
		assertThat(exportedFiles, hasItems("1.xml", "2.xml"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testExport_SchemaConform() throws Exception {
		FeatureCollection featureCollection = readFeatureCollection("xplan41/V4_1_ID_103.zip");

		XPlanExporter planExporter = new XPlanExporter();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		planExporter.export(outputStream, XPLAN_41, featureCollection, null);

		String exportedPlan = new String(outputStream.toByteArray());

		assertThat(exportedPlan, ValidationMatcher.valid(Input.fromURI(XPLAN_41.getSchemaUrl().toURI())));
	}

	private XPlanArchiveContent createContents(XPlanArtefactIterator artefacts) throws Exception {
		FeatureCollection restoredFeatureCollection = readFeatureCollection("xplan41/V4_1_ID_103.zip");
		return new XPlanArchiveContent(restoredFeatureCollection, artefacts, XPlanVersion.XPLAN_41);
	}

	private XPlanArtefactIterator mockArtefactIterator() throws Exception {
		XPlanArtefactIterator mockedIterator = mock(XPlanArtefactIterator.class);
		when(mockedIterator.hasNext()).thenReturn(true, true, false);
		XPlanArtefact stream1 = stream("1.xml");
		XPlanArtefact stream2 = stream("2.xml");
		when(mockedIterator.next()).thenReturn(stream1, stream2, null);
		return mockedIterator;
	}

	private XPlanArtefact stream(String name) throws IOException {
		XPlanArtefact xPlanArtefact = mock(XPlanArtefact.class);
		when(xPlanArtefact.getFileName()).thenReturn(name);
		ByteArrayInputStream content = createZippedContent(name);
		when(xPlanArtefact.getContent()).thenReturn(content);
		return xPlanArtefact;
	}

	private List<String> readExportedContent(ByteArrayOutputStream outputStream) throws Exception {
		List<String> exportedFiles = new ArrayList<String>();
		ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(outputStream.toByteArray()));
		ZipEntry entry;
		while ((entry = zipInputStream.getNextEntry()) != null) {
			exportedFiles.add(entry.getName());
		}
		return exportedFiles;
	}

	private FeatureCollection readFeatureCollection(String archiveName) throws Exception {
		XPlanArchive archive = createArchive(archiveName);
		XPlanFeatureCollection xplanFc = readFeatures(archive);
		return xplanFc.getFeatures();
	}

	private XPlanArchive createArchive(String testArchiveName) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		InputStream archiveResource = ResourceAccessor.readResourceStream(testArchiveName);
		return archiveCreator.createXPlanArchiveFromZip(testArchiveName, archiveResource);
	}

	private XPlanFeatureCollection readFeatures(XPlanArchive archive) throws Exception {
		ICRS defaultCrs = CRSManager.lookup("EPSG:31467");
		if (archive.getCrs() != null)
			defaultCrs = archive.getCrs();
		return XPlanGmlParserBuilder.newBuilder().withDefaultCrs(defaultCrs).build()
				.parseXPlanFeatureCollection(archive);
	}

	private ByteArrayInputStream createZippedContent(String name) throws IOException {
		InputStream is = new ByteArrayInputStream(name.getBytes());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(bos);
		copyLarge(is, gos);
		gos.close();
		return new ByteArrayInputStream(bos.toByteArray());
	}

}
