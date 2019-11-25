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

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GmlImportServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger( GmlImportServlet.class );

    private static final int INTERVAL_IN_SECONDS = 1;

    @Override
    public void init( ServletConfig cfg ) {
        String key = "org.quartz.impl.StdSchedulerFactory.KEY";
        ServletContext servletContext = cfg.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) servletContext.getAttribute( key );
        try {
            Scheduler quartzScheduler = factory.getScheduler( "GmlImportScheduler" );
            JobDetail job = JobBuilder.newJob( GmlImportJob.class)
                                    .withIdentity("job1", "group1")
                                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                                    .withIdentity("trigger1", "group1")
                                    .startNow()
                                    .withSchedule( SimpleScheduleBuilder.simpleSchedule()
                                                                        .withIntervalInSeconds( INTERVAL_IN_SECONDS )
                                                                        .repeatForever())
                                    .build();
            quartzScheduler.scheduleJob(job, trigger);
            quartzScheduler.start();
        } catch ( SchedulerException e ) {
            LOG.error( "Scheduler could not be initialised", e );
        }
    }

}
