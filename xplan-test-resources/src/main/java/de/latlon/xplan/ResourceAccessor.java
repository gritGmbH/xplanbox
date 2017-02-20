package de.latlon.xplan;

import java.io.InputStream;

/**
 * Convenient access to resources in this module
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class ResourceAccessor {

    private ResourceAccessor() {
    }

    /**
     * Retrieves the resource identified by name
     * 
     * @param name
     *            the file name of the resource
     * @return an <link>InputStream</link> for the contents of the file
     */
    public static InputStream readResourceStream( String name ) {
        return ResourceAccessor.class.getResourceAsStream( name );
    }
}
