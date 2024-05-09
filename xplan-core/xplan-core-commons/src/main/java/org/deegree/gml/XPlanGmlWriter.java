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
package org.deegree.gml;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.gml.feature.GMLFeatureWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlWriter extends GMLStreamWriter {

	private final XPlanVersion xPlanVersion;

	/**
	 * Creates a new {@link GMLStreamWriter} instance.
	 * @param xPlanVersion {@link XPlanVersion} of the output, must not be
	 * <code>null</code>
	 * @param xmlStream XML stream used to write the output, must not be <code>null</code>
	 * @throws XMLStreamException
	 */
	public XPlanGmlWriter(XPlanVersion xPlanVersion, XMLStreamWriter xmlStream) throws XMLStreamException {
		super(xPlanVersion.getGmlVersion(), xmlStream);
		this.xPlanVersion = xPlanVersion;
		getNamespaceBindings().put("xplan", xPlanVersion.getNamespace());
		getNamespaceBindings().put("gml", xPlanVersion.getGmlVersion().getNamespace());
	}

	@Override
	public GMLFeatureWriter getFeatureWriter() {
		return new XPlanGmlFeatureWriter(this, xPlanVersion);
	}

}
