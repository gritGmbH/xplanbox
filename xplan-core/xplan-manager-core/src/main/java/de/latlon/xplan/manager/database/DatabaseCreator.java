/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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
            FileReader xplan3 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan3/create.sql" );
            FileReader xplan40 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan40/create.sql" );
            FileReader xplan41 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan41/create.sql" );
            FileReader xplan41nsm = new FileReader( wsDirectory.toString() + "/sql/fix/xplan41nsm/create.sql" );
            FileReader xplan50 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan50/create.sql" );
            FileReader xplan51 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan51/create.sql" );
            FileReader xplan52 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan52/create.sql" );
            FileReader xplan53 = new FileReader( wsDirectory.toString() + "/sql/fix/xplan53/create.sql" );
            FileReader xplanSyn = new FileReader( wsDirectory.toString() + "/sql/fix/xplansyn/create.sql" );

            FileReader xplan3pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan3/create.sql" );
            FileReader xplan40pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan40/create.sql" );
            FileReader xplan41pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan41/create.sql" );
            FileReader xplan50pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan50/create.sql" );
            FileReader xplan51pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan51/create.sql" );
            FileReader xplan52pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan52/create.sql" );
            FileReader xplan53pre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan53/create.sql" );
            FileReader xplan41nsmpre = new FileReader( wsDirectory.toString() + "/sql/pre/xplan41nsm/create.sql" );
            FileReader xplanSynpre = new FileReader( wsDirectory.toString() + "/sql/pre/xplansyn/create.sql" );

            FileReader xplan3archive = new FileReader( wsDirectory.toString() + "/sql/archive/xplan3/create.sql" );
            FileReader xplan40archive   = new FileReader( wsDirectory.toString() + "/sql/archive/xplan40/create.sql" );
            FileReader xplan41archive = new FileReader( wsDirectory.toString() + "/sql/archive/xplan41/create.sql" );
            FileReader xplan50archive = new FileReader( wsDirectory.toString() + "/sql/archive/xplan50/create.sql" );
            FileReader xplan51archive = new FileReader( wsDirectory.toString() + "/sql/archive/xplan51/create.sql" );
            FileReader xplan52archive = new FileReader( wsDirectory.toString() + "/sql/archive/xplan52/create.sql" );
            FileReader xplan53archive = new FileReader( wsDirectory.toString() + "/sql/archive/xplan53/create.sql" );
            FileReader xplan41nsmarchive  = new FileReader( wsDirectory.toString() + "/sql/archive/xplan41nsm/create.sql" );
            FileReader xplanSynarchive  = new FileReader( wsDirectory.toString() + "/sql/archive/xplansyn/create.sql" );

            ScriptRunner runner = new ScriptRunner( connection );
            runner.setStopOnError( true );
            runner.setSendFullScript( true );
            runner.runScript( xplan3 );
            runner.runScript( xplan40 );
            runner.runScript( xplan41 );
            runner.runScript( xplan41nsm );
            runner.runScript( xplan50 );
            runner.runScript( xplan51 );
            runner.runScript( xplan52 );
            runner.runScript( xplan53 );
            runner.runScript( xplanMgr );
            runner.runScript( xplanSyn );

            runner.runScript( xplan3pre );
            runner.runScript( xplan40pre );
            runner.runScript( xplan41pre );
            runner.runScript( xplan50pre );
            runner.runScript( xplan51pre );
            runner.runScript( xplan52pre );
            runner.runScript( xplan53pre );
            runner.runScript( xplan41nsmpre );
            runner.runScript( xplanSynpre );
            
            runner.runScript( xplan3archive );
            runner.runScript( xplan40archive  );
            runner.runScript( xplan41archive );
            runner.runScript( xplan50archive );
            runner.runScript( xplan51archive );
            runner.runScript( xplan52archive );
            runner.runScript( xplan53archive );
            runner.runScript( xplan41nsmarchive );
            runner.runScript( xplanSynarchive );
        }
    }

}
