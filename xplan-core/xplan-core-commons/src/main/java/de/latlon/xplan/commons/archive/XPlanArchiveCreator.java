/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.commons.archive;

import org.deegree.commons.utils.Pair;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.lang.String.format;

/**
 * Creator for {@link XPlanArchive}s.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanArchiveCreator {

	public static final String MAIN_FILE = "xplan.gml";

	private final LocalCenterToDistrictMapper localCenterToDistrictMapper;

	public XPlanArchiveCreator() {
		this(null);
	}

	public XPlanArchiveCreator(LocalCenterToDistrictMapper localCenterToDistrictMapper) {
		this.localCenterToDistrictMapper = localCenterToDistrictMapper;
	}

	/**
	 * Creates a new {@link XPlanArchive} instance from the given file.
	 * @param file XPlan archive (ZIP-file), must not be <code>null</code>
	 * @throws IllegalArgumentException if the file can not be read or is obviously
	 * invalid
	 */
	public XPlanArchive createXPlanArchive(File file) throws IOException {
		String fileName = file.getName();
		if (fileName.toLowerCase().endsWith(".zip"))
			return createXPlanArchiveFromZip(fileName, new FileInputStream(file));
		return createXPlanArchiveFromGml(fileName, new FileInputStream(file));
	}

	/**
	 * Creates a new {@link XPlanArchive} instance from the given XPlanArchive.
	 * @param file XPlanArchive (ZIP-file), never <code>null</code>
	 * @throws IllegalArgumentException if the file can not be read or is obviously
	 * invalid
	 */
	public XPlanArchive createXPlanArchiveFromZip(File file) throws IOException {
		String fileName = file.getName();
		return createXPlanArchiveFromZip(fileName, new FileInputStream(file));
	}

	/**
	 * Creates a new {@link XPlanArchive} instance from the given XPlanArchive.
	 * @param name of the file, never <code>null</code>
	 * @param inputStream never <code>null</code> and is closed on return
	 * @throws IOException
	 */
	public XPlanArchive createXPlanArchiveFromZip(String name, InputStream inputStream) throws IOException {
		try {
			List<ZipEntryWithContent> zipEntries = new ArrayList<>();
			Pair<MainZipEntry, ArchiveMetadata> mainEntry = readEntries(inputStream, zipEntries);
			ArchiveMetadata archiveMetadata = mainEntry.getSecond();
			List<String> districts = mapDistricts(archiveMetadata);
			return new XPlanArchive(zipEntries, name, archiveMetadata.getVersion(), archiveMetadata.getType(),
					archiveMetadata.getCrs(), districts, archiveMetadata.hasVerbundenerPlanBereich(),
					archiveMetadata.hasMultipleXPlanElements());
		}
		catch (XMLStreamException | FactoryConfigurationError e) {
			String message = format("Kann Archiv '%s' nicht lesen.\nTechnischer Hinweis zur Fehlerursache: %s", name,
					e.getLocalizedMessage());
			throw new IllegalArgumentException(message, e);
		}
		finally {
			inputStream.close();
		}
	}

	/**
	 * Creates a new {@link XPlanArchive} instance from the given XPlanGML.
	 * @param file XPlanGML (GML-file), never <code>null</code>
	 * @throws IllegalArgumentException if the file can not be read or is obviously
	 * invalid
	 */
	public XPlanArchive createXPlanArchiveFromGml(File file) throws IOException {
		String fileName = file.getName();
		return createXPlanArchiveFromGml(fileName, new FileInputStream(file));
	}

	/**
	 * Creates a new {@link XPlanArchive} instance from the given XPlanGML.
	 * @param name of the file, never <code>null</code>
	 * @param inputStream never <code>null</code> and is closed on return
	 * @throws IOException
	 */
	public XPlanArchive createXPlanArchiveFromGml(String name, InputStream inputStream) throws IOException {
		XPlanGmlReader xPlanGmlReader = new XPlanGmlReader();
		try {
			Pair<MainZipEntry, ArchiveMetadata> mainEntry = xPlanGmlReader.createZipEntry(name, inputStream);
			ArchiveMetadata archiveMetadata = mainEntry.getSecond();
			List<String> districts = mapDistricts(archiveMetadata);
			return new XPlanArchive(mainEntry.first, name, archiveMetadata.getVersion(), archiveMetadata.getType(),
					archiveMetadata.getCrs(), districts, archiveMetadata.hasVerbundenerPlanBereich(),
					archiveMetadata.hasMultipleXPlanElements());
		}
		catch (XMLStreamException e) {
			String message = format("Kann Archiv '%s' nicht lesen.\nTechnischer Hinweis zur Fehlerursache: %s", name,
					e.getLocalizedMessage());
			throw new IllegalArgumentException(message, e);
		}
		finally {
			inputStream.close();
		}
	}

	private List<String> mapDistricts(ArchiveMetadata archiveMetadata) {
		List<String> districts = archiveMetadata.getDistricts();
		if (localCenterToDistrictMapper == null)
			return districts;
		return districts.stream()
			.map(district -> localCenterToDistrictMapper.mapToDistrict(district))
			.collect(Collectors.toList());
	}

	private Pair<MainZipEntry, ArchiveMetadata> readEntries(InputStream inputStream,
			List<ZipEntryWithContent> zipEntries) throws IOException, XMLStreamException {
		Pair<MainZipEntry, ArchiveMetadata> mainZipEntry = null;
		ZipInputStream zipInputStream = new ZipInputStream(inputStream, Charset.forName("UTF-8"));
		ZipEntry entry;
		while ((entry = zipInputStream.getNextEntry()) != null) {
			ArtefactEntry zipEntry = readZipEntryFromStream(zipInputStream, entry);
			if (MAIN_FILE.equals(entry.getName())) {
				XPlanGmlReader xPlanGmlReader = new XPlanGmlReader();
				mainZipEntry = xPlanGmlReader.createZipEntry(zipEntry);
				zipEntries.add(mainZipEntry.first);
			}
			else {
				zipEntries.add(zipEntry);
			}
		}
		if (mainZipEntry == null) {
			throw new IllegalArgumentException(
					"GML-Datei kann nicht eingelesen werden. Ist der Dateiname korrekt (xplan.gml)?");
		}
		return mainZipEntry;
	}

	private ArtefactEntry readZipEntryFromStream(ZipInputStream zipInputStream, ZipEntry entry) throws IOException {
		byte[] buffer = new byte[2048];
		try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			int len;
			while ((len = zipInputStream.read(buffer)) > 0) {
				output.write(buffer, 0, len);
			}
			return new ArtefactEntry(entry, output.toByteArray());
		}
	}

}
