package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Re-synthesizes single or all available plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ReSythesizer {

    private static final Logger LOG = LoggerFactory.getLogger( ReSythesizer.class );

    private final XPlanDao xPlanDao;

    private final XPlanSynthesizer xPlanSynthesizer;

    private final SortPropertyReader sortPropertyReader;

    /**
     * @param dao
     *                 used to access the database, never <code>null</code>
     * @param xPlanSynthesizer
     *                 used to synthesize the plans, never <code>null</code>
     * @param sortPropertyReader
     *                 used to to retrieve the configured sort property, never <code>null</code>
     */
    public ReSythesizer( XPlanDao dao, XPlanSynthesizer xPlanSynthesizer, SortPropertyReader sortPropertyReader ) {
        this.xPlanDao = dao;
        this.xPlanSynthesizer = xPlanSynthesizer;
        this.sortPropertyReader = sortPropertyReader;
    }

    /**
     * re-synthesizes all available plans.
     */
    public void reSynthesize()
                    throws Exception {
        List<XPlan> plans = xPlanDao.getXPlanList( false );
        for ( XPlan plan : plans ) {
            reSynthesize( plan );
        }
    }

    /**
     * re-synthesizes the plan with the passed id.
     *
     * @param mgrId
     *                 the id of the plan to synthesize
     * @throws IllegalArgumentException
     *                 if a plan with the passed id is not available
     */
    public void reSynthesize( int mgrId )
                    throws Exception {
        XPlan xPlanById = xPlanDao.getXPlanById( mgrId );
        if ( xPlanById == null )
            throw new IllegalArgumentException( "A plan with the id '" + mgrId + "' is not available" );
        reSynthesize( xPlanById );
    }

    private void reSynthesize( XPlan plan )
                    throws Exception {
        LOG.debug( "Synthesize plan with id {}", plan.getId() );
        XPlanType planType = XPlanType.valueOf( plan.getType() );
        XPlanVersion version = XPlanVersion.valueOf( plan.getVersion() );

        FeatureCollection featureCollection = xPlanDao.retrieveFeatureCollection( plan );
        XPlanFeatureCollection xPlanFeatureCollection = new XPlanFeatureCollection( featureCollection, version,
                                                                                    planType );
        Date sortDate = sortPropertyReader.readSortDate( planType, version, xPlanFeatureCollection.getFeatures() );

        FeatureCollection synthesizedFeatureCollection = xPlanSynthesizer.synthesize( version, xPlanFeatureCollection );

        xPlanDao.updateXPlanSynFeatureCollection( plan, synthesizedFeatureCollection, sortDate );
    }

}
