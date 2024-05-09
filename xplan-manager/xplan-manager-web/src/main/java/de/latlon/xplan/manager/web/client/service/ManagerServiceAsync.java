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

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanNameWithStatusResult;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.RechtsstandAndPlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Async interface for {@link ManagerService}.
 */
public interface ManagerServiceAsync {

	void getPlansFromManager(AsyncCallback<List<XPlan>> callback);

	void getPlanFromLocal(AsyncCallback<XPlan> callback);

	void getPlanToEdit(String planId, AsyncCallback<XPlanToEdit> callback);

	void editPlan(String planId, boolean updateRasterConfig, XPlanToEdit xPlanToEdit, AsyncCallback<Void> callback);

	void evaluateRaster(String planId, XPlanToEdit xPlanToEdit, AsyncCallback<List<RasterEvaluationResult>> callback);

	void removePlanFromManager(String planId, AsyncCallback<Boolean> callback);

	void removePlanFromFileSystem(String planId, AsyncCallback<Boolean> callback);

	void importPlan(String planId, String internalId, String defaultCrs, boolean makeRasterConfig,
			PlanStatus planStatus, Date startDateTime, Date endDateTime, AsyncCallback<Boolean> callback);

	void retrieveBereiche(String id, AsyncCallback<List<Bereich>> alertFailureCallback);

	void retrieveMatchingInternalIds(String internalId, AsyncCallback<Map<String, String>> callback);

	@Deprecated
	void isCrsSet(String id, AsyncCallback<Boolean> callback);

	void evaluateRaster(String id, AsyncCallback<List<RasterEvaluationResult>> callback);

	void evaluatePlanNameAndStatus(String id, PlanStatus status,
			AsyncCallback<List<PlanNameWithStatusResult>> callback);

	void determineLegislationStatus(String id, AsyncCallback<RechtsstandAndPlanStatus> callback);

	void publishPlan(String planId, AsyncCallback<Boolean> callback);

}
