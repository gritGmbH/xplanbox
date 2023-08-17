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
package de.latlon.xplan.manager.export;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.core.manager.db.model.Artefact;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.io.DecimalCoordinateFormatter;
import org.deegree.gml.GMLStreamWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static javax.xml.stream.XMLOutputFactory.IS_REPAIRING_NAMESPACES;
import static org.deegree.commons.xml.CommonNamespaces.XLNNS;
import static org.deegree.gml.GMLOutputFactory.createGMLStreamWriter;

/**
 * Exports the content of a plan as zip archive.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanExporter {

	private final Logger LOG = LoggerFactory.getLogger(XPlanExporter.class);

	private static final DateFormat DATEFORMAT = createDateFormat();

	/**
	 * Exports the content of a plan as zip archive.
	 * @param outputStream to write the artefacts into, never <code>null</code>
	 * @param artefacts the content to write, the underlying artefacts stream is closed
	 * after the export, never <code>null</code>
	 * @throws NullPointerException if outputStream or contents is <code>null</code>
	 * @throws XPlanExportException if an error occurred during export
	 */
	public void export(OutputStream outputStream, List<Artefact> artefacts) {
		long begin = System.currentTimeMillis();
		writeContentToStream(outputStream, artefacts);
		long elapsed = System.currentTimeMillis() - begin;
		LOG.info("OK [" + elapsed + " ms]");
	}

	/**
	 * Exports the xplan as gml.
	 * @param outputStream the stream to write the exported xplan.gml into, never
	 * <code>null</code>
	 * @param version the version of the xplan, never <code>null</code>
	 * @param fc to export, never <code>null</code>
	 * @param comment written in the exported xml, may be <code>null</code>
	 * @throws Exception
	 */
	public void export(OutputStream outputStream, XPlanVersion version, FeatureCollection fc, String comment)
			throws Exception {

		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
		xmlOutputFactory.setProperty(IS_REPAIRING_NAMESPACES, true);
		XMLStreamWriter writer = new IndentingXMLStreamWriter(
				xmlOutputFactory.createXMLStreamWriter(outputStream, "UTF-8"));
		writer.writeStartDocument("UTF-8", "1.0");
		if (comment != null)
			writer.writeComment(comment);
		GMLStreamWriter encoder = createGMLStreamWriter(version.getGmlVersion(), writer);
		encoder.setGenerateBoundedByForFeatures(true);
		encoder.setCoordinateFormatter(new DecimalCoordinateFormatter(3));
		String xplanNs = version.getNamespace();
		writer.setPrefix("xplan", xplanNs);
		writer.setPrefix("xlink", XLNNS);
		String gmlNs = version.getGmlVersion().getNamespace();
		writer.setPrefix("gml", gmlNs);
		writer.writeStartElement(xplanNs, "XPlanAuszug");
		String id = fc.getId();
		if (id == null)
			id = "AUSZUG_" + UUID.randomUUID().toString();
		writer.writeAttribute("gml", gmlNs, "id", id);
		exportEnvelope(encoder, fc, writer, gmlNs);
		for (Feature feature : fc) {
			writer.writeStartElement(gmlNs, "featureMember");
			encoder.write(feature);
			writer.writeEndElement();
		}
		writer.writeEndElement();
		writer.close();
	}

	private void writeContentToStream(OutputStream outputStream, List<Artefact> artefacts) {
		try {
			ZipOutputStream zipOS = new ZipOutputStream(outputStream);
			artefacts.forEach(artefact -> writeArtefactToStream(zipOS, artefact));
			zipOS.close();
		}
		catch (XPlanExportException e) {
			throw e;
		}
		catch (Exception e) {
			LOG.error("Plan could not be exported!", e);
			throw new XPlanExportException("Fehler beim Exportieren des Plans: " + e.getMessage() + ".", e);
		}
	}

	private void writeArtefactToStream(ZipOutputStream zos, Artefact artefact) {
		String fileName = artefact.getId().getFilename();
		LOG.info("- Schreibe Artefakt '" + fileName + "'...");
		byte[] artefactContent = artefact.getData();
		try (ByteArrayInputStream bis = new ByteArrayInputStream(artefactContent);
				GZIPInputStream is = new GZIPInputStream(bis)) {
			ZipEntry entry = new ZipEntry(fileName);
			zos.putNextEntry(entry);
			byte[] buffer = new byte[10240];
			int read;
			while ((read = is.read(buffer)) != -1) {
				zos.write(buffer, 0, read);
			}
		}
		catch (Exception e) {
			throw new XPlanExportException("Fehler beim Rekonstruieren des XPlan-Artefakts mit Namen " + fileName + ": "
					+ e.getLocalizedMessage(), e);
		}
		finally {
			try {
				zos.closeEntry();
			}
			catch (IOException e) {
			}
		}
	}

	private void exportEnvelope(GMLStreamWriter encoder, FeatureCollection fc, XMLStreamWriter writer, String gmlNs)
			throws XMLStreamException, UnknownCRSException, TransformationException {
		Envelope envelope = fc.getEnvelope();
		writer.writeStartElement(gmlNs, "boundedBy");
		if (envelope != null) {
			encoder.getGeometryWriter().exportEnvelope(envelope);
		}
		else {
			writer.writeStartElement(gmlNs, "null");
			writer.writeCharacters("missing");
			writer.writeEndElement();
		}
		writer.writeEndElement();
	}

	private static DateFormat createDateFormat() {
		DateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
		return simpleDateFormat;
	}

}
