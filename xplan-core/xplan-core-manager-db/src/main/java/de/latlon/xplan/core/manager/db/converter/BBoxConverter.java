/*-
 * #%L
 * xplan-core-manager-db - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplan.core.manager.db.converter;

import de.latlon.xplan.validator.web.shared.XPlanEnvelope;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.GeometryFactory;
import org.deegree.geometry.io.WKTReader;
import org.deegree.geometry.io.WKTWriter;
import org.locationtech.jts.io.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;

public class BBoxConverter implements AttributeConverter<XPlanEnvelope, String> {

	private static final Logger LOG = LoggerFactory.getLogger(BBoxConverter.class);

	private static final String CRS = "EPSG:4326";

	@Override
	public String convertToDatabaseColumn(XPlanEnvelope entityValue) {
		if (entityValue == null)
			return null;
		try {
			String crs = entityValue.getCrs() != null ? entityValue.getCrs() : CRS;
			Envelope bbox = new GeometryFactory().createEnvelope(entityValue.getMinX(), entityValue.getMinY(),
					entityValue.getMaxX(), entityValue.getMaxY(), CRSManager.lookup(crs));
			return WKTWriter.write(bbox);
		}
		catch (UnknownCRSException e) {
			LOG.error("Could not create WKT from " + entityValue, e);
			return null;
		}
	}

	@Override
	public XPlanEnvelope convertToEntityAttribute(String bboxAsWkt) {
		if (bboxAsWkt == null || bboxAsWkt.isEmpty())
			return null;
		try {
			WKTReader reader = new WKTReader(CRSManager.lookup(CRS));
			Geometry geometry = reader.read(bboxAsWkt);
			Envelope envelope = geometry.getEnvelope();
			return new XPlanEnvelope(envelope.getMin().get0(), envelope.getMin().get1(), envelope.getMax().get0(),
					envelope.getMax().get1(), CRS);
		}
		catch (UnknownCRSException | ParseException e) {
			LOG.error("Could not create envelope from " + bboxAsWkt, e);
			return null;
		}
	}

}
