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

import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Geometry;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamWriter;
import org.deegree.gml.GMLVersion;
import org.junit.Test;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.xmlunit.matchers.EvaluateXPathMatcher.hasXPath;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XplanGeometryTest {

	@Test
	public void testEvaluate() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XPlanGeometry expr = new XPlanGeometry(new Xpath("xplan:raeumlicherGeltungsbereich"));
		Geometry value = expr.evaluate(feature, features);
		assertNotNull(value);
	}

	@Test
	public void testEvaluateEmptyProperty() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_41);
		Feature feature = getTestFeature(features, "XP_PPO_3");
		XPlanGeometry expr = new XPlanGeometry(new Xpath("xplan:position"));
		Geometry value = expr.evaluate(feature, features);
		assertNull(value);
	}

	@Test
	public void testEvaluateCurve() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_41, "FeatureWithCurve.xml");
		Feature feature = getTestFeature(features, "BP_BaugebietsTeilFlaeche");

		XPlanGeometry expr = new XPlanGeometry(new Xpath("xplan:position"));
		Geometry value = expr.evaluate(feature, features);
		assertNotNull(value);

		String geom = writeGMLGeometry(value);

		String xPath = "count(/gml:Polygon/gml:exterior/gml:Ring/gml:curveMember/gml:Curve/gml:segments/gml:LineStringSegment[@interpolation='linear'])";
		assertThat(geom, hasXPath(xPath, is("6")).withNamespaceContext(nsContext()));

	}

	private Map<String, String> nsContext() {
		Map<String, String> nsContext = new HashMap<>();
		nsContext.put("gml", GMLVersion.GML_32.getNamespace());
		return nsContext;
	}

	private String writeGMLGeometry(Geometry value) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		XMLStreamWriter xmlStreamWriter = new IndentingXMLStreamWriter(
				XMLOutputFactory.newInstance().createXMLStreamWriter(bos));
		GMLStreamWriter gmlWriter = GMLOutputFactory.createGMLStreamWriter(GMLVersion.GML_32, xmlStreamWriter);
		gmlWriter.write(value);
		gmlWriter.close();
		xmlStreamWriter.close();
		bos.close();
		return bos.toString();
	}

}
