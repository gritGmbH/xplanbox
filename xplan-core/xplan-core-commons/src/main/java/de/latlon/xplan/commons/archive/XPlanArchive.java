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

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XmlUtils;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.deegree.cs.coordinatesystems.ICRS;

import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.commons.archive.XPlanArchiveCreator.MAIN_FILE;
import static java.lang.String.format;
import static org.deegree.commons.xml.stax.XMLStreamUtils.skipStartDocument;

/**
 * Provides easy access to the metadata and contents of an XPlan archive.
 * <p>
 * An XPlan archive is a ZIP file with a defined structure:
 * <ul>
 * <li><code>xplan.gml</code> (mandatory, main XPlan feature collection)</li>
 * <li><code>*</code> (optional, other artifacts referenced by the main file)</li>
 * </ul>
 * </p>
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanArchive implements XPlanArchiveContentAccess, SemanticValidableXPlanArchive {

	private final List<ZipEntryWithContent> zipFileEntries;

	private final MainZipEntry mainEntry;

	private final String fileName;

	private final XPlanVersion version;

	private final XPlanType type;

	private final ICRS crs;

	private boolean hasVerbundenerPlanBereich;

	private final boolean hasMultipleXPlanElements;

	XPlanArchive(List<ZipEntryWithContent> zipEntries, String fileName, XPlanVersion version, XPlanType type, ICRS crs,
			boolean hasVerbundenerPlanBereich, boolean hasMultipleXPlanElements) {
		this(zipEntries, null, fileName, version, type, crs, hasVerbundenerPlanBereich, hasMultipleXPlanElements);
	}

	public XPlanArchive(MainZipEntry mainEntry, String fileName, XPlanVersion version, XPlanType type, ICRS crs,
			boolean hasVerbundenerPlanBereich, boolean hasMultipleXPlanElements) {
		this(Collections.singletonList(mainEntry), mainEntry, fileName, version, type, crs, hasVerbundenerPlanBereich,
				hasMultipleXPlanElements);
	}

	private XPlanArchive(List<ZipEntryWithContent> zipEntries, MainZipEntry mainEntry, String fileName,
			XPlanVersion version, XPlanType type, ICRS crs, boolean hasVerbundenerPlanBereich,
			boolean hasMultipleXPlanElements) {
		this.zipFileEntries = zipEntries;
		this.mainEntry = mainEntry;
		this.fileName = fileName;
		this.version = version;
		this.type = type;
		this.crs = crs;
		this.hasVerbundenerPlanBereich = hasVerbundenerPlanBereich;
		this.hasMultipleXPlanElements = hasMultipleXPlanElements;
	}

	/**
	 * Returns the XPlan version.
	 * @return version, never <code>null</code>
	 */
	@Override
	public XPlanVersion getVersion() {
		return version;
	}

	/**
	 * Returns the XPlan type.
	 * @return type, never <code>null</code>
	 */
	public XPlanType getType() {
		return type;
	}

	/**
	 * Returns the CRS.
	 * @return crs, can be <code>null</code> (unspecified)
	 */
	public ICRS getCrs() {
		return crs;
	}

	/**
	 * @return <code>true</code> if the XPLanArchive contains at least one Plan with
	 * verbundenerPlan or verbundenerPlanBereich, <code>false</code> otherwise
	 */
	public boolean hasVerbundenerPlanBereich() {
		return hasVerbundenerPlanBereich;
	}

	/**
	 * @return <code>true</code> if the XPLanArchive contains multiple XPlanElements,
	 * <code>false</code> otherwise
	 */
	public boolean hasMultipleXPlanElements() {
		return hasMultipleXPlanElements;
	}

	@Override
	public List<ZipEntryWithContent> getZipFileEntries() {
		return zipFileEntries;
	}

	/**
	 * Retrieve a <link>InputStream</link> returning the main file of this archive
	 * @return the main file as <link>InputStream</link>
	 */
	@Override
	public InputStream getMainFileInputStream() {
		return new ByteArrayInputStream(getMainFile().getContent());
	}

	/**
	 * Returns a reader for the XML of the main file. Start document is skipped.
	 * @return reader, never <code>null</code>
	 */
	@Override
	@SuppressFBWarnings(value = "XXE_XMLSTREAMREADER")
	public XMLStreamReader getMainFileXmlReader() {
		try {
			XMLStreamReader xmlReader = XmlUtils.createXMLInputFactory()
				.createXMLStreamReader(getMainFileInputStream());
			skipStartDocument(xmlReader);
			return xmlReader;
		}
		catch (Exception e) {
			String message = format("Kann Datei '%s' aus '%s' nicht lesen. Fehlermeldung: %s", MAIN_FILE, this.fileName,
					e.getLocalizedMessage());
			throw new IllegalArgumentException(message, e);
		}
	}

	@Override
	public ZipEntryWithContent getEntry(String name) {
		checkNameParameter(name);
		for (ZipEntryWithContent zipEntry : zipFileEntries) {
			if (name.equals(zipEntry.getName()))
				return zipEntry;
		}
		return null;
	}

	@Override
	public InputStream retrieveInputStreamFor(String name) {
		ZipEntryWithContent entry = getEntry(name);
		if (entry != null)
			return entry.retrieveContentAsStream();
		String message = format("Zip entry with the name %s could not be found in archive %s", name, this.fileName);
		throw new IllegalArgumentException(message);
	}

	/**
	 * @param name the name of the entry to check, should not be <code>null</code>
	 * @return <code>true</code> if an entry with the passed name is available,
	 * <code>false</code> otherwise
	 */
	public boolean hasEntry(String name) {
		for (ZipEntryWithContent zipEntry : zipFileEntries) {
			if (zipEntry.getName().equals(name))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return format("[%s, %s, %s]", version, type, crs != null ? crs.getName() : "undefiniertes Bezugssystem");
	}

	private ZipEntryWithContent getMainFile() {
		if (mainEntry != null)
			return mainEntry;
		for (ZipEntryWithContent zipEntry : zipFileEntries) {
			if (MAIN_FILE.equals(zipEntry.getName()))
				return zipEntry;
		}
		String msg = format("%s ist kein gültiges XPlanArchiv (enthält keine Datei mit Namen '%s').", this.fileName,
				MAIN_FILE);
		throw new IllegalArgumentException(msg);
	}

	private void checkNameParameter(String name) {
		if (name == null)
			throw new IllegalArgumentException("Name to detect the zip entry must not be null.");
	}

}
