/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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
package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.ValidityPeriod;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.api.manager.v1.model.Zeitraum;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.Collections;

/**
 * Handles editing of Zeitraum.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class EditGueltigkeitHandler extends EditHandler {

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @return all Zeitraum of the plan, never <code>null</code>
	 * @throws Exception
	 */
	public Zeitraum retrieveGueltigkeit(String planId) throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		ValidityPeriod validityPeriod = xPlanToEdit.getValidityPeriod();
		return Zeitraum.fromValidityPeriod(validityPeriod);
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param gueltigkeit the Zeitraum to update, never <code>null</code> * @return the
	 * replaced Zeitraum. nerver <code>null</code>
	 * @throws Exception
	 */
	public Zeitraum replaceGueltigkeit(String planId, Zeitraum gueltigkeit) throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		xPlanToEdit.setValidityPeriod(gueltigkeit.toValidityPeriod());
		manager.editPlan(plan, xPlanToEdit, true, Collections.emptyList());
		return gueltigkeit;
	}

}
