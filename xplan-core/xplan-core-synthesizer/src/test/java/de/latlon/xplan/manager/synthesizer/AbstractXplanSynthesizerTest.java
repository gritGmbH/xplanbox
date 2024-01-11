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

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamWriter;
import org.junit.jupiter.api.BeforeEach;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.deegree.gml.GMLVersion.GML_32;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class AbstractXplanSynthesizerTest {

	protected XPlanSynthesizer xPlanSynthesizer;

	private AppSchema synSchema;

	@BeforeEach
	public void setup() {
		synSchema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
		SynRulesAccessor synRulesAccessor = new SynRulesAccessor();
		this.xPlanSynthesizer = new XPlanSynthesizer(synRulesAccessor);
	}

	protected Map<String, String> nsContext() {
		Map<String, String> nsContext = new HashMap<>();
		nsContext.put("xplansyn", XPlanVersion.XPLAN_SYN.getNamespace());
		return nsContext;
	}

	protected FeatureCollection createSynFeatures(String archiveName)
			throws IOException, XMLStreamException, UnknownCRSException {
		XPlanArchive archive = getTestArchive(archiveName);
		XPlanFeatureCollection xplanFc = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(archive);
		int id = 1;
		for (Feature feature : xplanFc.getFeatures()) {
			feature.setId("FEATURE_" + id++);
		}
		return xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);
	}

	protected FeatureCollection createSynFeatures(XPlanVersion version, XPlanFeatureCollection xplanFc) {
		int id = 1;
		for (Feature feature : xplanFc.getFeatures()) {
			feature.setId("FEATURE_" + id++);
		}
		return xPlanSynthesizer.synthesize(version, xplanFc);
	}

	protected String writeSynFeatureCollection(FeatureCollection fc) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(os);
		xmlWriter = new IndentingXMLStreamWriter(xmlWriter);
		GMLStreamWriter gmlWriter = GMLOutputFactory.createGMLStreamWriter(GML_32, xmlWriter);
		Map<String, String> nsBindings = synSchema.getNamespaceBindings();
		nsBindings.put("gml", GML_32.getNamespace());
		gmlWriter.setNamespaceBindings(nsBindings);
		gmlWriter.write(fc);
		gmlWriter.close();
		xmlWriter.close();
		return os.toString();
	}

	protected XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, getClass().getResourceAsStream("/testdata/" + name));
	}

}
