package de.latlon.xplan.manager.database;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static de.latlon.xplan.commons.archive.XPlanArchiveCreator.MAIN_FILE;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveAdditionalType;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDistrict;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveLegislationStatus;
import static de.latlon.xplan.manager.database.DatabaseUtils.closeQuietly;
import static de.latlon.xplan.manager.database.DatabaseUtils.openConnection;
import static de.latlon.xplan.manager.web.shared.PlanStatus.ARCHIVIERT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;
import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.copyLarge;
import static org.deegree.protocol.wfs.transaction.action.IDGenMode.USE_EXISTING;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.io.IOUtils;
import org.deegree.commons.utils.Pair;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreException;
import org.deegree.feature.persistence.FeatureStoreProvider;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.feature.persistence.query.Query;
import org.deegree.feature.persistence.sql.SQLFeatureStoreTransaction;
import org.deegree.feature.types.AppSchema;
import org.deegree.filter.IdFilter;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.io.WKTReader;
import org.deegree.geometry.io.WKTWriter;
import org.deegree.protocol.wfs.getfeature.TypeName;
import org.deegree.protocol.wfs.transaction.action.IDGenMode;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vividsolutions.jts.io.ParseException;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.export.DatabaseXPlanArtefactIterator;
import de.latlon.xplan.manager.export.XPlanArchiveContent;
import de.latlon.xplan.manager.export.XPlanArtefactIterator;
import de.latlon.xplan.manager.export.XPlanExportException;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.XPlanEnvelope;
import de.latlon.xplan.manager.web.shared.edit.AbstractReference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

