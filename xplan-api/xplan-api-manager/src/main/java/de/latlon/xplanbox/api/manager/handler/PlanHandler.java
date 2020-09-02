package de.latlon.xplanbox.api.manager.handler;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class PlanHandler {

    private static final Logger LOG = getLogger( PlanHandler.class );

    public void importPlan() {
        LOG.info( "Import Plan" );
    }

}