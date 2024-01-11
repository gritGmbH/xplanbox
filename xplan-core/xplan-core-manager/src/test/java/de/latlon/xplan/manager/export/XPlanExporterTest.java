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

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.core.manager.db.model.ArtefactId;
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

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
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

	@Test
	public void testExport() throws Exception {
		XPlanExporter exporter = new XPlanExporter();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		List<Artefact> artefacts = creatertefactStream();
		exporter.export(outputStream, artefacts);

		List<String> exportedFiles = readExportedContent(outputStream);

		assertEquals(2, exportedFiles.size());
		assertThat(exportedFiles, hasItems("1.xml", "2.xml"));
	}

	@Test
	public void testExportWithNullManagerConfiguration() throws Exception {
		XPlanExporter xplanExporter = new XPlanExporter();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		List<Artefact> artefacts = creatertefactStream();
		xplanExporter.export(outputStream, artefacts);

		List<String> exportedFiles = readExportedContent(outputStream);

		assertEquals(2, exportedFiles.size());
		assertThat(exportedFiles, hasItems("1.xml", "2.xml"));
	}

	@Test
	public void testExport_SchemaConform() throws Exception {
		FeatureCollection featureCollection = readFeatureCollection("xplan60/BPlan001_6-0.zip");

		XPlanExporter planExporter = new XPlanExporter();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		planExporter.export(outputStream, XPLAN_60, featureCollection, null);

		String exportedPlan = new String(outputStream.toByteArray());

		assertThat(exportedPlan, ValidationMatcher.valid(Input.fromURI(XPLAN_60.getSchemaUrl().toURI())));
	}

	private List<Artefact> creatertefactStream() throws Exception {
		Artefact artefact1 = artefact("1.xml");
		Artefact artefact2 = artefact("2.xml");
		return List.of(artefact1, artefact2);
	}

	private Artefact artefact(String name) throws IOException {
		Artefact xPlanArtefact = mock(Artefact.class);
		ArtefactId xPlanArtefactId = mock(ArtefactId.class);
		when(xPlanArtefactId.getFilename()).thenReturn(name);
		when(xPlanArtefact.getId()).thenReturn(xPlanArtefactId);
		byte[] content = createZippedContent(name);
		when(xPlanArtefact.getData()).thenReturn(content);
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
		InputStream archiveResource = getClass().getResourceAsStream("/testdata/" + testArchiveName);
		return archiveCreator.createXPlanArchiveFromZip(testArchiveName, archiveResource);
	}

	private XPlanFeatureCollection readFeatures(XPlanArchive archive) throws Exception {
		ICRS defaultCrs = CRSManager.lookup("EPSG:31467");
		if (archive.getCrs() != null)
			defaultCrs = archive.getCrs();
		return XPlanGmlParserBuilder.newBuilder()
			.withDefaultCrs(defaultCrs)
			.build()
			.parseXPlanFeatureCollection(archive);
	}

	private byte[] createZippedContent(String name) throws IOException {
		InputStream is = new ByteArrayInputStream(name.getBytes());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(bos);
		copyLarge(is, gos);
		gos.close();
		return bos.toByteArray();
	}

}
