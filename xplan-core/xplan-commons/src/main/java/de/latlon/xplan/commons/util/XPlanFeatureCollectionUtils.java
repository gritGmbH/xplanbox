/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.commons.util;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.parseFeatureCollection;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanFeatureCollectionUtils {

	private XPlanFeatureCollectionUtils() {
	}

	/**
	 * Reads the {@link XPlanFeatureCollection} from the passed {@link XPlanArchive}
	 * @param archive to parse, never <code>null</code>
	 * @return never <code>null</code>
	 * @throws XMLStreamException if the plan could not be read
	 * @throws UnknownCRSException if the CRS of a geometry in the plan is not known
	 */
	public static XPlanFeatureCollection parseXPlanFeatureCollection(XPlanArchive archive)
			throws XMLStreamException, UnknownCRSException {
		XMLStreamReader plan = archive.getMainFileXmlReader();
		XPlanType type = archive.getType();
		XPlanVersion version = archive.getVersion();
		AppSchema appSchema = XPlanSchemas.getInstance().getAppSchema(version, archive.getAde());
		return parseXPlanFeatureCollection(plan, type, version, appSchema);
	}

	/**
	 * Reads the {@link XPlanFeatureCollection} from the passed {@link InputStream}
	 * @param inputStream to parse, never <code>null</code>
	 * @param type of the plan, never <code>null</code>
	 * @param version of the plan, never <code>null</code>
	 * @param appSchema describing the plan, never <code>null</code>
	 * @return never <code>null</code>
	 * @throws XMLStreamException if the plan could not be read
	 * @throws UnknownCRSException if the CRS of a geometry in the plan is not known
	 */
	public static XPlanFeatureCollection parseXPlanFeatureCollection(InputStream inputStream, XPlanType type,
			XPlanVersion version, AppSchema appSchema) throws XMLStreamException, UnknownCRSException {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLStreamReader xmlStreamReader = null;
		try {
			xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
			return parseXPlanFeatureCollection(xmlStreamReader, type, version, appSchema);
		}
		finally {
			if (xmlStreamReader != null)
				xmlStreamReader.close();
		}
	}

	private static XPlanFeatureCollection parseXPlanFeatureCollection(XMLStreamReader plan, XPlanType type,
			XPlanVersion version, AppSchema appSchema) throws XMLStreamException, UnknownCRSException {
		FeatureCollection xplanFeatures = parseFeatureCollection(plan, version, appSchema);
		return new XPlanFeatureCollectionBuilder(xplanFeatures, type).build();
	}

}
