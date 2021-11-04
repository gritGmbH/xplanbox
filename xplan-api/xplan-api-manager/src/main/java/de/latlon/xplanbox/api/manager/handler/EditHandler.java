package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanId;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class EditHandler {

    private static final Logger LOG = getLogger( EditHandler.class );

    @Autowired
    protected XPlanManager manager;

    public XPlan findPlanById( String planId )
                    throws Exception {
        LOG.info( "Find plan by Id '{}'", planId );
        int id = Integer.parseInt( planId );
        return findPlanById( id );
    }

    private XPlan findPlanById( int id )
                    throws Exception {
        XPlan xPlanById = manager.getXPlanById( id );
        if ( xPlanById == null ) {
            throw new InvalidPlanId( id );
        }
        return xPlanById;
    }
}
