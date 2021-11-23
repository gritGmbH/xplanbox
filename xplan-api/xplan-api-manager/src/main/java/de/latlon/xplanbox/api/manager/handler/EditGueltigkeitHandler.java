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
		manager.editPlan(plan, xPlanToEdit, false, Collections.emptyList());
		return gueltigkeit;
	}

}
