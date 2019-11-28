package de.latlon.xplan.validator.wms;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import static de.latlon.xplan.validator.wms.GmlDeleteJob.DEFAULT_DELETE_AFTER_IN_MINUTES;
import static de.latlon.xplan.validator.wms.GmlDeleteJob.DELETE_AFTER_KEY;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GmlImportServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger( GmlImportServlet.class );

    public static final String MEMORY_FEATURESTORE = "xplansyn";

    private static final String DELETE_AFTER_IN_MINUTES_INIT_PARAM = "DeleteAfterInMinutes";

    private static final int IMPORT_INTERVAL_IN_SECONDS = 1;

    private static final int DELETE_INTERVAL_IN_SECONDS = 30;

    @Override
    public void init( ServletConfig cfg ) {
        String key = "org.quartz.impl.StdSchedulerFactory.KEY";
        ServletContext servletContext = cfg.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) servletContext.getAttribute( key );
        try {
            int deleteAfterInMinutes = getDeleteAfterInMInutes( cfg );
            Scheduler quartzScheduler = factory.getScheduler( "GmlImportScheduler" );
            scheduleImportJob( quartzScheduler );
            scheduleDeleteJob( quartzScheduler, deleteAfterInMinutes );
            quartzScheduler.start();
        } catch ( SchedulerException e ) {
            LOG.error( "Scheduler could not be initialised", e );
        }
    }

    private void scheduleImportJob( Scheduler quartzScheduler )
                            throws SchedulerException {
        JobDetail importJob = JobBuilder.newJob( GmlImportJob.class ).withIdentity( "job1", "group1" ).build();
        Trigger importTrigger = TriggerBuilder.newTrigger().withIdentity( "trigger1",
                                                                          "group1" ).startNow().withSchedule(
                                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(
                                                        IMPORT_INTERVAL_IN_SECONDS ).repeatForever() ).build();
        quartzScheduler.scheduleJob( importJob, importTrigger );
    }

    private void scheduleDeleteJob( Scheduler quartzScheduler, Integer deleteAfterInMInutes )
                            throws SchedulerException {
        JobDetail deleteJob = JobBuilder.newJob( GmlDeleteJob.class ).withIdentity( "job2", "group1" ).usingJobData(
                                DELETE_AFTER_KEY, deleteAfterInMInutes ).build();
        Trigger deleteTrigger = TriggerBuilder.newTrigger().withIdentity( "trigger2",
                                                                          "group1" ).startNow().withSchedule(
                                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(
                                                        DELETE_INTERVAL_IN_SECONDS ).repeatForever() ).build();
        quartzScheduler.scheduleJob( deleteJob, deleteTrigger );
    }

    private Integer getDeleteAfterInMInutes( ServletConfig cfg ) {
        String deleteAfterInMinutes = cfg.getInitParameter( DELETE_AFTER_IN_MINUTES_INIT_PARAM );
        if ( deleteAfterInMinutes != null )
            try {
                return Integer.parseInt( deleteAfterInMinutes );
            } catch ( NumberFormatException e ) {
                LOG.warn( "Init Parameter DeleteAfterInMinutes is not a valid integer, was: {}", deleteAfterInMinutes );
            }
        return DEFAULT_DELETE_AFTER_IN_MINUTES;
    }

}
