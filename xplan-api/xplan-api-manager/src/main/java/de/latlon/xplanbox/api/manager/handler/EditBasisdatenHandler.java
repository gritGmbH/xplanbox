package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.BaseData;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.api.manager.v1.model.Basisdaten;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.Collections;

/**
 * Handles editing of Basisdaten.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class EditBasisdatenHandler extends EditHandler {

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @return all Basisdaten of the plan, never <code>null</code>
     * @throws Exception
     */
    public Basisdaten retrieveBasisdaten( String planId )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        BaseData baseData = xPlanToEdit.getBaseData();
        return Basisdaten.fromBaseData( baseData );
    }

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @param basisdaten
     *                 the Basisdaten to update, never <code>null</code>
     * @return the replaced Basisdaten. nerver <code>null</code>
     * @throws Exception
     */
    public Basisdaten replaceBasisdaten( String planId,
                                         Basisdaten basisdaten )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        xPlanToEdit.setBaseData( basisdaten.toBaseData() );
        manager.editPlan( plan, xPlanToEdit, false, Collections.emptyList() );
        return basisdaten;
    }
}
