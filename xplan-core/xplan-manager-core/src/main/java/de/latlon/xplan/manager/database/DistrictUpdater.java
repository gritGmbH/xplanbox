package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDistrict;

/**
 * Updates the district column in table xplanmgr.plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DistrictUpdater {

    private static final Logger LOG = LoggerFactory.getLogger( DistrictUpdater.class );

    private XPlanDao dao;

    /**
     * @param dao
     *                 used to access the database, never <code>null</code>
     */
    public DistrictUpdater( XPlanDao dao ) {
        this.dao = dao;
    }

    /**
     * Retrieves all plans from the manager store, parses the district from the plan and updates the district column in the table xplanmgr.plans.
     */
    public void updateDistricts()
                    throws Exception {
        List<XPlan> plans = dao.getXPlanList( false );
        for ( XPlan plan : plans ) {
            LOG.debug( "Update district of plan with id {}", plan.getId() );
            FeatureCollection featureCollection = dao.retrieveFeatureCollection( plan );
            XPlanType planType = XPlanType.valueOf( plan.getType() );
            XPlanVersion version = XPlanVersion.valueOf( plan.getVersion() );
            String district = retrieveDistrict( featureCollection, planType, version );
            dao.updateDistrict( plan, district );
        }
    }
}
