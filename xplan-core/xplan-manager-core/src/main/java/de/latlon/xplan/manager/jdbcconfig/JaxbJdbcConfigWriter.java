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
package de.latlon.xplan.manager.jdbcconfig;

import static java.lang.Boolean.TRUE;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.deegree.db.datasource.jaxb.DataSourceConnectionProvider;
import org.deegree.db.datasource.jaxb.DataSourceConnectionProvider.DataSource;
import org.deegree.db.datasource.jaxb.DataSourceConnectionProvider.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * TODO add class documentation here
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class JaxbJdbcConfigWriter implements JdbcConfigWriter {

    private final Logger LOG = LoggerFactory.getLogger( JaxbJdbcConfigWriter.class );

    @Override
    public void write( OutputStream outputStream, String jdbcConnection, String dbName, String user, String pw )
                            throws IOException {
        DataSourceConnectionProvider datasource = createNewDatasource( jdbcConnection, dbName, user, pw );
        writeToStream( outputStream, datasource );
    }

    private DataSourceConnectionProvider createNewDatasource( String jdbcConnection, String dbName, String user,
                                                              String pw ) {
        DataSourceConnectionProvider connectionProvider = new DataSourceConnectionProvider();
        connectionProvider.setConfigVersion( "3.4.0" );
        DataSource dataSource = new DataSource();
        dataSource.setJavaClass( "org.apache.commons.dbcp.BasicDataSource" );
        connectionProvider.setDataSource( dataSource );
        addProperty( connectionProvider, "driverClassName", "org.postgresql.Driver" );
        addProperty( connectionProvider, "url", jdbcConnection + "/" + dbName );
        addProperty( connectionProvider, "username", user );
        addProperty( connectionProvider, "password", pw );
        addProperty( connectionProvider, "maxActive", "10" );
        return connectionProvider;
    }

    private void writeToStream( OutputStream outputStream, DataSourceConnectionProvider datasource )
                            throws IOException {
        try {
            JAXBContext ctx = JAXBContext.newInstance( "org.deegree.db.datasource.jaxb" );
            Marshaller marshaller = ctx.createMarshaller();
            marshaller.setProperty( JAXB_FORMATTED_OUTPUT, TRUE );
            marshaller.marshal( datasource, outputStream );
        } catch ( JAXBException e ) {
            LOG.trace( "Marshalling datasource configuration failed!", e );
            LOG.trace( "Marshalling datasource configuration failed: " + e.getMessage() );
            throw new IOException( e );
        }
    }

    private void addProperty( DataSourceConnectionProvider connectionProvider, String name, String value ) {
        Property property = new Property();
        property.setName( name );
        property.setValue( value );
        connectionProvider.getProperty().add( property );
    }

}
