/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TestFeaturesUtils {

	public static FeatureCollection load(String resourceName) throws Exception {
		XPlanFeatureCollection testFeatureCollection = getTestFeatureCollection(resourceName);
		return testFeatureCollection.getFeatures();
	}

	public static XPlanFeatureCollection getTestFeatureCollection(String resourceName) throws Exception {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(resourceName,
				ResourceAccessor.readResourceStream(resourceName));
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(archive.getVersion());
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(archive.getMainFileXmlReader(), null);
		GMLStreamReader gmlStream = createGmlStreamReader(archive.getVersion(), schema, xmlStream);
		FeatureCollection features = (FeatureCollection) gmlStream.readFeature();
		return new XPlanFeatureCollectionBuilder(features, archive.getType()).build();
	}

	public static Feature getTestFeature(FeatureCollection fc, String gmlId) {
		for (Feature f : fc) {
			if (gmlId.equals(f.getId())) {
				return f;
			}
		}
		return null;
	}

	public static FeatureCollection load(XPlanVersion version) throws Exception {
		switch (version) {
			case XPLAN_40:
				return load(version, "xplan40.xml");
			case XPLAN_41:
				return load(version, "xplan41.xml");
			case XPLAN_50:
				return load(version, "xplan50.xml");
			case XPLAN_53:
				return load(version, "xplan53.xml");
			case XPLAN_60:
				return load(version, "xplan60.xml");
		}
		throw new IllegalArgumentException();
	}

	public static FeatureCollection load(XPlanVersion version, String resource) throws Exception {
		InputStream is = null;
		XMLStreamReader xmlReader = null;
		GMLStreamReader gmlReader = null;
		try {
			is = TestFeaturesUtils.class.getResourceAsStream(resource);
			xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(is);
			gmlReader = createGmlStreamReader(version, XPlanSchemas.getInstance().getAppSchema(version), xmlReader);
			FeatureCollection fc = gmlReader.readFeatureCollection();
			gmlReader.getIdContext().resolveLocalRefs();
			return fc;
		}
		finally {
			if (gmlReader != null) {
				gmlReader.close();
			}
			if (xmlReader != null) {
				xmlReader.close();
			}
			closeQuietly(is);
		}
	}

	private static GMLStreamReader createGmlStreamReader(XPlanVersion xplanVersion, AppSchema schema,
			XMLStreamReader xmlStream) throws XMLStreamException {
		GMLVersion gmlVersion = xplanVersion.getGmlVersion();
		GeometryFactory geomFac = new GeometryFactory();
		GMLStreamReader gmlStream = createGMLStreamReader(gmlVersion, xmlStream);
		gmlStream.setGeometryFactory(geomFac);
		gmlStream.setApplicationSchema(schema);
		gmlStream.setSkipBrokenGeometries(true);
		return gmlStream;
	}

}
