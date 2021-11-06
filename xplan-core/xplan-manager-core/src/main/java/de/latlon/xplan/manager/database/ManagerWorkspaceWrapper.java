/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.web.shared.PlanStatus.ARCHIVIERT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ManagerWorkspaceWrapper {

    private static final Logger LOG = LoggerFactory.getLogger( ManagerWorkspaceWrapper.class );

    private static final String JDBC_POOL_ID = "xplan";

    private static final String INSPIREPLU_FS_ID = "inspireplu";

    private static final String PRE_SUFFIX = "pre";

    private static final String ARCHIVE_SUFFIX = "archive";

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
        String store = version.name().replace( "_", "" ).toLowerCase();
        if ( XPLAN_41.equals( version ) && XPlanAde.NSM.equals( ade ) ) {
            store = "xplan41nsm";
        }
        if ( managerConfiguration.isSeperatedDataManagementActived() ) {
            if ( IN_AUFSTELLUNG.equals( planStatus ) )
                return lookupStore( store + PRE_SUFFIX );
            else if ( ARCHIVIERT.equals( planStatus ) )
                return lookupStore( store + ARCHIVE_SUFFIX );
        }
        return lookupStore( store );
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
            LOG.debug( "Feature Store '" + id + "' is not available" );
            throw new IllegalArgumentException( "Wrong FeatureStore Id " + id );
        }
        return sfs;
    }

    public Workspace getWorkspace() {return this.managerWorkspace;}

    public ManagerConfiguration getConfiguration() {return this.managerConfiguration;}

}
