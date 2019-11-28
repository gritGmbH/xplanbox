package de.latlon.xplan.validator.wms;

import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreException;
import org.deegree.feature.persistence.FeatureStoreProvider;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.filter.IdFilter;
import org.deegree.services.controller.OGCFrontController;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.validator.wms.GmlImportServlet.MEMORY_FEATURESTORE;
import static de.latlon.xplan.validator.wms.InsertedFids.INSERTED_FIDS_KEY;
import static java.util.Calendar.MINUTE;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GmlDeleteJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger( GmlDeleteJob.class );

    public static final String DELETE_AFTER_KEY = "DELETEAFTERINMINUTES";

    public static final int DEFAULT_DELETE_AFTER_IN_MINUTES = 5;

    @Override
    public void execute( JobExecutionContext jobExecutionContext )
                            throws JobExecutionException {
        int deleteAfter = getDeleteAfter( jobExecutionContext );
        DeegreeWorkspace workspace = OGCFrontController.getServiceWorkspace();
        if ( workspace == null ) {
            return;
        }
        try {
            SchedulerContext context = jobExecutionContext.getScheduler().getContext();
            List<InsertedFids> insertedFids = (List<InsertedFids>) context.get( INSERTED_FIDS_KEY );
            Calendar deleteBeforeThis = Calendar.getInstance();
            deleteBeforeThis.add( MINUTE, -deleteAfter );
            if ( insertedFids != null ) {
                List<InsertedFids> deleteCandidates = insertedFids.stream().filter(
                                        f -> f.getInsertTime().before( deleteBeforeThis ) ).collect(
                                        Collectors.toList() );

                deleteCandidates.forEach( f -> deleteGml( f.getFids(), workspace ) );
                insertedFids.removeAll( deleteCandidates );
                context.put( INSERTED_FIDS_KEY, insertedFids );
            }
        } catch ( SchedulerException e ) {
            LOG.warn( "Could not retrieve scheduler context", e );
        }

    }

    private void deleteGml( List<String> fidsToDelete, DeegreeWorkspace workspace ) {
        LOG.info( "Delete {}", fidsToDelete );
        FeatureStore fs = workspace.getNewWorkspace().getResource( FeatureStoreProvider.class, MEMORY_FEATURESTORE );
        FeatureStoreTransaction ta = null;
        try {
            ta = fs.acquireTransaction();
            IdFilter idFilter = new IdFilter( fidsToDelete );
            ta.performDelete( idFilter, null );
            LOG.info( "Deleted " + fidsToDelete.size() + " features in memory." );
            ta.commit();
        } catch ( Exception e ) {
            LOG.warn( "Could not delete featureCollection", e );
            rollbackQuietly( ta );
        }
    }

    private int getDeleteAfter( JobExecutionContext jobExecutionContext ) {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        if ( jobDataMap.get( DELETE_AFTER_KEY ) != null )
            return jobDataMap.getInt( DELETE_AFTER_KEY );
        return DEFAULT_DELETE_AFTER_IN_MINUTES;
    }

    private void rollbackQuietly( FeatureStoreTransaction ta ) {
        if ( ta != null ) {
            try {
                ta.rollback();
            } catch ( FeatureStoreException ex ) {
            }
        }
    }

}