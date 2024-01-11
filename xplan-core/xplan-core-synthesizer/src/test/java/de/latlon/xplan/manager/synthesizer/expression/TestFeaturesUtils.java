/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import java.io.InputStream;

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
				TestFeaturesUtils.class.getResourceAsStream("/testdata/" + resourceName));
		return XPlanGmlParserBuilder.newBuilder().build().parseXPlanFeatureCollection(archive);
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
			default:
				throw new IllegalArgumentException();
		}
	}

	public static FeatureCollection load(XPlanVersion version, String resource) throws Exception {
		try (InputStream is = TestFeaturesUtils.class.getResourceAsStream(resource)) {
			return XPlanGmlParserBuilder.newBuilder().build().parseFeatureCollection(is, version);
		}
	}

}
