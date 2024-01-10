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
package de.latlon.xplan.manager.metadata;

import org.deegree.commons.xml.stax.XMLStreamUtils;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Properties;

import static org.deegree.commons.xml.stax.XMLStreamUtils.copy;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ServiceMetadataDocumentWriter {

	private final byte[] template;

	public ServiceMetadataDocumentWriter(byte[] template) {
		this.template = template;
	}

	public void writeServiceMetadataDocument(Properties properties, OutputStream out) throws XMLStreamException {
		XMLStreamWriter xmlStreamWriter = null;
		XMLStreamReader xmlStreamReader = null;
		try {
			xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(out);
			xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(new ByteArrayInputStream(template));

			TemplateXmlStreamWriterFilter templateWriterFilter = new TemplateXmlStreamWriterFilter(properties);
			templateWriterFilter.setDelegate(xmlStreamWriter);
			copy(templateWriterFilter, xmlStreamReader);
		}
		finally {
			closeQuietly(xmlStreamReader, xmlStreamWriter);
		}

	}

	private void closeQuietly(XMLStreamReader xmlStreamReader, XMLStreamWriter xmlStreamWriter) {
		XMLStreamUtils.closeQuietly(xmlStreamReader);
		if (xmlStreamWriter != null) {
			try {
				xmlStreamWriter.close();
			}
			catch (XMLStreamException e) {
				// quiet...
			}
		}
	}

}
