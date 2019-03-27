package de.latlon.xplan.planwerkwms;

import com.vividsolutions.jts.io.ParseException;
import org.deegree.commons.utils.JDBCUtils;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.io.WKTReader;
import org.deegree.services.OWS;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.ResourceMetadata;
import org.deegree.workspace.Workspace;
import org.deegree.workspace.standard.AbstractResourceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkReader {

    private static final Logger LOG = LoggerFactory.getLogger( PlanwerkReader.class );

    private final ResourceLocation<OWS> location;

    private final AbstractResourceProvider<OWS> provider;

    private final PlanwerkWmsMetadata planwerkWmsMetadata;

    public PlanwerkReader( ResourceLocation<OWS> location, AbstractResourceProvider<OWS> provider,
                           PlanwerkWmsMetadata planwerkWmsMetadata ) {
        this.location = location;
        this.provider = provider;
        this.planwerkWmsMetadata = planwerkWmsMetadata;
    }

    public List<ResourceMetadata<OWS>> readAvailablePlanwerke( Workspace workspace ) {
        List<Planwerk> availablePlanwerke = retrieveAvailablePlanwerke( workspace );

        String id = location.getIdentifier().getId();
        List<ResourceMetadata<OWS>> availablePlanwerkResources = new ArrayList<>();
        for ( Planwerk planwerk : availablePlanwerke ) {
            String newId = id + "/planname/" + planwerk.getName();
            ResourceLocation<OWS> resourceLocation = new PlanwerkResourceLocation( location, newId );
            PlanwerkMetadata planwerkMetadata = new PlanwerkMetadata( workspace, resourceLocation, provider,
                                                                      planwerkWmsMetadata, planwerk );
            availablePlanwerkResources.add( planwerkMetadata );
        }
        return availablePlanwerkResources;
    }

    private List<Planwerk> retrieveAvailablePlanwerke( Workspace workspace ) {
        List<Planwerk> availablePlanwerke = new ArrayList<>();
        Connection conn = getConnection( workspace );
        if ( conn != null ) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String sql = "SELECT name, array_agg(id), ST_AsText(ST_Envelope(ST_UNION(bbox))) from xplanmgr.plans GROUP BY name";
                ps = conn.prepareStatement( sql );
                rs = ps.executeQuery();
                while ( rs.next() ) {
                    Planwerk planwerk = createPlanwerk( rs );
                    availablePlanwerke.add( planwerk );
                }
            } catch ( SQLException e ) {
                throw new IllegalArgumentException( "Planwerke could not be requested", e );
            } finally {
                JDBCUtils.close( rs, ps, conn, LOG );
            }
        }
        return availablePlanwerke;
    }

    private Planwerk createPlanwerk( ResultSet rs )
                    throws SQLException {
        String name = rs.getString( 1 );
        List<Integer> managerIds = parseManagerIds( rs.getArray( 2 ) );
        Envelope bbox = parseBboxFromWkt( rs.getString( 3 ) );
        return new Planwerk( name, managerIds, bbox );
    }

    private List<Integer> parseManagerIds( Array managerIdArray )
                    throws SQLException {
        Object ids = managerIdArray.getArray();
        return Arrays.asList( (Integer[]) ids );
    }

    private Envelope parseBboxFromWkt( String bboxAsWkt ) {
        if ( bboxAsWkt != null && !bboxAsWkt.isEmpty() ) {
            try {
                String crs = "epsg:4326";
                WKTReader reader = new WKTReader( CRSManager.lookup( crs ) );
                Geometry geometry = reader.read( bboxAsWkt );
                return geometry.getEnvelope();
            } catch ( UnknownCRSException | ParseException e ) {
                LOG.error( "Could not create envelope from " + bboxAsWkt, e );
            }
        }
        return null;
    }

    private Connection getConnection( Workspace workspace ) {
        ResourceMetadata<ConnectionProvider> connectionMetadata = workspace.getResourceMetadata(
                        ConnectionProviderProvider.class, "xplan" );
        ResourceBuilder<ConnectionProvider> builder = workspace.prepare( connectionMetadata.getIdentifier() );
        ConnectionProvider connectionProvider = builder.build();
        return connectionProvider.getConnection();
    }

}