/**
 * DAO class for xplans.
 * 
 * @author Florian Bingel
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public class XPlanDao {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanDao.class );

    public static final String JDBC_POOL_ID = "xplan";

    private static final String XPLAN2_FS_ID = "xplan2";

    private static final String XPLAN3_FS_ID = "xplan3";

    private static final String XPLAN40_FS_ID = "xplan40";
    
    private static final String XPLAN41_FS_ID = "xplan41";

    private static final String XPLAN41_NSM_FS_ID = "xplan41nsm";

    private static final String XPLAN50_FS_ID = "xplan50";

    private static final String XPLAN51_FS_ID = "xplan51";
    
    private static final String XPLANSYN_FS_ID = "xplansyn";

    private static final String XPLAN2PRE_FS_ID = "xplan2pre";

    private static final String XPLAN3PRE_FS_ID = "xplan3pre";

    private static final String XPLAN40PRE_FS_ID = "xplan40pre";

    private static final String XPLAN41PRE_FS_ID = "xplan41pre";

    private static final String XPLAN41PRE_NSM_FS_ID = "xplan41nsmpre";

    private static final String XPLAN50PRE_FS_ID = "xplan50pre";

    private static final String XPLAN51PRE_FS_ID = "xplan51pre";

    private static final String XPLANSYNPRE_FS_ID = "xplansynpre";

    private static final String XPLAN2ARCHIVE_FS_ID = "xplan2archive";

    private static final String XPLAN3ARCHIVE_FS_ID = "xplan3archive";

    private static final String XPLAN40ARCHIVE_FS_ID = "xplan40archive";

    private static final String XPLAN41ARCHIVE_FS_ID = "xplan41archive";

    private static final String XPLAN41ARCHIVE_NSM_FS_ID = "xplan41nsmarchive";

    private static final String XPLAN50ARCHIVE_FS_ID = "xplan50archive";

    private static final String XPLAN51ARCHIVE_FS_ID = "xplan51archive";

    private static final String XPLANSYNARCHIVE_FS_ID = "xplansynarchive";

    private static final String INSPIREPLU_FS_ID = "inspireplu";

    private final Workspace ws;

    private final CategoryMapper categoryMapper;

    private final ManagerConfiguration managerConfiguration;

    private FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

    /**
     * Creates a new {@link XPlanDao} instance.
     * <p>
     * The DAO performs the initialization of the JDBC connection and feature stores on demand.
     * </p>
     * 
     * @param ws
     *            workspace, never <code>null</code>
     * @param categoryMapper
     *            mapping configuration, never <code>null</code>
     * @param managerConfiguration
     */
    public XPlanDao( Workspace ws, CategoryMapper categoryMapper, ManagerConfiguration managerConfiguration ) {
        this.ws = ws;
        this.categoryMapper = categoryMapper;
        this.managerConfiguration = managerConfiguration;
    }

    /**
     * Stores the given XPlan in the database (and feature stores).
     * 
     * @param archive
     *            plan archive, must not be <code>null</code>
     * @param fc
     *            features of the main GML document from the archive, must not be <code>null</code>
     * @param synFc
     *            flattened features (of the main GML document from the archive), must not be <code>null</code>
     * @param xPlanMetadata
     *            containing some metadata about the xplan, never <code>null</code>
     * 
     * @return database id of the plan
     */
    public int insert( de.latlon.xplan.commons.archive.XPlanArchive archive,
                       de.latlon.xplan.commons.XPlanFeatureCollection fc, FeatureCollection synFc,
                       de.latlon.xplan.manager.web.shared.XPlanMetadata xPlanMetadata, Date sortDate )
                                       throws Exception {
        Connection conn = null;
        try {
            LOG.info( "Insert XPlan" );

            PlanStatus planStatus = xPlanMetadata.getPlanStatus();
            FeatureStore xplanFs = lookupStore( archive.getVersion(), archive.getAde(), planStatus );
            FeatureStore synFs = lookupStore( XPLAN_SYN, null, planStatus );

            conn = openConnection( ws, JDBC_POOL_ID );
            conn.setAutoCommit( false );
            Pair<List<String>, SQLFeatureStoreTransaction> fidsAndXPlanTA = insertXPlan( archive.getVersion(), xplanFs,
                                                                                         fc );
            int planId = insertMetadata( conn, archive, fc, synFc, fidsAndXPlanTA.first, xPlanMetadata, sortDate );

            addAdditionalProperties( synFc, xPlanMetadata, synFs, planId, sortDate );

            Pair<List<String>, SQLFeatureStoreTransaction> fidsAndXPlanSynTA = insertXPlanSyn( synFs, synFc );

            insertArtefacts( archive, conn, planId );

            long begin = System.currentTimeMillis();
            LOG.info( "- Persistierung..." );

            // commit transactions
            fidsAndXPlanTA.second.commit();
            fidsAndXPlanSynTA.second.commit();
            conn.commit();

            long elapsed = System.currentTimeMillis() - begin;
            LOG.info( "OK [" + elapsed + " ms]." );
            return planId;
        } catch ( Exception e ) {
            throw new Exception( "Fehler beim Einfügen: " + e.getMessage(), e );
        } finally {
            closeQuietly( conn );
        }
    }

    public void insertInspirePlu( FeatureCollection featureCollection )
                            throws Exception {
        FeatureStoreTransaction transaction = null;
        try {
            LOG.info( "Insert INSPIRE PLU dataset" );
            FeatureStore inspirePluStore = lookupStore( INSPIREPLU_FS_ID );
            transaction = inspirePluStore.acquireTransaction();

            transaction.performInsert( featureCollection, IDGenMode.GENERATE_NEW );
            transaction.commit();
        } catch ( FeatureStoreException e ) {
            rollbackTransaction( transaction, e );
            throw new Exception( "Fehler beim Einfügen des INSPIRE PLU Datensatz: " + e.getMessage(), e );
        }
    }

    /**
     * @param oldXplan
     *            the {@link XPlan} describing the plan before update, never <code>null</code>
     * @param newXPlanMetadata
     *            of the {@link XPlan} with the updated values, never <code>null</code>
     * @param fc
     *            the edited feature collection, never <code>null</code>
     * @param synFc
     *            the edited feature collection with synthesized features, never <code>null</code>
     * @param planArtefact
     *            the edited xplan gml, never <code>null</code>
     * @param sortDate
     *            the date added to syn feature collection, may be <code>null</code>
     * @param removedRefs
     * @throws Exception
     */
    public void update( XPlan oldXplan, de.latlon.xplan.manager.web.shared.XPlanMetadata newXPlanMetadata,
                        XPlanFeatureCollection fc, FeatureCollection synFc, byte[] planArtefact,
                        XPlanToEdit xPlanToEdit, Date sortDate, List<File> uploadedArtefacts, Set<String> removedRefs )
                                        throws Exception {
        Connection conn = null;
        try {
            LOG.info( "Update XPlan" );

            conn = openConnection( ws, JDBC_POOL_ID );
            conn.setAutoCommit( false );

            updatePlanMetadata( conn, oldXplan, newXPlanMetadata, fc, synFc, sortDate );
            updatePlanArtefact( conn, oldXplan, planArtefact );
            updateArtefacts( conn, oldXplan, xPlanToEdit, uploadedArtefacts, removedRefs );
            updateXPlanAndXPlanSyn( conn, oldXplan, newXPlanMetadata, fc, synFc, xPlanToEdit, sortDate );

            long begin = System.currentTimeMillis();
            LOG.info( "- Persistierung..." );
            conn.commit();
            long elapsed = System.currentTimeMillis() - begin;
            LOG.info( "OK [" + elapsed + " ms]." );
        } catch ( Exception e ) {
            if ( conn != null )
                conn.rollback();
            throw new Exception( "Fehler beim Einfügen: " + e.getMessage(), e );
        } finally {
            closeQuietly( conn );
        }
    }

    /**
     * Deletes the specified plan from the database (and feature stores).
     * 
     * @param planId
     *            database id of the plan
     */
    public void deletePlan( String planId )
                    throws Exception {

        ensureWorkspaceInitialized();
        XPlanMetadata xPlanMetadata = selectXPlanMetadata( planId );
        deletePlan( xPlanMetadata, planId );
    }

    /**
     * Retrieve a list of all XPlans.
     * 
     * @return list of XPlans
     * @throws Exception
     */
    public List<XPlan> getXPlanList()
                    throws Exception {
        ensureWorkspaceInitialized();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try ( Connection mgrConn = openConnection( ws, JDBC_POOL_ID ) ) {
            stmt = mgrConn.prepareStatement( "SELECT id, import_date, xp_version, xp_type, name, "
                                             + "nummer, gkz, has_raster, release_date, ST_AsText(bbox), "
                                             + "ade, sonst_plan_art, planstatus, rechtsstand, district, "
                                             + "gueltigkeitBeginn, gueltigkeitEnde, inspirepublished FROM xplanmgr.plans" );
            rs = stmt.executeQuery();
            List<XPlan> xplanList = new ArrayList<>();
            while ( rs.next() ) {
                XPlan xPlan = retrieveXPlan( mgrConn, rs );
                xplanList.add( xPlan );
            }
            return xplanList;
        } catch ( Exception e ) {
            throw new Exception( "Interner-/Konfigurations-Fehler. Kann importierte Pläne nicht auflisten: "
                                 + e.getLocalizedMessage(),
                            e );
        } finally {
            closeQuietly( stmt, rs );
        }
    }

    /**
     * Retrieve a single {@link XPlan} by id.
     * 
     * @param planId
     *            id of a plan, must not be <code>null</code>
     * @return a single plan
     * @throws Exception
     */
    public XPlan getXPlanById( int planId )
                    throws Exception {
        ensureWorkspaceInitialized();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try ( Connection mgrConn = openConnection( ws, JDBC_POOL_ID ) ) {
            stmt = mgrConn.prepareStatement( "SELECT id, import_date, xp_version, xp_type, name, "
                                             + "nummer, gkz, has_raster, release_date, ST_AsText(bbox), "
                                             + "ade, sonst_plan_art, planstatus, rechtsstand, district, "
                                             + "gueltigkeitBeginn, gueltigkeitEnde, inspirepublished FROM xplanmgr.plans WHERE id =?" );
            stmt.setInt( 1, planId );
            rs = stmt.executeQuery();
            if ( rs.next() )
                return retrieveXPlan( mgrConn, rs );
        } catch ( Exception e ) {
            throw new Exception(
                            "Interner-/Konfigurations-Fehler. Kann Plan nicht auflisten: " + e.getLocalizedMessage(),
                            e );
        } finally {
            closeQuietly( stmt, rs );
        }
        return null;
    }

    /**
     * retrieves the id of the plan closest in future to the date passed
     * 
     * @param releaseDate
     *            minimal release date
     * @return id of plan with minimal release date
     * @throws SQLException
     */
    public String getPlanIdOfMoreRecentRasterPlan( Date releaseDate )
                    throws SQLException {
        String planId = null;

        Connection mgrConn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            mgrConn = openConnection( ws, JDBC_POOL_ID );
            stmt = mgrConn.prepareStatement( "SELECT id FROM xplanmgr.plans WHERE has_raster = true AND wmsSortDate=("
                                             + "SELECT min(wmsSortDate) FROM xplanmgr.plans "
                                             + "WHERE wmsSortDate IS NOT NULL AND wmsSortDate > ?)" );
            stmt.setDate( 1, new java.sql.Date( releaseDate.getTime() ) );
            rs = stmt.executeQuery();

            if ( rs.next() ) {
                planId = rs.getString( 1 );
            }
        } finally {
            closeQuietly( mgrConn, stmt, rs );
        }
        return planId;
    }

    /**
     * exports a plan
     * 
     * @param planId
     *            of plan to export
     * @return
     * @throws Exception
     */
    public XPlanArchiveContent retrieveAllXPlanArtefacts( String planId )
                    throws Exception {
        ensureWorkspaceInitialized();
        int id = getXPlanIdAsInt( planId );
        try {
            Connection conn = openConnection( ws, JDBC_POOL_ID );
            PreparedStatement stmt = conn.prepareStatement( "SELECT filename,data FROM xplanmgr.artefacts WHERE plan=? ORDER BY num" );
            stmt.setInt( 1, id );
            ResultSet rs = stmt.executeQuery();
            XPlanArtefactIterator artefacts = new DatabaseXPlanArtefactIterator( conn, stmt, rs );
            XPlanMetadata xPlanMetadata = selectXPlanMetadata( planId );
            FeatureCollection fc = restoreFeatureCollection( id, xPlanMetadata );
            return new XPlanArchiveContent( fc, artefacts, xPlanMetadata.version );
        } catch ( Exception e ) {
            LOG.error( "Plan could not be exported!", e );
            throw new XPlanExportException(
                            "Fehler beim Rekonstruieren der XPlan-Artefakte: " + e.getLocalizedMessage(), e );
        }
    }

    public FeatureCollection retrieveFeatureCollection( XPlan xPlanById )
                    throws Exception {
        ensureWorkspaceInitialized();
        int xPlanIdAsInt = getXPlanIdAsInt( xPlanById.getId() );
        XPlanMetadata xPlanMetadata = selectXPlanMetadata( xPlanIdAsInt );
        return restoreFeatureCollection( xPlanIdAsInt, xPlanMetadata );
    }

    /**
     * looks up feature store
     * 
     * @param version
     *            XPlan version
     * @param ade
     *            XPlan ADE extension, may be <code>null</code>
     * @param planStatus
     *            xplan status, if <code>null</code>, default store is chosen (non pre)
     * 
     * @return FeatureStore fitting to version and ade
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

    public InputStream retrieveXPlanArtefact( String planId )
                    throws Exception {
        Connection conn = null;
        try {
            conn = openConnection( ws, JDBC_POOL_ID );
            return retrieveXPlanArtefact( conn, getXPlanIdAsInt( planId ) );
        } finally {
            closeQuietly( conn );
        }
    }

    public InputStream retrieveXPlanArtefact( Connection conn, int planId )
                    throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement( "SELECT data FROM xplanmgr.artefacts WHERE plan=? and filename=?" );
            stmt.setInt( 1, planId );
            stmt.setString( 2, MAIN_FILE );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                return unzipArtefact( rs.getBinaryStream( 1 ) );
            }
        } catch ( Exception e ) {
            throw new Exception( "Fehler beim Rekonstruieren des XPlan-Artefakts '" + MAIN_FILE + "': "
                                 + e.getLocalizedMessage(),
                            e );
        } finally {
            closeQuietly( stmt, rs );
        }
        return null;
    }

    public List<String> reftrieveArtefactFileNames( String planId )
                    throws Exception {
        Connection conn = null;
        try {
            conn = openConnection( ws, JDBC_POOL_ID );
            return selectArtefactFileNames( conn, getXPlanIdAsInt( planId ) );
        } finally {
            closeQuietly( conn );
        }
    }

    /**
     * Retrieve internalId by the manager id from xplansyn schema.
     * 
     * @return the internal id of a plan (if available), <code>null</code> if an error occurred
     * @param planId
     *            the planId of the plan, never <code>null</code>
     * @param type
     *            the type of the plan, never <code>null</code>
     */
    public String retrieveInternalId( String planId, XPlanType type ) {
        ensureWorkspaceInitialized();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try (Connection mgrConn = openConnection( ws, JDBC_POOL_ID )) {
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append( "SELECT xplan_internalid FROM " );
            switch ( type ) {
            case BP_Plan:
                sqlBuilder.append( "xplansyn.xplan_bp_plan" );
                break;
            case FP_Plan:
                sqlBuilder.append( "xplansyn.xplan_fp_plan" );
                break;
            case LP_Plan:
                sqlBuilder.append( "xplansyn.xplan_lp_plan" );
                break;
            case RP_Plan:
                sqlBuilder.append( "xplansyn.xplan_rp_plan" );
                break;
            default:
                LOG.warn( "Unsupported xplan type " + type );
                return null;

            }
            sqlBuilder.append( " WHERE " );
            sqlBuilder.append( " xplan_mgr_planid = ?" );

            LOG.trace( "SQL Select to retrieve the internal id: " + sqlBuilder.toString() );

            stmt = mgrConn.prepareStatement( sqlBuilder.toString() );
            stmt.setInt( 1, getXPlanIdAsInt( planId ) );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                return rs.getString( 1 );
            }
        } catch ( Exception e ) {
            LOG.warn( "Die internalId des Plans mit der ID " + planId + " konnte nicht angefragt werden." );
        } finally {
            closeQuietly( stmt, rs );
        }
        return null;
    }

    /**
     * Updates the wmsSortDate column of all tables in the syn schema and in xplanmgr.plans table.
     * 
     * @param sortDate
     *            the new sort date, may be <code>null</code>
     * @param plan
     *            the plan to update, never <code>null</code>
     * @throws Exception
     */
    public void updateSortProperty( Date sortDate, XPlan plan )
                    throws Exception {
        Connection conn = null;
        try {
            conn = openConnection( ws, JDBC_POOL_ID );
            updateSortPropertyInSynSchema( sortDate, plan, conn );
            updateSortPropertyInMgrSchema( sortDate, plan, conn );
        } catch ( Exception e ) {
            conn.rollback();
        } finally {
            closeQuietly( conn );
        }
    }

    /**
     * @param planId
     *            of the plan to set the status
     * @throws SQLException
     *             if the sql could not be executed
     */
    public void setPlanWasInspirePublished( String planId )
                            throws SQLException {
        Connection conn = null;
        try {
            conn = openConnection( ws, JDBC_POOL_ID );
            updateInspirePublishedStatus( conn, planId, true );
        } finally {
            closeQuietly( conn );
        }
    }
    
    private void updateSortPropertyInSynSchema( Date sortDate, XPlan plan, Connection conn )
                    throws Exception {
        String selectSchemaAndColumnsToModify = "SELECT column_name, table_schema, table_name "
                                                + "FROM information_schema.columns "
                                                + "WHERE table_schema like 'xplansyn%' "
                                                + "AND table_name like 'xplan_%' "
                                                + "AND column_name = 'xplan_wmssortdate';";
        PreparedStatement stmt = conn.prepareStatement( selectSchemaAndColumnsToModify );
        ResultSet schemaAndTablesToModify = stmt.executeQuery();

        while ( schemaAndTablesToModify.next() ) {
            String schemaname = schemaAndTablesToModify.getString( "table_schema" );
            String tablename = schemaAndTablesToModify.getString( "table_name" );

            StringBuilder updateSql = new StringBuilder();
            updateSql.append( "UPDATE " ).append( schemaname ).append( '.' ).append( tablename );
            updateSql.append( " SET xplan_wmssortdate = ? " );
            updateSql.append( " WHERE xplan_mgr_planid = ?" );

            PreparedStatement updateStmt = conn.prepareStatement( updateSql.toString() );
            updateStmt.setDate( 1, convertToSqlDate( sortDate ) );
            updateStmt.setInt( 2, getXPlanIdAsInt( plan.getId() ) );
            LOG.trace( "SQL Update XPlan Syn sort date property: " + updateStmt );
            updateStmt.executeUpdate();
            closeQuietly( updateStmt );
        }
        closeQuietly( stmt );
    }

    private void updateSortPropertyInMgrSchema( Date sortDate, XPlan plan, Connection conn )
                    throws Exception {
        StringBuilder updateSql = new StringBuilder();
        updateSql.append( "UPDATE xplanmgr.plans" );
        updateSql.append( " SET wmssortdate = ? " );
        updateSql.append( " WHERE id = ?" );

        PreparedStatement updateStmt = null;
        try {
            updateStmt = conn.prepareStatement( updateSql.toString() );
            updateStmt.setDate( 1, convertToSqlDate( sortDate ) );
            updateStmt.setInt( 2, getXPlanIdAsInt( plan.getId() ) );
            LOG.trace( "SQL Update XPlan Manager sort date property: " + updateStmt );
            updateStmt.executeUpdate();
        } finally {
            closeQuietly( updateStmt );
        }
    }

    private void addAdditionalProperties( FeatureCollection synFc,
                                          de.latlon.xplan.manager.web.shared.XPlanMetadata xPlanMetadata,
                                          FeatureStore synFs, int planId, Date sortDate ) {
        AppSchema schema = synFs.getSchema();
        featureCollectionManipulator.addAdditionalPropertiesToFeatures( synFc, schema, planId, sortDate,
                                                                        xPlanMetadata );
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

    private InputStream unzipArtefact( InputStream zippedStream )
                    throws IOException, SQLException {
        try ( GZIPInputStream is = new GZIPInputStream( zippedStream );
              ByteArrayOutputStream bos = new ByteArrayOutputStream() ) {
            IOUtils.copy( is, bos );
            return new ByteArrayInputStream( bos.toByteArray() );
        }
    }

    private XPlan retrieveXPlan( Connection connection, ResultSet rs )
                    throws SQLException {
        int id = rs.getInt( 1 );
        Date importDate = rs.getTimestamp( 2 );
        String xpVersion = rs.getString( 3 );
        String xpType = rs.getString( 4 );
        String name = rs.getString( 5 );
        String number = rs.getString( 6 );
        String gkz = rs.getString( 7 );
        Boolean isRaster = rs.getBoolean( 8 );
        Date releaseDate = convertToDate( rs.getDate( 9 ) );
        XPlanEnvelope bbox = createBboxFromWkt( rs.getString( 10 ) );
        String ade = rs.getString( 11 );
        String sonstPlanArt = rs.getString( 12 );
        String planStatus = rs.getString( 13 );
        String rechtsstand = rs.getString( 14 );
        String district = rs.getString( 15 );
        Timestamp startDateTime = rs.getTimestamp( 16 );
        Timestamp endDateTime = rs.getTimestamp( 17 );
        Boolean isInspirePublished = rs.getBoolean( 18 );

        int numFeatures = retrieveNumberOfFeatures( connection, id );

        XPlan xPlan = new XPlan( ( name != null ? name : "-" ), ( new Integer( id ) ).toString(), xpType );
        xPlan.setVersion( xpVersion );
        xPlan.setNumber( number != null ? number : "-" );
        xPlan.setGkz( gkz );
        xPlan.setNumFeatures( numFeatures );
        xPlan.setRaster( isRaster );
        xPlan.setAde( ade );
        xPlan.setAdditionalType( sonstPlanArt );
        xPlan.setLegislationStatus( rechtsstand );
        xPlan.setReleaseDate( releaseDate );
        xPlan.setImportDate( importDate );
        xPlan.setBbox( bbox );
        xPlan.setXplanMetadata( createXPlanMetadata( planStatus, startDateTime, endDateTime ) );
        xPlan.setDistrict( categoryMapper.mapToCategory( district ) );
        xPlan.setInspirePublished( isInspirePublished );
        return xPlan;
    }

    private de.latlon.xplan.manager.web.shared.XPlanMetadata
                    createXPlanMetadata( String planStatus, Timestamp startDateTime, Timestamp endDateTime ) {
        PlanStatus planStatusAsEnum = null;
        if ( planStatus != null )
            planStatusAsEnum = PlanStatus.findByMessage( planStatus );
        return new de.latlon.xplan.manager.web.shared.XPlanMetadata( planStatusAsEnum, startDateTime, endDateTime );
    }

    private int getXPlanIdAsInt( String planId )
                    throws Exception {
        try {
            return Integer.parseInt( planId );
        } catch ( NumberFormatException e ) {
            throw new Exception( "Spezifizierter Wert '" + planId + "' ist keine gültige XPlan-Id (Ganzzahl).", e );
        }
    }

    private XPlanMetadata selectXPlanMetadata( String planId )
                    throws Exception {
        int id = getXPlanIdAsInt( planId );
        return selectXPlanMetadata( id );
    }

    private XPlanMetadata selectXPlanMetadata( int id )
                    throws Exception {
        XPlanVersion version = null;
        XPlanAde ade = null;
        PlanStatus planStatus = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try ( Connection mgrConn = openConnection( ws, JDBC_POOL_ID ) ) {
            stmt = mgrConn.prepareStatement( "SELECT xp_version, ade, planstatus FROM xplanmgr.plans WHERE id=?" );
            stmt.setInt( 1, id );
            rs = stmt.executeQuery();
            if ( !rs.next() ) {
                throw new Exception( "Kein XPlan mit Id " + id + " vorhanden." );
            }
            version = XPlanVersion.valueOf( rs.getString( 1 ) );
            ade = retrieveNsmAde( version, rs.getString( 2 ) );
            planStatus = retrievePlanStatus( rs.getString( 3 ) );
        } catch ( Exception e ) {
            throw new Exception( "Interner-/Konfigurations-Fehler. Kann XPlan-Informationen nicht aus DB lesen: "
                                 + e.getLocalizedMessage(),
                            e );
        } finally {
            closeQuietly( stmt, rs );
        }
        return new XPlanMetadata( version, ade, planStatus );
    }

    private List<String> selectArtefactFileNames( Connection conn, int id )
                    throws Exception {
        List<String> artefactFileNames = new ArrayList<String>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement( "SELECT filename FROM xplanmgr.artefacts WHERE plan=?" );
            stmt.setInt( 1, id );
            LOG.trace( "SQL Select artefacts filenames: {}", stmt );
            rs = stmt.executeQuery();
            while ( rs.next() ) {
                String fileName = rs.getString( 1 );
                if ( fileName != null && !"".equals( fileName ) )
                    artefactFileNames.add( fileName );
            }
        } catch ( Exception e ) {
            throw new Exception( "Interner-/Konfigurations-Fehler. Kann XPlan-Artefakte nicht aus DB lesen: "
                                 + e.getLocalizedMessage(),
                            e );
        } finally {
            closeQuietly( stmt, rs );
        }
        return artefactFileNames;
    }

    private int selectNextArtefactNumber( Connection conn, int id )
                    throws Exception {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement( "SELECT max(num) FROM xplanmgr.artefacts WHERE plan = ?" );
            stmt.setInt( 1, id );
            LOG.trace( "SQL Select artefacts max num value: {}", stmt );
            rs = stmt.executeQuery();
            if ( rs.next() ) {
                int maxNum = rs.getInt( 1 );
                return maxNum + 1;
            }
        } finally {
            closeQuietly( stmt, rs );
        }
        return 0;
    }

    private void deletePlan( XPlanMetadata xPlanMetadata, String planId )
                    throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            XPlanVersion version = xPlanMetadata.version;
            XPlanAde ade = xPlanMetadata.ade;
            PlanStatus planStatus = xPlanMetadata.planStatus;
            int id = getXPlanIdAsInt( planId );

            FeatureStore fs = lookupStore( version, ade, planStatus );
            FeatureStore fsSyn = lookupStore( XPLAN_SYN, null, planStatus );

            conn = openConnection( ws, JDBC_POOL_ID );
            conn.setAutoCommit( false );

            SQLFeatureStoreTransaction ta = (SQLFeatureStoreTransaction) fs.acquireTransaction();
            SQLFeatureStoreTransaction taSyn = (SQLFeatureStoreTransaction) fsSyn.acquireTransaction();

            stmt = conn.prepareStatement( "SELECT fid FROM xplanmgr.features WHERE plan=?" );
            stmt.setInt( 1, id );
            rs = stmt.executeQuery();
            Set<String> ids = new HashSet<>();
            while ( rs.next() ) {
                ids.add( rs.getString( 1 ) );
            }
            rs.close();
            stmt.close();

            IdFilter idFilter = new IdFilter( ids );
            LOG.info( "- Entferne XPlan " + planId + " aus dem FeatureStore (" + version + ( ade == null ? "" : ade )
                      + ")..." );
            ta.performDelete( idFilter, null );
            LOG.info( "OK" );

            LOG.info( "- Entferne XPlan " + planId + " aus dem FeatureStore (XPLAN_SYN)..." );
            taSyn.performDelete( idFilter, null );
            LOG.info( "OK" );

            LOG.info( "- Entferne XPlan " + planId + " aus der Manager-DB..." );
            stmt = conn.prepareStatement( "DELETE FROM xplanmgr.features WHERE plan=?" );
            stmt.setInt( 1, id );
            stmt.executeUpdate();

            stmt = conn.prepareStatement( "DELETE FROM xplanmgr.artefacts WHERE plan=?" );
            stmt.setInt( 1, id );
            stmt.executeUpdate();

            stmt = conn.prepareStatement( "DELETE FROM xplanmgr.plans WHERE id=?" );
            stmt.setInt( 1, id );
            stmt.executeUpdate();
            LOG.info( "OK" );

            LOG.info( "- Persistierung..." );
            ta.commit();
            taSyn.commit();
            conn.commit();
            LOG.info( "OK" );
        } catch ( Exception e ) {
            throw new Exception( "Fehler beim Löschen des Plans: " + e.getMessage() + ".", e );
        } finally {
            closeQuietly( conn, stmt, rs );
        }
    }

    private int retrieveNumberOfFeatures( Connection connection, int id )
                    throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.prepareStatement( "SELECT COUNT(*) FROM xplanmgr.features WHERE plan=?" );
            stmt.setInt( 1, id );
            rs = stmt.executeQuery();
            if ( rs.next() )
                return rs.getInt( 1 );
            return 0;
        } finally {
            closeQuietly( stmt, rs );
        }
    }

    private Set<String> determineFeatureIds( int planId )
                    throws SQLException {
        Set<String> ids = new LinkedHashSet<>();

        Connection mgrConn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            mgrConn = openConnection( ws, JDBC_POOL_ID );
            stmt = mgrConn.prepareStatement( "SELECT fid FROM xplanmgr.features WHERE plan=? ORDER BY num" );
            stmt.setInt( 1, planId );
            rs = stmt.executeQuery();

            while ( rs.next() ) {
                ids.add( rs.getString( 1 ) );
            }
        } finally {
            closeQuietly( mgrConn, stmt, rs );
        }
        return ids;
    }

    private FeatureCollection restoreFeatureCollection( int id, XPlanMetadata xPlanMetadata )
                    throws Exception {
        XPlanVersion version = xPlanMetadata.version;
        FeatureStore fs = lookupStore( version, xPlanMetadata.ade, xPlanMetadata.planStatus );
        Set<String> ids = determineFeatureIds( id );

        IdFilter filter = new IdFilter( ids );
        Query query = new Query( new TypeName[0], filter, null, null, null );
        return fs.query( query ).toCollection();
    }

    private Pair<List<String>, SQLFeatureStoreTransaction> insertXPlan( XPlanVersion version, FeatureStore fs,
                                                                        XPlanFeatureCollection fc )
                                                                                        throws FeatureStoreException {
        long begin = System.currentTimeMillis();
        LOG.info( "- Einfügen von " + fc.getFeatures().size() + " Feature(s) in den FeatureStore (" + version
                  + ")..." );
        SQLFeatureStoreTransaction ta = (SQLFeatureStoreTransaction) fs.acquireTransaction();
        List<String> fids = ta.performInsert( fc.getFeatures(), USE_EXISTING );
        long elapsed = System.currentTimeMillis() - begin;
        LOG.info( "OK [" + elapsed + " ms]." );
        return new Pair<List<String>, SQLFeatureStoreTransaction>( fids, ta );
    }

    private Pair<List<String>, SQLFeatureStoreTransaction> insertXPlanSyn( FeatureStore fs, FeatureCollection synFc )
                    throws FeatureStoreException {
        long begin = System.currentTimeMillis();
        LOG.info( "- Einfügen von " + synFc.size() + " Feature(s) in den FeatureStore (XPLAN_SYN)..." );
        SQLFeatureStoreTransaction ta = (SQLFeatureStoreTransaction) fs.acquireTransaction();
        List<String> fids = ta.performInsert( synFc, USE_EXISTING );
        long elapsed = System.currentTimeMillis() - begin;
        LOG.info( "OK [" + elapsed + " ms]." );
        return new Pair<>( fids, ta );
    }

    private void updateXPlanAndXPlanSyn( Connection conn, XPlan oldXPlan,
                                         de.latlon.xplan.manager.web.shared.XPlanMetadata newXPlanMetadata,
                                         XPlanFeatureCollection fc, FeatureCollection synFc, XPlanToEdit xPlanToEdit,
                                         Date sortDate )
                                                         throws Exception {
        PreparedStatement stmt = null;
        SQLFeatureStoreTransaction taSource = null;
        SQLFeatureStoreTransaction taSynSource = null;
        SQLFeatureStoreTransaction taTarget = null;
        SQLFeatureStoreTransaction taSynTarget = null;
        boolean sameSourceAndTarget = false;
        try {
            int planId = getXPlanIdAsInt( oldXPlan.getId() );
            XPlanVersion version = XPlanVersion.valueOf( oldXPlan.getVersion() );
            XPlanAde ade = retrieveNsmAde( version, oldXPlan.getAde() );
            PlanStatus oldPlanStatus = oldXPlan.getXplanMetadata().getPlanStatus();
            PlanStatus newPlanStatus = newXPlanMetadata.getPlanStatus();

            FeatureStore fsSource = lookupStore( version, ade, oldPlanStatus );
            FeatureStore synFsSource = lookupStore( XPLAN_SYN, null, oldPlanStatus );
            sameSourceAndTarget = oldPlanStatus == newPlanStatus || !managerConfiguration.isSeperatedDataManagementActived();
            FeatureStore fsTarget = sameSourceAndTarget ? fsSource : lookupStore( version, ade, newPlanStatus );
            FeatureStore synFsTarget = sameSourceAndTarget ? synFsSource
                                                           : lookupStore( XPLAN_SYN, null, newPlanStatus );

            taSource = (SQLFeatureStoreTransaction) fsSource.acquireTransaction();
            taSynSource = (SQLFeatureStoreTransaction) synFsSource.acquireTransaction();

            Set<String> ids = selectFids( conn, planId );

            IdFilter idFilter = new IdFilter( ids );
            LOG.info( "- Aktualisiere XPlan " + planId + " im FeatureStore (" + version + ( ade == null ? "" : ade )
                      + ")..." );
            taSource.performDelete( idFilter, null );
            Pair<List<String>, SQLFeatureStoreTransaction> fidsAndXPlanTa = insertXPlan( version, fsTarget, fc );
            taTarget = fidsAndXPlanTa.second;
            LOG.info( "OK" );

            addAdditionalProperties( synFc, newXPlanMetadata, synFsSource, planId, sortDate );
            LOG.info( "- Aktualisiere XPlan " + planId + " im FeatureStore (XPLAN_SYN)..." );
            taSynSource.performDelete( idFilter, null );
            Pair<List<String>, SQLFeatureStoreTransaction> fidsAndXPlanSynTa = insertXPlanSyn( synFsTarget, synFc );
            taSynTarget = fidsAndXPlanSynTa.second;
            LOG.info( "OK" );

            LOG.info( "- Aktualisiere Features von XPlan " + planId + " in der Manager-DB..." );
            String sql = "DELETE FROM xplanmgr.features WHERE plan=?";
            LOG.trace( "SQL Delete XPlanManager Features: " + sql );
            stmt = conn.prepareStatement( sql );
            stmt.setInt( 1, planId );
            stmt.executeUpdate();
            insertFeatureMetadata( conn, fidsAndXPlanTa.first, planId );
            LOG.info( "OK" );

            LOG.info( "- Persistierung..." );
            taSource.commit();
            taSynSource.commit();
            if ( !sameSourceAndTarget ) {
                taTarget.commit();
                taSynTarget.commit();
            }
            LOG.info( "OK" );
        } catch ( Exception e ) {
            LOG.error( "Fehler beim Aktualiseren der Features. Ein Rollback wird durchgeführt.", e );
            if ( taSource != null )
                taSource.rollback();
            if ( taSynSource != null )
                taSynSource.rollback();
            if ( !sameSourceAndTarget ) {
                if ( taTarget != null )
                    taTarget.rollback();
                if ( taSynTarget != null )
                    taSynTarget.rollback();
            }
            throw new Exception( "Fehler beim Aktualiseren des Plans: " + e.getMessage() + ".", e );
        } finally {
            closeQuietly( stmt );
        }

    }

    private int insertMetadata( Connection mgrConn, de.latlon.xplan.commons.archive.XPlanArchive archive,
                                XPlanFeatureCollection fc, FeatureCollection synFc, List<String> fids,
                                de.latlon.xplan.manager.web.shared.XPlanMetadata xPlanMetadata, Date sortDate )
                                                throws SQLException {

        long begin = System.currentTimeMillis();
        LOG.info( "- Einfügen der XPlan-Metadaten (XPLAN_MGR)..." );

        int planId = insertPlanMetadata( mgrConn, archive, fc, synFc, xPlanMetadata, sortDate );
        insertFeatureMetadata( mgrConn, fids, planId );

        long elapsed = System.currentTimeMillis() - begin;
        LOG.info( "OK [" + elapsed + " ms]." );
        return planId;
    }

    private void insertFeatureMetadata( Connection conn, List<String> fids, int planId )
                    throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement( "INSERT INTO xplanmgr.features (plan,fid,num) VALUES (?,?,?)" );
            stmt.setInt( 1, planId );
            for ( int i = 0; i < fids.size(); i++ ) {
                stmt.setString( 2, fids.get( i ) );
                stmt.setInt( 3, i );
                stmt.addBatch();
            }
            stmt.executeBatch();
        } finally {
            closeQuietly( stmt );
        }
    }

    private int insertPlanMetadata( Connection mgrConn, XPlanArchive archive, XPlanFeatureCollection fc,
                                    FeatureCollection synFc,
                                    de.latlon.xplan.manager.web.shared.XPlanMetadata xPlanMetadata, Date sortDate )
                                                    throws SQLException {
        String insertPlansSql = "INSERT INTO xplanmgr.plans "
                                + "(import_date, xp_version, xp_type, ade, name, nummer, gkz, has_raster, rechtsstand, "
                                + "release_date, sonst_plan_art, planstatus, district, "
                                + "wmsSortDate, gueltigkeitBeginn, gueltigkeitEnde, bbox)"
                                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,ST_GeometryFromText(?, 4326))";
        PreparedStatement stmt = null;
        int planId;
        try {
            stmt = mgrConn.prepareStatement( insertPlansSql, PreparedStatement.RETURN_GENERATED_KEYS );

            stmt.setTimestamp( 1, new Timestamp( System.currentTimeMillis() ) );
            stmt.setString( 2, archive.getVersion().name() );
            stmt.setString( 3, archive.getType().name() );
            XPlanAde ade = archive.getAde();
            if ( ade != null )
                stmt.setString( 4, ade.name() );
            else
                stmt.setString( 4, null );
            stmt.setString( 5, fc.getPlanName() );
            stmt.setString( 6, fc.getPlanNummer() );
            stmt.setString( 7, fc.getPlanGkz() );
            stmt.setBoolean( 8, fc.getHasRaster() );
            stmt.setString( 9, retrieveLegislationStatus( synFc, archive.getType() ) );
            stmt.setTimestamp( 10, convertToSqlTimestamp( fc.getPlanReleaseDate() ) );
            stmt.setString( 11, retrieveAdditionalType( synFc, archive.getType() ) );
            stmt.setString( 12, retrievePlanStatusMessage( xPlanMetadata ) );
            stmt.setString( 13, retrieveDistrict( fc.getFeatures(), archive.getType(), archive.getVersion() ) );
            stmt.setTimestamp( 14, convertToSqlTimestamp( sortDate ) );
            stmt.setTimestamp( 15, convertToSqlTimestamp( xPlanMetadata.getStartDateTime() ) );
            stmt.setTimestamp( 16, convertToSqlTimestamp( xPlanMetadata.getEndDateTime() ) );
            stmt.setString( 17, createWktFromBboxIn4326( fc ) );

            stmt.executeUpdate();
            planId = detectPlanId( stmt );
        } finally {
            closeQuietly( stmt );
        }
        return planId;
    }

    private void updatePlanMetadata( Connection conn, XPlan xplan,
                                     de.latlon.xplan.manager.web.shared.XPlanMetadata newXPlanMetadata,
                                     XPlanFeatureCollection fc, FeatureCollection synFc, Date sortDate )
                                                     throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append( "UPDATE xplanmgr.plans SET " );
        sql.append( "name = ?, " );
        sql.append( "rechtsstand = ?, " );
        sql.append( "sonst_plan_art = ?, " );
        sql.append( "wmsSortDate = ?, " );
        sql.append( "gueltigkeitbeginn = ?, " );
        sql.append( "gueltigkeitende = ?, " );
        sql.append( "planstatus = ? " );
        sql.append( "WHERE id = ? " );
        String updateSql = sql.toString();
        PreparedStatement stmt = null;
        try {
            XPlanType type = XPlanType.valueOf( xplan.getType() );
            stmt = conn.prepareStatement( updateSql );
            stmt.setString( 1, fc.getPlanName() );
            stmt.setString( 2, retrieveLegislationStatus( synFc, type ) );
            stmt.setString( 3, retrieveAdditionalType( synFc, type ) );
            stmt.setTimestamp( 4, convertToSqlTimestamp( sortDate ) );
            stmt.setTimestamp( 5, convertToSqlTimestamp( newXPlanMetadata.getStartDateTime() ) );
            stmt.setTimestamp( 6, convertToSqlTimestamp( newXPlanMetadata.getEndDateTime() ) );
            stmt.setString( 7, retrievePlanStatusMessage( newXPlanMetadata ) );
            stmt.setObject( 8, Integer.parseInt( xplan.getId() ) );
            LOG.trace( "SQL Update XPlanManager Metadata: {}", stmt );
            stmt.executeUpdate();
        } finally {
            closeQuietly( stmt );
        }
    }

    private void updateInspirePublishedStatus( Connection conn, String xplanId, boolean isPiublished )
                            throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append( "UPDATE xplanmgr.plans SET " );
        sql.append( "inspirepublished = ? " );
        sql.append( "WHERE id = ? " );
        String updateSql = sql.toString();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement( updateSql );
            stmt.setBoolean( 1, isPiublished );
            stmt.setObject( 2, Integer.parseInt( xplanId ) );
            LOG.trace( "SQL Update XPlanManager INSPIRE Published status: {}", stmt );
            stmt.executeUpdate();
        } finally {
            closeQuietly( stmt );
        }
    }

    private void insertArtefacts( XPlanArchive archive, Connection conn, int planId )
                    throws Exception {
        PreparedStatement stmt = null;
        List<? extends ArchiveEntry> entryEnum = archive.getZipFileEntries();
        int i = 0;
        for ( ArchiveEntry entry : entryEnum ) {
            long begin = System.currentTimeMillis();
            String name = entry.getName();
            LOG.info( String.format( "- Einfügen von XPlan-Artefakt '%s'...", name ) );
            try {
                InputStream is = archive.retrieveInputStreamFor( name );
                String mimetype = getArtefactMimeType( name );
                String insertStatement = "INSERT INTO xplanmgr.artefacts (plan,filename,data,num,mimetype)"
                                         + " VALUES (?,?,?,?,?)";
                stmt = conn.prepareStatement( insertStatement );
                stmt.setInt( 1, planId );
                stmt.setString( 2, name );
                stmt.setBytes( 3, createZipArtefact( is ) );
                stmt.setInt( 4, i++ );
                stmt.setString( 5, mimetype );
                stmt.executeUpdate();
                stmt.close();
                is.close();
                long elapsed = System.currentTimeMillis() - begin;
                LOG.info( "OK [" + elapsed + " ms]" );
            } catch ( IOException e ) {
                throw new Exception( "Fehler beim Lesen des Archiv-Eintrags: " + e.getLocalizedMessage(), e );
            } catch ( SQLException e ) {
                throw new Exception( "Fehler beim Einfügen in DB: " + e.getLocalizedMessage(), e );
            } finally {
                closeQuietly( stmt );
            }
        }
    }

    private void updatePlanArtefact( Connection conn, XPlan xplan, byte[] planArtefact )
                    throws Exception {
        PreparedStatement stmt = null;
        long begin = System.currentTimeMillis();
        LOG.info( String.format( "- Aktualisierung von XPlan-Artefakt 'xplan.gml'" ) );
        try {
            StringBuilder sql = new StringBuilder();
            sql.append( "UPDATE xplanmgr.artefacts SET " );
            sql.append( "data = ? " );
            sql.append( "WHERE plan = ? AND filename = ?" );
            String updateSql = sql.toString();
            stmt = conn.prepareStatement( updateSql );
            stmt.setBytes( 1, createZipArtefact( new ByteArrayInputStream( planArtefact ) ) );
            stmt.setInt( 2, Integer.parseInt( xplan.getId() ) );
            stmt.setString( 3, "xplan.gml" );
            LOG.trace( "SQL Update XPlanManager Artefacts: {}", stmt );
            stmt.executeUpdate();
            stmt.close();
            long elapsed = System.currentTimeMillis() - begin;
            LOG.info( "OK [" + elapsed + " ms]" );
        } catch ( SQLException e ) {
            throw new Exception( "Fehler beim Einfügen in DB: " + e.getLocalizedMessage(), e );
        } finally {
            closeQuietly( stmt );
        }
    }

    private void updateArtefacts( Connection conn, XPlan xPlan, XPlanToEdit xPlanToEdit, List<File> uploadedArtefacts,
                                  Set<String> removedRefs )
                                                  throws Exception {
        LOG.info( "- Aktualisierung der XPlan-Artefakte von Plan mit ID '{}'", xPlan.getId() );
        int id = getXPlanIdAsInt( xPlan.getId() );
        long begin = System.currentTimeMillis();
        try {
            List<String> referenceFileNames = retrieveReferenceFileNames( xPlanToEdit );
            List<String> artefactFileNames = selectArtefactFileNames( conn, id );
            Map<String, File> artefactsToUpdate = new HashMap<String, File>();
            Map<String, File> artefactsToInsert = new HashMap<String, File>();
            for ( String refFileName : referenceFileNames ) {
                LOG.debug( "Handle reference with name '{}'.", refFileName );
                File uploadedFile = retrieveUploadedArtefact( refFileName, uploadedArtefacts );
                boolean isStoredInArtefactsTable = artefactFileNames.contains( refFileName );
                if ( uploadedFile != null ) {
                    LOG.debug( "Reference was uploaded, update in DB required." );
                    if ( isStoredInArtefactsTable ) {
                        artefactsToUpdate.put( refFileName, uploadedFile );
                    } else {
                        artefactsToInsert.put( refFileName, uploadedFile );
                    }
                } else if ( isStoredInArtefactsTable ) {
                    LOG.debug( "Reference was not changed" );
                } else {
                    throw new Exception( "Could not find referenced artefact with name " + refFileName );
                }
            }
            executeUpdateArtefacts( conn, id, artefactsToUpdate );
            executeInsertArtefacts( conn, id, artefactsToInsert );
            executeDeleteArtefacts( conn, id, removedRefs );

            long elapsed = System.currentTimeMillis() - begin;
            LOG.info( "OK [" + elapsed + " ms]" );
        } catch ( SQLException e ) {
            throw new Exception( "Fehler beim Aktualiseren der XPlan-Artefakte in DB: " + e.getLocalizedMessage(), e );
        }
    }

    private void executeUpdateArtefacts( Connection conn, int id, Map<String, File> artefactsToUpdate )
                    throws SQLException, IOException, FileNotFoundException {
        StringBuilder sql = new StringBuilder();
        sql.append( "UPDATE xplanmgr.artefacts SET " );
        sql.append( "data = ? " );
        sql.append( "WHERE plan = ? AND filename = ?" );
        String updateSql = sql.toString();
        PreparedStatement stmt = null;
        for ( Entry<String, File> artefactToUpdate : artefactsToUpdate.entrySet() ) {
            FileInputStream fileInputStream = new FileInputStream( artefactToUpdate.getValue() );
            try {
                stmt = conn.prepareStatement( updateSql );
                stmt.setBytes( 1, createZipArtefact( fileInputStream ) );
                stmt.setInt( 2, id );
                stmt.setString( 3, artefactToUpdate.getKey() );
                LOG.trace( "SQL Update XPlanManager Artefacts: {}", stmt );
                stmt.executeUpdate();
            } finally {
                closeQuietly( stmt );
                closeQuietly( fileInputStream );
            }
        }
    }

    private void executeInsertArtefacts( Connection conn, int id, Map<String, File> artefactsToInsert )
                    throws Exception {
        StringBuilder sql = new StringBuilder();
        sql.append( "INSERT INTO xplanmgr.artefacts " );
        sql.append( "(plan,filename,data,num,mimetype) " );
        sql.append( "VALUES (?,?,?,?,?)" );
        String insertSql = sql.toString();
        int num = selectNextArtefactNumber( conn, id );
        PreparedStatement stmt = null;
        for ( Entry<String, File> artefactToInsert : artefactsToInsert.entrySet() ) {
            FileInputStream fileInputStream = new FileInputStream( artefactToInsert.getValue() );
            String fileName = artefactToInsert.getKey();
            try {
                stmt = conn.prepareStatement( insertSql );
                stmt.setInt( 1, id );
                stmt.setString( 2, fileName );
                stmt.setBytes( 3, createZipArtefact( fileInputStream ) );
                stmt.setInt( 4, num++ );
                stmt.setString( 5, getArtefactMimeType( fileName ) );
                LOG.trace( "SQL Insert XPlanManager Artefacts: {}", stmt );
                stmt.executeUpdate();
            } finally {
                closeQuietly( stmt );
                closeQuietly( fileInputStream );
            }
        }
    }

    private void executeDeleteArtefacts( Connection conn, int id, Set<String> artefactsToDelete )
                    throws Exception {
        LOG.debug( "Artefacts to delete: {}", artefactsToDelete );
        StringBuilder sql = new StringBuilder();
        sql.append( "DELETE FROM xplanmgr.artefacts " );
        sql.append( "WHERE plan = ? AND filename = ? " );
        String deleteSql = sql.toString();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement( deleteSql );
            stmt.setInt( 1, id );
            for ( String artefactToDelete : artefactsToDelete ) {
                stmt.setString( 2, artefactToDelete );
                LOG.trace( "SQL Delete XPlanManager Artefacts: {}", stmt );
                stmt.addBatch();
            }
            stmt.executeBatch();
        } finally {
            closeQuietly( stmt );
        }
    }

    private File retrieveUploadedArtefact( String refFileName, List<File> uploadedArtefacts ) {
        if ( uploadedArtefacts != null ) {
            for ( File uploadedArtefact : uploadedArtefacts ) {
                if ( refFileName.equals( uploadedArtefact.getName() ) )
                    return uploadedArtefact;
            }
        }
        return null;
    }

    private List<String> retrieveReferenceFileNames( XPlanToEdit xPlanToEdit ) {
        List<String> referenceFileNames = new ArrayList<String>();
        addReferences( referenceFileNames, xPlanToEdit.getTexts() );
        addReferences( referenceFileNames, xPlanToEdit.getReferences() );
        if ( xPlanToEdit.getRasterBasis() != null )
            addReferences( referenceFileNames, xPlanToEdit.getRasterBasis().getRasterReferences() );
        return referenceFileNames;
    }

    private void addReferences( List<String> referenceFileNames, List<? extends AbstractReference> references ) {
        for ( AbstractReference ref : references ) {
            String reference = ref.getReference();
            if ( reference != null && !"".equals( reference ) )
                referenceFileNames.add( reference );
            String georeference = ref.getGeoReference();
            if ( georeference != null && !"".equals( georeference ) )
                referenceFileNames.add( georeference );
        }
    }

    private Set<String> selectFids( Connection conn, int planId )
                    throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement( "SELECT fid FROM xplanmgr.features WHERE plan=?" );
            stmt.setInt( 1, planId );
            ResultSet rs = stmt.executeQuery();
            Set<String> ids = new HashSet<>();
            while ( rs.next() ) {
                ids.add( rs.getString( 1 ) );
            }
            return ids;
        } catch ( SQLException e ) {
            throw e;
        } finally {
            closeQuietly( stmt );
        }
    }

    private byte[] createZipArtefact( InputStream is )
                    throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gos = new GZIPOutputStream( bos );
        copyLarge( is, gos );
        gos.close();
        return bos.toByteArray();
    }

    private String getArtefactMimeType( String fileName ) {
        MimetypesFileTypeMap mimeMap = new MimetypesFileTypeMap();
        mimeMap.addMimeTypes( "text/xml gml xml" );
        mimeMap.addMimeTypes( "application/pdf pdf" );
        mimeMap.addMimeTypes( "application/zip zip" );
        mimeMap.addMimeTypes( "image/jpeg jpg jpeg" );
        mimeMap.addMimeTypes( "image/png png" );
        mimeMap.addMimeTypes( "image/tiff tif tiff" );
        mimeMap.addMimeTypes( "image/ecw ecw" );
        mimeMap.addMimeTypes( "text/html html" );
        mimeMap.addMimeTypes( "text/plain txt text" );
        return mimeMap.getContentType( fileName );
    }

    private FeatureStore lookupStore( String id ) {
        ensureWorkspaceInitialized();
        FeatureStore sfs = ws.getResource( FeatureStoreProvider.class, id );
        if ( sfs == null ) {
            LOG.debug( "Can't get Feature Store '" + id + "', available Feature Stores:" );
            throw new IllegalArgumentException( "Wrong FeatureStore Id " + id );
        }
        return sfs;
    }

    private void ensureWorkspaceInitialized() {
        try {
            ws.getResource( ConnectionProviderProvider.class, JDBC_POOL_ID );
        } catch ( Exception e ) {
            long begin = System.currentTimeMillis();
            LOG.info( "- Initialisiere Feature Stores..." );
            ws.initAll();
            long elapsed = System.currentTimeMillis() - begin;
            LOG.info( "OK [" + elapsed + " ms]" );
        }
    }

    private String createWktFromBboxIn4326( XPlanFeatureCollection fc ) {
        Envelope bboxIn4326 = fc.getBboxIn4326();
        if ( bboxIn4326 != null )
            return WKTWriter.write( bboxIn4326 );
        return null;
    }

    private XPlanEnvelope createBboxFromWkt( String bboxAsWkt ) {
        if ( bboxAsWkt != null && !bboxAsWkt.isEmpty() ) {
            try {
                String crs = "epsg:4326";
                WKTReader reader = new WKTReader( CRSManager.lookup( crs ) );
                Geometry geometry = reader.read( bboxAsWkt );
                Envelope envelope = geometry.getEnvelope();
                return new XPlanEnvelope( envelope.getMin().get0(), envelope.getMin().get1(), envelope.getMax().get0(),
                                envelope.getMax().get1(), crs );
            } catch ( UnknownCRSException | ParseException e ) {
                LOG.error( "Could not create envelope from " + bboxAsWkt, e );
            }
        }
        return null;
    }

    private int detectPlanId( PreparedStatement stmt )
                    throws SQLException {
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        try {
            if ( generatedKeys.next() ) {
                return generatedKeys.getInt( 1 );
            } else {
                LOG.error( "Detecting the generated planId failed!" );
                throw new SQLException( "Detecting planId failed, no generated key obtained." );
            }
        } finally {
            generatedKeys.close();
        }
    }

    private XPlanAde retrieveNsmAde( XPlanVersion version, String adeValue )
                    throws SQLException {
        if ( XPlanVersion.XPLAN_41.equals( version ) ) {
            if ( XPlanAde.NSM.name().equalsIgnoreCase( adeValue ) )
                return XPlanAde.NSM;
        }
        return null;
    }

    private PlanStatus retrievePlanStatus( String planStatusMessage )
                    throws SQLException {
        if ( planStatusMessage != null && planStatusMessage.length() > 0 )
            return PlanStatus.findByMessage( planStatusMessage );
        return FESTGESTELLT;
    }

    private String retrievePlanStatusMessage( de.latlon.xplan.manager.web.shared.XPlanMetadata xPlanMetadata ) {
        if ( xPlanMetadata.getPlanStatus() != null )
            return xPlanMetadata.getPlanStatus().getMessage();
        return FESTGESTELLT.getMessage();
    }

    private Date convertToDate( java.sql.Date dateToConvert )
                    throws SQLException {
        return dateToConvert != null ? new Date( dateToConvert.getTime() ) : null;
    }

    private Timestamp convertToSqlTimestamp( Date dateToConvert ) {
        if ( dateToConvert != null ) {
            return new Timestamp( dateToConvert.getTime() );
        }
        return null;
    }

    private java.sql.Date convertToSqlDate( Date dateToConvert ) {
        if ( dateToConvert != null ) {
            return new java.sql.Date( dateToConvert.getTime() );
        }
        return null;
    }

    private void rollbackTransaction( FeatureStoreTransaction transaction, FeatureStoreException e ) {
        if ( transaction != null )
            try {
                transaction.rollback();
            } catch ( FeatureStoreException fse ) {
                LOG.warn( "Rollback failed: " + e.getMessage() );
                LOG.trace( "Rollback failed.", e );

            }
    }

    private class XPlanMetadata {

        XPlanVersion version;

        XPlanAde ade;

        PlanStatus planStatus;

        /**
         * 
         * @param version
         *            may be <code>null</code>
         * @param ade
         *            may be <code>null</code>
         * @param planStatus
         *            may be <code>null</code>
         */
        XPlanMetadata( XPlanVersion version, XPlanAde ade, PlanStatus planStatus ) {
            this.version = version;
            this.ade = ade;
            this.planStatus = planStatus;
        }

    }

}
