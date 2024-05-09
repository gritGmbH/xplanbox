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
package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import org.deegree.commons.xml.stax.XMLStreamUtils;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.GenericFeatureCollection;
import org.deegree.feature.stream.FeatureInputStream;
import org.deegree.feature.types.FeatureCollectionType;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.schema.GMLAppSchemaReader;
import org.slf4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;
import static org.deegree.protocol.wfs.WFSConstants.WFS_200_NS;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * {@link FeatureInputStream} parsing WFS 2.0 FeatureCollections.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class WfsFeatureInputStream implements FeatureInputStream {

	private static final Logger LOG = getLogger(WfsFeatureInputStream.class);

	private static final QName WFS_20_MEMBER = new QName(WFS_200_NS, "member");

	private static final QName WFS_20_ADDITIONAL = new QName(WFS_200_NS, "additionalObjects");

	private static final QName WFS_20_SIMPLEFEATURECOLLECTION = new QName(WFS_200_NS, "SimpleFeatureCollection");

	public static final String XPLAN_FEATURECOLLECTION = "XPlanAuszug";

	private final XMLStreamReader xmlStream;

	private final GMLStreamReader gmlStream;

	private XPlanVersion version;

	private Feature next;

	/**
	 * @param xmlStream to parse, never <code>null</code>
	 * @param gmlStream used to parse the XPlanGML, never <code>null</code>
	 */
	public WfsFeatureInputStream(XMLStreamReader xmlStream, GMLStreamReader gmlStream) {
		this.xmlStream = xmlStream;
		this.gmlStream = gmlStream;
		this.next = retrieveNext(xmlStream, gmlStream);
	}

	@Override
	public Iterator<Feature> iterator() {

		return new Iterator<Feature>() {
			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public Feature next() {
				if (next == null) {
					throw new NoSuchElementException();
				}
				Feature currentFeature = next;
				next = retrieveNext(xmlStream, gmlStream);
				return currentFeature;
			}
		};

	}

	@Override
	public void close() {

	}

	@Override
	public FeatureCollection toCollection() {
		FeatureCollectionType featureCollectionType = extractFcCollectionType(version);
		GenericFeatureCollection genericFeatureCollection = new GenericFeatureCollection(featureCollectionType, null,
				Collections.emptyList(), null);

		Iterator<Feature> featuresIterator = iterator();
		while (featuresIterator.hasNext()) {
			genericFeatureCollection.add(featuresIterator.next());
		}
		return genericFeatureCollection;
	}

	@Override
	public int count() {
		return 0;
	}

	private Feature retrieveNext(XMLStreamReader xmlStream, GMLStreamReader gmlStream) {
		try {
			while (xmlStream.nextTag() == START_ELEMENT) {
				QName elName = xmlStream.getName();
				if (WFS_20_MEMBER.equals(elName)) {
					xmlStream.nextTag();
					Feature feature = gmlStream.readFeature();
					determineVersion(feature.getName());
					xmlStream.nextTag();
					return feature;
				}
				else if (WFS_20_ADDITIONAL.equals(elName) || WFS_20_SIMPLEFEATURECOLLECTION.equals(elName)) {
					LOG.debug("Handling element '" + elName + "'");
					XMLStreamUtils.nextElement(xmlStream);
				}
				else {
					LOG.debug("Ignoring element '" + elName + "'");
					XMLStreamUtils.skipElement(xmlStream);
				}
			}
		}
		catch (Exception e) {
			LOG.error("Failed", e);
		}
		return null;
	}

	private void determineVersion(QName elName) {
		if (version == null) {
			try {
				version = XPlanVersionUtils.determineBaseVersion(elName);
			}
			catch (IllegalArgumentException e) {
				LOG.debug("Could not determine XPlanGML version from {}", elName);
			}
		}
	}

	private FeatureCollectionType extractFcCollectionType(XPlanVersion version) {
		GMLAppSchemaReader schemaReader = null;
		try {
			URL schemaUrl = version.getSchemaUrl();
			schemaReader = new GMLAppSchemaReader(version.getGmlVersion(), null, schemaUrl.toString());
		}
		catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return (FeatureCollectionType) schemaReader.extractAppSchema()
			.getFeatureType(new QName(version.getNamespace(), XPLAN_FEATURECOLLECTION));
	}

}
