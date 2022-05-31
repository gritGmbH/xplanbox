/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.archive.XPlanPartArchive;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
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

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDescription;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrievePlanName;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.collectRemovedRefs;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.createExternalRefAddedOrUpdated;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.createExternalRefRemovedOrUpdated;
import static java.lang.Integer.parseInt;
import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.deegree.cs.persistence.CRSManager.lookup;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanEditManager extends XPlanTransactionManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanEditManager.class);

	public XPlanEditManager(XPlanSynthesizer xPlanSynthesizer, XPlanGmlTransformer xPlanGmlTransformer,
			XPlanDao xplanDao, XPlanExporter xPlanExporter, XPlanRasterManager xPlanRasterManager,
			WorkspaceReloader workspaceReloader, ManagerConfiguration managerConfiguration,
			ManagerWorkspaceWrapper managerWorkspaceWrapper, SortPropertyReader sortPropertyReader)
			throws DataServiceCouplingException {
		super(xPlanSynthesizer, xPlanGmlTransformer, xplanDao, xPlanExporter, xPlanRasterManager, workspaceReloader,
				managerConfiguration, managerWorkspaceWrapper, sortPropertyReader);
	}

	public void editPlan(XPlan oldXplan, XPlanToEdit xPlanToEdit, boolean makeRasterConfig,
			List<File> uploadedArtefacts) throws Exception {
		InputStream originalPlan = null;
		try {
			String planId = oldXplan.getId();
			LOG.info("- Editiere Plan mit ID {}", planId);
			LOG.debug("zu editierender Plan: {}", xPlanToEdit);
			String oldPlanName = oldXplan.getName();
			XPlanVersion version = XPlanVersion.valueOf(oldXplan.getVersion());
			XPlanType type = XPlanType.valueOf(oldXplan.getType());
			PlanStatus oldPlanStatus = oldXplan.getXplanMetadata().getPlanStatus();
			AppSchema appSchema = managerWorkspaceWrapper.lookupStore(version, oldPlanStatus).getSchema();
			originalPlan = xplanDao.retrieveXPlanArtefact(planId);
			XPlanFeatureCollection originalPlanFC = xPlanGmlParser.parseXPlanFeatureCollection(originalPlan, version,
					type);
			String oldDescription = retrieveDescription(originalPlanFC.getFeatures(), type);
			String oldLegislationStatus = FeatureCollectionUtils.retrieveRechtsstand(originalPlanFC.getFeatures(),
					type);
			FeatureCollection featuresToModify = originalPlanFC.getFeatures();
			ExternalReferenceInfo externalReferencesOriginal = new ExternalReferenceScanner().scan(featuresToModify);
			planModifier.modifyXPlan(featuresToModify, xPlanToEdit, version, type, appSchema);
			FeatureCollection modifiedFeatures = renewFeatureCollection(version, featuresToModify);
			ExternalReferenceInfo externalReferencesModified = new ExternalReferenceScanner().scan(modifiedFeatures);

			byte[] xPlanGml = createXPlanGml(version, modifiedFeatures);
			ExternalReferenceInfo externalReferenceInfoToUpdate = createExternalRefAddedOrUpdated(
					externalReferencesModified, uploadedArtefacts);
			ExternalReferenceInfo externalReferenceInfoToRemove = createExternalRefRemovedOrUpdated(
					externalReferencesModified, uploadedArtefacts, externalReferencesOriginal);
			Set<String> removedRefs = collectRemovedRefs(externalReferencesModified, externalReferencesOriginal);

			XPlanFeatureCollection modifiedPlanFc = new XPlanFeatureCollectionBuilder(modifiedFeatures, type)
					.withExternalReferenceInfo(externalReferenceInfoToUpdate).build();
			reassignFids(modifiedPlanFc);
			FeatureCollection synFc = createSynFeatures(modifiedPlanFc, version);
			String internalId = xplanDao.retrieveInternalId(planId, type);
			if (internalId != null) {
				AppSchema synSchema = managerWorkspaceWrapper.lookupStore(XPLAN_SYN, oldPlanStatus).getSchema();
				featureCollectionManipulator.addInternalId(synFc, synSchema, internalId);
			}

			// TODO: Validation required?
			PlanStatus newPlanStatus = detectNewPlanStatus(xPlanToEdit, oldLegislationStatus, oldPlanStatus);
			AdditionalPlanData xPlanMetadata = new AdditionalPlanData(newPlanStatus,
					xPlanToEdit.getValidityPeriod().getStart(), xPlanToEdit.getValidityPeriod().getEnd());
			Date sortDate = sortPropertyReader.readSortDate(type, version, modifiedFeatures);
			xplanDao.update(oldXplan, xPlanMetadata, modifiedPlanFc, synFc, xPlanGml, xPlanToEdit, sortDate,
					uploadedArtefacts, removedRefs);
			LOG.info("XPlanGML wurde erfolgreich editiert. ID: {}", planId);

			startCreationIfPlanNameHasChanged(planId, type, modifiedPlanFc, oldPlanName, oldDescription);

			xPlanRasterManager.removeRasterLayers(planId, externalReferenceInfoToRemove);
			if (makeRasterConfig) {
				XPlanArchiveContentAccess archive = new XPlanPartArchive(uploadedArtefacts);
				createRasterConfiguration(archive, modifiedPlanFc, parseInt(planId), BP_Plan, oldPlanStatus,
						newPlanStatus, sortDate);
				reloadWorkspace();
			}
			else {
				xPlanRasterManager.updateRasterLayers(parseInt(planId), type, oldPlanStatus, newPlanStatus);
			}
			LOG.info("Rasterkonfiguration für den Plan mit der ID {} wurde ausgetauscht (falls vorhanden).", planId);
		}
		finally {
			closeQuietly(originalPlan);
		}
	}

	private PlanStatus detectNewPlanStatus(XPlanToEdit xPlanToEdit, String oldLegislationStatus,
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
			return PlanStatus.findByLegislationStatusCode(newLegislationStatusCode);
		return oldPlanStatus;
	}

	private void startCreationIfPlanNameHasChanged(String planId, XPlanType type, XPlanFeatureCollection modifiedPlanFc,
			String oldPlanName, String oldDescription) throws UnknownCRSException {
		String newPlanName = retrievePlanName(modifiedPlanFc.getFeatures(), type);
		String newDescription = retrieveDescription(modifiedPlanFc.getFeatures(), type);
		if (hasChanged(oldPlanName, newPlanName) || hasChanged(oldDescription, newDescription)) {
			startCreationOfDataServicesCoupling(Integer.parseInt(planId), modifiedPlanFc,
					lookup(managerConfiguration.getRasterConfigurationCrs()));
		}
		else {
			LOG.info("Plan name does not change, creation of the service record is not required.");
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

}
