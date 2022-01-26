/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;

import javax.xml.stream.XMLStreamException;

import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlParser {

	/**
	 * @param xPlanArchive containing the gml file to parse, never <code>null</code>
	 * @return the parsed XPlanFeatureCollection, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	public XPlanFeatureCollection parseFeatureCollection(XPlanArchive xPlanArchive)
			throws XMLStreamException, UnknownCRSException {
		return parseFeatureCollection(xPlanArchive, xPlanArchive.getCrs());
	}

	/**
	 * @param xPlanArchive containing the gml file to parse, never <code>null</code>
	 * @param defaultCrs of the geometries if not specified, may be <code>null</code>
	 * @return the parsed XPlanFeatureCollection, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	public XPlanFeatureCollection parseFeatureCollection(XPlanArchive xPlanArchive, ICRS defaultCrs)
			throws XMLStreamException, UnknownCRSException {
		XPlanVersion version = xPlanArchive.getVersion();
		XPlanAde ade = xPlanArchive.getAde();
		XPlanType type = xPlanArchive.getType();
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(xPlanArchive.getMainFileXmlReader(), null);
		return parseFeatureCollection(version, type, ade, defaultCrs, xmlStream);
	}

	private XPlanFeatureCollection parseFeatureCollection(XPlanVersion version, XPlanType type, XPlanAde ade,
			ICRS defaultCrs, XMLStreamReaderWrapper xmlStream) throws XMLStreamException, UnknownCRSException {
		GMLStreamReader gmlStream = createGmlStreamReader(version, ade, defaultCrs, xmlStream);
		FeatureCollection features = (FeatureCollection) gmlStream.readFeature();
		return new XPlanFeatureCollectionBuilder(features, type).build();
	}

	private GMLStreamReader createGmlStreamReader(XPlanVersion version, XPlanAde ade, ICRS defaultCrs,
			XMLStreamReaderWrapper xmlStream) throws XMLStreamException {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, ade);
		GMLVersion gmlVersion = version.getGmlVersion();
		GeometryFactory geomFac = new GeometryFactory();
		GMLStreamReader gmlStream = createGMLStreamReader(gmlVersion, xmlStream);
		gmlStream.setDefaultCRS(defaultCrs);
		gmlStream.setGeometryFactory(geomFac);
		gmlStream.setApplicationSchema(schema);
		return gmlStream;
	}

}
