package de.latlon.xplan.manager.web.server.log;

import de.latlon.xplan.manager.log.SystemLog;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SystemLogListener implements ServletContextListener {
    @Override
    public void contextInitialized( ServletContextEvent servletContextEvent ) {
        SystemLog.log();
    }

    @Override
    public void contextDestroyed( ServletContextEvent servletContextEvent ) {
    }
}
