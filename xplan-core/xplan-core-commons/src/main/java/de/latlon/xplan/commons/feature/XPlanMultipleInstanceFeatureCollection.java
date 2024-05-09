/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.MainZipEntry;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.ZipEntryWithContent;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;
import org.deegree.gml.GMLStreamWriter;
import org.deegree.gml.XPlanGmlWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static de.latlon.xplan.commons.archive.XPlanArchiveCreator.MAIN_FILE;

/**
 * Provides convenient access to the information contained in the main document of an
 * {@link XPlanArchive} which contains multiple XPlan instances.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanMultipleInstanceFeatureCollection extends XPlanFeatureCollection {

	private final MainZipEntry mainZipEntry;

	XPlanMultipleInstanceFeatureCollection(FeatureCollection fc, XPlanType type, String name, String nummer, String gkz,
			Date planReleaseDate, ExternalReferenceInfo externalRefInfo, Envelope bboxIn4326, XPlanVersion version)
			throws FeatureCollectionParseException {
		super(fc, type, name, nummer, gkz, planReleaseDate, externalRefInfo, bboxIn4326, version);
		try {
			byte[] bytes = writeFeatures();
			this.mainZipEntry = new MainZipEntry(bytes, MAIN_FILE);
		}
		catch (Exception e) {
			throw new FeatureCollectionParseException("XPlan GML could not be written", e);
		}
	}

	@Override
	public List<ZipEntryWithContent> getArchiveEntries(XPlanArchive xPlanArchive)
			throws FeatureCollectionParseException {
		if (xPlanArchive == null)
			return null;
		List<ZipEntryWithContent> archiveEntries = new ArrayList<>();
		addReferencedArtefacts(xPlanArchive, archiveEntries);
		try {
			archiveEntries.add(mainZipEntry);
			return archiveEntries;
		}
		catch (Exception e) {
			throw new FeatureCollectionParseException("XPlan GML could not be written", e);
		}
	}

	private void addReferencedArtefacts(XPlanArchive xPlanArchive, List<ZipEntryWithContent> archiveEntries)
			throws FeatureCollectionParseException {
		ExternalReferenceInfo externalReferenceInfo = getExternalReferenceInfo();
		if (externalReferenceInfo != null) {
			for (ExternalReference externalRef : externalReferenceInfo.getAllReferences()) {
				addArtefact(xPlanArchive, archiveEntries, externalRef.getReferenzUrl());
				addArtefact(xPlanArchive, archiveEntries, externalRef.getGeoRefUrl());
			}
		}
	}

	private void addArtefact(XPlanArchive xPlanArchive, List<ZipEntryWithContent> archiveEntries, String artefactName)
			throws FeatureCollectionParseException {
		if (artefactName != null && !artefactName.startsWith("http")) {
			ZipEntryWithContent entry = xPlanArchive.getEntry(artefactName);
			if (entry == null) {
				throw new FeatureCollectionParseException("Could not identify archive entry with name " + artefactName);
			}
			if (!isAlreadyInserted(archiveEntries, artefactName)) {
				archiveEntries.add(entry);
			}
		}
	}

	private boolean isAlreadyInserted(List<ZipEntryWithContent> archiveEntries, String artefactName) {
		for (ZipEntryWithContent entry : archiveEntries) {
			if (artefactName.equals(entry.getName()))
				return true;
		}
		return false;
	}

	private byte[] writeFeatures()
			throws TransformationException, XMLStreamException, UnknownCRSException, IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		XMLStreamWriter xmlStream = null;
		GMLStreamWriter gmlStreamWriter = null;
		try {
			xmlStream = XMLOutputFactory.newFactory().createXMLStreamWriter(os);
			gmlStreamWriter = new XPlanGmlWriter(version, new IndentingXMLStreamWriter(xmlStream));
			gmlStreamWriter.write(getFeatures());
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

}
