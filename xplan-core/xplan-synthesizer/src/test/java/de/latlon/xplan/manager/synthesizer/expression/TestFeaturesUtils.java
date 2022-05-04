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

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.synthesizer.XpPpoLookup;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TestFeaturesUtils {

	public static FeatureCollection getTestFeatures(XPlanVersion version) {
		FeatureCollection fc = load(version);
		XpPpoLookup.init(fc);
		return fc;
	}

	public static FeatureCollection getTestFeatures(XPlanVersion version, String resourceName) {
		FeatureCollection fc = load(version, resourceName);
		XpPpoLookup.init(fc);
		return fc;
	}

	public static Feature getTestFeature(FeatureCollection fc, String gmlId) {
		for (Feature f : fc) {
			if (gmlId.equals(f.getId())) {
				return f;
			}
		}
		return null;
	}

	private static FeatureCollection load(XPlanVersion version) {
		switch (version) {
		case XPLAN_40:
			return load(version, "xplan40.xml");
		case XPLAN_41:
			return load(version, "xplan41.xml");
		case XPLAN_50:
			return load(version, "xplan50.xml");
		case XPLAN_53:
			return load(version, "xplan53.xml");
		}
		throw new IllegalArgumentException();
	}

	private static FeatureCollection load(XPlanVersion version, String resource) {
		InputStream is = null;
		XMLStreamReader xmlReader = null;
		GMLStreamReader gmlReader = null;
		try {
			is = TestFeaturesUtils.class.getResourceAsStream(resource);
			xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(is);
			gmlReader = GMLInputFactory.createGMLStreamReader(version.getGmlVersion(), xmlReader);
			gmlReader.setApplicationSchema(XPlanSchemas.getInstance().getAppSchema(version));
			FeatureCollection fc = gmlReader.readFeatureCollection();
			gmlReader.getIdContext().resolveLocalRefs();
			return fc;
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		finally {
			try {
				if (gmlReader != null) {
					gmlReader.close();
				}
				if (xmlReader != null) {
					xmlReader.close();
				}
			}
			catch (Exception e) {
				// nothing to do
			}
			closeQuietly(is);
		}
	}

}
