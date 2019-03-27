package de.latlon.xplan.planwerkwms;

import org.deegree.commons.utils.JDBCUtils;
import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, List<Integer>> availablePlanwerke = retrieveAvailablePlanwerke( workspace );

        String id = location.getIdentifier().getId();
        List<ResourceMetadata<OWS>> availablePlanwerkResources = new ArrayList<>();
        for ( Map.Entry<String, List<Integer>> planwerk : availablePlanwerke.entrySet() ) {
            String planname = planwerk.getKey();
            List<Integer> managerIds = planwerk.getValue();
            String newId = id + "/planname/" + planname;
            ResourceLocation<OWS> resourceLocation = new PlanwerkResourceLocation( location, newId );
            PlanwerkMetadata planwerkMetadata = new PlanwerkMetadata( workspace, resourceLocation, provider,
                                                                      planwerkWmsMetadata, managerIds );
            availablePlanwerkResources.add( planwerkMetadata );
        }
        return availablePlanwerkResources;
    }

    private Map<String, List<Integer>> retrieveAvailablePlanwerke( Workspace workspace ) {
        Map<String, List<Integer>> availablePlanwerke = new HashMap<>();
        Connection conn = getConnection( workspace );
        if ( conn != null ) {
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String sql = "SELECT name, array_agg(id) from xplanmgr.plans GROUP BY name";
                ps = conn.prepareStatement( sql );
                rs = ps.executeQuery();
                while ( rs.next() ) {
                    String name = rs.getString( 1 );
                    Array managerIdArray = rs.getArray( 2 );
                    List<Integer> managerIds = Arrays.asList( (Integer[]) managerIdArray.getArray() );
                    availablePlanwerke.put( name, managerIds );
                }
            } catch ( SQLException e ) {
                throw new IllegalArgumentException( "Planwerke could not be requested", e );
            } finally {
                JDBCUtils.close( rs, ps, conn, LOG );
            }
        }
        return availablePlanwerke;
    }

    private Connection getConnection( Workspace workspace ) {
        ResourceMetadata<ConnectionProvider> connectionMetadata = workspace.getResourceMetadata(
                        ConnectionProviderProvider.class, "xplan" );
        ResourceBuilder<ConnectionProvider> builder = workspace.prepare( connectionMetadata.getIdentifier() );
        ConnectionProvider connectionProvider = builder.build();
        return connectionProvider.getConnection();
    }

}
