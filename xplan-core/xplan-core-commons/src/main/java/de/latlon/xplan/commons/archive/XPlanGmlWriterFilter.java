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

import org.apache.axiom.om.util.XMLStreamWriterFilterBase;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlWriterFilter extends XMLStreamWriterFilterBase {

	private String currentPath = "";

	private final List<String> districts = new ArrayList<>();

	@Override
	public void writeStartElement(String localName) throws XMLStreamException {
		this.currentPath += "/" + localName;
		super.writeStartElement(localName);
	}

	@Override
	public void writeStartElement(String namespaceURI, String localName) throws XMLStreamException {
		this.currentPath += "/" + localName;
		super.writeStartElement(namespaceURI, localName);
	}

	@Override
	public void writeStartElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
		this.currentPath += "/" + localName;
		super.writeStartElement(prefix, localName, namespaceURI);
	}

	@Override
	public void writeEndElement() throws XMLStreamException {
		this.currentPath = this.currentPath.substring(0, this.currentPath.lastIndexOf("/"));
		super.writeEndElement();
	}

	@Override
	protected String xmlData(String s) {
		if (this.currentPath.endsWith("/gemeinde/XP_Gemeinde/ortsteilName")) {
			this.districts.add(s);
		}
		if (this.currentPath.endsWith("ortsteil")) {
			this.districts.add(s);
		}
		return s;
	}

	/**
	 * @return the districts (if available and the filter was already applied), otherwise
	 * <code>null</code>
	 */
	public List<String> getDistricts() {
		return districts;
	}

}
