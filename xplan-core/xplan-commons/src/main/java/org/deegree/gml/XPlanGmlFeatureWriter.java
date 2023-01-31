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
package org.deegree.gml;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.GenericFeatureCollection;
import org.deegree.gml.feature.GMLFeatureWriter;

import javax.xml.stream.XMLStreamException;
import java.util.UUID;

import static org.deegree.commons.xml.CommonNamespaces.XLNNS;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlFeatureWriter extends GMLFeatureWriter {

	private final XPlanVersion xPlanVersion;

	/**
	 * Creates a new {@link GMLFeatureWriter} instance.
	 * @param gmlStreamWriter GML stream writer, must not be <code>null</code>
	 */
	public XPlanGmlFeatureWriter(GMLStreamWriter gmlStreamWriter, XPlanVersion xPlanVersion) {
		super(gmlStreamWriter);
		this.xPlanVersion = xPlanVersion;
	}

	@Override
	public void export(Feature feature) throws XMLStreamException, UnknownCRSException, TransformationException {
		if (feature instanceof GenericFeatureCollection) {
			writeStartElementWithNS(xPlanVersion.getNamespace(), "XPlanAuszug");
			String id = getOrCreateId(feature);
			GMLVersion gmlVersion = xPlanVersion.getGmlVersion();
			writeAttributeWithNS(gmlVersion.getNamespace(), "id", id);

			for (Feature member : ((FeatureCollection) feature)) {
				String memberFid = member.getId();
				writeStartElementWithNS(gmlNs, "featureMember");
				if (memberFid != null && referenceExportStrategy.isObjectExported(memberFid)) {
					writeAttributeWithNS(XLNNS, "href", "#" + memberFid);
				}
				else {
					export(member, getResolveStateForNextLevel(referenceExportStrategy.getResolveOptions()));
				}
				writer.writeEndElement();
			}
			writer.writeEndElement();
		}
		else {
			super.export(feature);
		}
	}

	private String getOrCreateId(Feature feature) {
		if (feature.getId() != null) {
			return feature.getId();
		}
		return "XPLANAUSZUG_" + UUID.randomUUID();
	}

}
