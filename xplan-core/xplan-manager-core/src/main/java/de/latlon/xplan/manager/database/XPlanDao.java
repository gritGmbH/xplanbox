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

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	protected final XPlanDbAdapter xPlanDbAdapter;

	protected final XPlanWfsAdapter xPlanWfsAdapter;

	protected final XPlanSynWfsAdapter xPlanSynWfsAdapter;

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
	public int getPlanIdOfMoreRecentRasterPlan(Date releaseDate) {
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
	 * exports a plan
	 * @param planId of plan to export
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<String> retrieveAllXPlanArtefactFileNames(int planId) {
		return xPlanDbAdapter.selectAllXPlanArtefactFileNames(planId);
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
	 * Retrieve internalId by the manager id from xplanmgr.plans.
	 * @param planId the planId of the plan, never <code>null</code>
	 * @return the internal id of a plan (if available), <code>null</code> if an error
	 * occurred
	 */
	public Date retrieveSortDate(int planId) {
		return xPlanDbAdapter.selectSortDate(planId);
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

	public boolean existsPlan(int planId) {
		return xPlanDbAdapter.existsPlan(planId);
	}

	protected int getXPlanIdAsInt(String planId) throws Exception {
		try {
			return Integer.parseInt(planId);
		}
		catch (NumberFormatException e) {
			throw new Exception("Spezifizierter Wert '" + planId + "' ist keine gültige XPlan-Id (Ganzzahl).", e);
		}
	}

}
