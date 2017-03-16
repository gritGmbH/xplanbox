package de.latlon.xplan.commons.configuration;

import java.io.File;
import java.io.InputStream;

/**
 * Loads properties from base class.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class DefaultPropertiesLoader extends AbstractPropertiesLoader {

    private Class<?> baseClass;

    /**
     * @param baseClass
     *            to load resources from
     */
    public DefaultPropertiesLoader( Class<?> baseClass ) {
        this.baseClass = baseClass;
    }

    @Override
    InputStream retrieveAsStream( String propertiesFileName ) {
        return baseClass.getResourceAsStream( propertiesFileName );
    }

    @Override
    public File getConfigDirectory() {
        return null;
    }

}