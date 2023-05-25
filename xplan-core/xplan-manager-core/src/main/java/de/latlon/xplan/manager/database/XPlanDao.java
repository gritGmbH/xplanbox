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
package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.core.manager.db.model.ArtefactType;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.namespace.QName;
import java.io.File;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer.SYN_FEATURETYPE_PREFIX;

/**
 * DAO class for xplans.
 *
 * @author Florian Bingel
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanDao {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanDao.class);

	private final FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

	private final XPlanDbAdapter xPlanDbAdapter;

	private final XPlanWfsAdapter xPlanWfsAdapter;

	private final XPlanSynWfsAdapter xPlanSynWfsAdapter;

	private final XPlanInspirePluAdapter xPlanInspirePluAdapter;

	/**
	 * Creates a new {@link XPlanDao} instance.
	 * <p>
	 * The DAO performs the initialization of the JDBC connection and feature stores on
	 * demand.
	 * </p>
	 * @param managerWorkspaceWrapper workspace, never <code>null</code>
	 * @param xPlanDbAdapter never <code>null</code>
	 * @param applicationEventPublisher
	 */
	public XPlanDao(ManagerWorkspaceWrapper managerWorkspaceWrapper, XPlanDbAdapter xPlanDbAdapter,
			ApplicationEventPublisher applicationEventPublisher) {
		this.xPlanDbAdapter = xPlanDbAdapter;
		this.xPlanWfsAdapter = new XPlanWfsAdapter(managerWorkspaceWrapper, applicationEventPublisher);
		this.xPlanSynWfsAdapter = new XPlanSynWfsAdapter(managerWorkspaceWrapper, applicationEventPublisher);
		this.xPlanInspirePluAdapter = new XPlanInspirePluAdapter(managerWorkspaceWrapper, applicationEventPublisher);
	}

	/**
	 * Stores the given XPlan in the database (and feature stores).
	 * @param archive plan archive, must not be <code>null</code>
	 * @param fc features of the main GML document from the archive, must not be
	 * <code>null</code>
	 * @param synFc flattened features (of the main GML document from the archive), must
	 * not be <code>null</code>
	 * @param planStatus the status of the plan, may be <code>null</code>
	 * @param beginValidity the start of the validity, may be <code>null</code>
	 * @param beginValidity the end of the validity, may be <code>null</code>
	 * <code>null</code>
	 * @param internalId
	 * @return database id of the plan
	 */
	@Transactional(propagation = Propagation.MANDATORY)
	public int insert(XPlanArchive archive, XPlanFeatureCollection fc, FeatureCollection synFc, PlanStatus planStatus,
			Date beginValidity, Date endValidity, Date sortDate, String internalId) throws Exception {
		try {
			LOG.info("Insert XPlan");
			long begin = System.currentTimeMillis();

			List<String> fidsXPlanWfs = xPlanWfsAdapter.insert(fc, planStatus);
			int planId = xPlanDbAdapter.insert(archive, fc, synFc, planStatus, beginValidity, endValidity, sortDate,
					internalId, fidsXPlanWfs);
			addAdditionalProperties(synFc, beginValidity, endValidity, planId, sortDate);
			xPlanSynWfsAdapter.insert(synFc, planStatus);
			xPlanDbAdapter.insertArtefacts(fc, archive, planId);

			long elapsed = System.currentTimeMillis() - begin;
			LOG.info("OK [" + elapsed + " ms].");
			return planId;
		}
		catch (AmbiguousBereichNummernException e) {
			throw e;
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Einfügen: " + e.getMessage(), e);
		}
	}

	public void insertInspirePlu(FeatureCollection featureCollection) throws Exception {
		xPlanInspirePluAdapter.insertInspirePlu(featureCollection);
	}

	/**
	 * Inserts in planwerkwmsmetadata
	 * @param planId the id of the plan to insert an
	 * @param title to insert, may be <code>null</code>
	 * @param resourceIdentifier to insert, may be <code>null</code>
	 * @param datasetMetadataUrl to insert, may be <code>null</code>
	 * @param serviceMetadataUrl to insert, may be <code>null</code>
	 * @throws Exception
	 */
	public void insertOrReplacePlanWerkWmsMetadata(int planId, String title, String resourceIdentifier,
			String datasetMetadataUrl, String serviceMetadataUrl) {
		xPlanDbAdapter.insertOrReplacePlanWerkWmsMetadata(planId, title, resourceIdentifier, datasetMetadataUrl,
				serviceMetadataUrl);
	}

	/**
	 * Deletes the specified plan from the database (and feature stores).
	 * @param planId database id of the plan
	 */
	public void deletePlan(String planId) throws Exception {
		LOG.info("Delete XPlan {}", planId);
		int planIdAsInt = getXPlanIdAsInt(planId);
		XPlanVersionAndPlanStatus xPlanMetadata = xPlanDbAdapter.selectXPlanMetadata(planIdAsInt);
		Set<String> fids = xPlanDbAdapter.selectFids(planIdAsInt);
		xPlanSynWfsAdapter.deletePlan(xPlanMetadata, fids, planIdAsInt);
		xPlanWfsAdapter.deletePlan(planIdAsInt, xPlanMetadata.version, xPlanMetadata.planStatus, fids);
		xPlanDbAdapter.deletePlan(planIdAsInt);
	}

	/**
	 * @param oldXplan the {@link XPlan} describing the plan before update, never
	 * <code>null</code>
	 * @param newAdditionalPlanData of the {@link XPlan} with the updated values, never
	 * <code>null</code>
	 * @param fc the edited feature collection, never <code>null</code>
	 * @param synFc the edited feature collection with synthesized features, never
	 * <code>null</code>
	 * @param planArtefact the edited xplan gml, never <code>null</code>
	 * @param sortDate the date added to syn feature collection, may be <code>null</code>
	 * @param removedRefs
	 * @throws Exception
	 */
	public void update(XPlan oldXplan, AdditionalPlanData newAdditionalPlanData, XPlanFeatureCollection fc,
			FeatureCollection synFc, byte[] planArtefact, XPlanToEdit xPlanToEdit, Date sortDate,
			List<File> uploadedArtefacts, Set<String> removedRefs) throws Exception {
		try {
			LOG.info("Delete XPlan {}", oldXplan.getId());
			long begin = System.currentTimeMillis();

			int planId = getXPlanIdAsInt(oldXplan.getId());
			Set<String> oldFids = xPlanDbAdapter.selectFids(planId);

			xPlanDbAdapter.update(oldXplan, newAdditionalPlanData, fc, synFc, planArtefact, xPlanToEdit, sortDate,
					uploadedArtefacts, removedRefs);
			addAdditionalProperties(synFc, newAdditionalPlanData.getStartDateTime(),
					newAdditionalPlanData.getEndDateTime(), planId, sortDate);

			List<String> newFids = xPlanSynWfsAdapter.update(planId, oldXplan, newAdditionalPlanData, synFc, oldFids);
			xPlanWfsAdapter.update(planId, oldXplan, newAdditionalPlanData, fc, oldFids);
			xPlanDbAdapter.updateFids(planId, newFids);

			long elapsed = System.currentTimeMillis() - begin;
			LOG.info("OK [" + elapsed + " ms].");
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Einfügen: " + e.getMessage(), e);
		}
	}

	/**
	 * @param xplan to update, never <code>null</code>
	 * @param synFc to update, never <code>null</code>
	 * @param sortDate may be <code>null</code>
	 * @throws Exception
	 */
	public void updateXPlanSynFeatureCollection(XPlan xplan, FeatureCollection synFc, XPlanFeatureCollection originalFc,
			Date sortDate, boolean updateFeaturesAndBlob) throws Exception {
		int planId = getXPlanIdAsInt(xplan.getId());
		AdditionalPlanData xplanMetadata = xplan.getXplanMetadata();
		PlanStatus planStatus = xplanMetadata.getPlanStatus();

		Set<String> ids = xPlanDbAdapter.selectFids(planId);

		addAdditionalProperties(synFc, xplanMetadata.getStartDateTime(), xplanMetadata.getEndDateTime(), planId,
				sortDate);

		if (updateFeaturesAndBlob) {
			List<String> newFids = xPlanWfsAdapter.update(planId, planStatus, originalFc, ids);

			AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
			List<QName> featureTypeNames = Arrays.stream(schema.getFeatureTypes())
					.map(featureType -> featureType.getName()).collect(Collectors.toList());

			Set<String> validIds = ids.stream().filter(oldFeatureId -> {
				Optional<QName> featureType = featureTypeNames.stream()
						.filter(featureTypeName -> oldFeatureId
								.startsWith(SYN_FEATURETYPE_PREFIX + featureTypeName.getLocalPart().toUpperCase()))
						.findFirst();
				if (featureType.isPresent()) {
					return true;
				}
				LOG.info("Es konnte kein feature type zu dem feature mit der ID " + oldFeatureId
						+ " gefunden werden. Es wird angenommen, dass dieser FeatureType nicht mehr existiert und die dazugehoerige Tabelle bereits geloescht wurde.");
				return false;
			}).collect(Collectors.toSet());

			xPlanSynWfsAdapter.update(planId, planStatus, synFc, validIds);
			xPlanDbAdapter.updateFids(planId, newFids);
		}
		else {
			xPlanSynWfsAdapter.update(planId, planStatus, synFc, ids);
		}

	}

	/**
	 * Inserts the passed featureCollection in the datastore specified by the version of
	 * the featureCollection and passed planStatus.
	 * @param xPlanFeatureCollection the featureCollection of the updated plan, never
	 * <code>null</code>
	 * @param planStatus of the plan, never <code>null</code>
	 */
	public void insertXPlanFeatureCollection(XPlanFeatureCollection xPlanFeatureCollection, PlanStatus planStatus)
			throws Exception {
		xPlanWfsAdapter.insert(xPlanFeatureCollection, planStatus);
	}

	/**
	 * Inserts the passed featureCollection in the datastore specified by the version of
	 * the featureCollection and passed planStatus.
	 * @param planId the featureCollection of the updated plan, never <code>null</code>
	 * @param planStatus
	 * @param fids to remove
	 */
	public void deleteXPlanFeatureCollection(int planId, XPlanVersion version, PlanStatus planStatus, Set<String> fids)
			throws Exception {
		xPlanWfsAdapter.deletePlan(planId, version, planStatus, fids);
	}

	/**
	 * Retrieve a list of all XPlans.
	 * @return list of XPlans
	 * @throws Exception
	 */
	public List<XPlan> getXPlanList() throws Exception {
		return xPlanDbAdapter.selectAllXPlans();
	}

	/**
	 * Retrieve a single {@link XPlan} by id.
	 * @param planId id of a plan, must not be <code>null</code>
	 * @return a single plan
	 * @throws Exception
	 */
	public XPlan getXPlanById(int planId) {
		return xPlanDbAdapter.selectXPlanById(planId);
	}

	public List<XPlan> getXPlanByName(String planName) {
		return xPlanDbAdapter.getXPlanByName(planName);
	}

	public List<XPlan> getXPlansLikeName(String planName) {
		return xPlanDbAdapter.getXPlansLikeName(planName);
	}

	/**
	 * retrieves the id of the plan closest in future to the date passed
	 * @param releaseDate minimal release date
	 * @return id of plan with minimal release date
	 * @throws SQLException
	 */
	public String getPlanIdOfMoreRecentRasterPlan(Date releaseDate) {
		return xPlanDbAdapter.selectXPlanIdOfMoreRecentRasterPlan(releaseDate);
	}

	/**
	 * exports a plan
	 * @param planId of plan to export
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<Artefact> retrieveAllXPlanArtefacts(String planId) throws Exception {
		int xPlanIdAsInt = getXPlanIdAsInt(planId);
		return xPlanDbAdapter.selectAllXPlanArtefacts(xPlanIdAsInt).collect(Collectors.toList());
	}

	/**
	 * @param xPlanById the id of the requested plan, never <code>null</code>
	 * @return the restored feature collection from xplan wfs datastore, never
	 * <code>null</code>
	 * @throws Exception
	 */
	public FeatureCollection retrieveFeatureCollection(XPlan xPlanById) throws Exception {
		int xPlanIdAsInt = getXPlanIdAsInt(xPlanById.getId());
		XPlanVersionAndPlanStatus xPlanMetadata = xPlanDbAdapter.selectXPlanMetadata(xPlanIdAsInt);
		Set<String> ids = xPlanDbAdapter.selectFids(xPlanIdAsInt);
		return xPlanWfsAdapter.restoreFeatureCollection(xPlanMetadata.version, xPlanMetadata.planStatus, ids);
	}

	/**
	 * @param planId the id of the requested plan, <code>null</code>
	 * @return the original plan artefact, never <code>null</code>
	 * @throws Exception
	 */
	public InputStream retrieveXPlanArtefact(String planId) throws Exception {
		int planIdAsInt = getXPlanIdAsInt(planId);
		return retrieveXPlanArtefact(planIdAsInt);
	}

	/**
	 * @param planId the id of the requested plan, <code>null</code>
	 * @return the original plan artefact, never <code>null</code>
	 * @throws Exception
	 */
	public InputStream retrieveXPlanArtefact(int planId) throws Exception {
		return xPlanDbAdapter.selectXPlanGmlArtefact(planId);
	}

	/**
	 * Retrieve internalId by the manager id from xplanmgr.plans.
	 * @param planId the planId of the plan, never <code>null</code>
	 * @return the internal id of a plan (if available), <code>null</code> if an error
	 * occurred
	 */
	public String retrieveInternalId(int planId) {
		return xPlanDbAdapter.selectInternalId(planId);
	}

	/**
	 * Updates the district column of the table xplanmgr.plans.
	 * @param plan the plan to update, never <code>null</code>
	 * @param district the new district, may be <code>null</code>
	 * @throws Exception
	 */
	public void updateDistrict(XPlan plan, String district) throws Exception {
		int planIdAsInt = getXPlanIdAsInt(plan.getId());
		xPlanDbAdapter.updateDistrict(planIdAsInt, district);
	}

	/**
	 * Updates the bereiche of the plan.
	 * @param plan the plan to update, never <code>null</code>
	 * @param bereiche the bereiche, never <code>null</code>
	 * @throws Exception
	 */
	public void updateBereiche(XPlan plan, List<Bereich> bereiche) throws Exception {
		int planIdAsInt = getXPlanIdAsInt(plan.getId());
		xPlanDbAdapter.updateBereiche(planIdAsInt, bereiche);
	}

	/**
	 * Updates the column artefacttype of the table xplanmgr.artefacts.
	 * @param planId of the plan to update, never <code>null</code>
	 * @param fileNames the fileNames to update, never <code>null</code>
	 * @param artefactType the artefactType to set, never <code>null</code>
	 * @throws Exception
	 */
	public void updateArtefacttype(String planId, List<String> fileNames, ArtefactType artefactType) throws Exception {
		int planIdAsInt = getXPlanIdAsInt(planId);
		xPlanDbAdapter.updateArtefacttype(planIdAsInt, fileNames, artefactType);
	}

	/**
	 * @param planId of the plan to set the status
	 * @throws SQLException if the sql could not be executed
	 */
	public void setPlanWasInspirePublished(String planId) throws Exception {
		int planIdAsInt = getXPlanIdAsInt(planId);
		xPlanDbAdapter.updatePlanWasInspirePublished(planIdAsInt);
	}

	public boolean checkIfPlanWithSameNameAndStatusExists(String planName, String status) {
		return xPlanDbAdapter.selectPlanWithSameNameAndStatusExists(planName, status);
	}

	public boolean existsPlan(String planId) throws Exception {
		int planIdAsInt = getXPlanIdAsInt(planId);
		return xPlanDbAdapter.existsPlan(planIdAsInt);
	}

	private void addAdditionalProperties(FeatureCollection synFc, Date beginValidity, Date endValidity, int planId,
			Date sortDate) {
		AppSchema schema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
		featureCollectionManipulator.addAdditionalPropertiesToFeatures(synFc, schema, planId, sortDate, beginValidity,
				endValidity);
	}

	private int getXPlanIdAsInt(String planId) throws Exception {
		try {
			return Integer.parseInt(planId);
		}
		catch (NumberFormatException e) {
			throw new Exception("Spezifizierter Wert '" + planId + "' ist keine gültige XPlan-Id (Ganzzahl).", e);
		}
	}

}
