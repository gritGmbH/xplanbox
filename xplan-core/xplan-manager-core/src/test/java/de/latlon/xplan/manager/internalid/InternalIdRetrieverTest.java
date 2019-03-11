package de.latlon.xplan.manager.internalid;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.h2.tools.SimpleResultSet;
import org.junit.Before;
import org.junit.Test;

import de.latlon.xplan.manager.configuration.InternalIdRetrieverConfiguration;

/**
 * Tests for {@link de.latlon.xplan.manager.internalid.InternalIdRetriever}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class InternalIdRetrieverTest {

    private InternalIdRetriever internalIdRetriever;

    @Before
    public void setup()
                    throws Exception {
        InternalIdRetrieverConfiguration configuration = new InternalIdRetrieverConfiguration();
        internalIdRetriever = spy( new InternalIdRetriever( configuration ) );
        Connection conn = mock( Connection.class );
        PreparedStatement ps = mock( PreparedStatement.class );
        ResultSet rs = createResultSet();
        doReturn( conn ).when( internalIdRetriever ).createConnectionFromWorkspace();
        doReturn( ps ).when( internalIdRetriever ).retrievePreparedStatement( anyString(), anyString(), eq( conn ) );
        doReturn( rs ).when( internalIdRetriever ).retrieveResultSet( eq( ps ) );
    }

    @Test
    public void testGetMatchingInternalIds()
                    throws Exception {
        Map<String, String> internalIds = internalIdRetriever.getMatchingInternalIds( "test" );

        assertThat( internalIds.size(), is( 3 ) );
        assertThat( internalIds.containsKey( "id1" ), is( true ) );
        assertThat( internalIds.containsKey( "id2" ), is( true ) );
        assertThat( internalIds.containsKey( "id3" ), is( true ) );
        assertThat( internalIds.containsValue( "name1" ), is( true ) );
        assertThat( internalIds.containsValue( "name2" ), is( true ) );
        assertThat( internalIds.containsValue( "name3" ), is( true ) );
    }

    private SimpleResultSet createResultSet() {
        SimpleResultSet rs = new SimpleResultSet();
        rs.addColumn( "verfahrensid", 0, 0, 0 );
        rs.addColumn( "verfahrensname", 0, 0, 0 );
        rs.addRow( "id1", "name1" );
        rs.addRow( "id2", "name2" );
        rs.addRow( "id3", "name3" );
        return rs;
    }

}