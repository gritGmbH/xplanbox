package de.latlon.xplanisk2.wms;

import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.GZIPInputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The <code>GetAttachmentHandler</code> class that implements <code>HttpServlet</code> retrieves the attached data
 * (xml, picture, pdf, etc.) of an XPlan.
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * 
 * @author last edited by: $Author: ionita $
 * 
 * @version $Revision: 883 $, $Date: 2010-02-01 15:55:53 +0100 (Mon, 01 Feb 2010) $
 * 
 */
public class GetAttachmentHandler extends HttpServlet {

    private static final long serialVersionUID = -3208934442687262173L;

    private static final Logger LOG = LoggerFactory.getLogger( GetAttachmentHandler.class );

    private Workspace workspace;

    private String jdbcConnectionId;

    @Override
    public void init()
                    throws ServletException {
        super.init();
        String workspaceName = getStringParamValue( "workspaceName", "xplan-wms-workspace" );
        jdbcConnectionId = getStringParamValue( "jdbcConnectionId", "xplan" );
        LOG.info( "Workspace: " + workspaceName );
        LOG.info( "Jdbc connection id: " + jdbcConnectionId );

        workspace = DeegreeWorkspace.getInstance( workspaceName ).getNewWorkspace();
        workspace.initAll();
    }

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) {
        String filename = request.getParameter( "filename" );
        String featureID = request.getParameter( "featureID" );

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            ConnectionProvider resource = workspace.getResource( ConnectionProviderProvider.class, jdbcConnectionId );
            conn = resource.getConnection();

            stmt = conn.prepareStatement( "SELECT X1.mimetype, X1.data FROM xplanmgr.artefacts X1, xplanmgr.features X2"
                                          + " WHERE X1.plan=X2.plan AND X2.fid=? AND X1.filename=?" );
            stmt.setString( 1, featureID );
            stmt.setString( 2, filename );
            rs = stmt.executeQuery();
            writeData( rs, response, filename, featureID );

        } catch ( Exception e ) {
            LOG.error( "Could not request attachement!", e );
        } finally {
            if ( rs != null ) {
                try {
                    rs.close();
                } catch ( SQLException e ) {
                    LOG.warn( "Could not close result stream: {}", e.getMessage() );
                }
            }
            if ( stmt != null ) {
                try {
                    stmt.close();
                } catch ( SQLException e ) {
                    LOG.warn( "Could not close statement: {}", e.getMessage() );
                }
            }
            if ( conn != null ) {
                try {
                    conn.close();
                } catch ( SQLException e ) {
                    LOG.warn( "Could not close connection: {}", e.getMessage() );
                }
            }
        }
    }

    private void writeData( ResultSet rs, HttpServletResponse response, String filename, String featureID )
                    throws SQLException, IOException {
        if ( rs.next() ) {
            ServletOutputStream out = null;
            GZIPInputStream inputstream = null;
            try {
                response.setContentType( rs.getString( 1 ) );
                response.setHeader( "Content-Disposition", "inline; filename=" + filename );
                out = response.getOutputStream();
                inputstream = new GZIPInputStream( rs.getBinaryStream( 2 ) );
                byte[] buffer = new byte[10240];
                int read = -1;
                while ( ( read = inputstream.read( buffer ) ) != -1 ) {
                    out.write( buffer, 0, read );
                }
            } finally {
                closeQuietly( inputstream );
                closeQuietly( out );
            }
        } else {
            response.sendError( 404, "No attachment was found for filename " + filename + " and feature id "
                                     + featureID );
        }
    }

    private String getStringParamValue( String key, String defaultValue ) {
        String initParameter = getInitParameter( key );
        if ( initParameter != null )
            return initParameter;
        return defaultValue;
    }

}