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
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.xml.XMLParsingException;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLStreamWriter;
import org.deegree.gml.GMLVersion;
import org.junit.Before;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;
import static org.deegree.gml.GMLVersion.GML_31;
import static org.deegree.gml.GMLVersion.GML_32;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class AbstractXplanSynthesizerTest {

	private final XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

	private AppSchema synSchema;

	@Before
	public void setup() {
		synSchema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
	}

	abstract XPlanVersion getXPlanVersion();

	protected Map<String, String> nsContext() {
		Map<String, String> nsContext = new HashMap<>();
		nsContext.put("xplansyn", XPlanVersion.XPLAN_SYN.getNamespace());
		return nsContext;
	}

	protected FeatureCollection createSynFeatures(String archiveName)
			throws IOException, XMLStreamException, UnknownCRSException {
		XPlanArchive archive = getTestArchive(archiveName);
		XPlanFeatureCollection xplanFc = readFeatures(archive);
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
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

	protected XPlanFeatureCollection readFeatures(XPlanArchive archive) throws XMLStreamException, UnknownCRSException {
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

	protected FeatureCollection readSynFeatures(String fileName)
			throws XMLStreamException, FactoryConfigurationError, XMLParsingException, UnknownCRSException {
		InputStream is = ResourceAccessor.readResourceStream("xplansyn/" + fileName);
		XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader(is);
		GMLVersion gmlVersion = getXPlanVersion().getGmlVersion();
		GMLStreamReader gmlReader = GMLInputFactory.createGMLStreamReader(gmlVersion, xmlReader);
		gmlReader.setApplicationSchema(synSchema);
		return gmlReader.readFeatureCollection();
	}

	protected void assertEqualContent(FeatureCollection expected, FeatureCollection actual) {
		assertEquals("Wrong number of synthesized features.", expected.size(), actual.size());
		Iterator<Feature> iter = actual.iterator();
		for (Feature expectedFeature : expected) {
			Feature actualFeature = iter.next();
			assertEqualContent(expectedFeature, actualFeature);
		}
	}

	private void assertEqualContent(Feature expected, Feature actual) {
		removeBoundedBy(expected);
		removeBoundedBy(actual);
		assertEquals("Wrong feature type.", expected.getType().getName(), actual.getType().getName());
		assertEquals("Wrong number of properties. ", expected.getProperties().size(), actual.getProperties().size());
		String expectedProps = toString(expected.getProperties());
		String actualProps = toString(actual.getProperties());
		assertEquals("Wrong property sequence. ", expectedProps, actualProps);
		Iterator<Property> iter = actual.getProperties().iterator();
		for (Property expectedProperty : expected.getProperties()) {
			Property actualProperty = iter.next();
			assertEqualContent(expectedProperty, actualProperty, actual.getId());
		}
	}

	private void assertEqualContent(Property expected, Property actual, String featureId) {
		if (expected.getName().getLocalPart().equals("xpPlanName")) {
			return;
		}
		TypedObjectNode value = expected.getValue();
		if (value instanceof Geometry) {
			assertEquals("Wrong geometry type of feature with id " + featureId + ".", expected.getValue().getClass(),
					actual.getValue().getClass());
		}
		else if (value instanceof PrimitiveValue) {
			// TODO references are skipped
			if (actual.getValue().toString().startsWith("[#"))
				return;
			assertEquals("Wrong value of property '" + expected.getName() + "' of feature with id " + featureId + ".",
					"" + expected.getValue(), "" + actual.getValue());
		}
		else {
			throw new IllegalArgumentException();
		}
	}

	private String toString(List<Property> props) {
		String s = "";
		for (Property property : props) {
			s += property.getName() + ", ";
		}
		return s;
	}

	private void removeBoundedBy(Feature f) {
		List<Property> props = f.getProperties();
		List<Property> newProps = new ArrayList<Property>(props.size());
		for (Property property : props) {
			if (property.getName().getNamespaceURI().equals(GML_31.getNamespace())) {
				continue;
			}
			newProps.add(property);
		}
		f.setProperties(newProps);
	}

	protected void writeTemp(FeatureCollection fc, String prefix) {
		try {
			File file = Files.createTempFile(prefix, ".gml").toFile();
			FileOutputStream os = new FileOutputStream(file);
			XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(os);
			xmlWriter = new IndentingXMLStreamWriter(xmlWriter);
			GMLStreamWriter gmlWriter = GMLOutputFactory.createGMLStreamWriter(GML_31, xmlWriter);
			Map<String, String> nsBindings = synSchema.getNamespaceBindings();
			nsBindings.put("gml32", GML_32.getNamespace());
			gmlWriter.setNamespaceBindings(nsBindings);
			gmlWriter.write(fc);
			gmlWriter.close();
			xmlWriter.close();
			os.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
