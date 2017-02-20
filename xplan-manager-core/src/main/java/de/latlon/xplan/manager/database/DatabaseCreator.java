package de.latlon.xplan.manager.database;

import static de.latlon.xplan.manager.database.DatabaseUtils.closeQuietly;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * creates and sets up a xplan database
 * 
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 */
public class DatabaseCreator {

    /**
     * creates a XPlan database with schemas
     * 
     * @param jdbcConnection
     *            connection string
     * @param dbName
     *            database name
     * @param template
     *            database template
     * @param user
     *            database user
     * @param pw
     *            user's password
     * @param wsDirectory
     *            workspace directory
     * @param writer
     *            for log messages
     * @throws SQLException
     *             schema creation fails
     * @throws java.io.FileNotFoundException
     *             if at least one database script is missing
     * @throws java.sql.SQLException
     *             if database creation fails
     */
    public void createInitialDB( String jdbcConnection, String dbName, String template, String user, String pw,
                                 File wsDirectory )
                    throws SQLException, IOException {
        setupDatabase( jdbcConnection, dbName, template, user, pw );
        ensurePostgisIsInstalled( jdbcConnection, dbName, user, pw );
        createInitialSchemas( jdbcConnection, dbName, wsDirectory, user, pw );
    }

    private void setupDatabase( String jdbcConnection, String dbName, String template, String user, String pw )
                    throws SQLException {
        try ( Connection connection = DriverManager.getConnection( jdbcConnection + "/postgres", user, pw ) ) {
            connection.setAutoCommit( true );
            createDatabase( dbName, template, connection );
        }
    }

    private void createDatabase( String dbName, String template, Connection connection )
                    throws SQLException {
        try ( Statement stmt = connection.createStatement() ) {
            if ( template != null && !template.isEmpty() ) {
                stmt.execute( "CREATE DATABASE " + dbName + " TEMPLATE=" + template );
            } else {
                stmt.execute( "CREATE DATABASE " + dbName );
            }
        }
    }

    private void ensurePostgisIsInstalled( String jdbcConnection, String dbName, String user, String pw )
                    throws SQLException {
        try ( Connection connection = DriverManager.getConnection( jdbcConnection + "/" + dbName, user, pw ) ) {
            connection.setAutoCommit( true );
            Statement postgisStmt = connection.createStatement();
            try {
                postgisStmt.execute( "SELECT PostGIS_Lib_Version()" );
            } catch ( SQLException noPostGisInstalled ) {
                Statement extensionStmt = connection.createStatement();
                try {
                    extensionStmt.execute( "CREATE EXTENSION postgis" );
                } catch ( SQLException noExtensionInstallable ) {
                    // can't create extension for postgis versions lower than 2.
                } finally {
                    closeQuietly( extensionStmt );
                }
            } finally {
                closeQuietly( postgisStmt );
                closeQuietly( connection );
            }
        }
    }

    private void createInitialSchemas( String jdbcConnection, String dbName, File wsDirectory, String user, String pw )
                    throws SQLException, IOException {
        try ( Connection connection = DriverManager.getConnection( jdbcConnection + "/" + dbName, user, pw ) ) {
            connection.setAutoCommit( true );
            FileReader xplanMgr = new FileReader( wsDirectory.toString() + "/sql/xplanmgr/create.sql" );
            FileReader xplan2 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan2/create.sql" );
            FileReader xplan3 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan3/create.sql" );
            FileReader xplan40 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan40/create.sql" );
            FileReader xplan41 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan41/create.sql" );
            FileReader xplan41nsm = new FileReader( wsDirectory.toString() + "/sql/fix/xplan41nsm/create.sql" );
            FileReader xplanSyn = new FileReader( wsDirectory.toString() + "/sql/fix/xplansyn/create.sql" );

            FileReader xplan2pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan2/create.sql" );
            FileReader xplan3pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan3/create.sql" );
            FileReader xplan40pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan40/create.sql" );
            FileReader xplan41pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan41/create.sql" );
            FileReader xplan41nsmpre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan41nsm/create.sql" );
            FileReader xplanSynpre = new FileReader( wsDirectory.toString() + "/sql/pre/xplansyn/create.sql" );

            FileReader xplan2archive = new FileReader( wsDirectory.toString() + "/sql/archive/xplan2/create.sql" );
            FileReader xplan3archive = new FileReader( wsDirectory.toString() + "/sql/archive/xplan3/create.sql" );
            FileReader xplan40archive   = new FileReader( wsDirectory.toString() + "/sql/archive/xplan40/create.sql" );
            FileReader xplan41archive = new FileReader( wsDirectory.toString() + "/sql/archive/xplan41/create.sql" );
            FileReader xplan41nsmarchive  = new FileReader( wsDirectory.toString() + "/sql/archive/xplan41nsm/create.sql" );
            FileReader xplanSynarchive  = new FileReader( wsDirectory.toString() + "/sql/archive/xplansyn/create.sql" );

            ScriptRunner runner = new ScriptRunner( connection );
            runner.setStopOnError( true );
            runner.setSendFullScript( true );
            runner.runScript( xplan2 );
            runner.runScript( xplan3 );
            runner.runScript( xplan40 );
            runner.runScript( xplan41 );
            runner.runScript( xplan41nsm );
            runner.runScript( xplanMgr );
            runner.runScript( xplanSyn );

            runner.runScript( xplan2pre );
            runner.runScript( xplan3pre );
            runner.runScript( xplan40pre );
            runner.runScript( xplan41pre );
            runner.runScript( xplan41nsmpre );
            runner.runScript( xplanSynpre );
            
            runner.runScript( xplan2archive );
            runner.runScript( xplan3archive );
            runner.runScript( xplan40archive  );
            runner.runScript( xplan41archive );
            runner.runScript( xplan41nsmarchive );
            runner.runScript( xplanSynarchive );
        }
    }

}
