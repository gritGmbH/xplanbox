/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

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
	public XPlanFeatureCollection parseXPlanFeatureCollection(XPlanArchive xPlanArchive)
			throws XMLStreamException, UnknownCRSException {
		return parseXPlanFeatureCollection(xPlanArchive, xPlanArchive.getCrs());
	}

	/**
	 * @param xPlanArchive containing the gml file to parse, never <code>null</code>
	 * @param fixOrientation <code>true</code> if the orientation should be fixed,
	 * <code>false</code> otherwise
	 * @return the parsed XPlanFeatureCollection, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	public XPlanFeatureCollection parseXPlanFeatureCollection(XPlanArchive xPlanArchive, boolean fixOrientation)
			throws XMLStreamException, UnknownCRSException {
		return parseXPlanFeatureCollection(xPlanArchive, xPlanArchive.getCrs(), fixOrientation);
	}

	/**
	 * @param xPlanArchive containing the gml file to parse, never <code>null</code>
	 * @param defaultCrs of the geometries if not specified, may be <code>null</code>
	 * @return the parsed XPlanFeatureCollection, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	public XPlanFeatureCollection parseXPlanFeatureCollection(XPlanArchive xPlanArchive, ICRS defaultCrs)
			throws XMLStreamException, UnknownCRSException {
		return parseXPlanFeatureCollection(xPlanArchive, defaultCrs, false);
	}

	/**
	 * @param xPlanArchive containing the gml file to parse, never <code>null</code>
	 * @param defaultCrs of the geometries if not specified, may be <code>null</code>
	 * @param fixOrientation <code>true</code> if the orientation should be fixed, *
	 * <code>false</code> otherwise
	 * @return the parsed XPlanFeatureCollection, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	public XPlanFeatureCollection parseXPlanFeatureCollection(XPlanArchive xPlanArchive, ICRS defaultCrs,
			boolean fixOrientation) throws XMLStreamException, UnknownCRSException {
		XPlanVersion version = xPlanArchive.getVersion();
		XPlanAde ade = xPlanArchive.getAde();
		XPlanType type = xPlanArchive.getType();
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(xPlanArchive.getMainFileXmlReader(), null);
		return parseXPlanFeatureCollection(version, type, ade, defaultCrs, xmlStream, fixOrientation);
	}

	/**
	 * @param xPlanArchive containing the gml file to parse, never <code>null</code>
	 * @return the parsed XPlanFeatureCollection, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	public XPlanFeatureCollections parseXPlanFeatureCollectionAllowMultipleInstances(XPlanArchive xPlanArchive)
			throws XMLStreamException, UnknownCRSException, FeatureCollectionParseException {
		return parseXPlanFeatureCollectionAllowMultipleInstances(xPlanArchive, null, false);
	}

	/**
	 * @param xPlanArchive containing the gml file to parse, never <code>null</code>
	 * @param defaultCrs of the geometries if not specified, may be <code>null</code>
	 * @param fixOrientation <code>true</code> if the orientation should be fixed, *
	 * <code>false</code> otherwise
	 * @return the parsed XPlanFeatureCollection, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	public XPlanFeatureCollections parseXPlanFeatureCollectionAllowMultipleInstances(XPlanArchive xPlanArchive,
			ICRS defaultCrs, boolean fixOrientation)
			throws XMLStreamException, UnknownCRSException, FeatureCollectionParseException {
		XPlanVersion version = xPlanArchive.getVersion();
		XPlanType type = xPlanArchive.getType();
		XPlanAde ade = xPlanArchive.getAde();
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(xPlanArchive.getMainFileXmlReader(), null);
		GMLStreamReader gmlStream = createGmlStreamReader(version, ade, defaultCrs, xmlStream, fixOrientation);
		XPlanFeatureCollections parse = new MultipleInstanceParser().parse(gmlStream, version, type);
		gmlStream.getIdContext().resolveLocalRefs();
		return parse;
	}

	/**
	 * Reads the {@link XPlanFeatureCollection} from the passed {@link InputStream}
	 * @param plan to parse, never <code>null</code>
	 * @param type of the plan, never <code>null</code>
	 * @param version of the plan, never <code>null</code>
	 * @return never <code>null</code>
	 * @throws XMLStreamException if the plan could not be read
	 * @throws UnknownCRSException if the CRS of a geometry in the plan is not known
	 */
	public XPlanFeatureCollection parseXPlanFeatureCollection(InputStream plan, XPlanVersion version, XPlanType type)
			throws XMLStreamException, UnknownCRSException {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLStreamReader xmlStreamReader = null;
		try {
			xmlStreamReader = xmlInputFactory.createXMLStreamReader(plan);
			return parseXPlanFeatureCollection(xmlStreamReader, type, version);
		}
		finally {
			if (xmlStreamReader != null)
				xmlStreamReader.close();
		}
	}

	/**
	 * Parses a FeatureCollection from the passed stream
	 * @param plan as XMLStreamReader, never <code>null</code>
	 * @param version of the plan, never <code>null</code>
	 * @return never <code>null</code>
	 * @throws XMLStreamException if the plan could not be read
	 * @throws UnknownCRSException if the CRS of a geometry in the plan is not known
	 */
	public FeatureCollection parseFeatureCollection(XMLStreamReader plan, XPlanVersion version)
			throws XMLStreamException, UnknownCRSException {
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(plan, null);
		GMLStreamReader gmlStreamReader = createGmlStreamReader(version, null, null, xmlStream);
		FeatureCollection features = gmlStreamReader.readFeatureCollection();
		gmlStreamReader.getIdContext().resolveLocalRefs();
		return features;
	}

	/**
	 * Parses a FeatureCollection from the passed stream
	 * @param plan as InputStream, never <code>null</code>
	 * @param version of the plan, never <code>null</code>
	 * @return never <code>null</code>
	 * @throws XMLStreamException if the plan could not be read
	 * @throws UnknownCRSException if the CRS of a geometry in the plan is not known
	 */
	public FeatureCollection parseFeatureCollection(InputStream plan, XPlanVersion version)
			throws XMLStreamException, UnknownCRSException {
		XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(plan);
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(xmlStreamReader, null);
		GMLStreamReader gmlStreamReader = createGmlStreamReader(version, null, null, xmlStream);
		FeatureCollection features = gmlStreamReader.readFeatureCollection();
		gmlStreamReader.getIdContext().resolveLocalRefs();
		return features;
	}

	private XPlanFeatureCollection parseXPlanFeatureCollection(XMLStreamReader plan, XPlanType type,
			XPlanVersion version) throws XMLStreamException, UnknownCRSException {
		FeatureCollection xplanFeatures = parseFeatureCollection(plan, version);
		return new XPlanFeatureCollectionBuilder(xplanFeatures, type).build();
	}

	private XPlanFeatureCollection parseXPlanFeatureCollection(XPlanVersion version, XPlanType type, XPlanAde ade,
			ICRS defaultCrs, XMLStreamReaderWrapper xmlStream, boolean fixOrientation)
			throws XMLStreamException, UnknownCRSException {
		GMLStreamReader gmlStream = createGmlStreamReader(version, ade, defaultCrs, xmlStream, fixOrientation);
		FeatureCollection features = gmlStream.readFeatureCollection();
		return new XPlanFeatureCollectionBuilder(features, type).build();
	}

	private GMLStreamReader createGmlStreamReader(XPlanVersion version, XPlanAde ade, ICRS defaultCrs,
			XMLStreamReaderWrapper xmlStream, boolean fixOrientation) throws XMLStreamException {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, ade);
		GMLVersion gmlVersion = version.getGmlVersion();
		GeometryFactory geomFac = new GeometryFactory();
		if (fixOrientation) {
			geomFac.addInspector(new OrientationFixer());
		}
		GMLStreamReader gmlStream = createGMLStreamReader(gmlVersion, xmlStream);
		gmlStream.setDefaultCRS(defaultCrs);
		gmlStream.setGeometryFactory(geomFac);
		gmlStream.setApplicationSchema(schema);
		return gmlStream;

	}

	private GMLStreamReader createGmlStreamReader(XPlanVersion version, XPlanAde ade, ICRS defaultCrs,
			XMLStreamReaderWrapper xmlStream) throws XMLStreamException {
		return createGmlStreamReader(version, ade, defaultCrs, xmlStream, false);
	}

}
