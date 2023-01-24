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

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.stream.FeatureInputStream;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.slf4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;
import static org.deegree.protocol.wfs.WFSConstants.WFS_200_NS;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlParser {

	private static final Logger LOG = getLogger(XPlanGmlParser.class);

	private ICRS defaultCrs;

	private boolean fixOrientation = false;

	private XPlanGmlParser() {
	}

	public static XPlanGmlParser newParser() {
		return new XPlanGmlParser();
	}

	public XPlanGmlParser withDefaultCrs(ICRS defaultCrs) {
		this.defaultCrs = defaultCrs;
		return this;
	}

	public XPlanGmlParser withFixOrientation(boolean fixOrientation) {
		this.fixOrientation = fixOrientation;
		return this;
	}

	/**
	 * Parses a {@link XPlanFeatureCollections} from the passed {@link XPlanArchive}
	 * @param xPlanArchive containing the gml file to parse, never <code>null</code>
	 * @return the parsed XPlanFeatureCollection, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	public XPlanFeatureCollections parseXPlanFeatureCollectionAllowMultipleInstances(XPlanArchive xPlanArchive)
			throws XMLStreamException, UnknownCRSException, FeatureCollectionParseException {
		GMLStreamReader gmlStreamReader = null;
		try {
			XPlanVersion version = xPlanArchive.getVersion();
			XPlanType type = xPlanArchive.getType();
			XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(xPlanArchive.getMainFileXmlReader(), null);
			gmlStreamReader = createGmlStreamReader(xmlStream, version);
			XPlanFeatureCollections xPlanFeatureCollection = new MultipleInstanceParser().parse(gmlStreamReader,
					version, type);
			gmlStreamReader.getIdContext().resolveLocalRefs();
			return xPlanFeatureCollection;
		}
		finally {
			close(gmlStreamReader);
		}
	}

	/**
	 * Parses a {@link XPlanFeatureCollection} from the passed {@link XPlanArchive}
	 * @param xPlanArchive containing the gml file to parse, never <code>null</code>
	 * @return the parsed XPlanFeatureCollection, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	public XPlanFeatureCollection parseXPlanFeatureCollection(XPlanArchive xPlanArchive)
			throws XMLStreamException, UnknownCRSException {
		XPlanVersion version = xPlanArchive.getVersion();
		XPlanType type = xPlanArchive.getType();
		XMLStreamReader xmlStreamReader = xPlanArchive.getMainFileXmlReader();
		return parseXPlanFeatureCollection(xmlStreamReader, version, type);
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
		XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(plan);
		return parseXPlanFeatureCollection(xmlStreamReader, version, type);
	}

	/**
	 * Parses a {@link FeatureCollection} from the passed {@link XPlanArchive}
	 * @param xPlanArchive containing the gml file to parse, never <code>null</code>
	 * @return never <code>null</code>
	 * @throws XMLStreamException if the plan could not be read
	 * @throws UnknownCRSException if the CRS of a geometry in the plan is not known
	 */
	public FeatureCollection parseFeatureCollection(XPlanArchive xPlanArchive)
			throws XMLStreamException, UnknownCRSException {
		XPlanVersion version = xPlanArchive.getVersion();
		XMLStreamReader xmlStreamReader = xPlanArchive.getMainFileXmlReader();
		return parseFeatureCollection(xmlStreamReader, version);
	}

	/**
	 * Parses a {@link FeatureCollection} from the passed {@link InputStream}
	 * @param plan as InputStream, never <code>null</code>
	 * @param version of the plan, never <code>null</code>
	 * @return never <code>null</code>
	 * @throws XMLStreamException if the plan could not be read
	 * @throws UnknownCRSException if the CRS of a geometry in the plan is not known
	 */
	public FeatureCollection parseFeatureCollection(InputStream plan, XPlanVersion version)
			throws XMLStreamException, UnknownCRSException {
		XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(plan);
		return parseFeatureCollection(xmlStreamReader, version);
	}

	/**
	 * Parses a {@link FeatureCollection} from the passed {@link XMLStreamReader}
	 * @param plan as XMLStreamReader, never <code>null</code>
	 * @param version of the plan, should not be <code>null</code>
	 * @return never <code>null</code>
	 * @throws XMLStreamException if the plan could not be read
	 * @throws UnknownCRSException if the CRS of a geometry in the plan is not known
	 */
	public FeatureCollection parseFeatureCollection(XMLStreamReader plan, XPlanVersion version)
			throws XMLStreamException, UnknownCRSException {
		GMLStreamReader gmlStreamReader = null;
		try {
			XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(plan, null);
			gmlStreamReader = createGmlStreamReader(xmlStream, version);
			FeatureCollection features = parseFeatures(xmlStream, gmlStreamReader);
			gmlStreamReader.getIdContext().resolveLocalRefs();
			return features;
		}
		finally {
			close(gmlStreamReader);
		}
	}

	private XPlanFeatureCollection parseXPlanFeatureCollection(XMLStreamReader xmlStream, XPlanVersion version,
			XPlanType type) throws XMLStreamException, UnknownCRSException {
		FeatureCollection features = parseFeatureCollection(xmlStream, version);
		return new XPlanFeatureCollectionBuilder(features, type).build();
	}

	private FeatureCollection parseFeatures(XMLStreamReader xmlStream, GMLStreamReader gmlStream)
			throws XMLStreamException, UnknownCRSException {
		if (new QName(WFS_200_NS, "FeatureCollection").equals(xmlStream.getName())) {
			LOG.debug("Features embedded in wfs20:FeatureCollection");
			FeatureInputStream featuresStream = new WfsFeatureInputStream(xmlStream, gmlStream);
			return featuresStream.toCollection();
		}
		return gmlStream.readFeatureCollection();
	}

	private GMLStreamReader createGmlStreamReader(XMLStreamReader xmlStream, XPlanVersion version)
			throws XMLStreamException {
		GMLVersion gmlVersion = version.getGmlVersion();
		GeometryFactory geomFac = new GeometryFactory();
		if (fixOrientation) {
			geomFac.addInspector(new OrientationFixer());
		}
		GMLStreamReader gmlStream = createGMLStreamReader(gmlVersion, xmlStream);
		gmlStream.setDefaultCRS(defaultCrs);
		gmlStream.setGeometryFactory(geomFac);
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version);
		gmlStream.setApplicationSchema(schema);
		return gmlStream;
	}

	private static void close(GMLStreamReader gmlStreamReader) throws XMLStreamException {
		if (gmlStreamReader != null)
			gmlStreamReader.close();
	}

}
