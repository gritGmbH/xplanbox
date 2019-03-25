package de.latlon.xplanisk2.wms;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ServletResponseWrapper extends HttpServletResponseWrapper {

    public ServletResponseWrapper( HttpServletResponse response ) {
        super( response );
    }

}