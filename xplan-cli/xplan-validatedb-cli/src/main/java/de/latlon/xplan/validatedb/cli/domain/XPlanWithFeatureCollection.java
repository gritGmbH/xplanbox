/*-
 * #%L
 * xplan-evaluation-schema-synchronize-cli - Datenbankschema für die Auswertung der XPlanGML-Daten
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
package de.latlon.xplan.validatedb.cli.domain;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 5.0
 */
public class XPlanWithFeatureCollection implements SemanticValidableXPlanArchive {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanWithFeatureCollection.class);

	private String id;

	private String xp_version;

	private String name;

	private String district;

	private byte[] data;

	private String filename;

	public XPlanWithFeatureCollection() {

	}

	public XPlanWithFeatureCollection(String id, String xp_version, String name, String district, String filename,
			byte[] data) {
		this.id = id;
		this.xp_version = xp_version;
		this.name = name;
		this.district = district;
		this.filename = filename;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public String getXp_version() {
		return xp_version;
	}

	public String getName() {
		return name;
	}

	public String getDistrict() {
		return district;
	}

	public byte[] getData() {
		return data;
	}

	public String getFilename() {
		return filename;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setXp_version(String xp_version) {
		this.xp_version = xp_version;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "XPlanWithFeatureCollection{" + "id='" + id + '\'' + ", xp_version='" + xp_version + '\'' + ", name='"
				+ name + '\'' + ", district='" + district + '\'' + '}';
	}

	@Override
	public XPlanVersion getVersion() {
		return XPlanVersion.valueOf(xp_version);
	}

	@Override
	public XMLStreamReader getMainFileXmlReader() {
		try {
			GZIPInputStream is = new GZIPInputStream(new ByteArrayInputStream(data));
			XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(is);
			return xmlStreamReader;
		}
		catch (XMLStreamException | IOException e) {
			LOG.error("Could not create XMLStreamReader from data.");
		}
		return null;
	}

	@Override
	public InputStream getMainFileInputStream() {
		try {
			return new GZIPInputStream(new ByteArrayInputStream(data));
		}
		catch (IOException e) {
			LOG.error("Could not create InputStream from data.");
		}
		return null;
	}

}
