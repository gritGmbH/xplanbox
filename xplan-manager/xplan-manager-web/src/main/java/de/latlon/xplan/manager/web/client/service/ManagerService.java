/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.XsrfProtect;
import de.latlon.xplan.manager.web.server.service.ManagerServiceImplException;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanNameWithStatusResult;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.RechtsstandAndPlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.core.gwt.commons.shared.InvalidParameterException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Interface of the manager to get, remove, import plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
@RemoteServiceRelativePath("manager")
@XsrfProtect
public interface ManagerService extends RemoteService {

	List<XPlan> getPlansFromManager() throws Exception;

	XPlan getPlanFromLocal();

	XPlanToEdit getPlanToEdit(String planId) throws ManagerServiceImplException;

	void editPlan(String planId, boolean updateRasterConfig, XPlanToEdit xPlanToEdit)
			throws ManagerServiceImplException;

	List<RasterEvaluationResult> evaluateRaster(String planId, XPlanToEdit xPlanToEdit)
			throws ManagerServiceImplException;

	Boolean removePlanFromManager(String planId) throws ManagerServiceImplException;

	Boolean removePlanFromFileSystem(String planId);

	Boolean importPlan(String planId, String internalId, String defaultCrs, boolean makeRasterConfig,
			PlanStatus planStatus, Date startDateTime, Date endDateTime)
			throws InvalidParameterException, ManagerServiceImplException;

	Map<String, String> retrieveMatchingInternalIds(String internalId) throws ManagerServiceImplException;

	List<Bereich> retrieveBereiche(String planId) throws Exception;

	@Deprecated
	Boolean isCrsSet(String id) throws ManagerServiceImplException;

	List<RasterEvaluationResult> evaluateRaster(String id) throws ManagerServiceImplException;

	List<PlanNameWithStatusResult> evaluatePlanNameAndStatus(String id, PlanStatus status)
			throws ManagerServiceImplException;

	RechtsstandAndPlanStatus determineLegislationStatus(String id) throws ManagerServiceImplException;

	Boolean publishPlan(String planId) throws ManagerServiceImplException;

}
