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
package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.archive.XPlanPartArchive;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.manager.configuration.ConfigurationException;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.edit.EditedArtefacts;
import de.latlon.xplan.manager.edit.XPlanManipulator;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.metadata.MetadataCouplingHandler;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transaction.service.XPlanEditService;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDescription;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrievePlanName;
import static de.latlon.xplan.manager.edit.ArtefactType.RASTER;
import static de.latlon.xplan.manager.edit.ArtefactType.RASTER_GEOREFERENCE;
import static de.latlon.xplan.manager.edit.EditType.ADDED;
import static de.latlon.xplan.manager.edit.EditType.REMOVED;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.collectEditedArtefacts;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.createExternalRefAddedOrUpdated;
import static de.latlon.xplan.manager.transaction.TransactionUtils.reassignFids;
import static de.latlon.xplan.manager.web.shared.PlanStatus.findByLegislationStatusCode;
import static java.lang.Integer.parseInt;
import static org.deegree.cs.persistence.CRSManager.lookup;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanEditManager extends XPlanTransactionManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanEditManager.class);

	private static final DateFormat DATEFORMAT = createDateFormat();

	private final XPlanEditService xPlanEditService;

	private final AttachmentUrlHandler attachmentUrlHandler;

	protected final XPlanExporter xPlanExporter;

	private final XPlanManipulator planModifier = new XPlanManipulator();

	public XPlanEditManager(XPlanSynthesizer xPlanSynthesizer, XPlanDao xplanDao, XPlanExporter xPlanExporter,
			XPlanRasterManager xPlanRasterManager, WorkspaceReloader workspaceReloader,
			ManagerConfiguration managerConfiguration, SortPropertyReader sortPropertyReader,
			XPlanEditService xPlanEditService, MetadataCouplingHandler metadataCouplingHandler,
			AttachmentUrlHandler attachmentUrlHandler) {
		super(xPlanSynthesizer, xplanDao, xPlanRasterManager, workspaceReloader, managerConfiguration,
				sortPropertyReader, metadataCouplingHandler);
		this.xPlanExporter = xPlanExporter;
		this.xPlanEditService = xPlanEditService;
		this.attachmentUrlHandler = attachmentUrlHandler;
	}

	public void editPlan(XPlan oldXplan, XPlanToEdit xPlanToEdit, boolean makeRasterConfig,
			List<File> uploadedArtefacts) throws Exception {
		int planId = parseInt(oldXplan.getId());
		LOG.info("- Editiere Plan mit ID {}", planId);
		LOG.debug("zu editierender Plan: {}", xPlanToEdit);
		String oldPlanName = oldXplan.getName();
		XPlanVersion version = XPlanVersion.valueOf(oldXplan.getVersion());
		XPlanType type = XPlanType.valueOf(oldXplan.getType());
		PlanStatus oldPlanStatus = oldXplan.getXplanMetadata().getPlanStatus();
		AppSchema appSchema = XPlanSchemas.getInstance().getAppSchema(version);
		try (InputStream originalPlan = xplanDao.retrieveXPlanArtefact(planId)) {
			XPlanFeatureCollection originalPlanFC = XPlanGmlParserBuilder.newBuilder()
				.build()
				.parseXPlanFeatureCollection(originalPlan, version, type);
			String oldDescription = retrieveDescription(originalPlanFC.getFeatures(), type);
			String oldLegislationStatus = FeatureCollectionUtils.retrieveRechtsstand(originalPlanFC.getFeatures(),
					type);
			FeatureCollection featuresToModify = originalPlanFC.getFeatures();
			ExternalReferenceInfo externalReferencesOriginal = new ExternalReferenceScanner().scan(featuresToModify);
			replaceRelativeUrls(planId, xPlanToEdit);
			planModifier.modifyXPlan(featuresToModify, xPlanToEdit, version, type, appSchema);
			FeatureCollection modifiedFeatures = renewFeatureCollection(version, featuresToModify);
			ExternalReferenceInfo externalReferencesModified = new ExternalReferenceScanner().scan(modifiedFeatures);

			List<String> uploadedFileNames = uploadedArtefacts.stream()
				.map(file -> file.getName())
				.collect(Collectors.toList());
			byte[] xPlanGml = createXPlanGml(version, modifiedFeatures);
			ExternalReferenceInfo externalReferenceInfoToUpdate = createExternalRefAddedOrUpdated(
					externalReferencesModified, uploadedArtefacts);
			List<String> originalArtefacts = xplanDao.retrieveAllXPlanArtefactFileNames(planId);
			EditedArtefacts editedArtefacts = collectEditedArtefacts(attachmentUrlHandler, oldXplan.getId(),
					externalReferencesModified, externalReferencesOriginal, originalArtefacts, uploadedFileNames);

			XPlanFeatureCollection modifiedPlanFc = new XPlanFeatureCollectionBuilder(modifiedFeatures, type)
				.withExternalReferenceInfo(externalReferenceInfoToUpdate)
				.build();
			reassignFids(modifiedPlanFc);
			FeatureCollection synFc = createSynFeatures(modifiedPlanFc, version);
			String internalId = xplanDao.retrieveInternalId(planId);

			// TODO: Validation required?
			PlanStatus newPlanStatus = detectNewPlanStatus(type, xPlanToEdit, oldLegislationStatus, oldPlanStatus);
			AdditionalPlanData xPlanMetadata = new AdditionalPlanData(newPlanStatus,
					xPlanToEdit.getValidityPeriod().getStart(), xPlanToEdit.getValidityPeriod().getEnd());
			Date sortDate = sortPropertyReader.readSortDate(type, version, modifiedFeatures);
			xPlanEditService.update(oldXplan, uploadedArtefacts, planId, xPlanGml, editedArtefacts, modifiedPlanFc,
					synFc, xPlanMetadata, sortDate, internalId);
			startCreationIfPlanNameHasChanged(planId, type, modifiedPlanFc, oldPlanName, oldDescription);
			updateRasterConfiguration(planId, makeRasterConfig, uploadedArtefacts, type, oldPlanStatus, editedArtefacts,
					newPlanStatus, sortDate);
			LOG.info("XPlanGML wurde erfolgreich editiert. ID: {}", planId);
		}
	}

	private void replaceRelativeUrls(int planId, XPlanToEdit xPlanToEdit) {
		if (attachmentUrlHandler != null) {
			attachmentUrlHandler.replaceRelativeUrls(planId, xPlanToEdit);
		}
	}

	private void updateRasterConfiguration(int planId, boolean makeRasterConfig, List<File> uploadedArtefacts,
			XPlanType type, PlanStatus oldPlanStatus, EditedArtefacts editedArtefacts, PlanStatus newPlanStatus,
			Date sortDate) throws JAXBException, IOException, ConfigurationException {
		List<String> removedRefFileNames = editedArtefacts.getFileNames(REMOVED, RASTER, RASTER_GEOREFERENCE);
		xPlanRasterManager.removeRasterLayers(planId, removedRefFileNames);
		if (makeRasterConfig) {
			XPlanArchiveContentAccess archive = new XPlanPartArchive(uploadedArtefacts);
			List<String> addedRefFileNames = editedArtefacts.getFileNames(ADDED, RASTER);
			createRasterConfiguration(archive, addedRefFileNames, planId, type, oldPlanStatus, newPlanStatus, sortDate);
			reloadWorkspace(planId);
		}
		else {
			xPlanRasterManager.updateRasterLayers(planId, type, oldPlanStatus, newPlanStatus);
		}
		LOG.info("Rasterkonfiguration für den Plan mit der ID {} wurde ausgetauscht (falls vorhanden).", planId);
	}

	private PlanStatus detectNewPlanStatus(XPlanType type, XPlanToEdit xPlanToEdit, String oldLegislationStatus,
			PlanStatus oldPlanStatus) {
		int newLegislationStatusCode = xPlanToEdit.getBaseData().getLegislationStatusCode();
		int oldLegislationStatusCode = -1;
		try {
			if (oldLegislationStatus != null)
				oldLegislationStatusCode = Integer.parseInt(oldLegislationStatus);
		}
		catch (NumberFormatException e) {
			LOG.warn("Could not parse legislation status code {} as integer", oldLegislationStatus);
		}
		if (newLegislationStatusCode != oldLegislationStatusCode)
			return findByLegislationStatusCode(type.name(), newLegislationStatusCode);
		return oldPlanStatus;
	}

	private void startCreationIfPlanNameHasChanged(int planId, XPlanType type, XPlanFeatureCollection modifiedPlanFc,
			String oldPlanName, String oldDescription) throws UnknownCRSException {
		String newPlanName = retrievePlanName(modifiedPlanFc.getFeatures(), type);
		String newDescription = retrieveDescription(modifiedPlanFc.getFeatures(), type);
		if (hasChanged(oldPlanName, newPlanName) || hasChanged(oldDescription, newDescription)) {
			startCreationOfDataServicesCoupling(planId, modifiedPlanFc,
					lookup(managerConfiguration.getRasterConfigurationCrs()));
		}
		else {
			LOG.info("Plan name does not change, creation of the service record is not required.");
		}
	}

	private byte[] createXPlanGml(XPlanVersion version, FeatureCollection plan) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		String comment = "Zuletzt aktualisiert am: " + DATEFORMAT.format(new Date());
		xPlanExporter.export(bos, version, plan, comment);
		return bos.toByteArray();
	}

	private FeatureCollection renewFeatureCollection(XPlanVersion version, FeatureCollection modifiedFeatures)
			throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		xPlanExporter.export(outputStream, version, modifiedFeatures, null);
		ByteArrayInputStream originalPlan = new ByteArrayInputStream(outputStream.toByteArray());
		XMLStreamReader originalPlanAsXmlReader = XMLInputFactory.newInstance().createXMLStreamReader(originalPlan);
		try {
			return XPlanGmlParserBuilder.newBuilder().build().parseFeatureCollection(originalPlanAsXmlReader, version);
		}
		finally {
			originalPlanAsXmlReader.close();
		}
	}

	private boolean hasChanged(String oldValue, String newValue) {
		if (newValue == null)
			if (oldValue == null)
				return false;
			else
				return true;
		return !newValue.equals(oldValue);
	}

	private static DateFormat createDateFormat() {
		DateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
		return simpleDateFormat;
	}

}
