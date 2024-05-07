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
package de.latlon.xplan.commons.archive;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import de.latlon.xplan.commons.util.XmlUtils;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.deegree.commons.utils.Pair;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.persistence.CRSManager;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.Location;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

import static de.latlon.xplan.commons.XPlanType.valueOfDefaultNull;
import static java.lang.String.format;
import static org.deegree.commons.xml.stax.XMLStreamUtils.skipStartDocument;

/**
 * Reads the XPlan GML, pareses required information.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlReader {

	private Location crsLocation;

	private XPlanVersion version;

	private XPlanType type;

	private ICRS crs;

	private List<String> districts;

	private boolean hasMultipleXPlanElements = false;

	private boolean hasVerbundenerPlanBereich;

	/**
	 * Reads the XPlan GML, pareses required information.
	 * @param entry XPlanGML to read, never <code>null</code>
	 * @return the XPlanGML as {@link MainZipEntry}, never <code>null</code>
	 * @throws XMLStreamException if the XPlanGML GML could not be parsed
	 */
	public Pair<MainZipEntry, ArchiveMetadata> createZipEntry(ArtefactEntry entry) throws XMLStreamException {
		XMLStreamReader reader = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			InputStream stream = entry.retrieveContentAsStream();
			reader = createReader(stream);
			XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(bos);

			copy(reader, writer);
		}
		finally {
			closeQuietly(reader);
		}
		ArchiveMetadata archiveMetadata = new ArchiveMetadata(version, type, crs, districts, hasVerbundenerPlanBereich,
				hasMultipleXPlanElements);
		return new Pair<>(new MainZipEntry(bos.toByteArray(), entry.getName()), archiveMetadata);
	}

	/**
	 * Reads the XPlan GML, pareses required information.
	 * @param name of the entry, never <code>null</code>
	 * @param xplanGml XPlanGML to read, never <code>null</code>
	 * @return the XPlanGML as {@link MainZipEntry}, never <code>null</code>
	 * @throws XMLStreamException if the XPlanGML GML could not be parsed
	 */
	public Pair<MainZipEntry, ArchiveMetadata> createZipEntry(String name, InputStream xplanGml)
			throws XMLStreamException {
		XMLStreamReader reader = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			reader = createReader(xplanGml);
			XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(bos);

			copy(reader, writer);
		}
		finally {
			closeQuietly(reader);
		}
		ArchiveMetadata archiveMetadata = new ArchiveMetadata(version, type, crs, districts, hasVerbundenerPlanBereich,
				hasMultipleXPlanElements);
		return new Pair<>(new MainZipEntry(bos.toByteArray(), name), archiveMetadata);
	}

	private void copy(XMLStreamReader reader, XMLStreamWriter writer) throws XMLStreamException {
		XPlanGmlWriterFilter filter = new XPlanGmlWriterFilter();
		filter.setDelegate(writer);
		writeAll(reader, filter);
		this.districts = filter.getDistricts();
		if (version == null) {
			throw new IllegalArgumentException("Could not determine version of the XPlanGML");
		}
	}

	@SuppressFBWarnings(value = "XXE_XMLSTREAMREADER")
	private XMLStreamReader createReader(InputStream stream) throws XMLStreamException, FactoryConfigurationError {
		XMLStreamReader xmlReader = XmlUtils.createXMLInputFactory().createXMLStreamReader(stream);
		skipStartDocument(xmlReader);
		return xmlReader;
	}

	private void writeAll(XMLStreamReader reader, XMLStreamWriter writer) throws XMLStreamException {
		while (reader.hasNext()) {
			write(reader, writer);
			reader.next();
		}
		write(reader, writer);
		writer.flush();
	}

	private void write(XMLStreamReader reader, XMLStreamWriter writer) throws XMLStreamException {
		switch (reader.getEventType()) {
			case XMLEvent.START_ELEMENT:
				writeStartElementWithNamespaceBindings(reader, writer);
				break;
			case XMLEvent.END_ELEMENT:
				writer.writeEndElement();
				break;
			case XMLEvent.SPACE:
			case XMLEvent.CHARACTERS:
				writer.writeCharacters(reader.getTextCharacters(), reader.getTextStart(), reader.getTextLength());
				break;
			case XMLEvent.PROCESSING_INSTRUCTION:
				writer.writeProcessingInstruction(reader.getPITarget(), reader.getPIData());
				break;
			case XMLEvent.CDATA:
				writer.writeCData(reader.getText());
				break;

			case XMLEvent.COMMENT:
				writer.writeComment(reader.getText());
				break;
			case XMLEvent.ENTITY_REFERENCE:
				writer.writeEntityRef(reader.getLocalName());
				break;
			case XMLEvent.START_DOCUMENT:
				String encoding = reader.getCharacterEncodingScheme();
				String version = reader.getVersion();

				if (encoding != null && version != null)
					writer.writeStartDocument(encoding, version);
				else if (version != null)
					writer.writeStartDocument(reader.getVersion());
				break;
			case XMLEvent.END_DOCUMENT:
				writer.writeEndDocument();
				break;
			case XMLEvent.DTD:
				writer.writeDTD(reader.getText());
				break;
		}
	}

	private void writeStartElementWithNamespaceBindings(XMLStreamReader reader, XMLStreamWriter writer)
			throws XMLStreamException {
		writeStartElement(reader, writer);
		writeNamespaces(reader, writer);
		writeAttributes(reader, writer);
	}

	private void writeStartElement(XMLStreamReader reader, XMLStreamWriter writer) throws XMLStreamException {
		String localName = reader.getLocalName();
		String namespaceURI = reader.getNamespaceURI();

		setVersion(namespaceURI);
		setType(localName);
		setCrs(reader);
		setHasVerbundenerPlanBereich(localName);

		if (namespaceURI != null && !namespaceURI.isEmpty()) {
			String prefix = reader.getPrefix();
			if (prefix != null) {
				writer.writeStartElement(prefix, localName, namespaceURI);
			}
			else {
				writer.writeStartElement(namespaceURI, localName);
			}
		}
		else {
			writer.writeStartElement(localName);
		}
	}

	private void writeNamespaces(XMLStreamReader reader, XMLStreamWriter writer) throws XMLStreamException {
		for (int namsespaceIndex = 0,
				namespaceCount = reader.getNamespaceCount(); namsespaceIndex < namespaceCount; namsespaceIndex++) {
			writer.writeNamespace(reader.getNamespacePrefix(namsespaceIndex), reader.getNamespaceURI(namsespaceIndex));
		}
	}

	private void writeAttributes(XMLStreamReader reader, XMLStreamWriter writer) throws XMLStreamException {
		for (int namsespaceIndex = 0,
				attCount = reader.getAttributeCount(); namsespaceIndex < attCount; namsespaceIndex++) {
			String attNamespace = reader.getAttributeNamespace(namsespaceIndex);
			if (attNamespace != null && !attNamespace.isEmpty()) {
				writer.writeAttribute(attNamespace, reader.getAttributeLocalName(namsespaceIndex),
						reader.getAttributeValue(namsespaceIndex));
			}
			else {
				writer.writeAttribute(reader.getAttributeLocalName(namsespaceIndex),
						reader.getAttributeValue(namsespaceIndex));
			}
		}
	}

	private void setVersion(String namespaceUri) {
		if (namespaceUri == null)
			return;
		if (version == null) {
			try {
				version = XPlanVersionUtils.determineBaseVersion(namespaceUri);
			}
			catch (IllegalArgumentException e) {
				// skip until feature with xplan namspace is found
			}
		}
	}

	private void setType(String localName) {
		XPlanType currentType = valueOfDefaultNull(localName);
		if (currentType != null) {
			if (type == null) {
				type = currentType;
			}
			else {
				hasMultipleXPlanElements = true;
			}
		}
	}

	private void setCrs(XMLStreamReader reader) {
		String srsName = reader.getAttributeValue(null, "srsName");
		if (srsName != null) {
			if (crs == null) {
				crs = CRSManager.getCRSRef(srsName);
				crsLocation = reader.getLocation();
			}
			else if (!crs.getName().equals(srsName)) {
				throwCrsInConflictException(reader.getLocation(), srsName);
			}
		}
	}

	private void setHasVerbundenerPlanBereich(String localName) {
		if ("verbundenerPlan".equals(localName) || "verbundenerPlanBereich".equals(localName)) {
			this.hasVerbundenerPlanBereich = true;
		}
	}

	private void throwCrsInConflictException(Location currentLocation, String currrentCrsName) {
		String msg = format(
				"Fehler: Dokument enthält widersprüchliche CRS-Informationen "
						+ "(srsName-Attribute): '%s' (Zeile: %d, Spalte: %d) und '%s' (Zeile: %d, Spalte: %d)",
				crs.getName(), crsLocation.getLineNumber(), crsLocation.getColumnNumber(), currrentCrsName,
				currentLocation.getLineNumber(), currentLocation.getColumnNumber());
		throw new IllegalArgumentException(msg);
	}

	private void closeQuietly(XMLStreamReader xmlReader) {
		if (xmlReader != null) {
			try {
				xmlReader.close();
			}
			catch (XMLStreamException e) {
				// nothing to do
			}
		}
	}

}
