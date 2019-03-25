package de.latlon.xplanisk2.wms;

import org.deegree.commons.utils.JDBCUtils;
import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.services.controller.OGCFrontController;
import org.deegree.workspace.Workspace;
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
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * Implements XPlanwerkWMS: Converts the incoming request to a WMS request with vendor specific parameter.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger( PlanwerkFilter.class );

    private static final Map<String, String> PLANWERK_TO_WMS_MAPPING = new HashMap<>();

    private static final String JDBC_RESOURCE_ID = "xplan";

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
        if ( servletRequest instanceof HttpServletRequest ) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String managerId = retrieveRequestedManagerId( httpServletRequest );
            RequestParameterWrapper wrappedRequest = new RequestParameterWrapper( httpServletRequest );

            wrappedRequest.setPathInfo( parsePathInfoWithoutRequestedPlanwerk( httpServletRequest ) );
            wrappedRequest.addParameter( "PLANWERK_MANAGERID", managerId );
            filterChain.doFilter( wrappedRequest, servletResponse );
        }
        throw new IllegalArgumentException( "Request is not supported" );
    }

    @Override
    public void destroy() {

    }

    private String retrieveRequestedManagerId( HttpServletRequest servletRequest ) {
        String requestedPlanName = parseRequestedPlanName( servletRequest );
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT ARRAY( SELECT id from xplanmgr.plans WHERE name = ?)";
            ps = conn.prepareStatement( sql );
            ps.setString( 1, requestedPlanName );
            rs = ps.executeQuery();
            if ( rs.next() ) {
                Array managerIdArray = rs.getArray( 1 );
                List<Integer> managerIds = Arrays.asList( (Integer[]) managerIdArray.getArray() );
                if ( !managerIds.isEmpty() ) {
                    return managerIds.stream().map( managerId -> managerId.toString() ).collect( joining( "," ) );
                }
            }
        } catch ( SQLException e ) {
            throw new IllegalArgumentException( "Planwerk with name " + requestedPlanName + " could not be requested",
                                                e );
        } finally {
            JDBCUtils.close( rs, ps, conn, LOG );
        }
        throw new IllegalArgumentException( "Planwerk with name " + requestedPlanName + " is not available" );
    }

    private String parseRequestedPlanName( HttpServletRequest servletRequest ) {
        String pathInfo = servletRequest.getPathInfo();
        return pathInfo.substring( pathInfo.lastIndexOf( "/" ) + 1, pathInfo.length() );
    }

    private Connection getConnection() {
        Workspace workspace = OGCFrontController.getServiceWorkspace().getNewWorkspace();
        ConnectionProvider connectionProvider = workspace.getResource( ConnectionProviderProvider.class,
                                                                       JDBC_RESOURCE_ID );
        return connectionProvider.getConnection();
    }

    private String parsePathInfoWithoutRequestedPlanwerk( HttpServletRequest servletRequest ) {
        String pathInfo = servletRequest.getPathInfo();
        String[] paths = pathInfo.split( "/" );
        return PLANWERK_TO_WMS_MAPPING.get( paths[1] );
    }

}