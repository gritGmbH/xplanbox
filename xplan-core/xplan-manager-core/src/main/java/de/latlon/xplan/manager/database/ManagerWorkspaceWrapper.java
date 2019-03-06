package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreProvider;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.INSPIREPLU_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN2ARCHIVE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN2PRE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN2_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN3ARCHIVE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN3PRE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN3_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN40ARCHIVE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN40PRE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN40_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN41ARCHIVE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN41ARCHIVE_NSM_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN41PRE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN41PRE_NSM_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN41_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN41_NSM_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN50ARCHIVE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN50PRE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN50_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN51ARCHIVE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN51PRE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLAN51_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLANSYNARCHIVE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLANSYNPRE_FS_ID;
import static de.latlon.xplan.manager.database.FeatureStoreResourceNames.XPLANSYN_FS_ID;
import static de.latlon.xplan.manager.web.shared.PlanStatus.ARCHIVIERT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ManagerWorkspaceWrapper {

    private static final Logger LOG = LoggerFactory.getLogger( ManagerWorkspaceWrapper.class );

    private static final String JDBC_POOL_ID = "xplan";

    private Workspace managerWorkspace;

    private ManagerConfiguration managerConfiguration;

    public ManagerWorkspaceWrapper( Workspace managerWorkspace, ManagerConfiguration managerConfiguration ) {
        this.managerWorkspace = managerWorkspace;
        this.managerConfiguration = managerConfiguration;
    }

    /**
     * Ensures that the workspace is initialised.
     */
    public void ensureWorkspaceInitialized() {
        try {
            managerWorkspace.getResource( ConnectionProviderProvider.class, JDBC_POOL_ID );
        } catch ( Exception e ) {
            long begin = System.currentTimeMillis();
            LOG.info( "- Initialisiere Feature Stores..." );
            managerWorkspace.initAll();
            long elapsed = System.currentTimeMillis() - begin;
            LOG.info( "OK [" + elapsed + " ms]" );
        }
    }

    /**
     * Opens a connection to the 'xplan' jdbc resource in the workspace.
     *
     * @return opened connection
     */
    public Connection openConnection() {
        ensureWorkspaceInitialized();
        ConnectionProvider resource = managerWorkspace.getResource( ConnectionProviderProvider.class, JDBC_POOL_ID );
        return resource.getConnection();
    }

    /**
     * Looks up a FeatureStore by the version, ade and type.
     *
     * @param version
     *                 XPlan version, never <code>null</code>
     * @param ade
     *                 XPlan ADE extension, may be <code>null</code>
     * @param planStatus
     *                 xplan status, if <code>null</code>, default store is chosen (FIX)
     * @return the FeatureStore fitting to version and ade, never <code>null</code>
     * @throws IllegalArgumentException
     *                 if a FeatureStore can not be found
     */
    public FeatureStore lookupStore( XPlanVersion version, XPlanAde ade, PlanStatus planStatus ) {
        switch ( version ) {
        case XPLAN_2:
            return decideIfPreStore( planStatus, XPLAN2_FS_ID, XPLAN2PRE_FS_ID, XPLAN2ARCHIVE_FS_ID );
        case XPLAN_3:
            return decideIfPreStore( planStatus, XPLAN3_FS_ID, XPLAN3PRE_FS_ID, XPLAN3ARCHIVE_FS_ID );
        case XPLAN_40:
            return decideIfPreStore( planStatus, XPLAN40_FS_ID, XPLAN40PRE_FS_ID, XPLAN40ARCHIVE_FS_ID );
        case XPLAN_41:
            if ( XPlanAde.NSM.equals( ade ) ) {
                return decideIfPreStore( planStatus, XPLAN41_NSM_FS_ID, XPLAN41PRE_NSM_FS_ID,
                                         XPLAN41ARCHIVE_NSM_FS_ID );
            } else {
                return decideIfPreStore( planStatus, XPLAN41_FS_ID, XPLAN41PRE_FS_ID, XPLAN41ARCHIVE_FS_ID );
            }
        case XPLAN_50:
            return decideIfPreStore( planStatus, XPLAN50_FS_ID, XPLAN50PRE_FS_ID, XPLAN50ARCHIVE_FS_ID );
        case XPLAN_51:
            return decideIfPreStore( planStatus, XPLAN51_FS_ID, XPLAN51PRE_FS_ID, XPLAN51ARCHIVE_FS_ID );
        case XPLAN_SYN:
            return decideIfPreStore( planStatus, XPLANSYN_FS_ID, XPLANSYNPRE_FS_ID, XPLANSYNARCHIVE_FS_ID );
        }
        throw new IllegalArgumentException();
    }

    /**
     * @return the INSPIRE PLU FeatureStore, never <code>null</code>
     */
    public FeatureStore lookupInspirePluStore() {
        return lookupStore( INSPIREPLU_FS_ID );
    }

    private FeatureStore lookupStore( String id ) {
        ensureWorkspaceInitialized();
        FeatureStore sfs = managerWorkspace.getResource( FeatureStoreProvider.class, id );
        if ( sfs == null ) {
            LOG.debug( "Can't get Feature Store '" + id + "', available Feature Stores:" );
            throw new IllegalArgumentException( "Wrong FeatureStore Id " + id );
        }
        return sfs;
    }

    private FeatureStore decideIfPreStore( PlanStatus planStatus, String store, String preStore, String archiveStore ) {
        if ( managerConfiguration.isSeperatedDataManagementActived() ) {
            if ( IN_AUFSTELLUNG.equals( planStatus ) )
                return lookupStore( preStore );
            else if ( ARCHIVIERT.equals( planStatus ) )
                return lookupStore( archiveStore );
        }
        return lookupStore( store );
    }

}