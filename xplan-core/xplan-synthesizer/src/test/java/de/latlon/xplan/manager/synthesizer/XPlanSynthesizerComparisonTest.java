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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.commons.io.IOUtils;
import org.apache.xerces.dom.TextImpl;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.GMLStreamWriter;
import org.deegree.gml.XPlanGmlWriter;
import org.junit.Before;
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

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(JUnitParamsRunner.class)
public class XPlanSynthesizerComparisonTest {

	private XPlanSynthesizer xPlanSynthesizer;

	@Before
	public void setup() {
		SynRulesAccessor synRulesAccessor = new SynRulesAccessor();
		this.xPlanSynthesizer = new XPlanSynthesizer(synRulesAccessor);
	}

	@Parameters({ "xplan41/BP2070", "xplan41/BP2135", "xplan41/LA22", "xplan41/LA67", "xplan50/BP2070",
			"xplan50/BP2135", "xplan50/LA22", "xplan50/LA67", "xplan51/BP2070", "xplan51/BP2135", "xplan51/LA22",
			"xplan51/LA67", "xplan52/BP2070", "xplan52/BP2135", "xplan52/LA22", "xplan52/LA67" })
	@Test
	public void test(String archiveName) throws Exception {
		XPlanFeatureCollection xplanFc = TestFeaturesUtils.getTestFeatureCollection(archiveName + ".zip");
		FeatureCollection synthesizedFeatureCollection = xPlanSynthesizer.synthesize(xplanFc.getVersion(), xplanFc);
		String synthesizedFeatures = writeFeatures(xplanFc.getVersion(), synthesizedFeatureCollection);

		String expectedFeatureCollection = IOUtils.toString(
				XPlanSynthesizerComparisonTest.class.getResourceAsStream("plans/" + archiveName + ".xml"),
				StandardCharsets.UTF_8);
		assertThat(synthesizedFeatures, CompareMatcher.isSimilarTo(expectedFeatureCollection)
			.ignoreWhitespace()
			.ignoreComments()
			.ignoreElementContentWhitespace()
			.withDifferenceEvaluator(
					(comparison, comparisonResult) -> ignoreGmlIdsAndXpPlanNameAndPrefix(comparison, comparisonResult))
			.withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byName)));

	}

	private ComparisonResult ignoreGmlIdsAndXpPlanNameAndPrefix(Comparison comparison,
			ComparisonResult comparisonResult) {
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
		if (comparisonResult == ComparisonResult.DIFFERENT && comparison.getType() == ComparisonType.NAMESPACE_PREFIX) {
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
			gmlStreamWriter.getNamespaceBindings().put("xplansyn", XPlanVersion.XPLAN_SYN.getNamespace());
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

}
