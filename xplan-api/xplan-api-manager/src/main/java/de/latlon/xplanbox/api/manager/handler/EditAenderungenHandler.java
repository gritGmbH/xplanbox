package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.api.manager.v1.model.Aenderungen;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

/**
 * Handles editing of Aenderungen.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class EditAenderungenHandler extends EditHandler {

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @return all Aenderungen of the plan, never <code>null</code>
     * @throws Exception
     */
    public Aenderungen retrieveAenderungen( String planId )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        List<Change> changes = xPlanToEdit.getChanges();
        return Aenderungen.fromChanges( changes );
    }

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @param aenderungen
     *                 the Aenderungen to update, never <code>null</code>
     * @return the replaced Aenderungen. nerver <code>null</code>
     * @throws Exception
     */
    public Aenderungen replaceAenderungen( String planId,
                                           Aenderungen aenderungen )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        xPlanToEdit.setChanges( aenderungen.toChanges() );
        manager.editPlan( plan, xPlanToEdit, false, Collections.emptyList() );
        return aenderungen;
    }

}
