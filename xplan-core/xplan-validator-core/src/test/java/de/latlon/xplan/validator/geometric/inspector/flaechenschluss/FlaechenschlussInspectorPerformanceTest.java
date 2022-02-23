package de.latlon.xplan.validator.geometric.inspector.flaechenschluss;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.geometric.inspector.GeometricFeatureInspector;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.deegree.gml.feature.FeatureInspector;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FlaechenschlussInspectorPerformanceTest {

	@Ignore
	@Test
	public void testCheckFlaechenschluss_OldInspector() throws Exception {
		FlaechenschlussInspector flaechenschlussInspector = new FlaechenschlussInspector();
		checkFlaechenschluss(flaechenschlussInspector);
	}

	@Ignore
	@Test
	public void testCheckFlaechenschluss_OptimizedInspector() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector();
		checkFlaechenschluss(flaechenschlussInspector);
	}

	private void checkFlaechenschluss(GeometricFeatureInspector flaechenschlussInspector)
			throws IOException, XMLStreamException, UnknownCRSException {
		XPlanArchive archive = getLocalTestArchive("Testplan.zip");
		long start = System.currentTimeMillis();
		readFeatures(archive, flaechenschlussInspector);
		flaechenschlussInspector.checkGeometricRule();
		long end = System.currentTimeMillis();
		System.out.println("Flaechenschluss with optimized implementation: " + (end - start) + " [ms]");
	}

	private void readFeatures(XPlanArchive archive, FeatureInspector flaechenschlussInspector)
			throws XMLStreamException, UnknownCRSException {
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(archive.getMainFileXmlReader(), null);
		XPlanVersion version = archive.getVersion();
		GMLVersion gmlVersion = version.getGmlVersion();
		XPlanAde ade = archive.getAde();
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(version, ade);

		GeometryFactory geomFac = new GeometryFactory();
		GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader(gmlVersion, xmlStream);
		gmlStream.setGeometryFactory(geomFac);
		gmlStream.setApplicationSchema(schema);
		gmlStream.setSkipBrokenGeometries(true);
		gmlStream.addInspector(flaechenschlussInspector);
		gmlStream.readFeature();

	}

	private XPlanArchive getLocalTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, getClass().getResourceAsStream(name));
	}

}
