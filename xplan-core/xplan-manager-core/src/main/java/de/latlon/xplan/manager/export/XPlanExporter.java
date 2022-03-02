/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import static java.lang.Math.max;
import static javax.xml.stream.XMLOutputFactory.IS_REPAIRING_NAMESPACES;
import static org.deegree.commons.xml.CommonNamespaces.XLNNS;
import static org.deegree.gml.GMLOutputFactory.createGMLStreamWriter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

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

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;

/**
 * Exports the content of a plan as zip archive.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanExporter {

	private final Logger LOG = LoggerFactory.getLogger(XPlanExporter.class);

	private static final DateFormat DATEFORMAT = createDateFormat();

	static final String MAIN_FILE_REEXPORTED_PREFIX = "xplan-reexported";

	static final String MAIN_FILE_REEXPORTED_SUFFIX = ".gml";

	static final String MAIN_FILE_REEXPORTED_SEPERATOR = "-";

	private ManagerConfiguration managerConfiguration;

	/**
	 * @param managerConfiguration the configuration of the manager containing details
	 * about the export, may be <code>null</code>
	 */
	public XPlanExporter(ManagerConfiguration managerConfiguration) {
		this.managerConfiguration = managerConfiguration;
	}

	/**
	 * Exports the content of a plan as zip archive.
	 * @param outputStream to write the artefacts into, never <code>null</code>
	 * @param contents the content to write, the underlying artefacts stream is closed
	 * after the export, never <code>null</code>
	 * @throws NullPointerException if outputStream or contents is <code>null</code>
	 * @throws XPlanExportException if an error occurred during export
	 */
	public void export(OutputStream outputStream, XPlanArchiveContent contents) {
		long begin = System.currentTimeMillis();
		writeContentToStream(outputStream, contents);
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
		createReexported(outputStream, version, fc, comment);
	}

	private void writeContentToStream(OutputStream outputStream, XPlanArchiveContent contents) {
		try {
			ZipOutputStream zipOS = new ZipOutputStream(outputStream);
			List<String> exportedArtefactFileNames = writeArchiveContentToStream(zipOS, contents);
			if (isExportOfReexportedActive())
				exportReexported(zipOS, contents, exportedArtefactFileNames);
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

	private List<String> writeArchiveContentToStream(ZipOutputStream zipOS, XPlanArchiveContent contents)
			throws Exception {
		List<String> exportedArtefactFileNames = new ArrayList<String>();
		XPlanArtefactIterator artefacts = contents.getArtefacts();
		while (artefacts.hasNext()) {
			XPlanArtefact artefact = artefacts.next();
			writeArtefactToStream(zipOS, artefact);
			exportedArtefactFileNames.add(artefact.getFileName());
		}
		artefacts.close();
		return exportedArtefactFileNames;
	}

	private void writeArtefactToStream(ZipOutputStream zos, XPlanArtefact artefact) {
		String fileName = artefact.getFileName();
		LOG.info("- Schreibe Artefakt '" + fileName + "'...");
		try {
			InputStream artefactContent = artefact.getContent();
			GZIPInputStream is = new GZIPInputStream(artefactContent);
			ZipEntry entry = new ZipEntry(fileName);
			zos.putNextEntry(entry);
			byte[] buffer = new byte[10240];
			int read;
			while ((read = is.read(buffer)) != -1) {
				zos.write(buffer, 0, read);
			}
			zos.closeEntry();
		}
		catch (Exception e) {
			throw new XPlanExportException("Fehler beim Rekonstruieren des XPlan-Artefakts mit Namen " + fileName + ": "
					+ e.getLocalizedMessage(), e);
		}
	}

	private void exportReexported(ZipOutputStream zipOS, XPlanArchiveContent contents,
			List<String> exportedArtefactFileNames) {
		String reexportedFileName = createReexportedFileName(exportedArtefactFileNames);
		LOG.info("- Schreibe reexported '{}'...", reexportedFileName);
		try {
			FeatureCollection restoredFeatureCollection = contents.getRestoredFeatureCollection();
			XPlanVersion version = contents.getVersion();
			ByteArrayOutputStream reexportedXplanAuszug = createReexported(version, restoredFeatureCollection);
			ZipEntry entry = new ZipEntry(reexportedFileName);
			zipOS.putNextEntry(entry);
			zipOS.write(reexportedXplanAuszug.toByteArray());
			zipOS.closeEntry();
		}
		catch (Exception e) {
			throw new XPlanExportException(
					"Fehler beim Rekonstruieren der XPlan reexported Datei: " + e.getLocalizedMessage(), e);
		}
	}

	private ByteArrayOutputStream createReexported(XPlanVersion version, FeatureCollection fc)
			throws FactoryConfigurationError, XMLStreamException, UnknownCRSException, TransformationException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String comment = "Generiert von XPlanManager, " + DATEFORMAT.format(new Date());
		createReexported(bos, version, fc, comment);
		return bos;
	}

	private void createReexported(OutputStream outputStream, XPlanVersion version, FeatureCollection fc, String comment)
			throws FactoryConfigurationError, XMLStreamException, UnknownCRSException, TransformationException {
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

	private String createReexportedFileName(List<String> exportedArtefactFileNames) {
		int maxCounter = Integer.MIN_VALUE;
		for (String fileName : exportedArtefactFileNames) {
			if (fileName.startsWith(MAIN_FILE_REEXPORTED_PREFIX) && fileName.endsWith(MAIN_FILE_REEXPORTED_SUFFIX)) {
				String mid = extractMid(fileName);
				maxCounter = max(maxCounter, parseMid(mid));
			}
		}
		String counter = "";
		if (maxCounter > -1) {
			int newCounter = maxCounter + 1;
			counter = MAIN_FILE_REEXPORTED_SEPERATOR + newCounter;
		}
		return MAIN_FILE_REEXPORTED_PREFIX + counter + MAIN_FILE_REEXPORTED_SUFFIX;
	}

	private boolean isExportOfReexportedActive() {
		if (managerConfiguration != null)
			return managerConfiguration.isExportOfReexportedActive();
		return false;
	}

	private int parseMid(String mid) {
		if (mid.length() > 0) {
			try {
				return Integer.parseInt(mid);
			}
			catch (NumberFormatException e) {
				LOG.trace("Error: {} is not parsable as integer. Returning 0", mid);
			}
		}
		return 0;
	}

	private String extractMid(String fileName) {
		String fileNameWithoutPrefix = fileName.replaceFirst(MAIN_FILE_REEXPORTED_PREFIX, "");
		int lastIndexOfSuffix = fileNameWithoutPrefix.lastIndexOf(MAIN_FILE_REEXPORTED_SUFFIX);
		String mid = fileNameWithoutPrefix.substring(0, lastIndexOfSuffix);
		if (mid.startsWith(MAIN_FILE_REEXPORTED_SEPERATOR) && mid.length() > 1)
			mid = mid.substring(1);
		return mid;
	}

	private static DateFormat createDateFormat() {
		DateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
		return simpleDateFormat;
	}

}
