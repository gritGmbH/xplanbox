package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import de.latlon.xplan.manager.metadata.MetadataCouplingHandler;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadata;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadataBuilder;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDescription;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrievePlanName;
import static org.deegree.cs.persistence.CRSManager.lookup;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ServiceMetadataRecordCreator {

    private static final Logger LOG = LoggerFactory.getLogger( ServiceMetadataRecordCreator.class );

    private final XPlanDao xPlanDao;

    private final ManagerConfiguration managerConfiguration;

    private final MetadataCouplingHandler metadataCouplingHandler;

    /**
     * @param xPlanDao
     *                 used to access the database, never <code>null</code>
     * @param managerConfiguration
     *                 configuration of the manager, never <code>null</code>
     */
    public ServiceMetadataRecordCreator( XPlanDao xPlanDao, ManagerConfiguration managerConfiguration )
                    throws DataServiceCouplingException {
        this.xPlanDao = xPlanDao;
        this.managerConfiguration = managerConfiguration;
        if ( managerConfiguration == null )
            throw new IllegalArgumentException(
                            "The configuration of the feature is invalid. Service metadata records could not be created." );
        this.metadataCouplingHandler = new MetadataCouplingHandler( xPlanDao,
                                                                    managerConfiguration.getCoupledResourceConfiguration() );
    }

    /**
     * Creates service metadata records for all plans and stores additional information written to the XPlanWerkWMS capabilities document.
     */
    public void createServiceMetadataRecords()
                    throws Exception {
        List<XPlan> plans = xPlanDao.getXPlanList( false );
        for ( XPlan plan : plans ) {
            createServiceMetadataRecords( plan );
        }
    }

    /**
     * Creates service metadata records the plan with the passed and stores additional information written to the XPlanWerkWMS capabilities document
     *
     * @param mgrId
     *                 the id of the plan to synthesize
     * @throws IllegalArgumentException
     *                 if a plan with the passed id is not available
     */
    public void createServiceMetadataRecords( int mgrId )
                    throws Exception {
        XPlan xPlanById = xPlanDao.getXPlanById( mgrId );
        if ( xPlanById == null )
            throw new IllegalArgumentException( "A plan with the id '" + mgrId + "' is not available" );
        createServiceMetadataRecords( xPlanById );
    }

    private void createServiceMetadataRecords( XPlan plan )
                    throws Exception {
        int id = Integer.parseInt( plan.getId() );
        String name = plan.getName();
        LOG.debug( "Process plan with id {} and name {}", id, name );

        XPlanType type = XPlanType.valueOf( plan.getType() );
        FeatureCollection features = xPlanDao.retrieveFeatureCollection( plan );
        XPlanFeatureCollection xPlanFeatureCollection = new XPlanFeatureCollectionBuilder( features, type ).build();

        String planName = retrievePlanName( features, type );
        String description = retrieveDescription( features, type );
        Envelope envelope = xPlanFeatureCollection.getBboxIn4326();

        CoupledResourceConfiguration coupledResourceConfiguration = managerConfiguration.getCoupledResourceConfiguration();
        PlanwerkServiceMetadataBuilder builder = new PlanwerkServiceMetadataBuilder( type, planName, description, envelope,
                                                                                     coupledResourceConfiguration );
        PlanwerkServiceMetadata planwerkServiceMetadata = builder.build(
                        lookup( managerConfiguration.getRasterConfigurationCrs() ) );
        metadataCouplingHandler.processMetadataCoupling( id, name, planwerkServiceMetadata );
    }

}
