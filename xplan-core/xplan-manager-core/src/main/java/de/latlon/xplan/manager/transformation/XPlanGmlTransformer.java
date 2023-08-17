/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.transformation;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.hale.TransformationException;
import org.apache.commons.io.IOUtils;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.gml.GMLStreamWriter;
import org.deegree.gml.XPlanGmlWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlTransformer {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanGmlTransformer.class);

	private final HaleXplan41ToXplan51Transformer xplan41ToXplan51Transformer;

	/**
	 * @param haleXplan41ToXplan51Transformer the underlying transformer used for the
	 * transformation between XPlanGML 4.1 and XPlanGML 5.1, never <code>null</code>
	 */
	public XPlanGmlTransformer(HaleXplan41ToXplan51Transformer haleXplan41ToXplan51Transformer) {
		this.xplan41ToXplan51Transformer = haleXplan41ToXplan51Transformer;
	}

	/**
	 * Transforms the XPLanGML in the passed {@link XPlanArchive} to an XPlanGML 5.1
	 * {@link XPlanFeatureCollection} if the archive is XPlanVersion 4.1.
	 * @param xPlanFeatureCollection to transform, never <code>null</code>
	 * @return the {@link TransformationResult} or <code>null</code> if the version of the
	 * source is not 4.1
	 * @throws TransformationException } if the transformation failed
	 */
	public TransformationResult transform(XPlanFeatureCollection xPlanFeatureCollection)
			throws TransformationException {
		if (XPLAN_41.equals(xPlanFeatureCollection.getVersion())) {
			LOG.info("Transform XPlanGML 4.1 to XPlanGml 5.1");
			try (InputStream xplanGml = new ByteArrayInputStream(asBytes(xPlanFeatureCollection))) {
				Path source = storeAsTmpFile(xplanGml);
				LOG.debug("Source file (XPlanGML 4.1) of the transformation: " + source);
				Path target = xplan41ToXplan51Transformer.transformToXPlanGml51(source);
				LOG.debug("Transformed XPlanGML 5.1 : " + target);
				return new TransformationResult(Files.readAllBytes(target), XPLAN_51);
			}
			catch (IOException | XMLStreamException | UnknownCRSException
					| org.deegree.cs.exceptions.TransformationException e) {
				LOG.error("Could not transform plan to XPlanGML 5.1", e);
				throw new TransformationException("Could not transform plan to XPlanGML 5.1", e);
			}
		}
		return null;
	}

	private byte[] asBytes(XPlanFeatureCollection xPlanFeatureCollection) throws XMLStreamException,
			UnknownCRSException, org.deegree.cs.exceptions.TransformationException, IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		XMLStreamWriter xmlStream = null;
		GMLStreamWriter gmlStreamWriter = null;
		try {
			xmlStream = XMLOutputFactory.newFactory().createXMLStreamWriter(os);
			gmlStreamWriter = new XPlanGmlWriter(XPLAN_41, xmlStream);
			gmlStreamWriter.write(xPlanFeatureCollection.getFeatures());
		}
		finally {
			if (gmlStreamWriter != null)
				gmlStreamWriter.close();
			if (xmlStream != null)
				xmlStream.close();
			os.close();
		}
		return os.toByteArray();

	}

	private Path storeAsTmpFile(InputStream sourceStream) throws IOException {
		Path sourceFile = Files.createTempFile("xplanGmlSource_", ".xml");
		try (FileOutputStream sourceFileStream = new FileOutputStream(sourceFile.toFile())) {
			IOUtils.copy(sourceStream, sourceFileStream);
			sourceStream.close();
		}
		return sourceFile;
	}

}
