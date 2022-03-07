package de.latlon.xplan.validator.cli.domain;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanWithFeatureCollection implements SemanticValidableXPlanArchive {

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
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InputStream getMainFileInputStream() {
		return new ByteArrayInputStream(data);
	}

}
