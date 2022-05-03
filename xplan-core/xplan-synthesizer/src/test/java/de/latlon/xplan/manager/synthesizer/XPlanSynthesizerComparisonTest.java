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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.commons.io.IOUtils;
import org.apache.xerces.dom.TextImpl;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLStreamWriter;
import org.deegree.gml.GMLVersion;
import org.deegree.gml.XPlanGmlWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.ComparisonType;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.ElementSelectors;
import org.xmlunit.matchers.CompareMatcher;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(JUnitParamsRunner.class)
public class XPlanSynthesizerComparisonTest {

	@Parameters({ "xplan41/BP2070", "xplan41/BP2135", "xplan41/LA22", "xplan41/LA67", "xplan50/BP2070",
			"xplan50/BP2135", "xplan50/LA22", "xplan50/LA67", "xplan51/BP2070", "xplan51/BP2135", "xplan51/LA22",
			"xplan51/LA67", "xplan52/BP2070", "xplan52/BP2135", "xplan52/LA22", "xplan52/LA67" })
	@Test
	public void test(String archiveName) throws Exception {

		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

		XPlanArchive archive = getTestArchive(archiveName + ".zip");
		XPlanFeatureCollection xplanFc = readFeatures(archive);
		FeatureCollection synthesizedFeatureCollection = xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		String synthesizedFeatures = writeFeatures(archive.getVersion(), synthesizedFeatureCollection);

		String expectedFeatureCollection = IOUtils.toString(
				XPlanSynthesizerComparisonTest.class.getResourceAsStream("plans/" + archiveName + ".xml"),
				StandardCharsets.UTF_8);
		assertThat(synthesizedFeatures, CompareMatcher.isSimilarTo(expectedFeatureCollection).ignoreWhitespace()
				.ignoreComments().ignoreElementContentWhitespace()
				.withDifferenceEvaluator(
						(comparison, comparisonResult) -> ignoreGmlIdsAndXpPlanName(comparison, comparisonResult))
				.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName)));

	}

	private ComparisonResult ignoreGmlIdsAndXpPlanName(Comparison comparison, ComparisonResult comparisonResult) {
		if (comparisonResult == ComparisonResult.EQUAL)
			return comparisonResult;
		Node controlNode = comparison.getControlDetails().getTarget();
		if (controlNode instanceof Attr) {
			Attr attr = (Attr) controlNode;
			if ("http://www.opengis.net/gml/3.2".equals(attr.getNamespaceURI()))
				return ComparisonResult.SIMILAR;
		}
		else if (controlNode instanceof TextImpl) {
			TextImpl textImpl = (TextImpl) controlNode;
			Node parentNode = textImpl.getParentNode();
			if ("xpPlanName".equals(parentNode.getLocalName()) && textImpl.getNodeValue() != null
					&& textImpl.getNodeValue().startsWith("Unbenannter XPlan"))
				return ComparisonResult.SIMILAR;
		}
		if (comparison.getType() == ComparisonType.CHILD_NODELIST_SEQUENCE) {
			return ComparisonResult.SIMILAR;
		}
		return comparisonResult;
	}

	private String writeFeatures(XPlanVersion version, FeatureCollection synthesizedFeatures)
			throws TransformationException, XMLStreamException, UnknownCRSException, IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		XMLStreamWriter xmlStream = null;
		GMLStreamWriter gmlStreamWriter = null;
		try {
			xmlStream = XMLOutputFactory.newFactory().createXMLStreamWriter(os);
			gmlStreamWriter = new XPlanGmlWriter(version, new IndentingXMLStreamWriter(xmlStream));
			gmlStreamWriter.write(synthesizedFeatures);
		}
		finally {
			if (gmlStreamWriter != null)
				gmlStreamWriter.close();
			if (xmlStream != null)
				xmlStream.close();
			os.close();
		}
		return os.toString();
	}

	private XPlanFeatureCollection readFeatures(XPlanArchive archive) throws XMLStreamException, UnknownCRSException {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(archive.getVersion());
		ICRS crs = archive.getCrs();

		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(archive.getMainFileXmlReader(), null);
		GMLStreamReader gmlStream = createGmlStreamReader(archive, crs, schema, xmlStream);
		FeatureCollection features = (FeatureCollection) gmlStream.readFeature();
		return new XPlanFeatureCollectionBuilder(features, archive.getType()).build();
	}

	private GMLStreamReader createGmlStreamReader(XPlanArchive archive, ICRS crs, AppSchema schema,
			XMLStreamReaderWrapper xmlStream) throws XMLStreamException {
		GMLVersion gmlVersion = archive.getVersion().getGmlVersion();
		GeometryFactory geomFac = new GeometryFactory();
		GMLStreamReader gmlStream = createGMLStreamReader(gmlVersion, xmlStream);
		gmlStream.setDefaultCRS(crs);
		gmlStream.setGeometryFactory(geomFac);
		gmlStream.setApplicationSchema(schema);
		gmlStream.setSkipBrokenGeometries(true);
		return gmlStream;
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

}
