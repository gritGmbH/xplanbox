package de.latlon.xplanisk2.wms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger( PlanwerkFilter.class );

    private static final Map<String, String> PLANWERK_TO_WMS_MAPPING = new HashMap<>();

    static {
        PLANWERK_TO_WMS_MAPPING.put( "planwerkwms", "/wms" );
        PLANWERK_TO_WMS_MAPPING.put( "planwerkwmspre", "/wmspre" );
        PLANWERK_TO_WMS_MAPPING.put( "planwerkwmsarchive", "/wmsarchive" );
    }

    @Override
    public void init( FilterConfig filterConfig )
                    throws ServletException {
    }

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain )
                    throws IOException, ServletException {
        LOG.debug( "Retrieved planwerk request..." );
        String managerId = retrieveRequestedManagerId( servletRequest );
        RequestParameterWrapper wrappedRequest = new RequestParameterWrapper( (HttpServletRequest) servletRequest );

        wrappedRequest.setPathInfo( parsePathInfoWithoutRequestedPlanwerk( servletRequest ) );
        wrappedRequest.addParameter( "PLANWERK_MANAGERID", managerId );
        filterChain.doFilter( wrappedRequest, servletResponse );
    }

    @Override
    public void destroy() {

    }

    private String retrieveRequestedManagerId( ServletRequest servletRequest ) {
        // TODO
        if ( servletRequest instanceof HttpServletRequest ) {
            String pathInfo = ( (HttpServletRequest) servletRequest ).getPathInfo();
            if ( pathInfo.endsWith( "Billstedt28" ) )
                return "1";
            if ( pathInfo.endsWith( "Billstedt73" ) )
                return "2";
        }
        throw new IllegalArgumentException( "Plan with name XXX is not available" );
    }

    private String parsePathInfoWithoutRequestedPlanwerk( ServletRequest servletRequest ) {
        if ( servletRequest instanceof HttpServletRequest ) {
            String pathInfo = ( (HttpServletRequest) servletRequest ).getPathInfo();
            String[] paths = pathInfo.split( "/" );
            return PLANWERK_TO_WMS_MAPPING.get( paths[1] );
        }
        throw new IllegalArgumentException( "Plan with name XXX is not available" );
    }

}