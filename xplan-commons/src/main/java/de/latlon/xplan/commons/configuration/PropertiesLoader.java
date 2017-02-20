package de.latlon.xplan.commons.configuration;

import java.util.Properties;

import de.latlon.xplan.manager.web.shared.ConfigurationException;

/**
 * Loads properties file.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public interface PropertiesLoader {

    /**
     * Loads a properties file with the specified name.
     * 
     * @param propertiesFileName
     *            the name of the properties file, never <code>null</code>
     * @return the properties loaded from specified file, <code>null</code> if the file was not found
     * @throws ConfigurationException
     *             - properties file could not be load
     */
    Properties loadProperties( String propertiesFileName )
                            throws ConfigurationException;
}
